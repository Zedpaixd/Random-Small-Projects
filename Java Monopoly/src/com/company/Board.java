package com.company;

import java.util.Random;

public class Board
{
    Square[] boardSquare;
    Square toAccess;       //ask at lab how to access without creating this

    String[] squareNames = {"House","Jail","Island","idk"};
    Random random = new Random();
    int players;

    public Board(int players)
    {
        this.players = players;

        boardSquare = new Square[40];
        toAccess = new Property("justAccessing",200,players);

        for (int i = 0; i < 40; i++)
        {

            if (i % 2 == 0 && i != 0)
            {
                boardSquare[i] = new Property(squareNames[random.nextInt(squareNames.length)],
                        ((Property)toAccess).rentPrices[random.nextInt(((Property)toAccess).rentPrices.length)],
                        players);
            }
            else if (i % 23 == 0)
            {
                boardSquare[i] = new Property("Scam lottery",500, players);
            }
            else
            {
                boardSquare[i] = new Property("Empty", 0, players);
            }

        }
    }


    public void backend(Player player)
    {
        if (boardSquare[player.position - 1].name != "Empty" && !((Property) boardSquare[player.position - 1]).isBought)
        {
            if(player.cash > ((Property) boardSquare[player.position - 1]).price)
            {
                ((Property) boardSquare[player.position - 1]).buy(player);

                System.out.println("Player: " + player.name +
                                   " bought a field for " + ((Property) boardSquare[player.position - 1]).price);

                player.cash = player.cash - ((Property) boardSquare[player.position - 1]).price;
            }
            else
            {
                System.out.println("Player: " + player.name +
                                   " attempted to buy a field but did not have enough money.");
            }

        }
        if (boardSquare[player.position - 1].name != "Empty" && ((Property) boardSquare[player.position - 1]).isBought)
        {
            ((Property) boardSquare[player.position - 1]).owner.cash = ((Property) boardSquare[player.position - 1]).owner.cash + ((Property) boardSquare[player.position - 1]).price;
            player.cash = player.cash - ((Property) boardSquare[player.position - 1]).price;

            System.out.println("Player: " + player.name +
                               " paid " + ((Property) boardSquare[player.position - 1]).owner.name + ((Property) boardSquare[player.position - 1]).price +
                               " for rent");
        }
    }

}



