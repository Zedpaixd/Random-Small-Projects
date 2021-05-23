from words import wordlist
import random
import os

clear = lambda: os.system('cls')

def WordPick():

    word = random.choice(wordlist)
    return word.upper()


def Guess():

    right = 0

    while right is not 1:
        guess = input("\nGuess a letter:").upper()


        if len(guess) > 1:
            print("Guess is invalid, try again\n")
            right = 0
        else:
            exists = 0
            for char in word:
                if char == guess:
                    exists = 1
                    break

            if exists == 1:
                global LettersGuessed
                if guess not in LettersGuessed:
                    print("Nailed it.")
                    LettersGuessed+=guess
                    for a in range(0,len(word)):
                        if guess == word[a]:
                            global blank
                            word_as_list = list(blank)
                            word_as_list[a * 2] = guess
                            blank = "".join(word_as_list)
                    print(blank,"\nGuessed letters are:",LettersGuessed,"\n")
                else:
                    print(blank,"\nSaid already, letters guessed are: " + LettersGuessed,".\n")
            else:
                global lives
                if guess not in LettersGuessed:
                    lives -=1
                    print("Guess is wrong, you have ",lives," lives left\n"+blank)
                    LettersGuessed+=guess
                    print("Guessed letters are: ",LettersGuessed,"\n")
                else:
                    print("Said already, letters guessed are: " + LettersGuessed)


        if lives == 0:
            clear()
            print("Game Over, word was:", word)
            right = 1
            global guessed 
            guessed = 0


        if "_" not in blank:
            print("You won!\nWord was:", word)
            right = 1
            guessed = 1
                


game = input("Do you want to play? (Y/N)").upper()

while game == "Y":
    word = WordPick()
    blank = "_ " * len(word)
    guessed = -1
    LettersGuessed = ""
    lives = 6

    while guessed == -1:
        print("The word is: ",blank)
        Guess()

        game = input("Do you want to play? (Y/N)").upper()
