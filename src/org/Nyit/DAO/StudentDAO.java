package org.Nyit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Nyit.Dbutilis.DbManager;
import org.Nyit.VO.StudentVO;

public class StudentDAO {
	private static final int FIRST_COLUMN_INDEX_COUNT_SQL = 1;
	private Connection connection;
	public StudentDAO() {
		connection = DbManager.getConnection();
	}

	public void addStudent(StudentVO studentVO){
		try{
			String sql = "insert into student(student_Id, student_nyit_Id, first_name, last_name, address, city, state, zipcode, email_Id, home_phoneNo, work_phoneNo, cell_phoneNo, academic_program, campus) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,studentVO.getStudentId());
			preparedStatement.setInt(2,studentVO.getStudentNyitId());
			preparedStatement.setString(3,studentVO.getFirstName());
			preparedStatement.setString(4,studentVO.getLastName());
			preparedStatement.setString(5,studentVO.getAddress());
			preparedStatement.setString(6,studentVO.getCity());
			preparedStatement.setString(7,studentVO.getState());
			preparedStatement.setLong(8,studentVO.getZipcode());
			preparedStatement.setString(9,studentVO.getEmailId());
			preparedStatement.setLong(10,studentVO.getHomePhone());
			preparedStatement.setLong(11,studentVO.getWorkPhone());
			preparedStatement.setLong(12,studentVO.getCellPhone());
			preparedStatement.setString(13,studentVO.getAcademicProgram());
			for (String campus : studentVO.getCampus()) {
				preparedStatement.setString(14,campus);
				preparedStatement.addBatch();

			}
			preparedStatement.executeBatch();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void addStudentSubjectInfo(StudentVO studentVO){
		try{
			String sql = "insert into student_subject_info(subjectInfo_Id,student_Id,term,subject) values (?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,studentVO.getStudentInfoId());
			preparedStatement.setInt(2,studentVO.getStudentId());
			for (String Term : studentVO.getTerm()) {
				preparedStatement.setString(3,Term);
			}
			for (String subject : studentVO.getSubject()) {
				preparedStatement.setString(4,subject);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void deleteStudent(int student_Id){
		try{
			String sql = "delete from student where student_Id =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,student_Id);
			preparedStatement.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public void updateStudent(StudentVO studentVO){
		try{
			String sql = "update student set first_name=?, last_name=? where student_Id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,studentVO.getFirstName());
			preparedStatement.setString(2, studentVO.getLastName());
			preparedStatement.setInt(3,studentVO.getStudentId());
			preparedStatement.executeUpdate();

		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public List<StudentVO>getStudentDetails(){
		String sql="select student_subject_info.student_Id,student.student_nyit_Id,student.last_name,student.first_name,student.email_Id,student.academic_program,student.campus,term,cell_phoneNo,group_concat(distinct(student_subject_info.subject)) as subject from student_subject_info join student on student_subject_info.student_Id = student.student_Id group by student_subject_info.student_Id";
		List<StudentVO> list = new ArrayList<StudentVO>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				StudentVO studentVO = new StudentVO();
				studentVO.setStudentId(resultSet.getInt("Student_Id"));
				studentVO.setStudentNyitId(resultSet.getInt("student_nyit_Id"));
				studentVO.setFirstName(resultSet.getString("first_name"));
				studentVO.setLastName(resultSet.getString("last_name"));
				studentVO.setEmailId(resultSet.getString("email_Id"));
				studentVO.setCellPhone(resultSet.getLong("cell_phoneNo"));
				studentVO.setAcademicProgram(resultSet.getString("academic_program"));
				studentVO.setCampusName(resultSet.getString("campus"));
				studentVO.setTerm(resultSet.getString("term").split(","));
				studentVO.setSubject(resultSet.getString("subject").split(","));
				list.add(studentVO);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	public StudentVO findStudentById(int nyit_Id){
		StudentVO studentVO = new StudentVO();
		String sql = "select * from student where student_nyit_Id=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, nyit_Id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				studentVO.setStudentId(resultSet.getInt("student_Id"));
				studentVO.setStudentNyitId(resultSet.getInt("student_nyit_Id"));
				studentVO.setFirstName(resultSet.getString("first_name"));
				studentVO.setLastName(resultSet.getString("last_name"));
				studentVO.setEmailId(resultSet.getString("email_Id"));
				studentVO.setAddress(resultSet.getString("address"));
				studentVO.setCity(resultSet.getString("city"));
				studentVO.setState(resultSet.getString("state"));
				studentVO.setZipcode(resultSet.getLong("zipcode"));
				studentVO.setHomePhone(resultSet.getLong("home_phoneNo"));
				studentVO.setWorkPhone(resultSet.getLong("work_phoneNo"));
				studentVO.setCellPhone(resultSet.getLong("cell_phoneNo"));
				studentVO.setAcademicProgram(resultSet.getString("academic_program"));
				studentVO.setCampusName(resultSet.getString("campus"));
			}

		}catch (SQLException e){
			e.printStackTrace();

		}
		return studentVO;
	}
	public long count(){
		String sql = "select count(*) from student";
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
