package com.nts.school.servlet.score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.school.service.ScoreService;
import com.nts.school.vo.object.StudentAvg;

/**
 * 특정 학생의 평균을 조회하고자 할 때 jsp에서 호출하는 servlet.
 * Servlet implementation class SearchSpecificStudentAvgServlet
 */
@WebServlet("/SearchSpecificStudentAvgServlet")
public class SearchSpecificStudentAvgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreService scoreService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchSpecificStudentAvgServlet() {
		super();
		scoreService = new ScoreService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			List<StudentAvg> specificStudent = new ArrayList<StudentAvg>();
			String getId = request.getParameter("specific_id");
			int specificStudentId = Integer.parseInt(getId);
			specificStudent = scoreService.searchAvgBySpecificStudent(specificStudentId);

			request.setAttribute("avgScores", specificStudent);
			RequestDispatcher dispatcher = request
				.getRequestDispatcher("/WEB-INF/jsp/view/score/searchAvgScorePage.jsp");
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
