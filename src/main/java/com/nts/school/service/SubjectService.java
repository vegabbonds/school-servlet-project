package com.nts.school.service;

import java.util.ArrayList;
import java.util.List;

import com.nts.school.dao.ScoreDao;
import com.nts.school.dao.SubjectDao;
import com.nts.school.dao.TeacherDao;
import com.nts.school.util.Constant;
import com.nts.school.util.ResultMessage;
import com.nts.school.util.SearchOption;
import com.nts.school.vo.object.Subject;

public class SubjectService {

	private SubjectDao subjectDao;
	private ScoreDao scoreDao;
	private TeacherDao teacherDao;

	public SubjectService() {
		subjectDao = new SubjectDao();
		scoreDao = new ScoreDao();
		teacherDao = new TeacherDao();
	}

	public ResultMessage addSubject(Subject subject) {
		if (subjectDao.getSubjectCount() >= Constant.MAX_PERSON) {
			return ResultMessage.ADD_FAIL;
		}

		return subjectDao.addSubject(subject);
	}

	public ResultMessage modifySubject(Subject subject) {
		if (subjectDao.isSubjectExistById(subject.getSubjectId()) == false) {
			return ResultMessage.MODIFY_FAIL;
		}

		return subjectDao.modifySubject(subject);
	}

	public ResultMessage deleteSubject(int subjectId) {
		ResultMessage resultMessage = ResultMessage.SUCCESS;

		resultMessage = scoreDao.deleteScoreBySubject(subjectId);
		if (resultMessage != ResultMessage.SUCCESS) {
			return ResultMessage.DELETE_FAIL;
		}

		resultMessage = teacherDao.deleteTeacherBySubject(subjectId);
		if (resultMessage != ResultMessage.SUCCESS) {
			return ResultMessage.DELETE_FAIL;
		}

		if (subjectDao.isSubjectExistById(subjectId) == false) {
			return ResultMessage.DELETE_FAIL;
		}

		return subjectDao.deleteSubject(subjectId);
	}

	public List<Subject> searchSubject() {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = subjectDao.searchSubject();
		return subjects;
	}

	public List<Subject> searchSpecificSubject(String getData, String selectOption) throws Exception {
		List<Subject> specificSubjects = new ArrayList<Subject>();
		SearchOption option = SearchOption.findSearchOption(selectOption);
		switch (option) {
			case ID:
				return searchSubjectById(getData);
			case NAME:
				return searchSubjectByName(getData);
			default:
				return specificSubjects;
		}
	}

	public List<Subject> searchSubjectById(String id) {
		List<Subject> specificSubjects = new ArrayList<Subject>();
		specificSubjects = subjectDao.searchSubjectById(Integer.parseInt(id));
		return specificSubjects;
	}

	public List<Subject> searchSubjectByName(String name) {
		List<Subject> specificSubjects = new ArrayList<Subject>();
		specificSubjects = subjectDao.searchSubjectByName(name);
		return specificSubjects;
	}

}
