package konec.superchinese.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import konec.superchinese.commons.ReturnCodeConst;
import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.agency.AgencyModel;
import konec.superchinese.models.agency.AgencyModelResponse;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import konec.superchinese.services.ZaloOa.ZaloOaService;
import konec.superchinese.services.agency.AgencyService;
import konec.superchinese.services.captcha.VerifyCapchaService;
import konec.superchinese.services.zalopay.ZaloOrderByPhoneService;

@Controller
public class RegisterAgencyController implements ScreenNameInterface, ReturnCodeConst {

	@Value("${spcApiAgency}")
	public String spcApiAgency;

	@Autowired
	AgencyService agencyService;

	@Autowired
	VerifyCapchaService verifyCapchaService;

	@Autowired
	ZaloOaService zaloOaService;

	@Autowired
	ZaloOrderByPhoneService zaloOrderService;

	@PostMapping(value = "/agency")
	@ResponseBody
	public AgencyModelResponse edit(@ModelAttribute(value = "agencyModel") AgencyModel agencyModel,
			HttpServletRequest request, HttpServletResponse respons, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		zaloLoginInfo.setAgencyCallBack(false);
		session.setAttribute("ZaloLoginInfo", zaloLoginInfo);
		String token = "";
		String zaloName = "";
		if (zaloLoginInfo != null) {
			token = zaloLoginInfo.getAccesstoken();
			zaloName = zaloLoginInfo.getZaloName();
		}
		
		AgencyModelResponse AgencyModelResponse = new AgencyModelResponse();
		
		if (zaloOrderService.checkOAFollow(token)) {
			AgencyModelResponse = agencyService.registAgency(spcApiAgency, agencyModel, AgencyModelResponse);
			if(AgencyModelResponse.getReturn_code() == 1) {
				String message = zaloOaService.messageReplyAgency(zaloName, agencyModel.getEmail(),
						agencyModel.getPhoneNumber());
				zaloOaService.sendMessageAgency(token, message);
			}
			return AgencyModelResponse;
		} else {
			AgencyModelResponse.setReturn_code(98);
			return AgencyModelResponse;
		}
	}
}
