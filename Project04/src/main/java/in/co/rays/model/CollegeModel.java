package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.util.JDBCDataSourceRb;

public class CollegeModel {

	public Integer nextPk() throws Exception {
		int pk = 0;
		Connection conn = JDBCDataSourceRb.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_college");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		JDBCDataSourceRb.closeConnection(conn);
		return pk + 1;
	}

	public void add(CollegeBean bean) throws Exception {

		CollegeBean existBean = findByName(bean.getName());

		int pk = nextPk();

		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_college values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getAddress());
		pstmt.setString(4, bean.getState());
		pstmt.setString(5, bean.getCity());
		pstmt.setString(6, bean.getPhoneNo());
		pstmt.setString(7, bean.getCreatedBy());
		pstmt.setString(8, bean.getModifiedBy());
		pstmt.setTimestamp(9, bean.getCreatedDateTime());
		pstmt.setTimestamp(10, bean.getModifiedDateTime());

		int i = pstmt.executeUpdate();

		JDBCDataSourceRb.closeConnection(conn);

		System.out.println("data inserted => " + i);
	}

	public void update(CollegeBean bean) throws Exception {

		CollegeBean existBean = findByName(bean.getName());

		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_college set name = ?, address = ?, state = ?, city = ?, phone_no = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ?  where id = ?");

		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getAddress());
		pstmt.setString(3, bean.getState());
		pstmt.setString(4, bean.getCity());
		pstmt.setString(5, bean.getPhoneNo());
		pstmt.setString(6, bean.getCreatedBy());
		pstmt.setString(7, bean.getModifiedBy());
		pstmt.setTimestamp(8, bean.getCreatedDateTime());
		pstmt.setTimestamp(9, bean.getModifiedDateTime());
		pstmt.setLong(10, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSourceRb.closeConnection(conn);
		System.out.println("data updated => " + i);
	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_college where id = ?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSourceRb.closeConnection(conn);

		System.out.println("data deleted => " + i);

	}

	public CollegeBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_college where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		CollegeBean bean = null;

		while (rs.next()) {
			bean = new CollegeBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setAddress(rs.getString(3));
			bean.setState(rs.getString(4));
			bean.setCity(rs.getString(5));
			bean.setPhoneNo(rs.getString(6));
			bean.setCreatedBy(rs.getString(7));
			bean.setModifiedBy(rs.getString(8));
			bean.setCreatedDateTime(rs.getTimestamp(9));
			bean.setModifiedDateTime(rs.getTimestamp(10));
		}
		JDBCDataSourceRb.closeConnection(conn);
		return bean;
	}

	public CollegeBean findByName(String name) throws Exception {

		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_college where name = ?");

		pstmt.setString(1, name);

		ResultSet rs = pstmt.executeQuery();

		CollegeBean bean = null;

		while (rs.next()) {
			bean = new CollegeBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setAddress(rs.getString(3));
			bean.setState(rs.getString(4));
			bean.setCity(rs.getString(5));
			bean.setPhoneNo(rs.getString(6));
			bean.setCreatedBy(rs.getString(7));
			bean.setModifiedBy(rs.getString(8));
			bean.setCreatedDateTime(rs.getTimestamp(9));
			bean.setModifiedDateTime(rs.getTimestamp(10));
		}
		JDBCDataSourceRb.closeConnection(conn);
		return bean;
	}

	public List search(CollegeBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSourceRb.getConnection();

		StringBuffer sql = new StringBuffer("select * from st_college where 1=1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and first_name like '" + bean.getName() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql ==>> " + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {
			bean = new CollegeBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setAddress(rs.getString(3));
			bean.setState(rs.getString(4));
			bean.setCity(rs.getString(5));
			bean.setPhoneNo(rs.getString(6));
			bean.setCreatedBy(rs.getString(7));
			bean.setModifiedBy(rs.getString(8));
			bean.setCreatedDateTime(rs.getTimestamp(9));
			bean.setModifiedDateTime(rs.getTimestamp(10));
			list.add(bean);
		}
		JDBCDataSourceRb.closeConnection(conn);
		return list;
	}
}