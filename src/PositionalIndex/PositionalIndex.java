/**
* Positional index is essentially a 3D list of terms, with each term containing a list of documents
* and each document containing a list of positons that have that term
* @author - Gabrielle Stein & Andrew Edwards
*/
package PositionalIndex;

import java.util.*;


public final class PositionalIndex {
	// Positional Index: hash map with term as key and that term's list of documents as value
	private final HashMap<Term, ArrayList<Document>> positionalIndex = new HashMap<Term, ArrayList<Document>>();

	/**
	* Adds term to positional index
	* @param t - The given term
	*/
	public void addTerm(Term t) {
		positionalIndex.put(t, t.getDocuments());
	}

	/**
	* Checks is term is already in positional index
	* @param t - The given term
	* @return whether this term is already in the positional index
	*/
	public boolean containsKey(String t) {
		Term temp = new Term(t);
		return positionalIndex.containsKey(temp);
	}
	
	/**
	* Gets the document list for a given term
	* @param t - The given term
	* @return List of documents
	*/
	public ArrayList<Document> get(String t) {
		Term temp = new Term(t);
		return positionalIndex.get(temp);
	}

	/**
	* Checks to see is is two terms are within k words of each other by intersecting
	* the document and position lists for each term
	* @param p1 - Document list for term 1
	* @param p2 - Document list for term 2
	* @param k - Maximum distance between term 1 and term 2
	* @return List of documents that have term 1 within k words of term 2
	*/
	public ArrayList<Document> positionalIntersect(ArrayList<Document> p1, ArrayList<Document> p2, int k) {
		// Total documents that contain term 1 within k words of term 2
		ArrayList<Document> answer = new ArrayList<Document>();
		int j = 0;
		int i = 0;
		while (i != p1.size() && j != p2.size()) {
			if (p1.get(i).getDocID() == p2.get(j).getDocID()) {
				ArrayList<Integer> l = new ArrayList<Integer>();
				ArrayList<Integer> pp1 = p1.get(i).getPositions();
				ArrayList<Integer> pp2 = p2.get(j).getPositions();
				int ii = 0;
				int jj = 0;
				while (ii != pp1.size()) {
					while (jj != pp2.size()) {
						if (Math.abs(pp1.get(ii) - pp2.get(jj)) <= k) {
							l.add(pp2.get(jj));
						} else if (pp2.get(jj) > pp1.get(ii)) {
							break;
						}
						jj++;
					}
					while (!l.isEmpty() && Math.abs(l.get(0) - pp1.get(ii)) > k) {
						l.remove(0);
					}
					if (!l.isEmpty() && !answer.contains(p1.get(i))) {
						answer.add(p1.get(i));
					}
					ii++;
				}
				i++;
				j++;
			} else if (p1.get(i).getDocID() < p2.get(j).getDocID()) {
				i++;
			} else {
				j++;
			}
		}
		return answer;
	}
}
