#include <iostream>
#include <time.h>
#include <windows.h>
#include <random>

using namespace std;



int main()
{
    srand(time(NULL));
    int x,y;
    cout<<"How many lines would you like to have? "; cin>>x;
    cout<<"\nHow many columns would you like to have? "; cin>>y;

    int grid[x][y],grid_copy[x][y];
    system("CLS");

    // Randomizes the grid with 1s and 0s
    for (int a=0;a<x;a++)
        for (int b=0;b<y;b++)
            {grid[a][b] = rand()%2;
            grid_copy[a][b]=grid[a][b];}

    string lines[x];
    string columns[y];

    // Does the left side of the nonogram UI thing
    int ctr = 0;
    for (int a=0;a<x;a++)
    {
        int sw=0;
        for (int b=0;b<y;b++)
        {
            if (grid_copy[a][b] == 1)
                {
                 sw = 1;
                 int temp=0;
                 while (sw == 1)
                    {
                     temp++;
                     b++;

                     if (grid_copy[a][b] == 0 || b >= y)
                        sw = 0;
                    }
                 lines[ctr] = lines[ctr] + to_string(temp) + " ";
                }
        }
        //cout<<lines[ctr]<<endl;
        ctr++;
    }


    // Does the top side of the nonogram UI thing
    ctr = 0;
    for (int b=0;b<y;b++)
    {
        int sw=0;
        for (int a=0;a<x;a++)
        {
            if (grid_copy[a][b] == 1)
                {
                 sw = 1;
                 int temp=0;
                 while (sw == 1)
                    {
                     temp++;
                     a++;

                     if (grid_copy[a][b] == 0 || a >= x)
                        sw = 0;
                    }
                 columns[ctr] = columns[ctr] + to_string(temp) + " ";
                }
        }
        //cout<<columns[ctr]<<endl;
        ctr++;
    }




    //Print the grid
    for (int a=0;a<x;a++)
        {for (int b=0;b<y;b++)
            cout<<grid_copy[a][b]<<" ";
        cout<<endl;

// grid[x][y] , grid_copy[x][y] , lines[x] , columns [y]

        }
}
