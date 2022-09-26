package konec.superchinese.models.activelicense;

public class SpcAPILicenseRequest {
	public String access_token;
	public String license_code;
	public String account;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getLicense_code() {
		return license_code;
	}
	public void setLicense_code(String license_code) {
		this.license_code = license_code;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
