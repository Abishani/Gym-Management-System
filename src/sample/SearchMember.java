package sample;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class SearchMember extends Application {
    @Override
    public void start(Stage stage) {


        final Label number = new Label("Membership Number");
        number.setLayoutX(120);
        number.setLayoutY(150);
//        number.setStyle("-fx-font-size: 20px;-fx-text-fill: white;-fx-font: arial");


        final TextField memberList = new TextField("");
        memberList.setFont(new Font("Arial", 20));

        final Label output = new Label("");
//        output.setStyle("-fx-font-size: 20px;-fx-text-fill: white;-fx-font: arial");


        Button search = new Button("Search");
        search.setLayoutX(100);
//        search.setLayoutY(10);
        search.setOnAction(e -> {
            try {
                String record=null;
                String memNo=String.valueOf(memberList.getText());
                BufferedReader br=new BufferedReader(new FileReader("memberDetails.txt"));

                while ((record=br.readLine())!=null){
                    StringTokenizer st=new StringTokenizer(record,",");
                    if (record.contains(memNo)){
                        output.setText(st.nextToken());
                    }
                }
                br.close();
            } catch (Exception prompt){
                memberList.setText("matching record not found");
            }
        });




        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 0, 0, 10));
        pane.setStyle("-fx-background-color: black");
        pane.getChildren().addAll(number,memberList,search,output);

        Scene scene = new Scene(pane,1300,600);
        stage.setTitle("Table View Sample");
        stage.setWidth(1900);
        stage.setHeight(550);
        stage.setScene(scene);
        stage.show();
    }
}
