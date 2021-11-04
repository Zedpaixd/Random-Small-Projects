package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.bitCount;
import static java.lang.Integer.parseInt;


public class Main {

    public static boolean isInt(String str)
    {
        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return true; //String is an Integer
        }
        catch (NumberFormatException e) {
            return false; //String is not an Integer
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        InputDevice ID = new InputDevice();
        OutputDevice OD = new OutputDevice();
        Game game = new Game(ID, OD);

        args[0] = "4";    //players
        args[1] = "800";   //maxRounds


        switch (args[0]) {
            case "words" -> ID.exampleHistorgram();

            case "numbers" ->
            {
                int n = 7;
                ID.randomArraySort(n);
                break;
            }

            case "play" ->
            {
                int c = Integer.parseInt(args[1]);
                game.playGame(c);
                break;
            }

            default ->
            {
                if (isInt(args[0]) && isInt(args[1]))
                {
                    int players = parseInt(args[0]);
                    int maxRounds = parseInt(args[1]);
                    int currentRound = 0;

                    Scanner input = new Scanner(System.in);
                    System.out.println("Boolean continue, true/false?");

                    boolean c = input.nextBoolean();

                    MonopolyGame Monopoly = new MonopolyGame(players, maxRounds,currentRound);

                    boolean save = Monopoly.playMonopoly(c);

                    if (save)
                    {
                        try
                        {
                            File data = new File("data.txt");
                            FileWriter dataW = new FileWriter("data.txt");

                            for(Player pl: Monopoly.players)
                            {
                                dataW.write(pl.name + "\n");
                                dataW.write(pl.cash + "\n");
                                dataW.write(pl.position + "\n");
                                dataW.write(pl.moveRestrict + "\n");
                            }
                            dataW.close();
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }

                else

                    System.out.println("First argument should be either \"words\" , \"numbers\" or 2 integers representing players and max rounds of monopoly");

            }
        }
    }
}