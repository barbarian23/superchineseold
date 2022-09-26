package konec.superchinese.models.vippackages;

public class ExistAccountModel {
	public Integer code;
	public String message = "";
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ExistAccountModel(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
}
