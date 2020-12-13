import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;


public class HashDirectory implements Directory{

	HashDirectory(String filename)             //constructor used to input file name and run the file Entries method
	{
		FileEntries(filename);
	}

	public HashMap<Character,ArrayList<Entry>> hashMap = new HashMap<Character,ArrayList<Entry>>(); //hash map that stores a character as key and list of entries as value to key 

	private void FileEntries(String fileName) {
		try {
			FileReader inputFile = new FileReader(fileName);   //fileReader reads the files 
			Scanner readFile = new Scanner(inputFile);         //scanner also reads the file 


			Path pathFile= Paths.get(fileName);       //responsible for reading number of lines in file 
			long numberLines=0;
			numberLines = Files.lines(pathFile).count();
			String[] eachLine =new String[(int) numberLines];
			for(int i=0; i<numberLines;i++) {   //each line of file is stored in an array 
				eachLine[i] =readFile.nextLine();
			}
			Arrays.sort(eachLine);//array sorts each line in alphabetical order 
			readFile.close();

			/*
			 * The following code is responsible for storing the first letters of surnames in an array,sorting them in alphabetical order
			 * and storing them as key in the hash map.
			 */
			char[] letters=new char[(int) numberLines];          
			for(int s=0; s<numberLines;s++) {
				String[] divideLine=eachLine[s].split("\t");
				Character letter = divideLine[0].charAt(0);
				letters[s]=letter;
			}

			Arrays.sort(letters);
			int index=0;
			/*
			 * The following code is responsible for making sure non of the keys are repeated 
			 */
						
			for(int l=0;l<letters.length-1;l++) {
				for (int q=l+1;q<letters.length;q++) {

					if(letters[l]==letters[q]) {
						index=q;
						char[] copy=letters;         
						letters=new char[copy.length-1];

						for(int e=0;e<index;e++) {    
							letters[e]=copy[e];
						}                                          
						for(int u=index;u<letters.length;u++) {  
							letters[u]=copy[u+1];
						}
					}

				}
			}
			
				
			/*
			 * The following code stores the letters in alphabetical order in the hash map key 
			 */
			for(int s=0;s<letters.length;s++) {
				char h=letters[s];
				hashMap.put(h, null);
			}
	
			/*
			 * the following code divides each line of data from file into a surname, letter, initial and connection. 
			 * It then creates an Entry object and stores surname, initial and connection into the entry orbject.
			 * After that, it finds the correct key in the hash map and stores the entry in the array for that key
			 * in the hash map
			 */
			for(int i=0; i<numberLines;i++) {   
				String[] divideLine=eachLine[i].split("\t");
				Character lettero = divideLine[0].charAt(0);
				String surname = divideLine[0];
				String initial=divideLine[1];
				int connection = Integer.parseInt(divideLine[2]);
				Entry temp=new Entry(surname,initial,connection);	

				if(hashMap.containsKey(lettero)==true) {
					ArrayList<Entry> myArray=new ArrayList<Entry>();
					hashMap.put(lettero, myArray);
					hashMap.get(lettero).add(temp);
					
				}					
			}
/*
 * the following code is used for error handling 
 */
		}catch(IOException e) {
			System.out.println("There was an error with file: "+e);  //if file is not found, following code runs
		}

	}
	@Override
	public void printDirectory() {   //responsible for printing the whole directory 
		for(java.util.Map.Entry<Character, ArrayList<Entry>> a : hashMap.entrySet()) {   //runs through every key and its array list 
			System.out.println("\n\n---------------------------\n"   //prints the key
					+ "¦       KEY SET:"+a.getKey()+"         ¦\n"
					+ "---------------------------\n\n");

			List<Entry> B =a.getValue();    //goes through every entry in array list and prints it 
			for(Entry C : B) {
				System.out.println(C.getSurname()+"\t"+C.getInitial()+"\t"+C.getConnection());
			}
		}

	}

	@Override
	public void NewEntry(String Surname, String initial, int connection) {//adds new entry to the hashMap
		if(String.valueOf(connection).length()!=5) {System.out.println("Error: Connection length must be 5 digits long");} //ensures the connection is 5 digits long 
		Character letter = Surname.charAt(0); //returns the first alphabet of surname
		Entry temp = new Entry(Surname,initial,connection); //creates a new entry with given parameters
		System.out.println(1);
		if(String.valueOf(connection).length()==5) {
			System.out.println(2);
			if(hashMap.containsKey(letter)==true) {
				System.out.println(3);//if the keys of hash map contains the given key of letter, the array list value of that key is returned and the new entry is inserted into that array list 
				hashMap.get(letter).add(temp);	
			} else {
				ArrayList<Entry> let= new ArrayList<Entry>();
				let.add(temp);
				hashMap.put(letter, let);

			}
		}
	}


	

	@Override
	public void LookUp(String surname) {   //responsible for finding a given entry in array list 
		int count=0;   //counts the number times surname didn't match with the surnames in the hash map
		int counter=0; //counts the total number of entries in the hash map
		Character letter =surname.charAt(0);		
		/*
		 * The following code is responsible for locating the correct key that matches with this surname,
		 * getting the array connected to the key and then returning the entry stored in that array 
		 */
			for(Character key:hashMap.keySet()) {
				if(letter==key) {
					List<Entry> temp=hashMap.get(letter);
					for(Entry entry:temp) {
						if(surname.compareToIgnoreCase(entry.getSurname())==0) {
							System.out.println( entry.getSurname()+"\t"+entry.getInitial()+"\t"+entry.getConnection());
							break;
						}
						break;
					}
					break;
				}
				break;
			}
			
		/*
		 * The following code checks if the entry with the given name exists in the hash map or not. 
		 */
		
		for(java.util.Map.Entry<Character, ArrayList<Entry>> a : hashMap.entrySet()) {  //loops through every single key/value pair in hash map

			List<Entry> newEntry=a.getValue();                             //the array list in each value is returned 
			for(Entry C : newEntry) {             //loops through every entry in the array list 
				counter++;                 //counts the number of entries 
				if(surname.compareToIgnoreCase(C.getSurname())!=0) {   
					count++;  //if surname is not found, the count is increased by 1 
				}
			}	
		}
		if(count==counter) {System.out.println("Error: Entry does not exist");}//if the count is same as counter,means the entry is not found 
		
	}

	@Override
	public void ChangeNumber(String surname, int connection) {   //responsible for changing the connecition number 
		if(String.valueOf(connection).length()!=5) {System.out.println("Error: Connection length must be 5 digits long");}
		int count=0;//counts the number times surname didn't match with the surnames in the hash map
		int counter=0; //counts the total number of entries in the hash map
		for(java.util.Map.Entry<Character, ArrayList<Entry>> a : hashMap.entrySet()) {  //loops through every single key/value pair in hash map
			List<Entry> Bo=a.getValue();                             //the array list in each value is returned 
			for(Entry C : Bo) {             //loops through every entry in the array list 
				counter++;                 //counts the number of entries 
				if(surname.compareToIgnoreCase(C.getSurname())!=0) {   //if the surname matches, prints out the surname, initial , connection
					count++;  //if surname is not found, the count is increased by 1 
				}
			}	
		}
		if(count==counter) {System.out.println("Error: Entry does not exist");}
		Character letter=surname.charAt(0);	
		
		if(String.valueOf(connection).length()==5) {
			/*
			 * The following code is responsible for locating the correct key that matches with this surname,
			 * getting the array connected to the key and then returning the entry stored in that array 
			 */
			for(Character key:hashMap.keySet()) {
				if(letter==key) {
					List<Entry> temp=hashMap.get(letter);
					for(Entry entry:temp) {
						if(surname.compareToIgnoreCase(entry.getSurname())==0) {
							entry.setConnection(connection);
							break;
						}
						break;
					}
					break;
				}
				break;
			}

		} 
	}

	@Override
	public void DeleteEntry(String surname) {  //responsible for deleting a given entry 
		int count=0;//counts the number times surname didn't match with the surnames in the hash map
		int counter=0;//counts the total number of entries in the hash map
				
		for(java.util.Map.Entry<Character, ArrayList<Entry>> a : hashMap.entrySet()) {//loops through every single key/value pair in hash map
			List<Entry> B =a.getValue(); 
			for(int i=0;i<B.size();i++) {  //loops through the entire array in value 
				counter++;    //number of entries is counted 
				if(surname.compareToIgnoreCase(B.get(i).getSurname())==0) {  //if the surname matches, entry at the given index is removed
					B.remove(i);
				}else {
					count++;  //if surname does not match, count is increased by 1 
				}
			}
		}
		if(count==counter) {System.out.println("Error: Entry does not exist");}//if the number of times surname is not found same as the number of entries, means surname does not exist and following code runs 
	
	}
}
