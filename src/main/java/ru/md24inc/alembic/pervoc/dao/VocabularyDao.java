package ru.md24inc.alembic.pervoc.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import ru.md24inc.alembic.pervoc.domains.CardType;
import ru.md24inc.alembic.pervoc.domains.VocabularyType;

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
	public void printToConsole(Collection<CardType> cards) {
		System.out.println("Printing All Cards");
		for (CardType card : cards) {
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
	public Collection<CardType> openFile(String fileName) {
		Collection<CardType> result = null;
		try {
			final JAXBContext jaxbContext = JAXBContext.newInstance(VocabularyType.class);
			final Unmarshaller um = jaxbContext.createUnmarshaller();
			VocabularyType myVoc = (VocabularyType) um.unmarshal(new FileReader(fileName));
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
