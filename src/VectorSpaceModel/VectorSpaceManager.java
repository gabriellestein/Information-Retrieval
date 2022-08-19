/**
 * Manages the vector space model for given documents and queries.
 * @author - Gabrielle Stein & Andrew Edwards
 */
package programming_assg_03;

import java.io.*;
import java.util.*;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.*;
import org.apache.lucene.search.*;
import org.apache.lucene.search.similarities.*;
import org.apache.lucene.store.*;


public class VectorSpaceManager {

	// Format for fields in the document
	private final static String I = ".I";
	private final static String T = ".T";
	private final static String A = ".A";
	private final static String B = ".B";
	private final static String W = ".W";

	/**
	 * Loads documents from a given directory.
	 * @param filename - directory path
	 * @return LinkedList<Doc> - list of documents
	 * @throws IOException
	 */
	public LinkedList<Doc> loadDocs(String filename) throws IOException {
		// Creates a linkedList to hold all documents
		LinkedList<Doc> docs = new LinkedList<Doc>();
		Doc data = null;

		// Opens the file
		File file = new File(filename);

		// br for efficiently reading characters from an input stream
		BufferedReader br = null;

		// A line read from file
		String line;
		
		// Used to correctly append data to the correct fields
		boolean appendingTitle = false;
		boolean appendingText = false;
		
		try {
			// Get a BufferedReader object, which encapsulates
			// access to a (disk) file
			br = new BufferedReader(new FileReader(file));

			// As long as we have more lines to process, read a line
			while ((line = br.readLine()) != null) {

				// If the line is the beginning of a new document
				if (line.contains(I)) {
					// Reset the data object
	                if (appendingText) {
	                	appendingText = false;
	                }
	               
					// Create a new document
	                data = new Doc();
	                docs.add(data);
					
					// Set the document ID
	                data.setId(Integer.parseInt(line.replaceAll("[^0-9]", "")));
	            }
	            
				// If the line is the beginning of a new journal
	            if (line.contains(B)) {
	                data.setJournal(br.readLine());
	            }

	            // If the line is the beginning of a new title
	            if (line.contains(T)) {
	            	appendingTitle = true;
	                continue;
	            }
	            
				// If the line is the beginning of a new authors
	            if (appendingTitle) {
	                if (line.contains(A)) {
	                	appendingTitle = false;
	                    data.setAuthors(br.readLine());
	                } else {
	                    data.setTitle(data.getTitle() + line);
	                }
	            }
	            // Builds the abstract of the document
	            if (appendingText) {
	                data.setText(data.getText() + line + " ");
	            }
	            
				// If the line is the beginning of a new abstract
	            if (line.contains(W)) {
	            	appendingText = true;
	            }
			}
		}

		// If an exception occurs while reading the file
		catch (IOException e) {
			throw new IOException("File " + filename + " not found. Program terminated.\n");
		}
		// Closes the buffered reader
		br.close();
		
		// Return the list of documents		
		return docs;

	}

	/**
	 * Loads queries from a given directory.
	 * @param filename - directory path
	 * @return LinkedList<Query> - list of queries
	 * @throws IOException - if file is not found
	 */
	public LinkedList<Query> loadQueries(String filename) throws IOException{
		// Creates a linkedList to hold all queries
		LinkedList<Query> ques = new LinkedList<Query>();
		Query data = null;

		
		File file = new File(filename);
		
		// br for efficiently reading characters from an input stream
		BufferedReader br = null;
		
		
        // A line read from file
		String line;
		
		// Data needs to be appended
		boolean appending = false;
		
		try {
			// Get a BufferedReader object, which encapsulates
			// access to a (disk) file
			br = new BufferedReader(new FileReader(file));

			// As long as we have more lines to process, read a line
			while ((line = br.readLine()) != null) {

				//if it starts with .I then it's a new query
	            if (line.contains(I)) {
	            	
	            	// Reset appending due to new document
	                if (appending) {
	                	appending = false;
	                }
	                
	                // Creates a new Query and adds it to the LinkedList
	                data = new Query();
	                ques.add(data);
	                
	                // Gets the id
	                data.setqID(Integer.parseInt(line.replaceAll("[^0-9]", "")));
	                
	            }
	            
	            // Builds the text of the query
	            if (appending) {
	                data.setText(data.getText() + line.toLowerCase(Locale.ROOT));
	            }
	            
	            // Tells the program we reading the query and need to append it
	            if (line.contains(W)) {
	            	appending = true;
	            }
			}
		}
		
		// File is not found - throws exception
		catch (IOException e) {
			throw new IOException("File " + filename + " not found. Program terminated.\n");
		}
		// Closes the bufferred reader
		br.close();
		
		// Returns the list of queries
		return ques;
	}

	/**
	 * Finds documents for user-chosen query
	 * @param documents - list of Docs
	 * @param q - user chosen query 
	 * @param tBoost - user chosen title boost
	 * @param aBoost - user chose abstract boost
	 * @param kbd - keyboard input
	 * @throws IOException
	 * @throws ParseException
	 */
	public void ranking(LinkedList<Doc> documents, Query q, double tBoost, double aBoost, Scanner kbd) throws IOException, ParseException {
		System.out.println("Searching Query: " + q.getqID() + "\n");
		
		// Used to stem, lower case, and ignore stop words
		StandardAnalyzer analyzer = new StandardAnalyzer();
		
		// Used to calculate the similarity Score
		TFIDFSimilarity sim = new ClassicSimilarity();
		
		// Used to store index Files
		Directory index = new ByteBuffersDirectory();
		
		// Used to hold the configuration for IndexWriter
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		
		// Sets configuration for TF-IDF
		config.setSimilarity(sim);
		
		// Creates and maintains the index
		IndexWriter writer = new IndexWriter(index, config);
		
		// This will write the documents read into lucene documents with 
		// correct format, stemming, and none stop words
		for (Doc d : documents) {
			Document document = new Document();
			document.add(new TextField("title", d.getTitle(), Field.Store.YES));
			document.add(new TextField("text", d.getText(), Field.Store.YES));
			document.add(new StoredField("id", d.getDocID()));
			writer.addDocument(document);
		}
		// Makes sure that any buffered data is written to writer
		writer.flush(); 
		writer.close();
		
		// Create boost to the wrapped query for the title and the abstract
		BoostQuery titleQuery = new BoostQuery(
				new QueryParser("title", analyzer).parse(q.getText()), (float)tBoost);
		BoostQuery textQuery = new BoostQuery(
				new QueryParser("text", analyzer).parse(q.getText()), (float)aBoost);

		// Matches documents matching boolean combinations of title and abstract queries
		BooleanQuery booleanQuery = new BooleanQuery.Builder()
				.add(titleQuery, BooleanClause.Occur.SHOULD)
				.add(textQuery, BooleanClause.Occur.SHOULD)
				.build();
		
		// Gets how many documents the user wants to search for
		System.out.println("Input top number of documents to display: ");
		
		// Documents to display
		int k = Integer.parseInt(kbd.nextLine());
		
		// Reads indexes
		IndexReader reader = DirectoryReader.open(index);
		
		// Searches through the index
		IndexSearcher searcher = new IndexSearcher(reader);
		
		// Set the Similarity implementation used by this IndexSearcher
		searcher.setSimilarity(sim);
		
		// Searches for the top documents in the query
		TopDocs docs = searcher.search(booleanQuery, k);
		
		// Creates an array which ranks all the documents based on Cosine Similarity Score
		ScoreDoc[] ranking = docs.scoreDocs;
		System.out.println("***Top " + k + " documents***");
		
		// Prints the top documents
		for (int i = 0; i < ranking.length; ++i) {
			ScoreDoc scoreDoc = ranking[i];
			int docId = scoreDoc.doc;
			Document d = searcher.doc(docId);
			System.out.println("Title: " + d.get("title"));
            System.out.format("%-4s \t %5s \t\n", "Rank", "DocID");
            System.out.format("%-4d \t %-5s\n", i + 1, d.get("id"));
            System.out.println();
		}
	}

}
