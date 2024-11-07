
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.ctl.CollegeListCtl"%>
<%@page import="in.co.rays.model.CollegeModel"%>
<%@page import="in.co.rays.bean.CollegeBean"%>
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
	<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">


		<jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean"
			scope="request"></jsp:useBean>

		<%
		List list = ServletUtility.getList(request);
		int pageNo = ServletUtility.getPageNo(request);
		int pageSize = ServletUtility.getPageSize(request);
		%>

		<div align="center">
			<h1>
				<font color="navy">College List</font>
			</h1>
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>

		<%
		List clist = (List) request.getAttribute("collegeList");
		%>

		<%
		int index = ((pageNo - 1) * pageSize) + 1;

		/* Iterator<CollegeBean> it = clist.iterator(); */

		if (list.size() != 0) {
		%>

		<table width="100%" align="center">
			<tr>
				<td align="right">&nbsp; <label>College Name:</label> <%=HTMLUtility.getList("collegename", String.valueOf(bean.getId()), clist)%>
					&nbsp; <label>State:</label> <input type="text" name="state"
					placeholder="Enter State Name" Size="25"
					value="<%=ServletUtility.getParameter("state", request)%>">
					&nbsp; <label>City:</label> <input type="text" name="city"
					placeholder="Enter City" Size="25"
					value="<%=ServletUtility.getParameter("city", request)%>">
					&nbsp; <label>Phone No:</label> <input type="text" name="phoneNo"
					placeholder="Enter PhoneNo" Size="25"
					value="<%=ServletUtility.getParameter("phoneNo", request)%>">

					&nbsp; <input type="submit" name="operation"
					value="<%=CollegeListCtl.OP_SEARCH%>"> <input type="submit"
					name="operation" value="<%=CollegeListCtl.OP_RESET%>"></td>
			</tr>
		</table>

		<br>

		<table border="1" width="100%" align="center" cellpadding=7px
			cellspacing=".2">
			<tr style="background: skyblue">
				<th><input type="checkbox" id="selectall"></th>
				
				<th>S.No.</th>

				<th>Name</th>
				<th>Address</th>

				<th>State</th>
				<th>City</th>
				<th>Phone No</th>
				<th>Edit</th>
			</tr>
			<%
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (CollegeBean) it.next();
				CollegeModel model = new CollegeModel();
				CollegeBean collegeBean = new CollegeBean();
				collegeBean = model.findByPk(bean.getId());
			%>
			<tr align="center">
				<td><input type="checkbox"></td>
				<td><%=index++%></td>
				

				<td><%=bean.getName()%></td>
				<td><%=bean.getAddress()%></td>
				<td><%=bean.getState()%></td>
				<td><%=bean.getCity()%></td>
				<td><%=bean.getPhoneNo()%></td>

				<td><a href="<%=ORSView.COLLEGE_CTL%>?id=<%=bean.getId()%>">edit</a></td>
			</tr>
			<%
			}
			%>
		</table>
		<table width="100%">
			<tr>
				<%
				if (pageNo == 1) {
				%>
				<td><input type="submit" name="operation" disabled="disabled"
					value="<%=CollegeListCtl.OP_PREVIOUS%>"> <%
 } else {
 %>
				<td><input type="submit" name="operation"
					value="<%=CollegeListCtl.OP_PREVIOUS%>"></td>
				<%
				}
				%>

				<td><input type="submit" name="operation"
					value="<%=CollegeListCtl.OP_DELETE%>"></td>
				<td><input type="submit" name="operation"
					value="<%=CollegeListCtl.OP_NEW%>"></td>

				<%
				CollegeModel model = new CollegeModel();
				%>
				<%
				if (list.size() < pageSize || model.nextPk() - 1 == bean.getId()) {
				%>
				<td align="right"><input type="submit" name="operation"
					disabled="disabled" value="<%=CollegeListCtl.OP_NEXT%>"></td>
				<%
				} else {
				%>
				<td align="right"><input type="submit" name="operation"
					value="<%=CollegeListCtl.OP_NEXT%>"></td>
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
			value="<%=CollegeListCtl.OP_BACK%>"></td>
		<%
		}
		%>

		<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
			type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	<%@ include file="Footer.jsp"%>
</body>
</html>
