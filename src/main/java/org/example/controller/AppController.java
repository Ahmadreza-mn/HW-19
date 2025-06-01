package org.example.controller;

import org.example.model.Student;
import org.example.repository.MasterRepository;
import org.example.repository.StudentRepository;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class AppController {
    private final StudentRepository studentRepository;
    private final MasterRepository masterRepository;
    private final Scanner scanner;

    public AppController(SessionFactory sessionFactory) {
        this.studentRepository = new StudentRepository(sessionFactory);
        this.masterRepository = new MasterRepository(sessionFactory);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Student Management");
            System.out.println("2. Master Management");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> new StudentController(studentRepository).run();
                case 2 -> new MasterController(masterRepository.getSessionFactory()).run();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}