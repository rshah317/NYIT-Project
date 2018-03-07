package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Nyit.Dbutilis.DbManager;

import org.Nyit.VO.SectionVO;


public class SectionDAO {
	private static final int FIRST_COLUMN_INDEX_COUNT_SQL = 1;
	private Connection connection;
	
	public SectionDAO () {
		
		connection = DbManager.getConnection();
	}

	public void addSection(SectionVO sectionVO){
		try{
			final Connection connection;
			connection = DbManager.getConnection();
			String sql = "insert into section (section_Name, created_By, created_Date, status) values (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sectionVO.getSectionName());
			preparedStatement.setString(2, sectionVO.getCreatedBy());
			preparedStatement.setDate(3, sectionVO.getCreatedDate());
			preparedStatement.setString(4, sectionVO.getStatus());
			preparedStatement.executeUpdate();	
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteSection(int section_Id){
		try{
			String sql = "delete from section where section_Id =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, section_Id);
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateSection(SectionVO sectionVO){
		try{
			String sql = "update section set section_Name=?, status=? where section_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sectionVO.getSectionName());
			preparedStatement.setString(2, sectionVO.getStatus());
			preparedStatement.setInt(3, sectionVO.getSectionId());
			preparedStatement.executeUpdate();		
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<SectionVO> getSectionDetails(){
		List<SectionVO> list = new ArrayList<SectionVO>();
		try{
			String sql= "select * from section where status='ACTIVE'";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				SectionVO sectionVO = new SectionVO();
				sectionVO.setSectionId(resultSet.getInt("section_Id"));
				sectionVO.setSectionName(resultSet.getString("section_Name"));
				list.add(sectionVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;

	}
	
	public List<SectionVO> getSectionDetails(int pageNo, int pageSize){
		int offset = (pageNo ==1)? 0: (pageNo-1) * pageSize;
		String sql = "select * from section";
		sql+= " limit " + pageSize + "  " + " offset " + offset;
		List<SectionVO> list = new ArrayList<SectionVO>();
		try{
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				SectionVO sectionVO = new SectionVO();
				sectionVO.setSectionId(resultSet.getInt("section_Id"));
				sectionVO.setSectionName(resultSet.getString("section_Name"));
				sectionVO.setCreatedBy(resultSet.getString("created_By"));
				sectionVO.setCreatedDate(resultSet.getDate("created_Date"));
				sectionVO.setStatus(resultSet.getString("status"));
				list.add(sectionVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public SectionVO findSectionById(int section_Id){
		SectionVO sectionVO = new SectionVO();
		String sql = "select * from section where section_Id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, section_Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				sectionVO.setSectionId(resultSet.getInt("section_Id"));
				sectionVO.setSectionName(resultSet.getString("section_Name"));
				sectionVO.setStatus(resultSet.getString("status"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return sectionVO;
	}
	
	
	public long count(){
		String sql = "select count(*) from section";
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
