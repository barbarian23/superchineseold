package konec.superchinese.models.vippackages;

public class VipstoreSale {
	public Integer code;
	public Integer status;
	String error_message = "";
	VipstoreSaleData data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public VipstoreSaleData getData() {
		return data;
	}
	public void setData(VipstoreSaleData data) {
		this.data = data;
	}
	public VipstoreSale(Integer code, Integer status, String error_message, VipstoreSaleData data) {
		super();
		this.code = code;
		this.status = status;
		this.error_message = error_message;
		this.data = data;
	}
}
