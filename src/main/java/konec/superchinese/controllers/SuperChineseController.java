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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SuperChineseController implements ScreenNameInterface {

	@GetMapping(value = "/super-chinese")
	public String action(ModelMap modelMap, HttpServletRequest request, HttpServletResponse respons,
			HttpSession session, RedirectAttributes redirectAttributes,
                        @RequestParam(name = "clickid", required = false, defaultValue = "") String clickid) throws Exception {
		modelMap.put("superchinese", true);
		session.removeAttribute("orderStore");
		session.removeAttribute("orderStoreFinal");
                Cookie adpia_clickid = null;
                clickid = clickid.trim();
		if (!clickid.isEmpty()) {
			adpia_clickid = new Cookie("adpiaClickid", clickid);
			adpia_clickid.setMaxAge(60 * 60 * 24 * 30);
			respons.addCookie(adpia_clickid);
		}
		return SUPPERCHINESE;
	}
}