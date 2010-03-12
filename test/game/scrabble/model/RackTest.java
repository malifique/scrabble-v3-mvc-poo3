package game.scrabble.model;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RackTest {

    private Rack rack;
    
    @Before
    public void setUp() throws Exception {
        rack = new Rack();
    }

    @After
    public void tearDown() throws Exception {
        rack = null;
    }

    @Test
    public void testRack() {
        assertNotNull(rack);
    }

    @Test
    public void testAddLetter() {
        Letter b = new Letter(1,'b');
        rack.addLetter(b);
        assertTrue(b.equals(rack.getLetter('b')));
    }

    @Test
    public void testGetLetterInt() {
        assertNull(rack.getLetter(0));
        Letter b = new Letter(1,'b');
        rack.addLetter(b);
        assertTrue(b.equals(rack.getLetter(0)));
    }

    @Test
    public void testGetLetterChar() {
        assertNull(rack.getLetter('a'));
    }

    /*@Test
    public void testAddLettersLetterArray() {
        fail("Not yet implemented");
    }*/

    @Test
    public void testAddLettersCollectionOfLetter() {
        HashSet<Letter> letters = new HashSet<Letter>();
        letters.add(new Letter(1,'a'));
        letters.add(new Letter(1,'b'));
        rack.addLetters(letters);
        assertEquals(2,rack.size());
    }

    @Test
    public void testSize() {
        assertEquals(0,rack.size());
        Letter b = new Letter(1,'b');
        rack.addLetter(b);
        assertEquals(1,rack.size());
    }

    @Test
    public void testLength() {
        assertEquals(0,rack.length());
        Letter b = new Letter(1,'b');
        rack.addLetter(b);
        assertEquals(1,rack.length());
    }

    @Test
    public void testToString() {
        assertEquals("",rack.toString());
        Letter b = new Letter(1,'b');
        rack.addLetter(b);
        assertEquals("B",rack.toString());
    }

}
