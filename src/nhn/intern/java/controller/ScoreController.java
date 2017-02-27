package nhn.intern.java.controller;

import nhn.intern.java.model.object.Score;
import nhn.intern.java.service.ScoreService;
import nhn.intern.java.view.ScoreView;

/**
 * 사용자가 View에서 원하는 메뉴를 선택하여
 * Service를 실행하고자 할 경우에, Controller를 통해서 하게 하였습니다.
 * Controller는 Service실행 결과를 토대로 적절한 View를 호출하게 하였습니다.
 * @author 이정석
 */
public class ScoreController {

	static ScoreView scoreView = new ScoreView();
	static ScoreService scoreService = new ScoreService();

	public void doAddScore(Score score) {
		scoreService.addScore(score);
	}

	public void doModifyScore(Score score) {
		scoreService.modifyScore(score);
	}

	public void doDeleteScore(Score score) {
		scoreService.deleteScore(score);
	}

	/**
	 * 특정 과목의 평균을 구하는 service를 호출하였고,
	 * service값에 따라서 적절한 view를 호출하도록
	 * control하는 메소드.
	 * @param checkSubjectId
	 * @return
	 */
	public double doCheckSubjectAvg(int checkSubjectId) {
		double avg = scoreService.checkSubjectAvg(checkSubjectId);
		if (avg == -1) {
			scoreView.printDataEmpty();
		} else if (avg == -2) {
			scoreView.printSubjectIdNotExist();
		}
		return avg;
	}

	public void doPrintIdNotExist() {
		scoreView.printIdNotExist();
	}

	public void doPrintSubjectIdNotExist() {
		scoreView.printSubjectIdNotExist();
	}

	public void doPrintStudentHaveSubjectScore() {
		scoreView.PrintStudentHaveSubjectScore();
	}

	public void doPrintStudentNotHaveSubjectScore() {
		scoreView.printStudentNotHaveSubjectId();
	}
}
