package com.nts.school.servlet.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.school.service.StaffService;
import com.nts.school.util.ResultMessage;
import com.nts.school.vo.person.Staff;

/**
 * Servlet implementation class DeleteStaffServlet
 */
@WebServlet("/DeleteStaffServlet")
public class DeleteStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffService staffService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteStaffServlet() {
		super();
		staffService = new StaffService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			List<Staff> staffs = new ArrayList<Staff>();
			String getId = request.getParameter("id");
			int id = Integer.parseInt(getId);

			ResultMessage resultMessage = staffService.deleteStaff(id);
			staffs = staffService.searchStaff();

			request.setAttribute("message", resultMessage.getMessage());
			request.setAttribute("staffs", staffs);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/view/staff/searchStaffPage.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request
				.getRequestDispatcher("/errorPage.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request, response);
	}

}
