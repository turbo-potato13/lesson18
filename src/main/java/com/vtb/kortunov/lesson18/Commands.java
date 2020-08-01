package com.vtb.kortunov.lesson18;

import com.vtb.kortunov.lesson18.entities.Customer;
import com.vtb.kortunov.lesson18.entities.Product;

import javax.persistence.Query;
import java.util.List;

public class Commands {

    private final DbManagement dbManagement;

    public Commands(DbManagement dbManagement) {
        this.dbManagement = dbManagement;
    }

    public void executor(String str) {
        String[] command = str.split(" ");
        StringBuilder name = new StringBuilder();
        for (int i = 1; i < command.length; i++) {
            name.append(command[i]);
            name.append(" ");
        }
        name.setLength(name.length() - 1);
        if (command[0].equals("showProductsByConsumer")) {
            showProductsByConsumer(name.toString());
        }
        if (command[0].equals("showConsumersByProductTitle")) {
            showConsumersByProductTitle(name.toString());
        }
        if (command[0].equals("deleteConsumer")) {
            deleteConsumer(name.toString());
        }
        if (command[0].equals("deleteProduct")) {
            deleteProduct(name.toString());
        }
        if (command[0].equals("buy")) {
            Long idCustomer = Long.parseLong(command[1]);
            Long idProduct = Long.parseLong(command[2]);
            buy(idCustomer, idProduct);
        }
    }

    public void showProductsByConsumer(String name) {
        dbManagement.beginTransaction();
        List<Product> products = dbManagement.getProductsByCustomer(name);
        for (Product product : products) {
            System.out.println(product.getTitle());
        }
        dbManagement.commitAndClose();
    }

    public void showConsumersByProductTitle(String title) {
        dbManagement.beginTransaction();
        Query query = dbManagement.getSession().createQuery("SELECT p FROM product p WHERE p.title = :title", Product.class);
        query.setParameter("title", title);
        Product product = (Product) query.getSingleResult();
        System.out.println(product.getCustomer().getName());
        dbManagement.commitAndClose();
    }

    public void deleteConsumer(String name) {
        dbManagement.beginTransaction();
        dbManagement.deleteConsumer(name);
        dbManagement.commitAndClose();
    }

    public void deleteProduct(String title) {
        dbManagement.beginTransaction();
        dbManagement.deleteProduct(title);
        dbManagement.commitAndClose();
    }

    public void buy(Long idCustomer, Long idProduct) {
        dbManagement.beginTransaction();
        Customer customer = dbManagement.getSession().get(Customer.class, idCustomer);
        Product product = dbManagement.getSession().get(Product.class, idProduct);
        product.setCustomer(customer);
        dbManagement.getSession().save(product);
        dbManagement.commitAndClose();
    }

}
