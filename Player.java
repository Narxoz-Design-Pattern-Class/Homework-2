package com.example.mud.controller;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private List<Item> inventory;
    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
        this.inventory = new ArrayList<>();
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }
    public List<Item> getInventory() {
        return inventory;
    }
    public void addItem(Item item) {
        inventory.add(item);
    }
}