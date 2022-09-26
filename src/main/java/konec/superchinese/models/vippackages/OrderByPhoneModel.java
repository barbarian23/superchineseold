package konec.superchinese.models.vippackages;

import java.util.regex.Pattern;

public class OrderByPhoneModel {
	public static final String regexPhone = "^[0-9]{10}$";
	
	public static final String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	public String scCode;
	public String stCode;
	public String phoneNumber;
	public String emailOa;
	public boolean hasError;
	public boolean activeSuccess;
	public String message;
	public String getEmailOa() {
		return emailOa;
	}
	public void setEmailOa(String emailOa) {
		this.emailOa = emailOa;
	}
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getScCode() {
		return scCode;
	}
	public void setScCode(String scCode) {
		this.scCode = scCode;
	}
	public String getStCode() {
		return stCode;
	}
	public void setStCode(String stCode) {
		this.stCode = stCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean validate() {
		if (this.phoneNumber == null || !patternMatches(this.phoneNumber, regexPhone)) {
			this.setHasError(true);
			this.setMessage("Số điện thoại không đúng!");
			return false;
		}
		if (this.emailOa == null || !patternMatches(this.emailOa, regexEmail)) {
			this.setHasError(true);
			this.setMessage("Email không đúng!");
			return false;
		}
		this.setHasError(false);
		return true;
	}
	public boolean patternMatches(String param, String regexPattern) {
		return Pattern.compile(regexPattern).matcher(param).matches();
	}
}
