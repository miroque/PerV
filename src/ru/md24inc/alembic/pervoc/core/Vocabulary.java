package src.ru.md24inc.alembic.pervoc.core;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Vocabulary for making XML to object
 *
 * @author Alexander Panov
 *
 * @version 0.1
 */

public class Vocabulary {

	protected List <Card> voc = new ArrayList<Card>();
	
	public void add(Card c){
		voc.add(c);
	}

}
