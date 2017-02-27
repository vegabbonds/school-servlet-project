<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="w3-main" style="margin-left: 300px;">
	<div id="myTop"
		class="w3-top w3-container w3-padding-16 w3-theme w3-large w3-hide-large">
		<i
			class="fa fa-bars w3-opennav w3-xlarge w3-margin-left w3-margin-right"
			onclick="w3_open()"></i>
	</div>
	<div class="w3-container w3-padding-large w3-section w3-light-grey">
		<div id="search_student_info">
			<div>
				<h2>Student Information</h2>
			</div>
			<form name="more_student_search" method="get"
				action="SearchSpecificStudentServlet">
				<span style="float: right"><select name="sel_opt">
						<option value="id">Id</option>
						<option value="name">Name</option>
						<option value="birthDate">BirthDate</option>
				</select> <input type="text" maxlength="10" name="specific_id" required>
					<input type="submit" value="More Search"></span>
			</form>
			<div>
				<table style="width: 100%">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>BrithDate</th>
						<th></th>
					</tr>
					<c:forEach items="${students}" var="i">
						<tr>
							<td>${i.id}</td>
							<td>${i.name}</td>
							<td>${i.birthDate}</td>
							<td><input type="button" value="modify"
								onclick="modify_student(this);"> <input type="button"
								value="delete" onclick="delete_student(this);"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div id="search_staff_info">
			<div>
				<h2>Staff Information</h2>
			</div>
			<form name="more_staff_search" method="get"
				action="SearchSpecificStaffServlet">
				<span style="float: right"><select name="sel_opt">
						<option value="id">Id</option>
						<option value="name">Name</option>
						<option value="birthDate">BirthDate</option>
				</select> <input type="text" maxlength="10" name="specific_id" required>
					<input type="submit" value="More Search"></span>
			</form>
			<div>
				<table style="width: 100%">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>BrithDate</th>
						<th></th>
					</tr>
					<c:forEach items="${staffs}" var="i">
						<tr>
							<td>${i.id}</td>
							<td>${i.name}</td>
							<td>${i.birthDate}</td>
							<td><input type="button" value="modify"
								onclick="modify_staff(this);"> <input type="button"
								value="delete" onclick="delete_staff(this);"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div id="search_subject_info">
			<div>
				<h2>Subject Information</h2>
			</div>
			<form name="more_subject_search" method="get"
				action="SearchSpecificSubjectServlet">
				<span style="float: right"><select name="sel_opt">
						<option value="id">Id</option>
						<option value="name">Name</option>
				</select> <input type="text" maxlength="10" name="specific_id" required>
					<input type="submit" value="More Search"></span>
			</form>
			<div>
				<table style="width: 100%">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th></th>
					</tr>
					<c:forEach items="${subjects}" var="i">
						<tr>
							<td>${i.subjectId}</td>
							<td>${i.subjectName}</td>
							<td><input type="button" value="modify"
								onclick="modify_subject(this);"> <input type="button"
								value="delete" onclick="delete_subject(this);"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div id="search_teacher_info">
			<div>
				<h2>Teacher Information</h2>
			</div>
			<form name="more_teacher_search" method="get"
				action="SearchSpecificTeacherServlet">
				<span style="float: right"><select name="sel_opt">
						<option value="id">Id</option>
						<option value="name">Name</option>
						<option value="birthDate">BirthDate</option>
						<option value="subjectId">SubjectId</option>
				</select> <input type="text" maxlength="10" name="specific_id" required>
					<input type="submit" value="More Search"></span>
			</form>
			<div>
				<table style="width: 100%">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>BrithDate</th>
						<th>SubjectId</th>
						<th></th>
					</tr>
					<c:forEach items="${teachers}" var="i">
						<tr>
							<td>${i.id}</td>
							<td>${i.name}</td>
							<td>${i.birthDate}</td>
							<td>${i.subjectId}</td>
							<td><input type="button" value="modify"
								onclick="modify_teacher(this);"> <input type="button"
								value="delete" onclick="delete_teacher(this);"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div id="search_score_info">
			<div>
				<h2>Score Information</h2>
			</div>
			<form name="more_score_search" method="get"
				action="SearchSpecificScoreServlet">
				<span style="float: right"><select name="sel_opt">
						<option value="id">Id</option>
				</select> <input type="text" maxlength="10" name="specific_id" required>
					<input type="submit" value="More Search"></span>
			</form>
			<div>
				<table style="width: 100%">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>SubjectId</th>
						<th>SubjectName</th>
						<th>Score</th>
						<th></th>
					</tr>
					<c:forEach items="${scores}" var="i">
						<tr>
							<td>${i.id}</td>
							<td>${i.name}</td>
							<td>${i.subjectId}</td>
							<td>${i.subjectName}</td>
							<td>${i.score}</td>
							<td><input type="button" value="modify"
								onclick="modify_score(this);"> <input type="button"
								value="delete" onclick="delete_score(this);"></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div id="search_avg_info">
			<div>
				<h2>Score Avg Information</h2>
			</div>

			<form name="more_avg_search" method="get"
				action="SearchSpecificStudentAvgServlet">
				<span style="float: right"><select name="sel_opt">
						<option value="id">Id</option>
				</select> <input type="text" maxlength="10" name="specific_id" required>
					<input type="submit" value="More Search"></span>
			</form>
			<div>
				<table style="width: 100%">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Average Score</th>
					</tr>
					<c:forEach items="${avgScores}" var="i">
						<tr>
							<td>${i.id}</td>
							<td>${i.name}</td>
							<td>${i.score}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

		<div id="search_overall_avg_info">
			<div>
				<h2>Score Overall Avg Information</h2>
			</div>
			<div>
				<table style="width: 100%">
					<tr>
						<th></th>
						<th>Score</th>
					</tr>

					<tr>
						<td>Overall Avg Score</td>
						<td>${overallAvgScore}</td>
					</tr>

				</table>
			</div>
		</div>

	</div>
</div>
<form action="SearchStudentServlet" method="get" id="search_student">
</form>
<form action="DeleteStudentServlet" method="get"
	id="delete_student_form">
	<input type="hidden" name="id" id="delete_student_id">
</form>

<form action="SearchStaffServlet" method="get" id="search_staff">
</form>
<form action="DeleteStaffServlet" method="get" id="delete_staff_form">
	<input type="hidden" name="id" id="delete_staff_id">
</form>

<form action="SearchSubjectServlet" method="get" id="search_subject">
</form>
<form action="DeleteSubjectServlet" method="get"
	id="delete_subject_form">
	<input type="hidden" name="id" id="delete_subject_id">
</form>

<form action="SearchTeacherServlet" method="get" id="search_teacher">
</form>
<form action="DeleteTeacherServlet" method="get"
	id="delete_teacher_form">
	<input type="hidden" name="id" id="delete_teacher_id">
</form>

<form action="SearchScoreServlet" method="get" id="search_score">
</form>
<form action="DeleteScoreServlet" method="get" id="delete_score_form">
	<input type="hidden" name="id" id="delete_score_id"> <input
		type="hidden" name="subjectId" id="delete_score_subjectId">
</form>

<form action="SearchAvgScoreServlet" method="get" id="search_avg_score">
</form>

<form action="SearchOverallAvgScoreServlet" method="get"
	id="search_overall_avg_score"></form>
