package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Button ClientButton;

    @FXML
    private Button CommendeButton;

    @FXML
    private ComboBox<?> Commende_Prix;

    @FXML
    private TableColumn<ClientData, String> Commende_col_clientID;

    @FXML
    private TableColumn<ClientData, String> Commende_col_nom;

    @FXML
    private TableColumn<ClientData, String> Commende_col_phone;

    @FXML
    private TableColumn<ClientData, String> Commende_col_prix;

    @FXML
    private TableColumn<ClientData, String> Commende_col_produit;

    @FXML
    private TableColumn<ClientData, String> Commende_col_status;

    @FXML
    private AnchorPane Commende_form;

    @FXML
    private ComboBox<?> Commende_id;

    @FXML
    private ComboBox<?> Commende_nom;

    @FXML
    private Button Commende_pay;

    @FXML
    private ComboBox<?> Commende_produit;

    @FXML
    private TableView<ClientData> Commende_tableview;

    @FXML
    private Button ProduitButton;

    @FXML
    private TextField client_ID;

    @FXML
    private Button client_addBtn;

    @FXML
    private TableColumn<ClientData, String> client_col_ID;

    @FXML
    private TableColumn<ClientData, String> client_col_name;

    @FXML
    private TableColumn<ClientData, String> client_col_phone;

    @FXML
    private TableColumn<ClientData, String> client_col_prix;

    @FXML
    private TableColumn<ClientData, String> client_col_produit;

    @FXML
    private TableColumn<ClientData, String> client_col_status;

    @FXML
    private Button client_deleteBtn;

    @FXML
    private AnchorPane client_form;

    @FXML
    private TextField client_name;

    @FXML
    private TextField client_phone;

    @FXML
    private ComboBox<?> client_price;

    @FXML
    private ComboBox<?> client_produit;

    @FXML
    private Button client_resetBtn;

    @FXML
    private ComboBox<?> client_status;

    @FXML
    private Button client_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Button creditButton;

    @FXML
    private Button credit_enregistrer;

    @FXML
    private AnchorPane credit_form;

    @FXML
    private AnchorPane credit_formo;

    @FXML
    private TableView<ClientData> credit_tableview;

    @FXML
    private ComboBox<?> crediti_ID;

    @FXML
    private TableColumn<ClientData, String> crediti_col_ID;

    @FXML
    private TableColumn<ClientData, String> crediti_col_nom;

    @FXML
    private TableColumn<ClientData, String> crediti_col_phone;

    @FXML
    private TableColumn<ClientData, String> crediti_col_prix;

    @FXML
    private TableColumn<ClientData, String> crediti_col_produit;

    @FXML
    private TableColumn<ClientData, String> crediti_col_status;

    @FXML
    private ComboBox<?> crediti_nom;

    @FXML
    private ComboBox<?> crediti_prix;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button minimize;

    @FXML
    private TableView<ProduitData> produit_TableView;
    @FXML
    private TableView<ClientData> client_tableview;

    @FXML
    private Button produit_addBtn;

    @FXML
    private ComboBox<?> produit_category;

    @FXML
    private TableColumn<ProduitData, String> produit_col_ID;

    @FXML
    private TableColumn<ProduitData, String> produit_col_categorie;

    @FXML
    private TableColumn<ProduitData, String> produit_col_name;

    @FXML
    private TableColumn<ProduitData, String> produit_col_prix;

    @FXML
    private TableColumn<ProduitData, String> produit_col_status;

    @FXML
    private Button produit_deleteBtn;

    @FXML
    private AnchorPane produit_form;

    @FXML
    private TextField produit_name;

    @FXML
    private TextField produit_price;

    @FXML
    private TextField produit_produitID;

    @FXML
    private Button produit_resetBtn;

    @FXML
    private ComboBox<?> produit_status;

    @FXML
    private Button produit_updateBtn;
    //DATABASE tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    //**************************

    //Alerts function
    public void emptyFields(){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all terms");
        alert.showAndWait();
    }
    //**********************




    //!!PRODUIT

    //Produit_add_boutton

    public void produitAddBtn(){
        String Sql = "INSERT INTO produit (produit_id, name, price, category, status) "+"VALUES (?,?,?,?,?)";

        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");


            if (produit_produitID.getText().isEmpty()
                    || produit_name.getText().isEmpty()
                    || produit_price.getText().isEmpty()
                    || produit_category.getSelectionModel().getSelectedItem()==null
                    ||produit_status.getSelectionModel().getSelectedItem()==null)
            {
                emptyFields();
            }else {

                String checkData = "SELECT produit_id FROM produit WHERE produit_id = '"+produit_produitID.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()){
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("produit_id : "+produit_produitID.getText()+"was already taken!");
                    alert.showAndWait();
                }else{
                    prepare= connect.prepareStatement(Sql);
                    prepare.setString(1,produit_produitID.getText());
                    prepare.setString(2,produit_name.getText());
                    prepare.setString(3,produit_price.getText());
                    prepare.setString(4, (String) produit_category.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String) produit_status.getSelectionModel().getSelectedItem());

                    Alert alert;
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added");
                    alert.showAndWait();

                    //pour entrer data
                    prepare.executeUpdate();
                    //to update tableview
                    produitShowData();
                    //to clear data
                    produitClearButtton();
                }
            }

        }catch (Exception e){e.printStackTrace();}

    }
    //***********

    //produit Update button

    public void produitUpdateBtn(){
        String sql = "UPDATE produit SET name = '"
                +produit_name.getText()+"', Price = '"
                +produit_price.getText()+"', category = '"
                +produit_category.getSelectionModel().getSelectedItem()+"',  status = '"
                +produit_status.getSelectionModel().getSelectedItem()+"' WHERE produit_id = '"
                +produit_produitID.getText()+"'";
        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            if (produit_produitID.getText().isEmpty() || produit_name.getText().isEmpty()
                    || produit_price.getText().isEmpty()
                    || produit_category.getSelectionModel().getSelectedItem()==null
                    ||produit_status.getSelectionModel().getSelectedItem()==null)
            {
                emptyFields();
            }else{

                Alert alert;
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update"+produit_produitID.getText()+"?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated");
                    alert.showAndWait();

                    //to update tableview
                    produitShowData();
                    //to clear data
                    produitClearButtton();

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Update Canceled!!");
                    alert.showAndWait();
                }
            }

        }catch(Exception e){e.printStackTrace();}

    }
    //********************

    //clear button produit

    public void produitClearButtton(){
        produit_produitID.setText("");
        produit_name.setText("");
        produit_price.setText("");
        produit_category.getSelectionModel().clearSelection();
        produit_status.getSelectionModel().clearSelection();
    }

    //******************

    //produit delete_button

    public void produitDeletBtn(){
        String sql = "DELETE FROM produit WHERE produit_id = '"+produit_produitID.getText()+"'";
        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            if (produit_produitID.getText().isEmpty())
            {
                emptyFields();
            }else{

                Alert alert;
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to Delete "+produit_produitID.getText()+"?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    //to execute query
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();

                    //to update tableview
                    produitShowData();
                    //to clear data
                    produitClearButtton();

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Delete Cancelled!!");
                    alert.showAndWait();
                }
            }

        }catch(Exception e){e.printStackTrace();}
    }
    //******************


    //Produit data list
    public ObservableList<ProduitData> produitDataList(){
        ObservableList<ProduitData> ListData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM produit";

        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();
            ProduitData pd;
            while (result.next()){
                pd = new ProduitData(result.getInt("id"),result.getString("produit_id"), result.getString("name")
                        ,result.getDouble("price"),result.getString("category"),
                        result.getString("status"));
                ListData.add(pd);
            }



        }catch (Exception e){e.printStackTrace();}

        return ListData;
    }

    //**********************

    private ObservableList<ProduitData> produitListData;

    // produit show data
    public void produitShowData(){
        produitListData = produitDataList();
        produit_col_ID.setCellValueFactory(new PropertyValueFactory<>("produit_id"));
        produit_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        produit_col_prix.setCellValueFactory(new PropertyValueFactory<>("price"));
        produit_col_categorie.setCellValueFactory(new PropertyValueFactory<>("category"));
        produit_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));


        produit_TableView.setItems(produitListData);

    }
    //*****************

    //select Items
    public void produitSelect(){
        ProduitData pd = produit_TableView.getSelectionModel().getSelectedItem();
        int num = produit_TableView.getSelectionModel().getSelectedIndex();
        produit_produitID.setText(pd.getProduit_id());
        produit_name.setText(pd.getName());
        produit_price.setText(String.valueOf(pd.getPrice()));

    }
    //***********


    //produit category and status
    private String categories [] = {"Fruit", "legume", "nourriture"};
    private String status [] = {"Disponible", "Indisponible"};
    public void produitCategoryListe(){
        List<String> genderlist = new ArrayList<>();

        for (String data : categories){
            genderlist.add(data);
        }
        ObservableList ListData = FXCollections.observableArrayList(genderlist);
        produit_category.setItems(ListData);
    }
    public void ProduitStatusList(){
        List<String> statuslist = new ArrayList<>();

        for (String data : status){
            statuslist.add(data);
        }
        ObservableList ListData = FXCollections.observableArrayList(statuslist);
        produit_status.setItems(ListData);
    }
    //**************
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    //CLIENT

    //client ADDbtn
    public void clientAddBtn(){
        String Sql = "INSERT INTO client (clientId, name, phone, produit, status, price) "+"VALUES (?,?,?,?,?,?)";

        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");


            if (client_ID.getText().isEmpty()
                    ||client_name.getText().isEmpty()
                    || client_phone.getText().isEmpty()
                    || client_produit.getSelectionModel().getSelectedItem()==null
                    || client_status.getSelectionModel().getSelectedItem()==null
                    ||client_price.getSelectionModel().getSelectedItem()==null)
            {
                emptyFields();
            }else {

                String checkData = "SELECT clientId FROM client WHERE clientId = '"+client_ID.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()){
                    Alert alert;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("client_id : "+client_ID.getText()+"was already taken!");
                    alert.showAndWait();
                }else{
                    prepare= connect.prepareStatement(Sql);
                    prepare.setString(1,client_ID.getText());
                    prepare.setString(2,client_name.getText());
                    prepare.setString(3,client_phone.getText());
                    prepare.setString(4, (String) client_produit.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String) client_status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, (String) client_price.getSelectionModel().getSelectedItem());

                    Alert alert;
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added");
                    alert.showAndWait();

                    //pour entrer data
                    prepare.executeUpdate();
                    //to update tableview
                    clientShowData();
                    //to clear data
                    clientClearButtton();
                }
            }

        }catch (Exception e){e.printStackTrace();}

    }
    //***********

    //client_update_button
    public void clientUpdateBtn(){
        String sql = "UPDATE client SET name = '"
                +client_name.getText()+"', phone = '"
                +client_phone.getText()+"', produit = '"
                +client_produit.getSelectionModel().getSelectedItem()+"',  status = '"
                +client_status.getSelectionModel().getSelectedItem()+"', price = '"
                +client_price.getSelectionModel().getSelectedItem()+"' WHERE clientId='"
                +client_ID.getText()+"'";
        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            if (client_ID.getText().isEmpty()
                    ||client_name.getText().isEmpty()
                    || client_phone.getText().isEmpty()
                    || client_produit.getSelectionModel().getSelectedItem()==null
                    || client_status.getSelectionModel().getSelectedItem()==null
                    ||client_status.getSelectionModel().getSelectedItem()==null)
            {
                emptyFields();
            }else{

                Alert alert;
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update"+produit_produitID.getText()+"?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated");
                    alert.showAndWait();

                    //to update tableview
                    clientShowData();
                    //to clear data
                    clientClearButtton();

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Update Canceled!!");
                    alert.showAndWait();
                }
            }

        }catch(Exception e){e.printStackTrace();}

    }
    //*******************

    //client_delete_button

    public void clientDeletBtn(){
        String sql = "DELETE FROM client WHERE clientId = '"+client_ID.getText()+"'";
        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            if (client_ID.getText().isEmpty())
            {
                emptyFields();
            }else{

                Alert alert;
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to Delete "+client_ID.getText()+"?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(sql);
                    //to execute query
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();

                    //to update tableview
                    clientShowData();
                    //to clear data
                    clientClearButtton();

                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Delete Cancelled!!");
                    alert.showAndWait();
                }
            }

        }catch(Exception e){e.printStackTrace();}
    }

    //********************


    //Client clearbutton
    public void clientClearButtton(){
        client_ID.setText("");
        client_name.setText("");
        client_phone.setText("");
        client_produit.getSelectionModel().clearSelection();
        client_status.getSelectionModel().clearSelection();
        client_price.getSelectionModel().clearSelection();
    }
    //***************



    //client DatatList
    public ObservableList<ClientData> clientDataList(){
        ObservableList<ClientData> ListData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client";

        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();
            ClientData cd;
            while (result.next()){
                cd = new ClientData(result.getInt("id"),result.getString("clientId"),
                        result.getString("name")
                        ,result.getString("phone"),
                        result.getString("produit"),
                        result.getString("status"),
                        result.getDouble("price"));
                ListData.add(cd);
            }



        }catch (Exception e){e.printStackTrace();}

        return ListData;
    }
    private ObservableList<ClientData> clientListData;

    //members affisher data

    public void clientShowData(){
        clientListData = clientDataList();
        client_col_ID.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        client_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        client_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        client_col_produit.setCellValueFactory(new PropertyValueFactory<>("produit"));
        client_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        client_col_prix.setCellValueFactory(new PropertyValueFactory<>("price" ));


        client_tableview.setItems(clientListData);

    }
    //**********************

    //select Items
    public void clientSelect(){
        ClientData cd = client_tableview.getSelectionModel().getSelectedItem();
        int num = client_tableview.getSelectionModel().getSelectedIndex();
        //if((num-1) < 1) return;
        client_ID.setText(cd.getClientId());
        client_name.setText(cd.getName());
        client_phone.setText(cd.getPhone());

    }
    //***********


    //Client_PRODUIT
    public void clientProduitName(){

        String sql = "SELECT name FROM produit WHERE status = 'Disponible'";

        try{
            ObservableList listData = FXCollections.observableArrayList();
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("name"));
            }
            client_produit.setItems(listData);
            clientProduitPrice();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //****************

    //Client_PRODUIT_Price
    public void clientProduitPrice(){
        String sql = "SELECT price FROM produit WHERE name = '"+client_produit.getSelectionModel().getSelectedItem()+"'";

        try{
            ObservableList listData = FXCollections.observableArrayList();
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("price"));
            }
            client_price.setItems(listData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //********************


    // Client STatus
    private String statut [] = {"Maintenent", "Credit"};
    public void ClienttStatusList(){
        List<String> statuslist = new ArrayList<>();

        for (String data : statut){
            statuslist.add(data);
        }
        ObservableList ListData = FXCollections.observableArrayList(statuslist);
        client_status.setItems(ListData);
    }
    //****************

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    //COMMENDE

    //client DatatList
    public ObservableList<ClientData> commendeDataList(){
        ObservableList<ClientData> ListData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client ";

        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();
            ClientData cd;
            while (result.next()){
                cd = new ClientData(result.getInt("id"),result.getString("clientId"),
                        result.getString("name")
                        ,result.getString("phone"),
                        result.getString("produit"),
                        result.getString("status"),
                        result.getDouble("price"));
                ListData.add(cd);
            }



        }catch (Exception e){e.printStackTrace();}

        return ListData;
    }
    private ObservableList<ClientData> commendeListData;

    //Commende affisher data

    public void commendeShowData(){
        commendeListData = commendeDataList();
        Commende_col_clientID.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        Commende_col_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        Commende_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        Commende_col_produit.setCellValueFactory(new PropertyValueFactory<>("produit"));
        Commende_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Commende_col_prix.setCellValueFactory(new PropertyValueFactory<>("price" ));


        Commende_tableview.setItems(commendeListData);

    }
    //**********************


    //commende client Id
    public void commendeClientID(){

        String sql = "SELECT clientId FROM client WHERE status = 'Maintenent'";

        try{
            ObservableList listData = FXCollections.observableArrayList();
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("clientId"));
            }
            Commende_id.setItems(listData);
            commendeClientName();
            commendeProduitName();
            commendeProduitPrice();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //*****************************************

    //commende client name
    public void commendeClientName(){
        String sql = "SELECT name FROM client WHERE clientId = '"+Commende_id.getSelectionModel().getSelectedItem()+"'";

        try{
            ObservableList listData = FXCollections.observableArrayList();
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("name"));
            }
            Commende_nom.setItems(listData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //*********************

    // commende produit
    public void commendeProduitName(){
        String sql = "SELECT produit FROM client WHERE clientId = '"+Commende_id.getSelectionModel().getSelectedItem()+"'";

        try{
            ObservableList listData = FXCollections.observableArrayList();
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("produit"));
            }
            Commende_produit.setItems(listData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //************************

    // commende price
    public void commendeProduitPrice(){
        String sql = "SELECT price FROM client WHERE clientId = '"+Commende_id.getSelectionModel().getSelectedItem()+"'";

        try{
            ObservableList listData = FXCollections.observableArrayList();
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("price"));
            }
            Commende_Prix.setItems(listData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //******************

    // PAYER BUTTON

    public void paymentButton(){
        String sql = "UPDATE client SET status = 'Payé' WHERE clientId = '"+Commende_id.getSelectionModel().getSelectedItem()+"'";
        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare = connect.prepareStatement(sql);
            prepare.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information message!!");
            alert.setHeaderText(null);
            alert.setContentText("successfully payed!!");
            alert.showAndWait();

            commendeShowData();
            clearPayment();

        }catch(Exception e){
            e.printStackTrace();
        }

    }


    //************
    //payment clear

    public void clearPayment(){
        Commende_id.getSelectionModel().clearSelection();
        Commende_nom.getSelectionModel().clearSelection();
        Commende_produit.getSelectionModel().clearSelection();
        Commende_Prix.getSelectionModel().clearSelection();
    }

    //***********

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    //CREDIT

    // credit data liste
    public ObservableList<ClientData> creditDataList(){
        ObservableList<ClientData> ListData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client ";

        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();
            ClientData Md;
            while (result.next()){
                Md = new ClientData(result.getInt("id"),result.getString("clientId"),
                        result.getString("name")
                        ,result.getString("phone"),
                        result.getString("produit"),
                        result.getString("status"),
                        result.getDouble("price"));
                ListData.add(Md);
            }



        }catch (Exception e){e.printStackTrace();}

        return ListData;
    }
    //********************

    private ObservableList<ClientData> creditListData;

    //credit show data
    public void creditShowData(){
        creditListData = creditDataList();
        crediti_col_ID.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        crediti_col_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        crediti_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        crediti_col_produit.setCellValueFactory(new PropertyValueFactory<>("produit"));
        crediti_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        crediti_col_prix.setCellValueFactory(new PropertyValueFactory<>("price" ));


        credit_tableview.setItems(commendeListData);

    }
    //**********

    //credit client ID
    public void creditClientID(){

        String sql = "SELECT clientId FROM client WHERE status = 'Credit' ";

        try{
            ObservableList listData = FXCollections.observableArrayList();
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("clientId"));
            }
            crediti_ID.setItems(listData);
            creditClientName();
            creditProduitName();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //********************

    //credit Client name
    public void creditClientName(){
        String sql = "SELECT name FROM client WHERE clientId = '"+crediti_ID.getSelectionModel().getSelectedItem()+"'";

        try{
            ObservableList listData = FXCollections.observableArrayList();
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("name"));
            }
            crediti_nom.setItems(listData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //********************

    //credit produit nom
    public void creditProduitName(){
        String sql = "SELECT produit FROM client WHERE clientId = '"+crediti_ID.getSelectionModel().getSelectedItem()+"'";

        try{
            ObservableList listData = FXCollections.observableArrayList();
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare= connect.prepareStatement(sql);
            result=prepare.executeQuery();

            while (result.next()) {
                listData.add(result.getString("produit"));
            }
            crediti_prix.setItems(listData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //*******************

    //enregistrer button
    public void enregistrerButton(){
        String sql = "UPDATE client SET status = 'Enregistrer' WHERE clientId = '"+crediti_ID.getSelectionModel().getSelectedItem()+"'";
        try{
            connect =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esalaf","root","");
            prepare = connect.prepareStatement(sql);
            prepare.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information message!!");
            alert.setHeaderText(null);
            alert.setContentText("successfully Saved!!");
            alert.showAndWait();

            creditShowData();
            clearEnregistrement();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
    //**********************

    //clear enregistrement
    public void clearEnregistrement(){
        crediti_ID.getSelectionModel().clearSelection();
        crediti_nom.getSelectionModel().clearSelection();
        crediti_prix.getSelectionModel().clearSelection();

    }
    //*****************


    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!





    //switch form on dashbord
    public void switchForm(ActionEvent event){
        if (event.getSource()==ProduitButton){
            produit_form.setVisible(true);
            client_form.setVisible(false);
            Commende_form.setVisible(false);
            credit_form.setVisible(false);
            produitCategoryListe();
            ProduitStatusList();
            produitShowData();
        } else if (event.getSource() == ClientButton){
            produit_form.setVisible(false);
            client_form.setVisible(true);
            Commende_form.setVisible(false);
            credit_form.setVisible(false);
            clientProduitName();
            clientProduitPrice();
            clientShowData();
            ClienttStatusList();
        } else if(event.getSource() == CommendeButton){
            produit_form.setVisible(false);
            client_form.setVisible(false);
            Commende_form.setVisible(true);
            credit_form.setVisible(false);
            commendeShowData();
            commendeClientID();
            commendeClientName();
            commendeProduitName();
            commendeProduitPrice();

        } else if (event.getSource() == creditButton){
            produit_form.setVisible(false);
            client_form.setVisible(false);
            Commende_form.setVisible(false);
            credit_form.setVisible(true);
            creditShowData();
            creditClientID();
            creditClientName();
            creditProduitName();
        }
    }
    //*************



    //logout button
    public void logout (){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Logout!!");
        alert.setHeaderText(null);
        alert.setContentText("You want to leave us!!");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get().equals(ButtonType.OK)){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/demo/hello-view.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root));
                stage.show();

                logoutBtn.getScene().getWindow().hide();
            }catch (Exception e){e.printStackTrace();}

        }

    }
    //****************************************

    //minimize button

    public void minimize(){
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }
    //**************

    //exit button
    public void close(){
        javafx.application.Platform.exit();
    }
    //********

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produitCategoryListe();
        ProduitStatusList();
        produitShowData();

        clientProduitName();
        clientProduitPrice();
        clientShowData();
        ClienttStatusList();

        commendeShowData();
        commendeClientID();
        commendeClientName();
        commendeProduitName();
        commendeProduitPrice();

        creditShowData();
        creditClientID();
        creditClientName();
        creditProduitName();
    }
}
