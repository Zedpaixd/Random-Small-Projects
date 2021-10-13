package com.company;
import java.util.Random;


public class Player {

    int cash;
    String name;
    int position;
    Random randomNumb = new Random();

    public Player(String name)
    {
        this.cash = 0;
        this.position = 1;
        this.name = name;
    }

    public int throwDice() {
        return randomNumb.nextInt(2,12);
    }

    public void move() {
        position = position + throwDice();
        if (position > 40)
        {
            position = position - 40;
            cash = cash + 200;
            System.out.println("Player: " + name + " has gone past the last square for a total of extra 200 credit!");
        }
    }
}
