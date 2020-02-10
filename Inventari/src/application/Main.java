package application;
	
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import Model.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws SQLException {
		try {
			/*Pane p=new Pane();
			
			Scene scene = new Scene(p,400,400);
			Button b=new Button();
			Label lb=new Label("Tekst");
			lb.setLayoutX(100);
			lb.setLayoutY(200);
			b.setLayoutX(100);
			b.setLayoutY(100);
			b.setText("Hello");
			
			b.setOnAction(new EventHandler<ActionEvent>()
					{
						@Override
						public void handle(ActionEvent arg0)
						{
							lb.setText("Uno");
						}
					});
			p.getChildren().add(b);
			p.getChildren().add(lb);*/
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Parent root =FXMLLoader.load(getClass().getResource("UserListView.fxml"));
			
			Scene scene =new Scene(root);
			
			primaryStage.setTitle("Regjistro Perdorues");
			String str = "2017-06-25";

			// parse string to `LocalDate`
			LocalDate date = LocalDate.parse(str);

			// print `LocalDate`
			System.out.println("Parsed LocalDate: " + date);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//User a=new User("Arben","Cakaj",LocalDate.of(1990, Month.MARCH, 21));
		//a.insertIntoDB();
		
		//System.out.println(a.toString());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
