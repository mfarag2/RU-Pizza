package com.example.project_5;


import java.util.ArrayList;

/**
 * This class defines the type StoreOrder representing all the store's orders
 *  Includes methods to add/remove orders, as well as getters/setters to edit the store orders
 *
 */
public class StoreOrder implements Customizable {

    private ArrayList<Order> orders;

    /**
     * Constructor creates a new StoreOrder object with orders set as a new
     * ArrayList.
     */
    public StoreOrder() {
        orders = new ArrayList<>();
    }

    /**
     * Returns an array list containing the store's orders
     *
     * @return array list of orders.
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Adds an order to the store order database.
     *
     * @param obj Object representing the order to be added.
     * @return true if the object was added, false if otherwise (if obj is
     * already in orders or if obj is not an Order).
     */
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof Order) || orders.contains(obj)) {
            return false;
        }
        orders.add((Order) obj);
        return true;
    }

    /**
     * Removes an order from the store order database.
     *
     * @param obj Object representing the order to be removed.
     * @return true if the object was removed, false if otherwise (if obj is
     * not in orders).
     */
    @Override
    public boolean remove(Object obj) {
        if (!orders.contains(obj)) {
            return false;
        }
        orders.remove((Order) obj);
        return true;
    }

    /**
     * Returns a list of all orders in this store order database.
     *
     * @return ObservableList of all orders in this store order database.
     */
 //   public ObservableList<Order> getItems() {
   //     return FXCollections.observableList(orders);
   // }
//
//
//    /**
//     * Exports a text file representation of all orders in this store order
//     * database.
//     * Increments fileCount if a file with the current fileCount already
//     * exists.
//     *
//     * @throws IOException if an I/O error occurs.
//     */
//    public void export() throws IOException {
//        File output = new File(FILENAME + COUNT + ENDING);
//        while (output.exists()) {
//            COUNT++;
//            output = new File(FILENAME + COUNT + ENDING);
//        }
//        boolean fileMade = output.createNewFile();
//        if (fileMade) {
//            PrintWriter out = new PrintWriter(output);
//            for (Order i : orders) {
//                out.println(i);
//            }
//            out.close();
//        }
//    }
}