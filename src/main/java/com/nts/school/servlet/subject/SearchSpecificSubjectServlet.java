package com.nts.school.servlet.subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.school.service.SubjectService;
import com.nts.school.vo.object.Subject;

/**
 * Servlet implementation class SearchSpecificSubjectServlet
 */
@WebServlet("/SearchSpecificSubjectServlet")
public class SearchSpecificSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectService subjectService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchSpecificSubjectServlet() {
		super();
		subjectService = new SubjectService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			List<Subject> subjects = new ArrayList<Subject>();
			String selectOption = request.getParameter("sel_opt");
			String id = request.getParameter("specific_id");

			subjects = subjectService.searchSpecificSubject(id, selectOption);
			request.setAttribute("subjects", subjects);

			RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/jsp/view/subject/searchSubjectPage.jsp");
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
