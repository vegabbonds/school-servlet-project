package nhn.intern.java.view;

import java.util.HashMap;

import nhn.intern.java.controller.ScoreController;
import nhn.intern.java.database.SchoolData;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.model.object.Score;
import nhn.intern.java.util.IOUtil;

/**
 * 성적관리 관련 View 클래스
 * @author 이정석
 */
public class ScoreView extends StandardView {

	ScoreController scoreController = new ScoreController();

	public void scoreMenuBar() {
		System.out.println("---------성적관리----------");
		System.out.println("1. 입력");
		System.out.println("2. 수정");
		System.out.println("3. 삭제");
		System.out.println("4. 전체조회");
		System.out.println("5. 학생별조회");
		System.out.println("6. 전체평균");
		System.out.println("7. 학생평균");
		System.out.println("8. 과목평균");
		System.out.println("9. 상위메뉴");
		System.out.print("항목을 선택해 주십시오 : ");
	}

	public void inputMenu() {

		while (true) {
			int choice = getChoiceValue();
			switch (choice) {
				case 1:
					addScoreInfo();
					break;
				case 2:
					modifyScoreInfo();
					break;
				case 3:
					deleteScoreInfo();
					break;
				case 4:
					printAllScore();
					break;
				case 5:
					checkScoreInfo();
					break;
				case 6:
					checkAllAvgScore();
					break;
				case 7:
					checkAvgScore();
					break;
				case 8:
					checkSubjectAvg();
					break;
				case 9:
					return;
				default:
					invalidInput();
					break;
			}
		}
	}

	public void addScoreInfo() {
		Score score = getScore();
		scoreController.doAddScore(score);
	}

	public void modifyScoreInfo() {
		Score score = getScore();
		scoreController.doModifyScore(score);
	}

	public void deleteScoreInfo() {
		Score score = new Score();
		System.out.print("학생 Id를 입력하십시오(0~9999) : ");
		int studentId = IOUtil.getIntId();
		System.out.print("과목 Id를 입력하십시오 : ");
		int subjectId = IOUtil.getIntId();

		score.setStudentId(studentId);
		score.setSubjectId(subjectId);
		score.setSubjectScore(0);
		scoreController.doDeleteScore(score);
	}

	public int getChoiceValue() {
		scoreMenuBar();
		int choice = IOUtil.getInt();
		return choice;
	}

	public void checkScoreInfo() {
		System.out.print("학생 Id를 입력하십시오(0~9999) : ");
		int checkStudentId = IOUtil.getIntId();
		printScore(checkStudentId);
	}

	public void printScore(int checkStudentId) {
		viewScoreTitle();
		for (Integer studentId : SchoolData.studentScoreMap.keySet()) {
			if (checkStudentId == studentId) {
				HashMap<Integer, Integer> student = SchoolData.studentScoreMap.get(studentId);
				for (Integer subjectId : student.keySet()) {
					int score = student.get(subjectId);
					System.out.printf("   %04d    %04d   %3d\n", studentId, subjectId,
						score);
				}
				return;
			}
		}
		System.out.println("해당 학생은 수강한 과목이 없습니다.");
	}

	public void checkAvgScore() {
		if (SchoolData.studentScoreMap.isEmpty()) {
			printDataEmpty();
			return;
		}
		int checkStudentId = checkStudentId();
		if (checkStudentId == -1) {
			printIdNotExist();
			return;
		}
		double scoreSum = 0;
		for (Integer studentId : SchoolData.studentScoreMap.keySet()) {
			if (checkStudentId == studentId) {
				HashMap<Integer, Integer> student = SchoolData.studentScoreMap.get(studentId);
				int subjectCount = student.size();
				for (Integer subjectId : student.keySet()) {
					int score = student.get(subjectId);
					scoreSum += score;
				}
				viewAvgScore(studentId, subjectCount, scoreSum);
			}
		}
	}

	/**
	 * 전체 평균을 구하는 메소드.
	 * 각 학생들의 평균에 대한 평균을 구하였다.
	 */
	public void checkAllAvgScore() {
		if (SchoolData.studentScoreMap.isEmpty()) {
			printDataEmpty();
			return;
		}
		double scoreSum = 0;
		double eachAvg = 0;
		double avgSum = 0;
		int totalStudentCount = 0;
		for (Integer studentId : SchoolData.studentScoreMap.keySet()) {
			HashMap<Integer, Integer> student = SchoolData.studentScoreMap.get(studentId);
			int subjectCount = student.size();
			if (subjectCount == 0) {
				continue;
			}
			eachAvg = 0;
			scoreSum = 0;
			totalStudentCount++;
			for (Integer subjectId : student.keySet()) {
				int score = student.get(subjectId);
				scoreSum += score;
			}
			eachAvg = scoreSum / subjectCount;
			avgSum += eachAvg;
		}
		viewAllAvgScore(totalStudentCount, avgSum);
	}

	/**
	 * 특정 과목에 대한 평군을 구하는 메소드.
	 */
	public void checkSubjectAvg() {
		System.out.print("과목 Id를 입력하십시오 : ");
		int checkSubjectId = IOUtil.getIntId();
		double avg = scoreController.doCheckSubjectAvg(checkSubjectId);
		if (avg > 0) {
			String avgFormat = String.format("%.2f", avg);
			System.out.println(
				"과목ID : " + checkSubjectId + ", 과목이름 : " + SchoolData.subjectMap.get(checkSubjectId).getSubjectName()
					+ ", 과목평균 : " + avgFormat);
		}
	}

	public Score getScore() {
		Score newScore = new Score();
		System.out.print("학생 Id를 입력하십시오(0~9999) : ");
		int studentId = IOUtil.getIntId();
		System.out.print("과목 Id를 입력하십시오 : ");
		int subjectId = IOUtil.getIntId();
		System.out.print("점수를 입력하십시오 : ");
		int score = IOUtil.getIntScore();

		newScore.setStudentId(studentId);
		newScore.setSubjectId(subjectId);
		newScore.setSubjectScore(score);
		return newScore;
	}

	@Override
	public void printSubjectIdNotExist() {
		System.out.println("해당 ID를 가진 과목이 없습니다.");
	}

	public void printSubjectIdExist() {
		System.out.println("해당 ID를 가진 과목이 이미 있습니다.");
	}

	public void PrintStudentHaveSubjectScore() {
		System.out.println("해당 학생은 이미 해당 과목 점수를 가지고 있습니다.");
	}

	public void printStudentNotHaveSubjectId() {
		System.out.println("해당 학생은 해당 과목을 듣지 않았습니다.");
	}

	public void printDataEmpty() {
		System.out.println("아직 성적 data가 없습니다.");
	}

	public void viewScoreTitle() {
		System.out.println("|NAMEID|SUBJECT|SCORE|");
	}

	public void printAllScore() {
		if (SchoolData.studentScoreMap.isEmpty()) {
			printDataEmpty();
			return;
		}
		viewScoreTitle();
		for (Integer studentId : SchoolData.studentScoreMap.keySet()) {
			HashMap<Integer, Integer> student = SchoolData.studentScoreMap.get(studentId);
			for (Integer subjectId : student.keySet()) {
				int score = student.get(subjectId);
				System.out.printf("   %04d    %04d   %3d\n", studentId, subjectId, score);
			}
		}
	}

	public void viewInfo(Score score) {
		viewScoreTitle();
		System.out.printf("   %04d    %04d   %3d\n", score.getStudentId(), score.getSubjectId(),
			score.getSubjectScore());
	}

	public void viewAvgScore(int studentId, int subjectCount, double scoreSum) {
		if (subjectCount == 0) {
			System.out.println("해당 학생은 듣고 있는 과목이 없습니다.");
			return;
		}
		double avg = scoreSum / subjectCount;
		System.out.println(studentId + "번 id 학생의 평균 : " + avg);
	}

	public void viewAllAvgScore(int totalStudentCount, double avgSum) {
		if (totalStudentCount != 0) {
			double totalAvg = avgSum / totalStudentCount;
			System.out.println("전체 평균 : " + totalAvg);
		} else {
			System.out.println("아직 전체 평균을 구할 수 없습니다.");
		}
	}

	public int checkStudentId() {
		int studentId;
		System.out.print("ID를 입력해주십시오(0~9999) : ");
		studentId = IOUtil.getIntId();
		if (SchoolPerson.studentMap.containsKey(studentId)) {
			return studentId;
		}
		return -1;
	}
}
