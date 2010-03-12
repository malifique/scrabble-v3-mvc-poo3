package game.scrabble.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CaseTest {

    private Case c;
    
    @Before
    public void setUp() throws Exception {
        c = new Case();
    }

    @After
    public void tearDown() throws Exception {
        c = null;
    }

    @Test
    public void testCase() {
        assertNotNull(c);
    }

    @Test
    public void testCaseInt() {
        c = new Case(3);
        assertNotNull(c);
        Letter a = new Letter(2,'a');
        c.setLetter(a);
        assertEquals(c.getScore(),6);
    }

    @Test
    public void testCaseIntInt() {
        c = new Case(1,4);
        assertEquals(c.getWordMultiplier(),4);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCaseBadIntInt() {
        c = new Case(2,4);
    }

    @Test
    public void testSetLetter() {
        Letter a = new Letter(2,'a');
        c.setLetter(a);
        assertEquals(a,c.getLetter());
        assertEquals(2,c.getScore());
    }

    @Test
    public void testGetScore() {
        assertEquals(0,c.getScore());
    }

    @Test
    public void testGetWordMultiplier() {
        assertEquals(1,c.getWordMultiplier());
    }

    @Test
    public void testGetLetterMultiplier() {
        assertEquals(1,c.getLetterMultiplier());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(c.isEmpty());
        Letter a = new Letter(2,'a');
        c.setLetter(a);
        assertFalse(c.isEmpty());
    }

    @Test
    public void testHasBeenUsed() {
        c = new Case(1,4);
        c.hasBeenUsed();
        assertEquals(1,c.getWordMultiplier());
    }

    @Test
    public void testHasBeenUsedLetter() {
        c = new Case(3);
        Letter a = new Letter(1,'a');
        c.setLetter(a);
        c.hasBeenUsed();
        assertEquals(1,c.getScore());
    }

}
