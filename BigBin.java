/**
 * Die Klasse BigBin implementiert eine Bibliothek für die Arithmetik mit beliebig
 * großen, nicht-negativen ganzen Zahlen.
 */
public class BigBin {

    /* ===================== Ausgabe ===================== */

    public static void print(boolean[] n) {
        if (equal(n, Zero())) {
            System.out.print("0");
            return;
        }

        boolean[] tmp = copy(n);
        StringBuilder sb = new StringBuilder();

        while (!equal(tmp, Zero())) {
            int r = divmod10(tmp);
            sb.append(r);
            tmp = trim(tmp);
        }

        System.out.print(sb.reverse().toString());
    }

    /* ===================== Konstruktoren ===================== */

    public static boolean[] Zero() {
        return new boolean[] { false };
    }

    public static boolean[] One() {
        return new boolean[] { true };
    }

    public static boolean[] copy(boolean[] n) {
        boolean[] c = new boolean[n.length];
        for (int i = 0; i < n.length; i++) {
            c[i] = n[i];
        }
        return c;
    }

    public static boolean[] fromInt(int n) {
        if (n == 0) return Zero();

        boolean[] tmp = new boolean[32];
        int i = 0;
        while (n > 0) {
            tmp[i++] = (n % 2 == 1);
            n /= 2;
        }

        boolean[] res = new boolean[i];
        for (int j = 0; j < i; j++) res[j] = tmp[j];
        return res;
    }

    /* ===================== Vergleiche ===================== */

    public static boolean equal(boolean[] a, boolean[] b) {
        a = trim(a);
        b = trim(b);

        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    public static boolean less(boolean[] a, boolean[] b) {
        a = trim(a);
        b = trim(b);

        if (a.length != b.length)
            return a.length < b.length;

        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] != b[i])
                return !a[i] && b[i];
        }
        return false;
    }

    /* ===================== Grundoperationen ===================== */

    public static boolean[] times2(boolean[] n) {
        if (equal(n, Zero())) return Zero();

        boolean[] r = new boolean[n.length + 1];
        for (int i = 0; i < n.length; i++) {
            r[i + 1] = n[i];
        }
        return r;
    }

    public static boolean[] div2(boolean[] n) {
        if (n.length == 1) return Zero();

        boolean[] r = new boolean[n.length - 1];
        for (int i = 1; i < n.length; i++) {
            r[i - 1] = n[i];
        }
        return trim(r);
    }

    public static boolean[] add(boolean[] a, boolean[] b) {
        int max = Math.max(a.length, b.length);
        boolean[] r = new boolean[max + 1];

        boolean carry = false;
        for (int i = 0; i < max; i++) {
            boolean ai = i < a.length && a[i];
            boolean bi = i < b.length && b[i];

            r[i] = ai ^ bi ^ carry;
            carry = (ai && bi) || (ai && carry) || (bi && carry);
        }
        r[max] = carry;
        return trim(r);
    }

    /* ===================== Multiplikation ===================== */

    public static boolean[] times(boolean[] a, boolean[] b) {
        boolean[] result = Zero();
        boolean[] x = copy(a);
        boolean[] y = copy(b);

        while (!equal(y, Zero())) {
            if (y[0]) {
                result = add(result, x);
            }
            x = times2(x);
            y = div2(y);
        }
        return result;
    }

    /* ===================== Dezimalausgabe ===================== */

    private static int divmod10(boolean[] n) {
        int rest = 0;

        for (int i = n.length - 1; i >= 0; i--) {
            rest = rest * 2 + (n[i] ? 1 : 0);
            if (rest >= 10) {
                n[i] = true;
                rest -= 10;
            } else {
                n[i] = false;
            }
        }
        return rest;
    }

    /* ===================== Hilfsmethode ===================== */

    private static boolean[] trim(boolean[] n) {
        int i = n.length - 1;
        while (i > 0 && !n[i]) i--;

        if (i == n.length - 1) return n;

        boolean[] r = new boolean[i + 1];
        for (int j = 0; j <= i; j++) r[j] = n[j];
        return r;
    }

    /* ===================== Testprogramm ===================== */

    public static void main(String[] s) {
        boolean[] a = One();
        for (int i = 0; i < 33222; ++i) {
            a = times2(a);
        }
        System.out.print("2^33222 = ");
        print(a);
        System.out.println();

        boolean[] b = fromInt(13);
        boolean[] c = copy(b);
        for (int i = 1; i < 8978; ++i) {
            c = times(c, b);
        }
        System.out.print("13^8978 = ");
        print(c);
        System.out.println();

        System.out.println("2^33222 < 13^8978 is " + less(a, c));
    }
}
