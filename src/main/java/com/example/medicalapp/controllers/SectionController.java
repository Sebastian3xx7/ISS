package com.example.medicalapp.controllers;

import com.example.medicalapp.domain.Employee;
import com.example.medicalapp.domain.MedicalStaff;
import com.example.medicalapp.domain.Order;
import com.example.medicalapp.domain.OrderLine;
import com.example.medicalapp.services.Service;
import com.example.medicalapp.utils.events.ChangeEventType;
import com.example.medicalapp.utils.events.OrderChangeEntityEvent;
import com.example.medicalapp.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SectionController implements Observer<OrderChangeEntityEvent> {


    MedicalStaff med;
    Service service;
    Order ordr;

    ObservableList<OrderLine> model = FXCollections.observableArrayList();
    ObservableList<OrderLine> model2 = FXCollections.observableArrayList();
    ObservableList<Order> modelNew = FXCollections.observableArrayList();
    @FXML
    Text userLabel;

    @FXML
    TableColumn medColumn;

    @FXML
    TableColumn quantityColumn;

    @FXML
    TableColumn medCol;

    @FXML
    TableColumn canCol;

    @FXML
    TableColumn dateCol;

    @FXML
    TableColumn descCol;

    @FXML
    Button sendButton;

    @FXML
    Button sendOrderButton;

    @FXML
    TextField medField;

    @FXML
    TextField quantityField;

    @FXML
    private TableView<Order> tableForUser;

    @FXML
    TextField descriptionField;

    @FXML
    TextField medicineField;

    @FXML
    TextField cantitate;

    @FXML
    Button detailsButton;

    @FXML
    Button delButton;
    @FXML
    Button updateButton;
    @FXML
    Button updateDeleteButton;

    @FXML
    private TableView<OrderLine> requestsTable;
    @FXML
    private TableView<OrderLine> tableForReqs;



    public void setService(Service service) {
        this.service = service;
        initModelO();
        //service.addObserver(this);
        //initModel();
    }


    void initData(Employee user) {
        userLabel.setText(user.getUsername());
        initialize();
        this.med = (MedicalStaff) user;


        ordr=new Order(LocalDate.now().toString(), "asdasd", med.getIdEm());
        int min = 1; // Minimum value
        int max = 100000; // Maximum value
        int id = ThreadLocalRandom.current().nextInt(min, max + 1);
        ordr.setId(id);

    }

    private void initModel() {

        List<OrderLine> messages = ordr.getOrderLines();
        List<OrderLine> friendships = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(friendships);
    }

    private void initModel2(List<OrderLine> lines) {

        //List<OrderLine> lines = ord.getOrderLines();
        List<OrderLine> linesFinal = StreamSupport.stream(lines.spliterator(), false)
                .collect(Collectors.toList());
        model2.setAll(linesFinal);
    }

    private void initModelO() {

        System.out.println(med.getIdEm());
        List<Order> messages = service.getOrdersForUser(med.getIdEm());
        System.out.println(messages.get(0));
        List<Order> friendships = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        modelNew.setAll(friendships);
    }

    /*private void initModel() {
        List<> messages = null;
        try {
            messages = (List<Trip>) service.getAllTrips();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Trip> trips = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        Tmodel.setAll(trips);
    }*/

    void initialize() {

        medColumn.setCellValueFactory(new PropertyValueFactory<OrderLine, String>("medicine"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>("quantity"));
        requestsTable.setItems(model);

        dateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        descCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("description"));
        tableForUser.setItems(modelNew);

        medCol.setCellValueFactory(new PropertyValueFactory<OrderLine, String>("medicine"));
        canCol.setCellValueFactory(new PropertyValueFactory<OrderLine, Integer>("quantity"));
        tableForReqs.setItems(model2);


    }

    @FXML
    public void addReq(){
        String medicine=medField.getText();
        Integer quantity= Integer.valueOf(quantityField.getText());
        int min = 1; // Minimum value
        int max = 10000; // Maximum value
        int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
        OrderLine line=new OrderLine(randomNumber, medicine, quantity, ordr.getIdOrder());
        try{
            service.addToOrder(line, ordr);
            initModel();
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Added to order!");

        }catch(Exception ex){
            System.out.println(ex);
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Invalid! Not added");
        }
    }
    @FXML
    public void sendOrder(){
        try{
            String description=descriptionField.getText();
            ordr.setDescription(description);
            service.sendOrder(ordr);
            service.notifyObservers(new OrderChangeEntityEvent(ChangeEventType.UPDATE, ordr));
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Order saved!");
        }catch(Exception ex){
            System.out.println(ex);
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Invalid! Not saved");
        }
    }

    @FXML
    private void seeDetails(){
        System.out.println(tableForUser.getSelectionModel().getSelectedItem().getIdOrder());
        List<OrderLine> lines=service.getOrderlinesForOrder(tableForUser.getSelectionModel().getSelectedItem().getIdOrder());
        System.out.println(lines.get(0));
        initModel2(lines);
    }

    //aici init modelu pentru ordere !!!
    @Override
    public void update(OrderChangeEntityEvent userEntityChangeEvent) {
        initModel();
    }
}
