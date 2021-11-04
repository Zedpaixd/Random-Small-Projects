package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class MonopolyGame {

    int rounds,currentRound;
    Player[] players;
    Board gameBoard;
    Player winner;
    String[] colours = {"Red","Orange","Yellow","Green","Blue","Purple","White","Grey",
                        "Black","Pink","Lavender","This is a colour, yes"};


    public MonopolyGame(int players, int rounds, int currentRound)
    {
        this.gameBoard = new Board(players);
        this.players = new Player[players];
        this.rounds = rounds;
        this.currentRound = currentRound;
    }

    public boolean playMonopoly(boolean cont) throws FileNotFoundException {

        if (cont == false)
        {
            for (int i = 0; i < players.length; i++)

                players[i] = new Player(colours[i]);

        }

        else
        {
            File dataF = new File("data.txt");
            Scanner dataR = new Scanner(dataF);
            int i = 0;

            while (dataR.hasNextLine() && i < players.length)
            {
                String plname = dataR.nextLine();
                String plcash = dataR.nextLine();
                String plpos = dataR.nextLine();
                String plmoverestrict = dataR.nextLine();

                players[i] = new Player(plname,Integer.parseInt(plcash),Integer.parseInt(plpos),Integer.parseInt(plmoverestrict));

                i++;
            }

            while (i < players.length)
            {
                players[i] = new Player(colours[i]);
                i++;
            }
        }


        for (int currentRound = this.currentRound; currentRound < rounds; currentRound++)
        {
            for (Player pla: players)

                System.out.println("-> " + pla.name + " : " + pla.cash + " : " + pla.position + " <-");


            for (Player pl : players)
            {

                pl.move();

                gameBoard.backend(pl);

            }

            System.out.println("------------------------------------------------------------------------------");
        }

        System.out.println("Since game ended we shall now add each player the cost of their buildings to their final score.");
        System.out.println("------------------------------------------------------------------------------");

        for (Player pl : players)
        {
            for (int i = 0; i < 40; i++)
            {
                if (Objects.equals(gameBoard.boardSquare[i].owner.name, pl.name))
                {
                    pl.cash = pl.cash + gameBoard.boardSquare[i].price;

                    System.out.println("Player: " + pl.name +
                                       " owns square " + i +
                                       " therefore " + gameBoard.boardSquare[i].price +
                                       " pounds have been added to his final score");
                }
            }

            System.out.println("------------------------------------------------------------------------------");

        }

        System.out.println("Final scores:");

        for (Player pl: players)
        {
            System.out.println(pl.name + " : " + pl.cash);
        }




        winner = players[players.length-1];

        for (Player pl : players)
        {
            if(pl.cash > winner.cash) winner = pl;

            if(pl.cash == winner.cash && pl.position > winner.position) winner = pl;
        }

        System.out.printf("Winner is: %s with a total sum of %d and at position %d\n",winner.name,winner.cash,winner.position);


        Scanner input = new Scanner(System.in);
        System.out.println("Boolean save game state, true/false?\n");

        boolean c = input.nextBoolean();

        return c;

    }


}

