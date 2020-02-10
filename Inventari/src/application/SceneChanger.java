package application;

import java.io.IOException;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneChanger {

		public void SceneChanger()
		{
			
		}
		
		public void changeScene(ActionEvent event,String viewName,String Title) throws IOException
		{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(getClass().getResource(viewName));
			
			Parent parent=loader.load();
			
			Scene scene=new Scene(parent);
			
			Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
		
			
			stage.setTitle(Title);
			stage.setScene(scene);
			stage.show();
			
		}
		
		public void changeScene(ActionEvent event,String viewName,String Title,User user,ControllerClass  controllerClass) throws IOException
		{
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(getClass().getResource(viewName));
			
			Parent parent=loader.load();
			
			Scene scene=new Scene(parent);
			
			controllerClass=loader.getController();
			controllerClass.preloadData(user);
			
			Stage stage= (Stage)((Node)event.getSource()).getScene().getWindow();
		
			
			stage.setTitle(Title);
			stage.setScene(scene);
			stage.show();
		}
}

