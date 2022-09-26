package konec.superchinese.services.profile;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import konec.superchinese.models.profile.HistoryModel;
import konec.superchinese.models.profile.ProfileModel;
import konec.superchinese.models.profile.SpcAPIHistoryInfo;
import konec.superchinese.models.profile.SpcAPIHistoryResponse;
import konec.superchinese.models.profile.SpcAPIPackActiveInfo;
import konec.superchinese.models.profile.SpcAPIProfileRequest;
import konec.superchinese.models.profile.SpcAPIProfileResponse;
import konec.superchinese.models.profile.SpcAPIUserInfo;
import konec.superchinese.services.logs.LogService;

@Service
public class ProfileService {
	
	@Autowired
	LogService logService;

	public ProfileModel getFrofileInfo(String accessToken, URI url, ProfileModel profileModel) throws Exception {
		SpcAPIProfileRequest spcAPIProfileRequest = new SpcAPIProfileRequest();
		spcAPIProfileRequest.setAccess_token(accessToken);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SpcAPIProfileRequest> entity = new HttpEntity<SpcAPIProfileRequest>(spcAPIProfileRequest, headers);

		ResponseEntity<SpcAPIProfileResponse> result = restTemplate.exchange(url, HttpMethod.POST, entity,
				SpcAPIProfileResponse.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(entity);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("getFrofileInfo","api/spc/get_user_profile",requestLog,responseLog);

		if (result.getStatusCodeValue() == 200) {
			if (result.getBody().getReturn_code() == 1) {
				// login succesful
				List<SpcAPIPackActiveInfo> active_packages = result.getBody().getActive_packages();
				SpcAPIUserInfo spcAPIUserInfo = result.getBody().getUser_info();
				if (active_packages.size() > 0) {
					for (SpcAPIPackActiveInfo info : active_packages) {
						if (info.getPackageCode().contains("SC")) {
							profileModel.setPackageCode_sc(info.getPackageCode());
							profileModel.setStartTime_sc(convertMilisecondsToDate(info.getStartTime()));
							profileModel.setEndTime_sc(convertMilisecondsToDate(info.getEndTime()));
							profileModel.setCreateTime_sc(convertMilisecondsToDate(info.getCreateTime()));
							profileModel.setRenewDay_sc(info.getRenewDay());
							profileModel.setRenewCountInDay_sc(info.getRenewCountInDay());
						}
						if (info.getPackageCode().contains("ST")) {
							profileModel.setPackageCode_st(info.getPackageCode());
							profileModel.setStartTime_st(convertMilisecondsToDate(info.getStartTime()));
							profileModel.setEndTime_st(convertMilisecondsToDate(info.getEndTime()));
							profileModel.setCreateTime_st(convertMilisecondsToDate(info.getCreateTime()));
							profileModel.setRenewDay_st(info.getRenewDay());
							profileModel.setRenewCountInDay_st(info.getRenewCountInDay());
						}
					}
				}
				profileModel.setPhone(spcAPIUserInfo.getPhone());
				profileModel.setName(spcAPIUserInfo.getName());
				profileModel.setEmail(spcAPIUserInfo.getEmail());
				profileModel.setStatus(spcAPIUserInfo.getStatus());
				profileModel.setUserId(spcAPIUserInfo.getZaloUserId());
			}
		}
		return profileModel;
	}

	public HistoryModel getHistoryTransaction(String accessToken, URI url, HistoryModel historyModel) throws Exception{
		SpcAPIProfileRequest spcAPIProfileRequest = new SpcAPIProfileRequest();
		spcAPIProfileRequest.setAccess_token(accessToken);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<SpcAPIProfileRequest> entity = new HttpEntity<SpcAPIProfileRequest>(spcAPIProfileRequest, headers);

		ResponseEntity<SpcAPIHistoryResponse> result = restTemplate.exchange(url, HttpMethod.POST, entity,
				SpcAPIHistoryResponse.class);
		
		ObjectMapper mapperLog = new ObjectMapper();
		String requestLog = mapperLog.writeValueAsString(entity);
		String responseLog = mapperLog.writeValueAsString(result);
		logService.writeLogApi("getHistoryTransaction","api/spc/get_license_list",requestLog,responseLog);
		
		if (result.getStatusCodeValue() == 200) {
			if (result.getBody().getReturn_code() == 1) {
				List<SpcAPIHistoryInfo> data = result.getBody().getData();
				if (data.size() > 0) {
					data.forEach(d -> d.setCreateTime(convertMilisecondsToDate(d.getCreateTime())));
					historyModel.setData(data);
				}
			}
		}

		return historyModel;
	}

	public String convertMilisecondsToDate(String milisecond) {
		if(milisecond != null && milisecond !="") {
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
}
