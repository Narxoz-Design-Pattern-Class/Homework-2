package com.example.mud.controller;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private String description;
    private List<Item> items;
    private Room forward;
    private Room back;
    private Room left;
    private Room right;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null; // Item not found
    }

    public String listItems() {
        if (items.isEmpty()) {
            return "none";
        }
        StringBuilder itemList = new StringBuilder();
        for (Item item : items) {
            itemList.append(item.getName()).append(", ");
        }
        return itemList.substring(0, itemList.length() - 2); // Remove last comma
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setForward(Room room) {
        this.forward = room;
    }

    public Room getForward() {
        return forward;
    }

    public void setBack(Room room) {
        this.back = room;
    }

    public Room getBack() {
        return back;
    }

    public void setLeft(Room room) {
        this.left = room;
    }

    public Room getLeft() {
        return left;
    }

    public void setRight(Room room) {
        this.right = room;
    }

    public Room getRight() {
        return right;
    }
}