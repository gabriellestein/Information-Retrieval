Andrew Edwards
Gabrielle Stein


***NOTE: Our group got an extension due to extenuating circumstance for this assignment.
Please confirm with Xiaoli.

## Directions to run program

Locate the VectorSpaceModel.jar file
cd to this location in terminal

Run command: java -jar VectorSpaceModel.jar
You will be asked to input the file path to the corpus.
Input full file path, without quotations.

You will be alerted when the corpus indexing starts and finishes.

You will be asked to input the file path to the query file.
Input full file path, without quotations.

Then you will be asked to enter a query ID, as well as boosts that should be given to the title and abstract.
Then you will be asked how many documents you want to display.

The list of documents that match this query will be returned.
You will then be asked if you want to continue.
Type Y or N to continue.

If you would like to make another jar file to test the code:
In Eclipse, right-click the correct package containing all the source code.
Select export, then export as a runnable jar file.
You can run this with the above command, just make sure if you change the .jar file name that you use that instead of PositionalIndex.jar.
You will need to add included external jar file (included with submisson) to buildpath is you do this.

## Results from Test Cases
Please enter corpus file path: 
cran.all.1400
Input files directory path name is: cran.all.1400
Beginning corpus indexing . . .
Finished corpus indexing

Time taken to index corpus was 54ms
Please enter query file path: 
cran.qry
Query files directory path name is cran.qry
Beginning query indexing . . .
Finished query indexing

Time taken to index corpus was 2ms

Enter a query id: 
1
Query 1: what similarity laws must be obeyed when constructing aeroelastic modelsof heated high speed aircraft .
Enter the title boost value: 
.5
Enter the abstract boost value: 
.5
Boost values set: 0.5, 0.5
Searching Query: 1

Input top number of documents to display: 
10
***Top 10 documents***
Title: similarity laws for stressing heated wings .
Rank 	 DocID 	
1    	 13   

Title: similarity laws for aerothermoelastic testing .
Rank 	 DocID 	
2    	 486  

Title: some low speed problems of high speed aircraft .
Rank 	 DocID 	
3    	 792  

Title: aeroelastic problems in connection with high speedflight .
Rank 	 DocID 	
4    	 746  

Title: scale models for thermo-aeroelastic research .
Rank 	 DocID 	
5    	 184  

Title: some research on high speed flutter .
Rank 	 DocID 	
6    	 1111 

Title: free-flight techniques for high speed aerodynamic research .
Rank 	 DocID 	
7    	 141  

Title: high-speed viscous corner flow .
Rank 	 DocID 	
8    	 1250 

Title: models for aeroelastic investigation .
Rank 	 DocID 	
9    	 875  

Title: on flutter testing in high speed wind tunnels .
Rank 	 DocID 	
10   	 876  

Do you want to continue (y/n):
y

Enter a query id: 
2
Query 2: what are the structural and aeroelastic problems associated with flightof high speed aircraft .
Enter the title boost value: 
.5
Enter the abstract boost value: 
.5
Boost values set: 0.5, 0.5
Searching Query: 2

Input top number of documents to display: 
5
***Top 5 documents***
Title: some low speed problems of high speed aircraft .
Rank 	 DocID 	
1    	 792  

Title: aeroelastic problems in connection with high speedflight .
Rank 	 DocID 	
2    	 746  

Title: some structural and aerelastic considerations of highspeed flight .
Rank 	 DocID 	
3    	 12   

Title: two and three-dimensional unsteady lift problems in high speed flight .
Rank 	 DocID 	
4    	 700  

Title: theory of aircraft structural models subjected to aerodynamicheating and external loads .
Rank 	 DocID 	
5    	 51   

Do you want to continue (y/n):
y

Enter a query id: 
136
Query 136: how does a satellite orbit contract under the action of air drag inan atmosphere in which the scale height varies with altitude .
Enter the title boost value: 
.7
Enter the abstract boost value: 
.3
Boost values set: 0.7, 0.3
Searching Query: 136

Input top number of documents to display: 
6
***Top 6 documents***
Title: the contraction of satellite orbits under the influenceof air drag . pt .iv with scale height dependent onaltitude .
Rank 	 DocID 	
1    	 548  

Title: the contraction of satellite orbits under the influence of air dragpart i . with spherically symmetrical atmosphere .
Rank 	 DocID 	
2    	 613  

Title: the contraction of satellite orbits under the influence of air drag .
Rank 	 DocID 	
3    	 614  

Title: scale height in the upper atmosphere, derived from changes in satelliteorbits .
Rank 	 DocID 	
4    	 622  

Title: determination of upper-atmosphere air density and scale height fromsatellite observations .
Rank 	 DocID 	
5    	 616  

Title: the contraction of satellite orbits under the influence of air drag .part iii . high eccentricity orbits . /0.2 e 1/ .
Rank 	 DocID 	
6    	 615  

Do you want to continue (y/n):
n
