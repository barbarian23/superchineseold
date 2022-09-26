package konec.superchinese.models.profile;

public class SpcAPIUserInfo {
	public Integer id;
	public String  phone;
	public String name;
	public String email;
	public String status;
	public String createTime;
	public String updateTime;
	public String zaloUserId;
	public Integer zaloBind;
	public String payToken;
	public String bindingId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getZaloUserId() {
		return zaloUserId;
	}
	public void setZaloUserId(String zaloUserId) {
		this.zaloUserId = zaloUserId;
	}
	public Integer getZaloBind() {
		return zaloBind;
	}
	public void setZaloBind(Integer zaloBind) {
		this.zaloBind = zaloBind;
	}
	public String getPayToken() {
		return payToken;
	}
	public void setPayToken(String payToken) {
		this.payToken = payToken;
	}
	public String getBindingId() {
		return bindingId;
	}
	public void setBindingId(String bindingId) {
		this.bindingId = bindingId;
	}
}
