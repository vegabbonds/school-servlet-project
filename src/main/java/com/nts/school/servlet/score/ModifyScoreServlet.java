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
 * Servlet implementation class ModifyScoreServlet
 */
@WebServlet("/ModifyScoreServlet")
public class ModifyScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreService scoreService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyScoreServlet() {
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
			List<StudentScore> searchScoreList = new ArrayList<StudentScore>();
			String getId = request.getParameter("modify_id");
			int id = Integer.parseInt(getId);
			String getSubjectId = request.getParameter("modify_subjectId");
			int subjectId = Integer.parseInt(getSubjectId);
			String getScore = request.getParameter("score");
			int subjectScore = Integer.parseInt(getScore);
			Score score = new Score(id, subjectId, subjectScore);

			ResultMessage resultMessage = scoreService.modifyScore(score);
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
