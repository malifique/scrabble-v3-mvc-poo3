package game.scrabble;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JokerTest {

    private Letter l;
    private Rack r;
    
    @Before
    public void setUp() throws Exception {
        l = new Joker();
    }

    @After
    public void tearDown() throws Exception {
        l = null;
    }

    @Test
    public void testJoker() {
        assertNotNull(l);
    }

    @Test
    public void testGetScore() {
        assertEquals(0,l.getScore());
    }

    @Test
    public void testGetSymbol() {
        assertEquals('*',l.getSymbol());
    }

    @Test
    public void testEqualsLetter() {
        Letter j = new Joker();
        assertTrue(l.equals(j));
    }

    @Test
    public void testEqualsChar() {
        assertTrue(l.equals('w'));
        assertEquals(0,l.getScore());
        assertEquals('w',l.getSymbol());
    }

    @Test
    public void testToString() {
        assertEquals("*",l.toString());
        assertTrue(l.equals('w'));
        assertEquals("*",l.toString());
    }
    
    @Test
    public void testRack() {
        r = new Rack();
        r.addLetter(l);
        Letter l2 = r.getLetter('c');
        assertNotNull(l2);
        assertEquals("*",l2.toString());
        assertFalse(l2.equals('b'));
        assertFalse(l2.equals('*'));
        assertTrue(l2.equals('c'));
    }

}
