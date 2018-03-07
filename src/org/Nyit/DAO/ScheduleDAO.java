package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Nyit.Dbutilis.DbManager;
import org.Nyit.VO.ScheduleVO;

public class ScheduleDAO {
	
	private static final int FIRST_COLUMN_INDEX_COUNT_SQL = 1;
	private Connection connection;
	
	public ScheduleDAO() {
		connection = DbManager.getConnection();
	}
	
	public void addSchedule(ScheduleVO scheduleVO){
		try{
			String sql = "insert into campus_schedule(schedule_Id, campus_subject_seat_evaluation_Id, day, time,created_Date,created_By,status) values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,scheduleVO.getScheduleId());
			preparedStatement.setInt(2,scheduleVO.getCampus_subject_seat_evaluation_Id());
			preparedStatement.setString(3,scheduleVO.getDay());
			preparedStatement.setString(4,scheduleVO.getTime());
			preparedStatement.setDate(5, scheduleVO.getCreatedDate());
			preparedStatement.setString(6,scheduleVO.getCreatedBy());
			preparedStatement.setString(7,scheduleVO.getStatus());
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void deleteSchedule(int schedule_Id){
		try{
			String sql = "delete from campus_schedule where schedule_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,schedule_Id);
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void updateSchedule(ScheduleVO scheduleVO){
		try{
			String sql = "update campus_schedule set campus_subject_seat_evaluation_Id=?, day=?, time=? ,status=? where schedule_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,scheduleVO.getCampus_subject_seat_evaluation_Id());
			preparedStatement.setString(2,scheduleVO.getDay());
			preparedStatement.setString(3,scheduleVO.getTime());
			preparedStatement.setString(4, scheduleVO.getStatus());
			preparedStatement.setInt(5,scheduleVO.getScheduleId());
			preparedStatement.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<ScheduleVO> getScheduleDetails(){
		List<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try{
			String sql = "select * from campus_schedule inner join campus_subject_seat_evaluation on campus_subject_seat_evaluation.campus_subject_seat_evaluation_Id = campus_schedule.campus_subject_seat_evaluation_Id inner join section_subject_schedule on section_subject_schedule.section_subject_schedule_Id = campus_subject_seat_evaluation.section_subject_schedule_Id inner join section on section.section_Id = section_subject_schedule.section_Id inner join campus_subject on campus_subject.campus_subject_Id = section_subject_schedule.campus_subject_Id inner join subject on subject.subject_Id = campus_subject.subject_Id inner join major on major.major_Id = campus_subject.major_Id where major.major_Id=?;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				ScheduleVO scheduleVO = new ScheduleVO();
				scheduleVO.setScheduleId(resultSet.getInt("schedule_Id"));
				scheduleVO.setSubjectName(resultSet.getString("subject_Name"));
				scheduleVO.setSubjectCode(resultSet.getString("subject_Code"));
				list.add(scheduleVO);
				
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	public List<ScheduleVO> getSubjectDayTime(int campusSubjectId){
		List<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try{
			String sql = "select * from campus_schedule inner join campus_subject_seat_evaluation on campus_subject_seat_evaluation.campus_subject_seat_evaluation_Id = campus_schedule.campus_subject_seat_evaluation_Id inner join section_subject_schedule on section_subject_schedule.section_subject_schedule_Id = campus_subject_seat_evaluation.section_subject_schedule_Id inner join section on section.section_Id = section_subject_schedule.section_Id inner join campus_subject on campus_subject.campus_subject_Id = section_subject_schedule.campus_subject_Id inner join subject on subject.subject_Id = campus_subject.subject_Id inner join major on major.major_Id = campus_subject.major_Id where campus_subject_seat_evaluation.campus_subject_seat_evaluation_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,campusSubjectId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				ScheduleVO scheduleVO = new ScheduleVO();
				scheduleVO.setScheduleId(resultSet.getInt("schedule_Id"));
				scheduleVO.setSubjectId(resultSet.getInt("subject_Id"));
				scheduleVO.setCampus_subject_seat_evaluation_Id(resultSet.getInt("campus_subject_seat_evaluation_Id"));
				scheduleVO.setDay(resultSet.getString("day"));
				scheduleVO.setTime(resultSet.getString("time"));
				scheduleVO.setSectionId(resultSet.getInt("section_Id"));
				scheduleVO.setSectionName(resultSet.getString("section_Name"));
				list.add(scheduleVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	public List<ScheduleVO> getMajorSubjectDetails(int major_Id){
		List<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try{
			String sql = "select * from campus_schedule inner join campus_subject_seat_evaluation on campus_subject_seat_evaluation.campus_subject_seat_evaluation_Id = campus_schedule.campus_subject_seat_evaluation_Id inner join section_subject_schedule on section_subject_schedule.section_subject_schedule_Id = campus_subject_seat_evaluation.section_subject_schedule_Id inner join section on section.section_Id = section_subject_schedule.section_Id inner join campus_subject on campus_subject.campus_subject_Id = section_subject_schedule.campus_subject_Id inner join subject on subject.subject_Id = campus_subject.subject_Id inner join major on major.major_Id = campus_subject.major_Id where major.major_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,major_Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				ScheduleVO scheduleVO = new ScheduleVO();
				scheduleVO.setCampus_subject_seat_evaluation_Id(resultSet.getInt("campus_subject_seat_evaluation_Id"));
				scheduleVO.setScheduleId(resultSet.getInt("schedule_Id"));
				scheduleVO.setSubjectId(resultSet.getInt("subject_Id"));
				scheduleVO.setSubjectName(resultSet.getString("subject_Name"));
				scheduleVO.setSubjectCode(resultSet.getString("subject_Code"));
				scheduleVO.setSectionName(resultSet.getString("section_Name"));
				scheduleVO.setSectionId(resultSet.getInt("section_Id"));
				list.add(scheduleVO);
				
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	public List<ScheduleVO> getScheduleDetails(int pageNo, int pageSize){
		int offset = (pageNo ==1)? 0: (pageNo-1) * pageSize;
		String sql = "select * from campus_schedule inner join campus_subject_seat_evaluation on campus_subject_seat_evaluation.campus_subject_seat_evaluation_Id = campus_schedule.campus_subject_seat_evaluation_Id inner join section_subject_schedule on section_subject_schedule.section_subject_schedule_Id = campus_subject_seat_evaluation.section_subject_schedule_Id inner join section on section.section_Id = section_subject_schedule.section_Id inner join campus_subject on campus_subject.campus_subject_Id = section_subject_schedule.campus_subject_Id inner join subject on subject.subject_Id = campus_subject.subject_Id inner join major on major.major_Id = campus_subject.major_Id";
		sql+= " limit " + pageSize + " " + " offset " + offset;
		List<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				ScheduleVO scheduleVO = new ScheduleVO();
				scheduleVO.setScheduleId(resultSet.getInt("schedule_Id"));
				scheduleVO.setSectionName(resultSet.getString("section_Name"));
				scheduleVO.setSubjectName(resultSet.getString("subject_Name"));
				scheduleVO.setSubjectCode(resultSet.getString("subject_Code"));
				scheduleVO.setMajorCode(resultSet.getString("major_Code"));
				scheduleVO.setCampus_subject_seat_evaluation_Id(resultSet.getInt("campus_subject_seat_evaluation_Id"));
				scheduleVO.setDay(resultSet.getString("day"));
				scheduleVO.setTime(resultSet.getString("time"));
				scheduleVO.setCreatedDate(resultSet.getDate("created_Date"));
				scheduleVO.setCreatedBy(resultSet.getString("created_By"));
				scheduleVO.setStatus(resultSet.getString("status"));
				list.add(scheduleVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public ScheduleVO findScheduleById(int schedule_Id){
		ScheduleVO scheduleVO = new ScheduleVO();
		String sql = "select * from campus_schedule where schedule_Id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, schedule_Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				scheduleVO.setScheduleId(resultSet.getInt("schedule_Id"));
				scheduleVO.setCampus_subject_seat_evaluation_Id(resultSet.getInt("campus_subject_seat_evaluation_Id"));
				scheduleVO.setDay(resultSet.getString("day"));
				scheduleVO.setTime(resultSet.getString("time"));
				scheduleVO.setStatus(resultSet.getString("status"));
			}
	
		}catch (SQLException e){
			e.printStackTrace();
		}
		return scheduleVO;
	}
	
	public long count(){
		String sql = "select count(*) from campus_schedule";
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
