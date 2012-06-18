package src.ru.md24inc.alembic.pervoc.core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class IoUtils {

	//
	// Data Members
	//
	// xml file location - from user’s input
	public static String fileLocation;

	// TABLE META DATA
	public static final String ROOT_ELEMENT_TAG = "vocabulary";

	//
	// Methods
	//

	/**
	 * Return Vocabulary object
	 * 
	 * @return a Vocabulary Object
	 */

	public static Vocabulary getVocabulary(String file) {

		fileLocation = file;
		// instantiate Vocabulary object
		Vocabulary vocaBul = new Vocabulary();
		Card card;

		try {
			// convert comming file location into input stream
			// InputStream is = new FileInputStream(file);

			// create xml document
			// File fXmlFile = new File("d:/workspace/PerVoc/clay/mud.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("card");

			for (int i = 0; i < nList.getLength(); i++) {

				// instanticate a Card object
				card = new Card();

				// from another tutorial
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					card.setWord(getTagValue(("word"), eElement));
				}

				// add a person to an address book
				vocaBul.add(card);

			}// end for

		}

		catch (Exception e) {
			System.out.println(e);
			return vocaBul;
		}

		return vocaBul;

	}// end method

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}// end of IoUtils class
