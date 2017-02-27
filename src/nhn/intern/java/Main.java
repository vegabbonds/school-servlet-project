package nhn.intern.java;

import nhn.intern.java.util.FileIO;
import nhn.intern.java.view.MainView;

/**
 * 프로그램을 시작해서 MainView를 호출한다.
 * 시작시에 이전에 기록한 data를 불러들인다.
 * @author 이정석
 */
public class Main {
	public static void main(String[] args) {
		FileIO.readAllFile();
		MainView mainView = new MainView();
		mainView.inputMenu();
	}
}
