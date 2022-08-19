/*
 *  This program computes letter and word frequencies
    on a subset of documents in the Gutenberg corpus
    Author: Gabrielle Stein, Andrew Edwards, and Seymone Gugneja
    Date Created: 02 Jan 2021
    Date Last Modified: 03 Jan 2022
*/
package Ngrams;

// represents files and directory pathnames 
// in an abstract manner
import java.io.File;

// reads data from files as streams of characters
import java.io.FileReader;

// reads text efficiently from character-input
// stream buffers 
import java.io.BufferedReader;

// for writing data to files
import java.io.PrintWriter;

// signals that an input/output (I/O) exception 
// of some kind has occurred
import java.io.IOException;

// compiled representation of a regular expressions
import java.util.regex.Pattern;

import java.util.Comparator;

import java.util.Iterator;

import java.util.Map;

import java.util.TreeMap;
// matches a compiled regular expression with an input string
import java.util.regex.Matcher;

public class Ngrams {

	// no more than this many input files needs to be processed
	final static int MAX_NUMBER_OF_INPUT_FILES = 100;

	// an array to hold Gutenberg corpus file names
	static String[] inputFileNames = new String[MAX_NUMBER_OF_INPUT_FILES];

	static int fileCount = 0;

	// loads all files names in the directory subtree into an array
	// violates good programming practice by accessing a global variable
	// (inputFileNames)
	public static void listFilesInPath(final File path) {
		for (final File fileEntry : path.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesInPath(fileEntry);
			} else if (fileEntry.getName().endsWith((".txt"))) {
				inputFileNames[fileCount++] = fileEntry.getPath();
				// fileNameListWriter.println(fileEntry.getPath());
				// System.out.println(fileEntry.getName());
				// System.out.println(fileEntry.getAbsolutePath());
				// System.out.println(fileEntry.getCanonicalPath());
			}
		}
	}

	// returns index of a character in the alphabet
	// uses zero-based indexing
	public static int getLetterValue(char letter) {
		return (int) Character.toUpperCase(letter) - 65;
	}

	public static void main(String[] args) {
		
		// did the user provide correct number of command line arguments?
		// if not, print message and exit
		if (args.length != 6) {
			System.err.println("Number of command line arguments must be 3");
			System.err.println("You have given " + args.length + " command line arguments");
			System.err.println("Incorrect usage. Program terminated");
			System.err.println(
					"Correct usage: java Ngrams <path-to-input-files> <outfile-for-words> <outfile-for-char-counts>"
							+ "<output-for-unigram> <output-for-bigrams> <output-for-trigrams>");
			System.exit(1);
		}
		
		// extract input file name from command line arguments
		// this is the name of the file from the Gutenberg corpus
		String inputFileDirName = args[0];
		System.out.println("Input files directory path name is: " + inputFileDirName);

		// collects file names and write them to
		listFilesInPath(new File(inputFileDirName));

		// System.out.println("Number of Gutenberg corpus files: " + fileCount);

		// br for efficiently reading characters from an input stream
		BufferedReader br = null;

		// wdWriter for writing extracted words to an output file
		PrintWriter wdWriter = null;

		// ccWriter for writing characters and their occurrence
		// counts to an output file
		PrintWriter ccWriter = null;

		// will write unigrams to file
		PrintWriter uniWriter = null;

		// will write bigrams to file
		PrintWriter biWriter = null;

		// will write trigrams to file
		PrintWriter triWriter = null;

		// wordPattern specifies pattern for words using a regular expression
		Pattern wordPattern = Pattern.compile("[a-zA-Z]+");

		// wordMatcher finds words by spotting word word patterns with input
		Matcher wordMatcher;

		// a line read from file
		String line;

		// an extracted word from a line
		String word;

		// letter characters
		String alphabet = "abcdefghijklmnopqrstuvwxyz";

		// TreeMap that will store unigrams
		Map<String, Integer> uni = new TreeMap<String, Integer>();

		// TreeMap that will store bigram
		Map<String, Integer> bi = new TreeMap<String, Integer>();

		// TreeMap that will store trigram
		Map<String, Integer> tri = new TreeMap<String, Integer>();

		// open output file for writing words
		try {
			wdWriter = new PrintWriter(args[1], "UTF-8");
			System.out.println(args[1] + " successfully opened for writing words");
		} catch (IOException ex) {
			System.err.println("Unable to open " + args[1] + " for writing words");
			System.err.println("Program terminated\n");
			System.exit(1);
		}
		// array to keep track of character occurrence counts
		int[] charCountArray = new int[26];

		// initialize character counts
		for (int index = 0; index < charCountArray.length; index++) {
			charCountArray[index] = 0;
		}

		// process one file at a time
		for (int index = 0; index < fileCount; index++) {

			// open the input file, read one line at a time, extract words
			// in the line, extract characters in a word, write words and
			// character counts to disk files
			try {
				// get a BufferedReader object, which encapsulates
				// access to a (disk) file
				br = new BufferedReader(new FileReader(inputFileNames[index]));

				// String that will contain bigram
				String bigram = null;

				// String that will contain trigram
				String trigram = null;

				// as long as we have more lines to process, read a line
				// the following line is doing two things: makes an assignment
				// and serves as a boolean expression for while test
				while ((line = br.readLine()) != null) {
					// process the line by extracting words using the wordPattern
					wordMatcher = wordPattern.matcher(line);

					// process one word at a time
					while (wordMatcher.find()) {
						// extract the word
						word = line.substring(wordMatcher.start(), wordMatcher.end());
						// System.out.println(word);

						// // convert the word to lowercase, and write to word file
						word = word.toLowerCase();

						// Builds unigram
						int uniCount = uni.containsKey(word) ? uni.get(word) : 0;
						uni.put(word, uniCount + 1);

						// Builds bigram
						if (bigram != null) {
							int biCount = bi.containsKey(bigram + " " + word) ? bi.get(bigram + " " + word) : 0;
							bi.put(bigram + " " + word, biCount + 1);
							// Builds trigram
							if (trigram != null) {
								int triCount = tri.containsKey(trigram + " " + bigram + " " + word)
										? tri.get(trigram + " " + bigram + " " + word)
										: 0;
								tri.put(trigram + " " + bigram + " " + word, triCount + 1);
								trigram = bigram;
							} else {
								trigram = bigram;
							}
							bigram = word;
						} else {
							bigram = word;
						}

						wdWriter.println(word);
						// process characters in a word
						for (int i = 0; i < word.length(); i++) {
							// System.out.println("word.charAt(i) " + word.charAt(i));
							// System.out.println("alphabet.indexOf(word.charAt(i)) " +
							// alphabet.indexOf(word.charAt(i)));

							// if the character is a letter, increment the
							// corresponding count, otherwise discard the character
							if (Character.isLetter(word.charAt(i))) {
								charCountArray[alphabet.indexOf(word.charAt(i))]++;
							}
						} // for
					} // while - wordMatcher
				} // while - line
			} // try
			catch (IOException ex) {
				System.err.println("File " + inputFileNames[index] + " not found. Program terminated.\n");
				System.exit(1);
			}
		} // for -- process one file at a time

		// write letters and their counts to file named args[2]
		// open output file 2 for writing characters and their counts
		try {
			ccWriter = new PrintWriter(args[2], "UTF-8");
			System.out.println(args[2] + " successfully opened for writing character counts");
		} catch (IOException ex) {
			System.err.println("Unable to open " + args[2] + " for writing character counts");
			System.err.println("Program terminated\n");
			System.exit(1);
		}

		for (int index = 0; index < charCountArray.length; index++) {
			ccWriter.println(alphabet.charAt(index) + "\t\t" + charCountArray[index]);
		}

		// Unigram file
		try {
			uniWriter = new PrintWriter(args[3], "UTF-8");
			System.out.println(args[3] + " successfully opened for writing unigrams");
		} catch (IOException ex) {
			System.err.println("Unable to open " + args[3] + " for writing unigrams");
			System.err.println("Program terminated\n");
			System.exit(1);
		}

		for (Map.Entry<String, Integer> entry : uni.entrySet()) {
			uniWriter.println(entry.getKey() + "\t\t" + entry.getValue());
		}

		// Bigram file
		try {
			biWriter = new PrintWriter(args[4], "UTF-8");
			System.out.println(args[4] + " successfully opened for writing bigrams");
		} catch (IOException ex) {
			System.err.println("Unable to open " + args[4] + " for writing bigrams");
			System.err.println("Program terminated\n");
			System.exit(1);
		}

		for (Map.Entry<String, Integer> entry : bi.entrySet()) {
			biWriter.println(entry.getKey() + "\t\t" + entry.getValue());
		}

		// Trigram file
		try {
			triWriter = new PrintWriter(args[5], "UTF-8");
			System.out.println(args[5] + " successfully opened for writing trigrams");
		} catch (IOException ex) {
			System.err.println("Unable to open " + args[5] + " for writing trigrams");
			System.err.println("Program terminated\n");
			System.exit(1);
		}

		for (Map.Entry<String, Integer> entry : tri.entrySet()) {
			triWriter.println(entry.getKey() + "\t\t" + entry.getValue());
		}

		// Sorts all ngram TreeMaps by value instead of key
		Map<String, Integer> uniSorted = valueSort(uni);
		Map<String, Integer> biSorted = valueSort(bi);
		Map<String, Integer> triSorted = valueSort(tri);
		
		// Iterates through newly sorted maps
		Iterator<Map.Entry<String, Integer>> uniIter = uniSorted.entrySet().iterator();
		Iterator<Map.Entry<String, Integer>> biIter = biSorted.entrySet().iterator();
		Iterator<Map.Entry<String, Integer>> triIter = triSorted.entrySet().iterator();

		System.out.println("\n The 5 most common unigrams:");
		for (int i = 0; i < 5; i++) {
			if (uniIter.hasNext()) {
				Map.Entry<String, Integer> entry = uniIter.next();
				System.out.println(entry.getKey() + "\t\t" + entry.getValue());
			}
		}
		System.out.println("\nThe 5 most common bigrams:");
		for (int i = 0; i < 5; i++) {
			if (biIter.hasNext()) {
				Map.Entry<String, Integer> entry = biIter.next();
				System.out.println(entry.getKey() + "\t\t" + entry.getValue());
			}
		}
		System.out.println("\nThe 5 most common trigrams:");
		for (int i = 0; i < 5; i++) {
			if (triIter.hasNext()) {
				Map.Entry<String, Integer> entry = triIter.next();
				System.out.println(entry.getKey() + "\t\t" + entry.getValue());
			}
		}

		// close buffered reader. gives error
		// needs a try ... catch block
		try {
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		// close output file 1
		wdWriter.close();

		// close output file 2
		ccWriter.close();

		// close output file 3
		uniWriter.close();

		// close output file 4
		biWriter.close();

		// close output file 5
		triWriter.close();

	} // main()
	
	/**
	 * Allows TreeMaps to be sorted in descending order by value
	 * @param <K> String ngram
	 * @param <V> Integer ngram frequency
	 * @param map - TreeMap of Ngrams
	 * @return Map sorted by value
	 */
	public static <K, V extends Comparable<V>> Map<K, V> valueSort(final Map<K, V> map) {
		// Static Method with return type Map and
		// extending comparator class which compares values
		// associated with two keys
		Comparator<K> valueComparator = new Comparator<K>() {

			// return comparison results of values of
			// two keys
			public int compare(K k1, K k2) {
				int comp = map.get(k2).compareTo(map.get(k1));
				if (comp == 0)
					return 1;
				else
					return comp;
			}
		};
		// SortedMap created using the comparator
		Map<K, V> sorted = new TreeMap<K, V>(valueComparator);
		sorted.putAll(map);
		return sorted;
	}
}
