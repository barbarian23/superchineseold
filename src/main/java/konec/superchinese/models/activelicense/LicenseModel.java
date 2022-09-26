package konec.superchinese.models.activelicense;

public class LicenseModel {
	public String email;
	public String codeAc;
	public boolean hasError;
	
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodeAc() {
		return codeAc;
	}
	public void setCodeAc(String codeAc) {
		this.codeAc = codeAc;
	}
}
