package service;

import java.util.List;

import model.Student;

public class StudentService {
	public  List<Student> getAllStudents(){
		return Student.dao.find("select * from tbl_student");
	} 
}
