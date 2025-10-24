package com.application;

import java.util.Scanner;
public class Depreciated {
    public static void main(String[] args) {
        System.out.println("Calculate ammount after *-*");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Initial ammount: ");
        double initial = scanner.nextDouble();
        System.out.print("Interest rate (%): ");
        double interest = scanner.nextDouble();
        System.out.print("Period (months): "); 
        int period = scanner.nextInt();
        System.out.println("Composite Interest:");
        Calculator.composite(initial, interest, period);
        System.out.println("Simple Interest:");
        Calculator.simple(initial, interest, period);
        scanner.close();
    }
}

