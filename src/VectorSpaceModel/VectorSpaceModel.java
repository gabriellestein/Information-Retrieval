/**
 * Reads a corpus and builds a positonal index for every term in the corpus.
 * Allows a user do a promximity search for two terms and returns all documents found in search. 
 * @author - Gabrielle Stein & Andrew Edwards
 */
package programming_assg_03;

import java.io.*;
import java.util.*;

import org.apache.lucene.queryparser.classic.ParseException;



public class VectorSpaceModel {

	public static void main(String[] args) throws IOException, ParseException  {

		Scanner kbd = new Scanner(System.in);
		// Get the corpus file name
		System.out.println("Please enter corpus file path: ");
		
		long start = 0;
		long end = 0;		
		// Creates a VectorSpaceManager object
		VectorSpaceManager VSM = new VectorSpaceManager();

		// Get file path to corpus
		String corpus = kbd.nextLine();
		System.out.println("Input files directory path name is: " + corpus);
		
		System.out.println("Beginning corpus indexing . . .");
		// Collects file names and write them to
		LinkedList<Doc> corpusDocs = null;
		try{
			start = System.currentTimeMillis();
			corpusDocs = VSM.loadDocs(corpus);
			end = System.currentTimeMillis();
		} catch (IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.out.println("Finished corpus indexing\n");
		System.out.println("Time taken to index corpus was "+(end-start)+"ms");
		// Get the query file path
		System.out.println("Please enter query file path: ");
		String query = kbd.nextLine();
		System.out.println("Query files directory path name is " + query);
		
		System.out.println("Beginning query indexing . . .");
		LinkedList<Query> fileQueries = null;
		try{
			start = System.currentTimeMillis();
			fileQueries = VSM.loadQueries(query);
			end = System.currentTimeMillis();
		} catch (IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.out.println("Finished query indexing\n");
		System.out.println("Time taken to index corpus was "+(end-start)+"ms");
		
		//map the query ids
		Map<Integer, Query> queryDataMap = new HashMap<Integer, Query>();
		Iterator<Query> iter = fileQueries.iterator();
		while(iter.hasNext()) {
			Query q = iter.next();
			queryDataMap.put(q.getqID(), q);
		}
		
		char choice;
		do{
			System.out.println();
			System.out.println("Enter a query id: ");
			int id = Integer.parseInt(kbd.nextLine().replaceAll("[^0-9]", ""));

			//check if the id exists in the map
			Query data = queryDataMap.get(id);
			if (data == null) {
				System.out.println("Query with this ID does not exist!");
			} else{
				System.out.println(data);
				System.out.println("Enter the title boost value: ");
				double tBoost = Double.parseDouble(kbd.nextLine());
				System.out.println("Enter the abstract boost value: ");
				double aBoost = Double.parseDouble(kbd.nextLine());
				System.out.println("Boost values set: " + tBoost + ", " + aBoost);
				VSM.ranking(corpusDocs, data, tBoost, aBoost, kbd);
			}
			System.out.println("Do you want to continue (y/n):");
			choice = kbd.nextLine().toUpperCase().charAt(0);
			System.out.println();

		} while(choice == 'Y');
		kbd.close();
	}
}