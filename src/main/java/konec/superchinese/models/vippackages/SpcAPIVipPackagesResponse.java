package konec.superchinese.models.vippackages;

import java.util.ArrayList;
import java.util.List;

import konec.superchinese.commons.SpcApiResponse;

public class SpcAPIVipPackagesResponse extends SpcApiResponse{
	List<SpcVIPPackagesData> data = new ArrayList<SpcVIPPackagesData>();

	public List<SpcVIPPackagesData> getData() {
		return data;
	}

	public void setData(List<SpcVIPPackagesData> data) {
		this.data = data;
	}
	
}
