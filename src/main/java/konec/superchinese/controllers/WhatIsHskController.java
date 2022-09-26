package konec.superchinese.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import konec.superchinese.commons.ScreenNameInterface;

@Controller
public class WhatIsHskController implements ScreenNameInterface {

	@RequestMapping(value = "/ky-thi-hsk-la-gi")
	public String action(ModelMap modelMap, HttpServletRequest request, HttpServletResponse respons,
			HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		session.removeAttribute("orderStore");
		session.removeAttribute("orderStoreFinal");
		return WHATISHSK;
	}
}