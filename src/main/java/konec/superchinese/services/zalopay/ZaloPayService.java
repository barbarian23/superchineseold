package konec.superchinese.services.zalopay;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import konec.superchinese.commons.MessageUtils;
import konec.superchinese.commons.ReturnCodeConst;
import konec.superchinese.commons.ScreenNameInterface;
import konec.superchinese.models.appotapay.UrlAppotaPayment;
import konec.superchinese.models.loginzalo.ZaloLoginInfo;
import konec.superchinese.models.vippackages.PaymentModel;
import konec.superchinese.services.logs.LogService;

@Service
public class ZaloPayService implements MessageUtils,ReturnCodeConst,ScreenNameInterface {
	
	@Value("${queryBalanceUrl}")
	public String queryBalanceUrl;
	
	@Value("${createOrderUrl}")
	public String createOrderUrl;
	
	@Value("${agreementBindUrl}")
	public String agreementBindUrl;
	
	@Value("${orderByPhoneUrl}")
	public String orderByPhoneUrl;
	
	@Value("${checkBindPayUrl}")
	public  String checkBindPayUrl;
	
	@Value("${appotacreateoder}")
	public  String appotacreateoder;
	
	@Value("${checkBillAppotaUrl}")
	public  String checkBillAppotaUrl;
	
	@Value("${superchinese.agency}")
	public  String agency;
        
        @Value("${superchinese.voucher_code}")
	public String agency_voucher_code;
        
        @Value("${appota.redirect_url}")
	public String appota_redirect_url;
	
	@Autowired
	LogService logService;

	public HashMap<String, String> queryBalance(String accessToken, String packageCode) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("package_code", packageCode);
		URI uri = new URI(queryBalanceUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("queryBalance","api/zalo/query_balance",requestLog,responseLog);

		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
			return map;

		} else {
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
			return new HashMap<String, String>();
		}
	}

	public HashMap<String, String> createOrder(String accessToken, String packageCode, String account, String voucher_code, String clickId)
			throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("package_code", packageCode);
		params.put("account", account);
		
		if(!voucher_code.isEmpty()) {
			params.put("voucher_code", voucher_code);
		}
                else if (!agency_voucher_code.isEmpty()) {
                    params.put("voucher_code", agency_voucher_code);
                }
		if(!clickId.isEmpty()) {
			params.put("click_id", clickId);
		}
                params.put("order_channel", "WEB");
		URI uri = new URI(createOrderUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("createOrder","api/zalo/create_order",requestLog,responseLog);

		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			return map;

		} else {
			params.put("getStatusCodeValue", "error");
			return new HashMap<String, String>();
		}
	}
	
	public HashMap<String, String> createOrderAppota(String accessToken, String packageCode, String account, String voucher_code, String clickId)
			throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("package_code", packageCode);
		params.put("account", account);
		params.put("agency", agency);
		params.put("redirect_url", appota_redirect_url);		
		if(!voucher_code.isEmpty()) {
			params.put("voucher_code", voucher_code);
		}
                else if (!agency_voucher_code.isEmpty()) {
                    params.put("voucher_code", agency_voucher_code);
                }
		if(!clickId.isEmpty()) {
			params.put("click_id", clickId);
		}
                params.put("order_channel", "WEB");
		URI uri = new URI(appotacreateoder);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("createOrderAppota","api/appotapay/create_order",requestLog,responseLog);

		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			return map;

		} else {
			params.put("getStatusCodeValue", "error");
			return new HashMap<String, String>();
		}
	}

	public HashMap<String, String> agreementBind(String accessToken, String packCode, String account, String voucher_code, String clickId) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("package_code", packCode);
		params.put("account", account);
		params.put("agency", agency);
		if(!voucher_code.isEmpty()) {
			params.put("voucher_code", voucher_code);
		} 
                else if (!agency_voucher_code.isEmpty()) {
                    params.put("voucher_code", agency_voucher_code);
                }
		if(!clickId.isEmpty()) {
			params.put("click_id", clickId);
		}
                params.put("order_channel", "WEB");
		URI uri = new URI(agreementBindUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("agreementBind","api/zalo/agreement_bind",requestLog,responseLog);
		
		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			if (_1.equals(String.valueOf(map.get("return_code")))) {
				// Tạo liên kết thành công
				return map;
			} else {
				return new HashMap<String, String>();
			}
		} else {
			return new HashMap<String, String>();
		}
	}
	
	public PaymentModel getStatusOrder(String accessToken, String binding_id, PaymentModel pm) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("binding_id", binding_id);
		URI uri = new URI(checkBindPayUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("getStatusOrder","api/zalo/check_bind_pay_status",requestLog,responseLog);

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
				JsonObject  jobject = jsonElement.getAsJsonObject();
				String status = jobject.get("pay_status").getAsString();
				orderStatusCase(pm, status);
			} else {
				pm.setStatus(false);
				pm.setMessage(ERROR_SYSTEM);
			}
		} else {
			pm.setStatus(false);
			pm.setMessage(ERROR_SYSTEM);
		}
		return pm;
	}
	
	public PaymentModel getStatusOrderAppota(String accessToken, String order_id, PaymentModel pm) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("order_id", order_id);
		URI uri = new URI(checkBillAppotaUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("appota_status","api/appotapay/check_order",requestLog,responseLog);
		
		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			if (_1.equals(String.valueOf(map.get("return_code")))) {
				// Thanh toan thanh cong
				String status = map.get("pay_status");
				orderStatusCaseAppota(pm, status);
			} else {
				pm.setStatus(false);
				pm.setMessage(ERROR_FAILED_PAYMENT);
			}
		} else {
			pm.setStatus(false);
			pm.setMessage(ERROR_FAILED_PAYMENT);
		}
		
		return pm;
	}
	
	public HashMap<String, String> orderByPhone(String phone, String packageCode)
			throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("package_code", packageCode);
		params.put("phone", phone);
		params.put("agency", agency);
                params.put("order_channel", "WEB");
		URI uri = new URI(orderByPhoneUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("orderByPhone","api/zalo/create_order",requestLog,responseLog);

		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			return map;

		} else {
			params.put("getStatusCodeValue", "error");
			return new HashMap<String, String>();
		}
	}

	public String casePayment(HashMap<String, String> hm, PaymentModel pm, HttpSession session, HttpServletRequest request) {
		HashMap<String, String> log = new HashMap<String, String>();
		ZaloLoginInfo zaloLoginInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
		log.put("accesstoken", zaloLoginInfo.getAccesstoken());
		log.put("Phone", zaloLoginInfo.getZaloName());
		log.put("licence", zaloLoginInfo.getZaloPhone());
		String message = hm.get("return_message");
		switch (String.valueOf(hm.get("return_code"))) {
		case "1":
			log.put("return_code", "1");
			// tao don hang thanh cong
			if ("1".equals(String.valueOf(hm.get("pay_return_code")))) {
				// thanh toan thanh cong
				log.put("pay_return_code", "1");
				pm.setStatus(true);
				pm.setMessage(SUCCESS_PAYMENT_SUCCESS);
				request.setAttribute("paymentModel", pm);
				return "forward:/thanh-toan-thanh-cong";
			}
			if ("2".equals(String.valueOf(hm.get("pay_return_code")))) {
				// thanh toan loi
				log.put("pay_return_code", "2");
				pm.setStatus(false);
				pm.setMessage(ERROR_FAILED_PAYMENT);
			}
			if ("3".equals(String.valueOf(hm.get("pay_return_code")))) {
				// thanh toan dang xu ly
				log.put("pay_return_code", "3");
				pm.setStatus(true);
				pm.setMessage(WAIT_FOR_PAYMENT);
				request.setAttribute("paymentModel", pm);
				return "forward:/thanh-toan-thanh-cong";
			}
			break;
		case "1301":
			// chua lien ket vi
			log.put("return_code", "1301");
			log.put("pay_return_code", "empty");
			pm.setStatus(false);
			pm.setMessage(ERROR_NEED_CONNECT_ZALO_WALLET);
			break;
		case "1308":
			// dang xu ly don hang, khach hang cho thanh toan
			log.put("return_code", "1308");
			log.put("pay_return_code", "empty");
			pm.setStatus(false);
			pm.setMessage(WAIT_FOR_PAYMENT);
			break;
		default:
		}
		pm.setStatus(false);
		pm.setMessage(message);
		return  VIPSTOREPAYMENTMETHOD;
	}
	
	public String casePaymentAppota(HashMap<String, String> hm, PaymentModel pm, HttpServletRequest request) throws JsonProcessingException {
		String returnUrl = "";
		switch (String.valueOf(hm.get("return_code"))) {
		case _1:
			// tao don hang thanh cong
			pm.setStatus(true);
			pm.setMessage(SUCCESS_PAYMENT_SUCCESS);
			request.setAttribute("paymentModel", pm);
			ObjectMapper mapper = new ObjectMapper();
			Object data = hm.get("data");
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(data);
			UrlAppotaPayment Urls = mapper.readValue(json, UrlAppotaPayment.class);
			returnUrl =Urls.getPaymentUrl();
			break;
		case _1300:
			//Gói cước không tồn tại;
			pm.setStatus(false);
			pm.setMessage(ERROR_PACKAGES_CANCELED);
			returnUrl ="";
			break;
		case _1308:
			//Đơn hàng đang được xử lý, vui lòng chờ",
			pm.setStatus(false);
			pm.setMessage(WAIT_FOR_PAYMENT);
			returnUrl ="";
			break;
		case _1309:
			//User hiện đã active gói này và vẫn còn thời hạn
			pm.setStatus(false);
			pm.setMessage(ERROR_ORDER_NOT_EXPIRE_YET);
			returnUrl ="";
			break;
		case _1310:
			//User hiện đã active gói này và vẫn còn thời hạn
			pm.setStatus(false);
			pm.setMessage(ERROR_PACK_ORDER_EXIST);
			returnUrl ="";
			break;
		default:
			pm.setStatus(false);
			pm.setMessage(ERROR_SYSTEM);
		}
		return returnUrl;
	}
	
	public void orderStatusCase(PaymentModel pm, String status) throws Exception {
		switch (status) {
		case PAY_WAITING:
			pm.setStatus(true);
			pm.setMessage(WAIT_FOR_PAYMENT);
			break;
		case PAY_SUCCESS:
			pm.setStatus(true);
			pm.setMessage(SUCCESS_PAYMENT_SUCCESS);
			break;
		case PAY_FAILED:
			pm.setStatus(false);
			pm.setMessage(ERROR_FAILED_PAYMENT);
			break;
		case PAY_PROCESSING:
			pm.setStatus(true);
			pm.setMessage(WAIT_FOR_PAYMENT);
			break;
		default:
		}
	}
	
	public void orderStatusCaseAppota(PaymentModel pm, String status) throws Exception {
		switch (status) {
		case SUCCESS:
			pm.setStatus(true);
			pm.setMessage(SUCCESS_PAYMENT_SUCCESS);
			break;
		case PROCESSING:
			pm.setStatus(true);
			pm.setMessage(WAIT_FOR_PAYMENT);
			break;
		case FAILED:
			pm.setStatus(false);
			pm.setMessage(ERROR_FAILED_PAYMENT);
			break;
		default:
		}
	}
}
