package konec.superchinese.models.profile;

public class SpcAPIPackActiveInfo {
	public Integer id;
	public Integer customerId;
	public String packageCode;
	public String status;
	public String startTime;
	public String endTime;
	public String createTime;
	public Integer renewDay;
	public Integer renewCountInDay;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getPackageCode() {
		return packageCode;
	}
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getRenewDay() {
		return renewDay;
	}
	public void setRenewDay(Integer renewDay) {
		this.renewDay = renewDay;
	}
	public Integer getRenewCountInDay() {
		return renewCountInDay;
	}
	public void setRenewCountInDay(Integer renewCountInDay) {
		this.renewCountInDay = renewCountInDay;
	}
}
