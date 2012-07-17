package ru.md24inc.alembic.pervoc.control;

import ru.md24inc.alembic.pervoc.core.Card;
import ru.md24inc.alembic.pervoc.dao.Vocabulary;

import java.util.Collection;

public class ConsoleXMLObj {
    //public final static String srcOfFile = "D:\\workspace\\PerV\\Xclay\\mud.xml";

    public final static String srcOfFile = "c:\\Users\\miroque\\Documents\\NetBeansProjects\\PerV\\Xclay\\mud.xml";

    public static void main(String args[]) {
        Vocabulary vocaBul = new Vocabulary();
        final Collection<Card> cards = vocaBul.openXMLFile(srcOfFile);
        vocaBul.printVocabular(cards);
        vocaBul.saveXMLFile("c:\\Users\\miroque\\Documents\\NetBeansProjects\\PerV\\Xclay\\mudSaved.xml", cards);
    }
}
