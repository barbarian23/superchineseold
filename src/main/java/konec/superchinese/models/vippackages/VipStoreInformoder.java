package konec.superchinese.models.vippackages;

public class VipStoreInformoder {
	public String packageCode_sc;
	public String packageCode_st;

	public Integer sellPrice_sc;
	public Integer originalPrice_sc;

	public Integer sellPrice_st;
	public Integer originalPrice_st;

	public boolean vatStatus;
	public Integer vatPrice;
	public Integer totalPrice;
	public Integer totalPriceVat;

	public boolean discountStatus;
	public String discountCode;
	public Integer discountValue;

	public String sc_account;
	public String st_account;

	public String getSc_account() {
		return sc_account;
	}

	public void setSc_account(String sc_account) {
		this.sc_account = sc_account;
	}

	public String getSt_account() {
		return st_account;
	}

	public void setSt_account(String st_account) {
		this.st_account = st_account;
	}

	public String getPackageCode_sc() {
		return packageCode_sc;
	}

	public void setPackageCode_sc(String packageCode_sc) {
		this.packageCode_sc = packageCode_sc;
	}

	public String getPackageCode_st() {
		return packageCode_st;
	}

	public void setPackageCode_st(String packageCode_st) {
		this.packageCode_st = packageCode_st;
	}

	public Integer getSellPrice_sc() {
		return sellPrice_sc;
	}

	public void setSellPrice_sc(Integer sellPrice_sc) {
		this.sellPrice_sc = sellPrice_sc;
	}

	public Integer getOriginalPrice_sc() {
		return originalPrice_sc;
	}

	public void setOriginalPrice_sc(Integer originalPrice_sc) {
		this.originalPrice_sc = originalPrice_sc;
	}

	public Integer getSellPrice_st() {
		return sellPrice_st;
	}

	public void setSellPrice_st(Integer sellPrice_st) {
		this.sellPrice_st = sellPrice_st;
	}

	public Integer getOriginalPrice_st() {
		return originalPrice_st;
	}

	public void setOriginalPrice_st(Integer originalPrice_st) {
		this.originalPrice_st = originalPrice_st;
	}

	public boolean isVatStatus() {
		return vatStatus;
	}

	public void setVatStatus(boolean vatStatus) {
		this.vatStatus = vatStatus;
	}

	public Integer getVatPrice() {
		return vatPrice;
	}

	public void setVatPrice(Integer vatPrice) {
		this.vatPrice = vatPrice;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotalPriceVat() {
		return totalPriceVat;
	}

	public void setTotalPriceVat(Integer totalPriceVat) {
		this.totalPriceVat = totalPriceVat;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public boolean isDiscountStatus() {
		return discountStatus;
	}

	public void setDiscountStatus(boolean discountStatus) {
		this.discountStatus = discountStatus;
	}

	public Integer getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Integer discountValue) {
		this.discountValue = discountValue;
	}
}
