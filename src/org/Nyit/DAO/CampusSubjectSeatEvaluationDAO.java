package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Nyit.Dbutilis.DbManager;
import org.Nyit.VO.CampusSubjectSeatEvaluationVO;


public class CampusSubjectSeatEvaluationDAO {
	
	private static final int FIRST_COLUMN_INDEX_COUNT_SQL = 1;
	private Connection connection;
	
	public CampusSubjectSeatEvaluationDAO() {
		connection = DbManager.getConnection();
	}
	
	public void addCampusSubjectSeatEvaluation(CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO){
		try{
			String sql = "insert into campus_subject_seat_evaluation(campus_subject_seat_evaluation_Id, section_subject_schedule_Id, total_seats, seats_available, start_date, end_date, created_Date,created_By,status) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,campusSubjectSeatEvaluationVO.getCampusSubjectSeatEvaluationId());
			preparedStatement.setInt(2, campusSubjectSeatEvaluationVO.getSectionSubjectScheduleId());
			preparedStatement.setInt(3, campusSubjectSeatEvaluationVO.getTotalSeats());
			preparedStatement.setInt(4, campusSubjectSeatEvaluationVO.getSeatsAvailable());
			preparedStatement.setString(5, campusSubjectSeatEvaluationVO.getStartDate());
			preparedStatement.setString(6, campusSubjectSeatEvaluationVO.getEndDate());
			preparedStatement.setDate(7, campusSubjectSeatEvaluationVO.getCreatedDate());
			preparedStatement.setString(8, campusSubjectSeatEvaluationVO.getCreatedBy());
			preparedStatement.setString(9, campusSubjectSeatEvaluationVO.getStatus());
			preparedStatement.executeUpdate();
			}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	public void deleteCampusSubjectSeatEvaluation(int campusSubjectSeatEvaluation_Id){
		try{
			String sql = "delete from campus_subject_seat_evaluation where campus_subject_seat_evaluation_Id =?";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setInt(1, campusSubjectSeatEvaluation_Id);
			preparedStatment.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}

	}
	
	public void updateSubjectSeatEvaluation(CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO){
		try{
			String sql= "update campus_subject_seat_evaluation set section_subject_schedule_Id=?, total_seats=?, seats_available=?, start_date=?,end_date=?, status=? where campus_subject_seat_evaluation_Id=?";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setInt(1, campusSubjectSeatEvaluationVO.getSectionSubjectScheduleId());
			preparedStatment.setInt(2, campusSubjectSeatEvaluationVO.getTotalSeats());
			preparedStatment.setInt(3, campusSubjectSeatEvaluationVO.getSeatsAvailable());
			preparedStatment.setString(4, campusSubjectSeatEvaluationVO.getStartDate());
			preparedStatment.setString(5, campusSubjectSeatEvaluationVO.getEndDate());
			preparedStatment.setString(6,campusSubjectSeatEvaluationVO.getStatus());
			preparedStatment.setInt(7, campusSubjectSeatEvaluationVO.getCampusSubjectSeatEvaluationId());
			preparedStatment.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public List<CampusSubjectSeatEvaluationVO> getCampusSubjectSeatEvaluationDetails(){
		List<CampusSubjectSeatEvaluationVO> list = new ArrayList<CampusSubjectSeatEvaluationVO>();
		try{
			String sql= "select * from campus_subject_seat_evaluation inner join section_subject_schedule on section_subject_schedule.section_subject_schedule_Id = campus_subject_seat_evaluation.section_subject_schedule_Id inner join campus_subject on campus_subject.campus_subject_Id = section_subject_schedule.campus_subject_Id inner join section on section.section_Id = section_subject_schedule.section_Id inner join subject on subject.subject_Id = campus_subject.subject_Id inner join major on major.major_Id = campus_subject.major_Id where section_subject_schedule.status='ACTIVE'";
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO = new CampusSubjectSeatEvaluationVO();
				campusSubjectSeatEvaluationVO.setCampusSubjectSeatEvaluationId(resultSet.getInt("campus_subject_seat_Evaluation_Id"));
				campusSubjectSeatEvaluationVO.setSectionSubjectScheduleId(resultSet.getInt("section_subject_schedule_Id"));
				campusSubjectSeatEvaluationVO.setTotalSeats(resultSet.getInt("total_Seats"));
				campusSubjectSeatEvaluationVO.setSeatsAvailable(resultSet.getInt("seats_Available"));
				campusSubjectSeatEvaluationVO.setStartDate(resultSet.getString("start_Date"));
				campusSubjectSeatEvaluationVO.setEndDate(resultSet.getString("end_Date"));
				campusSubjectSeatEvaluationVO.setMajorCode(resultSet.getString("major_Code"));
				campusSubjectSeatEvaluationVO.setSubjectName(resultSet.getString("subject_Name"));
				campusSubjectSeatEvaluationVO.setSubjectCode(resultSet.getString("subject_Code"));
				campusSubjectSeatEvaluationVO.setSectionName(resultSet.getString("section_Name"));
				campusSubjectSeatEvaluationVO.setCreatedDate(resultSet.getDate("created_Date"));
				campusSubjectSeatEvaluationVO.setCreatedBy(resultSet.getString("created_By"));
				campusSubjectSeatEvaluationVO.setStatus(resultSet.getString("status"));
				list.add(campusSubjectSeatEvaluationVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;

	}

		public List<CampusSubjectSeatEvaluationVO> getCampusSubjectSeatEvaluationDetails(int pageNo, int pageSize){
		int offset = (pageNo ==1)? 0: (pageNo-1) * pageSize;
		String sql = "select * from campus_subject_seat_evaluation inner join section_subject_schedule on section_subject_schedule.section_subject_schedule_Id = campus_subject_seat_evaluation.section_subject_schedule_Id inner join campus_subject on campus_subject.campus_subject_Id = section_subject_schedule.campus_subject_Id inner join section on section.section_Id = section_subject_schedule.section_Id inner join subject on subject.subject_Id = campus_subject.subject_Id inner join major on major.major_Id = campus_subject.major_Id ";
		sql+= " limit " + pageSize + "  " + " offset " + offset;
		List<CampusSubjectSeatEvaluationVO> list = new ArrayList<CampusSubjectSeatEvaluationVO>();
		try{
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatment.executeQuery();
			while(resultSet.next()){
				CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO = new CampusSubjectSeatEvaluationVO();
				campusSubjectSeatEvaluationVO.setCampusSubjectSeatEvaluationId(resultSet.getInt("campus_subject_seat_Evaluation_Id"));
				campusSubjectSeatEvaluationVO.setSectionSubjectScheduleId(resultSet.getInt("section_subject_schedule_Id"));
				campusSubjectSeatEvaluationVO.setTotalSeats(resultSet.getInt("total_Seats"));
				campusSubjectSeatEvaluationVO.setSeatsAvailable(resultSet.getInt("seats_available"));
				campusSubjectSeatEvaluationVO.setStartDate(resultSet.getString("start_date"));
				campusSubjectSeatEvaluationVO.setEndDate(resultSet.getString("end_date"));
				campusSubjectSeatEvaluationVO.setMajorCode(resultSet.getString("major_Code"));
				campusSubjectSeatEvaluationVO.setSubjectName(resultSet.getString("subject_Name"));
				campusSubjectSeatEvaluationVO.setSubjectCode(resultSet.getString("subject_Code"));
				campusSubjectSeatEvaluationVO.setSectionName(resultSet.getString("section_Name"));
				campusSubjectSeatEvaluationVO.setStatus(resultSet.getString("status"));
				list.add(campusSubjectSeatEvaluationVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	public CampusSubjectSeatEvaluationVO findCampusSubjectSeatEvaluationById(int campusSubjectSeatEvaluation_Id){
		CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO = new CampusSubjectSeatEvaluationVO();
		String sql = "select * from campus_subject_seat_evaluation where campus_subject_seat_evaluation_Id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, campusSubjectSeatEvaluation_Id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				campusSubjectSeatEvaluationVO.setCampusSubjectSeatEvaluationId(resultSet.getInt("campus_subject_seat_evaluation_Id"));
				campusSubjectSeatEvaluationVO.setSectionSubjectScheduleId(resultSet.getInt("section_subject_schedule_Id"));
				campusSubjectSeatEvaluationVO.setTotalSeats(resultSet.getInt("total_Seats"));
				campusSubjectSeatEvaluationVO.setSeatsAvailable(resultSet.getInt("seats_Available"));
				campusSubjectSeatEvaluationVO.setStartDate(resultSet.getString("start_Date"));
				campusSubjectSeatEvaluationVO.setEndDate(resultSet.getString("end_Date"));
				campusSubjectSeatEvaluationVO.setCreatedDate(resultSet.getDate("created_Date"));
				campusSubjectSeatEvaluationVO.setCreatedBy(resultSet.getString("created_By"));
				campusSubjectSeatEvaluationVO.setStatus(resultSet.getString("status"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return campusSubjectSeatEvaluationVO;
	}
	public long count(){
		String sql = "select count(*) from campus_subject_seat_evaluation";
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
