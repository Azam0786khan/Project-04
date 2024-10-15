package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.UserBean;
import in.co.rays.util.JDBCDataSourceRb;

public class UserModel {

	public Integer nextPk() throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select max(id) from st_user");

		int pk = 0;
		ResultSet rs = psmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;
	}

	public void add(UserBean bean) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("insert into st_user values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

		psmt.setLong(1, nextPk());
		psmt.setString(2, bean.getFirstName());
		psmt.setString(3, bean.getLastName());
		psmt.setString(4, bean.getLogin());
		psmt.setString(5, bean.getPassword());
		psmt.setTimestamp(6, new Timestamp(bean.getDob().getTime()));
		psmt.setString(7, bean.getMobileNo());
		psmt.setLong(8, bean.getRoleId());
		psmt.setString(9, bean.getGender());
		psmt.setString(10, bean.getCreatedBy());
		psmt.setString(11, bean.getModifiedBy());
		psmt.setTimestamp(12, bean.getCreatedDateTime());
		psmt.setTimestamp(13, bean.getModifiedDateTime());

		int i = psmt.executeUpdate();

	}

	public void update(UserBean bean) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement(
				"update st_user set first_name = ?, last_name = ?, login = ?, password = ?, dob = ?, mobile_no = ? role_id = ?, gender = ? created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		
		psmt.setString(1, bean.getFirstName());
		psmt.setString(2, bean.getLastName());
		psmt.setString(3, bean.getLogin());
		psmt.setString(4, bean.getPassword());
		psmt.setTimestamp(5, new Timestamp(bean.getDob().getTime()));
		psmt.setString(6, bean.getMobileNo());
		psmt.setLong(7, bean.getRoleId());
		psmt.setString(8, bean.getGender());
		psmt.setString(9, bean.getCreatedBy());
		psmt.setString(10, bean.getModifiedBy());
		psmt.setTimestamp(11, bean.getCreatedDateTime());
		psmt.setTimestamp(12, bean.getModifiedDateTime());
		psmt.setLong(13, nextPk());

		int i = psmt.executeUpdate();

	}

	public void delete(int id) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("delete from st_user where id = ?");

		psmt.setInt(1, id);

		int i = psmt.executeUpdate();

	}

	public UserBean findByPk(int id) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select * from st_user where id = ?");
		psmt.setInt(1, id);

		UserBean bean = null;
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getTimestamp(6));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDateTime(rs.getTimestamp(12));
			bean.setModifiedDateTime(rs.getTimestamp(13));
		}
		return bean;
	}
	
	public UserBean findByName(String name) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select * from st_user where name = ?");
		psmt.setString(1, name);

		UserBean bean = null;
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getTimestamp(6));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDateTime(rs.getTimestamp(12));
			bean.setModifiedDateTime(rs.getTimestamp(13));
		}
		return bean;
	}
	
	public List search(UserBean bean, int pageNo, int pageSize) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_user where 1=1");

		if (bean != null) {

		}
		if (bean != null) {
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and firstName like '" + bean.getFirstName() + "%'");
			}

			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and lastName like '" + bean.getLastName() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("sql" + sql.toString());

		PreparedStatement psmt = con.prepareStatement(sql.toString());

		List list = new ArrayList();
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getTimestamp(6));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDateTime(rs.getTimestamp(12));
			bean.setModifiedDateTime(rs.getTimestamp(13));
			list.add(bean);
		}

		return list;
	}
	
	
	public UserBean findByLogin(String login) throws Exception {

		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ?");

		pstmt.setString(1, login);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDateTime(rs.getTimestamp(12));
			bean.setModifiedDateTime(rs.getTimestamp(13));
		}
		JDBCDataSourceRb.closeConnection(conn);
		return bean;
	}
	
	public UserBean authenticate(String loginId, String password) throws Exception {

		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ? and password = ?");

		pstmt.setString(1, loginId);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getTimestamp(6));
			bean.setMobileNo(rs.getString(7));
			bean.setRoleId(rs.getLong(8));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getString(10));
			bean.setModifiedBy(rs.getString(11));
			bean.setCreatedDateTime(rs.getTimestamp(12));
			bean.setModifiedDateTime(rs.getTimestamp(13));
		}

		JDBCDataSourceRb.closeConnection(conn);

		return bean;
	}
}
