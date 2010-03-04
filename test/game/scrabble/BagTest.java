package game.scrabble;


import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BagTest {
	
	private Bag bag;

	@Before
	public void setUp() throws Exception {
		bag = new Bag();
	}

	@After
	public void tearDown() throws Exception {
		bag = null;
	}

	@Test
	public void constructorTest() {
		assertNotNull(bag);
		assertNull(bag.getLetter());
	}

    @Test(expected=IllegalArgumentException.class)
    public void constructorBadArgumentTest() {
        new Bag(null);
    }

    @Test
    public void constructorHashSetTest() {
        ArrayList<Letter> ls = new ArrayList<Letter>();
        Letter a = new Letter(1,'a');
        ls.add(a);
        bag = new Bag(ls);
        assertEquals(bag.getLetter(),a);
    }

    @Test
    public void addLetterTest() {
        Letter a = new Letter(1,'a');
        bag.addLetter(a);
        assertEquals(bag.getLetter(),a);
    }

    @Test
    public void addTwoLetterTest() {
        Letter a = new Letter(1,'a');
        bag.addLetter(a);
        bag.addLetter(a);
        assertEquals(bag.getLetter(),a);
        assertEquals(bag.getLetter(),a);
    }
	
}