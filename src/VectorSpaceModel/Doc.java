/**
* Creates a document object from a file
* @author - Gabrielle Stein & Andrew Edwards
*/
package programming_assg_03;

public class Doc{

	// Document ID 
	private int docID;
	// Document name
	private String title;
	// Array of all authors
	private String authors;
	// Journal document is in
	private String journal;
	// Document abstract
	private String text;
	
	/**
	 * Default Constructor for the document
	 */
	public Doc() {
		this.docID = 0;
		this.title = "";
		this.authors = "";
		this.journal = "";
		this.text = "";
	}
	
	/**
	* Five parameter constructor
	* @param docID - document ID
	* @param journal - document journal
	* @param authors - list of authors
	* @param text - document abstract
	 */
	public Doc(int docID, String title, String authors, String journal, String text) {
		this.docID = docID;
		this.title = title;
		this.authors = authors;
		this.journal = journal;
		this.text = text;
	}
	
	/**
	* Returns document ID
	* @return docID
	*/
	public int getDocID() {
		return docID;
	}
	
	/**
	 * Sets document ID
	 * @param docID - document ID
	 */
	public void setId(int docID) {
        this.docID = docID;
    }
	
	/**
	* Returns document name
	* @return docName
	*/
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets document title
	 * @param title - document title
	 */
	public void setTitle(String title) {
        this.title = title;
    }
	
	/**
	* Returns authors
	* @return authors
	*/
	public String getAuthors() {
		return authors;
	}
	
	/**
	 * Sets document authors
	 * @param authors - document authors
	 */
	public void setAuthors(String authors) {
        this.authors = authors;
    }
	
	/**
	* Returns journal
	* @return jounral
	*/
	public String getJournal() {
		return journal;
	}
	
	/**
	 * Sets document journal
	 * @param journal - document journal
	 */
	public void setJournal(String journal) {
        this.journal = journal;
    }

	/**
	 * Returns document abstract
	 * @return text - document abstract
	 */	
	public String getText() {
		return text;
	}

	/**
	 * Sets document abstract
	 * @param text - document abstract
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Returns a string representation of the document
	 */
	@Override
    public String toString() {
        return "Document:" +
                "\nID: " + docID +
                "\nTitle: " + title + 
                "\nAuthors: " + authors + 
                "\nJournal: " + journal +
                "\nAbstract: " + text;
    }
}