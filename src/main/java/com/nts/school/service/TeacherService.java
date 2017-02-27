package com.nts.school.service;

import java.util.ArrayList;
import java.util.List;

import com.nts.school.dao.SubjectDao;
import com.nts.school.dao.TeacherDao;
import com.nts.school.util.Constant;
import com.nts.school.util.ResultMessage;
import com.nts.school.util.SearchOption;
import com.nts.school.vo.person.Teacher;

public class TeacherService {

	private TeacherDao teacherDao;
	private SubjectDao subjectDao;

	public TeacherService() {
		teacherDao = new TeacherDao();
		subjectDao = new SubjectDao();
	}

	public ResultMessage addTeacher(Teacher teacher) {
		if (teacherDao.getTeacherCount() >= Constant.MAX_PERSON) {
			return ResultMessage.ADD_FAIL;
		}

		if (subjectDao.isSubjectExistById(teacher.getSubjectId()) == false) {
			return ResultMessage.ADD_FAIL;
		}

		return teacherDao.addTeacher(teacher);
	}

	public ResultMessage modifyTeacher(Teacher teacher) {
		if (teacherDao.isTeacherExistById(teacher.getId()) == false) {
			return ResultMessage.MODIFY_FAIL;
		}

		if (subjectDao.isSubjectExistById(teacher.getSubjectId()) == false) {
			return ResultMessage.MODIFY_FAIL;
		}

		return teacherDao.modifyTeacher(teacher);
	}

	public ResultMessage deleteTeacher(int teacherId) {
		if (teacherDao.isTeacherExistById(teacherId) == false) {
			return ResultMessage.DELETE_FAIL;
		}

		return teacherDao.deleteTeacher(teacherId);
	}

	public List<Teacher> searchTeacher() {
		List<Teacher> teachers = new ArrayList<Teacher>();
		teachers = teacherDao.searchTeacher();
		return teachers;
	}

	public List<Teacher> searchSpecificTeacher(String getData, String selectOption) throws Exception {
		List<Teacher> specificTeachers = new ArrayList<Teacher>();
		SearchOption option = SearchOption.findSearchOption(selectOption);
		switch (option) {
			case ID:
				return searchTeacherById(getData);
			case NAME:
				return searchTeacherByName(getData);
			default:
				return specificTeachers;
		}
	}

	public List<Teacher> searchTeacherById(String id) {
		List<Teacher> specificTeachers = new ArrayList<Teacher>();
		specificTeachers = teacherDao.searchTeacherById(Integer.parseInt(id));
		return specificTeachers;
	}

	public List<Teacher> searchTeacherByName(String name) {
		List<Teacher> specificTeachers = new ArrayList<Teacher>();
		specificTeachers = teacherDao.searchTeacherByName(name);
		return specificTeachers;
	}

}
