import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Volunteer {
    String name;
    String contact;
    ArrayList<String> activities;

    public Volunteer(String name, String contact) {
        this.name = name;
        this.contact = contact;
        this.activities = new ArrayList<>();
    }

    public void logActivity(String activity) {
        activities.add(activity);
    }

    public void displayActivities() {
        if (activities.isEmpty()) {
            System.out.println("No activities logged yet.");
        } else {
            System.out.println("Activities:");
            for (String activity : activities) {
                System.out.println("- " + activity);
            }
        }
    }
}

public class VolunteerTrackerApp {
    private static HashMap<String, Volunteer> volunteers = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Local Volunteer Tracker");
        System.out.println("Commands: register, log, view, list, exit");

        while (true) {
            System.out.print("\nEnter command: ");
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "register":
                    registerVolunteer(scanner);
                    break;
                case "log":
                    logActivity(scanner);
                    break;
                case "view":
                    viewVolunteer(scanner);
                    break;
                case "list":
                    listVolunteers();
                    break;
                case "exit":
                    System.out.println("Thank you for contributing to the community!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }

    private static void registerVolunteer(Scanner scanner) {
        System.out.print("Enter volunteer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact information: ");
        String contact = scanner.nextLine();

        if (volunteers.containsKey(name)) {
            System.out.println("Volunteer already registered.");
        } else {
            volunteers.put(name, new Volunteer(name, contact));
            System.out.println("Volunteer registered successfully!");
        }
    }

    private static void logActivity(Scanner scanner) {
        System.out.print("Enter volunteer name: ");
        String name = scanner.nextLine();

        Volunteer volunteer = volunteers.get(name);
        if (volunteer == null) {
            System.out.println("Volunteer not found. Please register first.");
            return;
        }

        System.out.print("Enter activity description: ");
        String activity = scanner.nextLine();

        volunteer.logActivity(activity);
        System.out.println("Activity logged successfully!");
    }

    private static void viewVolunteer(Scanner scanner) {
        System.out.print("Enter volunteer name: ");
        String name = scanner.nextLine();

        Volunteer volunteer = volunteers.get(name);
        if (volunteer == null) {
            System.out.println("Volunteer not found.");
        } else {
            System.out.println("Volunteer: " + volunteer.name);
            System.out.println("Contact: " + volunteer.contact);
            volunteer.displayActivities();
        }
    }

    private static void listVolunteers() {
        if (volunteers.isEmpty()) {
            System.out.println("No volunteers registered yet.");
        } else {
            System.out.println("Registered Volunteers:");
            for (String name : volunteers.keySet()) {
                System.out.println("- " + name);
            }
        }
    }
}