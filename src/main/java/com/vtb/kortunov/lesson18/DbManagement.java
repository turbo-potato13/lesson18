package com.vtb.kortunov.lesson18;

import com.vtb.kortunov.lesson18.entities.Customer;
import com.vtb.kortunov.lesson18.entities.Product;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class DbManagement {

    private Session session;
    private final PrepareDataApp dataApp;

    public DbManagement(Session session, PrepareDataApp dataApp) {
        this.session = session;
        this.dataApp = dataApp;
    }

    public void beginTransaction() {
        session = dataApp.getSession();
        session.beginTransaction();
    }

    public void commitAndClose() {
        session.getTransaction().commit();
        session.close();
    }

    public Customer getCustomer(String name) {
        Query query = session.createQuery("SELECT c FROM customer c WHERE c.name = :name", Customer.class);
        query.setParameter("name", name);
        return (Customer) query.getSingleResult();
    }

    public List<Product> getProductsByCustomer(String name) {
        Customer customer = getCustomer(name);
        return customer.getProducts();
    }

    public void deleteConsumer(String name) {
        Query query = session.createQuery("DELETE FROM customer c WHERE c.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    public Session getSession() {
        return session;
    }

    public void deleteProduct(String name) {
        Query query = session.createQuery("DELETE FROM product p WHERE c.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }
}
