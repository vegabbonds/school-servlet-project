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
import com.nts.school.util.ResultMessage;
import com.nts.school.vo.object.Subject;

/**
 * Servlet implementation class ModifySubjectServlet
 */
@WebServlet("/ModifySubjectServlet")
public class ModifySubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectService subjectService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifySubjectServlet() {
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
			String getId = request.getParameter("modify_id");
			int id = Integer.parseInt(getId);
			String name = request.getParameter("name");
			Subject subject = new Subject(id, name);

			ResultMessage resultMessage = subjectService.modifySubject(subject);
			subjects = subjectService.searchSubject();

			request.setAttribute("message", resultMessage.getMessage());
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
