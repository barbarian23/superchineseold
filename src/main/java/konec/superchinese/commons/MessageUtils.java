package konec.superchinese.commons;

public interface MessageUtils {
	public static final String ERROR_INVALID_CAPTCHA = "Mã captcha không hợp lê!";
	public static final String ERROR_NEED_LOGIN = "Bạn cần đăng nhập zalo để có thể thanh toán. <a href='javascript:void(0)' onclick=\"loginZalo()\" style=\"color: #2bab67;text-decoration: underline!important;\">Click vào đây</a> để đăng nhập";
	public static final String ERROR_NEED_LOGIN_FOR_ORDER = "Bạn cần đăng nhập zalo để đặt hàng";
	public static final String ERROR_NEED_LOGIN_FOR_ADVISE = "Bạn cần đăng nhập zalo để được tư vấn.";
	public static final String ERROR_ORDER_FAILUED = "Đặt hàng thất bại";
	public static final String ERROR_ORDER_NEED_FOLLOW_OA = "Bạn cần phải follow ZaloOA để đặt hàng, ";
	public static final String ERROR_NEED_FOLLOW_OA = "Bạn cần phải follow ZaloOA để được tư vấn. ";
	public static final String ERROR_SYSTEM = "Lỗi hệ thống, vui lòng thử lại sau ít phút!";
	public static final String ERROR_NOT_ENOUGH_MONEY = "Ví zalopay của bạn hiện không có đủ số dư để thanh toán!";
	public static final String ERROR_EXPIRE_ZALO = "Ví zalopay của bạn hiện không có đủ số dư để thanh toán!";
	public static final String ERROR_OUT_OF_DATE_KYC = "Giao dịch thất bại.Bạn đã sử dụng quá hạn mức giới hạn theo KYC!";
	public static final String ERROR_PACKAGES_CANCELED = "Gói cước bạn chọn không tồn tại hoặc đã bị huy, vui lòng chọn lại gói cước để thanh toán!";
	public static final String ERROR_NEED_CONNECT_ZALO_WALLET = "Bạn cần liên kết ví zalopay để thanh toán!";
	public static final String ERROR_FAILED_PAYMENT = "Thanh toán thất bại!";
	public static final String SUCCESS_PAYMENT_SUCCESS = "Thanh toán thành công, để sử dụng vui lòng truy câp vào <a href='/kich-hoat-vip' class='profile-url-class'>Trang cá nhân</a> để kích hoạt gói!";
	public static final String WAIT_FOR_PAYMENT = "Đơn thanh toán đang được xử lý, vui lòng kiểm tra lại sau it phút!";

	public static final String ERROR_PACKAGE_NOT_VALID = "Gói cước không hợp lệ!";
	public static final String HAS_PACKAGE = "Tài khoản đã sở hữu gói cước ";
	public static final String HAS_NOT_PACKAGE = "Tài khoản chưa sở hữu gói cước ";
	public static final String ERROR_ACCOUNT_NOT_VALID = "Tài khoản phải là số điện thoại hoặc email! ";

	public static final String SUCCESS_LICENSE_ACTIVE_SUCCESS = "Kích hoạt thành công!";
	public static final String SUCCESS_ORDER_SUCCESS = "Đặt hàng thành công!";
	public static final String SUCCESS_OA_CHEAP = "Đăng ký nhận tư vấn giá rẻ thành công, vui lòng check inbox zalo!";
	public static final String ERROR_LICENSE_NOT_VALID = "Mã kích hoạt không hợp lệ!";
        public static final String ERROR_LICENSE_NOT_EXIST = "Mã kích hoạt không tồn tại!";
	public static final String ERROR_LICENSE_HAS_ACTIVED = "Mã kích hoạt đã được đã được sử dụng!";
	public static final String ERROR_LICENSE_DONOT_EXIST_PACKAGE = "Gói hàng mà bạn muốn kích hoạt hiện không còn tồn tại!";
	public static final String ERROR_LICENSE_DONOT_MATCH_ACCOUNT = "Mã kích hoạt không khớp với Email/Số điện thoại!";
	public static final String ERROR_LICENSE_NOT_EXPIRE_YET = "Gói hàng bạn muốn kích hoạt hiện vẫn còn thời hạn sử dụng!";
	public static final String ERROR_ORDER_NOT_EXPIRE_YET = "Gói hàng bạn muốn mua hiện vẫn còn thời hạn sử dụng!";
	public static final String ERROR_PACK_ORDER_EXIST = "Bạn đã sở hữu gói hàng này, vui lòng kích hoạt để sử dụng!";
	public static final String ERROR_LICENSE_SERVER_BUSY = "Server đang bận, vui lòng kích hoạt mã sau ít phút!";
	
	public static final String ERROR_VALIDATE_ACCOUNT_NOT_VALID = "Email/Số điện thoại không hợp lệ!";
	public static final String ERROR_ACTIVE_FAILED = "Kích hoạt thất bại";
	public static final String ERROR_ACTIVE_TIME_OUT = "Chưa đăng nhập hoặc đã hết phiên làm việc!";
	
	public static final String ERROR_OA_CHEAP = "Đăng ký thất bại!";
}
