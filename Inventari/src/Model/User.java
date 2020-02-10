package Model;


import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import com.mysql.*;
public class User {
	private String firstName,lastName;
	private LocalDate birthday;
	private int userID;
	private String pass;
	private byte[] salt;
	
	
	public int getUserID() {
		return userID;
	}

	
	public void setUserID(int userID) {
		if(userID>0)
		this.userID = userID;
		else {
			throw new IllegalArgumentException("Illegal ID num");
		}
	}

	final String url="jdbc:mysql://localhost:3306/inventar";
	final String dbUsername="root";
	final String dbPassword="testing1234";
	
	public User(String firstName,String lastName,LocalDate birthday,String pass) throws NoSuchAlgorithmException
	{
		setFirstName(firstName);
		setLastName(lastName);
		setBirthday(birthday);
		setPass(pass);
		salt=Password.getSalt();
		this.pass=Password.getSHA512Password(pass, salt);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		int age=Period.between(birthday, LocalDate.now()).getYears();
		if(age>=18 && age<=90)
		this.birthday = birthday;
		else
			throw new IllegalArgumentException("Mosha duhet te jete mbi 18 dhe nen 90");
	}
	
	
	/*
	 * Kjo metode do te fusi perdoruesin ne databaze
	 */
	
	public void insertIntoDB() throws SQLException
	{
		Connection conn=null;
		PreparedStatement preparedSt=null;
		
		try {
			
			conn=DriverManager.getConnection(url,dbUsername,dbPassword);
			
			
			String mysql="INSERT INTO `inventar`.`user`"+ "(Emri,Mbiemri,Datelindja,Password,salt)"+" VALUES(?,?,?,?,?)";
			preparedSt=conn.prepareStatement(mysql);
			Date db=Date.valueOf(birthday);
			preparedSt.setString(1, firstName);
			preparedSt.setString(2, lastName);
			preparedSt.setDate(3, db);
			preparedSt.setString(4,pass);
			preparedSt.setBlob(5, new javax.sql.rowset.serial.SerialBlob(salt));
			preparedSt.executeUpdate();
			
			
			
			
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}finally {
			if(preparedSt!=null)
				preparedSt.close();
			if(conn!=null)
				conn.close();
		}
	}
	
	public void updateIntoDB() throws SQLException
	{
		
		Connection conn=null;
		PreparedStatement preparedSt=null;
		
		try {
			
			conn=DriverManager.getConnection(url,dbUsername,dbPassword);
			
			
			String mysql="UPDATE `inventar`.`user` SET Emri=?, Mbiemri=?,Datelindja=? WHERE idUser=?";
			preparedSt=conn.prepareStatement(mysql);
			Date db=Date.valueOf(birthday);
			preparedSt.setString(1, firstName);
			preparedSt.setString(2, lastName);
			preparedSt.setDate(3, db);
			preparedSt.setInt(4, userID);
			preparedSt.executeUpdate();
			
			
			
			
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}finally {
			if(preparedSt!=null)
				preparedSt.close();
			if(conn!=null)
				conn.close();
		}
		
		
	}
	
	@Override
	public String toString()
	{
		return String.format("%s %s is %d years old", firstName,lastName,Period.between(birthday, LocalDate.now()).getYears());
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}
	
	

}
