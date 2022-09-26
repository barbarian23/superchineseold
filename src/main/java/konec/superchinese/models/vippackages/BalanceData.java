package konec.superchinese.models.vippackages;

public class BalanceData {
	public String bank_code;
	public boolean payable;
	public Integer channel;
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public boolean isPayable() {
		return payable;
	}
	public void setPayable(boolean payable) {
		this.payable = payable;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
}
