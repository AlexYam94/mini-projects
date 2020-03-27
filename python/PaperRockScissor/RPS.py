import random


def check_result(playerInputIndex, comInputIndex):
    print("playerInputIndex: "+ str(playerInputIndex))
    print("comInputIndex: "+ str(comInputIndex))

    if (playerInputIndex + comInputIndex) == 0:
        print("Draw!\n")
    elif playerInputIndex == 2:
        if playerInputIndex + comInputIndex > 0:
            print("You Win!\n")
        else:
            print("You Lose!\n")
    elif playerInputIndex == 1:
        if comInputIndex == -3:
            print("You Win!\n")
        else:
            print("You Lose!\n")
    elif playerInputIndex == 3:
        if comInputIndex != -1:
            print("You Win!\n")
        else:
            print("You Lose!\n")

choice = "1";
choices = ["dummy","rock", "paper", "scissors"]
choiceIndex = 0

while True:
    choice = input("Please input: rock, paper or scissors\n")
    choice = choice.lower();
    choiceIndex = random.randint(1,3)
    check_result(choices.index(choice), choiceIndex*-1)
        
        

