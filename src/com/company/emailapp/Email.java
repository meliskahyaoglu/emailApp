package com.company.emailapp;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Scanner;

public class Email {

    private static final Scanner IN = new Scanner(System.in);

    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private int mailboxCapacity = 500;
    private String email;
    private final int defaultPasswordLength = 10;
    private String alternateEmail;
    private final String compSuffix = "melco.com";

    // constructor to receive last name and first name
    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        System.out.println("Email created: " + this.firstName + " " + this.lastName);

        this.department = setDepartment();
        System.out.println("Department created: " + (this.department.isEmpty() ? "(none)" : this.department));

        this.password = randPassword(defaultPasswordLength);
        System.out.println("Password created: " + this.password);

        // combine elements to generate email (normalized)
        String deptPart = department.isEmpty() ? "" : department + ".";

        String normalizedFirst = normalize(firstName).toLowerCase();
        String normalizedLast = normalize(lastName).toLowerCase();

        this.email = normalizedFirst + "." + normalizedLast + "@" + deptPart + compSuffix;
        System.out.println("Your email is: " + email);
    }

    // ask department
    private String setDepartment() {
        while (true) {
            System.out.print(
                    "Departments\n" +
                            "1 for Sales\n" +
                            "2 for Development\n" +
                            "3 for Accounting\n" +
                            "0 for None\n" +
                            "Enter Department code: "
            );

            //no crash
            if (!IN.hasNextInt()) {
                IN.next(); // hatalı token'ı temizle
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            int deptChoice = IN.nextInt();

            switch (deptChoice) {
                case 1: return "sal";
                case 2: return "dev";
                case 3: return "acc";
                case 0: return "";
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // normalize Turkish characters to ASCII for email safety
    private String normalize(String input) {
        // NFD: harf + aksan ayrıştırma; sonra ASCII olmayanları at
        return Normalizer
                .normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    // generate random password
    private String randPassword(int length) {
        // İstersen TR karakterleri şifrede tutabilirsin; emailde tutmuyoruz.
        String passwordSet = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ1234567890!@$#%.";
        SecureRandom random = new SecureRandom();
        char[] pass = new char[length];

        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(passwordSet.length());
            pass[i] = passwordSet.charAt(idx);
        }
        return new String(pass);
    }

    // setters
    public void setMailboxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }

    public void setAlternateEmail(String alternate) {
        this.alternateEmail = alternate;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    // getters
    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public String getPassword() {
        return password;
    }

    public String showInfo() {
        return "DISPLAY NAME: " + firstName + " " + lastName +
                "\nCOMPANY EMAIL: " + email +
                "\nMAILBOX CAPACITY: " + mailboxCapacity + "MB";
    }
}