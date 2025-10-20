package com.application;

class Calculator {

    public static void simple(double initial, double interest, int period) {
        int i = 0;
        double acc = initial;
        do {
            double gain = initial * interest / 100;
            acc = acc + gain;
            if (i == 0) {
                System.out.println("+------------+------------+------------+------------+------------+");
                System.out.printf("| %10s | %10s | %10s | %10s | %10s |\n", "Period", "Interest", "Initial", "Gain",
                        "Total");
                System.out.println("+------------+------------+------------+------------+------------+");
            }
            System.out.printf("| %10d | %10.2f | %10.2f | %10.2f | %10.2f |\n", i, interest, initial, gain, acc);
            if (i == period) {
                System.out.println("+------------+------------+------------+------------+------------+");
            }
            i += 1;
        } while (i <= period);
    }

    public static void composite(double initial, double interest, int period) {
        int i = 0;
        double acc = initial;
        do {
            double gain = acc * interest / 100;
            acc = acc + gain;
            if (i == 0) {
                System.out.println("+------------+------------+------------+------------+------------+");
                System.out.printf("| %10s | %10s | %10s | %10s | %10s |\n", "Period", "Interest", "Initial", "Gain",
                        "Total");
                System.out.println("+------------+------------+------------+------------+------------+");
            }
            System.out.printf("| %10d | %10.2f | %10.2f | %10.2f | %10.2f |\n", i, interest, initial, gain, acc);
            if (i == period) {
                System.out.println("+------------+------------+------------+------------+------------+");
            }
            i += 1;
        } while (i <= period);
    }

}
