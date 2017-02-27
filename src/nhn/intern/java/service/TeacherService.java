package nhn.intern.java.service;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.TeacherController;
import nhn.intern.java.dao.TeacherDao;
import nhn.intern.java.database.SchoolData;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.model.person.Teacher;

public class TeacherService {
	private static TeacherController teacherController = new TeacherController();

	/**
	 * teacher를 추가하기 위한 메소드.
	 * 제약조건
	 * 1. unique Id
	 * 2. 제한인원 이내
	 * 3. 과목을 반드시 가져야 한다.
	 * @param teacher
	 */
	public void addTeacher(Teacher teacher) {
		if (SchoolPerson.teacherMap.size() >= Constant.MAX_PERSON) {
			teacherController.doPrintFullPeople();
			return;
		}

		int teacherId = teacher.getId();
		int subjectId = teacher.getSubjectId();
		if (SchoolPerson.teacherMap.containsKey(teacherId)) {
			teacherController.doPrintIdExist();
			return;
		}

		if (SchoolData.subjectMap.containsKey(subjectId)) {
			TeacherDao.addTeacher(teacher);
		} else {
			teacherController.doPrintSubjectIdNotExist();
		}
	}

	public void modifyTeacher(Teacher teacher) {

		int teacherId = teacher.getId();
		if (!SchoolPerson.teacherMap.containsKey(teacherId)) {
			teacherController.doPrintIdNotExist();
		}
		int subjectId = teacher.getSubjectId();
		if (SchoolData.subjectMap.containsKey(subjectId)) {
			TeacherDao.modifyTeacher(teacher);
		} else {
			teacherController.doPrintSubjectIdNotExist();
		}
	}

	public boolean deleteTeacher(int teacherId) {
		if (SchoolPerson.teacherMap.containsKey(teacherId)) {
			TeacherDao.deleteTeacher(teacherId);
			return true;
		} else {
			return false;
		}
	}

	public boolean checkTeacher(int teacherId) {
		if (SchoolPerson.teacherMap.containsKey(teacherId)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAllTeacher() {
		if (SchoolPerson.teacherMap.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
