import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
            if(i > 0) {
                flag = arr[i - 1] <= arr[i];
            }
            if(!flag) break;
        }
        System.out.println(flag);
    }
}