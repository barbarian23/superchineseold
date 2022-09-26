package konec.superchinese.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import konec.superchinese.commons.MessageUtils;
import konec.superchinese.commons.ReturnCodeConst;
import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import konec.superchinese.models.vippackages.VipPackageStore;
import konec.superchinese.services.vippackages.VipPackagesService;
import konec.superchinese.services.zalopay.ZaloOrderByPhoneService;

@Controller
public class VipStoreController implements ScreenNameInterface, ReturnCodeConst, MessageUtils {

	@Value("${superchinese.folder.logs}")
	private String rootPath;

	public static final String SUPER_SC_PACKAGE = "sc";
	public static final String SUPER_ST_PACKAGE = "st";
	@Value("${superchinese.discount}")
	private String discount;

	@Value("${superchinese.date.discount}")
	private String Datediscount;

	@Value("${spcApiVipPackage}")
	public String spcApiVipPackage;

	@Autowired
	VipPackagesService vipPackagesService;

	@Autowired
	ZaloOrderByPhoneService zaloOrderService;

	@GetMapping(value = "/goi-vip-superchinese")
	public String action(ModelMap modelMap, HttpServletRequest request, HttpServletResponse respons,
			HttpSession session, RedirectAttributes redirectAttributes,
			@RequestParam(name = "clickid", required = false, defaultValue = "") String clickid) throws Exception {
		session.removeAttribute("orderStore");
		session.removeAttribute("orderStoreFinal");
		VipPackageStore vipPackageStore = new VipPackageStore();
		Cookie adpia_clickid = null;
                clickid = clickid.trim();
		if (!clickid.isEmpty()) {
			adpia_clickid = new Cookie("adpiaClickid", clickid);
			adpia_clickid.setMaxAge(60 * 60 * 24 * 30);
			respons.addCookie(adpia_clickid);
		}
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		if (zaloLoginInfo != null) {
			modelMap.put("zaloName", zaloLoginInfo.getZaloName());
		}

		// get all superchinese packages
		vipPackageStore = vipPackagesService.getVipPackages(spcApiVipPackage, SUPER_SC_PACKAGE, vipPackageStore);
		// get all super test packages
		vipPackageStore = vipPackagesService.getVipPackages(spcApiVipPackage, SUPER_ST_PACKAGE, vipPackageStore);
		vipPackageStore.setHave_sc_packages(
				vipPackageStore.getPackageCode_m() != null && vipPackageStore.getPackageCode_m() != "" ? true : false);
		vipPackageStore.setHave_st_packages(
				vipPackageStore.getPackageCode_st_m() != null && vipPackageStore.getPackageCode_st_m() != "" ? true
						: false);
		modelMap.put("vipPackageStore", vipPackageStore);
		if (session.getAttribute("packageStore") == null) {
			session.setAttribute("packageStore", vipPackageStore);
		}
		return VIPSTORE;
	}
}