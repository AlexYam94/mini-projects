import random
from hangman_graphic import HANGMANPICS
def random_line(afile):
    line = next(afile)
    for num, aline in enumerate(afile, 2):
      if random.randrange(num): continue
      line = aline
    return line

def is_answer_filled(answer):
      result = 0
      for x in answer:
        if x == "_":
              result +=1
      if result > 0:
            return False
      return True

file = open("words.txt")
answer = []
enteredLetters = []
index = -1
count = 0
i = 0
guess = ""
word = random_line(file).strip()
# print(word)
print("----------------------------------Hangman----------------------------------")
print("You have 7 attempts")
print("Target contains " + str(len(word)) + " letters")
while True:
    for x in word:
     answer.append("_")
    print(answer)
    while i <7:
          if ''.join(answer) == word:
            print("Win!")
            break
          guess = input("please enter a guess\n")
          if len(guess.strip()) != 1:
            print("Please enter only 1 letter\n")
            continue
          if guess in word and not is_answer_filled(answer):
            index = word.find(guess)
            count = word.count(guess)
            if count == 1 or guess not in answer:
                  answer[index] = guess
            else:
                  index = word.find(guess, index+1)
                  answer[index] = guess
          else:
            # print("wrong! attempts - 1")
            print(HANGMANPICS[i])
            i+=1
            print("attempts remaing: " + str(7 - i) + "")
          enteredLetters.append(guess)
          print("Entered values: ")
          print(enteredLetters)
          print("The word:")
          print(answer)
    if i == 7:
      print(word)
    guess = input("Enter q to quit\n")
    if guess.lower() == "q":
      break
    file.close();
    file = open("words.txt")
    word = random_line(file).strip()
    i=0
    answer.clear()
    enteredLetters.clear()
