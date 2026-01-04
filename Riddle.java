public class Riddle {

    static int count = 0;

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int[] arr = new int[2 * N];
        solve(arr, N);

        if (count == 0) System.out.println("keine Lösung");
        else if (N < 10 && count == 1) System.out.println("eine Lösung");
        else if (N >= 10) System.out.println(count + " Lösungen");
        else System.out.println(count + " Lösungen");
    }

    static void solve(int[] arr, int k) {
        if (k == 0) {
            if (arr.length <= 18) {
                for (int num : arr) System.out.print(num);
                System.out.println();
            }
            count++;
            return;
        }

        for (int i = 0; i + k + 1 < arr.length; i++) {
            if (arr[i] == 0 && arr[i + k + 1] == 0) {
                arr[i] = k;
                arr[i + k + 1] = k;
                solve(arr, k - 1);
                arr[i] = 0;
                arr[i + k + 1] = 0;
            }
        }
    }
}
