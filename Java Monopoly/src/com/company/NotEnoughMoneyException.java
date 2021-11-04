package com.company;

public class NotEnoughMoneyException extends Exception{

    public NotEnoughMoneyException(){
        super("NotEnoughMoneyException thrown. Player does not dispose of enough money.");
    }
}
