public class Entry {
	
	private String surname;          
	private String initial;
	private int connection;

	public Entry(String Surname, String initial, int connection) {   //constructor that stores the surname, initials, connection for each entry 
		this.surname=Surname;
		this.initial=initial;
		this.connection=connection;
	}

	public String getSurname() {return surname;	}          //getters for private parameters surname, initial,connection 
	public String getInitial() {return initial;	}
	public int getConnection() {return connection;}

	public void setConnection(int connection) {this.connection=connection;}  //setter for connection as it could be changed 
}

