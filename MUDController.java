package mud_game;
import java.util.List;
import java.util.Scanner;

public class MUDController {
    private Player player;
    private boolean running;
    private final Scanner scanner;

    public MUDController(Player player) {
        this.player = player;
        this.running = true;
        this.scanner = new Scanner(System.in);
    }

    public void runGameLoop() {
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            handleInput(input);
        }
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
                if (argument.startsWith("up ")) pickUp(argument.substring(3));
                else System.out.println("Unknown command.");
                break;
            case "inventory":
                checkInventory();
                break;
            case "talk":
                talkToNPC(argument);
                break;
            case "help":
                showHelp();
                break;
            case "quit":
            case "exit":
                running = false;
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Unknown command.");
        }
    }
    private void lookAround() {
        System.out.println(player.getCurrentRoom().describe());
    }

    private void move(String direction) {
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            System.out.println("You move " + direction + ".");
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
            System.out.println("You pick up the " + itemName + ".");
        } else {
            System.out.println("No item named " + itemName + " here!");
        }
    }

    private void checkInventory() {
        List<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("You are carrying nothing.");
        } else {
            System.out.println("You are carrying:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName());
            }
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


    private void talkToNPC(String npcName) {
        for (NPC npc : player.getCurrentRoom().getNPCs()) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                System.out.println(npc.talk());
                return;
            }
        }
        System.out.println("No NPC named " + npcName + " here!");
    }
    public static void main(String[] args) {
        Room startRoom = new Room("Starting Room", "A dimly lit room with stone walls.");
        Room nextRoom = new Room("Hallway", "A long, dark hallway.");
        startRoom.setExit("forward", nextRoom);
        startRoom.addItem(new Item("sword"));
        startRoom.addItem(new Item("shield"));
        startRoom.addNPC(new NPC("Old Man", "Beware of the dangers ahead!"));

        Player player = new Player("Hero", startRoom);
        MUDController controller = new MUDController(player);
        controller.runGameLoop();
    }
}
