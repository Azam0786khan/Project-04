package in.co.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.EmployeeBean;
import in.co.rays.bean.OrderBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.EmployeeModel;
import in.co.rays.model.OrderModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.ServletUtility;

@WebServlet(name="EmployeeListCtl", urlPatterns = {"/EmployeeListCtl"})
public class EmployeeListCtl extends BaseCtl{
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		EmployeeBean bean = new EmployeeBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFullName(DataUtility.getString(request.getParameter("fullName")));
		bean.setUserName(DataUtility.getString(request.getParameter("userName")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setPhoneNo(DataUtility.getLong(request.getParameter("phoneNo")));
		return bean;
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list;
		
		int pageNo = 1;
		int pageSize = 10;

		EmployeeBean bean = (EmployeeBean) populateBean(request);
		EmployeeModel model = new EmployeeModel();
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		try {
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list = null;
		String op = DataUtility.getString(request.getParameter("operation"));

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? 10 : pageSize;

		EmployeeBean bean = (EmployeeBean) populateBean(request);

		String[] ids = request.getParameterValues("ids");
		EmployeeModel model = new EmployeeModel();

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			pageNo--;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.EMPLOYEE_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.EMPLOYEE_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;

			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					try {
						model.delete(DataUtility.getInt(id));
						ServletUtility.setSuccessMessage("Data Successfully Deleted", request);

					} catch (ApplicationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return;
					}

					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}

				}
			} else {
				ServletUtility.setErrorMessage("Select Atleast One Record", request);
			}

		}

		try {
			list = model.search(bean, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
			ServletUtility.setErrorMessage("No Record Found", request);
		}
		ServletUtility.setBean(bean, request);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
	return	ORSView.EMPLOYEE_LIST_VIEW;	
		}

}
