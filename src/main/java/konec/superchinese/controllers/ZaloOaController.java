package konec.superchinese.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import konec.superchinese.commons.MessageUtils;
import konec.superchinese.commons.ReturnCodeConst;
import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.ZaloOa.ZaloOaModel;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import konec.superchinese.models.vippackages.VipPackageStore;
import konec.superchinese.services.ZaloOa.ZaloOaService;
import konec.superchinese.services.vippackages.VipPackagesService;
import konec.superchinese.services.zalopay.ZaloOrderByPhoneService;

@Controller
public class ZaloOaController implements ScreenNameInterface, ReturnCodeConst, MessageUtils {

	public static final String SUPER_SC_PACKAGE = "sc";
	public static final String SUPER_ST_PACKAGE = "st";
	@Autowired
	VipPackagesService vipPackagesService;

	@Autowired
	ZaloOrderByPhoneService zaloOrderService;

	@Autowired
	ZaloOaService zaloOaService;

	@PostMapping(value = "/tu-van-gia-re")
	public String edit(@ModelAttribute(value = "agencyModel") ZaloOaModel zaloOaModel, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse respons, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		zaloLoginInfo.setAdviseCallBack(false);
		session.setAttribute("ZaloLoginInfo", zaloLoginInfo);
		VipPackageStore vipPackageStore = (VipPackageStore) session.getAttribute("packageStore");
		String token = "";
		String pack = zaloOaModel.getPackChoise();
		String zaloName = "";
		if (zaloLoginInfo != null) {
			token = zaloLoginInfo.getAccesstoken();
			zaloName = zaloLoginInfo.getZaloName();
		}
		if (!zaloOrderService.checkOAFollow(token)) {
			return "redirect:https://zalo.me/4513138367536811082";
		} else {
			String message = zaloOaService.messageReply(pack, zaloName, vipPackageStore);
			String messageWeb = zaloOaService.messageReplyWeb(pack, zaloName, vipPackageStore);
			String returnMess = zaloOaService.sendMessage(token, message, vipPackageStore);
			if (_1.equals(returnMess)) {
				zaloOaModel.setMessage(messageWeb);
				modelMap.put("zaloOaModel", zaloOaModel);
				return PAYSUCCESSCHEAP;
			} else {
				zaloOaModel.setMessage(ERROR_OA_CHEAP);
				modelMap.put("zaloOaModel", zaloOaModel);
				return PAYSUCCESSCHEAP;
			}
		}
	}
}