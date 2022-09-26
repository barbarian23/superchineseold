package konec.superchinese.services.activelicense;

import java.net.URI;
import java.util.HashMap;
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

import konec.superchinese.commons.MessageUtils;
import konec.superchinese.models.activelicense.LicenseModel;
import konec.superchinese.models.activelicense.LicenseModelResponse;
import konec.superchinese.models.activelicense.LicenseResponse;
import konec.superchinese.models.activelicense.SpcAPILicenseRequest;
import konec.superchinese.services.logs.LogService;

@Service
public class LicenseService implements MessageUtils {
	public static final String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static final String regexPhone = "^[0-9]{10}$";

	public static final String regexCode = "^[0-9]{12,18}$";
	
	@Autowired
	LogService logService;
	
	/**
	 * validate
	 * 
	 * @param licenseModel
	 * @param license
	 * @return
	 */
	public boolean validate(LicenseModel licenseModel, LicenseModelResponse license) {
		if ((licenseModel.getEmail() == null || !patternMatches(licenseModel.getEmail(), regexEmail))
				&& (licenseModel.getEmail() == null || !patternMatches(licenseModel.getEmail(), regexPhone))) {
			license.setError("email", ERROR_VALIDATE_ACCOUNT_NOT_VALID);
			license.setReturn_code(99);
			license.setHasError(true);
		}

		if (licenseModel.getCodeAc() == null || !patternMatches(licenseModel.getCodeAc(), regexCode)) {
			license.setError("codeAc", ERROR_LICENSE_NOT_VALID);
			license.setReturn_code(99);
			licenseModel.setHasError(true);
		}
		return licenseModel.isHasError() ? true : false;
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
	 * active license code
	 * 
	 * @param url
	 * @param token
	 * @param model
	 * @param license
	 * @return
	 * @throws Exception
	 */
	public LicenseModelResponse activeLicense(String url, String token, LicenseModel model,
			LicenseModelResponse license) throws Exception {
		URI uri = new URI(url);
		SpcAPILicenseRequest request = new SpcAPILicenseRequest();
		request.setAccess_token(token);
		request.setAccount(model.getEmail());
		request.setLicense_code(model.getCodeAc());
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SpcAPILicenseRequest> entity = new HttpEntity<SpcAPILicenseRequest>(request, headers);
		ResponseEntity<LicenseResponse> result = restTemplate.exchange(uri, HttpMethod.POST, entity,
				LicenseResponse.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("active_License","api/spc/activate_license_code",requestLog,responseLog);
		
		if (result.getStatusCodeValue() == 200) {
			if (result.getBody().getReturn_code() == 1) {
				license.setReturn_code(result.getBody().getReturn_code());
				license.setReturn_message(SUCCESS_LICENSE_ACTIVE_SUCCESS);
			} else if (result.getBody().getReturn_code() == 1302) {
				license.setReturn_code(99);
				license.setError("general", ERROR_LICENSE_NOT_EXIST);
				license.setReturn_message(result.getBody().getReturn_message());
			} else if (result.getBody().getReturn_code() == 1303) {
				license.setReturn_code(99);
				license.setError("general", ERROR_LICENSE_HAS_ACTIVED);
				license.setReturn_message(ERROR_ACTIVE_FAILED);
			} else if (result.getBody().getReturn_code() == 1304) {
				license.setReturn_code(99);
				license.setError("general", ERROR_LICENSE_DONOT_EXIST_PACKAGE);
				license.setReturn_message(ERROR_ACTIVE_FAILED);
			} else if (result.getBody().getReturn_code() == 1305) {
				license.setReturn_code(99);
				license.setError("general", ERROR_LICENSE_DONOT_MATCH_ACCOUNT);
				license.setReturn_message(ERROR_ACTIVE_FAILED);
			} else if (result.getBody().getReturn_code() == 1309) {
				license.setReturn_code(99);
				license.setError("general", ERROR_LICENSE_NOT_EXPIRE_YET);
				license.setReturn_message(ERROR_ACTIVE_FAILED);
			} else if (result.getBody().getReturn_code() == 1310) {
				license.setReturn_code(99);
				license.setError("general", result.getBody().getReturn_message());
				license.setReturn_message(ERROR_ACTIVE_FAILED);
			} else {
				license.setReturn_code(99);
				license.setError("general", ERROR_LICENSE_SERVER_BUSY);
				license.setReturn_message(ERROR_ACTIVE_FAILED);
			}
		}
		return license;
	}
}
