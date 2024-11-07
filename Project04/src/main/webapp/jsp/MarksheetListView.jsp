<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.ctl.MarksheetListCtl"%>
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
	<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean"
			scope="request"></jsp:useBean>
		<div align="center">
			<h1>
				<font color="navy">Marksheet List</font>
			</h1>
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>
		<%
		int pageNo = ServletUtility.getPageNo(request);
		int pageSize = ServletUtility.getPageSize(request);
		int index = ((pageNo - 1) * pageSize) + 1;
		int nextPageSize = (request.getAttribute("nextListSize") != null)
				? DataUtility.getInt(request.getAttribute("nextListSize").toString())
				: 0;
		List roleNoList = (List) request.getAttribute("rollNo");
		List list = ServletUtility.getList(request);
		Iterator it = list.iterator();
		if (list.size() != 0) {
		%>
		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
		<table style="width: 100%">
			<tr>
				<td align="center"><label><b>Name :</b></label> <input
					type="text" name="name"
					value="<%=ServletUtility.getParameter("name", request)%>">&nbsp;

					<label><b>Roll No :</b></label> <%=HTMLUtility.getList("rollNoId", DataUtility.getStringData(bean.getRollNo()), roleNoList)%>&nbsp;

					<input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_SEARCH%>"> &nbsp; <input
					type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_RESET%>"></td>
			</tr>
		</table>
		<br>
		<table border="1" width="100%" align="center" cellpadding=7px
			cellspacing=".2">
			<tr style="background: skyblue">
				<th><input type="checkbox" id="selectall"></th>
				<th>S.No.</th>
				<th>RollNo</th>
				<th>Name</th>
				<th>Physics</th>
				<th>Chemistry</th>
				<th>Maths</th>
				<th>Total</th>
				<th>Percentage</th>
				<th>Result</th>
				<th>Edit</th>
			</tr>
			<%
			while (it.hasNext()) {
				bean = (MarksheetBean) it.next();
				MarksheetModel model = new MarksheetModel();
				MarksheetBean marksheetBean = model.findByPk(bean.getStudentId());
			%>
			<tr align="center">
				<td><input type="checkbox" class="case" name="ids"
					value="<%=bean.getId()%>"></td>
				<td><%=index++%></td>
				<td><%=bean.getRollNo()%></td>
				<td><%=bean.getName()%></td>
				<td><%=bean.getPhysics()%></td>
				<td><%=bean.getChemistry()%></td>
				<td><%=bean.getMaths()%></td>
				<td><%=bean.getPhysics() + bean.getChemistry() + bean.getMaths()%></td>
				<td><%=String.format("%.2f", ((bean.getPhysics() + bean.getChemistry() + bean.getMaths()) / 3.0)) + " %"%></td>
				<td>
					<%
					if (bean.getPhysics() >= 33 && bean.getChemistry() >= 33 && bean.getMaths() >= 33) {
					%><span style="color: green;"> Pass </span> <%
 } else {
 %> <span style="color: red"> Fail</span> <%
 }
 %>
				</td>
				<td><a href="<%=ORSView.MARKSHEET_CTL%>?id=<%=bean.getId()%>">edit</a></td>
			</tr>
			<%
			}
			%>
		</table>
		<br>

		<table style="width: 100%">
			<tr>
				<td style="width: 30%"><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_PREVIOUS%>"
					<%=(pageNo == 1) ? "disabled" : ""%>></td>
				<td style="width: 30%"><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_NEW%>"></td>
				<td style="width: 25%"><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_DELETE%>"></td>
				<td style="text-align: right;"><input type="submit"
					name="operation" value="<%=MarksheetListCtl.OP_NEXT%>"
					<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
			</tr>
		</table>
		<%
		}
		if (list.size() == 0) {
		%>
		<br>
		<table>
			<tr>
				<td align="right"><input type="submit" name="operation"
					value="<%=MarksheetListCtl.OP_BACK%>"></td>
			</tr>
		</table>
		<%
		}
		%>
	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>
