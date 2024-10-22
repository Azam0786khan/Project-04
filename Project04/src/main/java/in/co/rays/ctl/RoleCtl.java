package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.RoleBean;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "RoleCtl", urlPatterns = "/RoleCtl")
public class RoleCtl extends BaseCtl {
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		
		if(DataValidator.isNull(DataUtility.getStringData(request.getParameter("name")))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass =false;
		} else if(!DataValidator.isName(DataUtility.getStringData(request.getParameter("name")))){
			request.setAttribute("name", "Invalid Name");
			pass =false;
		}
		
		if(DataValidator.isNull(DataUtility.getStringData(request.getParameter("description")))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass =false;
		} 
		
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		RoleBean bean = new RoleBean();
		bean.setName(DataUtility.getStringData(request.getParameter("name")));
		bean.setDescription(DataUtility.getStringData(request.getParameter("description")));
		
		return bean;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletUtility.forward(getView(), req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletUtility.forward(getView(), req, resp);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ROLE_VIEW;
	}

}
