package game.scrabble.model;

import java.io.*;
import java.util.ArrayList;

/**
 * 
 * @author BERNIGAUD Côme
 *
 */
public class Dictionary {

    private ArrayList<String> words;
    
	/**
	 * The {@code Dictionary} instance.
	 */
	private static Dictionary dictionaryInstance;
    
	/**
	 * Get an instance of {@code Dictionary}.
	 * 
	 * @return an instance of {@code Dictionary}.
	 */
	public static Dictionary getInstance() {
		return dictionaryInstance;
	}
	
	/**
	 * Get an instance of {@code Dictionary}.
	 * 
	 * @return an instance of {@code Dictionary}.
	 * @throws FileNotFoundException 
	 */
	public static Dictionary getInstance(String filename) throws FileNotFoundException {
		if (dictionaryInstance == null) {
			dictionaryInstance = new Dictionary(filename);
		}
		return dictionaryInstance;
	}
    
    private Dictionary(String filename) throws FileNotFoundException {
        File f = new File(filename);
        if(!f.exists())
            throw new FileNotFoundException("Specified file does not exist.");
        
        BufferedReader fReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        words = new ArrayList<String>();

        String w = null;
        try
        {
            w = fReader.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        while(w != null)
        {
            words.add(w);
            w = null;
            try
            {
                w = fReader.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public boolean contains(String w) {
        return words.contains(w.toLowerCase());
    }
    
    public void list(String beginToken)
    {
        for(String s : words)
        {
            if(s.startsWith(beginToken)) System.out.println(s);
        }
    }

}
