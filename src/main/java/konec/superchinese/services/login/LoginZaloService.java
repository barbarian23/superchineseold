package konec.superchinese.services.login;

import java.net.URI;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import konec.superchinese.models.loginzalo.RefreshTokenRequest;
import konec.superchinese.models.loginzalo.RefreshTokenResponse;
import konec.superchinese.models.profile.SpcAPILoginRequest;
import konec.superchinese.models.profile.SpcAPILoginResponse;
import konec.superchinese.services.logs.LogService;

@Service
public class LoginZaloService {
		
	@Value("${logOutZaloUrl}")
	private String logOutZaloUrl;
	
	@Autowired
	LogService logService;

	/**
	 * get accesstoken from zalo
	 * 
	 * @param code
	 * @param app_id
	 * @param secret_key
	 * @param access_token_url
	 * @param verifierCode
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getAccessTokenFromZalo(String code, String app_id, String secret_key,
			String access_token_url, String verifierCode) throws Exception {
		// set access token from authorization code
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.set("code", code);
		params.set("app_id", app_id);
		params.set("grant_type", "authorization_code");
		params.set("code_verifier", verifierCode);
		URI uri = new URI(access_token_url);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.set("secret_key", secret_key);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(request);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("getAccessTokenFromZalo","https://oauth.zaloapp.com/v4/access_token",requestLog,responseLog);

		ObjectMapper mapper = new ObjectMapper();
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			@SuppressWarnings("unchecked")
			Map<String, String> map = mapper.readValue(result.getBody(), Map.class);
			return map;
		} else {
			return new HashMap<String, String>();
		}
	}

	/**
	 * Insert AccessToken to database
	 * 
	 * @param accesstoken
	 * @param spcApiLogin
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public boolean saveToken(String accesstoken, String refreshAccesstoken, String spcApiLogin) throws Exception {
		SpcAPILoginRequest spcAPILoginRequest = new SpcAPILoginRequest();
		spcAPILoginRequest.setAccess_token(accesstoken);
		spcAPILoginRequest.setRefresh_token(refreshAccesstoken);
		RestTemplate restTemplate = new RestTemplate();
		URI spcApiLoginUri = new URI(spcApiLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SpcAPILoginRequest> entity = new HttpEntity<SpcAPILoginRequest>(spcAPILoginRequest, headers);

		ResponseEntity<SpcAPILoginResponse> result = restTemplate.exchange(spcApiLoginUri, HttpMethod.POST, entity,
				SpcAPILoginResponse.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(entity);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("saveToken","api/spc/login_by_zalo",requestLog,responseLog);
		
		if (result.getStatusCodeValue() == 200) {
			if (result.getBody().getReturn_code() == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Insert AccessToken to database
	 * 
	 * @param accesstoken
	 * @param spcApiLogin
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<SpcAPILoginResponse> checkToken(String accesstoken, String spcApiLogin) throws Exception {
		SpcAPILoginRequest spcAPILoginRequest = new SpcAPILoginRequest();
		spcAPILoginRequest.setAccess_token(accesstoken);
		RestTemplate restTemplate = new RestTemplate();
		URI spcApiLoginUri = new URI(spcApiLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SpcAPILoginRequest> entity = new HttpEntity<SpcAPILoginRequest>(spcAPILoginRequest, headers);

		ResponseEntity<SpcAPILoginResponse> result = restTemplate.exchange(spcApiLoginUri, HttpMethod.POST, entity,
				SpcAPILoginResponse.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(entity);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("checkToken","api/spc/check_zalo_token",requestLog,responseLog);
		
		return result;
	}

	/**
	 * get new access token from refresh token
	 * 
	 * @param access_token_url
	 * @param app_id
	 * @param secret_key
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<RefreshTokenResponse> getNewAccessToken(String access_token_url, String app_id,
			String secret_key, HttpSession session, HttpServletRequest request) throws Exception {
		// hard code
		String refresh_token = "";// get value from database
		// end hard code
		HttpHeaders headers = new HttpHeaders();
		RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
		refreshTokenRequest.setRefresh_token(refresh_token);
		refreshTokenRequest.setApp_id(app_id);
		refreshTokenRequest.setGrant_type(refresh_token);
		RestTemplate restTemplate = new RestTemplate();
		URI uri = new URI(access_token_url);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("secret_key", secret_key);
		HttpEntity<RefreshTokenRequest> entity = new HttpEntity<RefreshTokenRequest>(refreshTokenRequest, headers);

		ResponseEntity<RefreshTokenResponse> result = restTemplate.exchange(uri, HttpMethod.POST, entity,
				RefreshTokenResponse.class);

		// get access token from refresh token
		if (result.getStatusCodeValue() == 201) {
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 * logout zalo
	 * 
	 * @param accessToken
	 * @throws Exception
	 */
	public void logoutZalo(String accessToken) throws Exception {
		Logger logger = LoggerFactory.getLogger(getClass());
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		URI uri = new URI(logOutZaloUrl);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<HashMap<String, String>> request = new HttpEntity<>(params, headers);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

		ObjectMapper mapper = new ObjectMapper();
		logger.info("logoutZalo status value : " + result.getStatusCodeValue());
		logger.info("logoutZalo body : " + result.getBody());
		// get access token from refresh token
		if (result.getStatusCodeValue() == 200) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> map = (HashMap<String, String>) mapper.readValue(result.getBody(), Map.class);
			logger.info("logoutZalo map : " + map);
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
		} else {
			params.put("getStatusCodeValue", String.valueOf(result.getStatusCodeValue()));
		}
	}

	/**
	 * create random verifier code with 43 character
	 * 
	 * @return String
	 */
	public String verifierCodeGenerate() {
		SecureRandom sr = new SecureRandom();  
        byte[] code = new byte[32];  
        sr.nextBytes(code);  
        String verifier = Base64.getUrlEncoder().withoutPadding().encodeToString(code);  
        return verifier;
	}

	/**
	 * create code challenge from code verifier
	 * 
	 * @param codeVerifier
	 * @return
	 */
	public String getCodeChallenge(String  verifierCode) {
		String result = null;  
        try {  
            byte[] bytes = verifierCode.getBytes("US-ASCII");  
            MessageDigest md = MessageDigest.getInstance("SHA-256");  
            md.update(bytes, 0, bytes.length);  
            byte[] digest = md.digest();  
            result = Base64.getUrlEncoder().withoutPadding().encodeToString(digest);  
        } catch (Exception ex) {
        }  
        return result;  
	}
}
