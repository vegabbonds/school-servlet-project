package nhn.intern.java.view;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.TeacherController;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.model.person.Teacher;
import nhn.intern.java.util.IOUtil;

/**
 * 사용자가 보는 화면 및 input을 받아서
 * Controller에 넘기는 메소드.
 * @author 이정석
 */
public class TeacherView extends StandardView {

	static TeacherController teacherController = new TeacherController();

	public void teacherMenuBar() {
		System.out.println("---------선생님관리----------");
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
					addTeacherInfo();
					break;
				case 2:
					modifyTeacherInfo();
					break;
				case 3:
					deleteTeacherInfo();
					break;
				case 4:
					checkTeacherInfo();
					break;
				case 5:
					printAllTeacher();
					teacherController.doCheckAllTeacher();
					break;
				case 6:
					return;
				default:
					invalidInput();
					break;
			}
		}
	}

	public void addTeacherInfo() {
		Teacher teacher = getTeacher();
		teacherController.doAddTeacher(teacher);
	}

	public void modifyTeacherInfo() {
		Teacher teacher = getTeacher();
		teacherController.doModifyTeacher(teacher);
	}

	public void deleteTeacherInfo() {
		System.out.print("ID를 입력해주십시오 : ");
		int teacherId = IOUtil.getIntId();
		teacherController.doDeleteTeacher(teacherId);
	}

	public void checkTeacherInfo() {
		System.out.print("ID를 입력해주십시오 : ");
		int teacherId = IOUtil.getIntId();
		Teacher teacher = SchoolPerson.teacherMap.get(teacherId);
		if (teacher != null) {
			printTeacherInfo(teacher);
		}
		teacherController.doCheckTeacher(teacherId);
	}

	public int getChoiceValue() {
		teacherMenuBar();
		int choice = IOUtil.getInt();
		return choice;
	}

	public Teacher getTeacher() {
		Teacher newTeacher = new Teacher();
		System.out.print("ID를 입력해주십시오 : ");
		int teacherId = IOUtil.getIntId();
		System.out.print("Name를 입력해주십시오 : ");
		String teacherName = IOUtil.getStringName();
		System.out.print("BirthDate를 입력해주십시오 (ex>19900302): ");
		int teacherBirth = IOUtil.getIntBirthDate();
		System.out.print("SubjectID를 입력해주십시오 : ");
		int teacherSubjectId = IOUtil.getIntId();

		newTeacher.setId(teacherId);
		newTeacher.setName(teacherName);
		newTeacher.setBirthDate(teacherBirth);
		newTeacher.setSubjectId(teacherSubjectId);
		return newTeacher;
	}

	@Override
	public void viewPersonTitle() {
		System.out.println("|NAMEID|SUBJECT| NAME | BIRTHDATE |");
	}

	public void printAllTeacher() {
		viewPersonTitle();
		for (Integer teacherId : SchoolPerson.teacherMap.keySet()) {
			Teacher teacher = SchoolPerson.teacherMap.get(teacherId);
			String formatName = teacher.getName();
			int nameLen = teacher.getName().length();
			if (nameLen > Constant.MAX_NAME_LENGTH) {
				formatName = teacher.getName().substring(0, Constant.MAX_NAME_LENGTH);
			}
			System.out.printf("   %04d    %04d %5s   %8d\n", teacher.getId(), teacher.getSubjectId(), formatName,
				teacher.getBirthDate());
		}
	}

	public void printTeacherInfo(Teacher teacher) {
		viewPersonTitle();
		String formatName = teacher.getName();
		int nameLen = teacher.getName().length();
		if (nameLen > Constant.MAX_NAME_LENGTH) {
			formatName = teacher.getName().substring(0, Constant.MAX_NAME_LENGTH);
		}
		System.out.printf("   %04d    %04d %5s   %8d\n", teacher.getId(), teacher.getSubjectId(), formatName,
			teacher.getBirthDate());
	}
}
