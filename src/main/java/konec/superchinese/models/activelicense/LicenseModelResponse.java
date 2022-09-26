package konec.superchinese.models.activelicense;

import java.util.HashMap;

public class LicenseModelResponse {
	public Integer return_code;
	public String return_message;
	public boolean hasError;
	public HashMap<String,String> error = new HashMap<String, String>();
	
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	public Integer getReturn_code() {
		return return_code;
	}
	public void setReturn_code(Integer return_code) {
		this.return_code = return_code;
	}
	public String getReturn_message() {
		return return_message;
	}
	public void setReturn_message(String return_message) {
		this.return_message = return_message;
	}
	
	public HashMap<String, String> getError() {
		return error;
	}
	public void setError(String key, String value) {
		this.error.put(key, value);
	}
}
