package nhn.intern.java.view;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.StudentController;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.model.person.Student;
import nhn.intern.java.util.IOUtil;

public class StudentView extends StandardView {

	static StudentController studentController = new StudentController();

	public void studentMenuBar() {
		System.out.println("---------학생관리----------");
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
					addStudentInfo();
					break;
				case 2:
					modifyStudentInfo();
					break;
				case 3:
					deleteStudentInfo();
					break;
				case 4:
					checkStudentInfo();
					break;
				case 5:
					printAllStudent();
					studentController.doCheckAllStudent();
					break;
				case 6:
					return;
				default:
					invalidInput();
					break;
			}
		}
	}

	public void addStudentInfo() {
		Student student = getStudent();
		studentController.doAddStudent(student);
	}

	public void modifyStudentInfo() {
		Student student = getStudent();
		studentController.doModifyStudent(student);
	}

	public void deleteStudentInfo() {
		System.out.print("ID를 입력해주십시오 : ");
		int studentId = IOUtil.getIntId();
		studentController.doDeleteStudent(studentId);
	}

	public void checkStudentInfo() {
		System.out.print("ID를 입력해주십시오 : ");
		int studentId = IOUtil.getIntId();
		Student student = SchoolPerson.studentMap.get(studentId);
		if (student != null) {
			printStudentInfo(student);
		}
		studentController.doCheckStudent(studentId);
	}

	public int getChoiceValue() {
		studentMenuBar();
		int choice = IOUtil.getInt();
		return choice;
	}

	public Student getStudent() {
		Student newStudent = new Student();
		System.out.print("ID를 입력해주십시오(0~9999) : ");
		int studentId = IOUtil.getIntId();
		System.out.print("Name를 입력해주십시오 : ");
		String studentName = IOUtil.getStringName();
		System.out.print("BirthDate를 입력해주십시오 (ex>19900302): ");
		int studentBirth = IOUtil.getIntBirthDate();

		newStudent.setId(studentId);
		newStudent.setName(studentName);
		newStudent.setBirthDate(studentBirth);
		return newStudent;
	}

	public void printAllStudent() {
		viewPersonTitle();
		for (Integer studentId : SchoolPerson.studentMap.keySet()) {
			Student student = SchoolPerson.studentMap.get(studentId);
			String formatName = student.getName();
			int nameLen = student.getName().length();
			if (nameLen > Constant.MAX_NAME_LENGTH) {
				formatName = student.getName().substring(0, Constant.MAX_NAME_LENGTH);
			}
			System.out.printf(" %04d %5s   %8d\n", student.getId(), formatName, student.getBirthDate());
		}
	}

	public void printStudentInfo(Student student) {
		viewPersonTitle();
		String formatName = student.getName();
		int nameLen = student.getName().length();
		if (nameLen > Constant.MAX_NAME_LENGTH) {
			formatName = student.getName().substring(0, Constant.MAX_NAME_LENGTH);
		}
		System.out.printf(" %04d %5s   %8d\n", student.getId(), formatName, student.getBirthDate());
	}
}
