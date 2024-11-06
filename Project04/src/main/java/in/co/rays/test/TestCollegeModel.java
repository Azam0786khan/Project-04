package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.model.CollegeModel;

public class TestCollegeModel {

	public static void main(String[] args) throws Exception {
		 testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByName();
		//testSearch();
	}

	public static void testAdd() throws Exception {

		CollegeBean bean = new CollegeBean();
		bean.setName("azam");
		bean.setAddress("Indore");
		bean.setState("MP");
		bean.setCity("Indore");
		bean.setPhoneNo("1234567890");
		bean.setCreatedBy("ak@gmail.com");
		bean.setModifiedBy("ak@gmail.com");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));

		CollegeModel model = new CollegeModel();
		model.add(bean);
	}

	public static void testUpdate() throws Exception {

		CollegeModel model = new CollegeModel();

		CollegeBean bean = model.findByPk(4);
		bean.setName("Bansal");
		bean.setAddress("indore");
		bean.setState("MP");

		model.update(bean);
	}

	public static void testDelete() throws Exception {
		CollegeModel model = new CollegeModel();
		model.delete(6);
	}

	public static void testFindByPk() throws Exception {

		CollegeModel model = new CollegeModel();

		CollegeBean bean = model.findByPk(2);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		} else {
			System.out.println("id not found");
		}
	}

	public static void testFindByName() throws Exception {

		CollegeModel model = new CollegeModel();

		CollegeBean bean = model.findByName("Medi");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		} else {
			System.out.println("college name not found");
		}
	}

	public static void testSearch() throws Exception {

		CollegeBean bean = new CollegeBean();
		// bean.setName("m");

		CollegeModel model = new CollegeModel();

		List list = model.search(bean, 1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CollegeBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDateTime());
			System.out.println("\t" + bean.getModifiedDateTime());
		}
	}
}