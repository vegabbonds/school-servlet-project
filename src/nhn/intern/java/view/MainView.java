package nhn.intern.java.view;

import nhn.intern.java.util.IOUtil;

public class MainView extends StandardView {

	static HumanView humanView = new HumanView();
	static SubjectView SubjectView = new SubjectView();
	static ScoreView ScoreView = new ScoreView();

	public void mainMenuBar() {
		System.out.println("---------학교관리시스템----------");
		System.out.println("1. 인원관리");
		System.out.println("2. 과목관리");
		System.out.println("3. 성적관리");
		System.out.println("4. 종료");
		System.out.print("항목을 선택해 주십시오 : ");
	}

	public void inputMenu() {

		while (true) {
			mainMenuBar();
			int choice = IOUtil.getInt();

			switch (choice) {
				case 1:
					humanView.inputMenu();
					break;
				case 2:
					SubjectView.inputMenu();
					break;
				case 3:
					ScoreView.inputMenu();
					break;
				case 4:
					return;
				default:
					invalidInput();
					break;
			}
		}
	}
}
