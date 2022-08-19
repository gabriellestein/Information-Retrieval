/**
* Creates a term with the list of documents that contain the term
* @author - Gabrielle Stein & Andrew Edwards
*/
package PositionalIndex;

import java.util.*;

public class Term {
	// Stemmed term to be indexed
	private String term;
	// List of documents that contain this term
	private ArrayList<Document> documents;
	
	/**
	* Constructor for a term object
	* @param term - The given term
	*/
	public Term(String term) {
		this.term = term;
		documents = new ArrayList<Document>();
	}
	
	/**
	* Retrieve the term
	* @return term
	*/
	public String getTerm() {
		return term;
	}
	
	/**
	* Retrieve the documents for this term
	* @return List of documents
	*/
	public ArrayList<Document> getDocuments(){
		return documents;
	}
	
	/**
	* Adds a document to this term's list of documents
	* @param doc - The given document
	*/
	public void addDocument(Document doc) {
		documents.add(doc);
	}

	/**
	* Checks if two terms are equal
	* @param obj - other Term
	* @return boolean stating whether two terms are the same
	*/
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if(obj instanceof Term) {
			Term temp = (Term)obj;
			return this.term.equals(temp.term);
		}
		else
			return false;
	}
	
	/**
	* Makes sure each term has a unique has code that carries over to other
	* terms with the same given string
	* @return hashcode
	*/
	@Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + term.hashCode();  
	    return result;
	}
	
}
