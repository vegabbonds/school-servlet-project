package nhn.intern.java.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.TeacherController;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.exception.ExceptionHandling;
import nhn.intern.java.model.person.Teacher;

public class TeacherDao {
	public static void readTeacherMap() {
		String fileName = Constant.TEACHER_FILENAME + ".txt";
		try {
			TeacherController teacherController = new TeacherController();
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			System.out.println(Constant.TEACHER_FILENAME + " data를 성공적으로 불러들였습니다.");
			String readLine;
			while ((readLine = in.readLine()) != null) {
				Teacher teacher = new Teacher();
				String readData[] = readLine.split(" ");
				if (readData.length != Constant.TEACHER_FILE_FORMAT_LENGTH) {
					System.out.println("잘못된 input정보 입니다. 이 data는 저장되지 않습니다.");
					System.out.println("wrong data : " + readLine);
					continue;
				}

				teacher.setId(Integer.parseInt(readData[0]));
				teacher.setName(readData[1]);
				teacher.setBirthDate(Integer.parseInt(readData[2]));
				teacher.setSubjectId(Integer.parseInt(readData[3]));
				teacherController.doAddTeacher(teacher);
			}
			in.close();
		} catch (IOException e) {
			ExceptionHandling.getInstance().fileReadExceptionHandling(fileName);
		}
	}

	public static void writeTeacherMap() {
		try {
			String fileName = "." + File.separator + Constant.TEACHER_FILENAME + ".txt";
			File file = new File(fileName);
			FileWriter fileWrite = new FileWriter(file, false);

			for (Integer teacherId : SchoolPerson.teacherMap.keySet()) {
				Teacher teacher = SchoolPerson.teacherMap.get(teacherId);
				String txt = teacher.getFormatString();
				txt += "\r\n";
				fileWrite.write(txt);
			}
			fileWrite.flush();
			fileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addTeacher(Teacher teacher) {
		SchoolPerson.teacherMap.put(teacher.getId(), teacher);
		writeTeacherMap();
	}

	public static void modifyTeacher(Teacher teacher) {
		SchoolPerson.teacherMap.put(teacher.getId(), teacher);
		writeTeacherMap();
	}

	public static void deleteTeacher(int teacherId) {
		SchoolPerson.teacherMap.remove(teacherId);
		writeTeacherMap();
	}
}
