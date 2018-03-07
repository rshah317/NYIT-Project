package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Nyit.Dbutilis.DbManager;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.StudentRegistrationRequestVO;




public class StudentRequestDAO {
	private static final int FIRST_COLUMN_INDEX_COUNT_SQL = 1;
	private Connection connection;
	public StudentRequestDAO() {
		connection = DbManager.getConnection();
	}
public void addStudentRequest(StudentRegistrationRequestVO registrationRequestVO){
	try{
		String sql = "insert into student_registration_request(student_registration_request_Id, student_Id, schedule_Id, status) values (?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,registrationRequestVO.getStudentRegistrationRequestId());
		preparedStatement.setInt(2,registrationRequestVO.getStudent_Id());
		preparedStatement.setString(4,registrationRequestVO.getStatus());
		for (int scheduleId : registrationRequestVO.getSchedule_Id()) {
			preparedStatement.setInt(3,scheduleId);
			preparedStatement.addBatch();
		}
		preparedStatement.executeBatch();
	}catch (SQLException e){
		e.printStackTrace();
	}

	}
public List<StudentRegistrationRequestVO>getStudentRequestDetails(){
	String sql="select student_registration_request.student_Id,student_registration_request.status,student_registration_request_Id,student.student_nyit_Id,student.last_name,student.first_name,student.email_Id,student.academic_program,student.campus,cell_phoneNo,group_concat(distinct(section.section_Name)) as section ,group_concat(distinct(subject.subject_Code)) as subject from student_registration_request inner join student on student.student_Id = student_registration_request.student_Id inner join campus_schedule on campus_schedule.schedule_Id = student_registration_request.schedule_Id inner join campus_subject_seat_evaluation on campus_subject_seat_evaluation.campus_subject_seat_evaluation_Id = campus_schedule.campus_subject_seat_evaluation_Id inner join section_subject_schedule on section_subject_schedule.section_subject_schedule_Id = campus_subject_seat_evaluation.section_subject_schedule_Id inner join section ON section.section_Id = section_subject_schedule.section_Id inner join campus_subject ON campus_subject.campus_subject_Id = section_subject_schedule.campus_subject_Id inner join subject ON subject.subject_Id = campus_subject.subject_Id group by student_registration_request.student_Id";
	List<StudentRegistrationRequestVO> list = new ArrayList<StudentRegistrationRequestVO>();
	try{
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			StudentRegistrationRequestVO registrationRequestVO = new StudentRegistrationRequestVO();
			registrationRequestVO.setStudentRegistrationRequestId(resultSet.getInt("student_registration_request_Id"));
			registrationRequestVO.setStudent_Id(resultSet.getInt("student_Id"));
			registrationRequestVO.setStudent_nyit_Id(resultSet.getInt("student_nyit_Id"));
			registrationRequestVO.setFirstName(resultSet.getString("first_Name"));
			registrationRequestVO.setLastName(resultSet.getString("last_name"));
			registrationRequestVO.setEmail_Id(resultSet.getString("email_Id"));
			registrationRequestVO.setAcademicProgram(resultSet.getString("academic_program"));
			registrationRequestVO.setCampus(resultSet.getString("campus"));
			registrationRequestVO.setSection(resultSet.getString("section"));
			registrationRequestVO.setSubjectName(resultSet.getString("subject"));
			registrationRequestVO.setStatus(resultSet.getString("status"));
			list.add(registrationRequestVO);
		}
	}catch (SQLException e){
		e.printStackTrace();
	}
	return list;
}
public long count(){
	String sql = "select count(*) from student_registration_request";
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
public StudentRegistrationRequestVO findStudentsById(int studentId){
	String sql="select student_registration_request.status,student_registration_request_Id,student.student_nyit_Id,student.last_name,student.first_name,student.email_Id,student.academic_program,student.campus,cell_phoneNo,group_concat(distinct(student_registration_request.schedule_Id)) as schedule ,group_concat(distinct(section.section_Name)) as section ,group_concat(distinct(subject.subject_Code)) as subject from student_registration_request inner join student on student.student_Id = student_registration_request.student_Id inner join campus_schedule on campus_schedule.schedule_Id = student_registration_request.schedule_Id inner join campus_subject_seat_evaluation on campus_subject_seat_evaluation.campus_subject_seat_evaluation_Id = campus_schedule.campus_subject_seat_evaluation_Id inner join section_subject_schedule on section_subject_schedule.section_subject_schedule_Id = campus_subject_seat_evaluation.section_subject_schedule_Id inner join section on section.section_Id = section_subject_schedule.section_Id inner join campus_subject on campus_subject.campus_subject_Id = section_subject_schedule.campus_subject_Id inner join subject ON subject.subject_Id = campus_subject.subject_Id where student_registration_request.student_Id=?";
	StudentRegistrationRequestVO registrationRequestVO = new StudentRegistrationRequestVO();
	try{
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, studentId);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()){
			registrationRequestVO.setStudentRegistrationRequestId(resultSet.getInt("student_registration_request_Id"));
			registrationRequestVO.setStudent_nyit_Id(resultSet.getInt("student_nyit_Id"));
			registrationRequestVO.setFirstName(resultSet.getString("first_Name"));
			registrationRequestVO.setLastName(resultSet.getString("last_name"));
			registrationRequestVO.setEmail_Id(resultSet.getString("email_Id"));
			registrationRequestVO.setAcademicProgram(resultSet.getString("academic_program"));
			registrationRequestVO.setCampus(resultSet.getString("campus"));
			registrationRequestVO.setSection(resultSet.getString("section"));
			registrationRequestVO.setSubjectName(resultSet.getString("subject"));
			registrationRequestVO.setStatus(resultSet.getString("status"));
		}
	}catch (SQLException e){
		e.printStackTrace();
	}
	return registrationRequestVO;
	
}
public void updateStatus(int studentId) throws SQLException{
	String sql = "update student_registration_request set status=? where student_Id=?";
	PreparedStatement preparedStatement = connection.prepareStatement(sql);
	preparedStatement.setString(1,StoreConstant.STATUS_APPROVED);
	preparedStatement.setInt(2,studentId);
	preparedStatement.executeUpdate();
}
public void saveApprovedSubject(StudentRegistrationRequestVO registrationRequestVO){
	String sql = "insert into student_approved_subject (student_approved_Id,student_registration_request_Id,student_Id,subject_Name,status,ApprovedDeclined_by) values (?,?,?,?,?,?)";		
	try{
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, registrationRequestVO.getStudent_approved_Id());
		preparedStatement.setInt(2,registrationRequestVO.getStudentRegistrationRequestId());
		preparedStatement.setInt(3, registrationRequestVO.getStudent_Id());
	    preparedStatement.setString(4, registrationRequestVO.getSubjectName());
		preparedStatement.setString(5, registrationRequestVO.getStatus());
		preparedStatement.setString(6, registrationRequestVO.getApproveDeclineBy());
		preparedStatement.executeUpdate();
	}catch (SQLException e) {
		e.printStackTrace();
	}
}
public void updateDeclineStatus(int studentId) throws SQLException{
	String sql = "update student_registration_request set status=? where student_Id=?";
	PreparedStatement preparedStatement = connection.prepareStatement(sql);
	preparedStatement.setString(1,StoreConstant.STATUS_DECLINE);
	preparedStatement.setInt(2,studentId);
	preparedStatement.executeUpdate();
}
}
