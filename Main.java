package com.example.mud.controller;

public class Main {
    public static void main(String[] args) {
        Item sword = new Item("sword", "A sharp blade.");
        Item shield = new Item("shield", "A sturdy shield.");

        Room room1 = new Room("A small stone chamber", "It's dark and damp.");
        room1.addItem(sword);
        room1.addItem(shield);

        Room room2 = new Room("A bright sunny room", "You can see the sky.");
        room1.setForward(room2); 

        Player player = new Player("Hero", room1);

        MUDController controller = new MUDController(player);

        controller.runGameLoop();
    }
}
