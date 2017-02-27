package nhn.intern.java.controller;

import nhn.intern.java.model.person.Teacher;
import nhn.intern.java.service.TeacherService;
import nhn.intern.java.view.SubjectView;
import nhn.intern.java.view.TeacherView;

public class TeacherController {
	static TeacherService teacherService = new TeacherService();
	static TeacherView teacherView = new TeacherView();
	static SubjectView subjectView = new SubjectView();

	public void doAddTeacher(Teacher teacher) {
		teacherService.addTeacher(teacher);
	}

	public void doModifyTeacher(Teacher teacher) {
		teacherService.modifyTeacher(teacher);
	}

	public void doDeleteTeacher(int teacherId) {
		if (!teacherService.deleteTeacher(teacherId)) {
			teacherView.printIdNotExist();
		}
	}

	public void doCheckTeacher(int teacherId) {
		if (!teacherService.checkTeacher(teacherId)) {
			teacherView.printIdNotExist();
		}
	}

	public void doCheckAllTeacher() {
		if (!teacherService.checkAllTeacher()) {
			teacherView.printPeopleEmpty();
		}
	}

	public void doPrintFullPeople() {
		teacherView.printFullPeople();
	}

	public void doPrintIdExist() {
		teacherView.printIdExist();
	}

	public void doPrintIdNotExist() {
		teacherView.printIdNotExist();
	}

	public void doPrintSubjectIdNotExist() {
		subjectView.printSubjectIdNotExist();
	}
}
