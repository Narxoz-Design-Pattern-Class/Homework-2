package com.example.mud.controller;

public class Main {
    public static void main(String[] args) {
        // Create items
        Item sword = new Item("sword", "A sharp blade.");
        Item shield = new Item("shield", "A sturdy shield.");

        // Create rooms
        Room room1 = new Room("A small stone chamber", "It's dark and damp.");
        room1.addItem(sword);
        room1.addItem(shield);

        Room room2 = new Room("A bright sunny room", "You can see the sky.");
        room1.setForward(room2); // Connect room1 to room2

        // Create a player
        Player player = new Player("Hero", room1);

        // Create the MUD controller
        MUDController controller = new MUDController(player);

        // Start the game loop
        controller.runGameLoop();
    }
}