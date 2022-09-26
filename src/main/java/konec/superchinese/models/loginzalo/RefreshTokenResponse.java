package konec.superchinese.models.loginzalo;

public class RefreshTokenResponse {
	public String access_token;
	public String refresh_token;
	public String expires_in;
	
	public RefreshTokenResponse(String access_token, String refresh_token, String expires_in) {
		super();
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.expires_in = expires_in;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
}
