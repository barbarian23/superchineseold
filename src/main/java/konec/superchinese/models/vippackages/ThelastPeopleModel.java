package konec.superchinese.models.vippackages;

public class ThelastPeopleModel {
	public String customerName;
	public String packageName;
	public String createTime;
	public String appName;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public ThelastPeopleModel(String customerName, String packageName, String createTime, String appName) {
		super();
		this.customerName = customerName;
		this.packageName = packageName;
		this.createTime = createTime;
		this.appName = appName;
	}
}
