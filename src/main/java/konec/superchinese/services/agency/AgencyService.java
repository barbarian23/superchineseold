package konec.superchinese.services.agency;

import java.net.URI;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import konec.superchinese.models.agency.AgencyModel;
import konec.superchinese.models.agency.AgencyModelResponse;
import konec.superchinese.models.agency.AgencyResponse;
import konec.superchinese.models.agency.SpcAPIAgencyRequest;
import konec.superchinese.services.logs.LogService;

@Service
public class AgencyService {
	@Autowired
	LogService logService;
	
	public static final String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static final String regexPhone = "^[0-9]{10}$";

	/**
	 * validate
	 * 
	 * @param agencyModel
	 * @param agency
	 * @return
	 */
	public boolean validate(AgencyModel agencyModel, AgencyModelResponse agency) {
		if (agencyModel.getEmail() == null || !patternMatches(agencyModel.getEmail(), regexEmail)) {
			agency.setError("email", "Email không hợp lệ!");
			agency.setReturn_code(99);
			agencyModel.setHasError(true);
		}
		if (agencyModel.getPhoneNumber() == null || !patternMatches(agencyModel.getPhoneNumber(), regexPhone)) {
			agency.setError("phoneNumber", "Số điện thoại không hợp lệ!");
			agency.setReturn_code(99);
			agencyModel.setHasError(true);
		}
		return agencyModel.isHasError() ? true : false;
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
	 * regist agency
	 * 
	 * @param url
	 * @param Model
	 * @param agency
	 * @return
	 * @throws Exception
	 */
	public AgencyModelResponse registAgency(String url, AgencyModel Model, AgencyModelResponse agency)
			throws Exception {
		URI uri = new URI(url);
		SpcAPIAgencyRequest Request = new SpcAPIAgencyRequest();
		Request.setEmail(Model.getEmail());
		Request.setPhone(Model.getPhoneNumber());
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SpcAPIAgencyRequest> entity = new HttpEntity<SpcAPIAgencyRequest>(Request, headers);
		ResponseEntity<AgencyResponse> result = restTemplate.exchange(uri, HttpMethod.POST, entity,
				AgencyResponse.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(entity);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("regist_agency","api/spc/register_agency",requestLog,responseLog);
		
		if (result.getStatusCodeValue() == 200) {
			if (result.getBody().getReturn_code() == 1) {
				agency.setReturn_code(1);
				agency.setReturn_message("Cảm ơn bạn đã liên hệ, thông tin của bạn đã được ghi nhận. Chúng tôi sẽ liên hệ với bạn trong thời gian sớm nhất!");
			} else if (result.getBody().getReturn_code() == 1400) {
				agency.setReturn_code(1400);
				agency.setError("general", "Email/Số điện thoại đã được đăng ký!");
				agency.setReturn_message(result.getBody().getReturn_message());
			} else {
				agency.setReturn_code(99);
				agency.setError("general", "Email/Số điện thoại không hợp lệ!");
				agency.setReturn_message("Đăng ký thất bại");
			}
		}

		return agency;
	}
}
