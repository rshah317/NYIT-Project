package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Nyit.Dbutilis.DbManager;
import org.Nyit.VO.CampusSubjectMappingVO;

public class CampusSubjectMappingDAO {
	
	private static final int FIRST_COLUMN_INDEX_COUNT_SQL = 1;
	private Connection connection;
	
	public CampusSubjectMappingDAO() {
			connection = DbManager.getConnection();
	}
	
	public void addCampusSubject(CampusSubjectMappingVO campusSubjectVO){
		try{
			String sql = "insert into campus_subject (campus_subject_Id, campus_Id, subject_Id, major_Id ,created_By, created_Date, status)values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,campusSubjectVO.getCampusSubjectId());
			preparedStatement.setInt(2,campusSubjectVO.getCampusId());
			preparedStatement.setInt(3,campusSubjectVO.getSubjectId());
			preparedStatement.setString(5,campusSubjectVO.getCreatedBy());
			preparedStatement.setDate(6,campusSubjectVO.getCreatedDate());
			preparedStatement.setString(7,campusSubjectVO.getStatus());
			preparedStatement.setInt(4,campusSubjectVO.getMajorId());
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}	
		
	}
	
	public void deleteCampusSubject(int campusSubject_Id){
		try{
			String sql = "delete from campus_subject where campus_subject_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, campusSubject_Id);
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateCampusSubject(CampusSubjectMappingVO campusSubjectVO){
		try{
			String sql = "update campus_subject set campus_Id=?, subject_Id=?, major_Id=? ,status=? where campus_subject_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,campusSubjectVO.getCampusId());
			preparedStatement.setInt(2,campusSubjectVO.getSubjectId());
			preparedStatement.setString(4,campusSubjectVO.getStatus());
			preparedStatement.setInt(5,campusSubjectVO.getCampusSubjectId());
			preparedStatement.setInt(3, campusSubjectVO.getMajorId());
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public List<CampusSubjectMappingVO> getCampusSubjectDetails(){
		List<CampusSubjectMappingVO> list = new ArrayList<CampusSubjectMappingVO>();
		try{
			String sql= "select * from campus_subject inner join subject on subject.subject_Id = campus_subject.subject_Id inner join campus on campus.campus_Id = campus_subject.campus_Id inner join major on major.major_Id = campus_subject.major_Id where campus_subject.status = 'ACTIVE'";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				CampusSubjectMappingVO campusSubjectMapppingVO = new CampusSubjectMappingVO();
				campusSubjectMapppingVO.setCampusSubjectId(resultSet.getInt("campus_Subject_Id"));
				campusSubjectMapppingVO.setCampusName(resultSet.getString("campus_Name"));
				campusSubjectMapppingVO.setSubjectCode(resultSet.getString("subject_Code"));
				campusSubjectMapppingVO.setSubjectName(resultSet.getString("subject_Name"));
				campusSubjectMapppingVO.setMajorName(resultSet.getString("major_Name"));
				campusSubjectMapppingVO.setMajorCode(resultSet.getString("major_Code"));
				campusSubjectMapppingVO.setCampusId(resultSet.getInt("campus_Id"));
				campusSubjectMapppingVO.setSubjectId(resultSet.getInt("subject_Id"));
				list.add(campusSubjectMapppingVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;

	}
	public List<CampusSubjectMappingVO> getCampusSubjectDetails(int pageNo, int pageSize){
		int offset = (pageNo ==1)? 0: (pageNo -1)* pageSize;
		String sql = "select * from campus_subject inner join subject on subject.subject_Id = campus_subject.subject_Id inner join campus on campus.campus_Id = campus_subject.campus_Id inner join major on major.major_Id = campus_subject.major_Id";
		sql+= " limit " + pageSize + " " + " offset " + offset;
		List<CampusSubjectMappingVO> list = new ArrayList<CampusSubjectMappingVO>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				CampusSubjectMappingVO campusSubjectVO = new CampusSubjectMappingVO();
				campusSubjectVO.setCampusSubjectId(resultSet.getInt("campus_Subject_Id"));
				campusSubjectVO.setCampusName(resultSet.getString("campus_Name"));
				campusSubjectVO.setMajorCode(resultSet.getString("major_Code"));
				campusSubjectVO.setSubjectCode(resultSet.getString("subject_Code"));
				campusSubjectVO.setSubjectName(resultSet.getString("subject_Name"));
				campusSubjectVO.setCreatedDate(resultSet.getDate("created_Date"));
				campusSubjectVO.setCreatedBy(resultSet.getString("created_By"));
				campusSubjectVO.setStatus(resultSet.getString("status"));
				list.add(campusSubjectVO);
				
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public CampusSubjectMappingVO findCampusSubjectById(int campusSubject_Id){
		CampusSubjectMappingVO campusSubjectVO = new CampusSubjectMappingVO();
		String sql = "select * from campus_subject where campus_subject_Id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, campusSubject_Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				campusSubjectVO.setCampusSubjectId(resultSet.getInt("campus_subject_Id"));
				campusSubjectVO.setCampusId(resultSet.getInt("campus_Id"));
				campusSubjectVO.setSubjectId(resultSet.getInt("Subject_Id"));
				campusSubjectVO.setMajorId(resultSet.getInt("major_Id"));
				campusSubjectVO.setStatus(resultSet.getString("status"));
				
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return campusSubjectVO;
				
	}
	public long count(){
		String sql = "select count(*) from campus_subject";
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
