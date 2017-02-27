package nhn.intern.java.service;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.SubjectController;
import nhn.intern.java.dao.SubjectDao;
import nhn.intern.java.database.SchoolData;
import nhn.intern.java.model.object.Subject;

public class SubjectService {

	public static final int MAX_SUBJECT = 100;
	private SubjectController subjectController;

	public void addSubject(Subject subject) {
		if (SchoolData.subjectMap.size() >= Constant.MAX_PERSON) {
			subjectController.doPrintFullSubject();
			return;
		}
		int subjectId = subject.getSubjectId();
		if (!SchoolData.subjectMap.containsKey(subjectId)) {
			SubjectDao.addSubject(subject);
		} else {
			subjectController.doPrintIdExist();
		}
	}

	public void modifySubject(Subject subject) {
		int subjectId = subject.getSubjectId();
		if (!SchoolData.subjectMap.containsKey(subjectId)) {
			subjectController.doPrintIdNotExist();
		} else {
			SubjectDao.modifySubject(subject);
		}
	}

	public boolean deleteSubject(int subjectId) {
		if (SchoolData.subjectMap.containsKey(subjectId)) {
			SubjectDao.deleteSubject(subjectId);
			return true;
		} else {
			return false;
		}
	}

	public boolean checkSubject(int subjectId) {
		if (SchoolData.subjectMap.containsKey(subjectId)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAllSubject() {
		if (SchoolData.subjectMap.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
