#include <iostream>
#include <cstring>
#include <time.h>
#include <random>
#include <cstdlib>
#include <windows.h>

//#include <chrono>
//#include <thread>


using namespace std;

//using namespace std::this_thread;
//using namespace std::chrono;



char you[32],enemy[32],a[100],b[100],c[100],d[100],doubleHit[4]="yes",e[100],f[100];
int turn,random,you_hp=100,enemy_hp=100,hit,damage,hitPercent=40,MaxDamage=32,MinDamage=1,sum=0,i,doubleHitChance=1,potion1=0,potion2=0,potion3=0;
float flt;


void choosingScreen();



int Verify(char d[])
{
sum=0;
for (i=0;i<strlen(d);i++)

    {if (isdigit(d[i]))
        {sum+=(int)d[i]-(int)'0';
        if (i<strlen(d)-1)
            sum*=10;}

    else {cout<<"\n\nThis value is invalid. Please input another value made solely out of digits.";
          Sleep(2000); cout<<"\n\nSelection:"; sum=-1; break;}}
}



int optionMenu()
{
system("CLS");
cout<<"--------------------------------------------------\n\n\tWhat would you like to edit? \n\n->Health\n->Odds\n->Damage\n->DoubleHit\n->Return\n\n--------------------------------------------------\n\nSelection: ";
cin.getline(b,100);

while (strcmp(b,"Health")==0||strcmp(b,"health")==0||strcmp(b,"Odds")==0||strcmp(b,"odds")==0||strcmp(b,"Damage")==0||strcmp(b,"damage")==0||strcmp(b,"doublehit")==0||strcmp(b,"DoubleHit")==0||strcmp(b,"doubleHit")==0||strcmp(b,"return")==0||strcmp(b,"Return")==0)

    {if (strcmp(b,"Health")==0||strcmp(b,"health")==0)
        {cout<<"\n\nYour health or the enemy's? (mine/enemy)\n";
         cout<<"Selection: ";cin.getline(c,100);

        if (strcmp(c,"Mine")==0||strcmp(c,"mine")==0)
            {cout<<"\nCurrent max is:"<<you_hp<<". Change it to:"; do { cin.get(d,100); cin.get(); Verify(d); } while(i<strlen(d)); you_hp=sum;}

        if (strcmp(c,"Enemy")==0||strcmp(c,"enemy")==0)
            {cout<<"\nCurrent max is:"<<enemy_hp<<". Change it to:"; do { cin.get(d,100); cin.get(); Verify(d); } while(i<strlen(d)); enemy_hp=sum;}}



    if (strcmp(b,"Odds")==0||strcmp(b,"odds")==0)
        {cout<<"\nCurrent chance of hitting is:"<<100-hitPercent<<". Change it to:";

         do{
            cin.get(d,100);cin.get();
            Verify(d);
            if (sum>100||sum==0) {cout<<"\n\nThis value is invalid. Please input another value made solely out of digits. \n\nSelection:"; Sleep(2000);sum=0;i=0;}}
         while(i<strlen(d));

         hitPercent=sum; hitPercent=100-hitPercent;}



    if (strcmp(b,"Damage")==0||strcmp(b,"damage")==0)
        {cout<<"\n\nMinimum damage or maximum damage? (min/max)\n";
         cout<<"Selection: ";cin.getline(c,100);

        if (strcmp(c,"Min")==0||strcmp(c,"min")==0)
            {cout<<"\nCurrently the minimum damage is:"<<MinDamage<<". Change it to:"; do { cin.get(d,100); cin.get(); Verify(d); } while(i<strlen(d)); MinDamage=sum;}

        if (strcmp(c,"Max")==0||strcmp(c,"max")==0)
            {cout<<"\nCurrently the maximum damage is:"<<MaxDamage<<". Change it to:"; do { cin.get(d,100); cin.get(); Verify(d); } while(i<strlen(d)); MaxDamage=sum;}}



    if (strcmp(b,"doublehit")==0||strcmp(b,"DoubleHit")==0||strcmp(b,"doubleHit")==0)
        {cout<<"\n\nDo you want a chance of double-hitting to exist (currently it is set on "<<doubleHit<<")? (yes/no)\n";

        while (strcmp(d,"yes")!=0&&strcmp(d,"Yes")!=0&&strcmp(d,"no")!=0&&strcmp(d,"No")!=0)
            {cin.get(d,100); cin.get();

            if (strcmp(d,"yes")==0||strcmp(d,"Yes")==0) strcpy(doubleHit,d);

                else if (strcmp(d,"no")==0||strcmp(d,"No")==0) strcpy(doubleHit,d);

                    else {cout<<"\n\nThis value is invalid. Please try again."; Sleep(2000); system("CLS"); cout<<"--------------------------------------------------\n\n\tWhat would you like to edit? \n\n->Health\n->Odds\n->Damage\n->DoubleHit\n->Return\n\n--------------------------------------------------\n\nSelection: "
                                                                                                                <<b
                                                                                                                <<"\n\n\nDo you want a chance of double-hitting to exist (currently it is set on "<<doubleHit<<")? (yes/no)\n";}}

        strcpy(d,"\0");}


if (strcmp(b,"return")==0||strcmp(b,"Return")==0)
    {cout<<"\n\nMoving back to selection screen..."; Sleep(2000); return 1;}


system("CLS");
cout<<"--------------------------------------------------\n\n\tWhat would you like to edit? \n\n->Health\n->Odds\n->Damage\n->DoubleHit\n->Return\n\n--------------------------------------------------\n\nSelection: ";
cin.getline(b,100);}

if (strcmp(b,"Return")!=0&&strcmp(b,"return")!=0&&strcmp(b,"Health")!=0&&strcmp(b,"health")!=0&&strcmp(b,"Odds")!=0&&strcmp(b,"odds")!=0&&strcmp(b,"Damage")!=0&&strcmp(b,"damage")!=0&&strcmp(b,"doublehit")!=0&&strcmp(b,"DoubleHit")!=0&&strcmp(b,"doubleHit")!=0)
    {cout<<"\n\nThis command does not exist. Please try again."; Sleep(2000); optionMenu();}

}



void gameStart()
{
srand(time(NULL));
MaxDamage++;
system("CLS");

cout<<"Insert your name: ";cin.getline(you,32);
cout<<"\nInsert your opponent's name: ";cin.getline(enemy,32);

cout<<"\nWho would you rather go first? You, Your enemy or randomly chosen? Reply with 1 2 or 3   - ";
cin>>turn;turn--;

if (turn==2)
    {cout<<"\n\nRolling dice which will decide who goes first...";
    turn=rand()%2;
    if (turn==0) cout<<"\nThe odds were in your favor, you're first!\n\n";
        else cout<<"\nThe odds were against you, you're second.\n\n";}


    while (you_hp>0&&enemy_hp>0)

        {if (turn==0)
            {hit=rand()%101;
            cout<<"-----------------------------YOUR TURN--------------------------------\n";

            if (potion1==1) {you_hp+=rand()%5+1; cout<<"(( The passive regen elixir took effect, you are now at "<<you_hp<<" hp )) \n"; }

            if (potion3==1) hit=hitPercent+1;

            if(hit<hitPercent) cout<<you<<" missed.\n\n";

                else {damage=rand()%(MaxDamage-MinDamage)+MinDamage; enemy_hp-=damage;
                    cout<<you<<" hits for "<<damage<< " reducing " <<enemy <<" to "<<enemy_hp<<" hp\n\n";}
                    turn++;

                if ((strcmp(doubleHit,"yes")==0||strcmp(doubleHit,"Yes")==0)&&(hit>hitPercent))
                    {doubleHitChance=rand()%101;
                    if (doubleHitChance<=10) {cout<<"!!Double hit!! ";
                                              damage=rand()%(MaxDamage-MinDamage)+MinDamage; enemy_hp-=damage;
                                              cout<<you<<" hits for "<<damage<< " reducing " <<enemy <<" to "<<enemy_hp<<" hp\n\n";}}

             }


            if (turn==1&&enemy_hp>0)
                {hit=rand()%101;
                cout<<"-----------------------------ENEMY'S TURN-----------------------------\n";
                if(hit<hitPercent) cout<<enemy<<" missed.\n\n";

                    else {damage=rand()%(MaxDamage-MinDamage)+MinDamage; you_hp-=damage;
                        cout<<enemy<<" hit for "<<damage<< " reducing "<<you<<" to "<<you_hp<<" hp\n\n";}
                        turn--;

                    if ((strcmp(doubleHit,"yes")==0||strcmp(doubleHit,"Yes")==0)&&(hit>hitPercent))
                        {doubleHitChance=rand()%101;
                        if (doubleHitChance<=10) {cout<<"!!Double hit!! ";
                                                  damage=rand()%(MaxDamage-MinDamage)+MinDamage; you_hp-=damage;
                                                  cout<<enemy<<" hit for "<<damage<< " reducing "<<you<<" to "<<you_hp<<" hp\n\n";}}

                    if (potion2==1) {flt=damage*12/100;
                                    if (flt-damage*12/100>=0.5) damage=flt+1;
                                        else damage=flt;
                                    cout<<"Due to the Wind's Blessing effect, "<<damage
                                    <<" self-damage has been inflicted. Current hp:"
                                    <<enemy_hp-damage<<endl<<endl;
                                    enemy_hp-=damage;}}}

        if (you_hp>enemy_hp) cout<<you<<" won!"; else cout<<enemy<<" won!                                                                                                                     \n\n";
        system ("pause");
}



int extraEffects()
{
system("CLS");
cout<<"--------------------------------------------------\n\nWhat effects would you like to be applied? (Please input 1 2 3 or return) \n\n->Passive regen (1-5 health recovery per round, can increase your max hp) \n\n->Wind's blessing (enemy takes damage when attacking you) \n\n->Eulne's blessing (you will never miss any hits) \n\n->Return\n\n--------------------------------------------------\n\nSelection: ";
cin.get(e,100); cin.get();

while (strcmp (e,"1")==0||strcmp (e,"2")==0||strcmp (e,"3")==0)
    {if (strcmp (e,"1")==0)
        {if (potion1==1)
            {cout<<"The potion has already been drank.";Sleep(2000);}

        if (potion1==0)
            {cout<<"\n\nMassive line of text here about the history of the potion \n\n Would you like to drink the potion? The action can not be undone. (yes/no)\n\nSelection: ";
            cin.getline(f,100);

            if (strcmp(f,"yes")==0||strcmp(f,"Yes")==0)
                potion1=1;

                else if (strcmp(f,"yes")!=0&&strcmp(f,"Yes")!=0&&strcmp(f,"no")!=0&&strcmp(f,"No")!=0)
                    {cout<<"\n\nYour selection is invalid."; Sleep(2000);}}}



    if (strcmp (e,"2")==0)
        {if (potion2==1)
            {cout<<"The potion has already been drank.";Sleep(2000);}

        if (potion2==0)
            {cout<<"\n\nMassive line of text here about the history of the potion \n\n Would you like to drink the potion? The action can not be undone. (yes/no)\n\nSelection: ";
            cin.getline(f,100);

            if (strcmp(f,"yes")==0||strcmp(f,"Yes")==0)
                potion2=1;

                else if (strcmp(f,"yes")!=0&&strcmp(f,"Yes")!=0&&strcmp(f,"no")!=0&&strcmp(f,"No")!=0)
                    {cout<<"\n\nYour selection is invalid."; Sleep(2000);}}}



    if (strcmp (e,"3")==0)
        {if (potion3==1)
            {cout<<"The potion has already been drank.";Sleep(2000);}

        if (potion3==0)
            {cout<<"\n\nMassive line of text here about the history of the potion \n\n Would you like to drink the potion? The action can not be undone. (yes/no)\n\nSelection: ";
            cin.getline(f,100);

            if (strcmp(f,"yes")==0||strcmp(f,"Yes")==0)
                potion3=1;

                else if (strcmp(f,"yes")!=0&&strcmp(f,"Yes")!=0&&strcmp(f,"no")!=0&&strcmp(f,"No")!=0)
                    {cout<<"\n\nYour selection is invalid."; Sleep(2000);}}}



system("CLS");
cout<<"--------------------------------------------------\n\nWhat effects would you like to be applied? (Please input 1 2 3 or return) \n\n->Passive regen (1-5 health recovery per round) \n\n->Wind's blessing (enemy takes damage when attacking you) \n\n->Eulne's blessing (you will never miss any hits) \n\n->Return\n\n--------------------------------------------------\n\nSelection: ";
cin.get(e,100); cin.get();}

if (strcmp(e,"Return")==0||strcmp(e,"return")==0)
    {cout<<"\n\nMoving back to selection screen..."; Sleep(2000); return 1;}

if (strcmp(e,"1")!=0&&strcmp(e,"2")!=0&&strcmp(e,"3")!=0&&strcmp(e,"Return")!=0&&strcmp(e,"return")!=0)
    {cout<<"\n\nThis command does not exist. Please try again."; Sleep(2000); extraEffects();}

}




void choosingScreen()
{
system("CLS");
cout<<"--------------------------------------------------\n\n\t\t\tWelcome!";
cout<<"\n\n\n\tWhere would you like to go:\n\n->Start\n->Effects\n->Options\n->Exit";
cout<<"\n\n--------------------------------------------------\n\nSelection: ";
cin.getline(a,100);

if (strcmp(a,"Start")==0||strcmp(a,"start")==0)
    gameStart();

if (strcmp(a,"Options")==0||strcmp(a,"options")==0)
    optionMenu();

if (strcmp(a,"effects")==0||strcmp(a,"Effects")==0)
    extraEffects();

if (strcmp(a,"Start")!=0&&strcmp(a,"start")!=0&&strcmp(a,"Options")!=0&&strcmp(a,"options")!=0&&strcmp(a,"Exit")!=0&&strcmp(a,"exit")!=0&&strcmp(a,"effects")!=0&&strcmp(a,"Effects")!=0)
    {cout<<"\n\nThis command does not exist. Please try again."; Sleep(2000); choosingScreen();}
}



int main()
{choosingScreen();

while (strcmp(a,"Options")==0||strcmp(a,"options")==0||strcmp(a,"effects")==0||strcmp(a,"Effects")==0)
    choosingScreen();

return 0;
}

