import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class GUI implements ActionListener{

	//below, i've made buttons for different numbers for 10 bit and given each of them a name 
	public JButton first;
	public JButton second;
	public JButton third;
	public JButton fourth;
	public JButton fifth;
	public JButton sixth;
	public JButton seventh;
	public JButton eighth;
	public JButton nineth;
	public JButton tenth;
	
	// this is a text field being made where the decimal version of the binary will be printed out in the GUI
	public JTextField digitalNum;
	
	//the labels for the buttons 
	public String z ="0";
	public String o ="1";
	
	//binary values 
	public int zero =0;
	public int one =1;   
	
	//total binary value 
	public static double binary=0.0;

	//this is a constructor that's called from MAIN method at the bottom
	public GUI() {

		//creates a frame
		JFrame frame = new JFrame("Binary to Digital Calculator");
		//sets frame size
		frame.setSize(1000, 120);
		
		//creates a vertical panel
		JPanel verticalLayout = new JPanel();
		
		//creates a horizontal panel
		JPanel horizontalLayoutBUTTONS=new JPanel();

		//creating layout for frame
		frame.setLayout(new BorderLayout());
		//creating a button that will be in NORTH section of the frame 
		frame.add(new JButton("Binary to Digital Calculator- Shayanne"), BorderLayout.NORTH);
		
		//creating a layout for vertical panel
		verticalLayout.setLayout(new BoxLayout(verticalLayout, BoxLayout.Y_AXIS));	
		//creating a layout for horizontal panel 
		horizontalLayoutBUTTONS.setLayout(new BoxLayout(horizontalLayoutBUTTONS, BoxLayout.X_AXIS));

		//making the buttons and giving them a label... the label z represents "0"
		first=new JButton(z);
		second=new JButton(z);
		third=new JButton(z);
		fourth=new JButton(z);
		fifth=new JButton(z);
		sixth=new JButton(z);
		seventh=new JButton(z);
		eighth=new JButton(z);
		nineth=new JButton(z);
		tenth=new JButton(z);
		digitalNum=new JTextField("0");

		//adding action listener to the buttons so user can interact with the buttons 
		first.addActionListener(this);
		second.addActionListener(this);
		third.addActionListener(this);
		fourth.addActionListener(this);
		fifth.addActionListener(this);
		sixth.addActionListener(this);
		seventh.addActionListener(this);
		eighth.addActionListener(this);
		nineth.addActionListener(this);
		tenth.addActionListener(this);

		//adding the buttons to the horizontal panel 
		horizontalLayoutBUTTONS.add(first);
		horizontalLayoutBUTTONS.add(second);
		horizontalLayoutBUTTONS.add(third);
		horizontalLayoutBUTTONS.add(fourth);
		horizontalLayoutBUTTONS.add(fifth);
		horizontalLayoutBUTTONS.add(sixth);
		horizontalLayoutBUTTONS.add(seventh);
		horizontalLayoutBUTTONS.add(eighth);
		horizontalLayoutBUTTONS.add(nineth);
		horizontalLayoutBUTTONS.add(tenth);
		horizontalLayoutBUTTONS.add(digitalNum);

		//adding the vertical panel to the centre of the frame 
		frame.add(verticalLayout,BorderLayout.CENTER);
		//adding the horizontal panel to the vertical panel
		verticalLayout.add(horizontalLayoutBUTTONS);

		//make the program stop running when X is pressed 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		//making the frame visible to user when program runs 
		frame.setVisible(true);

	}

	//this method is responsible for dealing with what happens when user interacts with the components with 
	//ActionListner
	@Override
	public void actionPerformed(ActionEvent e) {

		/*
		 * When button is clicked, the text of the button is changed to 1 or 0 depending on
		 * previous label and if its switched to 1, the binary amount is added to the binary total 
		 * however, if it switched from 1 to 0, the binary amount is subtracted from the total binary amount
		 */
		if(e.getSource() == first) {                       //this means if the user clicks on a button called first 
			if(first.getText()==z) {                       //if previous text is zero...
				first.setText(o);                          //text is set to 1
				binary = binary + (one * Math.pow(2, 9));  //relevant binary amount is added to total binary
			}else { 
				first.setText(z);                          //if previous text is 1...
				binary = binary -(one * Math.pow(2, 9));   //binary amount is subtracted from total binary amount
			}
		}

		if(e.getSource() == second) {
			if(second.getText()==z) {
				second.setText(o);
				binary =binary +(one * Math.pow(2, 8));
			}else {
				second.setText(z);
				binary = binary -(one * Math.pow(2, 8));
			}
		}

		if(e.getSource() == third) {
			if(third.getText()==z) {
				third.setText(o);
				binary = binary +(one * Math.pow(2, 7));
			}else {
				third.setText(z);
				binary =binary -(one * Math.pow(2, 7));
			}
		}
		if(e.getSource() == fourth) {
			if(fourth.getText()==z) {
				fourth.setText(o);
				binary =binary +(one * Math.pow(2, 6));
			}else {
				fourth.setText(z);
				binary =binary -(one * Math.pow(2, 6));
			}
		}
		if(e.getSource() == fifth) {
			if(fifth.getText()==z) {
				fifth.setText(o);
				binary =binary +(one * Math.pow(2, 5));
			}else {
				fifth.setText(z);
				binary =binary -(one * Math.pow(2, 5));
			}
		}

		if(e.getSource() == sixth) {
			if(sixth.getText()==z) {
				sixth.setText(o);
				binary =binary +(one * Math.pow(2, 4));
			}else {
				sixth.setText(z);
				binary =binary -(one * Math.pow(2, 4));
			}
		}

		if(e.getSource() == seventh) {
			if(seventh.getText()==z) {
				seventh.setText(o);
				binary =binary +(one * Math.pow(2, 3));
			}else {
				seventh.setText(z);
				binary =binary -(one * Math.pow(2, 3));
			}
		}
		if(e.getSource() == eighth) {
			if(eighth.getText()==z) {
				eighth.setText(o);
				binary =binary +(one * Math.pow(2, 2));
			}else {
				eighth.setText(z);
				binary =binary -(one * Math.pow(2, 2));
			}
		}
		if(e.getSource() == nineth) {
			if(nineth.getText()==z) {
				nineth.setText(o);
				binary =binary +(one * Math.pow(2, 1));
			}else {
				nineth.setText(z);
				binary =binary -(one * Math.pow(2, 1));
			}
		}

		if(e.getSource() == tenth) {
			if(tenth.getText()==z) {
				tenth.setText(o);
				binary =binary +(one * Math.pow(2, 0));
			}else {
				tenth.setText(z);
				binary =binary -(one * Math.pow(2, 0));
			}
		}
		//once relevant operations are done to the total binary amount, the textField called digialNum 
		//sets the digital version of the binary amount as its' text
		digitalNum.setText(Double.toString(binary));
	}
	
	//the MAIN method... when the run button is pressed, only the code in here runs 
	public static void main(String[] args) {
		//new instance of the GUI class is made and run 
		new GUI();
	}
}
