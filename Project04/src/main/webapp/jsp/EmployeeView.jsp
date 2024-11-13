<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.EmployeeCtl"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<form action="<%=ORSView.EMPLOYEE_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.EmployeeBean"
			scope="request"></jsp:useBean>
		<div align="center">
			<div>
				<h1>Add Employee</h1>
			</div>
			<h3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</h3>
			<h3>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</h3>
			<table>
				<tr>
					<th>Full Name</th>
					<td><input type="text" name="fullName"
					 placeholder="Enter Full Name"
					  value="<%=DataUtility.getStringData(bean.getFullName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("fullName", request)%></font></td>
				</tr>

				<tr>
					<th>User Name</th>
					<td><input type="text" name="userName"
					placeholder="Enter User Name"
					value="<%=DataUtility.getStringData(bean.getUserName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("userName", request)%></font></td>
				</tr>

				<tr>
					<th>Password</th>
					<td><input type="text" name="password"
					placeholder="Enter password"
					value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>

				<tr>
					<th>Dob</th>
					<td><input type="text" name="dob" id="udate"
					placeholder="Enter Dob"
					value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>

				<tr>
					<th>PhoneNo.</th>
					<td><input type="text" name="phoneNo"
					placeholder="Enter Phone No"
					value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<%
					if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=EmployeeCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=EmployeeCtl.OP_CANCEL%>">
						<%
						} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=EmployeeCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=EmployeeCtl.OP_RESET%>">
						<%
						}
						%>
				</tr>
			</table>
		</div>

	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>