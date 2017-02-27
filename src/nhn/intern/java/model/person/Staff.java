package nhn.intern.java.model.person;

public class Staff extends Person {

	public String getFormatString() {
		String stringFormat = String.format("%d %s %d", this.id, this.name, this.birthDate);
		return stringFormat;
	}
}
