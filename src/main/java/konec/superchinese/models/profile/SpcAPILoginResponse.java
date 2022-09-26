package konec.superchinese.models.profile;

import konec.superchinese.commons.SpcApiResponse;

public class SpcAPILoginResponse extends SpcApiResponse {
	public String new_access_token;
	public String new_refresh_token;
	public String getNew_access_token() {
		return new_access_token;
	}
	public void setNew_access_token(String new_access_token) {
		this.new_access_token = new_access_token;
	}
	public String getNew_refresh_token() {
		return new_refresh_token;
	}
	public void setNew_refresh_token(String new_refresh_token) {
		this.new_refresh_token = new_refresh_token;
	}
	
}
