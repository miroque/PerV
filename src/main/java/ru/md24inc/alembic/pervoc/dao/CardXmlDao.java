package ru.md24inc.alembic.pervoc.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import ru.md24inc.alembic.pervoc.core.Card;

/**
 * Class for making XML to object
 *
 * @author Alexander Panov
 *
 * @version 0.2
 */
public class CardXmlDao {

    /**
     * Class constructor.
     */
    public CardXmlDao() {
        System.out.println("Initial Vocab");
    }

    /**
     * Printing to console the content of vocabulary in tabs columns
     */
    public void printToConsole(Collection<Card> cards) {
        System.out.println("Printing All Cards");
        for (Card card : cards) {
            System.out.println("\t" + card.getWord() + " \t["
                                    + card.getTranscript() + "] \t"
                                    + card.getTranslation());
        }
    }

    /**
     * Opens XML file of Vocabulary and fills up Vocabulary
     *
     * @param fileName String for source XML file
     */
    //TODO Add some kind of Error Exceptions
    // Method tries to do too many things. Need to separate it apart in future
    public Collection<Card> openXMLFileAndGetAll(String fileName) {
        final Collection<Card> result = new ArrayList<Card>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileName);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("card");

            for (int i = 0; i < nList.getLength(); i++) {
                final Card card = new Card();

                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    card.setWord(getTagValue(("word"), eElement));
                    card.setTranscript(getTagValue(("transcript"), eElement));
                    card.setTranslation(getTagValue(("translation"), eElement));
                }

                result.add(card);

            }// end for

        }// end try
        catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }
//    public Collection<Card> openXMLFileAndGetAll(File  file) {
//        final Collection<Card> result = new ArrayList<Card>();
//        try {
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document doc = dBuilder.parse(file.getAbsolutePath());
//            doc.getDocumentElement().normalize();
//            NodeList nList = doc.getElementsByTagName("card");
//
//            for (int i = 0; i < nList.getLength(); i++) {
//                final Card card = new Card();
//
//                Node nNode = nList.item(i);
//                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//                    Element eElement = (Element) nNode;
//                    card.setWord(getTagValue(("word"), eElement));
//                    card.setTranscript(getTagValue(("transcript"), eElement));
//                    card.setTranslation(getTagValue(("translation"), eElement));
//                }
//
//                result.add(card);
//
//            }// end for
//
//        }// end try
//        catch (Exception e) {
//            System.out.println(e);
//        }
//
//        return result;
//    }
    public List<Card> openXMLFileAndGetAll(File  file) {
        final List<Card> result = new ArrayList<Card>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file.getAbsolutePath());
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("card");

            for (int i = 0; i < nList.getLength(); i++) {
                final Card card = new Card();

                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    card.setWord(getTagValue(("word"), eElement));
                    card.setTranscript(getTagValue(("transcript"), eElement));
                    card.setTranslation(getTagValue(("translation"), eElement));
                }

                result.add(card);

            }// end for

        }// end try
        catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    /**
     * Saves Vocabulary to XML file
     *
     * @param fileName String for output XML file
     */
    //TODO Add some kind of Error Exceptions
    // See? The method have a difficult name. Something is wrong here.
    public void openXMLFileAndSaveAll(String fileName, Collection<Card> cards) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("vocabulary");
            doc.appendChild(rootElement);

            //Cycle for adding cards elements goes here
            //One iteration
            int i = 1;
            for (Card c : cards) {

                //card element
                Element card = doc.createElement("card");
                rootElement.appendChild(card);
                //card.setAttribute("count", Integer.toString(i));
                Attr attr = doc.createAttribute("count");
                attr.setValue(Integer.toString(i));
                card.setAttributeNode(attr);
                //word element
                Element word = doc.createElement("word");
                word.appendChild(doc.createTextNode(c.getWord()));
                card.appendChild(word);
                //transcript
                Element transcript = doc.createElement("transcript");
                transcript.appendChild(doc.createTextNode(c.getTranscript()));
                card.appendChild(transcript);
                //translation
                Element translation = doc.createElement("translation");
                translation.appendChild(doc.createTextNode(c.getTranslation()));
                card.appendChild(translation);

                i++;
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);

            System.out.println("File saved!");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    /**
     * Inner Method for getting Value of Node Chiled by it's Name
     *
     * @param sTag
     * @param eElement
     * @return the Value of Node
     */
    private static String getTagValue(String sTag, Element eElement) {
        // ugly. rewrite.
        return eElement.getElementsByTagName(sTag).item(0).getChildNodes().item(0).getNodeValue();
    }
}
