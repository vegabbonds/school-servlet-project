package com.nts.school.servlet.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.school.service.TeacherService;
import com.nts.school.vo.person.Teacher;

/**
 * Servlet implementation class SearchSpecificTeacherServlet
 */
@WebServlet("/SearchSpecificTeacherServlet")
public class SearchSpecificTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherService teacherService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchSpecificTeacherServlet() {
		super();
		teacherService = new TeacherService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			List<Teacher> teachers = new ArrayList<Teacher>();
			String selectOption = request.getParameter("sel_opt");
			String id = request.getParameter("specific_id");

			teachers = teacherService.searchSpecificTeacher(id, selectOption);

			request.setAttribute("teachers", teachers);
			RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/jsp/view/teacher/searchTeacherPage.jsp");
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
