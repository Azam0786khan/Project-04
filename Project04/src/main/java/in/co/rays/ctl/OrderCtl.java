package in.co.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.bean.OrderBean;
import in.co.rays.model.MarksheetModel;
import in.co.rays.model.OrderModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

@WebServlet(name = "OrderCtl", urlPatterns = "/OrderCtl")
public class OrderCtl extends BaseCtl {
	
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "quantity"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("quantity"))) {
			request.setAttribute("quantity", "Invalid Quantity");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("product"))) {
			request.setAttribute("product", PropertyReader.getValue("error.require", "product"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getValue("error.require", "amount"));
			pass = false;
		}
		return pass;
	}
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		OrderBean bean = new OrderBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		bean.setProduct(DataUtility.getString(request.getParameter("product")));
		bean.setDate(DataUtility.getDate(request.getParameter("date")));
		bean.setAmount(DataUtility.getDouble(request.getParameter("amount")));
		populateDTO(bean, request);
		
		return bean;
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			OrderModel model = new OrderModel();

			try {
				OrderBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderModel model = new OrderModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		
		System.out.println(request.getParameter("operation"));
		
		if(OP_SAVE.equalsIgnoreCase(op)) {
			
			OrderBean bean = (OrderBean) populateBean(request);
			try {
				model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data Added successfully", request);
				
				ServletUtility.forward(getView(), request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ORDER_CTL, request, response);
			return;
		}
	}

	@Override
	protected String getView() {
		return ORSView.ORDER_VIEW;
	}

}
