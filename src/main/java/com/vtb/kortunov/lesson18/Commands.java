package com.vtb.kortunov.lesson18;

import com.vtb.kortunov.lesson18.entities.Customer;
import com.vtb.kortunov.lesson18.entities.Product;
import org.hibernate.Session;

public class Commands {

    public static void executor(String str, Session session) {
        String[] command = str.split(" ");
        if (command[0].equals("showProductsByConsumer")) {
            StringBuilder name = new StringBuilder();
            for (int i = 1; i < command.length; i++) {
                name.append(command[i]);
                name.append(" ");
            }
            name.setLength(name.length() - 1);
            showProductsByConsumer(name.toString(), session);
        }
        if (command[0].equals("showConsumersByProductTitle")) {
            StringBuilder title = new StringBuilder();
            for (int i = 1; i < command.length; i++) {
                title.append(command[i]);
                title.append(" ");
            }
            title.setLength(title.length() - 1);
            showConsumersByProductTitle(title.toString(), session);
        }
        if (command[0].equals("deleteConsumer")) {
            StringBuilder name = new StringBuilder();
            for (int i = 1; i < command.length; i++) {
                name.append(command[i]);
                name.append(" ");
            }
            name.setLength(name.length() - 1);
            deleteConsumer(name.toString(), session);
        }
        if (command[0].equals("deleteProduct")) {
            StringBuilder title = new StringBuilder();
            for (int i = 1; i < command.length; i++) {
                title.append(command[i]);
                title.append(" ");
            }
            title.setLength(title.length() - 1);
            deleteProduct(title.toString(), session);
        }
        if (command[0].equals("buy")) {
            Long idCustomer = Long.parseLong(command[1]);
            Long idProduct = Long.parseLong(command[2]);
            buy(idCustomer, idProduct, session);
        }
    }


    public static void showProductsByConsumer(String name, Session session) {
        Long i = 1L;
        Long j = 1L;
        Customer customer = session.get(Customer.class, i);
        Product product = session.get(Product.class, j);
        while (customer != null) {
            i++;
            if (customer.getName().equals(name)) {
                while (product != null) {
                    if (product.getCustomer().getName().equals(name)) {
                        System.out.println(product.getTitle());
                    }
                    j++;
                    product = session.get(Product.class, j);
                }
            }
            customer = session.get(Customer.class, i);
        }
        session.getTransaction().commit();
    }

    public static void showConsumersByProductTitle(String title, Session session) {
        Long i = 1L;
        Long j = 1L;
        Customer customer = session.get(Customer.class, i);
        Product product = session.get(Product.class, j);
        while (product != null) {
            i++;
            if (product.getTitle().equals(title)) {
                while (customer != null) {
                    if (customer.getId().equals(product.getCustomer().getId())) {
                        System.out.println(customer.getName());
                    }
                    j++;
                    customer = session.get(Customer.class, j);
                }
            }
            product = session.get(Product.class, i);
        }
        session.getTransaction().commit();
    }

    public static void deleteConsumer(String name, Session session) {
        Long i = 1L;
        Customer customer = session.get(Customer.class, i);
        while (customer != null) {
            if (customer.getName().equals(name)) {
                session.delete(session.get(Customer.class, i));
            }
            i++;
            customer = session.get(Customer.class, i);
        }
    }

    public static void deleteProduct(String title, Session session) {
        Long i = 1L;
        Product product = session.get(Product.class, i);
        while (product != null) {
            if (product.getTitle().equals(title)) {
                session.delete(session.get(Product.class, i));
            }
            i++;
            product = session.get(Product.class, i);
        }
    }

    public static void buy(Long idCustomer, Long idProduct, Session session) {
        Customer customer = session.get(Customer.class, idCustomer);
        Product product = session.get(Product.class, idProduct);
        product.setCustomer(customer);
    }

}
