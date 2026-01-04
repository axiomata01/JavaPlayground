public class ArmstrongNumbers {

    public static void main(String[] args) {
        int[] test = giveArmstrongNumbers(15);
        printArray(test);
    }

    public static boolean isArmstrongNumber(int number) {
        if (number < 0) {
            return false;
        }

        if (number == 0) {
            return true;
        }

        int digits = (int) Math.floor(Math.log10(number)) + 1;
        int sum = 0;
        int temp = number;

        while (temp > 0) {
            int digit = temp % 10;
            sum += (int) Math.pow(digit, digits);
            temp /= 10;
        }

        return sum == number;
    }

    public static int[] giveArmstrongNumbers(int n) {
        int[] result = new int[n];
        int count = 0;
        int number = 0;

        while (count < n) {
            if (isArmstrongNumber(number)) {
                result[count] = number;
                count++;
            }
            number++;
        }

        return result;
    }

    private static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            System.out.print(i < a.length - 1 ? ", " : "\n");
        }
    }
}