package JY_hw6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Anagrams {
  /**
   * The constant primes should be initialized to an array consisting of the first 26 prime numbers
   */
  final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
  Map<Character,Integer> letterTable;
  Map<Long,ArrayList<String>> anagramTable;

  /**
   * constructor initializes anagramTable.
   */
  public Anagrams() {
    buildLetterTable();
    anagramTable = new HashMap<>();
  }

  /**
   * This method should be invoked by the constructor for the class Anagrams and should build
   * the hash table letterTable.
   * This table is to be used in myHashCode, described next, for constructing the hash code of strings.
   */
  private void buildLetterTable() {
    letterTable = new Hashtable<Character, Integer>();
    Character[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    for(int i = 0; i < 26; i++) {

      letterTable.put(letters[i],primes[i]);
    }
  }

  /**
   * This method should compute the hash code of the string s passed as argument, and should add this word to the hash table anagramTable.
   */
  private void addWord(String s) {
    long hashS = myHashCode(s);
    if(!anagramTable.containsKey(hashS)) {
      ArrayList<String> tempList = new ArrayList<String>();
      tempList.add(s);
      anagramTable.put(hashS,tempList);
    }else {
      anagramTable.get(hashS).add(s);
    }
  }

  /**
   *  A requirement for myHashCode is that all the anagrams of a word should receive the same hash code.
   */
  private Long myHashCode(String s) {
    long stringHash = 1;
    char[] charArr = s.toCharArray();
    for(int i = 0; i < charArr.length; i++){
      stringHash = stringHash * letterTable.get(charArr[i]);
    }
    return stringHash;
  }

  /**
   * The main method is processFile which receives the name of a text file containing words, one
   * per line, and builds the hash table anagramTable.
   */
  private void processFile (String s) throws IOException {
    FileInputStream fstream = new FileInputStream(s);
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
    String strLine;
    while ((strLine = br.readLine()) != null) {
      this.addWord(strLine);
    }
    br.close();
  }

  /**
   * This method should return the entries in the anagramTable that have the largest number of  anagrams.
   * Gets the key with the most anagrams
   */
  private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
    int max = 0;
    ArrayList<Map.Entry<Long,ArrayList<String>>> maxList = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
    for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
      if(entry.getValue().size() > max) {
        maxList.clear();
        maxList.add(entry);
        max = entry.getValue().size();
      }else if(entry.getValue().size() < max) {
        continue;
      }else {
        maxList.add(entry);
      }
    }
    return maxList;
  }

  /**
   * The main method will read all the strings in a file, place them in the hash table of anagrams and then iterate over the hash table to report which words have the largest number of anagrams.
   */
  public static void main (String[] args) {
    Anagrams a = new Anagrams();
    final long startTime = System.nanoTime();
    try {
      a.processFile("words_alpha.txt");
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
    final long estimatedTime = System.nanoTime() - startTime;
    final double seconds = ((double) estimatedTime / 1000000000);
    long key = 0;
    ArrayList<String> listMaxAnagrams = new ArrayList<>();
    for (Map.Entry<Long, ArrayList<String>> entry : maxEntries) {
      key = entry.getKey();
      listMaxAnagrams = entry.getValue();
    }
    System.out.println("Elapsed Time: " + seconds);
    System.out.println("Key of max anagrams: " + key);
    System.out.println("List of max anagrams: " + maxEntries);
    System.out.println("Length of list of max anagrams: " + listMaxAnagrams.size());
  }

}
