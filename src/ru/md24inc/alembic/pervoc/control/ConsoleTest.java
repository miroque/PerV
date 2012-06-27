package src.ru.md24inc.alembic.pervoc.control;

import src.ru.md24inc.alembic.pervoc.core.*;

public class ConsoleTest {
	public static void main(String args[]) {
		// Initializing New Vocabular
		Vocabulary myVocabulary = new Vocabulary();

		// Filling each card with some words and info
		Card[] myCards = new Card[6];
		// добавил спец символов утф-8
		myCards[0] = new Card("Test", "S\u00ED Se\u00F1or", "Кракозябра");
		myCards[1] = new Card("God", "gad", "Бог");
		myCards[2] = new Card("hub", "Hab", "Концентратор. или место сборки");
		myCards[3] = new Card("Test", "S\u00ED Se\u00F1or", "Кракозябра");
		myCards[4] = new Card("God", "gad", "Бог");
		myCards[5] = new Card("hub", "Hab", "Концентратор. или место сборки");

		// Inserting cards into vocabulary
		myVocabulary.addCard(myCards[0]);
		myVocabulary.addCard(myCards[1]);
		myVocabulary.addCard(myCards[2]);
		myVocabulary.addCard(myCards[3]);
		myVocabulary.addCard(myCards[4]);
		myVocabulary.addCard(myCards[5]);

		// Printing Vocabular
		myVocabulary.printVocabular();
	}
}
