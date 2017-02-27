package nhn.intern.java.view;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.SubjectController;
import nhn.intern.java.database.SchoolData;
import nhn.intern.java.model.object.Subject;
import nhn.intern.java.util.IOUtil;

public class SubjectView {

	static SubjectController subjectController = new SubjectController();

	public void subjectMenuBar() {
		System.out.println("---------과목관리----------");
		System.out.println("1. 입력");
		System.out.println("2. 수정");
		System.out.println("3. 삭제");
		System.out.println("4. 조회");
		System.out.println("5. 전체조회");
		System.out.println("6. 상위메뉴");
		System.out.print("항목을 선택해 주십시오 : ");
	}

	public void inputMenu() {
		while (true) {
			int choice = getChoiceValue();
			switch (choice) {
				case 1:
					addSubjectInfo();
					break;
				case 2:
					modifySubjectInfo();
					break;
				case 3:
					deleteSubjectInfo();
					break;
				case 4:
					checkSubjectInfo();
					break;
				case 5:
					printAllSubject();
					subjectController.doCheckAllSubject();
					break;
				case 6:
					return;
				default:
					invalidInput();
					break;
			}
		}
	}

	public void addSubjectInfo() {
		Subject subject = getSubject();
		int subjectId = subject.getSubjectId();

		if (!SchoolData.subjectMap.containsKey(subjectId)) {
			subjectController.doAddSubject(subject);
		} else {
			printSubjectIdExist();
			return;
		}
	}

	public void modifySubjectInfo() {
		Subject subject = getSubject();
		subjectController.doModifySubject(subject);
	}

	public void deleteSubjectInfo() {
		System.out.print("ID를 입력해주십시오 : ");
		int subjectId = IOUtil.getIntId();
		subjectController.doDeleteSubject(subjectId);
	}

	public void checkSubjectInfo() {
		System.out.print("ID를 입력해주십시오 : ");
		int subjectId = IOUtil.getIntId();
		Subject subject = SchoolData.subjectMap.get(subjectId);
		if (subject != null) {
			printSubjectInfo(subject);
		}
		subjectController.doCheckSubject(subjectId);
	}

	public int getChoiceValue() {
		subjectMenuBar();
		int choice = IOUtil.getInt();
		return choice;
	}

	public Subject getSubject() {
		Subject newSubject = new Subject();
		System.out.print("ID를 입력해주십시오 : ");
		int subjectId = IOUtil.getIntId();
		System.out.print("subject Name를 입력해주십시오 : ");
		String subjectName = IOUtil.getStringSubjectName();

		newSubject.setSubjectId(subjectId);
		newSubject.setSubjectName(subjectName);
		return newSubject;
	}

	public void invalidInput() {
		System.out.println("잘못된 input입니다.");
	}

	public void printFullSubject() {
		System.out.println("과목이 가득 찼습니다.");
	}

	public void printSubjectEmpty() {
		System.out.println("과목이 비었습니다.");
	}

	public void printSubjectIdNotExist() {
		System.out.println("해당 ID를 가진 과목이 없습니다.");
	}

	public void printSubjectIdExist() {
		System.out.println("해당 ID를 가진 과목이 이미 있습니다.");
	}

	public void noExistSubjectId() {
		System.out.println("해당 ID를 가진 과목이 없습니다");
	}

	public void viewSubjectTitle() {
		System.out.println("| ID |SUBJECT_NAME|");
	}

	/**
	 * 모든 과목을 출력하는 메소드.
	 * 일정 길이를 넘어갈 경우에 잘라서 출력.
	 */
	public void printAllSubject() {
		viewSubjectTitle();
		for (Integer subjectId : SchoolData.subjectMap.keySet()) {
			Subject subject = SchoolData.subjectMap.get(subjectId);
			String formatName = subject.getSubjectName();
			int nameLen = subject.getSubjectName().length();
			if (nameLen > Constant.MAX_SUBJECT_LENGTH) {
				formatName = subject.getSubjectName().substring(0, Constant.MAX_SUBJECT_LENGTH);
			}
			System.out.printf(" %04d %12s\n", subject.getSubjectId(), formatName);
		}
	}

	/**
	 * 특정 과목을 출력하는 메소드.
	 * 일정 길이를 넘어갈 경우에 잘라서 출력.
	 * @param subject
	 */
	public void printSubjectInfo(Subject subject) {
		viewSubjectTitle();
		String formatName = subject.getSubjectName();
		int nameLen = subject.getSubjectName().length();
		if (nameLen > Constant.MAX_SUBJECT_LENGTH) {
			formatName = subject.getSubjectName().substring(0, Constant.MAX_SUBJECT_LENGTH);
		}
		System.out.printf(" %04d %12s\n", subject.getSubjectId(), formatName);
	}

}
