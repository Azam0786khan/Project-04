package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.FacultyBean;
import in.co.rays.util.JDBCDataSourceRb;

public class FacultyModel {

	public Integer nextPk() throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select max(id) from st_faculty");

		int pk = 0;
		ResultSet rs = psmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;
	}

	public void add(FacultyBean bean) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("insert into st_faculty values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		psmt.setLong(1, nextPk());
		psmt.setString(2, bean.getFirstName());
		psmt.setString(3, bean.getLastName());
		psmt.setTimestamp(4, new Timestamp(bean.getDob().getTime()));
		psmt.setString(5, bean.getGender());
		psmt.setString(6, bean.getMobileNo());
		psmt.setString(7, bean.getEmail());
		psmt.setLong(8, bean.getCollegeId());
		psmt.setString(9, bean.getCollegeName());
		psmt.setLong(10, bean.getCourseId());
		psmt.setString(11, bean.getCourseName());
		psmt.setLong(12, bean.getSubjectId());
		psmt.setString(13, bean.getSubjectName());
		psmt.setString(14, bean.getCreatedBy());
		psmt.setString(15, bean.getModifiedBy());
		psmt.setTimestamp(16, bean.getCreatedDateTime());
		psmt.setTimestamp(17, bean.getModifiedDateTime());

		int i = psmt.executeUpdate();

	}

	public void update(FacultyBean bean) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement(
				"update st_faculty set first_name = ?, last_name = ?, dob = ?, gender = ?, mobile_no = ?, email = ?, colleg_id = ?, college_name = ?, course_id = ?, course_name = ?, subject_id = ?, subject_name = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		psmt.setString(1, bean.getFirstName());
		psmt.setString(2, bean.getLastName());
		psmt.setTimestamp(3, new Timestamp(bean.getDob().getTime()));
		psmt.setString(4, bean.getGender());
		psmt.setString(5, bean.getMobileNo());
		psmt.setString(6, bean.getEmail());
		psmt.setLong(7, bean.getCollegeId());
		psmt.setString(8, bean.getCollegeName());
		psmt.setLong(9, bean.getCourseId());
		psmt.setString(10, bean.getCourseName());
		psmt.setLong(11, bean.getSubjectId());
		psmt.setString(12, bean.getSubjectName());
		psmt.setString(13, bean.getCreatedBy());
		psmt.setString(14, bean.getModifiedBy());
		psmt.setTimestamp(15, bean.getCreatedDateTime());
		psmt.setTimestamp(16, bean.getModifiedDateTime());
		psmt.setLong(17, nextPk());

		int i = psmt.executeUpdate();

	}

	public void delete(int id) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("delete from st_faculty where id = ?");

		psmt.setInt(1, id);

		int i = psmt.executeUpdate();

	}

	public FacultyBean findByPk(int id) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select * from st_faculty where id = ?");
		psmt.setInt(1, id);

		FacultyBean bean = null;
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getTimestamp(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDateTime(rs.getTimestamp(16));
			bean.setModifiedDateTime(rs.getTimestamp(17));
		}
		return bean;
	}
	
	public FacultyBean findByName(String name) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select * from st_faculty where name = ?");
		psmt.setString(1, name);

		FacultyBean bean = null;
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getTimestamp(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDateTime(rs.getTimestamp(16));
			bean.setModifiedDateTime(rs.getTimestamp(17));
		}
		return bean;
	}
	
	public List search(FacultyBean bean, int pageNo, int pageSize) throws Exception {
		Connection con = JDBCDataSourceRb.getConnection();
		StringBuffer sql = new StringBuffer("select * from st_faculty where 1=1");

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
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getTimestamp(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDateTime(rs.getTimestamp(16));
			bean.setModifiedDateTime(rs.getTimestamp(17));
			list.add(bean);
		}

		return list;
	}
	
	public FacultyBean authenticate(String loginId, String password) throws Exception {

		Connection conn = JDBCDataSourceRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_faculty where login = ? and password = ?");

		pstmt.setString(1, loginId);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		FacultyBean bean = null;

		while (rs.next()) {
			bean = new FacultyBean();
			bean.setId(rs.getLong(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setDob(rs.getTimestamp(4));
			bean.setGender(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setEmail(rs.getString(7));
			bean.setCollegeId(rs.getLong(8));
			bean.setCollegeName(rs.getString(9));
			bean.setCourseId(rs.getLong(10));
			bean.setCourseName(rs.getString(11));
			bean.setSubjectId(rs.getLong(12));
			bean.setSubjectName(rs.getString(13));
			bean.setCreatedBy(rs.getString(14));
			bean.setModifiedBy(rs.getString(15));
			bean.setCreatedDateTime(rs.getTimestamp(16));
			bean.setModifiedDateTime(rs.getTimestamp(17));
		}

		JDBCDataSourceRb.closeConnection(conn);

		return bean;
	}
}
