package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Nyit.Dbutilis.DbManager;
import org.Nyit.VO.SectionSubjectMappingVO;

public class SectionSubjectMappingDAO {
	
	private static final int FIRST_COLUMN_INDEX_COUNT_SQL = 1;
	private Connection connection;
	
	public SectionSubjectMappingDAO(){
		connection = DbManager.getConnection();
	}
	public void addSectionSubjectSchedule(SectionSubjectMappingVO sectionSubjectMappingVO){
		try{
			String sql = "insert into section_subject_schedule(section_subject_schedule_Id, campus_subject_Id, section_Id, created_By, created_Date, status) values (?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sectionSubjectMappingVO.getSectionSubjectScheduleId());
			preparedStatement.setInt(2, sectionSubjectMappingVO.getCampusSubjectId());
			preparedStatement.setString(4, sectionSubjectMappingVO.getCreatedBy());
			preparedStatement.setDate(5, sectionSubjectMappingVO.getCreatedDate());
			preparedStatement.setString(6, sectionSubjectMappingVO.getStatus());
			for (int value : sectionSubjectMappingVO.getSectionId()) {
				preparedStatement.setInt(3, value);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void deleteSectionSubjectSchedule(int sectionSubjectSchedule_Id){
		try{
			String sql = "delete from section_subject_schedule where section_subject_schedule_Id =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sectionSubjectSchedule_Id);
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	 
	public void updateSectionSubjectSchedule(SectionSubjectMappingVO sectionSubjectMappingVO){
		try{
			String sql = "update section_subject_schedule set campus_subject_Id=?, section_Id=?, status =? where section_subject_schedule_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sectionSubjectMappingVO.getCampusSubjectId());
			preparedStatement.setString(3, sectionSubjectMappingVO.getStatus());
			preparedStatement.setInt(4,sectionSubjectMappingVO.getSectionSubjectScheduleId());
			for (int value : sectionSubjectMappingVO.getSectionId()) {
				preparedStatement.setInt(2,value);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public List<SectionSubjectMappingVO> getSectionSubjectScheduleDetails(){
		List<SectionSubjectMappingVO> list = new ArrayList<SectionSubjectMappingVO>();
		try{
			String sql= " select * from section_subject_schedule inner join section ON section.section_Id = section_subject_schedule.section_Id inner join campus_subject on campus_subject.campus_subject_Id = section_subject_schedule.campus_subject_Id inner join subject on subject.subject_Id = campus_subject.subject_Id inner join major on major.major_Id = campus_subject.major_Id where section_subject_schedule.status='ACTIVE'";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				SectionSubjectMappingVO sectionSubjectMappingVO = new SectionSubjectMappingVO();
				sectionSubjectMappingVO.setSectionSubjectScheduleId(resultSet.getInt("section_subject_schedule_Id"));
				sectionSubjectMappingVO.setCampusSubjectId(resultSet.getInt("campus_subject_Id"));
				sectionSubjectMappingVO.setSectionName(resultSet.getString("section_Name"));
				sectionSubjectMappingVO.setSubjectName(resultSet.getString("subject_Name"));
				sectionSubjectMappingVO.setSubjectCode(resultSet.getString("subject_Code"));
				sectionSubjectMappingVO.setMajorCode(resultSet.getString("major_Code"));
				list.add(sectionSubjectMappingVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;

	}
	public List<SectionSubjectMappingVO> getSectionSubjectScheduleDetails(int pageNo, int pageSize){
		int offset = (pageNo ==1)? 0: (pageNo-1) * pageSize;
		String sql = " select * from section_subject_schedule inner join section on section.section_Id = section_subject_schedule.section_Id inner join campus_subject on campus_subject.campus_subject_Id = section_subject_schedule.campus_subject_Id inner join subject ON subject.subject_Id = campus_subject.subject_Id inner join major ON major.major_Id = campus_subject.major_Id";
		sql+= " limit " + pageSize + "  " + " offset " + offset;
		List<SectionSubjectMappingVO> list = new ArrayList<SectionSubjectMappingVO>();
		try{
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				SectionSubjectMappingVO sectionSubjectMappingVO = new SectionSubjectMappingVO();
				sectionSubjectMappingVO.setSectionSubjectScheduleId(resultSet.getInt("section_subject_schedule_Id"));
				sectionSubjectMappingVO.setCampusSubjectId(resultSet.getInt("campus_subject_Id"));
				sectionSubjectMappingVO.setSectionName(resultSet.getString("section_Name"));
				sectionSubjectMappingVO.setSubjectName(resultSet.getString("subject_Name"));
				sectionSubjectMappingVO.setSubjectCode(resultSet.getString("subject_Code"));
				sectionSubjectMappingVO.setMajorCode(resultSet.getString("major_Code"));
				sectionSubjectMappingVO.setCreatedDate(resultSet.getDate("created_Date"));
				sectionSubjectMappingVO.setCreatedBy(resultSet.getString("created_By"));
				sectionSubjectMappingVO.setStatus(resultSet.getString("status"));
				list.add(sectionSubjectMappingVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	public SectionSubjectMappingVO findSectionSubjectScheduleById(int sectionSubjectSchedule_Id){
		SectionSubjectMappingVO sectionSubjectMappingVO = new SectionSubjectMappingVO();
		String sql = "select * from section_subject_schedule where section_subject_schedule_Id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sectionSubjectSchedule_Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				sectionSubjectMappingVO.setSectionSubjectScheduleId(resultSet.getInt("section_subject_schedule_Id"));
				sectionSubjectMappingVO.setCampusSubjectId(resultSet.getInt("campus_subject_Id"));
				sectionSubjectMappingVO.setSectionName(resultSet.getString("section_Id"));
				sectionSubjectMappingVO.setStatus(resultSet.getString("status"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return sectionSubjectMappingVO;
	}
	public long count(){
		String sql = "select count(*) from section_subject_schedule";
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
