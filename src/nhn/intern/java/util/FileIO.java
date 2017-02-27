package nhn.intern.java.util;

import nhn.intern.java.dao.StaffDao;
import nhn.intern.java.dao.StudentDao;
import nhn.intern.java.dao.StudentScoreDao;
import nhn.intern.java.dao.SubjectDao;
import nhn.intern.java.dao.TeacherDao;

public class FileIO {
	public static void readAllFile() {
		System.out.println("------------기존 data를 불러들이고 있습니다.-------------");
		StudentDao.readStudentMap();
		StaffDao.readStaffMap();
		SubjectDao.readSubjectMap();
		TeacherDao.readTeacherMap();
		StudentScoreDao.readScoreMap();
	}
}
