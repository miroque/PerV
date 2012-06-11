package src.ru.md24inc.alembic.pervoc.core;

/**
 * Class Word for making XML to object
 *
 * @author Alexander Panov
 *
 * @version 0.1
 */

public class Word {
	private String word;

	/** 
	 * Class constructor.
	 */
	public Word(){
		
	}
	
	/** 
	 * Class constructor.
	 * @param word	String word to write into Word
	 */
	public Word(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
