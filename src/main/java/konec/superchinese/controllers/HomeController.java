package konec.superchinese.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController implements ScreenNameInterface {

	@GetMapping(value = "/")
	public String action(ModelMap modelMap, HttpServletRequest request, HttpServletResponse respons,
			HttpSession session, RedirectAttributes redirectAttributes,
                        @RequestParam(name = "clickid", required = false, defaultValue = "") String clickid) throws Exception {
		session.removeAttribute("orderStore");
		session.removeAttribute("orderStoreFinal");
                Cookie adpia_clickid = null;
                clickid = clickid.trim();
		if (!clickid.isEmpty()) {
			adpia_clickid = new Cookie("adpiaClickid", clickid);
			adpia_clickid.setMaxAge(60 * 60 * 24 * 30);
			respons.addCookie(adpia_clickid);
		}
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		if (zaloLoginInfo != null && zaloLoginInfo.isLoginCallBack()) {
			zaloLoginInfo.setLoginCallBack(false);
			session.setAttribute("ZaloLoginInfo", zaloLoginInfo);
			return "redirect:/kich-hoat-vip";
		}
		return HOME;
	}
}