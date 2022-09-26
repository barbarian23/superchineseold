package konec.superchinese.models.loginzalo;

public class AuthorCodeRequest {
	public String code;
	public String app_id;
	public String grant_type;
	public String code_verifier;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	public String getCode_verifier() {
		return code_verifier;
	}
	public void setCode_verifier(String code_verifier) {
		this.code_verifier = code_verifier;
	}
	
}
