package com.company;

public class MonopolyGame {

    int rounds;
    Player[] players;
    Board gameBoard;
    Player winner;
    String[] colours = {"Red","Orange","Yellow","Green","Blue","Purple","White","Grey","Black","Pink","Lavender","This is a colour, yes"};

    public MonopolyGame(int players, int rounds)
    {
        this.gameBoard = new Board(players);
        this.players = new Player[players];
        this.rounds = rounds;
    }

    public void playMonopoly()
    {
        for (int i = 0; i < players.length; i++)
        {
            players[i] = new Player(colours[i]);
        }

        for (int i = 0; i < rounds; i++)
        {
            for (Player pl : players)
            {
                //System.out.println(gameBoard.boardSquare[pl.position-1].name);

                for (Player pla: players)
                    System.out.println("-> " + pla.name + " : " + pla.cash + " <-");

                pl.move();

                gameBoard.backend(pl);

                System.out.println("------------------------------------------------------------------------------");
            }
        }

        System.out.println("Since game ended we shall now add each player the cost of their buildings to their final score.");
        System.out.println("------------------------------------------------------------------------------");

        for (Player pl : players)
        {
            for (int i = 0; i < 40; i++)
            {
                if (gameBoard.boardSquare[i].owner.name == pl.name)
                {
                    pl.cash = pl.cash + gameBoard.boardSquare[i].price;
                    System.out.println("Player: " + pl.name + " owns square " + i + " therefore " + gameBoard.boardSquare[i].price + " pounds have been added to his final score");
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

        System.out.printf("Winner is: %s with a total sum of %d and at position %d",winner.name,winner.cash,winner.position);

    }


}
