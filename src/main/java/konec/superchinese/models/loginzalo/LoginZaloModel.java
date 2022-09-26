package konec.superchinese.models.loginzalo;

public class LoginZaloModel {
	public String redirect_uri;
	public String state;
	public String authorization_code;
	public String code_verifier;
	
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAuthorization_code() {
		return authorization_code;
	}
	public void setAuthorization_code(String authorization_code) {
		this.authorization_code = authorization_code;
	}
	public String getCode_verifier() {
		return code_verifier;
	}
	public void setCode_verifier(String code_verifier) {
		this.code_verifier = code_verifier;
	}
	
}
