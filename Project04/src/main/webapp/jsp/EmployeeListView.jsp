<%@page import="in.co.rays.bean.EmployeeBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.ctl.EmployeeListCtl"%>
<%@page import="in.co.rays.model.EmployeeModel"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
</head>
<body>

	<jsp:useBean id="bean" class="in.co.rays.bean.EmployeeBean"
		scope="request"></jsp:useBean>

	<form action="<%=ORSView.EMPLOYEE_LIST_CTL%>" method="post">
		<%@include file="Header.jsp"%>

		<center>

			<div align="center">
				<h1>Employee List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>
			<%
			int pageNo = ServletUtility.getPageNo(request);
			int pageSize = ServletUtility.getPageSize(request);
			int index = ((pageNo - 1) * pageSize) + 1;

			List list = ServletUtility.getList(request);
			Iterator it = list.iterator();

			if (list.size() != 0) {
			%>
			<table width="100%" align="center">
				<tr>
					<td align="center"><label> Full Name:</label> <input
						type="text" name="fullName" placeholder="Enter fullName" Size="25"
						value="<%=ServletUtility.getParameter("fullName", request)%>">&nbsp;


						&nbsp; <label>User Name:</label> <input type="text"
						name="userName" placeholder="Enter userName" Size="25"
						value="<%=ServletUtility.getParameter("userName", request)%>">

						&nbsp; <input type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_RESET%>"></td>
				</tr>
			</table>

			<br>

			<table width="100%" align="center">

				<br>

				<table border="1" width="100%" align="center" cellpadding=6px
					cellspacing=".2">
					<tr style="background: skyblue">
						<th><input type="checkbox" id="selectall" name="select"></th>
						<th>S No.</th>
						<th>Full Name</th>
						<th>User Name</th>

						<th>DOB</th>
						<th>PhoneNo</th>

						<th>Edit</th>
					</tr>

					<%
					while (it.hasNext()) {
						bean = (EmployeeBean) it.next();
					%>


					<tr align="center">
						<td><input type="checkbox" class="case" name="ids"
							value="<%=bean.getId()%>">
						<td><%=index++%></td>
						<td><%=bean.getFullName()%></td>
						<td><%=bean.getUserName()%></td>
						<td><%=bean.getDob()%></td>
						<td><%=bean.getPhoneNo()%></td>

						<td><a href="EmployeeCtl?id=<%=bean.getId()%>">Edit</a></td>
					</tr>
					<%
					}
					%>
				</table>

				<br>
				<table width="100%">
					<tr>
						<%
						if (pageNo == 1) {
						%>
						<td><input type="submit" name="operation" disabled="disabled"
							value="<%=EmployeeListCtl.OP_PREVIOUS%>"> <%
 } else {
 %>
						<td><input type="submit" name="operation"
							value="<%=EmployeeListCtl.OP_PREVIOUS%>"></td>
						<%
						}
						%>

						<td><input type="submit" name="operation"
							value="<%=EmployeeListCtl.OP_DELETE%>"></td>
						<td><input type="submit" name="operation"
							value="<%=EmployeeListCtl.OP_NEW%>"></td>

						<%
						EmployeeModel model = new EmployeeModel();
						%>
						<%
						if (list.size() < pageSize || model.nextPk() - 1 == bean.getId()) {
						%>
						<td align="right"><input type="submit" name="operation"
							disabled="disabled" value="<%=EmployeeListCtl.OP_NEXT%>"></td>
						<%
						} else {
						%>
						<td align="right"><input type="submit" name="operation"
							value="<%=EmployeeListCtl.OP_NEXT%>"></td>
						<%
						}
						%>

					</tr>
				</table>
				<%
				}
				if (list.size() == 0) {
				%>
				<td align="center"><input type="submit" name="operation"
					value="<%=EmployeeListCtl.OP_BACK%>"></td>
				<%
				}
				%>

				<input type="hidden" name="pageNo" value="<%=pageNo%>">
				<input type="hidden" name="pageSize" value="<%=pageSize%>">
				</form>
				</center>

				<%@include file="Footer.jsp"%>
</body>
</html>