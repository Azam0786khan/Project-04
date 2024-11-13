package in.co.rays.model;

import java.lang.classfile.instruction.ReturnInstruction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import in.co.rays.bean.EmployeeBean;
import in.co.rays.util.JDBCDataSourceRb;

public class EmployeeModel {

	public Integer nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSourceRb.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_employee");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}
		JDBCDataSourceRb.closeConnection(conn);
		return pk + 1;
	}

	public void add(EmployeeBean bean) throws Exception {

		int pk = nextPk();

		Connection conn = JDBCDataSourceRb.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into st_employee values(?,?,?,?,?,?,?,?,?,?)");

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getFullName());
		pstmt.setString(3, bean.getUserName());
		pstmt.setString(4, bean.getPassword());
		pstmt.setTimestamp(5, new Timestamp(bean.getDob().getTime()));
		pstmt.setLong(6, bean.getPhoneNo());
		pstmt.setString(7, bean.getCreatedBy());
		pstmt.setString(8, bean.getModifiedBy());
		pstmt.setTimestamp(9, bean.getCreatedDateTime());
		pstmt.setTimestamp(10, bean.getModifiedDateTime());

		int i = pstmt.executeUpdate();

		JDBCDataSourceRb.closeConnection(conn);

		System.out.println("Data Inserted = " + i);
	}

	public void update(EmployeeBean bean) throws Exception {
		int pk = nextPk();

		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_employee set full_name=?, user_name=?, password=?, dob=?, phone_no=?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id=?");

		pstmt.setString(1, bean.getFullName());
		pstmt.setString(2, bean.getUserName());
		pstmt.setString(3, bean.getPassword());
		pstmt.setTimestamp(4, new Timestamp(bean.getDob().getTime()));
		pstmt.setLong(5, bean.getPhoneNo());
		pstmt.setString(6, bean.getCreatedBy());
		pstmt.setString(7, bean.getModifiedBy());
		pstmt.setTimestamp(8, bean.getCreatedDateTime());
		pstmt.setTimestamp(9, bean.getModifiedDateTime());
		pstmt.setLong(10, bean.getId());

		int i = pstmt.executeUpdate();
		JDBCDataSourceRb.closeConnection(conn);
		System.out.println("Data Updated = " + i);
	}

	public void delete(long id) throws Exception {
		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_employee where id=?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSourceRb.closeConnection(conn);

		System.out.println("Data Deleted = " + i);

	}

	public EmployeeBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_employee where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		EmployeeBean bean = null;

		while (rs.next()) {
			bean = new EmployeeBean();
			bean.setId(rs.getLong(1));
			bean.setFullName(rs.getString(2));
			bean.setUserName(rs.getString(3));
			bean.setPassword(rs.getString(4));
			bean.setDob(rs.getTimestamp(5));
			bean.setPhoneNo(rs.getLong(6));
			bean.setCreatedBy(rs.getString(7));
			bean.setModifiedBy(rs.getString(8));
			bean.setCreatedDateTime(rs.getTimestamp(9));
			bean.setModifiedDateTime(rs.getTimestamp(10));
		}
		JDBCDataSourceRb.closeConnection(conn);
		return bean;	
	}
	
	public List search(EmployeeBean bean, int pageNo, int pageSize) throws Exception{
		
		Connection conn = JDBCDataSourceRb.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_employee where 1=1");
		
		if(bean != null) {
			if(bean.getId() > 0) {
				sql.append(" and id =" + bean.getId());
			}
			if(bean.getFullName() != null && bean.getFullName().length() > 0) {
				sql.append(" and full_name like '" + bean.getFullName() + "%'");
			}
			if(bean.getUserName() != null && bean.getUserName().length() > 0) {
				sql.append(" and user_name like '" + bean.getUserName() + "%'");
			}
			if(bean.getPassword() != null && bean.getPassword().length() > 0) {
				sql.append(" and password like '" + bean.getPassword() + "%'");
			}
			if (bean.getDob() != null) {
				sql.append("and dob like '" + bean.getDob() + "%'");
			}
			if (bean.getPhoneNo() > 0) {
				sql.append(" and phone_no =" + bean.getPhoneNo());

			}
			
				}
		
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}
		
		System.out.println("sql = " + sql.toString());
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		List list = new ArrayList();
		
		while(rs.next()) {
			
			bean = new EmployeeBean();
			bean.setId(rs.getLong(1));
			bean.setFullName(rs.getString(2));
			bean.setUserName(rs.getString(3));
			bean.setPassword(rs.getString(4));
			bean.setDob(rs.getTimestamp(5));
			bean.setPhoneNo(rs.getLong(6));
			bean.setCreatedBy(rs.getString(7));
			bean.setModifiedBy(rs.getString(8));
			bean.setCreatedDateTime(rs.getTimestamp(9));
			bean.setModifiedDateTime(rs.getTimestamp(10));
			list.add(bean);
		}
		
		JDBCDataSourceRb.closeConnection(conn);
		return list;
		
		
	}
	
	public List list() throws Exception{
		return search(null, 0, 0);
	}

}
