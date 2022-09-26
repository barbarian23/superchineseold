package konec.superchinese.models.profile;

import java.util.List;

import konec.superchinese.commons.SpcApiResponse;

public class SpcAPIHistoryResponse extends SpcApiResponse {
	public List<SpcAPIHistoryInfo> data;

	public List<SpcAPIHistoryInfo> getData() {
		return data;
	}

	public void setData(List<SpcAPIHistoryInfo> data) {
		this.data = data;
	}
}
