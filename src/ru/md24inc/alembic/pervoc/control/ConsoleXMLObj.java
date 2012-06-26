package src.ru.md24inc.alembic.pervoc.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.*;
import org.jdom2.input.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import src.ru.md24inc.alembic.pervoc.core.*;

public class ConsoleXMLObj {
	public final static String srcOfFile = "D:\\workspace\\PerV\\Xclay\\mud.xml";
	public Vocabulary myVoc = new Vocabulary();

	public static void main(String args[]) throws JDOMException, IOException {
		// instantiate Vocabulary object
		Vocabulary vocaBul = new Vocabulary();
		Card card;

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(srcOfFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("card");
			System.out.println(nList.getLength());

			for (int i = 0; i < nList.getLength(); i++) {
				card = new Card();

				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					card.setWord(getTagValue(("word"), eElement));
					card.setTranscript(getTagValue(("transcript"), eElement));
					card.setTranslation(getTagValue(("translation"), eElement));
				}

				// add a person to an address book
				vocaBul.add(card);

			}// end for

		}

		catch (Exception e) {
			System.out.println(e);
		}
		
		vocaBul.printAll();

	}

	public static void openXMLFile(String fileName) {
		// Opens xml file, sort of.
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}
}
