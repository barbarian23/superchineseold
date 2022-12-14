package konec.superchinese.services.ZaloOa;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import konec.superchinese.commons.ReturnCodeConst;
import konec.superchinese.models.vippackages.VipPackageStore;
import konec.superchinese.services.logs.LogService;

@Service
public class ZaloOaService implements ReturnCodeConst {
	
	@Autowired
	LogService logService;

	@Value("${zaloOaSendMessageUrl}")
	public String zaloOaSendMessageUrl;

	/**
	 * sendMessage
	 * 
	 * @param token
	 * @param message
	 * @param vipPackageStore
	 * @return
	 * @throws Exception
	 */
	public String sendMessage(String token, String message, VipPackageStore vipPackageStore) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("access_token", token);
		params.put("message", message);
		URI uri = new URI(zaloOaSendMessageUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("sendMessage","api/zalo/oa_send_message",requestLog,responseLog);
		
		ObjectMapper mapper = new ObjectMapper();
		if (result.getStatusCodeValue() == 200) {
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			return String.valueOf(map.get("return_code"));
		} else {
			return "99";
		}
	}

	/**
	 * messageReply
	 * 
	 * @param pack
	 * @param vipPackageStore
	 * @return
	 */
	public String messageReply(String pack, String zaloName, VipPackageStore vipPackageStore) {
		StringBuilder sb = new StringBuilder();
		sb.append("Super Chinese x??c nh???n,\n");
		sb.append("C??m ??n " + zaloName
				+ " ???? tin t?????ng. Ch??ng t??i ???? nh???n ???????c y??u c???u ?????t h??ng c???a b???n. Th??ng tin chi ti???t ????n h??ng:\n");
		sb.append("M?? ????n " + getOrderCode() + "\n");
		sb.append("Tr???ng th??i ch??a thanh to??n\n");
		sb.append(namePack(pack, vipPackageStore));
		sb.append("Qu???n tr??? vi??n s??? li??n h??? ?????n b???n v?? x??? l?? ????n h??ng trong th???i gian s???m nh???t. Tr??n tr???ng c???m ??n.");
		return sb.toString();
	}

	/**
	 * sendMessage
	 * 
	 * @param token
	 * @param message
	 * @param vipPackageStore
	 * @return
	 * @throws Exception
	 */
	public String sendMessageAgency(String token, String message) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("access_token", token);
		params.put("message", message);
		URI uri = new URI(zaloOaSendMessageUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, Object>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("sendMessageAgency","api/zalo/oa_send_message",requestLog,responseLog);
		
		ObjectMapper mapper = new ObjectMapper();
		if (result.getStatusCodeValue() == 200) {
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			return String.valueOf(map.get("return_code"));
		} else {
			return "99";
		}
	}

	/**
	 * messageReply
	 * 
	 * @param pack
	 * @param vipPackageStore
	 * @return
	 */
	public String messageReplyAgency(String zaloName, String email, String phone) {
		StringBuilder sb = new StringBuilder();
		sb.append("Super Chinese x??c nh???n,\n");
		sb.append("C??m ??n " + zaloName
				+ " ???? tin t?????ng. Ch??ng t??i ???? nh???n ???????c y??u c???u ????ng k?? l??m ?????i l?? c???a b???n. Th??ng tin chi ti???t:\n");
		sb.append("Email  " + email + "\n");
		sb.append("S??? ??i???n tho???i " + phone + "\n");
		sb.append("Qu???n tr??? vi??n s??? li??n h??? ?????n b???n trong th???i gian s???m nh???t. Tr??n tr???ng c???m ??n.");
		return sb.toString();
	}

	/**
	 * namePack
	 * 
	 * @param packcode
	 * @param vipPackageStore
	 * @return
	 */
	public String namePack(String packcode, VipPackageStore vipPackageStore) {

		String name = "";
		String price = "";
		switch (packcode) {
		case "SCM": {
			name = "Super Chinese 1 th??ng\nTh???i h???n VIP 1 th??ng\n";
			price = "????n gi?? " + vipPackageStore.getSellPrice_m() + " VN??\n";
			break;
		}
		case "SCY": {
			name = "Super Chinese 1 n??m\nTh???i h???n VIP 1 n??m\n";
			price = "????n gi?? " + vipPackageStore.getSellPrice_y() + " VN??\n";
			break;
		}
		case "SCL": {
			name = "Super Chinese Life Time\nTh???i h???n VIP kh??ng gi???i h???n\n";
			price = "????n gi?? " + vipPackageStore.getSellPrice_l() + " VN??\n";
			break;
		}
		case "STM": {
			name = "Super Test 1 th??ng\nTh???i h???n VIP 1 th??ng\n";
			price = "????n gi?? " + vipPackageStore.getSellPrice_st_m() + " VN??\n";
			break;
		}
		case "STY": {
			name = "Super Test 1 n??m\nTh???i h???n VIP 1 n??m\n";
			price = "????n gi?? " + vipPackageStore.getSellPrice_st_y() + " VN??\n";
			break;
		}
		case "STL": {
			name = "Super Test Life Time\nTh???i h???n VIP kh??ng gi???i h???n\n";
			price = "????n gi?? " + vipPackageStore.getSellPrice_st_l() + " VN??\n";
			break;
		}
		}
		return "S???n ph???m " + name + price;
	}

	/**
	 * messageReply
	 * 
	 * @param pack
	 * @param vipPackageStore
	 * @return
	 */
	public String messageReplyWeb(String pack, String zaloName, VipPackageStore vipPackageStore) {
		StringBuilder sb = new StringBuilder();
		sb.append("Super Chinese x??c nh???n,<br>");
		sb.append("C??m ??n " + zaloName
				+ " ???? tin t?????ng. Ch??ng t??i ???? nh???n ???????c y??u c???u ?????t h??ng c???a b???n. Th??ng tin chi ti???t ????n h??ng:<br>");
		sb.append("M?? ????n " + getOrderCode() + "<br>");
		sb.append("Tr???ng th??i ch??a thanh to??n<br>");
		sb.append(namePackWeb(pack, vipPackageStore));
		sb.append("Qu???n tr??? vi??n s??? li??n h??? ?????n b???n v?? x??? l?? ????n h??ng trong th???i gian s???m nh???t. Tr??n tr???ng c???m ??n.");
		return sb.toString();
	}

	/**
	 * namePack
	 * 
	 * @param packcode
	 * @param vipPackageStore
	 * @return
	 */
	public String namePackWeb(String packcode, VipPackageStore vipPackageStore) {

		String name = "";
		String price = "";
		switch (packcode) {
		case "SCM": {
			name = "Super Chinese 1 th??ng<br>Th???i h???n VIP 1 th??ng<br>";
			price = "????n gi?? " + vipPackageStore.getSellPrice_m() + " VN??<br>";
			break;
		}
		case "SCY": {
			name = "Super Chinese 1 n??m<br>Th???i h???n VIP 1 n??m<br>";
			price = "????n gi?? " + vipPackageStore.getSellPrice_y() + " VN??<br>";
			break;
		}
		case "SCL": {
			name = "Super Chinese Life Time<br>Th???i h???n VIP kh??ng gi???i h???n<br>";
			price = "????n gi?? " + vipPackageStore.getSellPrice_l() + " VN??\n";
			break;
		}
		case "STM": {
			name = "Super Test 1 th??ng<br>Th???i h???n VIP 1 th??ng<br>";
			price = "????n gi?? " + vipPackageStore.getSellPrice_st_m() + " VN??<br>";
			break;
		}
		case "STY": {
			name = "Super Test 1 n??m<br>Th???i h???n VIP 1 n??m<br>";
			price = "????n gi?? " + vipPackageStore.getSellPrice_st_y() + " VN??<br>";
			break;
		}
		case "STL": {
			name = "Super Test Life Time<br>Th???i h???n VIP kh??ng gi???i h???n<br>";
			price = "????n gi?? " + vipPackageStore.getSellPrice_st_l() + " VN??<br>";
			break;
		}
		}
		return "S???n ph???m " + name + price;
	}

	/**
	 * getOrderCode
	 * 
	 * @param
	 * @return
	 */
	public String getOrderCode() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(new Date());
	}
}
