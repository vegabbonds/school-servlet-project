package nhn.intern.java.exception;

import nhn.intern.java.util.IOUtil;

/**
 * exception발생 시 해결하는 class.
 * @author 이정석
 */
public class ExceptionHandling {

	private ExceptionHandling() {}

	private static class Singleton {
		private static final ExceptionHandling instance = new ExceptionHandling();
	}

	public static ExceptionHandling getInstance() {
		return Singleton.instance;
	}

	public void inputMismatchExceptionHandling() {
		System.out.print("잘못된 int값을 입력하셨습니다. 다시 입력해 주십시오 : ");
		IOUtil.scan.nextLine();
	}

	public void fileReadExceptionHandling(String fileName) {
		System.out.println(fileName + "는 존재하지 않습니다.");
	}
}
