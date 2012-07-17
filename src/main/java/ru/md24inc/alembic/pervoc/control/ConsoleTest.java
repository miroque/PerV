package ru.md24inc.alembic.pervoc.control;

import ru.md24inc.alembic.pervoc.core.*;
import ru.md24inc.alembic.pervoc.dao.Vocabulary;

import java.util.ArrayList;
import java.util.Collection;

public class ConsoleTest {
	public static void main(String args[]) {
		// Initializing New Vocabular
		Vocabulary myVocabulary = new Vocabulary();

		// Filling each card with some words and info
		final Collection<Card> myCards = new ArrayList<Card>(6);
		// Inserting cards into vocabulary
		myCards.add(new Card("Test", "S\u00ED Se\u00F1or", "Кракозябра"));
		myCards.add(new Card("God", "gad", "Бог"));
		myCards.add(new Card("hub", "Hab", "Концентратор. или место сборки"));
		myCards.add(new Card("Test", "S\u00ED Se\u00F1or", "Кракозябра"));
		myCards.add(new Card("God", "gad", "Бог"));
		myCards.add(new Card("hub", "Hab", "Концентратор. или место сборки"));

		// Printing Vocabular
		myVocabulary.printVocabular(myCards);
	}
}
