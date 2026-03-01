package com.company.emailapp;

public class EmailApp {

    public static void main(String[] args) {

        Email em1 = new Email("Melis", "Kahyaoglu");

        em1.setAlternateEmail("mk@gmail.com");

        System.out.println("ALTERNATE EMAIL: " + em1.getAlternateEmail());
        System.out.println("--------------------------------");
        System.out.println(em1.showInfo());
    }
}