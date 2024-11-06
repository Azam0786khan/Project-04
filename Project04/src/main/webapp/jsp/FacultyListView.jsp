
<%@page import="in.co.rays.model.FacultyModel"%>
<%@page import="in.co.rays.bean.FacultyBean"%>
<%@page import="in.co.rays.bean.MarksheetBean"%>
<%@page import="in.co.rays.model.MarksheetModel"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">
		

		<jsp:useBean id="bean" class="in.co.rays.bean.FacultyBean"
			scope="request"></jsp:useBean>

		<%
		List list = ServletUtility.getList(request);
		int pageNo = ServletUtility.getPageNo(request);
		int pageSize = ServletUtility.getPageSize(request);
		%>

		<div align="center">
			<h1>
				<font color="navy">Faculty List</font>
			</h1>
		</div>

		<table border="1%" style="width: 100%">
			<tr>
				<th><input type="checkbox"></th>
				<th>S.No.</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>DOB</th>

				<th>Gender</th>
				<th>Mobile No</th>
				<th>Email</th>
				<th>College Id</th>
				<th>College Name</th>
				<th>Course Id</th>
				<th>Course Name</th>
				<th>Subject Id </th>
				<th>Subject Name</th>
				<th>Edit</th>
			</tr>
			<%
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (FacultyBean) it.next();
				FacultyModel model = new FacultyModel();
				FacultyBean facultyBean = new FacultyBean();
				facultyBean = model.findByPk(bean.getId());
			%>
			<tr align="center">
				<td><input type="checkbox"></td>
				<td><%=bean.getId()%></td>
				<td><%=bean.getFirstName()%></td>
				<td><%=bean.getLastName()%></td>
				<td><%=bean.getDob()%></td>
				<td><%=bean.getGender()%></td>
				<td><%=bean.getMobileNo()%></td>
				<td><%=bean.getEmail()%></td>
				<td><%=bean.getCollegeId()%></td>
				<td><%=bean.getCollegeName()%></td>
				<td><%=bean.getCourseId()%></td>
				<td><%=bean.getCourseName()%></td>
				<td><%=bean.getSubjectId()%></td>
				<td><%=bean.getSubjectName()%></td>

				<td><a href="<%=ORSView.FACULTY_CTL%>?id=<%=bean.getId()%>">edit</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>
