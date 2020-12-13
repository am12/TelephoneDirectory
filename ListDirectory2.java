import java.io.File;
import java.nio.file.*;
import java.util.*;

public class ListDirectory2 implements Directory2 {

	ListDirectory2(String filename){    //constructor that inputs the filename and runs the fileEntries method 
		FileEntries(filename);
	}
	public LinkedList<Entry> array = new LinkedList<Entry>();            //linked list used as data structure type 
	public ListIterator<Entry> iterateArray=array.listIterator();        //linked list iterator is used to input data in between nodes 

	private void FileEntries(String fileName) {                     //responsible for storing data from text file in alphabetical order in linked list 
		try {
			File inputFile = new File(fileName);                //used to access file 
			Scanner readFile = new Scanner(inputFile);           //used t read data from file 


			Path pathFile= Paths.get(fileName);           //used to count the number of lines in file 
			long numberLines=0;
			numberLines = Files.lines(pathFile).count();
			String[] eachLine =new String[(int) numberLines];
			
			for(int i=0; i<numberLines;i++) {      //used to store each line in array 
				eachLine[i] =readFile.nextLine();
			}
			readFile.close();
			Arrays.sort(eachLine);       //every line in array is sorted in alphabetical order 



			for(int i=0; i<numberLines;i++) {                    //each index in array of lines is split into three components: surname, initial,connection 
				String[] divideLine=eachLine[i].split("\t");
				String surname = divideLine[0];
				String initial=divideLine[1];
				int connection = Integer.parseInt(divideLine[2]);
				iterateArray.add(new Entry(surname, initial, connection));  //iterator is used to add each entry consisting of 3 components: surname, initial, connection 
			}
		}catch(Exception e) {                                          //if file is not found, following code will run 
			System.out.println("There was a error with file:"+e);
		}
	}
	@Override
	public void printDirectory() {      //responsible for printing the whole directory 
		System.out.println("*********************************************************\n"
				+ "                     List Directory 					 \n"
				+ "*********************************************************\n");

		for(Entry A : array) {   //loops through every Entry in linked list and prints out the surname, initial and connection
			System.out.println(A.getSurname()+"\t"+A.getInitial()+"\t"+A.getConnection());
		}

	}
	@Override
	public void NewEntry(String Surname, String initial, int connection) {     //responsible for adding a new entry to linked list in alphabetical order 
		Entry newEntry=new Entry(Surname, initial,connection);       // new entry object is created and values from parameters are appropiately inputted 
		if(String.valueOf(connection).length()==5) {         //ensures the connection length is 5 digits long
			for(int i=0;i<array.size();i++) {     // loops through the whole linked list 
				if(newEntry.getSurname().compareToIgnoreCase(array.get(i).getSurname())<0) {//if the new surname is smaller than the old surname, a new entry is inserted 
					array.add(i, newEntry); //new entry is inserted at the exact index the new surname is smaller then the old surname: ensures its alphabetical 
					break;
				}

			}
		}else {System.out.println("Error: Connection should be 5 Digits long");}  //if connection is not 5 digits long, this is printed 
	}

	@Override
	public String LookUp(String surname) {     //responsible for printing a given entry 
		int count =0;     //counts the number of times parameter surname didn't match with surname from array 
		for(int i=0; i<array.size();i++) {
			if(surname.compareToIgnoreCase(array.get(i).getSurname())==0){  //if surname matches, the surname, initial and connection at that specific index will be printed 
				return array.get(i).getSurname()+"\t"+array.get(i).getInitial()+"\t"+array.get(i).getConnection();
			}else {
				count++;    //if surname doesn't match, the count will be increasded by 1
			}
		}
		if(count==array.size()) {System.out.println("Error:Entry was not found");} //if count is same as the array length, means surname was not found and following code will run
		return surname;

	}
	@Override
	public void ChangeNumber(String surname, int connection) {  //responsible for changing a connection for given surname
		if(String.valueOf(connection).length()!=5) {System.out.println("Error:Connection length should be 5 digits long");} //ensure the connection is 5 digits long
		int count=0;  //counts the number of times parameter surname didn't match with surname from array 
		if(String.valueOf(connection).length()==5) {
			for(int i=0;i<array.size();i++) {   //loops through the whole linked list 
				if(surname.compareToIgnoreCase(array.get(i).getSurname())==0) { //is surname is found, connection is set 
					array.get(i).setConnection(connection);
				}else {
					count++;   //else, count is increased by 1 meaning the surname was not found
				}
			}
		}
		if(count==array.size()) {System.out.println("Error:Entry was not found");}  //if count is the same as array size,means the surname was not found anywhere in lineked list 

	}
	@Override
	public void DeleteEntry(String surname) {   //responsible for deleting specific entry 
		int count =0;  //counts the number of times surname was not found

		for(int i=0;i<array.size();i++) {   //loops through the whole linked list 
			if(surname.compareToIgnoreCase(array.get(i).getSurname())==0) {  //if linked list is found, entry is removed
				array.remove();
			}else {
				count++;  //if surname is not found, count increases by 1 
			}
		}
		if(count==array.size()) {System.out.println("Error: Entry was not found");}  //if count is same as the length of linked list, means surname is not found and followign code is printed
	}

}
