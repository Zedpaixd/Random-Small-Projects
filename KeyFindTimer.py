import msvcrt
import random
import time

Press = "wasd"

GameStart = input("Do you want to start the game? (Y/N) :").lower()
print ("To stop the game press the \".\" button")
time.sleep(3)

if (GameStart == 'y'):
    while GameStart == 'y':

        a = chr(random.randint(1,26)+96)
        print("\n>>> "+a+" <<<")
        init = time.perf_counter()


        while Press is not a:
             if msvcrt.kbhit():
                 key = msvcrt.getch()
                 Press = chr(key[0])
            
             if (Press == a):
                fin = time.perf_counter()
                print("\nYou took " , round(fin - init, 4) , " seconds to find the key")

             if (Press == "."):
                GameStart = "n"
                break
