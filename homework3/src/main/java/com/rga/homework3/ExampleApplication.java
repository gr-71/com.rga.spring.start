package com.rga.homework3;

import org.hibernate.cfg.Configuration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ExampleApplication {

    private static <T> void createEntity(EntityManager em, T entity){
        // Transaction
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        System.out.println("Our entity has already been created.");
    }

    private static <T> T readEntity(EntityManager em, Class<T> tClass, long id){
        // Transaction
        em.getTransaction().begin();
        T person = em.find(tClass, id);
        em.getTransaction().commit();
        System.out.println("Accordingly with reading our entity is " + person + ".");
        return person;
    }

    private static void createCustomer(EntityManager em, String name){
        Customer customer = new Customer();
        customer.setName(name);
        List<Order> order = em.createQuery
                ("SELECT o FROM Order o WHERE o.customer.id = customer.id").getResultList();
        customer.setOrders(order);
        createEntity(em, customer);
    }

    private static void createProduct(EntityManager em, String title, float price){
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        List<Order> order = em.createQuery
                ("SELECT o FROM Order o WHERE o.product.id = product.id").getResultList();
        product.setOrders(order);
        createEntity(em, product);
    }

    private static void createOrder(EntityManager em, long customerId, long productId){
        Order order = new Order();
        Customer customer = readEntity(em, Customer.class, customerId);
        Product product = readEntity(em, Product.class, productId);
        customer.getOrders().add(order);
        product.getOrders().add(order);
        order.setCustomer(customer);
        order.setProduct(product);
        createEntity(em, order);
    }
    private static void toFindCustomerByProduct(EntityManager em, long id){
        // Transaction
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(product);
        System.out.println("____________________________________________________");
        System.out.println("Our found customers are: ");
        for (Order o: product.getOrders()) {
            System.out.println(o.getCustomer().getName());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
        em.getTransaction().commit();
    }

    private static void toFindProductByCustomer(EntityManager em, long id){
        // Transaction
        em.getTransaction().begin();
        Customer customer = em.find(Customer.class, id);
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(customer);
        System.out.println("____________________________________________________");
        System.out.println("Our found products are: ");
        for (Order o: customer.getOrders()) {
            System.out.println(o.getProduct().getTitle());
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
        em.getTransaction().commit();
    }

    private static void toRemoveProductById(EntityManager em, long id){
        // Transaction
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Order o WHERE o.product.id = :id")
                .setParameter("id", id).executeUpdate();
        em.createQuery("DELETE FROM Product c WHERE id = :id")
                .setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
    }

    private static void toRemoveCustomerById(EntityManager em, long id){
        // Transaction
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Order o WHERE o.customer.id = :id")
                .setParameter("id", id).executeUpdate();
        em.createQuery("DELETE FROM Customer c WHERE id = :id")
                .setParameter("id", id).executeUpdate();
        em.getTransaction().commit();
    }

    public static void main(String[] args) {
        EntityManagerFactory managerFactory = new Configuration()
                .configure("hibernate_file.cfg.xml")
                .buildSessionFactory();

        EntityManager em = managerFactory.createEntityManager();

        createProduct(em, "Rice", 38.20f);
        createProduct(em, "Juice", 56.70f);
        createProduct(em, "Chocolate", 44.80f);
        createProduct(em, "Butter", 73.50f);

        createCustomer(em, "Ivanov");
        createCustomer(em, "Petrov");
        createCustomer(em, "Sidorov");
        createCustomer(em, "Bobrov");

        createOrder(em, 1, 4);
        createOrder(em, 1, 3);
        createOrder(em, 1, 1);
        createOrder(em, 2, 4);
        createOrder(em, 3, 2);
        createOrder(em, 3, 4);
        createOrder(em, 4, 3);

        toFindProductByCustomer(em, 3);
        toFindCustomerByProduct(em, 4);
        toRemoveProductById(em, 4);
        toRemoveCustomerById(em,1);
    }
}
