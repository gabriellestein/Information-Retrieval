/**
* Creates a document that contains a list of positions where this term is found
* @author - Gabrielle Stein & Andrew Edwards
*/
package programming_assg_02;

import java.util.*;

public class Document {
	// Document name
	private String docName;
	// Document ID (number in corpus)
	private int docID;
	// List of positions in document for current term
	private ArrayList<Integer> positions;
	
	/**
	* Two paramter constructor
	* @param docName - document name
	* @param docID - document ID
	 */
	public Document(String docName, int docID) {
		this.docName = docName.substring(docName.lastIndexOf("\\")+1);
		this.docID = docID;
		positions = new ArrayList<Integer>();
	}
	
	/**
	* Returns document name
	* @return docName
	*/
	public String getDocName() {
		return docName;
	}
	
	/**
	* Returns document ID
	* @return docID
	*/
	public int getDocID() {
		return docID;
	}
	
	/**
	* Retuns list of positions in current document for current term
	* @return positions
	*/
	public ArrayList<Integer> getPositions(){
		return positions;
	}
	
	/**
	* Adds a new position to positions list for current document for current term
	* @param pos - The position in this document of this term
	*/
	public void addPosition(int pos) {
		positions.add(pos);
	}
	
	/**
	* Checks if two documents are equal
	* @param obj - The document
	* @return boolean stating whether two documents are the same
	*/
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if(obj instanceof Document) {
			Document temp = (Document)obj;
			return this.docID == temp.docID;
		}
		else
			return false;
	}
}
