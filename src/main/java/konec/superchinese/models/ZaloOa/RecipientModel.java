package konec.superchinese.models.ZaloOa;

public class RecipientModel {
	public String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public RecipientModel(String user_id) {
		super();
		this.user_id = user_id;
	}
}
