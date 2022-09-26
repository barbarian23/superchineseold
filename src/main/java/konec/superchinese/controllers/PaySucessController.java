package konec.superchinese.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import konec.superchinese.commons.MessageUtils;
import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import konec.superchinese.models.vippackages.PaymentModel;
import konec.superchinese.models.vippackages.VipStoreInformoder;
import konec.superchinese.services.logs.LogService;
import konec.superchinese.services.vippackages.VipPackagesService;
import konec.superchinese.services.zalopay.ZaloPayService;

@Controller
public class PaySucessController implements ScreenNameInterface, MessageUtils {

	public static final String SUPER_SC_PACKAGE = "sc";
	public static final String SUPER_ST_PACKAGE = "st";
	@Value("${superchinese.discount}")
	private String discount;

	@Value("${superchinese.date.discount}")
	private String Datediscount;

	@Value("${spcApiVipPackage}")
	public String spcApiVipPackage;
	
	@Autowired
	ZaloPayService zaloPayService;

	@Autowired
	VipPackagesService vipPackagesService;
	
	@Autowired
	LogService logService;

	@GetMapping(value = "/trang-thai-thanh-toan")
	public String action(ModelMap modelMap, HttpServletRequest request, HttpServletResponse respons,
			HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam(required = true, defaultValue = "") String orderId) throws Exception {
		VipStoreInformoder vi = (VipStoreInformoder) session.getAttribute("orderStore");
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		PaymentModel pm = new PaymentModel();

		String token = "";
		if (zaloLoginInfo != null) {
			token = zaloLoginInfo.getAccesstoken();
			pm = zaloPayService.getStatusOrderAppota(token, orderId, pm);
		} else {
			pm.setStatus(false);
			pm.setMessage(ERROR_FAILED_PAYMENT);
		}
		
		modelMap.put("paymentModel", pm);
		session.removeAttribute("orderStore");
		return PAYSUCCESS;
	}
}