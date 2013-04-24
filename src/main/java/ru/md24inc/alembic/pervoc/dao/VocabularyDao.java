package ru.md24inc.alembic.pervoc.dao;

import java.io.FileReader;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ru.md24inc.alembic.pervoc.domains.Card;
import ru.md24inc.alembic.pervoc.domains.Vocabulary;

/**
 * Class for making XML to object
 * 
 * @author Alexander Panov
 * 
 * @version 0.4
 */
public class VocabularyDao {

	/**
	 * Class constructor.
	 */
	public VocabularyDao() {
		System.out.println("Initial VocabularyDao");
	}

	/**
	 * Printing to console the content of vocabulary in tabs columns
	 */
	public void printToConsole(Collection<Card> cards) {
		System.out.println("Printing All Cards");
		for (Card card : cards) {
			System.out.println("\t" + card.getWord() + " \t["
					+ card.getTranscript() + "] \t" + card.getTranslation());
		}
	}

	/**
	 * Opens XML file of Vocabulary and fills up Vocabulary
	 * 
	 * @param fileName
	 *            String for source XML file
	 */
	public List<Card> getVocabular(String fileName) {
		List<Card> result = null;
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(Vocabulary.class);
			final Unmarshaller um = jaxbContext.createUnmarshaller();
			Vocabulary myVoc = (Vocabulary) um.unmarshal(new FileReader(fileName));
			result = myVoc.getCard();
			System.out.println(result.toString());
		} catch (Exception e) {
			// I guess that one of the solution is
			// converting this input file to UTF-8 without BOM
			e.printStackTrace();
		}
		return result;
	}

}
