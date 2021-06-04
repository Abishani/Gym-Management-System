package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ViewDetails extends Application {
    private TableView  memDetails = new TableView();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("ONLINE GYM MANAGEMENT SYSTEM");

        final Label lb = new Label("MEMBER DETAILS");

        memDetails.setEditable(false);

        //table columns

        TableColumn<Integer, DefaultMember> memNum = new TableColumn<>("Membership Number");
        memNum.setCellValueFactory(new PropertyValueFactory<>("membershipNumber"));
        memNum.setPrefWidth(150);



        TableColumn<String, DefaultMember> name = new TableColumn<>("Member Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(200);

        TableColumn<String, DefaultMember> date = new TableColumn<>("Start Membership Date");
        date.setCellValueFactory(new PropertyValueFactory<>("startMembershipDate"));
        date.setPrefWidth(200);


        TableColumn<String, DefaultMember> phoneNO = new TableColumn<>("Phone Number");
        phoneNO.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNO.setPrefWidth(150);

        TableColumn<String, StudentMember> schName = new TableColumn<>("School Name");
        schName.setCellValueFactory(new PropertyValueFactory<>("schoolName"));
        schName.setPrefWidth(150);

        TableColumn<Integer, Over60Member> age = new TableColumn<>("Age");
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        age.setPrefWidth(100);

        TableColumn<Integer, Over60Member> nic = new TableColumn<>("NIC Number");
        nic.setCellValueFactory(new PropertyValueFactory<>("NIC Number"));
        nic.setPrefWidth(100);

        TableColumn <String, Over60Member>memType = new TableColumn<>("Membership Type");
        memType.setCellValueFactory(new PropertyValueFactory<>("memberType"));
        memType.setPrefWidth(150);

        memDetails.getColumns().add(memNum);
        memDetails.getColumns().add(name);
        memDetails.getColumns().add(date);
        memDetails.getColumns().add(phoneNO);
        memDetails.getColumns().add(schName);
        memDetails.getColumns().add(age);
        memDetails.getColumns().add(memType);
        memDetails.getColumns().add(nic);


        File file = new File("searchMemberDetails.txt");      //file which is having member details
        try {
            Scanner scanner = new Scanner(file);

            DefaultMember member = null;

            //reading data from file
            while (scanner.hasNext()){
                int membershipNumber = scanner.nextInt();
                String memName = scanner.next();
                String startMembershipDate = scanner.next();
                String nicNumber = scanner.next();
                String phoneNumber = scanner.next();
                String schoolName = scanner.next();
                int memAge = scanner.nextInt();
                String membershipType = scanner.next();



                //inserting data into the table
                if (membershipType.equals("student")) {
                    member = new StudentMember(membershipNumber, memName,startMembershipDate,nicNumber, phoneNumber, schoolName);

                } else if (membershipType.equals("over60")) {
                    member = new Over60Member(membershipNumber, memName, startMembershipDate, phoneNumber, membershipType, memAge,nicNumber);

                } else {
                    member = new DefaultMember(membershipNumber, memName, startMembershipDate, phoneNumber, membershipType,nicNumber);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HBox hBox = new HBox();

        TextField textFieldSearch = new TextField();
        textFieldSearch.setPromptText("Enter member id / name");
        textFieldSearch.setPrefSize(500, 30);
        textFieldSearch.setStyle("-fx-font-size: 18px;");

        Button btnSearch = new Button("Search");
        btnSearch.setPrefHeight(30);
        btnSearch.setStyle("-fx-font-size: 18px;");
        btnSearch.setDefaultButton(true);

        Label lblResult = new Label("Result");
        lblResult.setStyle("-fx-font-size: 18px;");

        textFieldSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnSearch.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String searchValue = textFieldSearch.getText();
                        MyGymManager manager = new MyGymManager();
                        manager.search(searchValue, lblResult);
                    }
                });
            }
        });

        hBox.getChildren().addAll(textFieldSearch,btnSearch);



        memDetails.setPrefSize(1300, 500);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,0,0,10));
        vbox.getChildren().addAll(lb,memDetails,hBox,lblResult);

        ((Group)scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
        stage.setWidth(1400);
        stage.setHeight(700);
    }
}
