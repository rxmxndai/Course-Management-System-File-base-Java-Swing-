

import java.awt.Color;
import java.awt.Image;
import javax.swing.*;



public class SignIn {
	
	// to get to admin dash
	Admin admin = new Admin();
	
	//--- to get to tutor dash
	TutorSpace tutorspace = new TutorSpace();
	
	
	//--to get student dash
	StudentSpace studentspace = new StudentSpace();
	
	//--to read tutor detais
	TutorFileManager tutormanager = new TutorFileManager();

	//--to read student details
	StudentFileManager studentmanager = new StudentFileManager();
	
	
	
	
	SignIn () {

	}
	
	
	
	private JPasswordField passwordText;
	private JTextField userText;
	
	private JFrame lframe;
	


	// -----------------------------------------------------------------------------SIGN IN GUI--------------------------------------------------------------------------------------------------------









	
	protected void signIn() {
		
		lframe = new JFrame();
		lframe.setSize(600, 350);
		lframe.setResizable(false);
		lframe.setTitle("HCK login");
		lframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x123456));
		panel.setOpaque(true);
		panel.setLayout(null);
		
		
		ImageIcon headerimg = null;
		
		try {
			headerimg  = new ImageIcon(getClass().getResource("/p/user.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Picture not found!  " + e, null, JOptionPane.INFORMATION_MESSAGE);
		}
        
		if (headerimg != null ) {
			JLabel p = new JLabel();
			p.setBounds(350, 50, 180, 170);
			Image scaled =  headerimg.getImage().getScaledInstance(p.getWidth(), p.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon sc = new ImageIcon(scaled);
			p.setIcon(sc);
			p.setOpaque(true);
			p.setBackground(Color.green);
			panel.add(p);
			lframe.setIconImage(headerimg.getImage());
		}
		
		
		
		
		JLabel userLabel = new JLabel("User Name");
		userLabel.setForeground(Color.white);
		userLabel.setBounds(50, 50, 80, 40);
		panel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(120, 50, 200, 40);
		panel.add(userText);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.white);
		passwordLabel.setBounds(50, 120, 80, 40);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField(20);
		passwordText.setBounds(120, 120, 200, 40);
		panel.add(passwordText);
		
		JButton button = new JButton("Login");
		button.setBounds(120, 180, 80, 40);
		button.setBackground(Color.cyan);
		button.addActionListener(e->validate());
		button.setFocusable(false);
		panel.add(button);
		
		JButton button1 = new JButton("Exit");
		button1.setBounds(240, 180, 80, 40);
		button1.setBackground(Color.cyan);
		button1.addActionListener(e->System.exit(0));
		button1.setFocusable(false);
		panel.add(button1);

		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		lframe.add(panel);
		lframe.setVisible(true);
		
	}
	
	
	
	
	// -----------------------------------VALIDATING IF STUDENT, TUTOR, AND ADMIN ---------------------------------------------------------------------------------------------------------

	private void validate(){
		
		studentmanager.readStudentFile();
		tutormanager.readTutorFile();
		
		
		String userinputUserID = userText.getText().toUpperCase();
		char[] getPass = passwordText.getPassword();
        String userinputPassword = String.valueOf(getPass);

		Boolean flag = true;
	


		while (flag) {
			
			
			
			if(studentmanager.studentnamearr.contains(userinputUserID) && studentmanager.studentpasswordarr.contains(userinputPassword)){
				for (int i=0; i< studentmanager.studentnamearr.size(); i++) {				
					if (studentmanager.studentnamearr.get(i).equalsIgnoreCase(userinputUserID) && studentmanager.studentpasswordarr.get(i).equals(userinputPassword) )  {
						lframe.dispose();
						studentspace.studentDash(userinputUserID);
						flag = false;
					}
				}
			}
			
			 

			else if(tutormanager.tutornamearr.contains(userinputUserID) && tutormanager.tutorpasswordarr.contains(userinputPassword)){
				for (int i=0; i< tutormanager.tutornamearr.size(); i++) {
					if (tutormanager.tutornamearr.get(i).equalsIgnoreCase(userinputUserID) && tutormanager.tutorpasswordarr.get(i).equals(userinputPassword) )  {
						
						lframe.dispose();
						tutorspace.TutorDash(userinputUserID);
						flag = false;
					}
				}
			}
			
		
			else if (admin.getName().equalsIgnoreCase(userinputUserID) && admin.getPassword().equals(userinputPassword)){
				lframe.dispose();
				admin.adminDash();
				flag = false;

			}

			else{
				JOptionPane.showMessageDialog(null, "Username / password do not match", "Credentials mismatch!", JOptionPane.INFORMATION_MESSAGE);
                break;
			}
		}
	}
	
	
	
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public static void main(String[] args) {
		SignIn s = new SignIn();
		s.signIn();
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
}
