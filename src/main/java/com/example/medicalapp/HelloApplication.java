package com.example.medicalapp;

import com.example.medicalapp.controllers.LogInController;
import com.example.medicalapp.persistance.employees.MedStaffHibernateRepo;
import com.example.medicalapp.persistance.employees.PharmacistHibernateRepo;
import com.example.medicalapp.persistance.orders.OrderHibernateRepo;
import com.example.medicalapp.persistance.orders.OrderLineHibernateRepo;
import com.example.medicalapp.persistance.pharmacy.StockHibernateRepo;
import com.example.medicalapp.services.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;

public class HelloApplication extends Application {

    Service service;
    static SessionFactory sessionFactory;

    @Override
    public void start(Stage stage){

        try {
            initialize();
            MedStaffHibernateRepo medrepo = new MedStaffHibernateRepo(sessionFactory);
            OrderHibernateRepo ordrepo = new OrderHibernateRepo(sessionFactory);
            OrderLineHibernateRepo ordlrepo = new OrderLineHibernateRepo(sessionFactory);
            PharmacistHibernateRepo phrepo = new PharmacistHibernateRepo(sessionFactory);
            StockHibernateRepo strepo = new StockHibernateRepo(sessionFactory);
            service = new Service(medrepo, ordrepo, ordlrepo, phrepo, strepo);
            initView(stage);
            stage.setWidth(420);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private void initView(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginview.fxml"));
        VBox userLayout = fxmlLoader.load();
        primaryStage.setScene(new Scene(userLayout));

        LogInController userController = fxmlLoader.getController();
        userController.setService(service);
    }

    public static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Exception " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Override
    public void stop() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
