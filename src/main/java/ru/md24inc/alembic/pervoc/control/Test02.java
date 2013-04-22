package ru.md24inc.alembic.pervoc.control;

import ru.md24inc.alembic.pervoc.domains.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

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
		
	}
}
