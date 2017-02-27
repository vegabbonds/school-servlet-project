package nhn.intern.java.view;

import nhn.intern.java.util.IOUtil;

public class HumanView {

	private StudentView studentView = new StudentView();
	private StaffView staffView = new StaffView();
	private TeacherView teacherView = new TeacherView();

	public void humanSystemMenuBar() {
		System.out.println("---------인원관리----------");
		System.out.println("1. 교직원");
		System.out.println("2. 선생님");
		System.out.println("3. 학생");
		System.out.println("4. 상위메뉴");
		System.out.print("항목을 선택해 주십시오 : ");
	}

	public void inputMenu() {

		while (true) {
			int choice = getChoiceValue();
			switch (choice) {
				case 1:
					staffView.inputMenu();
					break;
				case 2:
					teacherView.inputMenu();
					break;
				case 3:
					studentView.inputMenu();
					break;
				case 4:
					return;
				default:
					invalidInput();
					break;
			}
		}
	}

	public int getChoiceValue() {
		humanSystemMenuBar();
		int choice = IOUtil.getInt();
		return choice;
	}

	public void invalidInput() {
		System.out.println("잘못된 input");
	}
}
