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
		sb.append("Super Chinese xác nhận,\n");
		sb.append("Cám ơn " + zaloName
				+ " đã tin tưởng. Chúng tôi đã nhận được yêu cầu đặt hàng của bạn. Thông tin chi tiết đơn hàng:\n");
		sb.append("Mã đơn " + getOrderCode() + "\n");
		sb.append("Trạng thái chưa thanh toán\n");
		sb.append(namePack(pack, vipPackageStore));
		sb.append("Quản trị viên sẽ liên hệ đến bạn và xử lý đơn hàng trong thời gian sớm nhất. Trân trọng cảm ơn.");
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
		sb.append("Super Chinese xác nhận,\n");
		sb.append("Cám ơn " + zaloName
				+ " đã tin tưởng. Chúng tôi đã nhận được yêu cầu đăng ký làm đại lý của bạn. Thông tin chi tiết:\n");
		sb.append("Email  " + email + "\n");
		sb.append("Số điện thoại " + phone + "\n");
		sb.append("Quản trị viên sẽ liên hệ đến bạn trong thời gian sớm nhất. Trân trọng cảm ơn.");
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
			name = "Super Chinese 1 tháng\nThời hạn VIP 1 tháng\n";
			price = "Đơn giá " + vipPackageStore.getSellPrice_m() + " VNĐ\n";
			break;
		}
		case "SCY": {
			name = "Super Chinese 1 năm\nThời hạn VIP 1 năm\n";
			price = "Đơn giá " + vipPackageStore.getSellPrice_y() + " VNĐ\n";
			break;
		}
		case "SCL": {
			name = "Super Chinese Life Time\nThời hạn VIP không giới hạn\n";
			price = "Đơn giá " + vipPackageStore.getSellPrice_l() + " VNĐ\n";
			break;
		}
		case "STM": {
			name = "Super Test 1 tháng\nThời hạn VIP 1 tháng\n";
			price = "Đơn giá " + vipPackageStore.getSellPrice_st_m() + " VNĐ\n";
			break;
		}
		case "STY": {
			name = "Super Test 1 năm\nThời hạn VIP 1 năm\n";
			price = "Đơn giá " + vipPackageStore.getSellPrice_st_y() + " VNĐ\n";
			break;
		}
		case "STL": {
			name = "Super Test Life Time\nThời hạn VIP không giới hạn\n";
			price = "Đơn giá " + vipPackageStore.getSellPrice_st_l() + " VNĐ\n";
			break;
		}
		}
		return "Sản phẩm " + name + price;
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
		sb.append("Super Chinese xác nhận,<br>");
		sb.append("Cám ơn " + zaloName
				+ " đã tin tưởng. Chúng tôi đã nhận được yêu cầu đặt hàng của bạn. Thông tin chi tiết đơn hàng:<br>");
		sb.append("Mã đơn " + getOrderCode() + "<br>");
		sb.append("Trạng thái chưa thanh toán<br>");
		sb.append(namePackWeb(pack, vipPackageStore));
		sb.append("Quản trị viên sẽ liên hệ đến bạn và xử lý đơn hàng trong thời gian sớm nhất. Trân trọng cảm ơn.");
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
			name = "Super Chinese 1 tháng<br>Thời hạn VIP 1 tháng<br>";
			price = "Đơn giá " + vipPackageStore.getSellPrice_m() + " VNĐ<br>";
			break;
		}
		case "SCY": {
			name = "Super Chinese 1 năm<br>Thời hạn VIP 1 năm<br>";
			price = "Đơn giá " + vipPackageStore.getSellPrice_y() + " VNĐ<br>";
			break;
		}
		case "SCL": {
			name = "Super Chinese Life Time<br>Thời hạn VIP không giới hạn<br>";
			price = "Đơn giá " + vipPackageStore.getSellPrice_l() + " VNĐ\n";
			break;
		}
		case "STM": {
			name = "Super Test 1 tháng<br>Thời hạn VIP 1 tháng<br>";
			price = "Đơn giá " + vipPackageStore.getSellPrice_st_m() + " VNĐ<br>";
			break;
		}
		case "STY": {
			name = "Super Test 1 năm<br>Thời hạn VIP 1 năm<br>";
			price = "Đơn giá " + vipPackageStore.getSellPrice_st_y() + " VNĐ<br>";
			break;
		}
		case "STL": {
			name = "Super Test Life Time<br>Thời hạn VIP không giới hạn<br>";
			price = "Đơn giá " + vipPackageStore.getSellPrice_st_l() + " VNĐ<br>";
			break;
		}
		}
		return "Sản phẩm " + name + price;
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
