package com.example.mud.controller;

import java.util.Scanner;

public class MUDController {
    private final Player player;
    private boolean running;

    public MUDController(Player player) {
        this.player = player;
        this.running = true;
    }
    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            handleInput(input);
        }
        scanner.close();
    }
    public void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = (parts.length > 1) ? parts[1] : "";
        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                move(argument);
                break;
            case "pick":
                pickUp(argument);
                break;
            case "inventory":
                checkInventory();
                break;
            case "help":
                showHelp();
                break;
            case "quit":
            case "exit":
                running = false;
                System.out.println("Exiting game. Goodbye!");
                break;
            default:
                System.out.println("Unknown command. Type 'help' for a list of commands.");
        }
    }
    private void lookAround() {
        Room currentRoom = player.getCurrentRoom();
        System.out.println(currentRoom.getDescription());
        if (!currentRoom.getItems().isEmpty()) {
            System.out.println("Items here: " + currentRoom.getItems());
        }
    }
    private void move(String direction) {
        Room newRoom = player.getCurrentRoom().getConnectedRoom(direction);
        if (newRoom != null) {
            player.setCurrentRoom(newRoom);
            System.out.println("You moved " + direction + ".");
            lookAround();
        } else {
            System.out.println("You can't go that way!");
        }
    }
    private void pickUp(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        Item item = currentRoom.getItem(itemName);
        if (item != null) {
            player.addItem(item);
            currentRoom.removeItem(item);
            System.out.println("You picked up " + itemName + ".");
        } else {
            System.out.println("No item named " + itemName + " here!");
        }
    }
    private void checkInventory() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("You are carrying: " + player.getInventory());
        }
    }
    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("look - Describe the current room");
        System.out.println("move <direction> - Move in a specified direction");
        System.out.println("pick up <item> - Pick up an item");
        System.out.println("inventory - Show inventory");
        System.out.println("help - Show this help menu");
        System.out.println("quit/exit - Quit the game");
    }
}
