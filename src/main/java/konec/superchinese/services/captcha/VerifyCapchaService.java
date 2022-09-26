package konec.superchinese.services.captcha;

import org.springframework.stereotype.Service;

@Service
public class VerifyCapchaService {
//	@Value("${captcha.enable}")
//	private boolean isEnable;
//
//	@Value("${captcha.sitekey}")
//	private String sitekey;
//
//	@Value("${captcha.secretkey}")
//	private String secretkey;
//
//	@Value("${captcha.siteverifyurl}")
//	private String siteverifyurl;

//	public boolean verifyCapcha(String capcha) throws Exception {
//		if(isEnable) {
//			RestTemplate restTemplate = new RestTemplate();
//			URI uri = new URI(siteverifyurl);
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
//			map.add("secret", secretkey);
//			map.add("response", capcha);
//			HttpEntity<MultiValueMap<String, String>> requests = new HttpEntity<>(map, headers);
//			ResponseEntity<String> result = restTemplate.postForEntity(uri, requests, String.class);
//			ObjectMapper mapper = new ObjectMapper();
//			if (result.getStatusCodeValue() == 200) {
//				@SuppressWarnings("unchecked")
//				HashMap<String, String> mapRes = (HashMap<String, String>) mapper.readValue(result.getBody(),
//						Map.class);
//				return String.valueOf(mapRes.get("success")).equals("true");
//
//			} else {
//				return false;
//			}
//		} else {
//			return true;
//		}
//	}

}
