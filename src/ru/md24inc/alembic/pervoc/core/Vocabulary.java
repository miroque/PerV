package src.ru.md24inc.alembic.pervoc.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Vocabulary for making XML to object
 * 
 * @author Alexander Panov
 * 
 * @version 0.1
 */

public class Vocabulary {

	protected List<Card> voc = new ArrayList<Card>();

	/**
	 * Class constructor.
	 */

	public Vocabulary() {
		System.out.println("Initial Vocab");
	}

	public void add(Card c) {
		voc.add(c);
	}

	public void printAll() {
		System.out.println("Printing All Cards");
		for (int i = 0; i < voc.size(); i++) {
			System.out.println("\t" + voc.get(i).getWord() + " \t["
					+ voc.get(i).getTranscript() + "] \t"
					+ voc.get(i).getTranslation());
		}

	}

}
