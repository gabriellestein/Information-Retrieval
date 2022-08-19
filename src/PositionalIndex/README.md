Directions to run program

Locate the PositionalIndex.jar file
cd to this location in terminal

Run command: java -jar PositionalIndex.jar
You will be asked to input the file path to the corpus. Input full file path, without quotations.

You will be alerted when the corpus indexing starts and finishes.

This corpus will be indexed. Then you will be asked to enter a search query.
Enter a query in this format: 'term 1' \k 'term 2'
For example: chicken \1 nugget

The list of documents that match this query will be returned. You will then be asked if you want to continue.
Type Y or N to continue.

If you would like to make another jar file to test the code:
In Eclipse, right-click the correct package containing all the source code.
Select export, then export as a runnable jar file.
You can run this with the above command, just make sure if you change the .jar file name that you use that instead of PositionalIndex.jar.
You will need to add included external jar file (included with submisson) to buildpath is you do this.

Results from Test Cases
Input files directory path name is: C:\Users\gabby\OneDrive - East Carolina University\CSCI 4130\Programming Assignments\02-programming-assignment\corpus
Beginning corpus indexing . . .
Finished corpus indexing
Please enter a phrase to search for in corpus ('term a' \k 'term b')
when \1 she

DocID   DocName
7       duval-2011.txt
26      verbert-2011.txt
43      garcia-solorzano-2012.txt
154     mendiburo-2014.txt
222     milligan-2015.txt
238     simsek-2015.txt
393     herodotou-2017.txt
Continue? (Y/N)
y
Please enter a phrase to search for in corpus ('term a' \k 'term b')
complicated \2 questions

DocID   DocName
286     hsiao-2016.txt
344     wise-2016.txt
Continue? (Y/N)
y
Please enter a phrase to search for in corpus ('term a' \k 'term b')
for \3 when

DocID   DocName
3       bakharia-2011.txt
8       fancsali-2011.txt
11      graf-2011.txt
13      haythornthwaite-2011.txt
16      ochoa-2011.txt
28      arnold-2012.txt
55      mckay-2012.txt
56      mcnely-2012.txt
61      rahman-2012.txt
65      santos-2012.txt
74      suthers-2012a.txt
76      ahn-2013.txt
80      blikstein-2013.txt
84      clow-2013.txt
86      dyckhoff-2013.txt
90      halatchliyski-2013.txt
93      kizilcec-2013.txt
94      knight-2013.txt
96      lauria-2013.txt
97      lonn-2013.txt
102     niemann-2013.txt
103     prinsloo-2013.txt
112     slotta-2013.txt
119     aguiar-2014.txt
123     arnold-2014.txt
133     dawson-2014.txt
142     grann-2014.txt
143     greenberg-2014.txt
146     hecking-2014.txt
147     hickey-2014.txt
153     mendez-2014.txt
154     mendiburo-2014.txt
156     okada-2014.txt
163     swenson-2014.txt
167     waddington-2014.txt
179     bergner-2015.txt
181     brooks-2015a.txt
198     gibson-2015.txt
216     kovanovic-2015.txt
223     molenaar-2015.txt
240     vahdati-2015.txt
241     van_inwegen-2015.txt
245     worsley-2015.txt
264     bull-2016.txt
279     grawemeyer-2016.txt
284     hatala-2016.txt
292     joksimovic-2016.txt
298     koedinger-2016.txt
304     liu-2016.txt
314     muslim-2016.txt
315     ochoa-2016.txt
323     pelanek-2016.txt
327     rienties-2016.txt
329     robinson-2016.txt
333     selent-2016.txt
335     shum-2016-a.txt
340     wang-2016.txt
341     wang1-2016.txt
344     wise-2016.txt
364     bote-lorenzo-2017.txt
372     chiu-2017.txt
377     cross-2017.txt
384     ez-zaouia-2017.txt
390     hansen-2017.txt
393     herodotou-2017.txt
397     holstein-2017a.txt
425     mills-2017.txt
428     mouri-2017.txt
430     ocumpaugh-2017.txt
435     park-2017.txt
437     poquet-2017.txt
457     wise-2017.txt
Continue? (Y/N)
y
Please enter a phrase to search for in corpus ('term a' \k 'term b')
Michael \1 Atkisson

DocID   DocName
1       atkisson-2011.txt
Continue? (Y/N)
n
