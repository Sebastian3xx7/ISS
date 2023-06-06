package com.example.medicalapp.controllers;


import com.example.medicalapp.HelloApplication;
import com.example.medicalapp.domain.Employee;
import com.example.medicalapp.domain.MedicalStaff;
import com.example.medicalapp.domain.Pharmacist;
import com.example.medicalapp.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class LogInController {

    Service service;

    @FXML
    public TextField userField;
    @FXML
    public TextField passField;
    @FXML
    private Button logBtn;


    SectionController controller;

    public void setService(Service service) {
        this.service = service;
        //comboLogIn.getItems().addAll("Pharmacist", "Medical Staff");
        //service.addObserver(this);
    }

    /*public Stage showPage(Employee user) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("orderview.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        controller = loader.getController();
        controller.initData(user);
        controller.setService(service);
        stage.show();
        return stage;
    }*/

    public Stage showPage(Employee user) {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("orderview.fxml"));

        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SectionController controller = loader.getController();
        controller.initData(user);
        controller.setService(service);
        stage.show();
        return stage;
    }

    public Stage showPage2(Employee user) {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("pharmaview.fxml"));

        Stage stage = new Stage(StageStyle.DECORATED);
        try {
            stage.setScene(
                    new Scene(loader.load())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PharmacyController controller = loader.getController();
        controller.initData(user);
        controller.setService(service);
        stage.show();
        return stage;
    }

    @FXML
    protected void onLog() {

        boolean found = false;
        boolean valid = false;
        String username = userField.getText();
        String password = passField.getText();
        //String choice= (String) comboLogIn.getValue();


        if (!username.isEmpty() && !password.isEmpty()) {
            valid = true;
            MedicalStaff us = service.getMed(username, password);
            //System.out.println(us.getIdUser()+" "+us.getUsername()+" "+us.getPassword());
            if (us!=null) {
                System.out.println(us.getUsername());
                //deschid fereastra noua cu user
                showPage(us);
                found = true;
            }else{
                Pharmacist usph = service.getPharmacist(username, password);
                showPage2(usph);
                found = true;
            }

            if (!valid || !found) {
                //System.out.println(0);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "User not found!");

            }
        }




        /*if(choice.equals("Medical Staff")){

            if (!username.isEmpty() && !password.isEmpty()) {
                valid = true;
                MedicalStaff us = service.getMed(username, password);
                //System.out.println(us.getIdUser()+" "+us.getUsername()+" "+us.getPassword());
                if (us!=null) {
                    System.out.println(us.getUsername());
                    //deschid fereastra noua cu user
                    showPage(us);
                    found = true;
                }
            }
            if (!valid || !found) {
                //System.out.println(0);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "User not found!");

            }

        }

        if(choice.equals("Pharmacist")){

            if (!username.isEmpty() && !password.isEmpty()) {
                valid = true;
                Pharmacist us = service.getPharmacist(username, password);
                //System.out.println(us.getIdUser()+" "+us.getUsername()+" "+us.getPassword());
                if (us!=null) {
                    System.out.println(us.getUsername());
                    //deschid fereastra noua cu user
                    showPage2(us);
                    found = true;
                }
            }
            if (!valid || !found) {
                //System.out.println(0);
                MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "User not found!");

            }

        }*/




    }




    /*@FXML
    private void onLog(){
        String username = userField.getText();
        String password = passField.getText();
        String choice= (String) comboLogIn.getValue();
        Random rand = new Random(); //instance of random class
        int upperbound = 10000;
        int id = rand.nextInt(upperbound);


        if(choice.equals("Medical Staff")){

            MedicalStaff med=service.getMed(username, password);
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("order.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            try {
                stage.setScene(
                        new Scene(loader.load())
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            controller = loader.getController();

            try{
                User employee=service.login(toLogin,controller);
                if (employee != null){
                    controller.setService(service);
                    controller.initData(employee);
                    stage.show();

                }

            }
            catch (Exception e){
                Alert alert=new Alert(Alert.AlertType.ERROR,e.getMessage());
                alert.show();
            }
        }


    }*/



}

