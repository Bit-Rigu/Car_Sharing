import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] a = new int[num];
        int[] b = new int[num];
        for (int i = 0; i < num; i++) {
            a[i] = scanner.nextInt();
        }
        int last = a[num - 1];
        for (int i = 0; i < num - 1; i++) {
            b[i + 1] = a[i];
        }
        b[0] = last;
        for (int temp : b) {
            System.out.print(temp + " ");
        }
    }
}