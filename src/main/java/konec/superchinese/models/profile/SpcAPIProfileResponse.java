package konec.superchinese.models.profile;

import java.util.List;

import konec.superchinese.commons.SpcApiResponse;

public class SpcAPIProfileResponse extends SpcApiResponse {
	public List<SpcAPIPackActiveInfo> active_packages;
	public SpcAPIUserInfo user_info;
	public List<SpcAPIPackActiveInfo> getActive_packages() {
		return active_packages;
	}
	public void setActive_packages(List<SpcAPIPackActiveInfo> active_packages) {
		this.active_packages = active_packages;
	}
	public SpcAPIUserInfo getUser_info() {
		return user_info;
	}
	public void setUser_info(SpcAPIUserInfo user_info) {
		this.user_info = user_info;
	}
	
}
