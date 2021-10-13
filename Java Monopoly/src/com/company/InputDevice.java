package com.company;
import java.util.Arrays;
import java.util.Random;

public class InputDevice {

    Random randomNumb = new Random();

    public String getType()
    {
        String a = "Random";
        return a;
    }

    public int[] getNumbers(int n)
    {
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = randomNumb.nextInt(1,100);
        }
        return arr;
    }

    public void sortNumbers(int[] array)
    {

         Arrays.sort(array);

    }

    public void randomArraySort(int n)
    {
        int[] array = new int[1000];
        array = getNumbers(n);
        sortNumbers(array);
        for(int a:array)
        {
            System.out.println("Element: "+a);
        }
    }

    public String getLine()
    {
        return "The quick brown fox jumps over the energetic dog";
    }

    public int[] wordSizeHistogram(String s)
    {
        String[] words = s.split("\\s+");

        int[] wordSize = new int[words.length];
        int ctr = 0;
        for (String a:words)
        {
            wordSize[ctr] = a.length();
            ctr++;
        }
        return wordSize;
    }

    public void exampleHistorgram()
    {
        int n = 7;

        int[] array = new int[n];
        array = wordSizeHistogram(getLine());
        for (int i: array)
            System.out.println(i);
    }


    public int nextInt()
    {
        return randomNumb.nextInt(1,100);
    }

    public String roundsLeft(int maxRounds, int score1, int score2)
    {

        int rl = maxRounds-Math.max(score1,score2);
        return "Assuming the best case scenario: "+ rl;

    }

}