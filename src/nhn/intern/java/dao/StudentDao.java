package nhn.intern.java.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.StudentController;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.exception.ExceptionHandling;
import nhn.intern.java.model.person.Student;

public class StudentDao {

	public static void readStudentMap() {
		String fileName = Constant.STUDENT_FILENAME + ".txt";
		try {
			StudentController studentController = new StudentController();
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			System.out.println(Constant.STUDENT_FILENAME + " data를 성공적으로 불러들였습니다.");
			String readLine;
			while ((readLine = in.readLine()) != null) {
				Student student = new Student();
				String readData[] = readLine.split(" ");
				if (readData.length != Constant.STUDENT_FILE_FORMAT_LENGTH) {
					System.out.println("잘못된 input정보 입니다. 이 data는 저장되지 않습니다.");
					System.out.println("wrong data : " + readLine);
					continue;
				}

				student.setId(Integer.parseInt(readData[0]));
				student.setName(readData[1]);
				student.setBirthDate(Integer.parseInt(readData[2]));
				studentController.doAddStudent(student);
			}
			in.close();
		} catch (IOException e) {
			ExceptionHandling.getInstance().fileReadExceptionHandling(fileName);
		}
	}

	public static void writeStudentMap() {
		try {
			String fileName = "." + File.separator + Constant.STUDENT_FILENAME + ".txt";
			File file = new File(fileName);
			FileWriter fileWrite = new FileWriter(file, false);

			for (Integer studentId : SchoolPerson.studentMap.keySet()) {
				Student student = SchoolPerson.studentMap.get(studentId);
				String txt = student.getFormatString();
				txt += "\r\n";
				fileWrite.write(txt);
			}
			fileWrite.flush();
			fileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addStudent(Student student) {
		SchoolPerson.studentMap.put(student.getId(), student);
		writeStudentMap();
	}

	public static void modifyStudent(Student student) {
		SchoolPerson.studentMap.put(student.getId(), student);
		writeStudentMap();
	}

	public static void deleteStudent(int studentId) {
		SchoolPerson.studentMap.remove(studentId);
		writeStudentMap();
	}
}
