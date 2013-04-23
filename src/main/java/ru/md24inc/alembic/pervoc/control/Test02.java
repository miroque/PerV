package ru.md24inc.alembic.pervoc.control;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.UnmappableCharacterException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ru.md24inc.alembic.pervoc.domains.CardType;
import ru.md24inc.alembic.pervoc.domains.ObjectFactory;
import ru.md24inc.alembic.pervoc.domains.TranscriptType;
import ru.md24inc.alembic.pervoc.domains.TranslationType;
import ru.md24inc.alembic.pervoc.domains.VocabularyType;
import ru.md24inc.alembic.pervoc.domains.WordType;

public class Test02 {
	public static void main(String args[]) throws JAXBException {
		ObjectFactory of = new ObjectFactory();
		
		VocabularyType vocabulary = of.createVocabularyType();

		CardType card = of.createCardType();
		card.setDate(new GregorianCalendar().getInstance().getTime().toString());
		
		WordType word = of.createWordType();
		word.setLang("en");
		word.setValue("ser");

		TranscriptType transcript = of.createTranscriptType();
		transcript.setValue("234jfaa");
		
		TranslationType translation = of.createTranslationType();
		translation.setLang("ru");
		translation.setValue("сер");
		
		card.setWord(word);
		card.setTranscript(transcript);
		card.setTranslation(translation);
		
		CardType card2 = of.createCardType();
		card2.setDate(new GregorianCalendar().getInstance().getTime().toString());
		
		WordType word2 = of.createWordType();
		word2.setLang("en");
		word2.setValue("cat");

		TranscriptType transcript2 = of.createTranscriptType();
		transcript2.setValue("cat");
		TranslationType translation2 = of.createTranslationType();
		translation2.setLang("ru");
		translation2.setValue("кот");

		card2.setWord(word2);
		card2.setTranscript(transcript2);
		card2.setTranslation(translation2);
		
		vocabulary.getCard().add(card);
		vocabulary.getCard().add(card2);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(VocabularyType.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
		jaxbMarshaller.marshal(vocabulary, System.out);
		try {
			jaxbMarshaller.marshal(vocabulary, new FileWriter("C:\\Users\\panov\\workspaces\\experiz\\PerV\\Xclay\\Musrum.xml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Unmarshaller um  = jaxbContext.createUnmarshaller();
		try {
//			VocabularyType myVoc = (VocabularyType) um.unmarshal(new FileReader("C:\\Users\\panov\\workspaces\\experiz\\PerV\\Xclay\\Musrum.xml"));
			VocabularyType myVoc = (VocabularyType) um.unmarshal(new FileReader("C:\\Users\\panov\\workspaces\\experiz\\PerV\\Xclay\\mud.xml"));
			List<CardType> vc = myVoc.getCard();
			System.out.println(vc.toString());
		} catch (FileNotFoundException e) {
			//I guess that one of the solution is 
			// converting this input file to UTF-8 without BOM
			e.printStackTrace();
		}
		
	}
}
