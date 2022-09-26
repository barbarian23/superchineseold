package konec.superchinese.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import konec.superchinese.commons.MessageUtils;
import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import konec.superchinese.models.vippackages.ExistAccountModel;
import konec.superchinese.models.vippackages.PaymentModel;
import konec.superchinese.models.vippackages.VipPackageStore;
import konec.superchinese.models.vippackages.VipStoreInformoder;
import konec.superchinese.models.vippackages.VipstoreSale;
import konec.superchinese.models.vippackages.VipstoreSaleData;
import konec.superchinese.services.vippackages.VipPackagesService;
import konec.superchinese.services.vippackages.VipStoreInformoderService;

@Controller
public class VipStoreInformorderController implements ScreenNameInterface, MessageUtils {
	@Value("${spcApiVipPackage}")
	public String spcApiVipPackage;
	
	@Value("${spcApiVipcheckUser}")
	public String spcApiVipcheckUser;

	public ArrayList<String> listSc = new ArrayList<String>() {
		{
			add("SCM");
			add("SCY");
			add("SCL");
		}
	};
	public ArrayList<String> listSt = new ArrayList<String>() {
		{
			add("STM");
			add("STY");
			add("STL");
		}
	};
	@Value("${superchinese.discount}")
	private String discount;

	@Value("${superchinese.date.discount}")
	private String Datediscount;

	@Autowired
	VipPackagesService vipPackagesService;

	@Autowired
	VipStoreInformoderService vipStoreInformoderService;

	@RequestMapping(value = "/xac-nhan-thanh-toan-goi-vip-superchinese", method = { RequestMethod.GET, RequestMethod.POST })
	public String action(@ModelAttribute(value = "vipPackageStore") VipPackageStore vipPackageStore, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse respons, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		VipStoreInformoder vipStoreInformoder = new VipStoreInformoder();
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		if(zaloLoginInfo != null) {
			modelMap.put("zaloName", zaloLoginInfo.getZaloName());
		}
		VipStoreInformoder vi = (VipStoreInformoder) session.getAttribute("orderStore");
		String packageScChoise = "";
		String packageStChoise = "";
		if (vi != null) {
			packageScChoise = vi.getPackageCode_sc();
			packageStChoise = vi.getPackageCode_st();
		}

		if (vipPackageStore.getPackageCode_sc() == null && vipPackageStore.getPackageCode_st() == null) {
			vipStoreInformoder.setPackageCode_sc(packageScChoise);
			vipStoreInformoder.setPackageCode_st(packageStChoise);
		} else {
			vipStoreInformoder.setPackageCode_sc(vipPackageStore.getPackageCode_sc());
			vipStoreInformoder.setPackageCode_st(vipPackageStore.getPackageCode_st());
		}
		VipPackageStore vipPackage = (VipPackageStore) session.getAttribute("packageStore");

		vipStoreInformoderService.initModel(vipPackage, vipStoreInformoder);
		modelMap.put("vipStoreInformoder", vipStoreInformoder);
		session.setAttribute("orderStore", vipStoreInformoder);
		if(zaloLoginInfo == null) {
			return "redirect:/dang-nhap?order=1";
		}
		zaloLoginInfo.setOrderCallBack(false);
		session.setAttribute("zaloLoginInfo", zaloLoginInfo);
		return VIPSTOREINFORMORDER;
	}

	@PostMapping(value = "/xac-nhan-thanh-toan-goi-vip-superchinese/existAccount")
	@ResponseBody
	public ExistAccountModel existAccount(ModelMap modelMap, @RequestParam("packages") String packages,
			@RequestParam("account") String account, HttpServletRequest request, HttpServletResponse respons,
			HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
		Logger logger = LoggerFactory.getLogger(getClass());
		PaymentModel pm = new PaymentModel();
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		String token = "";
		String phone = "";
		if (zaloLoginInfo != null) {
			token = zaloLoginInfo.getAccesstoken();
			phone = zaloLoginInfo.getZaloPhone();
		}

		if (!packages.equalsIgnoreCase("SC") && !packages.equalsIgnoreCase("ST")) {
			return new ExistAccountModel(200, ERROR_PACKAGE_NOT_VALID);
		}
		if (!vipStoreInformoderService.validate(account)) {
			return new ExistAccountModel(200, ERROR_ACCOUNT_NOT_VALID);
		}
		String packName = packages.equalsIgnoreCase("SC") ? "Super Chinese" : "Super Test";
		if (vipStoreInformoderService.checkExistAccount(packages, account)) {
			return new ExistAccountModel(200, HAS_PACKAGE + packName);
		} else {
			return new ExistAccountModel(200, HAS_NOT_PACKAGE + packName);
		}

	}

	@PostMapping(value = "/xac-nhan-thanh-toan-goi-vip-superchinese/salecode")
	@ResponseBody
	public VipstoreSale checkSaleCode(ModelMap modelMap, @RequestParam("saleCode") String saleCode,
			HttpServletRequest request, HttpServletResponse respons, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		VipStoreInformoder vipStoreInformoder = (VipStoreInformoder) session.getAttribute("orderStore");
		if (vipStoreInformoder != null) {
			String packCode = !vipStoreInformoder.getPackageCode_sc().isEmpty() ? vipStoreInformoder.getPackageCode_sc()
					: vipStoreInformoder.getPackageCode_st();
			VipstoreSaleData vipstoreSaleData = vipStoreInformoderService.checkVoucher(packCode, saleCode);
			if (vipstoreSaleData.isStatus()) {
				return new VipstoreSale(200, 1, "Success", vipstoreSaleData);
			}
			return new VipstoreSale(100, 0, "false", new VipstoreSaleData());
		} else {
			return new VipstoreSale(100, 0, "false", new VipstoreSaleData());
		}

	}
}