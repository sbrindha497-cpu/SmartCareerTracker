package com.careertracker.util;

import java.time.LocalDate;
import java.util.Scanner;

public class InputUtil {

    private static final Scanner sc = new Scanner(System.in);

    public static String readString(String label) {
        System.out.print(label + " : ");
        return sc.nextLine().trim();
    }

    public static int readInt(String label) {
        while (true) {
            try {
                System.out.print(label + " : ");
                return Integer.parseInt(sc.nextLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static double readDouble(String label) {
        while (true) {
            try {
                System.out.print(label + " : ");
                return Double.parseDouble(sc.nextLine().trim());
            } 
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (example 6.0).");
            }
        }
    }

    public static LocalDate readDate(String label) {
        while (true) {
            try {
                System.out.print(label + " (yyyy-MM-dd) : ");
                return LocalDate.parse(sc.nextLine().trim());
            } 
            catch (Exception e) {
                System.out.println("Invalid date. Example: 2026-10-08");
            }
        }
    }

    public static String choose(String title, String... options) {
        System.out.println(title);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        while (true) {
            int c = readInt("Choose");
            if (c >= 1 && c <= options.length) {
                return options[c - 1];
            }
            System.out.println("Invalid choice.");
        }
    }
}
