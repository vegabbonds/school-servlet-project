package nhn.intern.java.service;

import java.util.HashMap;

import nhn.intern.java.controller.ScoreController;
import nhn.intern.java.dao.StudentScoreDao;
import nhn.intern.java.database.SchoolData;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.model.object.Score;

/**
 * Controller를 통해서 적절한 서비스를 실행.
 * 실제 logic을 처리하는 메소드를 구현해 놓았다.
 * @author 이정석
 */
public class ScoreService {
	private static ScoreController scoreController = new ScoreController();

	public void addNewStudent(int sameStudentId) {
		if (!SchoolData.studentScoreMap.containsKey(sameStudentId)) {
			SchoolData.studentScoreMap.put(sameStudentId, new HashMap<Integer, Integer>());
		}
	}

	public void addScore(Score score) {

		int studentId = score.getStudentId();
		int subjectId = score.getSubjectId();
		if (!SchoolPerson.studentMap.containsKey(studentId)) {
			scoreController.doPrintIdNotExist();
			return;
		}
		if (!SchoolData.subjectMap.containsKey(subjectId)) {
			scoreController.doPrintSubjectIdNotExist();
			return;
		}

		addNewStudent(studentId);
		HashMap<Integer, Integer> student = SchoolData.studentScoreMap.get(studentId);
		if (student.containsKey(subjectId)) {
			scoreController.doPrintStudentHaveSubjectScore();
			return;
		}
		StudentScoreDao.addScore(student, score);
	}

	/**
	 * 성적을 수정하는 메소드.
	 * 올바른 studentId, sujbectId를 입력받았을 경우에,
	 * 점수를 수정할 수 있다.
	 * @param score
	 */
	public void modifyScore(Score score) {

		int studentId = score.getStudentId();
		int subjectId = score.getSubjectId();
		if (!SchoolPerson.studentMap.containsKey(studentId)) {
			scoreController.doPrintIdNotExist();
			return;
		}
		if (!SchoolData.subjectMap.containsKey(subjectId)) {
			scoreController.doPrintSubjectIdNotExist();
			return;
		}

		HashMap<Integer, Integer> student = SchoolData.studentScoreMap.get(studentId);
		if (student.containsKey(subjectId)) {
			StudentScoreDao.modifyScore(student, score);
		} else {
			scoreController.doPrintStudentNotHaveSubjectScore();
		}
	}

	/**
	 * 성적을 제거하는 메소드.
	 * 특정 학생에 대해서 특정 과목 
	 * 하나를 제거할 수 있다.
	 * @param score
	 */
	public void deleteScore(Score score) {

		int studentId = score.getStudentId();
		int subjectId = score.getSubjectId();
		if (!SchoolPerson.studentMap.containsKey(studentId)) {
			scoreController.doPrintIdNotExist();
			return;
		}
		if (!SchoolData.subjectMap.containsKey(subjectId)) {
			scoreController.doPrintSubjectIdNotExist();
			return;
		}

		HashMap<Integer, Integer> student = SchoolData.studentScoreMap.get(studentId);
		if (student.containsKey(subjectId)) {
			StudentScoreDao.deleteScore(student, subjectId);
			if (student.size() == 0) {
				SchoolData.studentScoreMap.remove(studentId);
			}
		} else {
			scoreController.doPrintStudentNotHaveSubjectScore();
		}
	}

	public double checkSubjectAvg(int checkSubjectId) {
		if (SchoolData.studentScoreMap.isEmpty()) {
			return -1;
		}
		if (!SchoolData.subjectMap.containsKey(checkSubjectId)) {
			return -2;
		}

		double scoreSum = 0;
		int studentCount = 0;
		for (Integer studentId : SchoolData.studentScoreMap.keySet()) {
			HashMap<Integer, Integer> student = SchoolData.studentScoreMap.get(studentId);
			for (Integer subjectId : student.keySet()) {
				if (subjectId == checkSubjectId) {
					int score = student.get(subjectId);
					scoreSum += score;
					studentCount++;
				}
			}
		}
		if (studentCount != 0) {
			return scoreSum / studentCount;
		} else {
			return -1;
		}
	}
}
