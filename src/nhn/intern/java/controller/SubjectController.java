package nhn.intern.java.controller;

import nhn.intern.java.model.object.Subject;
import nhn.intern.java.service.SubjectService;
import nhn.intern.java.view.SubjectView;

public class SubjectController {
	static SubjectService subjectService = new SubjectService();
	static SubjectView subjectView = new SubjectView();

	public void doAddSubject(Subject subject) {
		subjectService.addSubject(subject);
	}

	public void doModifySubject(Subject subject) {
		subjectService.modifySubject(subject);
	}

	public void doDeleteSubject(int subjectId) {
		if (!subjectService.deleteSubject(subjectId)) {
			subjectView.printSubjectIdNotExist();
		}
	}

	public void doCheckSubject(int subjectId) {
		if (!subjectService.checkSubject(subjectId)) {
			subjectView.printSubjectIdNotExist();
		}
	}

	public void doCheckAllSubject() {
		if (!subjectService.checkAllSubject()) {
			subjectView.printSubjectEmpty();
		}
	}

	public void doPrintFullSubject() {
		subjectView.printFullSubject();
	}

	public void doPrintIdExist() {
		subjectView.printSubjectIdExist();
	}

	public void doPrintIdNotExist() {
		subjectView.printSubjectIdNotExist();
	}
}
