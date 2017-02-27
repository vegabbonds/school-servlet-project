package nhn.intern.java.service;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.StudentController;
import nhn.intern.java.dao.StudentDao;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.model.person.Student;

public class StudentService {
	private static StudentController studentController = new StudentController();

	public void addStudent(Student student) {
		if (SchoolPerson.studentMap.size() >= Constant.MAX_PERSON) {
			studentController.doPrintFullPeople();
			return;
		}
		int studentId = student.getId();
		if (!SchoolPerson.studentMap.containsKey(studentId)) {
			StudentDao.addStudent(student);
			return;
		} else {
			studentController.doPrintIdExist();
			return;
		}
	}

	public void modifyStudent(Student student) {
		int studentId = student.getId();
		if (!SchoolPerson.studentMap.containsKey(studentId)) {
			studentController.doPrintIdNotExist();
		} else {
			StudentDao.modifyStudent(student);
		}
	}

	public boolean deleteStudent(int studentId) {
		if (SchoolPerson.studentMap.containsKey(studentId)) {
			StudentDao.deleteStudent(studentId);
			return true;
		} else {
			return false;
		}
	}

	public boolean checkStudent(int studentId) {
		if (SchoolPerson.studentMap.containsKey(studentId)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAllStudent() {
		if (SchoolPerson.studentMap.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
