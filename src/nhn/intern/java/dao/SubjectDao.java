package nhn.intern.java.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.SubjectController;
import nhn.intern.java.database.SchoolData;
import nhn.intern.java.exception.ExceptionHandling;
import nhn.intern.java.model.object.Subject;

public class SubjectDao {
	public static void readSubjectMap() {
		String fileName = Constant.SUBJECT_FILENAME + ".txt";
		try {
			SubjectController subjectController = new SubjectController();
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			System.out.println(Constant.SUBJECT_FILENAME + " data를 성공적으로 불러들였습니다.");
			String readLine;
			while ((readLine = in.readLine()) != null) {
				Subject subject = new Subject();
				String readData[] = readLine.split(" ");
				if (readData.length != Constant.SUBJECT_FILE_FORMAT_LENGTH) {
					System.out.println("잘못된 input정보 입니다. 이 data는 저장되지 않습니다.");
					System.out.println("wrong data : " + readLine);
					continue;
				}

				subject.setSubjectId(Integer.parseInt(readData[0]));
				subject.setSubjectName(readData[1]);
				subjectController.doAddSubject(subject);
			}
			in.close();
		} catch (IOException e) {
			ExceptionHandling.getInstance().fileReadExceptionHandling(fileName);
		}
	}

	public static void writeSubjectMap() {
		try {
			String fileName = "." + File.separator + Constant.SUBJECT_FILENAME + ".txt";
			File file = new File(fileName);
			FileWriter fileWrite = new FileWriter(file, false);

			for (Integer subjectId : SchoolData.subjectMap.keySet()) {
				Subject subject = SchoolData.subjectMap.get(subjectId);
				String txt = subject.getFormatString();
				txt += "\r\n";
				fileWrite.write(txt);
			}
			fileWrite.flush();
			fileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addSubject(Subject subject) {
		SchoolData.subjectMap.put(subject.getSubjectId(), subject);
		writeSubjectMap();
	}

	public static void modifySubject(Subject subject) {
		SchoolData.subjectMap.put(subject.getSubjectId(), subject);
		writeSubjectMap();
	}

	public static void deleteSubject(int subjectId) {
		SchoolData.subjectMap.remove(subjectId);
		writeSubjectMap();
	}
}
