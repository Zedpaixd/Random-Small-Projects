package com.company;
import java.util.Random;


public class Player {

    int cash,position;
    String name;
    Random randomNumb = new Random();
    int moveRestrict;

    public Player(String name)
    {
        this.cash = 0;
        this.position = 1;
        this.name = name;
        this.moveRestrict = 0;
    }

    public Player(String name, int cash, int position, int moveRestrict)
    {
        this.cash = cash;
        this.position = position;
        this.name = name;
        this.moveRestrict = moveRestrict;
    }

    public int throwDice()
    {
        return randomNumb.nextInt(2,12);
    }

    public void move()
    {

        if (moveRestrict > 0)

        {
            System.out.println("Player: " + name + " was unable to move for " + moveRestrict + " turn(s), therefore his turn was skipped");
            moveRestrict = moveRestrict - 1;
        }

        else

        {
            position = position + throwDice();

            if (position > 40)
            {
                position = position - 40;
                cash = cash + 200;

                System.out.println("Player: " + name + " has gone past the last square for a total of extra 200 credit!");
            }
        }
    }

    public void advance(int moveBy)
    {

        if (moveRestrict > 0)
        {
            System.out.println("Player: " + name + " is unable to move for " + moveRestrict + " turn(s), therefore his turn was skipped");

            moveRestrict = moveRestrict - 1;
        }

        else

        {

            position = position + moveBy;

            if (position > 40)
            {
                position = position - 40;
                cash = cash + 200;

                System.out.println("Player: " + name + " has gone past the last square for a total of extra 200 credit!");
            }

            if (position < 0)
            {
                position = position + 40;
            }

        }

    }

    public void sendToJail(int duration)
    {
        position = 17;
        moveRestrict = moveRestrict + duration;
        System.out.println("Player: " + name + " has been sent to jail for " + duration + " turns.");
    }

}
