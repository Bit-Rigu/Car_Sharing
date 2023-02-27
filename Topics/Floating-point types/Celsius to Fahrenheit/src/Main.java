import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.UK);
        double C = scanner.nextDouble();
        System.out.println(C * 1.8 + 32);
    }
}