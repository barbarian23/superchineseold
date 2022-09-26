package konec.superchinese.models.loginzalo;

public class ZaloLoginInfo {
	public String accesstoken;
	public String ZaloName;
	public String zaloPhone;
	public String verifierCode;
	public String userId;
	public String urlBefore;
	public String loginFlag;
	public boolean agencyCallBack = false;
	public boolean loginCallBack  = false;
	public boolean paymentCallBack = false;
	public boolean adviseCallBack = false;
	public boolean orderCallBack = false;
	
	public boolean isOrderCallBack() {
		return orderCallBack;
	}
	public void setOrderCallBack(boolean orderCallBack) {
		this.orderCallBack = orderCallBack;
	}
	public boolean isAdviseCallBack() {
		return adviseCallBack;
	}
	public void setAdviseCallBack(boolean adviseCallBack) {
		this.adviseCallBack = adviseCallBack;
	}
	public boolean isAgencyCallBack() {
		return agencyCallBack;
	}
	public void setAgencyCallBack(boolean agencyCallBack) {
		this.agencyCallBack = agencyCallBack;
	}
	public boolean isLoginCallBack() {
		return loginCallBack;
	}
	public void setLoginCallBack(boolean loginCallBack) {
		this.loginCallBack = loginCallBack;
	}
	public boolean isPaymentCallBack() {
		return paymentCallBack;
	}
	public void setPaymentCallBack(boolean paymentCallBack) {
		this.paymentCallBack = paymentCallBack;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public String getZaloName() {
		return ZaloName;
	}
	public void setZaloName(String zaloName) {
		ZaloName = zaloName;
	}
	public String getZaloPhone() {
		return zaloPhone;
	}
	public void setZaloPhone(String zaloPhone) {
		this.zaloPhone = zaloPhone;
	}
	public String getVerifierCode() {
		return verifierCode;
	}
	public void setVerifierCode(String verifierCode) {
		this.verifierCode = verifierCode;
	}
	public String getLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUrlBefore() {
		return urlBefore;
	}
	public void setUrlBefore(String urlBefore) {
		this.urlBefore = urlBefore;
	}
}
