package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Nyit.Dbutilis.DbManager;
import org.Nyit.VO.MajorVO;


public class MajorDAO {
	
	private static final int FIRST_COLUMN_INDEX_COUNT_SQL = 1;
	private Connection connection;

	public MajorDAO() {
		connection = DbManager.getConnection();
	}

	public void addMajor(MajorVO majorVO){
		try{
			String sql = "insert into major (major_Id, major_Name, major_Code created_By, created_Date, status) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setInt(1,majorVO.getMajorId());
			preparedStatment.setString(2,majorVO.getMajorName());
			preparedStatment.setString(3,majorVO.getMajorCode());
			preparedStatment.setString(4,majorVO.getCreatedBy());
			preparedStatment.setDate(5,majorVO.getCreatedDate());
			preparedStatment.setString(6,majorVO.getStatus());
			preparedStatment.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void deleteMajor(int major_Id){
		try{
			String sql = "delete from major where major_Id =?";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setInt(1, major_Id);
			preparedStatment.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}

	}
	public void updateMajor(MajorVO majorVO){
		try{
			String sql= "update major set major_Name=?, major_Code=?, status=? where major_Id=?";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setString(1, majorVO.getMajorName());
			preparedStatment.setString(2, majorVO.getMajorCode());
			preparedStatment.setString(3, majorVO.getStatus());
			preparedStatment.setInt(4, majorVO.getMajorId());
			preparedStatment.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public List<MajorVO> getMajorDetails(){
		List<MajorVO> list = new ArrayList<MajorVO>();
		try{
			String sql= "select * from major where status='ACTIVE'";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				MajorVO majorVO = new MajorVO();
				majorVO.setMajorId(resultSet.getInt("major_Id"));
				majorVO.setMajorName(resultSet.getString("major_Name"));
				majorVO.setMajorCode(resultSet.getString("major_Code"));
				list.add(majorVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;

	}
	public List<MajorVO> getMajorDetails(int pageNo, int pageSize){
		int offset = (pageNo ==1)? 0: (pageNo-1) * pageSize;
		String sql = "select * from major";
		sql+= " limit " + pageSize + "  " + " offset " + offset;
		List<MajorVO> list = new ArrayList<MajorVO>();
		try{
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				MajorVO majorVO = new MajorVO();
				majorVO.setMajorId(resultSet.getInt("major_Id"));
				majorVO.setMajorName(resultSet.getString("major_Name"));
				majorVO.setMajorCode(resultSet.getString("major_Code"));
				majorVO.setCreatedDate(resultSet.getDate("created_Date"));
				majorVO.setCreatedBy(resultSet.getString("created_By"));
				majorVO.setStatus(resultSet.getString("status"));
				list.add(majorVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	public MajorVO findMajorById(int major_Id){
		MajorVO majorVO = new MajorVO();
		String sql = "select * from major where major_Id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, major_Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				majorVO.setMajorId(resultSet.getInt("major_Id"));
				majorVO.setMajorName(resultSet.getString("major_Name"));
				majorVO.setMajorCode(resultSet.getString("major_Code"));
				majorVO.setStatus(resultSet.getString("status"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return majorVO;
	}
	public long count(){
		String sql = "select count(*) from major";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				return resultSet.getLong(FIRST_COLUMN_INDEX_COUNT_SQL);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
