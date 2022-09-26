package konec.superchinese.controllers;

import java.util.HashMap;

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

import konec.superchinese.commons.MessageUtils;
import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.activelicense.LicenseModel;
import konec.superchinese.models.activelicense.LicenseModelResponse;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import konec.superchinese.services.activelicense.LicenseService;
import konec.superchinese.services.captcha.VerifyCapchaService;
import konec.superchinese.services.logs.LogService;

@Controller
public class ActiveLicenseController implements ScreenNameInterface, MessageUtils {

	@Value("${spcApiActiveLicense}")
	public String spcApiActiveLicense;

	@Autowired
	LicenseService licenseService;
	
	@Autowired
	VerifyCapchaService verifyCapchaService;
	
	@Autowired
	LogService logService;

	@PostMapping(value = "/activeLicense")
	@ResponseBody
	public LicenseModelResponse get(@ModelAttribute(value = "licenseModel") LicenseModel licenseModel,
			HttpServletRequest request, HttpServletResponse respons, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		 // Verify CAPTCHA
//		if (!verifyCapchaService.verifyCapcha(licenseModel.getCapcha())) {
//			LicenseModelResponse model = new LicenseModelResponse();
//			model.setError("email", ERROR_VALIDATE_ACCOUNT_NOT_VALID);
//			model.setReturn_code(99);
//			model.setHasError(true);
//		    return model;
//		 }
		
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		String accesstoken = "";
		if (zaloLoginInfo != null) {
			accesstoken = zaloLoginInfo.getAccesstoken();
		}
		LicenseModelResponse licenseModelResponse = new LicenseModelResponse();
		if (accesstoken == "") {
			licenseModelResponse.setReturn_code(100);
			licenseModelResponse.setError("timeout", ERROR_ACTIVE_TIME_OUT);
			licenseModelResponse.setReturn_message("Không thể kích hoạt");

			return licenseModelResponse;
		}

		licenseService.validate(licenseModel, licenseModelResponse);
		if (licenseModel.isHasError()) {
			return licenseModelResponse;
		} else {
			licenseModelResponse = licenseService.activeLicense(spcApiActiveLicense, accesstoken, licenseModel,
					licenseModelResponse);
		}
		return licenseModelResponse;
	}
}
