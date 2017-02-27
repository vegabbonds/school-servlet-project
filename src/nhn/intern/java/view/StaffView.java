package nhn.intern.java.view;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.StaffController;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.model.person.Staff;
import nhn.intern.java.util.IOUtil;

public class StaffView extends StandardView {

	static StaffController staffController = new StaffController();

	public void staffMenuBar() {
		System.out.println("---------교직원관리----------");
		System.out.println("1. 입력");
		System.out.println("2. 수정");
		System.out.println("3. 삭제");
		System.out.println("4. 조회");
		System.out.println("5. 전체조회");
		System.out.println("6. 상위메뉴");
		System.out.print("항목을 선택해 주십시오 : ");
	}

	public void inputMenu() {
		while (true) {
			int choice = getChoiceValue();
			switch (choice) {
				case 1:
					addStaffInfo();
					break;
				case 2:
					modifyStaffInfo();
					break;
				case 3:
					deleteStaffInfo();
					break;
				case 4:
					checkStaffInfo();
					break;
				case 5:
					printAllStaff();
					staffController.doCheckAllStaff();
					break;
				case 6:
					return;
				default:
					invalidInput();
					break;
			}
		}
	}

	public void addStaffInfo() {
		Staff staff = getStaff();
		staffController.doAddStaff(staff);
	}

	public void modifyStaffInfo() {
		Staff staff = getStaff();
		staffController.doModifyStaff(staff);
	}

	public void deleteStaffInfo() {
		System.out.print("ID를 입력해주십시오 : ");
		int staffId = IOUtil.getIntId();
		staffController.doDeleteStaff(staffId);
	}

	public void checkStaffInfo() {
		System.out.print("ID를 입력해주십시오 : ");
		int staffId = IOUtil.getIntId();
		Staff staff = SchoolPerson.staffMap.get(staffId);
		if (staff != null) {
			printStaffInfo(staff);
		}
		staffController.doCheckStaff(staffId);
	}

	public int getChoiceValue() {
		staffMenuBar();
		int choice = IOUtil.getInt();
		return choice;
	}

	public Staff getStaff() {
		Staff newStaff = new Staff();
		System.out.print("ID를 입력해주십시오(0~9999) : ");
		int staffId = IOUtil.getIntId();
		System.out.print("Name를 입력해주십시오 : ");
		String staffName = IOUtil.getStringName();
		System.out.print("BirthDate를 입력해주십시오 (ex>19900302): ");
		int staffBirth = IOUtil.getIntBirthDate();

		newStaff.setId(staffId);
		newStaff.setName(staffName);
		newStaff.setBirthDate(staffBirth);
		return newStaff;
	}

	public void printAllStaff() {
		viewPersonTitle();
		for (Integer staffId : SchoolPerson.staffMap.keySet()) {
			Staff staff = SchoolPerson.staffMap.get(staffId);
			String formatName = staff.getName();
			int nameLen = staff.getName().length();
			if (nameLen > Constant.MAX_NAME_LENGTH) {
				formatName = staff.getName().substring(0, Constant.MAX_NAME_LENGTH);
			}
			System.out.printf(" %04d %5s   %8d\n", staff.getId(), formatName, staff.getBirthDate());
		}
	}

	public void printStaffInfo(Staff staff) {
		viewPersonTitle();
		String formatName = staff.getName();
		int nameLen = staff.getName().length();
		if (nameLen > Constant.MAX_NAME_LENGTH) {
			formatName = staff.getName().substring(0, Constant.MAX_NAME_LENGTH);
		}
		System.out.printf(" %04d %5s   %8d\n", staff.getId(), formatName, staff.getBirthDate());
	}
}
