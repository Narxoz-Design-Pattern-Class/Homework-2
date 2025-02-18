package com.example.mud.controller;

import com.example.mud.controller.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String description;
    private Map<String, com.example.mud.controller.Room> connectedRooms;
    private List<Item> items;
    public Room(String description) {
        this.description = description;
        this.connectedRooms = new HashMap<>();
        this.items = new ArrayList<>();
    }
    public String getDescription() {
        return description;
    }
    public void connectRoom(String direction, com.example.mud.controller.Room room) {
        connectedRooms.put(direction, room);
    }
    public com.example.mud.controller.Room getConnectedRoom(String direction) {
        return connectedRooms.get(direction);
    }
    public List<Item> getItems() {
        return items;
    }
    public Item getItem(String name) {
        return items.stream().filter(item -> item.getName().equals(name)).findFirst().orElse(null);
    }
    public void removeItem(Item item) {
        items.remove(item);
    }
}