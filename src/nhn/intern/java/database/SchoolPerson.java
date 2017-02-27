package nhn.intern.java.database;

import java.util.HashMap;

import nhn.intern.java.model.person.Staff;
import nhn.intern.java.model.person.Student;
import nhn.intern.java.model.person.Teacher;

public class SchoolPerson {
	public static HashMap<Integer, Student> studentMap = new HashMap<Integer, Student>();
	public static HashMap<Integer, Staff> staffMap = new HashMap<Integer, Staff>();
	public static HashMap<Integer, Teacher> teacherMap = new HashMap<Integer, Teacher>();
}
