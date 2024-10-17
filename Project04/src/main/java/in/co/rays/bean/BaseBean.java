package in.co.rays.bean;

import java.sql.Timestamp;

public abstract class BaseBean implements DropdownListBean {

	protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp CreatedDateTime;
	protected Timestamp ModifiedDateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDateTime() {
		return CreatedDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDatetime) {
		this.CreatedDateTime = createdDatetime;
	}

	public Timestamp getModifiedDateTime() {
		return ModifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDatetime) {
		this.ModifiedDateTime = modifiedDatetime;
	}
}
