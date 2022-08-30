package crocker.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        if (part != null) {
            allParts.add(part);
        }
    }
    public static void addProduct(Product product) {
        allProducts.add(product);
    }
    public static Part lookUpPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }
    public static Product lookupProduct(int productId) {
        for (Product product: allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> searchList = FXCollections.observableArrayList();
        for (Part part: allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                searchList.add(part);
            }
        }
        return searchList;
    }
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> searchList = FXCollections.observableArrayList();
        for (Product product: allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                searchList.add(product);
            }
        }
        return searchList;
    }
    public static void updatePart(int index, Part part) {
        allParts.set(index, part);
    }
    public static void updateProduct(int index, Product product) {
        allProducts.set(index, product);
    }
    public static void deletePart(Part part) {
        allParts.remove(part);
    }
    public static void deleteProduct(Product product) {
        allProducts.remove(product);
    }
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
