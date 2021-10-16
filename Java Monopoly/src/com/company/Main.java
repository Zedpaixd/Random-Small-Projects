package com.company;
import static java.lang.Integer.parseInt;


public class Main {

    public static boolean isInt(String str) {
        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return true; //String is an Integer
        } catch (NumberFormatException e) {
            return false; //String is not an Integer
        }
    }

    public static void main(String[] args) {

        InputDevice ID = new InputDevice();
        OutputDevice OD = new OutputDevice();
        Game game = new Game(ID, OD);

        args[0] = "4";
        args[1] = "40";


        switch (args[0]) {
            case "words":
                ID.exampleHistorgram();
                break;

            case "numbers": {
                int n = 7;
                ID.randomArraySort(n);
                break;
            }

            case "play": {
                int c = Integer.parseInt(args[1]);
                game.playGame(c);
                break;
            }

            default: {
                if (isInt(args[0]) && isInt(args[1])) {
                    int players = parseInt(args[0]);
                    int maxRounds = parseInt(args[1]);
                    MonopolyGame Monopoly = new MonopolyGame(players, maxRounds);
                    Monopoly.playMonopoly();
                } else

                    System.out.println("First argument should be either \"words\" , \"numbers\" or 2 integers representing players and max rounds of monopoly");
            }
        }
    }
}

        /*if (args[0].equals("words"))

            ID.exampleHistorgram();

        else if(args[0].equals("numbers"))

            {
                int n = 7;
                ID.randomArraySort(n);
            }

            else if(args[0].equals("play"))
            {

                int c = Integer.parseInt(args[1]);
                game.playGame(c);
            }

                else if (isInt(args[0]) && isInt(args[1]))
                {
                    int players = parseInt(args[0]);
                    int maxRounds = parseInt(args[1]);
                    MonopolyGame Monopoly = new MonopolyGame(players,maxRounds);
                    Monopoly.playMonopoly();
                }
                    else

                        System.out.println("First argument should be either \"words\" , \"numbers\" or 2 integers representing players and max rounds of monopoly");*/

        //OD.printArgs(args);