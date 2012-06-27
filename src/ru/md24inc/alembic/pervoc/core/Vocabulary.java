package src.ru.md24inc.alembic.pervoc.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class Vocabulary for making XML to object
 * 
 * @author Alexander Panov
 * 
 * @version 0.2
 */

public class Vocabulary {

	protected List<Card> voc;

	/**
	 * Class constructor.
	 */

	public Vocabulary() {
		System.out.println("Initial Vocab");
		voc = new ArrayList<Card>();
	}

	public void addCard(Card c) {
		voc.add(c);
	}

	public void printVocabular() {
		System.out.println("Printing All Cards");
		for (int i = 0; i < voc.size(); i++) {
			System.out.println("\t" + voc.get(i).getWord() + " \t["
					+ voc.get(i).getTranscript() + "] \t"
					+ voc.get(i).getTranslation());
		}

	}

	public void openXMLFile(String fileName) {
		Card card;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fileName);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("card");

			for (int i = 0; i < nList.getLength(); i++) {
				card = new Card();

				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					card.setWord(getTagValue(("word"), eElement));
					card.setTranscript(getTagValue(("transcript"), eElement));
					card.setTranslation(getTagValue(("translation"), eElement));
				}

				voc.add(card);

			}// end for

		}// end try

		catch (Exception e) {
			System.out.println(e);
		}

	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}
