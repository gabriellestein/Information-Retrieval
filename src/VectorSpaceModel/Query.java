/**
 * Creates a query object from a file
 * @author - Gabrielle Stein & Andrew Edwards
 */
package programming_assg_03;

public class Query {
	// Query ID
	private int qID;

	// Query search term
	private String text;
	
	/**
	 * Default Constructor for the query
	 */
	public Query() {
		qID = 0;
		text = "";
	}
	
	/**
	 * Two parameter constructor
	 * @param qID - query ID
	 * @param text - query search terms
	 */
	public Query(int qID, String text) {
		this.qID = qID;
		this.text = text;
	}

	/**
	 * Returns query ID
	 * @return qID - query ID
	 */
	public int getqID() {
		return qID;
	}

	/**
	 * Sets query ID
	 * @param qID - query ID
	 */
	public void setqID(int qID) {
		this.qID = qID;
	}

	/**
	 * Returns query search terms
	 * @return text - query search terms
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets query search terms
	 * @param text - query search terms
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Returns a string representation of the query
	 */
	@Override
    public String toString() {
        return "Query ID: " + qID +
                ": " + text;
    }
	
}
