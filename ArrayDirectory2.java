import java.awt.Component;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ArrayDirectory2  implements Directory2 {

	ArrayDirectory2(String filename){         //constructor responsible for adding the filename and running the FileEntries method 
		FileEntries(filename);
	}
	public static Entry[] array;

	private void FileEntries(String fileName) {               //FileEntries method responsible for storing data from text file in alphabetical order in an array 
		try {
			File inputFile = new File(fileName);             //used to input file 
			Scanner readFile = new Scanner(inputFile);       //used to read data from file 

			Path pathFile= Paths.get(fileName);               //used to measure the number of lines in file 
			long numberLines=0;
			numberLines = Files.lines(pathFile).count();
			
			array = new Entry[(int) numberLines];               //each line is read and stored in an array and sorted in alphabetical order
			String[] eachLine =new String[(int) numberLines];
			for(int i=0; i<numberLines;i++) {
				eachLine[i] =readFile.nextLine();
			}
			readFile.close();
			Arrays.sort(eachLine);            //each line is sorted in alphabetical order 

			for(int i=0; i<numberLines;i++) {                          //code responsible for splitting every line in array into three components: surname, initial and connection
				String[] divideLine=eachLine[i].split("\t");
				String surname = divideLine[0];
				String initial=divideLine[1];
				int connection = Integer.parseInt(divideLine[2]);
				Entry singleentry = new Entry(surname, initial, connection);    //new entry is created from each line split into three diff components
				array[i]=singleentry;   //entry is stored in our array
			}
		}catch(Exception e) {                         //exception handling: if file not found, following code will run
			System.out.println("File was not found: "+e);
		}

	}
	@Override
	public void ChangeNumber(String surname, int connection) {        //responsible for changing connection for given surname 
		int count =0;        //counts the number of times parameter surname didn't match with surname from array 
		if(String.valueOf(connection).length()!=5){System.out.println("Error: Connection must be 5 digits long");};//if connection is not 5 digits long, following code will run   //ensure the connection number is only 5 digits long
			for(int i=0; i<array.length;i++) {  //loops through whole array
				if(surname.compareTo(array[i].getSurname()) == 0) {   //if surname matches, new connection is set 
					array[i].setConnection(connection);
				}else {
					count++; //otherwise count is increased by 1
				}
			}
		
			
		
		if(count==array.length) {System.out.println("Error: Entry does not exist");}  //if count is same as the array length, means surname was not found and following code will run
	}
	@Override
	public void printDirectory()  {    // responsible for printing the whole directory neatly 
		System.out.println("*********************************************************\n"
				+ "                 Array Directory 						 \n"
				+ "*********************************************************\n");
		for(int i=0;i<array.length;i++) {    //loops through the whole array and prints every single surname, initial and connection
			System.out.println(array[i].getSurname()+"\t"+array[i].getInitial()+"\t"+array[i].getConnection());
		}
	}


	@Override
	public String LookUp(String surname) {    //responsible for returning information about given surname
		int count=0; //counts the number of times parameter surname didn't match with surname from array 
		for(int x=0; x<array.length;x++) {
			if(surname.compareTo(array[x].getSurname()) != 0) {count++;}//otherwise count is increased by 1
		}
		if(count==array.length) {System.out.println("Error: Entry by "+surname+" does not exist.");} //if count is same as the array length, means surname was not found and following code will run
		
		for(int i=0; i<array.length;i++) {	 //loops through whole array and if surnames matches, it prints surname, initial and connection
			if(surname.compareTo(array[i].getSurname()) == 0) {
				return array[i].getSurname()+"\t"+array[i].getInitial()+"\t"+array[i].getConnection();
			}
		}
		return surname;
	}






	public void NewEntry(String Surname, String initial, int connection) {

		if(String.valueOf(connection).length()==5){            //ensure the connection length is 5 digits long 
			int index=0;     //index at which the new entry should be placed, based on alphabetical order 
			Entry A = new Entry(Surname,initial,connection);  //created new entry for input parameters
			for(int i=0;i<array.length;i++) {				//loops through whole array
				int X =A.getSurname().compareTo(array[i].getSurname());
				if(X<0) {   //if new surname is smaller than old surname, index number is set
					index=i;
					break;		
				}else {
					index = i+1;     //otherwise index is increased by 1 to got o the next index of array
				}
			}

			Entry[] copy=array;             //copy is created and array size is increase by 1
			array=new Entry[copy.length+1];

			for(int e=0;e<index;e++) {       //up to given index, all values are copied from only array to new array 
				array[e]=copy[e];
			}
			array[index]=A;          //new entry is inserted at given index 
			for(int u=index+1;u<array.length;u++) {   //rest of entries in indexes after the new entry are inserted from old array to new array 
				array[u]=copy[u-1];
			}
		}else {
			System.out.println("Error: Connection should be 5 digits");     //if connection length is not 5 digits, this is printed 
		}

	}
	@Override 
	public void DeleteEntry(String surname) {    //responsible for searching and deleting a given entry 
		int index=0;   //counts the index at which the given entry is located 
		int count=0; //counts the number of times parameter surname didn't match with surname from array 
		for(int i=0; i<array.length;i++) {     //loops through the whole array 
			if(surname.compareTo(array[i].getSurname()) == 0) {  //if the surnames matches, index is set and the loop breaks 
				index=i;
				break;	
			}else {
				count++;//otherwise count is increased by 1 to go to the next index 
			}
		}
		if(count ==array.length) {System.out.println("Error: Entry not found");}  //if count is same as the array length, means surname was not found and following code will run
		Entry[] copy=array;         //array length is reduced by 1 
		array=new Entry[copy.length-1];

		for(int e=0;e<index;e++) {    //all entries before the entry expected to be deleted are copied from old to new array
			array[e]=copy[e];
		}                                           //notice how no new entry is inserted
		for(int u=index;u<array.length;u++) {  //all entries after the entry expected to be deleted are copied from old to new array 
			array[u]=copy[u+1];
		}

	}

}














