package nhn.intern.java.controller;

import nhn.intern.java.model.person.Staff;
import nhn.intern.java.service.StaffService;
import nhn.intern.java.view.StaffView;

/**
 * 사용자가 View에서 원하는 메뉴를 선택하여
 * Service를 실행하고자 할 경우에, Controller를 통해 제어.
 * Controller는 Service실행 결과를 토대로 적절한 View를 호출.
 * @author 이정석
 */
public class StaffController {
	static StaffService staffService = new StaffService();
	static StaffView staffView = new StaffView();

	public void doAddStaff(Staff staff) {
		staffService.addStaff(staff);
	}

	public void doModifyStaff(Staff staff) {
		staffService.modifyStaff(staff);
	}

	public void doDeleteStaff(int staffId) {
		if (!staffService.deleteStaff(staffId)) {
			staffView.printIdNotExist();
		}
	}

	public void doCheckStaff(int staffId) {
		if (!staffService.checkStaff(staffId)) {
			staffView.printIdNotExist();
		}
	}

	public void doCheckAllStaff() {
		if (!staffService.checkAllStaff()) {
			staffView.printPeopleEmpty();
		}
	}

	public void doPrintFullPeople() {
		staffView.printFullPeople();
	}

	public void doPrintIdExist() {
		staffView.printIdExist();
	}

	public void doPrintIdNotExist() {
		staffView.printIdNotExist();
	}
}
