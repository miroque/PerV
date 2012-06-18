package src.ru.md24inc.alembic.pervoc.core;

public class Card {
	// �� ���� ����� ����� ��� ����������, �� ��� ������������ �������� � ���
	// ��������.
	// �������� ����� ����� ��� ��� ����, ���� �� �����.
	// public static int cardId;
	private Word word;
	private Transcript transcript;
	private Translation translation;

	public Card() {
		word = new Word();
		transcript = new Transcript();
		translation = new Translation();
	}

	public Card(String wrd, String trc, String trs) {
		word = new Word(wrd);
		transcript = new Transcript(trc);
		translation = new Translation(trs);
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
}
