package nhn.intern.java.service;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.StaffController;
import nhn.intern.java.dao.StaffDao;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.model.person.Staff;

public class StaffService {
	private static StaffController staffController = new StaffController();

	public void addStaff(Staff staff) {
		if (SchoolPerson.staffMap.size() >= Constant.MAX_PERSON) {
			staffController.doPrintFullPeople();
			return;
		}
		int staffId = staff.getId();
		if (!SchoolPerson.staffMap.containsKey(staffId)) {
			StaffDao.addStaff(staff);
			return;
		} else {
			staffController.doPrintIdExist();
			return;
		}
	}

	public void modifyStaff(Staff staff) {
		int staffId = staff.getId();
		if (!SchoolPerson.staffMap.containsKey(staffId)) {
			staffController.doPrintIdNotExist();
		} else {
			StaffDao.modifyStaff(staff);
		}
	}

	public boolean deleteStaff(int staffId) {
		if (SchoolPerson.staffMap.containsKey(staffId)) {
			StaffDao.deleteStaff(staffId);
			return true;
		} else {
			return false;
		}
	}

	public boolean checkStaff(int staffId) {
		if (SchoolPerson.staffMap.containsKey(staffId)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAllStaff() {
		if (SchoolPerson.staffMap.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
}
