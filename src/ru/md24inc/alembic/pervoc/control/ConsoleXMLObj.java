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

	public static void main(String args[]) throws JDOMException, IOException {
		// How i would like to see this process?
		Vocabulary vocaBul = new Vocabulary();
		vocaBul.openXMLFile(srcOfFile);
		vocaBul.printVocabular();
	}
}