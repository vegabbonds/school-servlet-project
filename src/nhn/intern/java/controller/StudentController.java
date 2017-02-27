package nhn.intern.java.controller;

import nhn.intern.java.model.person.Student;
import nhn.intern.java.service.StudentService;
import nhn.intern.java.view.StudentView;

public class StudentController {

	static StudentService studentService = new StudentService();
	static StudentView studentView = new StudentView();

	public void doAddStudent(Student student) {
		studentService.addStudent(student);
	}

	public void doModifyStudent(Student student) {
		studentService.modifyStudent(student);
	}

	public void doDeleteStudent(int studentId) {
		if (!studentService.deleteStudent(studentId)) {
			studentView.printIdNotExist();
		}
	}

	public void doCheckStudent(int studentId) {
		if (!studentService.checkStudent(studentId)) {
			studentView.printIdNotExist();
		}
	}

	public void doCheckAllStudent() {
		if (!studentService.checkAllStudent()) {
			studentView.printPeopleEmpty();
		}
	}

	public void doPrintFullPeople() {
		studentView.printFullPeople();
	}

	public void doPrintIdExist() {
		studentView.printIdExist();
	}

	public void doPrintIdNotExist() {
		studentView.printIdNotExist();
	}
}
