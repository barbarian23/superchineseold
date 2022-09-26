package konec.superchinese.services.vippackages;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import konec.superchinese.commons.MessageUtils;
import konec.superchinese.commons.ReturnCodeConst;
import konec.superchinese.models.vippackages.VipPackageStore;
import konec.superchinese.models.vippackages.VipStoreInformoder;
import konec.superchinese.models.vippackages.VipstoreSaleData;
import konec.superchinese.services.logs.LogService;

@Service
public class VipStoreInformoderService implements MessageUtils, ReturnCodeConst {
	
	@Value("${checkVoucherUrl}")
	public String checkVoucherUrl;
	
	@Value("${spcApiVipcheckUser}")
	public String spcApiVipcheckUser;

	public String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static final String regexPhone = "^[0-9]{10}$";

	public static final String regexCode = "^[0-9]{16}$";
	
	@Autowired
	LogService logService;

	/**
	 * validate
	 * 
	 * @param account
	 * @return
	 */
	public boolean validate(String account) {
		if ((account == null || !patternMatches(account, regexEmail))
				&& (account == null || !patternMatches(account, regexPhone))) {
			return false;
		}
		return true;
	}
	
	/**
	 * patternMatches
	 * 
	 * @param param
	 * @param regexPattern
	 * @return
	 */
	public boolean patternMatches(String param, String regexPattern) {
		return Pattern.compile(regexPattern).matcher(param).matches();
	}
	
	/**
	 * initModel
	 * 
	 * @param vipPackageStore
	 * @param vipStoreInformoder
	 * @return
	 * @throws Exception
	 */
	public VipStoreInformoder initModel(VipPackageStore vipPackageStore, VipStoreInformoder vipStoreInformoder)
			throws Exception {
		if (vipStoreInformoder.getPackageCode_sc().equals("SCM")) {
			vipStoreInformoder.setOriginalPrice_sc(vipPackageStore.getOriginalPrice_m());
			vipStoreInformoder.setSellPrice_sc(vipPackageStore.getSellPrice_m());
		} else if (vipStoreInformoder.getPackageCode_sc().equals("SCY")) {
			vipStoreInformoder.setOriginalPrice_sc(vipPackageStore.getOriginalPrice_y());
			vipStoreInformoder.setSellPrice_sc(vipPackageStore.getSellPrice_y());
		} else if (vipStoreInformoder.getPackageCode_sc().equals("SCL")) {
			vipStoreInformoder.setOriginalPrice_sc(vipPackageStore.getOriginalPrice_l());
			vipStoreInformoder.setSellPrice_sc(vipPackageStore.getSellPrice_l());
		}
		if (vipStoreInformoder.getPackageCode_st().equals("STM")) {
			vipStoreInformoder.setOriginalPrice_st(vipPackageStore.getOriginalPrice_st_m());
			vipStoreInformoder.setSellPrice_st(vipPackageStore.getSellPrice_st_m());
		} else if (vipStoreInformoder.getPackageCode_st().equals("STY")) {
			vipStoreInformoder.setOriginalPrice_st(vipPackageStore.getOriginalPrice_st_y());
			vipStoreInformoder.setSellPrice_st(vipPackageStore.getSellPrice_st_y());
		} else if (vipStoreInformoder.getPackageCode_st().equals("STL")) {
			vipStoreInformoder.setOriginalPrice_st(vipPackageStore.getOriginalPrice_st_l());
			vipStoreInformoder.setSellPrice_st(vipPackageStore.getSellPrice_st_l());
		}
		Integer pricesc = vipStoreInformoder.getSellPrice_sc() == null ? 0 : vipStoreInformoder.getSellPrice_sc();
		Integer pricest = vipStoreInformoder.getSellPrice_st() == null ? 0 : vipStoreInformoder.getSellPrice_st();
		vipStoreInformoder.setTotalPrice(pricesc + pricest);
		vipStoreInformoder.setVatPrice((int) (vipStoreInformoder.getTotalPrice() * 0.1));
		vipStoreInformoder.setTotalPriceVat((int) (vipStoreInformoder.getTotalPrice() * 1.1));
		return vipStoreInformoder;
	}
	
	/**
	 * checkExistAccount
	 * 
	 * @param packageCode
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public boolean checkExistAccount(String packageCode, String account) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("app_type", packageCode);
		params.put("account", account);
		URI uri = new URI(spcApiVipcheckUser);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("exist_Account","api/spc/check_user_account",requestLog,responseLog);

		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			if ("1".equals(String.valueOf(map.get("return_code")))) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}
	/**
	 * checkVoucher
	 * 
	 * @param package_code
	 * @param voucher_code
	 * @return
	 * @throws Exception
	 */
	public VipstoreSaleData checkVoucher(String package_code, String voucher_code) throws Exception {
		VipstoreSaleData vipstoreSaleData = new VipstoreSaleData();
		vipstoreSaleData.setStatus(false);
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("package_code", package_code);
		params.put("voucher_code", voucher_code);
		URI uri = new URI(checkVoucherUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("checkVoucher","api/spc/check_voucher",requestLog,responseLog);

		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			if (_1.equals(String.valueOf(map.get("return_code")))) {
				// Tạo liên kết thành công
				// pay success
				ObjectMapper Obmap = new ObjectMapper();
				Object data = map.get("data");
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(data);
				JsonParser parser = new JsonParser();
				JsonElement jsonElement = parser.parse(json);
				JsonObject jobject = jsonElement.getAsJsonObject();
				String finalPrice = jobject.get("finalPrice").getAsString();
				String discount = jobject.get("discount").getAsString();
				vipstoreSaleData.setDiscount(discount);
				vipstoreSaleData.setFinalPrice(finalPrice);
				vipstoreSaleData.setStatus(true);
				return vipstoreSaleData;
			} else {
				return vipstoreSaleData;
			}
		} else {
			return vipstoreSaleData;
		}
	}
}
