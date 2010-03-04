package game.scrabble;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;

public class BoardTest {
    
    private Board board;
    private Rack rack;

    @Before
    public void setUp() throws Exception {
        board = new Board("/home/mcmic/dico_anglais.txt");
        rack = new Rack();
        rack.addLetter(new Letter(1,'/'));
    }

    @After
    public void tearDown() throws Exception {
        board = null;
    }

    @Test
    public void testBoard() {
        assertNotNull(board);
    }

    @Test
    public void testBoardBadDico() {
        try {
            board = new Board("zamsqdjgjvlhsgiohu.dljfhgsdhu");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            assertEquals(e.getLocalizedMessage(),"Specified file does not exist.");
        }
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testAddNullWord() {
        board.addWord(7,7,true,null,rack);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testAddNullRack() {
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(1,'G'));
        board.addWord(7,7,true,"pig",null);
    }

    @Test
    public void testGetLetter() {
        assertNull(board.getLetter(7, 7));
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        assertEquals(8,board.addWord(7, 7, true, "pig", rack));
        assertTrue(board.getLetter(7, 7).equals('p'));
    }
        
    @Test
    public void testAddBadWord() {
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(1,'A'));
        try {
            board.addWord(7, 7, true, "aaa", rack);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(),"This word doesn't exist");
        }
    }

    @Test
    public void testAddGoodWordBadStartingPlace() {
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(1,'G'));
        int size = rack.length();
        try {
            board.addWord(7, 8, true, "pig", rack);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Bad position : the first word must cover the centrale case");
            assertEquals(size,rack.length());
        }
    }

    @Test
    public void testAddGoodWordGoodPlaceNotInRack() {
        try {
            board.addWord(7, 7, true, "pig", rack);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(),"The letters of the rack does not fit");
        }
    }
    @Test
    public void testAddGoodWordGoodPlaceInRack() {
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(1,'G'));
        int size = rack.length();
        assertFalse(board.addWord(7, 7, true, "pig", rack)<=0);
        assertEquals(size-3,rack.length());
    }

    @Test
    public void testAddGoodWordGoodPlaceInRackHor() {
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(1,'G'));
        int size = rack.length();
        assertFalse(board.addWord(6, 7, true, "pig", rack)<=0);
        assertEquals(size-3,rack.length());
    }

    @Test
    public void testAddGoodWordGoodPlaceInRackVert() {
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(1,'G'));
        int size = rack.length();
        assertFalse(board.addWord(7, 6, false, "pig", rack)<=0);
        assertEquals(size-3,rack.length());
    }

    @Test
    public void testAddGoodWordBadPlace() {
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        board.addWord(7, 7, true, "pig", rack);
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        int size = rack.length();
        try {
            board.addWord(5, 5, true, "pig", rack);
        } catch(IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Your word is isolated");
            assertEquals(size,rack.length());
        }
    }

    @Test
    public void testAddWordScore() {
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        int size = rack.length();
        assertEquals(8,board.addWord(7, 7, true, "pig", rack));
        assertEquals(size-3,rack.length());
    }

    @Test
    public void testAddWordScoreLetterBonus() {
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        assertEquals(8,board.addWord(7, 7, true, "pig", rack));
        rack.addLetter(new Letter(4,'N'));
        assertEquals(9,board.addWord(8, 7, false, "in", rack));
    }

    @Test
    public void testAddWordScoreCrossWord() {
        int size = rack.length();
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        assertEquals(8,board.addWord(7, 7, true, "pig", rack));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        assertEquals(4,board.addWord(7, 7, false, "pig", rack));
        rack.addLetter(new Letter(1,'S'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(1,'L'));
        rack.addLetter(new Letter(1,'T'));
        assertEquals(13,board.addWord(10, 7, false, "salt", rack));
        assertEquals(size,rack.length());
    }

    @Test
    public void testAddGoodWordBadCrossWord() {
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        assertEquals(8,board.addWord(7, 7, true, "pig", rack));
        rack.addLetter(new Letter(1,'P'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        int size = rack.length();
        try {
            board.addWord(8, 8, true, "pig", rack);
        } catch(IllegalArgumentException e) {
            assertEquals("A word formed does not exist",e.getMessage());
            assertEquals(size,rack.length());
        }
    }

    @Test
    public void testAddGoodWordThatGoOut() {
        rack.addLetter(new Letter(1,'B'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        assertEquals(20,board.addWord(7, 7, true, "banana", rack));
        rack.addLetter(new Letter(1,'T'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        rack.addLetter(new Letter(1,'E'));
        rack.addLetter(new Letter(2,'R'));
        try {
            board.addWord(12, 8, true, "tiger", rack);
        } catch(IllegalArgumentException e) {
            assertEquals("Bad position",e.getMessage());
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void testAddGoodWordInPlace() {
        rack.addLetter(new Letter(1,'B'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        assertEquals(20,board.addWord(7, 7, true, "banana", rack));
        board.addWord(7, 7, true, "banana", rack);
    }

    @Test
    public void testAddGoodWordThatGoNearOut() {
        rack.addLetter(new Letter(1,'B'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        assertEquals(20,board.addWord(7, 7, true, "banana", rack));
        rack.addLetter(new Letter(1,'T'));
        rack.addLetter(new Letter(1,'O'));
        rack.addLetter(new Letter(2,'Y'));
        assertEquals(8,board.addWord(12, 8, true, "toy", rack));
    }

    @Test
    public void testAddFailWordGrid() {
        rack.addLetter(new Letter(1,'B'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        assertEquals(20,board.addWord(7, 7, true, "banana", rack));
        rack.addLetter(new Letter(1,'T'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        rack.addLetter(new Letter(1,'E'));
        rack.addLetter(new Letter(2,'R'));
        try {
            assertEquals(0,board.addWord(1, 1, true, "tiger", rack));
        } catch(IllegalArgumentException e) {
            assertEquals("Your word is isolated",e.getMessage());
        }
        System.out.println(board);
        assertNull(board.getLetter(1, 1));
    }

    @Test
    public void testAddFailWordBadLetterGrid() {
        rack.addLetter(new Letter(1,'B'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        assertEquals(20,board.addWord(7, 7, true, "banana", rack));
        rack.addLetter(new Letter(1,'T'));
        rack.addLetter(new Letter(1,'I'));
        rack.addLetter(new Letter(2,'G'));
        rack.addLetter(new Letter(1,'E'));
        rack.addLetter(new Letter(2,'R'));
        try {
            board.addWord(7, 7, false, "tiger", rack);
        } catch(IllegalArgumentException e) {
            assertEquals("The letters of the board does not fit",e.getMessage());
        }
    }

    @Test
    public void testAddWordScrabble() {
        rack.getLetter(0);
        rack.addLetter(new Letter(1,'B'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(2,'N'));
        rack.addLetter(new Letter(1,'A'));
        rack.addLetter(new Letter(1,'S'));
        assertEquals(72,board.addWord(7, 7, true, "bananas", rack));
    }
    

}
