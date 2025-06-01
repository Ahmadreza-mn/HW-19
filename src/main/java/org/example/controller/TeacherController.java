package org.example.controller;

import org.example.model.Master;
import org.example.repository.MasterRepository;
import org.hibernate.SessionFactory;

import java.util.Scanner;

public class MasterController {
    private final MasterRepository masterRepository;
    private final Scanner scanner;

    public MasterController(SessionFactory sessionFactory) {
        this.masterRepository = new MasterRepository(sessionFactory);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n===== MASTER MANAGEMENT =====");
            System.out.println("1. Add Master");
            System.out.println("2. List Masters");
            System.out.println("3. Find Master by ID");
            System.out.println("4. Update Master");
            System.out.println("5. Delete Master");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> addMaster();
                case 2 -> listMasters();
                case 3 -> findMaster();
                case 4 -> updateMaster();
                case 5 -> deleteMaster();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addMaster() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        Master master = new Master();
        master.setName(name);

        masterRepository.save(master);
        System.out.println("Master added.");
    }

    private void listMasters() {
        List<Master> masters = masterRepository.findAll();
        if (masters.isEmpty()) {
            System.out.println("No masters found.");
        } else {
            masters.forEach(System.out::println);
        }
    }

    private void findMaster() {
        System.out.print("Enter master ID: ");
        long id = readLong();
        Master master = masterRepository.findById(id);
        System.out.println(master != null ? master : "Master not found.");
    }

    private void updateMaster() {
        System.out.print("Enter master ID: ");
        long id = readLong();
        Master master = masterRepository.findById(id);
        if (master == null) {
            System.out.println("Master not found.");
            return;
        }

        System.out.print("Enter new name: ");
        master.setName(scanner.nextLine());

        masterRepository.update(master);
        System.out.println("Master updated.");
    }

    private void deleteMaster() {
        System.out.print("Enter master ID: ");
        long id = readLong();
        Master master = masterRepository.findById(id);
        if (master == null) {
            System.out.println("Master not found.");
            return;
        }

        masterRepository.delete(master);
        System.out.println("Master deleted.");
    }

    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private long readLong() {
        try {
            return Long.parseLong(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}