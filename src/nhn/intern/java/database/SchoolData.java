package nhn.intern.java.database;

import java.util.HashMap;

import nhn.intern.java.model.object.Subject;

/**
 * database역할.
 * 모든 data를 저장해 놓은 클래스.
 * @author 이정석
 */
public class SchoolData {
	public static HashMap<Integer, Subject> subjectMap = new HashMap<Integer, Subject>();
	public static HashMap<Integer, HashMap<Integer, Integer>> studentScoreMap = new HashMap<Integer, HashMap<Integer, Integer>>();
}
