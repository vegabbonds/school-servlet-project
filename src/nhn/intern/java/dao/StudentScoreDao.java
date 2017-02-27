package nhn.intern.java.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.ScoreController;
import nhn.intern.java.database.SchoolData;
import nhn.intern.java.exception.ExceptionHandling;
import nhn.intern.java.model.object.Score;

public class StudentScoreDao {
	public static void readScoreMap() {
		String fileName = Constant.STUDENTSCORE_FILENAME + ".txt";
		try {
			ScoreController scoreController = new ScoreController();
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			System.out.println(Constant.STUDENTSCORE_FILENAME + " data를 성공적으로 불러들였습니다.");
			String readLine;
			while ((readLine = in.readLine()) != null) {
				Score score = new Score();
				String readData[] = readLine.split(" ");
				if (readData.length != Constant.STUDENTSCORE_FILE_FORMAT_LENGTH) {
					System.out.println("잘못된 input정보 입니다. 이 data는 저장되지 않습니다.");
					System.out.println("wrong data : " + readLine);
					continue;
				}

				score.setStudentId(Integer.parseInt(readData[0]));
				score.setSubjectId(Integer.parseInt(readData[1]));
				score.setSubjectScore(Integer.parseInt(readData[2]));
				scoreController.doAddScore(score);
			}
			in.close();
		} catch (IOException e) {
			ExceptionHandling.getInstance().fileReadExceptionHandling(fileName);
		}
	}

	public static void writeStudentScoreMap() {
		try {
			String fileName = "." + File.separator + Constant.STUDENTSCORE_FILENAME + ".txt";
			File file = new File(fileName);
			FileWriter fileWrite = new FileWriter(file, false);

			for (Integer studentId : SchoolData.studentScoreMap.keySet()) {
				HashMap<Integer, Integer> student = SchoolData.studentScoreMap.get(studentId);
				for (Integer scoreId : student.keySet()) {
					int score = student.get(scoreId);
					String txt = String.format("%d %d %d\r\n", studentId, scoreId, score);
					fileWrite.write(txt);
				}
			}
			fileWrite.flush();
			fileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addScore(HashMap<Integer, Integer> student, Score score) {
		student.put(score.getSubjectId(), score.getSubjectScore());
		writeStudentScoreMap();
	}

	public static void modifyScore(HashMap<Integer, Integer> student, Score score) {
		student.put(score.getSubjectId(), score.getSubjectScore());
		writeStudentScoreMap();
	}

	public static void deleteScore(HashMap<Integer, Integer> student, int subjectId) {
		student.remove(subjectId);
		writeStudentScoreMap();
	}
}
