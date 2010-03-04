package game.scrabble;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DictionaryTest {

    private Dictionary dico;
    
    @Before
    public void setUp() throws Exception {
        dico = new Dictionary("/home/mcmic/dico_anglais.txt");
    }

    @After
    public void tearDown() throws Exception {
        dico = null;
    }

    @Test
    public void testDictionary() {
        assertNotNull(dico);
    }

    @Test
    public void testContains() {
        assertTrue(dico.contains("word"));
        assertTrue(dico.contains("WORD"));
        assertFalse(dico.contains("qslmdjvjhqs"));
    }

    @Test
    public void testList() {
        dico.list("worm");
    }

}
