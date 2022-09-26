package konec.superchinese.services.zalopay;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import konec.superchinese.commons.MessageUtils;
import konec.superchinese.commons.ReturnCodeConst;
import konec.superchinese.models.vippackages.OrderByPhoneData;
import konec.superchinese.models.vippackages.PaymentModel;
import konec.superchinese.services.logs.LogService;

@Service
public class ZaloOrderByPhoneService implements MessageUtils, ReturnCodeConst {
	@Value("${checOakUserUrl}")
	public String checOakUserUrl;
	
	@Value("${packOrderUrl}")
	public String packOrderUrl;
	
	@Value("${packOrderListUrl}")
	public String packOrderListUrl;
	
	@Autowired
	LogService logService;

	/**
	 * checkOAFollow
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public boolean checkOAFollow(String token) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", token);
		URI uri = new URI(checOakUserUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("checkOAFollow","api/zalo/oa_check_follow",requestLog,responseLog);

		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			if (_1.equals(String.valueOf(map.get("return_code")))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * packOrder
	 * 
	 * @param token
	 * @param phone
	 * @param account
	 * @param packcode
	 * @param pm
	 * @throws Exception
	 */
	public void packOrder(String token, String phone, String account, String packcode, PaymentModel pm) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", token);
		params.put("phone", phone);
		params.put("account", account);
		params.put("package_code", packcode);
		URI uri = new URI(packOrderUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("packOrder","api/spc/package_order",requestLog,responseLog);
		
		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			if (_1.equals(String.valueOf(map.get("return_code")))) {
				pm.setStatus(true);
				pm.setMessage(SUCCESS_ORDER_SUCCESS);
			} else {
				pm.setStatus(false);
				pm.setMessage(map.get("return_message"));
			}
		} else {
			pm.setStatus(false);
			pm.setMessage(ERROR_ORDER_FAILUED);
		}
	}
	
	/**
	 * packOrderList
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public List<OrderByPhoneData> packOrderList(String token) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", token);
		URI uri = new URI(packOrderListUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("packOrderList","api/spc/package_order_list",requestLog,responseLog);

		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			if (_1.equals(String.valueOf(map.get("return_code")))) {
				ObjectMapper mappers = new ObjectMapper();
				Object data = map.get("data");
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(data);
				OrderByPhoneData[] orderByPhone = mappers.readValue(json, OrderByPhoneData[].class);
				List<OrderByPhoneData> ar = new ArrayList<OrderByPhoneData>();
				List<OrderByPhoneData> arConvert = new ArrayList<OrderByPhoneData>();
				ar = Arrays.asList(orderByPhone);
				
				for (int i = 0; i < ar.size(); i++) {
					OrderByPhoneData order = ar.get(i);
					order.setPackageName(packageName(order.getPackageCode()));
					order.setPackageTime(packageTime(order.getPackageCode()));
					order.setCreateTime(convertMilisecondsToDate(order.getCreateTime()));
					arConvert.add(order);
				}
				return arConvert;
			} else {
				return new ArrayList<OrderByPhoneData>();
			}
		} else {
			return new ArrayList<OrderByPhoneData>();
		}
	}
	
	/**
	 * convertMilisecondsToDate
	 * 
	 * @param milisecond
	 * @return
	 */
	public String convertMilisecondsToDate(String milisecond) {
		if (milisecond != null && milisecond != "") {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			long milliSeconds = Long.parseLong(milisecond);
			System.out.println(milliSeconds);

			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(milliSeconds);
			return formatter.format(calendar.getTime());
		} else {
			return "";
		}
	}
	
	/**
	 * packageName
	 * 
	 * @param packageCode
	 * @return
	 */
	public String packageName(String packageCode) {
		return packageCode.contains(SC) ? SUPER_CHINESE : SUPER_TEST;
	}
	
	/**
	 * packageTime
	 * 
	 * @param packageCode
	 * @return
	 */
	public String packageTime(String packageCode) {
		if(packageCode.contains("M")) {
			return MONTH;
		} else if(packageCode.contains("Y")) {
			return YEAR;
		} else if(packageCode.contains("L")) {
			return LIMITED;
		} else {
			return "";
		}
	}
}
