package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.model.RoleModel;

public class TestRoleModel {
	
	public static void main(String[] args) throws Exception {
		testAdd();
//		testUpdate();
//		testDelete();
//		testSearch();
	}

	private static void testSearch() throws Exception {
		RoleBean bean = new RoleBean();
		bean.setName("Prabhakar");
		bean.setDescription("I am Prabhakar");
		
		RoleModel model = new RoleModel();
		List list = model.search(bean,0,5);
		
		Iterator it = list.iterator();
		while(it.hasNext()) {
			RoleBean printBean = (RoleBean) it.next();
			System.out.println(" Name is : " + printBean.getName());
		}
	}

	private static void testDelete() throws Exception {
		RoleModel model = new RoleModel();
		model.delete(1);
	}

	private static void testUpdate() throws Exception {
		RoleBean bean = new RoleBean();
		bean.setId(1);
		bean.setName("Prabhakar");
		bean.setDescription("I am Prabhakar");
		bean.setCreatedBy("B");
		bean.setModifiedBy("Enrolled");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		
		RoleModel model = new RoleModel();
		model.update(bean);
	}

	private static void testAdd() throws Exception{
		RoleBean bean = new RoleBean();
		bean.setId(1);
		bean.setName("Prabhakar");
		bean.setDescription("I am Prabhakar");
		bean.setCreatedBy("B");
		bean.setModifiedBy("Enrolled");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		
		RoleModel model = new RoleModel();
		model.add(bean);
		
	}

}
