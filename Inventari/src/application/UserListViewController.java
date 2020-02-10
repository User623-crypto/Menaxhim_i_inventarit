package application;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class UserListViewController implements Initializable,ControllerClass {
	@FXML private TableView<User> tableView;
	@FXML private TableColumn<User,Integer> idCol;
	@FXML private TableColumn<User,String> fNCol;
	@FXML private TableColumn<User,String> lNCol;
	@FXML private TableColumn<User,LocalDate> bCol;
	
	@FXML private Button mod;
	
	
	final String url="jdbc:mysql://localhost:3306/inventar";
	final String dbUsername="root";
	final String dbPassword="testing1234";
	

	public void modPushed(ActionEvent event) throws IOException
	{
		SceneChanger sc=new SceneChanger();
		User user= this.tableView.getSelectionModel().getSelectedItem();
		UserViewController uvc=new UserViewController();
		sc.changeScene(event, "UserView.fxml", "Modifiko perdorues", user, uvc);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mod.setDisable(true);
		
		idCol.setCellValueFactory(new PropertyValueFactory<User,Integer>("userID"));
		fNCol.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
		lNCol.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
		bCol.setCellValueFactory(new PropertyValueFactory<User,LocalDate>("birthday"));
		
		
		try {
			loadUser();
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		
	}
	public void loadUser()throws SQLException
	{
	
		ObservableList<User> user=FXCollections.observableArrayList();
		Connection conn=null;
		Statement stm=null;
		ResultSet rs=null;

		try {
			
			
			
			
				
				conn=DriverManager.getConnection(url,dbUsername,dbPassword);
				stm=conn.createStatement();
				
				String mysql="SELECT * FROM `inventar`.`user`";
				
				rs=stm.executeQuery(mysql);
				
				while(rs.next())
				{
					User users=new User(rs.getString("Emri"),rs.getString("Mbiemri"),rs.getDate("Datelindja").toLocalDate(),
							rs.getString("Password"));
					users.setUserID(rs.getInt("idUser"));
					
					user.add(users);
				}
				tableView.getItems().addAll(user);
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}finally {
			if(stm!=null)
				stm.close();
			
			if(conn!=null)
				conn.close();
			if(rs!=null)
			{
				rs.close();
			
		}
		
	}
	}
	
	public void rowSelected()
	{
		this.mod.setDisable(false);
	}

	public void krijoPerdorues(ActionEvent event) throws IOException
	{
		
		SceneChanger sc=new SceneChanger();
		
		sc.changeScene(event, "UserView.fxml", "Krijo Perdorues");
	}
	@Override
	public void preloadData(User user) {
		// TODO Auto-generated method stub
		
	}
	
}
