package application;


import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class UserViewController implements Initializable,ControllerClass {
	@FXML private TextField fNTextField;
	@FXML private TextField lNTextField;
	@FXML private TextField bTextField;
	@FXML private Label InvalidTextField;
	@FXML private Label titulli;
	@FXML private PasswordField pwF;
	@FXML private PasswordField cpwf;
	
	private User user=null;
	
	public void Regjistro(ActionEvent event) throws IOException, SQLException
	{
		if(validPass()) {
			
			if(user!=null)
			{
				updateUser();
				user.updateIntoDB();
				
			}else {
			try {
				
				User user=new User(fNTextField.getText(),lNTextField.getText(),LocalDate.parse(bTextField.getText()),pwF.getText());
				InvalidTextField.setText("");
				user.insertIntoDB();
				
				
				
			}catch(Exception e)
			{
				this.InvalidTextField.setText(e.getMessage());
			}
		  }
			SceneChanger sc=new SceneChanger();
			sc.changeScene(event, "UserListView.fxml", "Lista e perdoruesve");
		}
		
	}
	
	
	public boolean validPass()
	{
		if(pwF.getText().length() < 5)
		{
			InvalidTextField.setText("Fjalekalimi duhet te jete me i madh se 5 karaktere");
			return false;
		}
		
		if(pwF.getText().equals(cpwf.getText()))
			return true;
		else {
			InvalidTextField.setText("Fjalekalimi nuk perputhet");
			return false;
		}
		
			
	}

	public void Kthehu(ActionEvent event) throws IOException
	{
		SceneChanger sc=new SceneChanger();
		sc.changeScene(event, "UserListView.fxml", "Lista e perdoruesve");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.InvalidTextField.setText("");
		
	}

	@Override
	public void preloadData(User user) {
		String Data=user.getBirthday().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.user=user;
		this.fNTextField.setText(user.getFirstName());
		this.lNTextField.setText(user.getLastName());
		this.bTextField.setText(Data);
		this.titulli.setText("Modifiko Perdoruesin");
		
	}
	
	public void updateUser()
	{
		user.setFirstName(fNTextField.getText());
		user.setLastName(lNTextField.getText());
		user.setBirthday(LocalDate.parse(bTextField.getText()));
	}

}
