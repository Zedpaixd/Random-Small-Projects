package com.company;

public class Property extends Square{

    int[] rentPrices = {10,50,100,150,200,250,500};
    int price, rent, level;
    boolean isBought;
    Player owner = new Player("Not Owned");

    public Property(String name, int price, int players)
    {
        this.name = name;
        this.price = price;
        this.players = new Player[players];
        this.isBought = false;
    }


    public void buy(Player owner)
    {
        if (!isBought && !name.equals("Empty"))
        {
            if (owner.cash >= price)
            {
                this.owner.cash = owner.cash-price;
                this.owner = owner;
            }
            else

                System.out.println("Purchase for player has failed due to lack of credit.");


        }
    }
}
