import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Category {
    String name;
    List<Category> subcategories;

    Category(String name) {
        this.name = name;
        this.subcategories = new ArrayList<>();
    }

    void addSubcategory(Category subcategory) {
        subcategories.add(subcategory);
    }
}

class CategoryTree {
    Category root;

    CategoryTree(String rootName) {
        this.root = new Category(rootName);
    }

    Category findCategory(Category current, String name) {
        if (current == null) return null;
        if (current.name.equalsIgnoreCase(name)) return current;

        for (Category subcategory : current.subcategories) {
            Category found = findCategory(subcategory, name);
            if (found != null) return found;
        }
        return null;
    }

    void addCategory(String parentName, String categoryName) {
        Category parent = findCategory(root, parentName);
        if (parent != null) {
            parent.addSubcategory(new Category(categoryName));
            System.out.println("Added subcategory " + categoryName + " under " + parentName);
        } else {
            System.out.println("Parent category " + parentName + " not found!");
        }
    }

    void displayTree(Category current, String prefix) {
        if (current == null) return;
        System.out.println(prefix + current.name);
        for (Category subcategory : current.subcategories) {
            displayTree(subcategory, prefix + "--");
        }
    }

    void displayTree() {
        displayTree(root, "");
    }
}

public class CategoryManagerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the root category:");
        String rootName = scanner.nextLine();
        CategoryTree categoryTree = new CategoryTree(rootName);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Category");
            System.out.println("2. Display Categories");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter parent category name: ");
                    String parentName = scanner.nextLine();
                    System.out.print("Enter new category name: ");
                    String categoryName = scanner.nextLine();
                    categoryTree.addCategory(parentName, categoryName);
                    break;
                case 2:
                    System.out.println("\nCategory Tree:");
                    categoryTree.displayTree();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}