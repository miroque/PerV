package src.gui;

import java.util.Date;

public class Card {
	private int id;
	private Date cardDate;
	private String word;
    private String transcript;
    private String translation;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCardDate() {
		return cardDate;
	}
	public void setCardDate(Date cardDate) {
		this.cardDate = cardDate;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getTranscript() {
		return transcript;
	}
	public void setTranscript(String transcript) {
		this.transcript = transcript;
	}
	public String getTranslation() {
		return translation;
	}
	public void setTranslation(String translation) {
		this.translation = translation;
	}

}

