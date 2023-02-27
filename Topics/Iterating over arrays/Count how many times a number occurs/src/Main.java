import java.util.Scanner;

class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int length = scanner.nextInt();
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
        arr[i] = scanner.nextInt();
    }
    int num = scanner.nextInt();
    int count = 0;
    for (int temp : arr) {
        if (num == temp) {
            count++;
        }
    }
    System.out.println(count);
    }
}