package objects;

import java.util.List;

public class AnsPreferences {
	/*
	 * 00 - Succes
	 * 01 - Not bigger than 0
	 */
	private Answer answer;
	private Preferences preferences;
	private List<Preferences> listPreferences;



	public Answer getAnswer() {
		return answer;
	}



	public void setAnswer(Answer answer) {
		this.answer = answer;
	}



	public Preferences getPreferences() {
		return preferences;
	}



	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
	

}
