import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Admin {
	
	    
	
	Admin () {
		
	} 
	 
	
	AdminControl admincontroller = new AdminControl();
	
	
	private String name = "admin";
	private String password = "admin";
 

	//------------------------------------------ADMIN DASHBOARD--------------------------------------------------------------//
	
	protected void adminDash() {
		
		
	
		
		JFrame adminmenuframe = new JFrame();
		adminmenuframe.setVisible(true);
		adminmenuframe.setSize(800, 700);
		adminmenuframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		adminmenuframe.setTitle("Admin dashboard");
		adminmenuframe.setResizable(false);
		
		JPanel panel = new JPanel(); 
		panel.setBackground(new Color(0x123456));
		panel.setLayout(null);
		
		
		
		
		// ADMIN IMAGE ON DASH
		//------------------------------IMAGE------------------------------------------//
		ImageIcon headerimg = null;
		
		try {
			headerimg  = new ImageIcon(getClass().getResource("/p/admin.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Picture not found!  " + e, null, JOptionPane.INFORMATION_MESSAGE);
		}
        
		if (headerimg != null ) {
			JLabel p = new JLabel();
			p.setBounds(550, 20, 200, 200);
			Image scaled =  headerimg.getImage().getScaledInstance(p.getWidth(), p.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon sc = new ImageIcon(scaled);
			p.setIcon(sc);
			p.setOpaque(true);
			p.setBackground(Color.green);
			panel.add(p);
			adminmenuframe.setIconImage(headerimg.getImage());
		}
		//------------------------------------------------------------------------//
		
		JButton coursebutton = new JButton("View Courses");
		coursebutton.setFocusable(false);
		coursebutton.setBounds(20, 20, 200, 70);
		coursebutton.addActionListener(e -> {
			admincontroller.viewCourse();
		});
		panel.add(coursebutton);
		
		JButton managecoursebutton = new JButton("Courses Management");
		managecoursebutton.setFocusable(false);
		managecoursebutton.setBounds(300, 20, 200, 70);
		managecoursebutton.addActionListener(e -> {
			
			admincontroller.courseMenu();
		}); 
		panel.add(managecoursebutton);
		
		JButton modulebutton = new JButton("View Module");
		modulebutton.setFocusable(false);
		modulebutton.setBounds(20, 120, 200, 70);
		modulebutton.addActionListener(e -> admincontroller.viewModule());
		panel.add(modulebutton);
		 
		JButton managemodulebutton = new JButton("Module Management");
		managemodulebutton.setFocusable(false);
		managemodulebutton.setBounds(300, 120, 200, 70);
		managemodulebutton.addActionListener(e -> admincontroller.moduleMenu());
		panel.add(managemodulebutton);
		
		JButton tutorbutton = new JButton("View Tutor");
		tutorbutton.setFocusable(false);
		tutorbutton.setBounds(20, 220, 200, 70);
		tutorbutton.addActionListener(e->admincontroller.viewTutor());
		panel.add(tutorbutton);
		
		JButton managetutorbutton = new JButton("Tutor Management");
		managetutorbutton.setFocusable(false);
		managetutorbutton.setBounds(300, 220, 200, 70);
		managetutorbutton.addActionListener(e->admincontroller.tutorMenu());
		panel.add(managetutorbutton);
		 
		JButton studentbutton = new JButton("View Student"); 
		studentbutton.setFocusable(false);
		studentbutton.setBounds(20, 320, 200, 70);
		studentbutton.addActionListener(e->admincontroller.viewStudent());
		panel.add(studentbutton);
		
		JButton managestudentbutton = new JButton("Students Management");
		managestudentbutton.setFocusable(false);
		managestudentbutton.setBounds(300,320, 200, 70);
		managestudentbutton.addActionListener(e->admincontroller.studentMenu());
		panel.add(managestudentbutton);
		
		
		
		JButton viewresultbutton = new JButton("View Individual Result");
		viewresultbutton.setFocusable(false);
		viewresultbutton.setBounds(20, 420, 200, 70);
		viewresultbutton.addActionListener(e->admincontroller.viewIndividualResult());                
		panel.add(viewresultbutton);
		
		
		JButton var = new JButton();
		var = new JButton("View All students Result");
		var.setFocusable(false);
		var.setBounds(300, 420, 200, 70);
		var.addActionListener(e->admincontroller.viewAllResults());                
		panel.add(var);
		
		
		JButton signoutbutton = new JButton("Sign out");
		signoutbutton.setFocusable(false);
		signoutbutton.setBackground(Color.cyan);
		signoutbutton.setBounds(630, 440, 120, 50);
		signoutbutton.addActionListener(e -> {
			adminmenuframe.dispose();
			new SignIn();
		});
		panel.add(signoutbutton);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		
		adminmenuframe.add(panel);
		adminmenuframe.setVisible(true);
		
	}
	

	
	
	//-------------------------------GETTER ------------------------------------------------------------------------------------------//
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	
	public static void main(String[] args) {
		Admin a = new Admin();
		a.adminDash();
	}


	
}