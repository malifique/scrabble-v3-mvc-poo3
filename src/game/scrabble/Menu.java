package game.scrabble;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner scan = new Scanner(System.in);
    private String dico_path;
    
    public Menu(String d) {
        dico_path = d;
    }

    public static void main(String[] args) {
        String dico="";
        if(args.length > 0)
            dico=args[0];
        else
            dico="./anglais.txt";
        Menu menu=new Menu(dico);
        System.out.println("Welcome to Scrabble 2.1");
        menu.mainMenu();
        System.out.println("Good bye");
    }
    
    public void launchGame() {
        Bag bag = new Bag(Game.getLetters());
        System.out.println("---- Game configuration ---");
        Player players[];
        while (true) {
            try {
                System.out.println("How many players in this game ?");
                players = new Player[scan.nextInt()];
                scan.nextLine();
                break;
            } catch (InputMismatchException e) {
                scan.nextLine();
            }
        }
        for(int i=0;i<players.length;i++) {
            System.out.print("Name of player "+(i+1)+" : ");
            players[i] = new ConsolePlayer(bag,scan.nextLine());
        }
        try {
            Game game = new Game(bag , players, new Board(dico_path));
            game.play();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR : Unable to find the dictionary "+dico_path);
        }
    }
    
    public void mainMenu() {
        String choice;
        do {
            System.out.println("-------- Main menu --------");
            System.out.println("[L]aunch a game");
            System.out.println("[Q]uit");
            System.out.print("Choice : ");choice = scan.nextLine();
        } while(!choice.equalsIgnoreCase("L")&&!choice.equalsIgnoreCase("Q"));
        if(choice.equalsIgnoreCase("L")) {
            launchGame();
            mainMenu();
        }
    }

}
