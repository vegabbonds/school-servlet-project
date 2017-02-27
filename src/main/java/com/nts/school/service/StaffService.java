package com.nts.school.service;

import java.util.ArrayList;
import java.util.List;

import com.nts.school.dao.StaffDao;
import com.nts.school.util.Constant;
import com.nts.school.util.ResultMessage;
import com.nts.school.util.SearchOption;
import com.nts.school.vo.person.Staff;

public class StaffService {

	private StaffDao staffDao;

	public StaffService() {
		staffDao = new StaffDao();
	}

	public ResultMessage addStaff(Staff staff) {
		if (staffDao.getStaffCount() >= Constant.MAX_PERSON) {
			return ResultMessage.ADD_FAIL;
		}

		return staffDao.addStaff(staff);
	}

	public ResultMessage modifyStaff(Staff staff) {
		if (staffDao.isStaffExistById(staff.getId()) == false) {
			return ResultMessage.MODIFY_FAIL;
		}

		return staffDao.modifyStaff(staff);
	}

	public ResultMessage deleteStaff(int staffId) {
		if (staffDao.isStaffExistById(staffId) == false) {
			return ResultMessage.DELETE_FAIL;
		}

		return staffDao.deleteStaff(staffId);
	}

	public List<Staff> searchStaff() {
		List<Staff> staffs = new ArrayList<Staff>();
		staffs = staffDao.searchStaff();
		return staffs;
	}

	public List<Staff> searchSpecificStaff(String getData, String selectOption) throws Exception {
		List<Staff> specificStaffs = new ArrayList<Staff>();
		SearchOption option = SearchOption.findSearchOption(selectOption);
		switch (option) {
			case ID:
				return searchStaffById(getData);
			case NAME:
				return searchStaffByName(getData);
			default:
				return specificStaffs;
		}
	}

	public List<Staff> searchStaffById(String id) {
		List<Staff> specificStaffs = new ArrayList<Staff>();
		specificStaffs = staffDao.searchStaffById(Integer.parseInt(id));
		return specificStaffs;
	}

	public List<Staff> searchStaffByName(String name) {
		List<Staff> specificStaffs = new ArrayList<Staff>();
		specificStaffs = staffDao.searchStaffByName(name);
		return specificStaffs;
	}

}
