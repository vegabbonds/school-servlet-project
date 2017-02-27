package nhn.intern.java.view;

public class StandardView {
	public void invalidInput() {
		System.out.println("잘못된 input");
	}

	public void printFullPeople() {
		System.out.println("인원이 가득 찼습니다.");
	}

	public void printIdNotExist() {
		System.out.println("해당 ID를 가진 사람이 없습니다.");
	}

	public void printIdExist() {
		System.out.println("해당 ID를 가진 사람이 이미 있습니다.");
	}

	public void printSubjectIdNotExist() {
		System.out.println("해당 ID를 가진 과목이 없습니다");
	}

	public void printPeopleEmpty() {
		System.out.println("아직 사람이 없습니다.");
	}

	public void viewPersonTitle() {
		System.out.println("| ID | NAME | BIRTHDATE |");
	}
}
