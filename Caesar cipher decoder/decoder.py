import os
"""
A functional approach on the caesar cipher
This type of cipher is when all the letters are shifted to another letter
For an example
If all the letters we're shifted by one to the right,
A would become B
B would C
and so on.

To solve this problem, get a Dictionary (not the python type) and find one word that when shifted by a value turns into English. Then try to shift all the other words by that same value. If all words are readable, then the cipher is broken into plain text. Otherwise, it is not a Caesar Cipher
For this solution, the letters will be all lower case and z and a are on the boundaries.
"""


#Open List of words sample (included) and store into a set for fast retreival
#the words I'm using have one line each to themselves
word_bank = set()
word_bank_file = open('word_list.txt','r')
for word in word_bank_file:
    word_bank.add(word.strip())
    pass

#get file path of cipher text and store the text
file_path=""
while not os.path.isfile(file_path):
    file_path=str(input('Enter file path of cipher text:'))
    pass

cipher_text = open(file_path).read().strip()

words = cipher_text.lower().split(' ')


def shift_letter(letter:chr, shift:int)->chr:
    return chr((ord(letter)+shift-ord('a'))%26+ord('a'))

def attempt(words:set, word_index=0, shift=0)->tuple:
    """
    this will attempt to solve the cipher
    returns a tuple (bool, shift) stating if the shift is possible or not) and what the last shift tried was
    """
    global word_bank
    if shift == 26:#a base case
        return (False, shift)
    guess = ''.join(map(str,[shift_letter(c, shift) for c in words[word_index]]))
    if guess in word_bank:
        if len(words)-1 == word_index:#it's been successfully guessed
            return (True, shift)
        return attempt(words, word_index+1, shift)#continue if there's still words
    return attempt(words, 0, shift+1)#if the guess is incorrect, try another shift from the beginning

worked, shift = attempt(words)

def shift_cipher(text:str,shift:int)->str:
    plain_text =''
    for c in cipher_text:
        if c.isalpha():
            plain_text += shift_letter(c,shift)
        else:
            plain_text+=c
            pass
        pass
    return plain_text

if worked:
    print("The cipher worked. the plain text was shifted by {0} and the output text is \n{1}".format(shift,shift_cipher(cipher_text,shift)))
    pass
else:
    print("The cannot be solved with this method or word bank Here are the possibilities")
    for shift in range(0,26):
        print('shift: {0}\t phrase: {1}'.format(shift,shift_cipher(cipher_text,shift)))
print("Done")

