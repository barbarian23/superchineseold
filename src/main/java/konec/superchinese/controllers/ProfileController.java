package konec.superchinese.controllers;

import java.net.URI;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import konec.superchinese.models.profile.HistoryModel;
import konec.superchinese.models.profile.ProfileModel;
import konec.superchinese.services.logs.LogService;
import konec.superchinese.services.profile.ProfileService;

@Controller
public class ProfileController implements ScreenNameInterface {

	@Value("${spcApiProfile}")
	public String spcApiProfile;
	
	@Value("${spcApiHistoryList}")
	public String spcApiHistoryList;

	@Autowired
	ProfileService profileService;
	
	@Autowired
	LogService logService;

	@GetMapping(value = "/kich-hoat-vip")
	public String init(Model modelMap, HttpServletRequest request, HttpServletResponse respons, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		session.removeAttribute("orderStore");
		session.removeAttribute("orderStoreFinal");
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		zaloLoginInfo.setLoginCallBack(false);
		session.setAttribute("ZaloLoginInfo", zaloLoginInfo);
		String accesstoken = "";
		if (zaloLoginInfo != null) {
			accesstoken = zaloLoginInfo.getAccesstoken();
		}
		URI spcApiProfileUri = new URI(spcApiProfile);
		URI spcApiHistoryListUri = new URI(spcApiHistoryList);
		ProfileModel profileModel = new ProfileModel();
		HistoryModel historyModel = new HistoryModel();
		if (accesstoken == "") {
			return "redirect:/error";
		} else {
			profileModel = profileService.getFrofileInfo(accesstoken, spcApiProfileUri, profileModel);
			historyModel = profileService.getHistoryTransaction(accesstoken, spcApiHistoryListUri, historyModel);
		}
		modelMap.addAttribute("profileModel", profileModel);
		modelMap.addAttribute("historyModel", historyModel);

		return PROFILE;
	}
}