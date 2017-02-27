package nhn.intern.java.model.object;

public class Subject {
	private String subjectName;
	private int subjectId;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getFormatString() {
		String stringFormat = String.format("%d %s", this.subjectId, this.subjectName);
		return stringFormat;
	}
}
