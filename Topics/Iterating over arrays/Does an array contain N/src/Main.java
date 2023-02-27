import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        int number = scanner.nextInt();
        boolean flag = false;
        for(int temp : arr) {
            flag = temp == number;
            if (flag) break;
        }
        System.out.println(flag);
    }
}