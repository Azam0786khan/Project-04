package in.co.rays.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.util.JdbcDataSourceByRb;

public class RoleModel {

	public Integer nextPk() throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select max(id) from st_role");

		int pk = 0;
		ResultSet rs = psmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;
	}

	public void add(RoleBean bean) throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("insert into st_role values(?,?,?,?,?,?,?)");

		psmt.setInt(1, nextPk());
		psmt.setString(2, bean.getName());
		psmt.setString(3, bean.getDescription());
		psmt.setString(4, bean.getCreatedBy());
		psmt.setString(5, bean.getModifiedBy());
		psmt.setTimestamp(6, bean.getCreatedDateTime());
		psmt.setTimestamp(7, bean.getModifiedDateTime());

		int i = psmt.executeUpdate();
		System.out.println(" Data inserted : " + i);
	}

	public void update(RoleBean bean) throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		PreparedStatement psmt = con.prepareStatement(
				"update st_role set name = ?, description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime=?, where id = ?");

		psmt.setString(1, bean.getName());
		psmt.setString(2, bean.getDescription());
		psmt.setString(3, bean.getCreatedBy());
		psmt.setString(4, bean.getModifiedBy());
		psmt.setTimestamp(5, bean.getCreatedDateTime());
		psmt.setTimestamp(6, bean.getModifiedDateTime());
		psmt.setInt(7, nextPk());

		int i = psmt.executeUpdate();
		System.out.println(" Data updated : " + i);

	}

	public void delete(int id) throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("delete from st_role where id = ?");

		psmt.setInt(1, id);

		int i = psmt.executeUpdate();
		System.out.println(" Data deleted : " + i);

	}

	public RoleBean findByPk(int id) throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select * from st_role where id = ?");
		psmt.setInt(1, id);

		RoleBean bean = null;
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new RoleBean();
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDateTime(rs.getTimestamp(6));
			bean.setModifiedDateTime(rs.getTimestamp(7));
		}
		return bean;
	}

	public List search(RoleBean bean, int pageNo, int pageSize) throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_role where 1=1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and first_name like '" + bean.getName() + "%'");
			}

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and last_name like '" + bean.getDescription() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		PreparedStatement psmt = con.prepareStatement(sql.toString());

		List list = new ArrayList();
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new RoleBean();
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedBy(rs.getString(4));
			bean.setModifiedBy(rs.getString(5));
			bean.setCreatedDateTime(rs.getTimestamp(6));
			bean.setModifiedDateTime(rs.getTimestamp(7));
			list.add(bean);
		}

		return list;
	}

}
