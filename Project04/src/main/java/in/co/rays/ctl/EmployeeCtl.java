package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.EmployeeBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.EmployeeModel;
import in.co.rays.model.StudentModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "EmployeeCtl", urlPatterns = "/EmployeeCtl")
public class EmployeeCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("fullName"))) {
			request.setAttribute("fullName", PropertyReader.getValue("error.require", "fullName"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("fullName"))) {
			request.setAttribute("fullName", "Invalid Full Name");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("userName"))) {
			request.setAttribute("userName", PropertyReader.getValue("error.require", "userName"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("userName"))) {
			request.setAttribute("userName", "UserName should be email");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "password"));
			pass = false;
		} else if (DataValidator.isPasswordLength(request.getParameter("password"))) {
			request.setAttribute("passowrd", "Length should be greater than 8");
			pass = false;
		} else if (DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", "Should have one special case");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", "Invalid Date of Birth");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getValue("error.require", "phoneNo"));
			pass = false;
		} else if (!DataValidator.isPhoneLength(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", "Mobile No must have 10 digits");
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", "Invalid Mobile No");
			pass = false;
		}

		return pass;

	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		EmployeeBean bean = new EmployeeBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFullName(DataUtility.getString(request.getParameter("fullName")));
		bean.setUserName(DataUtility.getString(request.getParameter("userName")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		bean.setPhoneNo(DataUtility.getLong(request.getParameter("phoneNo")));

		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		EmployeeModel model = new EmployeeModel();
		if (id > 0 || op != null) {
			EmployeeBean bean;
			try {
				bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				return;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		System.out.println(request.getParameter("operation"));
		EmployeeModel model = new EmployeeModel();

		if (OP_SAVE.equalsIgnoreCase(op)) {
			EmployeeBean bean = (EmployeeBean) populateBean(request);

			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Data Added successfully", request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
			}
		}
	}

	@Override
	protected String getView() {
		return ORSView.EMPLOYEE_VIEW;
	}

}
