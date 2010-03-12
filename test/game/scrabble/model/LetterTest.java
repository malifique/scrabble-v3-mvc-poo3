package game.scrabble.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LetterTest {
    
    private Letter l;

    @Before
    public void setUp() throws Exception {
        l = new Letter(2,'a');
    }

    @After
    public void tearDown() throws Exception {
        l = null;
    }

    @Test
    public void testLetter() {
        assertNotNull(l);
    }

    @Test
    public void testGetScore() {
        assertEquals(2,l.getScore());
    }

    @Test
    public void testGetSymbol() {
        assertEquals('A',l.getSymbol());
    }

    @Test
    public void testEqualsLetter() {
        Letter l2 = new Letter(2,'A');
        assertTrue(l.equals(l2));
    }

    @Test
    public void testEqualsChar() {
        assertTrue(l.equals('a'));
        assertTrue(l.equals('A'));
    }

}
