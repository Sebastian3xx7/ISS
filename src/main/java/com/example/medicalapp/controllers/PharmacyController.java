package com.example.medicalapp.controllers;

import com.example.medicalapp.domain.*;
import com.example.medicalapp.services.Service;
import com.example.medicalapp.utils.events.OrderChangeEntityEvent;
import com.example.medicalapp.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PharmacyController implements Observer<OrderChangeEntityEvent> {


    Pharmacist pharmacist;
    Service service;
    ObservableList<Stock> modelStock = FXCollections.observableArrayList();
    ObservableList<Order> modelOrder = FXCollections.observableArrayList();

    ObservableList<OrderLine> modelOrderLine = FXCollections.observableArrayList();

    @FXML
    Text textPharm;
    @FXML
    TableColumn medicineCol;
    @FXML
    TableColumn quantityCol;
    @FXML
    TableColumn dateColumn;
    @FXML
    TableColumn descriptionCol;
    @FXML
    TableColumn skuCol;
    @FXML
    TableColumn medDetailsCol;
    @FXML
    TableColumn quantityDetailsCol;
    @FXML
    Button completeOrdButton;
    @FXML
    Button updateStockButton;
    @FXML
    TextField nameField;
    @FXML
    TextField quantityField;
    @FXML
    Button selectButton;
    @FXML
    Button logOutButton;


    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableView<Stock> stockTable;

    @FXML
    private TableView<OrderLine> orderDetailsTable;



    public void setService(Service service) {
        this.service = service;
        initModelOrders();
        initModelStock();
        //service.addObserver(this);
        //initModel();
    }


    void initData(Employee user) {
        textPharm.setText(user.getUsername());
        initialize();
        this.pharmacist = (Pharmacist) user;

    }

    private void initModelStock() {

        List<Stock> stock = service.getAllStock();
        List<Stock> stockFinal = StreamSupport.stream(stock.spliterator(), false)
                .collect(Collectors.toList());
        modelStock.setAll(stockFinal);
    }

    private void initModelOrders() {

        List<Order> orders = service.getAllOrders();
        List<Order> ordersFinal = StreamSupport.stream(orders.spliterator(), false)
                .collect(Collectors.toList());
        modelOrder.setAll(ordersFinal);
    }

    private void initModelOrderLines(List<OrderLine> lines) {

        //List<OrderLine> lines = ord.getOrderLines();
        List<OrderLine> linesFinal = StreamSupport.stream(lines.spliterator(), false)
                .collect(Collectors.toList());
        modelOrderLine.setAll(linesFinal);
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

        medicineCol.setCellValueFactory(new PropertyValueFactory<Stock, String>("medicine"));
        skuCol.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockKeepingUnit"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("quantity"));
        stockTable.setItems(modelStock);

        dateColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Order, String>("description"));
        orderTable.setItems(modelOrder);

        medDetailsCol.setCellValueFactory(new PropertyValueFactory<OrderLine, String>("medicine"));
        quantityDetailsCol.setCellValueFactory(new PropertyValueFactory<OrderLine, String>("quantity"));
        orderDetailsTable.setItems(modelOrderLine);


    }

    @FXML
    private void updateStock(){
        String med=nameField.getText();
        String quan=quantityField.getText();
        service.updateStock(med, Integer.valueOf(quan));
        initModelStock();
    }

    @FXML
    private void completeOrder(){
        Order ord=orderTable.getSelectionModel().getSelectedItem();
        if(service.completeOrder(ord)){
            initModelOrders();
            initModelStock();
            List<OrderLine> ln=new ArrayList<>();
            ln.add(new OrderLine(1, "-- Order Finished --", 0, 1));
            initModelOrderLines(ln);
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Order complete!");
        }else{
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Order could not be completed!");

        }

    }

    @FXML
    private void logOut(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void seeOrders(){
        System.out.println(orderTable.getSelectionModel().getSelectedItem().getIdOrder());
        List<OrderLine> lines=service.getOrderlinesForOrder(orderTable.getSelectionModel().getSelectedItem().getIdOrder());
        initModelOrderLines(lines);
    }

    @Override
    public void update(OrderChangeEntityEvent userEntityChangeEvent) {
        initModelOrders();
    }
}
