package konec.superchinese.models.profile;

import konec.superchinese.commons.SpcApiResponse;

public class SpcAPIHistoryInfo extends SpcApiResponse {
	public String code;
	public Integer paymentId;
	public Integer customerId;
	public Integer price;
	public String packageCode;
	public String appType;
	public String cycleText;
	public String createTime;
	public String status;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getPackageCode() {
		return packageCode;
	}
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getCycleText() {
		return cycleText;
	}
	public void setCycleText(String cycleText) {
		this.cycleText = cycleText;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
