package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Nyit.Dbutilis.DbManager;
import org.Nyit.VO.SubjectVO;

public class SubjectDAO {
	private static final int FIRST_COLUMN_INDEX_COUNT_SQL = 1;
	private Connection connection;
	
	public SubjectDAO() {
		connection = DbManager.getConnection();
	}
	
	public void  addSubject(SubjectVO subjectVO){
		try{
			String sql = "insert into subject (subject_Id, subject_Name, subject_Code, created_By, created_Date, status) values (?,?,?,?,?,?)";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setInt(1,subjectVO.getSubjectId());
			preparedStatment.setString(2,subjectVO.getSubjectName());
			preparedStatment.setString(3,subjectVO.getSubjectCode());
			preparedStatment.setString(4,subjectVO.getCreatedBy());
			preparedStatment.setDate(5,subjectVO.getCreatedDate());
			preparedStatment.setString(6,subjectVO.getStatus());
			preparedStatment.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	public void deleteSubject(int subject_Id){
		try{
			String sql = "delete from subject where subject_Id =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, subject_Id);
			preparedStatement.executeUpdate();
				
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void updateSubject(SubjectVO subjectVO){
		try{
			String sql = "update subject set subject_Name=?, subject_Code=?, status=? where subject_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, subjectVO.getSubjectName());
			preparedStatement.setString(2, subjectVO.getSubjectCode());
			preparedStatement.setString(3, subjectVO.getStatus());
			preparedStatement.setInt(4, subjectVO.getSubjectId());
			preparedStatement.executeUpdate();	
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<SubjectVO> getSubjectDetails(){
		List<SubjectVO> list = new ArrayList<SubjectVO>();
		try{
			String sql= "select * from subject where status='ACTIVE'";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				SubjectVO subjectVO = new SubjectVO();
				subjectVO.setSubjectId(resultSet.getInt("subject_Id"));
				subjectVO.setSubjectName(resultSet.getString("subject_Name"));
				subjectVO.setSubjectCode(resultSet.getString("subject_Code"));
				list.add(subjectVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;

	}

	public List<SubjectVO> getSubjectDetails(int pageNo, int pageSize){
		int offset = (pageNo ==1)? 0: (pageNo-1) * pageSize;
		String sql = "select * from subject";
		sql+= " limit " + pageSize + "  " + " offset " + offset;
		List<SubjectVO> list = new ArrayList<SubjectVO>();
		try{
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				SubjectVO subjectVO = new SubjectVO();
				subjectVO.setSubjectId(resultSet.getInt("subject_Id"));
				subjectVO.setSubjectName(resultSet.getString("subject_Name"));
				subjectVO.setSubjectCode(resultSet.getString("subject_Code"));
				subjectVO.setCreatedDate(resultSet.getDate("created_Date"));
				subjectVO.setCreatedBy(resultSet.getString("created_By"));
				subjectVO.setStatus(resultSet.getString("status"));
				list.add(subjectVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	public SubjectVO findSubjectById(int subject_Id){
		SubjectVO subjectVO = new SubjectVO();
		String sql = "select * from subject where subject_Id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, subject_Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				subjectVO.setSubjectId(resultSet.getInt("subject_Id"));
				subjectVO.setSubjectName(resultSet.getString("subject_Name"));
				subjectVO.setSubjectCode(resultSet.getString("subject_Code"));
				subjectVO.setStatus(resultSet.getString("status"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return subjectVO;
	}
	public long count(){
		String sql = "select count(*) from subject";
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

