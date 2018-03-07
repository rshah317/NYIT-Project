package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.Nyit.Dbutilis.DbManager;
import org.Nyit.VO.CampusVO;


public class CampusDAO {
	
	private static final int FIRST_COLUMN_INDEX_COUNT_SQL = 1;
	private Connection connection;
	public CampusDAO() {
		connection = DbManager.getConnection();
	}

	public void addCampus(CampusVO campusVO){
		try{
			final Connection connection;
			connection = DbManager.getConnection();
			String sql = "insert into campus(campus_name,created_Date,created_By,status) values (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,campusVO.getCampusName());
			preparedStatement.setDate(2,campusVO.getCreatedDate());
			preparedStatement.setString(3, campusVO.getCreatedBy());
			preparedStatement.setString(4,campusVO.getStatus());
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteCampus(int campus_Id){
		try{
			String sql = "delete from campus where campus_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,campus_Id);
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateCampus(CampusVO campusVO){
		try{
			 String sql = "update campus set campus_Name=?,status=? where campus_Id=?";
			 PreparedStatement preparedStatement = connection.prepareStatement(sql);
			 preparedStatement.setString(1,campusVO.getCampusName());
			 preparedStatement.setString(2,campusVO.getStatus());
			 preparedStatement.setInt(3, campusVO.getCampusId());
			 preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<CampusVO> getCampusDetails(){
		List<CampusVO> list = new ArrayList<CampusVO>();
		try{
			String sql = "select * from campus";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				CampusVO campusVO = new CampusVO();
				campusVO.setCampusId(resultSet.getInt("campus_Id"));
				campusVO.setCampusName(resultSet.getString("campus_Name"));
				list.add(campusVO);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<CampusVO> getCampusDetails(int pageNo,int pageSize){
		int offset = (pageNo==1) ? 0 : (pageNo-1) * pageSize;
		String sql = "select * from campus";
		sql+= " limit " + pageSize + "  " + " offset " + offset;
		List<CampusVO> list = new ArrayList<CampusVO>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				CampusVO campusVO = new CampusVO();
				campusVO.setCampusId(resultSet.getInt("campus_Id"));
				campusVO.setCampusName(resultSet.getString("campus_Name"));
				campusVO.setCreatedDate(resultSet.getDate("created_Date"));
				campusVO.setCreatedBy(resultSet.getString("created_By"));
				campusVO.setStatus(resultSet.getString("status"));
				list.add(campusVO);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public CampusVO findCampusById(int campus_Id){
		CampusVO campusVO = new CampusVO();
		String sql = "select * from campus where campus_Id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, campus_Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				campusVO.setCampusId(resultSet.getInt("campus_Id"));
				campusVO.setCampusName(resultSet.getString("campus_Name"));
				campusVO.setStatus(resultSet.getString("status"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return campusVO;
	}
	public long count(){
		String sql = "select count(*) from campus";
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
