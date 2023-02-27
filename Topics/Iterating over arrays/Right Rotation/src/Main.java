import java.util.Scanner;

class Main {

     static String[] shift(String[] a, int numArray) {

        String[] b = new String[numArray];
        String last = a[numArray - 1];
        for (int i = 0; i < numArray - 1; i++) {
            b[i + 1] = a[i];
        }
        b[0] = last;
        return b;
    }



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] entrance = scanner.nextLine().split(" ");
        int num = scanner.nextInt();
        for (int i = 0; i < num % entrance.length; i++) {
            entrance = shift(entrance, entrance.length);
        }

        for (String temp : entrance) {
            System.out.print(temp + " ");
        }
    }
}