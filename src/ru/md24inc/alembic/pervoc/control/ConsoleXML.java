package src.ru.md24inc.alembic.pervoc.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.*;

import src.ru.md24inc.alembic.pervoc.core.*;

public class ConsoleXML {
	public final static String srcOfFile = "D:\\workspace\\PerV\\Xclay\\mud.xml";

	public static void main(String args[]) throws JDOMException, IOException {
		// Open XML File
		SAXBuilder sax = new SAXBuilder();
		File file = new File(srcOfFile);
		Document doc = sax.build(file);

		// Parsing XML Tree, getting Root Element and it's attributes
		Element root = doc.getRootElement();
		List<Attribute> rootAttrs = root.getAttributes();
		System.out.println("Root element: " + root.getName());
		for (Attribute at : rootAttrs)
			System.out.println("attr: " + at.getName() + " = " + at.getValue());

		// Parsing XML Tree, getting Children Element and it's attributes
		List<Element> cards = root.getChildren();
		for (Element card : cards) {
			System.out.println("\nCard: " + card.getName());
			List<Attribute> attrs = card.getAttributes();
			for (Attribute at : attrs)
				System.out.println(card.getName() + "'s element " + "attr: "
						+ at.getName() + " = " + at.getValue());
			parseChild(card);// Our method for parsing child elements
		}

		// Parsing XML Tree to Java Objects
		// Printing to System.out with tub
	}

	private static void parseChild(Element element) {
		List<Element> children = element.getChildren();
		for (Element child : children) {
			System.out.println("child: " + child.getName());
			System.out.println("childValue: " + child.getValue());

			parseChild(child);
		}

	}

	public static void openXMLFile(String fileName) {
		// Opens xml file, sort of.
	}
}
