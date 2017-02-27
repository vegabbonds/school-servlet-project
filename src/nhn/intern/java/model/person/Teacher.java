package nhn.intern.java.model.person;

public class Teacher extends Person {
	private int subjectId;

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public String getFormatString() {
		String stringFormat = String.format("%d %s %d %d", this.id, this.name, this.birthDate, this.subjectId);
		return stringFormat;
	}
}
