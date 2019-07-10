package helpers;

import java.util.Scanner;

public class Add {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter numbers to add: ");
        System.out.println("Addition is "+sc.nextInt()+sc.nextInt());
    }
}
