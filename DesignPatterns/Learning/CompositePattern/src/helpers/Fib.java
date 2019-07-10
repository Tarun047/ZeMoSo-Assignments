package helpers;

import java.util.Scanner;

public class Fib {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of fibbonaci terms needed: ");
        int n=sc.nextInt(),a=0,b=1,c=a+b;
        while (n-->0)
        {
            System.out.println(c);
            a=b;
            b=c;
        }
    }
}
