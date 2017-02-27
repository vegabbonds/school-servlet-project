package nhn.intern.java.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.exception.ExceptionHandling;

/**
 * scanner를 통해 바로 input을 받아서 사용하는 것이 아니라,
 * 각 case마다 필요한 input메소드를 정의하고, input형식에 맞지 않을 경우
 * exception을 발생시켜 올바른 input을 받게 하였다.
 * @author 이정석
 */
public class IOUtil {
	public static Scanner scan = new Scanner(System.in);

	public static int getInt() {
		while (true) {
			try {
				int n = scan.nextInt();
				return n;
			} catch (InputMismatchException e) {
				ExceptionHandling.getInstance().inputMismatchExceptionHandling();
			}
		}
	}

	public static int getIntId() {
		while (true) {
			try {
				int n = scan.nextInt();
				if (n < Constant.MIN_ID || n > Constant.MAX_ID) {
					System.out.println("ID의 범위를 벗어 났습니다.");
					continue;
				}
				return n;
			} catch (InputMismatchException e) {
				ExceptionHandling.getInstance().inputMismatchExceptionHandling();
			}
		}
	}

	public static int getIntScore() {
		while (true) {
			try {
				int n = scan.nextInt();
				if (n < Constant.MIN_SCORE || n > Constant.MAX_SCORE) {
					System.out.println("ID의 범위를 벗어 났습니다.");
					continue;
				}
				return n;
			} catch (InputMismatchException e) {
				ExceptionHandling.getInstance().inputMismatchExceptionHandling();
			}
		}
	}

	public static int getIntBirthDate() {
		while (true) {
			try {
				int n = scan.nextInt();
				if (n < Constant.MIN_BIRTHDATE || n > Constant.MAX_BIRTHDATE) {
					System.out.print("BIRTHDATE의 범위를 벗어 났습니다. 다시 입력해 주십시오 : ");
					continue;
				}
				return n;
			} catch (InputMismatchException e) {
				ExceptionHandling.getInstance().inputMismatchExceptionHandling();
			}
		}
	}

	public static String getStringName() {
		while (true) {
			String string = scan.next();
			if (string.contains(" ")) {
				System.out.println("이름에 공백은 출력할 수 없습니다.");
				continue;
			}
			if (string.length() > Constant.MAX_NAME_LENGTH) {
				System.out.println("이름이 6자 이상은 잘려서 출력될 수 있습니다.");
			}
			return string;
		}
	}

	public static String getStringSubjectName() {
		while (true) {
			String string = scan.next();
			if (string.contains(" ")) {
				System.out.println("이름에 공백은 출력할 수 없습니다.");
				continue;
			}
			if (string.length() >= Constant.MAX_SUBJECT_LENGTH) {
				System.out.println("이름이 " + Constant.MAX_SUBJECT_LENGTH + "를 넘으면 잘려서 출력될 수 있습니다.");
			}
			return string;
		}
	}
}
