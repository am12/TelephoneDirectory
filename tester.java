import java.io.IOException;


public class tester {


	public static void main(String[] args) throws IOException {
		String fileName ="C:\\Users\\auzan\\OneDrive\\Documents\\University\\Year 1\\2020 Year 1 Semester 2\\Java Workplace\\Semester 2 Coursework\\src\\Data";	
		
		HashDirectory X = new HashDirectory(fileName);
		X.printDirectory();
		
		
		
		
		
		
		
		
		
		
		
		
		//watchsArray(fileName,10000);   // 1000
		//watchslist(fileName, 10000);   // 1368  
		//watchsHash(fileName,10000);    // 1953
		
		
	}
		

		public static void watchsArray(String filename,int numberRepeats) {
			ArrayDirectory2 A= new ArrayDirectory2(filename);
			StopWatch watch =new StopWatch();
			long[] allTimes=new long[numberRepeats];
			long total=0;
			
			for(int i=0;i<numberRepeats;i++) {
				watch.start();
				A.NewEntry("Dob","BA",12345);
				A.LookUp("Dob");
				A.ChangeNumber("Dob", 54321);
				A.DeleteEntry("Dob");
				watch.stop();
				allTimes[i]=watch.getElapsedTime();
			}
			
			for(int j=0;j<numberRepeats;j++) {
				total=total+allTimes[j];
			}
			
			
			System.out.println(total/numberRepeats);
			allTimes=null;
		}
	
		
		
		
		
		
		
		public static void watchslist(String C,int numberRepeats) {
			ListDirectory2 A= new ListDirectory2(C);
			long[] allTimes=new long[numberRepeats];
			long total=0;
			StopWatch watch =new StopWatch();
			
			for(int i=0;i<numberRepeats;i++) {
				watch.start();
				A.NewEntry("Dob","BA",12345);
				A.LookUp("Dob");
				A.ChangeNumber("Dob", 54321);
				A.DeleteEntry("Dob");
				watch.stop();
				allTimes[i]=watch.getElapsedTime();
			}
			for(int j=0;j<numberRepeats;j++) {
				total=total+allTimes[j];
			}
			System.out.println(total/numberRepeats);
			allTimes=null;
		}
		
		
		
		
		public static void watchsHash(String C,int numberRepeats) throws IOException {
			HashDirectory2 A= new HashDirectory2(C);
			long[] allTimes=new long[numberRepeats];
			long total=0;
			StopWatch watch =new StopWatch();
			
			for(int i=0;i<numberRepeats;i++) {
				watch.start();
				A.NewEntry("Dob","BA",12345);
				A.LookUp("Dob");
				A.ChangeNumber("Dob", 54321);
				A.DeleteEntry("Dob");
				watch.stop();
				allTimes[i]=watch.getElapsedTime();
			}
			for(int j=0;j<numberRepeats;j++) {
				total=total+allTimes[j];
			}
			System.out.println(total/numberRepeats);
			allTimes=null;
		}




}

