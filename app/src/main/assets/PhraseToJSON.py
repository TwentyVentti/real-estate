# -*- coding: utf-8 -*-
"""
Created on Tue Oct 20 19:58:42 2020

@author: andre
"""
# -*- coding: utf-8 -*-
"""
Created on Thu Oct  8 11:53:38 2020

@author: andre
"""
from bs4 import BeautifulSoup
import json
import traceback
import requests
from random import randint
from googletrans import Translator            
        
url = "https://www.fluentu.com/blog/english/english-travel-phrases/"
headers = {'User-Agent':'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36'}
request = (requests.get(url,timeout=None, headers= headers)).content
soup = BeautifulSoup(request, 'html.parser')
#phrases = [x.text.replace("\xa0","") if x.text.contains("\xa0") for x in soup.findAll('strong')][3:]
sections = [x.text for x in (soup.findAll('h2'))]+["Directions"]
phrases = [x.text.replace('\xa0','') for x in soup.findAll('strong')][3:-2]
directions = [x.text for x in soup.findAll('li')][:9]

greetingsCount = phrases[:5] #level 1,#level 2
airportCount = phrases[5:11] #level 2
airplaneCount = phrases[11:15]#level 1,#level 2
customsCount = phrases[15:21]#level 2
destinationCount = phrases[21:28]#level 1,#level 2
hotelCount = phrases[28:40]#level 2
townCount = phrases[41:47] #need to incorporate directions (not bold)#level 1,#level 2
restaurantCount = phrases[47:55]#level 2
problemsCount = phrases[55:58]#level 1,#level 2
#directions level 2,3
#print(directions)



cfile = open("input.csv",encoding="utf8").read().split(',')
cfile = list(filter(('').__ne__, cfile))
cfile = list(filter(('English Speaking Courses').__ne__, cfile))[1:-6]
a = []
for i,word in enumerate(cfile[:80]):
    s = word.replace('…','')
    f = s.replace('.','')
    if f[-1].isnumeric and f[-2].isnumeric:
        f = f[:-2]
        a +=[f]
    elif f[-1].isnumeric:
        f = f[:-1]
        a +=[f]
    else:    
        a +=[f]
    
usedIntegers = []

def randID(level, language):
    rand = int(str(level) + str(language) +str(randint(1,100)))
    
    for x in range(1,10000):  
        if rand not in usedIntegers:
            if not rand == None:    
                usedIntegers.append(rand)
            else:
                rand = int(str(level) + str(language) +str(randint(1,100)))
                continue
            return rand
        else:
            rand = int(str(level) + str(language) +str(randint(1,100)))

class phrase:
    def __init__(self):
        self.language =""
        self.phrase   = ""
        self.level   = 0
        self.id      = 0
        self.section = ""
        self.english = ""
        
    def setLevel(self, level):
        self.level = level
    def setID(self,ident):
        self.id = ident
    def setSection(self,section):
        self.section = section
        
    def setLang(self,language):
        self.language = language
        
    def setLangPhrase(self,phrase):
        self.phrase   = phrase
        
    def setEnglish(self,english):
        self.english = english
        
beginnerPhrases = a[1:28]
intermediatePhrases = a[29:56]
advancedPhrases = a[57:]
indexOfPhrases = [80]
for string in cfile[81:350]:
    for i,word in enumerate(beginnerPhrases):
        length = int(len(word)/8)
        if word[10:-length] in string and not length==0:
            indexOfPhrases+=[cfile.index(string)]

intermediatePhrases = []

for index in indexOfPhrases:
    firstChar = cfile[index][0]
    secondChar = cfile[index][1]
    sections.append(cfile[index])
    if firstChar.isnumeric and secondChar.isnumeric:
        tenPhrases = int(firstChar+secondChar)
        intermediatePhrases.append(cfile[index+1:index+tenPhrases])
    if firstChar.isnumeric and not secondChar.isnumeric:
        fivePhrases = int(firstChar)
        intermediatePhrases.append(cfile[index+1:index+fivePhrases])
            
spanishCode = "es"
italianCode = "it"
dutchCode = "nl"
frenchCode = "fr"
germanCode = "de"
p1Lang = 1
p2Lang = 2
p3Lang = 3
p4Lang = 4
p5Lang = 5
import time 
x = time.time()
def translate():    
    phraseDictionary = {k: [] for k in sections}
    totalPhrases =0
    totalSections =0
    for secIndex,section in enumerate(sections[:11],start=0):
        totalSections +=1
        print("Total Sections: " + str(totalSections))
        for i,english in enumerate(phrases,start=0):
            try:
                totalPhrases+=1
                print("Total Phrases: " + str(totalPhrases))
                z = time.time()-x
                print("Time Elapsed: " + str(round((z/60),2)) + " minutes")
                print()
                print()
                translator = Translator()
                french     = translator.translate(english,dest=frenchCode).text
                italian = translator.translate(english,dest=italianCode).text
                spanish = translator.translate(english,dest=spanishCode).text
                dutch = translator.translate(english,dest=dutchCode).text
                german = translator.translate(english,dest=germanCode).text
                    
                p1 =  phrase()
                p1.setEnglish(english)
                p1.setSection(section)
                p1.setLang("French")
                p1.setLangPhrase(french)
                p1.setEnglish(english)
                
                
                p2 =  phrase()
                p2.setEnglish(english)
                p2.setSection(section)
                p2.setLang("Italian")
                p2.setLangPhrase(italian)
                p2.setEnglish(english)
                
                
                p3 =  phrase()
                p3.setEnglish(english)
                p3.setSection(section)
                p3.setLang("Spanish")
                p3.setLangPhrase(spanish)
                p3.setEnglish(english)
                
                
                p4 =  phrase()
                p4.setEnglish(english)
                p4.setSection(section)
                p4.setLang("Dutch")
                p4.setLangPhrase(dutch)
                p4.setEnglish(english)
                
                p5 =  phrase()
                p5.setEnglish(english)
                p5.setSection(section)
                p5.setLang("German")
                p5.setLangPhrase(german)
                p4.setEnglish(english)
                
                print("Translated "+str(i)+" :phrases ")
                if i in range(0,5):
                    p1.setID(randID(1,p1Lang))
                    p2.setID(randID(1,p2Lang))
                    p3.setID(randID(1,p3Lang))
                    p4.setID(randID(1,p4Lang))
                    p5.setID(randID(1,p5Lang))
                    p1.setLevel(1)
                    p2.setLevel(1)
                    p3.setLevel(1)
                    p4.setLevel(1)
                    p5.setLevel(1)
                    phraseDictionary.get(section).append(p1.__dict__)
                    phraseDictionary.get(section).append(p2.__dict__)
                    phraseDictionary.get(section).append(p3.__dict__)
                    phraseDictionary.get(section).append(p4.__dict__)
                    phraseDictionary.get(section).append(p5.__dict__)
                    
                if i in range(5,11):      
                    p1.setID(randID(2,p1Lang))
                    p2.setID(randID(2,p2Lang))
                    p3.setID(randID(2,p3Lang))
                    p4.setID(randID(2,p4Lang))
                    p5.setID(randID(2,p5Lang))  
                    p1.setLevel(2)
                    p2.setLevel(2)
                    p3.setLevel(2)
                    p4.setLevel(2)
                    p5.setLevel(2)
                    phraseDictionary.get(section).append(p1.__dict__)
                    phraseDictionary.get(section).append(p2.__dict__)
                    phraseDictionary.get(section).append(p3.__dict__)
                    phraseDictionary.get(section).append(p4.__dict__)
                    phraseDictionary.get(section).append(p5.__dict__)
                    
                if i in range(11,15):
                    p1.setID(randID(1,p1Lang))
                    p2.setID(randID(1,p2Lang))
                    p3.setID(randID(1,p3Lang))
                    p4.setID(randID(1,p4Lang))
                    p5.setID(randID(1,p5Lang))
                    p1.setLevel(1)
                    p2.setLevel(1)
                    p3.setLevel(1)
                    p4.setLevel(1)
                    p5.setLevel(1)
                    phraseDictionary.get(section).append(p1.__dict__)
                    phraseDictionary.get(section).append(p2.__dict__)
                    phraseDictionary.get(section).append(p3.__dict__)
                    phraseDictionary.get(section).append(p4.__dict__)
                    phraseDictionary.get(section).append(p5.__dict__)
                    
                if i in range(15,21):     
                    p1.setID(randID(2,p1Lang))
                    p2.setID(randID(2,p2Lang))
                    p3.setID(randID(2,p3Lang))
                    p4.setID(randID(2,p4Lang))
                    p5.setID(randID(2,p5Lang))    
                    p1.setLevel(2)
                    p2.setLevel(2)
                    p3.setLevel(2)
                    p4.setLevel(2)
                    p5.setLevel(2)
                    phraseDictionary.get(section).append(p1.__dict__)
                    phraseDictionary.get(section).append(p2.__dict__)
                    phraseDictionary.get(section).append(p3.__dict__)
                    phraseDictionary.get(section).append(p4.__dict__)
                    phraseDictionary.get(section).append(p5.__dict__)
                    
                if i in range(21,28):   
                    p1.setID(randID(1,p1Lang))
                    p2.setID(randID(1,p2Lang))
                    p3.setID(randID(1,p3Lang))
                    p4.setID(randID(1,p4Lang))
                    p5.setID(randID(1,p5Lang))      
                    p1.setLevel(1)
                    p2.setLevel(1)
                    p3.setLevel(1)
                    p4.setLevel(1)
                    p5.setLevel(1)
                    phraseDictionary.get(section).append(p1.__dict__)
                    phraseDictionary.get(section).append(p2.__dict__)
                    phraseDictionary.get(section).append(p3.__dict__)
                    phraseDictionary.get(section).append(p4.__dict__)
                    phraseDictionary.get(section).append(p5.__dict__)
                    
                if i in range(28,40):   
                    p1.setID(randID(2,p1Lang))
                    p2.setID(randID(2,p2Lang))
                    p3.setID(randID(2,p3Lang))
                    p4.setID(randID(2,p4Lang))
                    p5.setID(randID(2,p5Lang))      
                    p1.setLevel(2)
                    p2.setLevel(2)
                    p3.setLevel(2)
                    p4.setLevel(2)
                    p5.setLevel(2)
                    phraseDictionary.get(section).append(p1.__dict__)
                    phraseDictionary.get(section).append(p2.__dict__)
                    phraseDictionary.get(section).append(p3.__dict__)
                    phraseDictionary.get(section).append(p4.__dict__)
                    phraseDictionary.get(section).append(p5.__dict__)
                    
                if i in range(41,47):  
                    p1.setID(randID(1,p1Lang))
                    p2.setID(randID(1,p2Lang))
                    p3.setID(randID(1,p3Lang))
                    p4.setID(randID(1,p4Lang))
                    p5.setID(randID(1,p5Lang))       
                    p1.setLevel(1)
                    p2.setLevel(1)
                    p3.setLevel(1)
                    p4.setLevel(1)
                    p5.setLevel(1)
                    phraseDictionary.get(section).append(p1.__dict__)
                    phraseDictionary.get(section).append(p2.__dict__)
                    phraseDictionary.get(section).append(p3.__dict__)
                    phraseDictionary.get(section).append(p4.__dict__)
                    phraseDictionary.get(section).append(p5.__dict__)
        
                if i in range(47,55):  
                    p1.setID(randID(1,p1Lang))
                    p2.setID(randID(1,p2Lang))
                    p3.setID(randID(1,p3Lang))
                    p4.setID(randID(1,p4Lang))
                    p5.setID(randID(1,p5Lang))       
                    p1.setLevel(1)
                    p2.setLevel(1)
                    p3.setLevel(1)
                    p4.setLevel(1)
                    p5.setLevel(1)
                    phraseDictionary.get(section).append(p1.__dict__)
                    phraseDictionary.get(section).append(p2.__dict__)
                    phraseDictionary.get(section).append(p3.__dict__)
                    phraseDictionary.get(section).append(p4.__dict__)
                    phraseDictionary.get(section).append(p5.__dict__)
                    
                if i in range(55,58):  
                    p1.setID(randID(1,p1Lang))
                    p2.setID(randID(1,p2Lang))
                    p3.setID(randID(1,p3Lang))
                    p4.setID(randID(1,p4Lang))
                    p5.setID(randID(1,p5Lang))       
                    p1.setLevel(1)
                    p2.setLevel(1)
                    p3.setLevel(1)
                    p4.setLevel(1)
                    p5.setLevel(1)
                    phraseDictionary.get(section).append(p1.__dict__)
                    phraseDictionary.get(section).append(p2.__dict__)
                    phraseDictionary.get(section).append(p3.__dict__)
                    phraseDictionary.get(section).append(p4.__dict__)
                    phraseDictionary.get(section).append(p5.__dict__)
            except Exception as err:
                print("Failed at: " + section+english)
                try:
                    raise TypeError("Again !?!")
                except:
                    pass
                traceback.print_tb(err.__traceback__)
                
    
    for k,sectionOfPhrases in enumerate(intermediatePhrases,start=11):
        totalSections +=1
        print("Total Sections: " + str(totalSections))
        for j,english in enumerate(sectionOfPhrases):
            try:
                totalPhrases+=1
                print("Total Phrases: " + str(totalPhrases))
                z = time.time()-x
                print("Time Elapsed: " + str(round((z/60),2)) + " minutes")
                print()
                print()
                translator = Translator()
                french     = translator.translate(english,dest=frenchCode).text
                italian = translator.translate(english,dest=italianCode).text
                spanish = translator.translate(english,dest=spanishCode).text
                dutch = translator.translate(english,dest=dutchCode).text
                german = translator.translate(english,dest=germanCode).text
                
                # Creating indiviudal objects for each language
                p1 =  phrase()
                p1.setEnglish(english)
                p1.setSection(sections[k])
                p1.setLang("French")
                p1.setLangPhrase(french)
                p1.setEnglish(english)
                
                
                p2 =  phrase()
                p2.setEnglish(english)
                p2.setSection(sections[k])
                p2.setLang("Italian")
                p2.setLangPhrase(italian)
                p2.setEnglish(english)
                
                
                p3 =  phrase()
                p3.setEnglish(english)
                p3.setSection(sections[k])
                p3.setLang("Spanish")
                p3.setLangPhrase(spanish)
                p3.setEnglish(english)
                
                
                p4 =  phrase()
                p4.setEnglish(english)
                p4.setSection(sections[k])
                p4.setLang("Dutch")
                p4.setLangPhrase(dutch)
                p4.setEnglish(english)
                
                p5 =  phrase()
                p5.setEnglish(english)
                p5.setSection(sections[k])
                p5.setLang("German")
                p5.setLangPhrase(german)
                p4.setEnglish(english)
                
                
                if k in range(11,22):
                    p1.setID(randID(3,p1Lang))
                    p2.setID(randID(3,p2Lang))
                    p3.setID(randID(3,p3Lang))
                    p4.setID(randID(3,p4Lang))
                    p5.setID(randID(3,p5Lang))
                    p1.setLevel(3)
                    p2.setLevel(3)
                    p3.setLevel(3)
                    p4.setLevel(3)
                    p5.setLevel(3)     
                    phraseDictionary.get(sections[k]).append(p1.__dict__)
                    phraseDictionary.get(sections[k]).append(p2.__dict__)
                    phraseDictionary.get(sections[k]).append(p3.__dict__)
                    phraseDictionary.get(sections[k]).append(p4.__dict__)
                    phraseDictionary.get(sections[k]).append(p5.__dict__)
                    
                if k in range(22,29):
                    p1.setID(randID(4,p1Lang))
                    p2.setID(randID(4,p2Lang))
                    p3.setID(randID(4,p3Lang))
                    p4.setID(randID(4,p4Lang))
                    p5.setID(randID(4,p5Lang))
                    p1.setLevel(4)
                    p2.setLevel(4)
                    p3.setLevel(4)
                    p4.setLevel(4)
                    p5.setLevel(4)     
                    phraseDictionary.get(sections[k]).append(p1.__dict__)
                    phraseDictionary.get(sections[k]).append(p2.__dict__)
                    phraseDictionary.get(sections[k]).append(p3.__dict__)
                    phraseDictionary.get(sections[k]).append(p4.__dict__)
                    phraseDictionary.get(sections[k]).append(p5.__dict__)
                    
                if k in range(29,40):
                    p1.setID(randID(5,p1Lang))
                    p2.setID(randID(5,p2Lang))
                    p3.setID(randID(5,p3Lang))
                    p4.setID(randID(5,p4Lang))
                    p5.setID(randID(5,p5Lang))
                    p1.setLevel(5)
                    p2.setLevel(5)
                    p3.setLevel(5)
                    p4.setLevel(5)
                    p5.setLevel(5)                
                    phraseDictionary.get(sections[k]).append(p1.__dict__)
                    phraseDictionary.get(sections[k]).append(p2.__dict__)
                    phraseDictionary.get(sections[k]).append(p3.__dict__)
                    phraseDictionary.get(sections[k]).append(p4.__dict__)
                    phraseDictionary.get(sections[k]).append(p5.__dict__)
            except Exception as err:
                print("Failed at: " + sections[k] + english)
                try:
                    raise TypeError("Again !?!")
                except:
                    pass
                traceback.print_tb(err.__traceback__)
    return phraseDictionary
pd = translate()
print(pd)
with open('result.json', 'w', encoding='utf8') as json_file:
        json.dump(pd, json_file, ensure_ascii=False)








