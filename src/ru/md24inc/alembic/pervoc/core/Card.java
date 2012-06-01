package src.ru.md24inc.alembic.pervoc.core;

public class Card {
	// Ќе знаю особо зачем эта переменна€, но дл€ эксперимента обучени€ и дл€
	// будущего.
	// возможно потом удалю или еще чего, пока не пон€л.
	// public static int cardId;
	private Word word;
	private Transcript transcript;
	private Translation translation;
	
	public Card(){
		word = new Word();
		transcript = new Transcript();
		translation = new Translation();
	}

	public String getWord() {
		return word.getWord();
	}

	public void setWord(String word) {
		this.word.setWord(word);
	}

	public String getTranscript() {
		return transcript.getTranscript();
	}

	public void setTranscript(String transcript) {
		this.transcript.setTranscript(transcript);
	}

	public String getTranslation() {
		return translation.getTranslation();
	}

	public void setTranslation(String translation) {
		this.translation.setTranslation(translation);
	}

	public static void main(String args[]) {
		Card myCard = new Card();
		myCard.setWord("God");
		myCard.setTranscript("gad");
		myCard.setTranslation("Ѕог");
		System.out.println(myCard.getWord() + " " + myCard.getTranscript()
				+ " " + myCard.getTranslation());
	}

}
