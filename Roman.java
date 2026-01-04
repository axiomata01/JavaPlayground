public class Roman {
    public static void main(String[] args) {
        int n = Integer .parseInt(args[0]);
        System.out.println(roman(n));
    }
    static String roman(int n) {
    if (n == 0) {
        return "";
    }
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    for (int i = 0; i < values.length;i++) {
if (n >= values[i]) {
    return symbols[i] + roman(n - values[i]);
}
    }
    return "";
}
}
