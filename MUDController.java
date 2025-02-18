package com.example.mud.controller;

import java.util.Scanner;

public class MUDController {
    private Player player;
    private boolean running;

    public MUDController(Player player) {
        this.player = player;
        this.running = true;
    }

    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();
            handleInput(input);
        }
        scanner.close();
    }

    private void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

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
                break;
            default:
                System.out.println("Unknown command.");
                break;
        }
    }

    private void lookAround() {
        Room currentRoom = player.getCurrentRoom();
        System.out.println("Room: " + currentRoom.getName());
        System.out.println(currentRoom.getDescription());
        System.out.println("Items here: " + currentRoom.listItems());
        System.out.println("No NPCs present."); // Assuming no NPCs for simplicity
    }

    private void move(String direction) {
        Room currentRoom = player.getCurrentRoom();
        Room nextRoom = null;

        switch (direction.toLowerCase()) {
            case "forward":
                nextRoom = currentRoom.getForward();
                break;
            case "back":
                nextRoom = currentRoom.getBack();
                break;
            case "left":
                nextRoom = currentRoom.getLeft();
                break;
            case "right":
                nextRoom = currentRoom.getRight();
                break;
            default:
                System.out.println("You can't go that way!");
                return;
        }

        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            System.out.println("You move " + direction + ".");
        } else {
            System.out.println("You can't go that way!");
        }
    }

    private void pickUp(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        Item item = currentRoom.removeItem(itemName);

        if (item != null) {
            player.addItemToInventory(item);
            System.out.println("You pick up the " + itemName + ".");
        } else {
            System.out.println("No item named " + itemName + " here!");
        }
    }

    private void checkInventory() {
        System.out.println("You are carrying:");
        for (Item item : player.getInventory()) {
            System.out.println("- " + item.getName());
        }
    }

    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("look");
        System.out.println("move <forward|back|left|right>");
        System.out.println("pick up <itemName>");
        System.out.println("inventory");
        System.out.println("help");
        System.out.println("quit/exit");
    }
}