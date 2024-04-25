package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CashMachine cashMachine = new CashMachine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.println(cashMachine.execute(command));
        }

        scanner.close();
    }

}