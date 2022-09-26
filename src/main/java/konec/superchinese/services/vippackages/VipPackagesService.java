package konec.superchinese.services.vippackages;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import konec.superchinese.commons.ReturnCodeConst;
import konec.superchinese.models.vippackages.SpcAPIVipPackagesRequest;
import konec.superchinese.models.vippackages.SpcAPIVipPackagesResponse;
import konec.superchinese.models.vippackages.SpcVIPPackagesData;
import konec.superchinese.models.vippackages.ThelastPeopleModel;
import konec.superchinese.models.vippackages.VipPackageStore;
import konec.superchinese.services.logs.LogService;

@Service
public class VipPackagesService implements ReturnCodeConst {
	
	@Value("${getLastTransactionUrl}")
	public String getLastTransactionUrl;
        
        @Value("${superchinese.voucher_code}")
	public String voucher_code;
        
        @Value("${superchinese.agency}")
	public  String agency;
	
	@Autowired
	LogService logService;

	/**
	 * get vip packages
	 * 
	 * @param uri
	 * @param type
	 * @param vipPackageStore
	 * @return
	 * @throws Exception
	 */
	public VipPackageStore getVipPackages(String uri, String type, VipPackageStore vipPackageStore) throws Exception {                
		URI spcApiVipPackageUri = new URI(uri);
		SpcAPIVipPackagesRequest spcAPIVipPackagesRequest = new SpcAPIVipPackagesRequest();
		spcAPIVipPackagesRequest.setApp_type(type);
                spcAPIVipPackagesRequest.setVoucher_code(voucher_code);
                spcAPIVipPackagesRequest.setAgency(agency);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SpcAPIVipPackagesRequest> entity = new HttpEntity<SpcAPIVipPackagesRequest>(spcAPIVipPackagesRequest,
				headers);

		ResponseEntity<SpcAPIVipPackagesResponse> result = restTemplate.exchange(spcApiVipPackageUri, HttpMethod.POST,
				entity, SpcAPIVipPackagesResponse.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(entity);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("get_Package","api/spc/get_vip_packages",requestLog,responseLog);
		
		ArrayList<SpcVIPPackagesData> SpcVIPPackagesDataList = new ArrayList<SpcVIPPackagesData>();
		if (result.getStatusCodeValue() == 200) {
			if (result.getBody().getReturn_code() == 1) {
				SpcVIPPackagesDataList = (ArrayList<SpcVIPPackagesData>) result.getBody().getData();
			} else {
				return vipPackageStore;
			}
		}
		if (SpcVIPPackagesDataList.size() > 0) {
			for (SpcVIPPackagesData ar : SpcVIPPackagesDataList) {
				if (ar.getPackageCode().equalsIgnoreCase("SCM") && ar.getStatus() == 1) {
					vipPackageStore.setPackageCode_m(ar.getPackageCode());
					vipPackageStore.setName_m(ar.getName());
					vipPackageStore.setSellPrice_m(ar.getSellPrice());
					vipPackageStore.setOriginalPrice_m(ar.getOriginalPrice());
					vipPackageStore.setTotalPurchased_m(ar.getTotalPurchased());
				}
				if (ar.getPackageCode().equalsIgnoreCase("SCY") && ar.getStatus() == 1) {
					vipPackageStore.setPackageCode_y(ar.getPackageCode());
					vipPackageStore.setName_y(ar.getName());
					vipPackageStore.setSellPrice_y(ar.getSellPrice());
					vipPackageStore.setOriginalPrice_y(ar.getOriginalPrice());
					vipPackageStore.setTotalPurchased_y(ar.getTotalPurchased());
				}
				if (ar.getPackageCode().equalsIgnoreCase("SCL") && ar.getStatus() == 1) {
					vipPackageStore.setPackageCode_l(ar.getPackageCode());
					vipPackageStore.setName_l(ar.getName());
					vipPackageStore.setSellPrice_l(ar.getSellPrice());
					vipPackageStore.setOriginalPrice_l(ar.getOriginalPrice());
					vipPackageStore.setTotalPurchased_l(ar.getTotalPurchased());
				}

				if (ar.getPackageCode().equalsIgnoreCase("STM") && ar.getStatus() == 1) {
					vipPackageStore.setPackageCode_st_m(ar.getPackageCode());
					vipPackageStore.setName_st_m(ar.getName());
					vipPackageStore.setSellPrice_st_m(ar.getSellPrice());
					vipPackageStore.setOriginalPrice_st_m(ar.getOriginalPrice());
					vipPackageStore.setTotalPurchased_st_m(ar.getTotalPurchased());
				}
				if (ar.getPackageCode().equalsIgnoreCase("STY") && ar.getStatus() == 1) {
					vipPackageStore.setPackageCode_st_y(ar.getPackageCode());
					vipPackageStore.setName_st_y(ar.getName());
					vipPackageStore.setSellPrice_st_y(ar.getSellPrice());
					vipPackageStore.setOriginalPrice_st_y(ar.getOriginalPrice());
					vipPackageStore.setTotalPurchased_st_y(ar.getTotalPurchased());
				}
				if (ar.getPackageCode().equalsIgnoreCase("STL") && ar.getStatus() == 1) {
					vipPackageStore.setPackageCode_st_l(ar.getPackageCode());
					vipPackageStore.setName_st_l(ar.getName());
					vipPackageStore.setSellPrice_st_l(ar.getSellPrice());
					vipPackageStore.setOriginalPrice_st_l(ar.getOriginalPrice());
					vipPackageStore.setTotalPurchased_st_l(ar.getTotalPurchased());
				}
			}
		}

		return vipPackageStore;
	}
	
	/**
	 * GetLastesttransaction
	 * 
	 * @return
	 * @throws Exception
	 */
	public ThelastPeopleModel GetLastesttransaction() throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		URI uri = new URI(getLastTransactionUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			if (_1.equals(String.valueOf(map.get("return_code")))) {
				// pay success
				ObjectMapper Obmap = new ObjectMapper();
				Object data = map.get("data");
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(data);
				JsonParser parser = new JsonParser();
				JsonElement jsonElement = parser.parse(json);
				JsonObject  jobject = jsonElement.getAsJsonObject();
				String customerName = jobject.get("customerName").getAsString();
				String packageName = jobject.get("packageName").getAsString();
				String createTime = jobject.get("createTime").getAsString();
				String appType = jobject.get("appType").getAsString();
				ThelastPeopleModel model  = new ThelastPeopleModel(customerName, packageName, convertMilisecondsToDate(createTime),packageName(appType) );
				return model;
			} else {
				return null;
			}
		} else {
			return null;
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
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");

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
}
