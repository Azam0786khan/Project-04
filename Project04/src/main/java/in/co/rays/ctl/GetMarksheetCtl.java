package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.model.MarksheetModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "GetMarksheetCtl", urlPatterns = { "/GetMarksheetCtl" })
public class GetMarksheetCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		
		if(DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
			pass = true;
		}
		 else if (!DataValidator.isRollNo(request.getParameter("rollNo"))) {
				request.setAttribute("rollNo", "Roll No. must be in Formate (0000XX000000)");
				pass = false;
	        }
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		MarksheetBean bean = new MarksheetBean();
		
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		return bean;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletUtility.forward(getView(), req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String op = DataUtility.getString(req.getParameter("operation"));
		long id = DataUtility.getLong(req.getParameter("id"));
		
		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = (MarksheetBean) populateBean(req);
		
		if (OP_GO.equalsIgnoreCase(op)) {

            try {
                bean = model.findByRoll(bean.getRollNo());
      
                
             
      
                if (bean != null) {
                    ServletUtility.setBean(bean, req);
                }else {
                    ServletUtility.setErrorMessage("RollNo Does Not Exists",req);
                    
                }
            } catch (Exception e) {
               e.printStackTrace();
            	
                
            }
        }
            else if (OP_RESET.equalsIgnoreCase(op)) {
            	ServletUtility.redirect(ORSView.GET_MARKSHEET_CTL, req, resp);
            	return ;
			}
        ServletUtility.forward(getView(), req, resp);
        
    }
	
	
	@Override
	protected String getView() {

		return ORSView.GET_MARKSHEET_VIEW;
	}

}
