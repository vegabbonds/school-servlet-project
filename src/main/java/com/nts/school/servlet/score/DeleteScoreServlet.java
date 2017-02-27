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
import com.nts.school.util.ResultMessage;
import com.nts.school.vo.object.Score;
import com.nts.school.vo.object.StudentScore;

/**
 * 요청한 작업을 적절한 service를 호출하고,
 * 적절한 jsp를 사용자에게 보여주도록 한다.
 * @author 이정석
 * Servlet implementation class DeleteScoreServlet
 */
@WebServlet("/DeleteScoreServlet")
public class DeleteScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreService scoreService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteScoreServlet() {
		super();
		scoreService = new ScoreService();
	}

	/**
	 * jsp에서 보내준 값을 받고 그에 맞는 service를 호출한뒤,
	 * 적절한 jsp를 사용자에게 보여준다.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			List<StudentScore> searchScoreList = new ArrayList<StudentScore>();
			String getId = request.getParameter("id");
			int id = Integer.parseInt(getId);
			String getSubjectId = request.getParameter("subjectId");
			int subjectId = Integer.parseInt(getSubjectId);
			Score score = new Score(id, subjectId, 0);

			ResultMessage resultMessage = scoreService.deleteScore(score);
			searchScoreList = scoreService.searchScore();

			request.setAttribute("message", resultMessage.getMessage());
			request.setAttribute("scores", searchScoreList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/view/score/searchScorePage.jsp");
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
