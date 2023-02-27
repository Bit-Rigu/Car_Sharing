// You can experiment here, it won’t be checked

import java.util.Scanner;

public class Task {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    double density = scanner.nextDouble();
    double height = scanner.nextDouble();
    double gravity = 9.8;
    System.out.println(density*height*gravity);
  }
}
