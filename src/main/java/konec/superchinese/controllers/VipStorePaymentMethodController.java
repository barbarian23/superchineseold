package konec.superchinese.controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import konec.superchinese.commons.MessageUtils;
import konec.superchinese.commons.ReturnCodeConst;
import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import konec.superchinese.models.vippackages.BalanceData;
import konec.superchinese.models.vippackages.OrderByPhoneData;
import konec.superchinese.models.vippackages.OrderByPhoneModel;
import konec.superchinese.models.vippackages.PaymentModel;
import konec.superchinese.models.vippackages.VipStoreInformoder;
import konec.superchinese.models.vippackages.VipstoreSaleData;
import konec.superchinese.services.captcha.VerifyCapchaService;
import konec.superchinese.services.vippackages.VipPackagesService;
import konec.superchinese.services.vippackages.VipStoreInformoderService;
import konec.superchinese.services.zalopay.ZaloOrderByPhoneService;
import konec.superchinese.services.zalopay.ZaloPayService;

@Controller
public class VipStorePaymentMethodController implements ScreenNameInterface, ReturnCodeConst, MessageUtils {
	@Autowired
	ZaloPayService zaloPayService;

	@Autowired
	ZaloOrderByPhoneService zaloOrderService;

	@Autowired
	VipPackagesService vipPackagesService;

	@Autowired
	VipStoreInformoderService vipStoreInformoderService;

	@Autowired
	VerifyCapchaService verifyCapchaService;

	@RequestMapping(value = "/thanh-toan-goi-vip", method = { RequestMethod.GET, RequestMethod.POST })
	public String action(@ModelAttribute(value = "vipStoreInformoder") VipStoreInformoder vipStoreInformoder,
			@RequestParam(name = "binding_id", required = false, defaultValue = "") String binding_id,
			ModelMap modelMap, HttpServletRequest request, HttpServletResponse respons, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		String beforeUrl = request.getHeader("Referer");
		
		VipStoreInformoder vi = (VipStoreInformoder) session.getAttribute("orderStore");
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");

		PaymentModel pm = new PaymentModel();
		String token = "";
		if (zaloLoginInfo != null) {
			modelMap.put("zaloName", zaloLoginInfo.getZaloName());
			token = zaloLoginInfo.getAccesstoken();
//			List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//			modelMap.put("orderByPhoneList", listOrder);
			if (!binding_id.isEmpty()) {
				pm = zaloPayService.getStatusOrder(token, binding_id, pm);
				request.setAttribute("paymentModel", pm);
				return "forward:/thanh-toan-thanh-cong";
			}
		}
		if(beforeUrl == null || !beforeUrl.contains("xac-nhan-thanh-toan-goi-vip-superchinese")) {
			VipStoreInformoder vis = (VipStoreInformoder) session.getAttribute("orderStoreFinal");
			modelMap.put("vipStoreInformoder", vis);
			return VIPSTOREPAYMENTMETHOD;
		}
		String packCode = "";
		String voucher_code = "";
		String discount = "";
		String finalPrice = "";
		if (!StringUtils.isEmpty(vi.getPackageCode_sc())) {
			packCode = vi.getPackageCode_sc();
		} else {
			packCode = vi.getPackageCode_st();
		}
		VipstoreSaleData vipstoreSaleData = vipStoreInformoderService.checkVoucher(packCode,
				vipStoreInformoder.getDiscountCode());
		if (vipstoreSaleData.isStatus()) {
			voucher_code = vipStoreInformoder.getDiscountCode();
			discount = vipstoreSaleData.getDiscount();
			finalPrice = vipstoreSaleData.getFinalPrice();
		}

		vi.setVatStatus(vipStoreInformoder.isVatStatus());
		vi.setSc_account(vipStoreInformoder.getSc_account());
		vi.setSt_account(vipStoreInformoder.getSt_account());
		if (voucher_code != "") {
			vi.setDiscountCode(vipStoreInformoder.getDiscountCode());
			vi.setDiscountValue(Integer.valueOf(discount));
			vi.setTotalPrice(Integer.valueOf(finalPrice));
			vi.setVatPrice((int) (vi.getTotalPrice() * 0.1));
			vi.setTotalPriceVat((int) (vi.getTotalPrice() * 1.1));
		} else {
			vi.setDiscountCode("");
		}
		session.setAttribute("orderStoreFinal", vi);
		modelMap.put("vipStoreInformoder", vi);
		return VIPSTOREPAYMENTMETHOD;
	}

	@GetMapping(value = "/tien-trinh-thanh-toan-qua-zalo-pay")
	public String createOrder(ModelMap modelMap, HttpServletRequest request, HttpServletResponse respons,
			HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		PaymentModel pm = new PaymentModel();
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		zaloLoginInfo.setPaymentCallBack(false);
		session.setAttribute("zaloLoginInfo", zaloLoginInfo);
		VipStoreInformoder vi = (VipStoreInformoder) session.getAttribute("orderStoreFinal");
		String token = "";
		String phone = "";
		if (zaloLoginInfo != null) {
			token = zaloLoginInfo.getAccesstoken();
			phone = zaloLoginInfo.getZaloPhone();
		}
		
		Cookie[] cookies = request.getCookies();
		String clickId = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("adpiaClickid")) {
					clickId = cookie.getValue();
				}
			}
		}

		if (StringUtils.isEmpty(token)) {
			return "redirect:/dang-nhap";
//			// can dang nhap de thanh toan
//			log.put("queryBalance_return_code", "empty");
//			LogUtils.getInstance().writeLog("tien-trinh-thanh-toan-qua-zalo-pay", log);
//			List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//			modelMap.put("orderByPhoneList", listOrder);
//			pm.setStatus(false);
//			pm.setMessage(ERROR_NEED_LOGIN);
//			modelMap.put("paymentModel", pm);
//			modelMap.put("vipStoreInformoder", vi);
//			return VIPSTOREPAYMENTMETHOD;
		} else {
			// check dieu kien thanh toan:
			HashMap<String, String> hm = new HashMap<String, String>();
			String packCode = "";
			if (!StringUtils.isEmpty(vi.getPackageCode_sc())) {
				packCode = vi.getPackageCode_sc();
				hm = zaloPayService.queryBalance(token, vi.getPackageCode_sc());
			} else if (!StringUtils.isEmpty(vi.getPackageCode_st())) {
				packCode = vi.getPackageCode_st();
				hm = zaloPayService.queryBalance(token, vi.getPackageCode_st());
			} else {
				// chưa chọn gói hàng nào
				pm.setStatus(false);
				pm.setMessage(ERROR_SYSTEM);
//				List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//				modelMap.put("orderByPhoneList", listOrder);
				modelMap.put("paymentModel", pm);
				modelMap.put("vipStoreInformoder", vi);
				return VIPSTOREPAYMENTMETHOD;
			}

			String voucher_code = "";
			VipstoreSaleData vipstoreSaleData = vipStoreInformoderService.checkVoucher(packCode, vi.getDiscountCode());
			if (vipstoreSaleData.isStatus()) {
				voucher_code = vi.getDiscountCode();
			}

			if (hm.size() > 0) {
				String returnCode = String.valueOf(hm.get("return_code"));
				String subReturnCode = String.valueOf(hm.get("sub_return_code"));
				if ("1".equals(returnCode)) {
					// success
					ObjectMapper mapper = new ObjectMapper();
					Object data = hm.get("data");
					ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					String json = ow.writeValueAsString(data);
					BalanceData[] balanceArray = mapper.readValue(json, BalanceData[].class);
					BalanceData Balance = balanceArray[0];
					if (!Balance.isPayable()) {
						pm.setStatus(false);
						pm.setMessage(ERROR_NOT_ENOUGH_MONEY);
//						List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//						modelMap.put("orderByPhoneList", listOrder);
						modelMap.put("paymentModel", pm);
						modelMap.put("vipStoreInformoder", vi);
						return VIPSTOREPAYMENTMETHOD;
					} else {
						String returnUrl = "";
//						List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//						modelMap.put("orderByPhoneList", listOrder);
						if (!StringUtils.isEmpty(vi.getPackageCode_sc())) {
							HashMap<String, String> hmc = zaloPayService.createOrder(token, vi.getPackageCode_sc(),
									vi.getSc_account(), voucher_code, clickId);
							if (hmc.size() > 0) {
								returnUrl = zaloPayService.casePayment(hmc, pm, session, request);
							}
						} else if (!StringUtils.isEmpty(vi.getPackageCode_st())) {
							HashMap<String, String> hmt = zaloPayService.createOrder(token, vi.getPackageCode_st(),
									vi.getSt_account(), voucher_code, clickId);
							if (hmt.size() > 0) {
								returnUrl = zaloPayService.casePayment(hmt, pm, session, request);
							}
						}
						modelMap.put("paymentModel", pm);
						modelMap.put("vipStoreInformoder", vi);
						return returnUrl;
					}
				} else if ("1301".equals(returnCode) || ("2".equals(returnCode) && "-1002".equals(subReturnCode))
						|| ("2".equals(returnCode) && "-1000".equals(subReturnCode))) {
					// KH đã tạm ngưng hoặc hủy liên kết hoặc liên kết không đúng
					HashMap<String, String> hmAgree = new HashMap<String, String>();
					if (!StringUtils.isEmpty(vi.getPackageCode_sc())) {
						hmAgree = zaloPayService.agreementBind(token, vi.getPackageCode_sc(), vi.getSc_account(),
								voucher_code, clickId);

					} else if (!StringUtils.isEmpty(vi.getPackageCode_st())) {
						hmAgree = zaloPayService.agreementBind(token, vi.getPackageCode_st(), vi.getSt_account(),
								voucher_code, clickId);
					}
					if (hmAgree.size() > 0) {
						String url = hmAgree.get("binding_qr_link");
						return "redirect:" + url;
					} else {
						// lien ket vi zalopay loi khong xac dinh
						pm.setStatus(false);
						pm.setMessage(ERROR_SYSTEM);
//						List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//						modelMap.put("orderByPhoneList", listOrder);
						modelMap.put("paymentModel", pm);
						modelMap.put("vipStoreInformoder", vi);
						return VIPSTOREPAYMENTMETHOD;
					}

				} else if ("2".equals(returnCode) && "-1800".equals(subReturnCode)) {
					// KH đã sử dụng quá hạn mức giới hạn theo KYC, popup thông báo đến KH
					pm.setStatus(false);
					pm.setMessage(ERROR_OUT_OF_DATE_KYC);
//					List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//					modelMap.put("orderByPhoneList", listOrder);
					modelMap.put("paymentModel", pm);
					modelMap.put("vipStoreInformoder", vi);
					return VIPSTOREPAYMENTMETHOD;
				} else {
					pm.setStatus(false);
					pm.setMessage(ERROR_SYSTEM);
//					List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//					modelMap.put("orderByPhoneList", listOrder);
					modelMap.put("paymentModel", pm);
					modelMap.put("vipStoreInformoder", vi);
					return VIPSTOREPAYMENTMETHOD;
				}
			} else {
				// loi he thong
				pm.setStatus(false);
				pm.setMessage(ERROR_SYSTEM);
//				List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//				modelMap.put("orderByPhoneList", listOrder);
				modelMap.put("paymentModel", pm);
				modelMap.put("vipStoreInformoder", vi);
				return VIPSTOREPAYMENTMETHOD;
			}
		}

	}

	@GetMapping(value = "/tien-trinh-thanh-toan-qua-appota-pay")
	public String createOrderAppota(ModelMap modelMap, HttpServletRequest request, HttpServletResponse respons,
			HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		PaymentModel pm = new PaymentModel();
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		zaloLoginInfo.setPaymentCallBack(false);
		session.setAttribute("ZaloLoginInfo", zaloLoginInfo);
		VipStoreInformoder vi = (VipStoreInformoder) session.getAttribute("orderStoreFinal");
		String token = "";
		String phone = "";
		if (zaloLoginInfo != null) {
			token = zaloLoginInfo.getAccesstoken();
			phone = zaloLoginInfo.getZaloPhone();
		}
		
		Cookie[] cookies = request.getCookies();
		String clickId = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("adpiaClickid")) {
					clickId = cookie.getValue();
				}
			}
		}

		if (StringUtils.isEmpty(token)) {
			return "redirect:/dang-nhap";
			// can dang nhap de thanh toan
//			log.put("queryBalance_return_code", "empty");
//			LogUtils.getInstance().writeLog("tien-trinh-thanh-toan-qua-appota-pay", log);
//			List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//			modelMap.put("orderByPhoneList", listOrder);
//			pm.setStatus(false);
//			pm.setMessage(ERROR_NEED_LOGIN);
//			modelMap.put("paymentModel", pm);
//			modelMap.put("vipStoreInformoder", vi);
//			return VIPSTOREPAYMENTMETHOD;
		} else {
//			List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//			modelMap.put("orderByPhoneList", listOrder);
			// check dieu kien thanh toan:
			HashMap<String, String> hm = new HashMap<String, String>();
			String packCode = "";
			String account = "";
			if (!StringUtils.isEmpty(vi.getPackageCode_sc())) {
				packCode = vi.getPackageCode_sc();
				account = vi.getSc_account();
			} else if (!StringUtils.isEmpty(vi.getPackageCode_st())) {
				packCode = vi.getPackageCode_st();
				account = vi.getSt_account();
			} else {
				// chưa chọn gói hàng nào
				pm.setStatus(false);
				pm.setMessage(ERROR_SYSTEM);
				modelMap.put("paymentModel", pm);
				modelMap.put("vipStoreInformoder", vi);
				return VIPSTOREPAYMENTMETHOD;
			}

			String voucher_code = "";
			VipstoreSaleData vipstoreSaleData = vipStoreInformoderService.checkVoucher(packCode, vi.getDiscountCode());
			if (vipstoreSaleData.isStatus()) {
				voucher_code = vi.getDiscountCode();
			}

			String returnUrl = "";
			HashMap<String, String> hmc = zaloPayService.createOrderAppota(token, packCode, account, voucher_code, clickId);
			modelMap.put("paymentModel", pm);
			modelMap.put("vipStoreInformoder", vi);
			if (hmc.size() > 0) {
				returnUrl = zaloPayService.casePaymentAppota(hmc, pm, request);
				if(!returnUrl.isEmpty()) {
					return "redirect:" + returnUrl;
				}
				modelMap.put("paymentModel", pm);
				modelMap.put("vipStoreInformoder", vi);
				return VIPSTOREPAYMENTMETHOD;
			} else {
				pm.setStatus(false);
				pm.setMessage(ERROR_SYSTEM);
				modelMap.put("paymentModel", pm);
				modelMap.put("vipStoreInformoder", vi);
				return VIPSTOREPAYMENTMETHOD;
			}
		}

	}

	@RequestMapping(value = "/orderByPhone", method = { RequestMethod.GET, RequestMethod.POST })
	public String orderPhone(@ModelAttribute(value = "orderByPhoneModel") OrderByPhoneModel orderByPhoneModel,
			ModelMap modelMap, HttpServletRequest request, HttpServletResponse respons, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		VipStoreInformoder vi = (VipStoreInformoder) session.getAttribute("orderStoreFinal");
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		PaymentModel pm = new PaymentModel();
		String token = "";
		if (zaloLoginInfo != null) {
			modelMap.put("zaloName", zaloLoginInfo.getZaloName());
			token = zaloLoginInfo.getAccesstoken();
		}
		// Verify CAPTCHA
//		if (!verifyCapchaService.verifyCapcha(orderByPhoneModel.getCapcha())) {
//			LogUtils.getInstance().writeLog("orderByPhone", log);
//			orderByPhoneModel.setPhoneNumber("");
//			orderByPhoneModel.setEmailOa("");
//			orderByPhoneModel.setHasError(true);
//			orderByPhoneModel.setMessage("Mã captcha không đúng!");
//			List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//			modelMap.put("orderByPhoneList", listOrder);
//			modelMap.put("vipStoreInformoder", vi);
//			modelMap.put("orderByPhoneModel", orderByPhoneModel);
//			return VIPSTOREPAYMENTMETHOD;
//			
//		}
		if (StringUtils.isEmpty(token)) {
			return "redirect:/dang-nhap";
//			LogUtils.getInstance().writeLog("orderByPhone", log);
//			pm.setStatus(false);
//			pm.setMessage(ERROR_NEED_LOGIN_FOR_ORDER);
//			List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//			modelMap.put("orderByPhoneList", listOrder);
//			modelMap.put("paymentModel", pm);
//			modelMap.put("vipStoreInformoder", vi);
//			return VIPSTOREPAYMENTMETHOD;
		} else {
			String packageCode = "";
			if (!StringUtils.isEmpty(orderByPhoneModel.getScCode())) {
				packageCode = orderByPhoneModel.getScCode();
			} else if (!StringUtils.isEmpty(orderByPhoneModel.getStCode())) {
				packageCode = orderByPhoneModel.getStCode();
			} else {
				pm.setStatus(false);
				pm.setMessage(ERROR_SYSTEM);
//				List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//				modelMap.put("orderByPhoneList", listOrder);
				modelMap.put("paymentModel", pm);
				modelMap.put("vipStoreInformoder", vi);
				modelMap.put("orderByPhoneModel", orderByPhoneModel);
				return VIPSTOREPAYMENTMETHOD;
			}
			if (!orderByPhoneModel.validate()) {
//				List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//				modelMap.put("orderByPhoneList", listOrder);
				modelMap.put("vipStoreInformoder", vi);
				modelMap.put("orderByPhoneModel", orderByPhoneModel);
				return VIPSTOREPAYMENTMETHOD;
			}

			if (zaloOrderService.checkOAFollow(token)) {
				zaloOrderService.packOrder(token, orderByPhoneModel.getPhoneNumber(), orderByPhoneModel.getEmailOa(),
						packageCode, pm);
				orderByPhoneModel.setPhoneNumber("");
				orderByPhoneModel.setEmailOa("");
				modelMap.put("orderByPhoneModel", orderByPhoneModel);
			} else {
				return "redirect:/dang-nhap";
//				pm.setStatus(false);
//				pm.setMessage(ERROR_ORDER_NEED_FOLLOW_OA);
//				modelMap.put("followOa", "1");
			}
		}
//		List<OrderByPhoneData> listOrder = zaloOrderService.packOrderList(token);
//		modelMap.put("orderByPhoneList", listOrder);
		modelMap.put("paymentModel", pm);
		modelMap.put("vipStoreInformoder", vi);
		return VIPSTOREPAYMENTMETHOD;
	}
}