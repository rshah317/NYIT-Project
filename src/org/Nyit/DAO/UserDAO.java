package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.Nyit.Dbutilis.DbManager;
import org.Nyit.VO.UserVO;

public class UserDAO {

	private Connection connection;

	public UserDAO() {
		connection = DbManager.getConnection();
	}
	
	public void registerUser(UserVO userVO){
		try{
			String sql = "insert into user (first_Name,last_Name,email_Id,password) values (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,userVO.getFirstName());
			preparedStatement.setString(2,userVO.getLastName());
			preparedStatement.setString(3,userVO.getEmailId());
			preparedStatement.setString(4,userVO.getPassword());
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
//			finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public UserVO Authenticate(String email_Id,String password) throws Exception{
		UserVO userVO = new UserVO();
			String sql = "select * from user where email_Id ='"+ email_Id + "' and password ='" + password +"'";
			Statement  statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
				if(email_Id.equals(resultSet.getString("email_Id")) && password.equals(resultSet.getString("password"))){
					userVO.setPassword(password);
					userVO.setEmailId(email_Id);
				}
				return userVO;
			}else{
				throw new Exception("Invalid EmailId or Password");
			}
	}
	
}
