package ru.md24inc.alembic.pervoc.control;

import ru.md24inc.alembic.pervoc.core.Card;
import ru.md24inc.alembic.pervoc.dao.CardXmlDao;

import java.util.Collection;

public class ConsoleXMLObj {
    //public final static String srcOfFile = "D:\\workspace\\PerV\\Xclay\\mud.xml";

    public final static String srcOfFile = "c:\\Users\\miroque\\Documents\\NetBeansProjects\\PerV\\Xclay\\mud.xml";

    public static void main(String args[]) {
        CardXmlDao cardDao = new CardXmlDao();
        final Collection<Card> cards = cardDao.openXMLFileAndGetAll(srcOfFile);
        cardDao.printToConsole(cards);
        cardDao.openXMLFileAndSaveAll("c:\\Users\\miroque\\Documents\\NetBeansProjects\\PerV\\Xclay\\mudSaved.xml", cards);
    }
}
