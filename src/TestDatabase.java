import java.util.Collection;
import java.util.Iterator;

import dao.GameDAO;
import entity.Hint;

public class TestDatabase {
	GameDAO dao = new GameDAO();

	private void listAllHint() {
		Collection<Hint> cHint  = dao.listAllHint();
		Iterator<Hint> it = cHint.iterator();
		while (it.hasNext()) {
			Hint hint = it.next();
			System.out.println(hint);
		}
		
	}
	
    private void insertHint(Hint hint) {
    	dao.insertHint(hint);
    	
    }
    
    private void deleteHint(Hint hint) {
    	dao.deleteHint(hint);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestDatabase test = new TestDatabase();
		Hint hint = new Hint(600, 700, 1000, "images/xyz.jpg");
		test.insertHint(hint);
		test.listAllHint();
//		hint.setHintId(6);
//		test.deleteHint(hint);
	}

}
