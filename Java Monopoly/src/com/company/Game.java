package com.company;

public class Game {

    InputDevice IDV;
    OutputDevice ODV;

    public Game(InputDevice IDV, OutputDevice ODV) {
        this.IDV = IDV;
        this.ODV = ODV;
    }


    public void playGame(int maxRounds) {
        int scorep1 = 0, scorep2 = 0;

        while (scorep1 < maxRounds && scorep2 < maxRounds) {
            int number1 = IDV.nextInt();
            int number2 = IDV.nextInt();

            if (number1 == number2) {
                scorep1 = scorep1 + 2;
                scorep2 = scorep2 + 2;
            } else if ((number1 % number2 == 0) && (number2 > 9)) {
                scorep2 = scorep2 + 1;
            } else if ((number2 % number1 == 0) && (number1 > 9)) {
                scorep1 = scorep1 + 1;
            } else if (number1 > number2) {
                scorep1 = scorep1 + 1;
            } else {
                scorep2 = scorep2 + 1;
            }

            ODV.WriteMessage(scorep1 + " - " + scorep2 + " | " + number1 + " " + number2);
            System.out.println(IDV.roundsLeft(maxRounds,scorep1,scorep2));

        }
    }

}

