package konec.superchinese.controllers;

import java.net.URI;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import konec.superchinese.models.profile.ProfileModel;
import konec.superchinese.models.profile.SpcAPILoginResponse;
import konec.superchinese.services.login.LoginZaloService;
import konec.superchinese.services.profile.ProfileService;

@Controller
public class LoginZaloController implements ScreenNameInterface {
	public String verifierCode = "";
	public String codeChallenge = "";

	@Value("${superchinese.app_id}")
	private String app_id;

	@Value("${superchinese.secret_key}")
	private String secret_key;

	@Value("${superchinese.redirect_uri_url}")
	private String redirect_uri_url;

	@Value("${superchinese.permission_url}")
	private String permission_url;

	@Value("${superchinese.access_token_url}")
	private String access_token_url;

	@Value("${superchinese.state}")
	private String state;

	@Value("${spcApiLogin}")
	public String spcApiLogin;

	@Value("${spcApiChecktoken}")
	public String spcApiChecktoken;

	@Value("${spcApiProfile}")
	public String spcApiProfile;

	@Autowired
	LoginZaloService loginZaloService;

	@Autowired
	ProfileService profileService;

	@GetMapping("/dang-nhap")
	public String login(HttpServletRequest request, HttpServletResponse respons, RedirectAttributes redirectAttributes,
			HttpSession session,
			@RequestParam(name = "accesstoken", required = false, defaultValue = "") String accesstoken,
			@RequestParam(name = "login", required = false, defaultValue = "") String loginCallBack,
			@RequestParam(name = "agency", required = false, defaultValue = "") String agencyCallBack,
			@RequestParam(name = "payment", required = false, defaultValue = "") String paymentCallBack,
			@RequestParam(name = "advise", required = false, defaultValue = "") String adviseCallBack,
			@RequestParam(name = "order", required = false, defaultValue = "") String orderCallBack) throws Exception {
		Logger logger = LoggerFactory.getLogger(getClass());
		ProfileModel profileModel = new ProfileModel();
		ZaloLoginInfo zaloLoginInfo = new ZaloLoginInfo();
		zaloLoginInfo.setLoginCallBack(loginCallBack.isEmpty() ? false : true);
		zaloLoginInfo.setAgencyCallBack(agencyCallBack.isEmpty() ? false : true);
		zaloLoginInfo.setPaymentCallBack(paymentCallBack.isEmpty() ? false : true);
		zaloLoginInfo.setAdviseCallBack(adviseCallBack.isEmpty() ? false : true);
		zaloLoginInfo.setOrderCallBack(orderCallBack.isEmpty() ? false : true);
		String beforeUrl = request.getHeader("Referer");
		zaloLoginInfo.setUrlBefore(beforeUrl);

//		//hardcode
//		hardcodeLogin(session, zaloLoginInfo);
//		session.setAttribute("zaloLoginInfo", zaloLoginInfo);
//		if (zaloLoginInfo.isLoginCallBack()) {
//			return "redirect:/kich-hoat-vip";
//		}
//		if (zaloLoginInfo.isOrderCallBack()) {
//			return "redirect:/xac-nhan-thanh-toan-goi-vip-superchinese";
//		}
//		return "redirect:" + beforeUrl;
		if (accesstoken.length() > 10) {
			ResponseEntity<SpcAPILoginResponse> result = loginZaloService.checkToken(accesstoken, spcApiChecktoken);
			if (result.getStatusCodeValue() == 200) {
				if (result.getBody().getReturn_code() == 0) {
					logger.info("token con han login thanh cong ");
					URI spcApiProfileUri = new URI(spcApiProfile);
					profileModel = profileService.getFrofileInfo(accesstoken, spcApiProfileUri, profileModel);
					logger.info("name zalo : " + profileModel.getName());
					logger.info("Phone zalo : " + profileModel.getPhone());
					zaloLoginInfo.setAccesstoken(accesstoken);
					zaloLoginInfo.setLoginFlag("1");
					zaloLoginInfo.setZaloName(profileModel.getName());
					zaloLoginInfo.setZaloPhone(String.valueOf(profileModel.getPhone()));
					zaloLoginInfo.setUserId(profileModel.getUserId());
					session.setAttribute("ZaloLoginInfo", zaloLoginInfo);

				} else if (result.getBody().getReturn_code() == 1201) {
					logger.info("tao token moi tu refresh token thanh cong ");
					accesstoken = result.getBody().getNew_access_token();
					URI spcApiProfileUri = new URI(spcApiProfile);
					profileModel = profileService.getFrofileInfo(accesstoken, spcApiProfileUri, profileModel);
					logger.info("name zalo : " + profileModel.getName());
					logger.info("Phone zalo : " + profileModel.getPhone());
					zaloLoginInfo.setAccesstoken(accesstoken);
					zaloLoginInfo.setLoginFlag("1");
					zaloLoginInfo.setZaloName(profileModel.getName());
					zaloLoginInfo.setZaloPhone(String.valueOf(profileModel.getPhone()));
					zaloLoginInfo.setUserId(profileModel.getUserId());
					session.setAttribute("ZaloLoginInfo", zaloLoginInfo);

				} else {
		            // login failed
					logger.info("login that bai");
					session.removeAttribute("ZaloLoginInfo");
				}
			} else {
				session.removeAttribute("ZaloLoginInfo");
			}
			if (zaloLoginInfo.isLoginCallBack()) {
				return "redirect:/kich-hoat-vip";
			}
			if (zaloLoginInfo.isOrderCallBack()) {
				return "redirect:/xac-nhan-thanh-toan-goi-vip-superchinese";
			}
			return "redirect:" + beforeUrl;
		} else {
			// init param to call api zalo
			verifierCode = loginZaloService.verifierCodeGenerate();
			codeChallenge = loginZaloService.getCodeChallenge(verifierCode);
			zaloLoginInfo.setVerifierCode(verifierCode);
			session.setAttribute("ZaloLoginInfo", zaloLoginInfo);
			String url = (new StringBuilder()).append(permission_url).append("?app_id=" + app_id)
					.append("&redirect_uri=").append(redirect_uri_url).append("&code_challenge=").append(codeChallenge)
					.append("&state=").append(state).toString();
			return "redirect:" + url;
		}
	}

	@GetMapping("/zalo/verify")
	public String accountVerify(ModelMap modelMap, HttpServletRequest request, HttpServletResponse respons,
			HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam(required = true, defaultValue = "") String code,
			@RequestParam(required = true, defaultValue = "") String state,
			@RequestParam(required = true, defaultValue = "") String code_challenge) throws Exception {
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		Logger logger = LoggerFactory.getLogger(getClass());

		logger.info("code: " + code);
		logger.info("state: " + state);
		logger.info("code_challenge: " + code_challenge);
		logger.info("verifierCode: " + zaloLoginInfo.getVerifierCode());
		if (zaloLoginInfo == null) {
			session.removeAttribute("ZaloLoginInfo");
			return "redirect:" + zaloLoginInfo.getUrlBefore();
		}
		Map<String, String> result = loginZaloService.getAccessTokenFromZalo(code, app_id, secret_key, access_token_url,
				zaloLoginInfo.getVerifierCode());
		String accesstoken = result.get("access_token");
		String refreshToken = result.get("refresh_token");
		logger.info("accesstoken: " + accesstoken);
		logger.info("refreshToken: " + refreshToken);
		if (accesstoken != null && refreshToken != null) {
			if (loginZaloService.saveToken(accesstoken, refreshToken, spcApiLogin)) {
				zaloLoginInfo.setAccesstoken(accesstoken);
				ProfileModel profileModel = new ProfileModel();
				URI spcApiProfileUri = new URI(spcApiProfile);
				profileModel = profileService.getFrofileInfo(accesstoken, spcApiProfileUri, profileModel);
				logger.info("name zalo : " + profileModel.getName());
				logger.info("Phone zalo : " + profileModel.getPhone());
				zaloLoginInfo.setAccesstoken(accesstoken);
				zaloLoginInfo.setLoginFlag("1");
				zaloLoginInfo.setZaloName(profileModel.getName());
				zaloLoginInfo.setZaloPhone(String.valueOf(profileModel.getPhone()));
				zaloLoginInfo.setUserId(profileModel.getUserId());
				session.setAttribute("ZaloLoginInfo", zaloLoginInfo);
				if (zaloLoginInfo.isLoginCallBack()) {
					return "redirect:/kich-hoat-vip";
				}
				if (zaloLoginInfo.isOrderCallBack()) {
					return "redirect:/xac-nhan-thanh-toan-goi-vip-superchinese";
				}
				return "redirect:" + zaloLoginInfo.getUrlBefore();
			} else {
				// login failed
				session.removeAttribute("ZaloLoginInfo");
				return "redirect:" + zaloLoginInfo.getUrlBefore();
			}
		}

		session.removeAttribute("ZaloLoginInfo");
		// login failed
		return "redirect:" + zaloLoginInfo.getUrlBefore();
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse respons, HttpSession session)
			throws Exception {
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		if (zaloLoginInfo != null) {
			loginZaloService.logoutZalo(zaloLoginInfo.getAccesstoken());
		}
		session.removeAttribute("ZaloLoginInfo");
		session.removeAttribute("orderStore");
		String beforeUrl = request.getHeader("Referer");
		return "redirect:" + beforeUrl;
	}

//	public void hardcodeLogin(HttpSession session, ZaloLoginInfo zaloLoginInfo) {
//		// hardcode
//		zaloLoginInfo.setAccesstoken(
//				"bBEKOmBt2LwJlvzf0VS4L92rcs44wmO_mFdGMXtBIooD_TPoMzuvMvFtq306p2jOc-_sBcR-57AjnSz1Gl8fS8griZbNWbXvoyR143dc8txRxluN4FXaO_xUbHGIx7XUsl600XF4PdxFp80UEf4RR_A9xpDYYNS4lO2ZMLAdNJ-xWiLVMA8z7wswtaDbgIKXXBR0I6c7BtIFlkaWKf4lIwwiiGruj7DRfPBiAMQp87oWXlS2S8OjQRAqzWHdZYXBhRZI05sB6NwXbk8OShPrHEUjcWWik4ryScAbSAu41Uq9LG");
//		zaloLoginInfo.setLoginFlag("1");
//		zaloLoginInfo.setZaloName("chientd");
//		zaloLoginInfo.setZaloPhone("0366313926");
//		zaloLoginInfo.setUserId("123");
//		session.setAttribute("ZaloLoginInfo", zaloLoginInfo);
//	}
}