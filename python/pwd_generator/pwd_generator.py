import random
import string

length = int(input("Please input the length of the password\n"))
while True:
    numOfLetters = int(input("Please input number of letters should be contain\n"))
    numOfDigits = int(input("Please input number of digits )should be contained\n"))
    if (numOfLetters+numOfDigits) > length:
        print("number of letters + number of digits exceed entered length, please re-enter")
        continue
    else:
        break

pwd = []

i = length
hasUpper = False
hasLower = False

while i > 0:
    ran = random.randint(1,100)
    if ran <= 50 and numOfLetters > 0:
        letter = random.choice(string.ascii_letters)
        print("numOfLetters: " + str(numOfLetters))
        numOfLetters -= 1
        if not hasUpper and letter.isupper():
            hasUpper = True
        pwd.append(letter)
    elif numOfDigits > 0:
        print("numOfDigits: " + str(numOfDigits))
        numOfDigits -= 1
        pwd.append(str(random.randint(0,9)))
    elif numOfDigits <= 0 and numOfLetters <= 0:
        pwd.append(random.choice(string.punctuation))
    else:
        continue
    i-=1
    print("i: " + str(i))
    print(pwd)

if not hasUpper:
    for x in pwd:
        if x in string.ascii_lowercase:
            pwd[pwd.index(x)] = x.upper()
            break;

random.shuffle(pwd)
print(''.join(pwd))