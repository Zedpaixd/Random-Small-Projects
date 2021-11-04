package com.company;

import java.util.Objects;
import java.util.Random;

public class Board implements Action
{
    Square[] boardSquare;
    Square toAccess;       //ask at lab how to access without creating this
    String[] squareNames = {"House","Another House","Dr House's House","Dr House's house from Dr House","Island","idk","The whole planet","Planet Neptune"};
    Random random = new Random();

    public cardAction drawCard()
    {
        Random random = new Random();
        return cardAction.values()[random.nextInt(cardAction.values().length)];
    }

    int players;

    public Board(int players)
    {
        this.players = players;

        boardSquare = new Square[40];
        toAccess = new Property(false,"justAccessing",200,players);

        for (int i = 0; i < 40; i++)
        {

            if (i % 2 == 0 && i % 5 != 0)
            {
                boardSquare[i] = new Property(true,
                        squareNames[random.nextInt(squareNames.length)],
                        ((Property)toAccess).rentPrices[random.nextInt(((Property)toAccess).rentPrices.length)],
                        players);
            }
            else if (i % 23 == 0)
            {
                boardSquare[i] = new Property(false,"Scam lottery",500, players);
            }
            else if (i % 17 == 0)
            {
                boardSquare[i] = new Property(false,"Jail",0, players);
            }
            else if (i % 13 == 0)
            {
                boardSquare[i] = new Property(false,"Go to jail!",0, players);
            }
            else if (i % 5 == 0)
            {
                boardSquare[i] = new Property(false, "Draw a card", 0, players);
            }
                else
                {
                    boardSquare[i] = new Property(false,"Empty", 0, players);
                }

        }
    }


    public void backend(Player player)
    {

        if (player.moveRestrict == 0)
        {
            if (boardSquare[player.position - 1].canBeBought) {   // IF SQUARE IS BUYABLE


                int choice = random.nextInt(0,2);

                if (choice == 1)
                {
                    // IF SQUARE IS NOT BOUGHT, BUY IT
                    if (boardSquare[player.position - 1].name != "Empty" && !((Property) boardSquare[player.position - 1]).isBought)
                    {
                        if (player.cash > boardSquare[player.position - 1].price)
                        {
                            ((Property) boardSquare[player.position - 1]).buy(player);

                            System.out.println("Player: " + player.name +
                                    " bought a field for " + boardSquare[player.position - 1].price);

                            player.cash = player.cash - boardSquare[player.position - 1].price;

                        } else {
                            System.out.println("Player: " + player.name +
                                    " wanted to buy a field but did not have enough money.");
                        }

                    }
                }

                else
                    System.out.println("Player: " + player.name +
                                       " had the choice of buying the " +  boardSquare[player.position - 1].name +
                                       " property for " + boardSquare[player.position - 1].price +
                                       " but decided not to.");



                // IF SQUARE IS BOUGHT, PAY RENT
                if (boardSquare[player.position - 1].name != "Empty" && ((Property) boardSquare[player.position - 1]).isBought)
                {

                    try
                    {
                        if (player.cash < boardSquare[player.position - 1].price)

                            throw new NotEnoughMoneyException();

                        else
                        {
                            boardSquare[player.position - 1].owner.cash = boardSquare[player.position - 1].owner.cash + boardSquare[player.position - 1].price;
                            player.cash = player.cash - boardSquare[player.position - 1].price;

                            System.out.println("Player: " + player.name +
                                    " paid " + boardSquare[player.position - 1].owner.name +
                                    " " + boardSquare[player.position - 1].price +
                                    " for rent");
                        }

                    }
                    catch (NotEnoughMoneyException e)
                    {
                        System.out.println(e.getMessage());

                        int i = 0;

                        while (player.cash < boardSquare[player.position - 1].price)
                        {
                            if (boardSquare[i].owner.name == player.name)
                            {
                                ((Property) boardSquare[i]).sell(player);
                                System.out.println("Player: " + player.name +
                                        " sold the field on square " + i +
                                        " for " + boardSquare[player.position - 1].price +
                                        " in order to be able to pay for rent");
                            }

                            i++;

                            if (i >= 40)
                            {
                                System.out.println("Player: " + player.name + " does not have more land to sell therefore he will go into debt.");
                                break;
                            }

                        }




                    }

                }
            }

            else         // IF SQUARE IS NOT BUYABLE
            {
                if (Objects.equals(boardSquare[player.position - 1].name, "Scam lottery"))
                {
                    player.cash = player.cash - boardSquare[player.position - 1].price;
                    System.out.println("Player: " + player.name +
                            " went to the lottery and lost " + boardSquare[player.position - 1].price +
                            " pounds");
                }

                if (Objects.equals(boardSquare[player.position - 1].name, "Go to jail!"))
                {
                    player.sendToJail(3);
                }

                if (Objects.equals(boardSquare[player.position - 1].name, "Draw a card"))
                {
                    String card = drawCard().toString();
                    System.out.println("Player: " + player.name +
                            " landed on the Draw a card square and drew a " + card + " card");


                    switch (card) {
                        case "GoToJail": {
                            player.sendToJail(3);
                            break;
                        }

                        case "MoveBack": {
                            int a = random.nextInt(1,10);
                            System.out.println("Given that, they have moved back for " + a + " squares");
                            player.advance(a);
                            break;
                        }

                        case "MoveFoward": {
                            int a = random.nextInt(1,10);
                            System.out.println("Given that, they have moved forward for " + a + " squares");
                            player.advance(a);
                            break;
                        }

                        case "RollAgain": {
                            System.out.println("Given that, the player rolls again.");
                            player.move();
                            break;
                        }

                        case "WinMoney": {
                            int a = random.nextInt(10,50)*100/10;
                            System.out.println("Given that, they've won " + a + " pounds");
                            player.cash = player.cash + a;
                            break;
                        }
                    }
                }
            }
        }
    }
}



