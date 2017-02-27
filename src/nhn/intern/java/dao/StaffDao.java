package nhn.intern.java.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import nhn.intern.java.constant.Constant;
import nhn.intern.java.controller.StaffController;
import nhn.intern.java.database.SchoolPerson;
import nhn.intern.java.exception.ExceptionHandling;
import nhn.intern.java.model.person.Staff;

/**
 * DAO를 통해서 DataBase에 접근.
 * DataBase 내용 읽기, 쓰기 기능.
 * @author 이정석
 */
public class StaffDao {
	/**
	 * 이전에 기록된 data를 읽어서 메모리로 불러들이는 메소드.
	 * File이 없거나 잘못된 input형식에 대해서 exception발생 및 처리.
	 */
	public static void readStaffMap() {
		String fileName = Constant.STAFF_FILENAME + ".txt";
		try {
			StaffController staffController = new StaffController();
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			System.out.println(Constant.STAFF_FILENAME + " data를 성공적으로 불러들였습니다.");
			String readLine;
			while ((readLine = in.readLine()) != null) {
				Staff staff = new Staff();
				String readData[] = readLine.split(" ");
				if (readData.length != Constant.STAFF_FILE_FORMAT_LENGTH) {
					System.out.println("잘못된 input정보 입니다. 이 data는 저장되지 않습니다.");
					System.out.println("wrong data : " + readLine);
					continue;
				}

				staff.setId(Integer.parseInt(readData[0]));
				staff.setName(readData[1]);
				staff.setBirthDate(Integer.parseInt(readData[2]));
				staffController.doAddStaff(staff);
			}
			in.close();
		} catch (IOException e) {
			ExceptionHandling.getInstance().fileReadExceptionHandling(fileName);
		}
	}

	public static void writeStaffMap() {
		try {
			String fileName = "." + File.separator + Constant.STAFF_FILENAME + ".txt";
			File file = new File(fileName);
			FileWriter fileWrite = new FileWriter(file, false);

			for (Integer staffId : SchoolPerson.staffMap.keySet()) {
				Staff staff = SchoolPerson.staffMap.get(staffId);
				String txt = staff.getFormatString();
				txt += "\r\n";
				fileWrite.write(txt);
			}
			fileWrite.flush();
			fileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addStaff(Staff staff) {
		SchoolPerson.staffMap.put(staff.getId(), staff);
		writeStaffMap();
	}

	public static void modifyStaff(Staff staff) {
		SchoolPerson.staffMap.put(staff.getId(), staff);
		writeStaffMap();
	}

	public static void deleteStaff(int staffId) {
		SchoolPerson.staffMap.remove(staffId);
		writeStaffMap();
	}
}
