import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AdminControl {
	
	
	private String studentID, moduleID, studentLevel, studentName, studentSemester;

	
	Course course = new Course();
	CourseManager coursemanager = new CourseManager();
	
	
	Module module = new Module();
	ModuleManager modulemanager = new ModuleManager();
	
	
	Tutor tutor = new Tutor(); 
	
	
	Student student = new Student(); 
	StudentFileManager studentmanager = new StudentFileManager();


	protected JScrollPane scroll;
	protected JPanel panel;
	protected JLabel t;
	protected JFrame spframe;
	protected JTextArea vmtext, op, topp;
	
	     
	// ---View Course in Course Class-----
	protected void viewCourse() {
		course.viewCourse();
	}
	
	// ---module menu in module file-----
	protected void moduleMenu() { 
		module.moduleMenu();
	}
	
	// ---Course Menu in course file-----
	protected void courseMenu() {
		course.courseMenu();
	}

	// ---View MODULE IN module file-----
	protected void viewModule() {
		module.viewModule(); 
	}
	
	// ---TUTOR LIST GUI-----
	protected void viewTutor() {
		tutor.viewTutor();
	}
	
	// ---STUDENTS LIST GUI-----
	protected void viewStudent() {
		student.viewStudents();
	}

	// ---TUTOR MENU GUI-----
	protected void tutorMenu() {
		tutor.tutorMenu();
	}
	
	// --- STUDENT MENU GUI-----
	protected void studentMenu() {
		student.studentMenu();
	}
	
	 
	

	
	//--------------------------------------------GET INDIVIDUAL STUDENTS RESULT GUI------------------------------------------------------------------------------------
	 
	

	public void viewIndividualResult() {
		
		coursemanager.readCourseFile();
		
		modulemanager.readModuleFile();
		
		studentmanager.readMarksFile();
		studentmanager.readStudentFile();
		
		spframe = new JFrame();
		spframe .setSize(1200, 900);
		spframe .setTitle("Students Grade Remarks dash");
		spframe .setBackground(Color.green);
		spframe .setResizable(false);
		spframe .setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(new Color(0x123456));
		panel.setLayout(null);
		
		
		JLabel sid = new JLabel("Student ID             :");
		sid.setBounds(20, 30, 150, 40);
		sid.setForeground(Color.white);
		
		 
		JLabel slevel = new JLabel("Student Level       :");
		slevel.setBounds(20, 90, 150, 40);
		slevel.setForeground(Color.white);
		
		JLabel semseterr = new JLabel("Semester              :");
		semseterr.setBounds(20, 150, 150, 40);
		semseterr.setForeground(Color.white);
		panel.add(semseterr);
		
		
		JTextField sleveltext = new JTextField();
		sleveltext.setEditable(false);
		sleveltext.setBounds(200, 90, 200, 40);
		panel.add(sleveltext);
		
		
		
		// studentID combo box
		ArrayList<String> semester1d = new ArrayList<String>();
		JComboBox<String> semcombo = new JComboBox<String>();
		semcombo.setBounds(200, 150, 200, 40);
		for (int o=0; o< studentmanager.studentsemarr.size(); o++) {
			if (!semester1d.contains(studentmanager.studentsemarr.get(o)) ) {
				semester1d.add(studentmanager.studentsemarr.get(o));
				semcombo.addItem(studentmanager.studentsemarr.get(o));
			} 
		}
		panel.add(semcombo);
		
		
		// studentID combo box
		ArrayList<String> ed = new ArrayList<String>();
		JComboBox<String> studentidcombobox = new JComboBox<String>();
		studentidcombobox.setBounds(200, 30, 200, 40);
		for (int o=0; o< studentmanager.studentidarr.size(); o++) {
			if (!ed.contains(studentmanager.studentidarr.get(o)) ) {
				ed.add(studentmanager.studentidarr.get(o));
				studentidcombobox.addItem(studentmanager.studentidarr.get(o));
			} 
		}
		studentidcombobox.addActionListener(e -> {
			for (int i=0; i< studentmanager.studentidarr.size(); i++) {
				if (studentmanager.studentidarr.get(i).equals((String)studentidcombobox.getSelectedItem())) {
					sleveltext.setText(studentmanager.studentlevelarr.get(i));
				}
			}
		});

		panel.add(studentidcombobox);
		


		
		JButton getinfo = new JButton("Get 	Result");
		getinfo.setBounds(450, 150, 120, 40);
		getinfo.setBackground(Color.cyan);
		getinfo.setFocusable(false);
		getinfo.addActionListener(e -> {
			String id = (String) studentidcombobox.getSelectedItem();
			String level = sleveltext.getText();
			String  semmm = (String) semcombo.getSelectedItem();
			getResults(id, level, semmm);
		});	

		JButton sinfoback = new JButton("Go back");
		sinfoback.setBounds(20, 750, 90, 40);
		sinfoback.setFocusable(false);
		sinfoback.addActionListener(e->spframe .dispose());
		
		op = new JTextArea();
		op.setEditable(false);
		
		vmtext = new JTextArea();
		topp = new JTextArea();
		
		panel.add(topp);
		panel.add(op);
		panel.add(sid);
		panel.add(getinfo);
		panel.add(slevel);
		panel.add(sleveltext);
		panel.add(sinfoback);
		panel.add(vmtext);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		spframe .add(panel);
		spframe .setVisible(true);
	}
	
	
	//------------------------------------------INDIVIDUAL RESULT OF STUDENT ---------------------------------------------------------------------------------------
	
	
	
	/* Checks student marked or not
	 * validates level and id of student
	 * then displays moduleID, marks, grade and remarks*/
	
	public void getResults(String id, String level, String semm) {
		
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		
		
		this.studentID = id;
		this.studentLevel = level;
		this.studentSemester = semm;
		
		
		topp.removeAll();
		op.removeAll();
		vmtext.removeAll();
		
		
		
		int flag = 0;
		
		
		while (flag == 0) {
			
			// if any of the text field is empty
			if (studentID.isEmpty() || studentLevel.isEmpty() || studentSemester.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Fill up the student info textfield" );
				flag =1;
			}
			
			
			else if (!studentmanager.markssid.contains(studentID)) {
				JOptionPane.showMessageDialog(null, "Student " + studentID + " not marked yet in Semester " + studentSemester);
				
				flag =1;
			}
			
			
			else if (studentmanager.markssid.contains(studentID) && studentmanager.markssl.contains(studentLevel) && flag==0) {
				String info = "";
				
				for (int  i=0; i< studentmanager.markssid.size(); i++ ) {

					
					if (studentmanager.markssid.get(i).equals(studentID) && studentmanager.markssl.get(i).equalsIgnoreCase(studentLevel) && studentmanager.marksssem.get(i).equalsIgnoreCase(studentSemester)
							) {
						
						info = info.concat(
								
								studentmanager.markssmid.get(i) + "\t "+
										studentmanager.markssl.get(i) +"\t " +
										studentmanager.markssmarks.get(i) + "\t " + 	
										studentmanager.markssgrade.get(i) +"\t " +
										studentmanager.markssremarks.get(i) +"\n " 
							);
						
					}

				} 
			
				
				//------------------------ getting student name from student ID ------------------------------------------------
				for (int  i=0; i< studentmanager.studentidarr.size(); i++ ) { 
					if (studentmanager.studentidarr.get(i).equals(studentID)) {
						this.studentName = studentmanager.studentnamearr.get(i);
					}
				}
				
				
				Font font1 = new Font("Segoe UI Semibold", Font.PLAIN, 20);
				String jk = null;
				jk = "   ID        :   " + studentID +  "\t\t    Level          :   " +    studentLevel  + "\n\n   Name  :     " + studentName  + "\t\t    Semester    :   " + 
							studentSemester;
				
							
				topp.setEditable(false);
				topp.setText(jk);
				topp.setFont(font1);
				topp.setBounds(20, 210, 1150, 90);
				topp.setBackground(new Color(127,255,212));
				panel.add(topp);
				
				
				
				String boom = "********************************************** RESULT***************************************************************************" + "\n" 
						+ "---------------------------------------------------------------------------------------------------------------------------------\n"
						+" Module\t Level\t Marks\t Grade\t Remarks\n"
				 + "----------------------------------------------------------------------------------------------------------------------------------\n";
				Font font = new Font("Segoe UI Semibold", Font.PLAIN, 15);
				
				op.setText(boom);
				op.setFont(font);
				op.setBackground(new Color(127,255,212));
				op.setBounds(20, 310, 1150, 90);
				panel.add(op);
				
				
				Font font2 = new Font("Segoe UI Semibold", Font.PLAIN, 15);
				vmtext.setText(info);
				vmtext.setFont(font2);
				vmtext.setBackground(new Color(127,255,212));
				vmtext.setBounds(20, 400, 1150, 150);
				vmtext.setEditable(false);
				panel.add(vmtext);
				
				
				spframe.add(panel);
				
				flag =1;
	
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Student " + studentID + " and Level do not match");
				flag =1;
			}
		}
	}
	
	
	

	
	//------------------------------View result of all students of a particular module------------------------------------------------
	
	
	
	
	/* Asks for module ID in GUI */
	
	protected void viewAllResults() {
		
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		
		
		
		spframe = new JFrame();
		spframe.setSize(1200, 700);
		spframe.setTitle("Students Grade Remarks dash");
		spframe.setBackground(Color.green);
		spframe.setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(new Color(0x123456));
		panel.setLayout(null);
		

		JLabel sid = new JLabel("Module ID:");
		sid.setBounds(20, 30, 150, 40);
		sid.setForeground(Color.white);
		
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> sd = new JComboBox<String>();
		sd.setBounds(200, 30, 200, 40);
		panel.add(sd);
		for (int o=0; o < modulemanager.moduleidarr.size(); o++) {
			if (!elements.contains(modulemanager.moduleidarr.get(o)) ) {
				elements.add(modulemanager.moduleidarr.get(o));
				sd.addItem(modulemanager.moduleidarr.get(o));
			} 
		}
		
		

		JButton getinfo = new JButton("Get 	Result");
		getinfo.setBounds(450, 30, 120, 40);
		getinfo.setBackground(Color.cyan);
		getinfo.setFocusable(false);
		getinfo.addActionListener(e -> {
			String id = (String) sd.getSelectedItem();
			
			t.removeAll();
			op.removeAll();
			vmtext.removeAll();
			panel.remove(scroll);
			getResults(id);
		});	
		
		JButton sinfoback = new JButton("Go back");
		sinfoback.setBounds(20, 600, 90, 40);
		sinfoback.setBackground(Color.cyan);
		sinfoback.setFocusable(false);
		sinfoback.addActionListener(e-> {
			
			spframe.dispose(); 
		});
		
		

		op = new JTextArea();
		vmtext = new JTextArea();
		scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		t= new JLabel();
		
		
		panel.add(t);
		panel.add(sid);
		panel.add(getinfo);
		panel.add(sinfoback);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		spframe.add(panel);
		spframe.setVisible(true);
	}
	


	
	
	
	
	//----------------------------------------Displays result of all students of a certain mopdule------------------------------------------------------------------------------------------------------
	
	/* Checks if student of that module is graded or not
	 * If found displays result of those graded students*/
	
	public void getResults(String id) {
		
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		
		this.moduleID =null;
		this.moduleID = id;
		
		
		
		String mname = "";
		String info = "";
		
		
		int flag =0;
		while (flag == 0) {
			
			if (!studentmanager.markssmid.contains(id)) {
				JOptionPane.showMessageDialog(null, "This module has no student graded yet." );
				flag =1;
				break;
			}
			else if (moduleID.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Fill up the student info textfield" );
				flag =1;
				break;
			}
			
			
			
			else {

					
				for (int  i=0; i< studentmanager.markssid.size(); i++ ) {
					
					if (studentmanager.markssmid.get(i).equalsIgnoreCase(moduleID)) {
							
						this.studentSemester =studentmanager.marksssem.get(i);
						mname = studentmanager.markssmname.get(i);
						info = info.concat(
								studentmanager.markssname.get(i)+ "\t " +
								studentmanager.markssid.get(i) +"\t " +
								studentmanager.markssmid.get(i) + "\t " +
								studentmanager.markssmarks.get(i) + "\t " +
								studentmanager.markssgrade.get(i) +"\t " +
								studentmanager.markssremarks.get(i) +"\n " 
							);
						
					}
					
				}
					
				Font font1 = new Font("Segoe UI Semibold", Font.PLAIN, 20);
				t.setText(mname);
				t.setFont(font1);
				t.setBounds(600, 30, 600, 40);
				t.setForeground(Color.white);
				t.setBackground(new Color(127,255,212));
				panel.add(t);
					
					
				String boom = "**********************************************      Semester " + studentSemester       + " RESULT        ********************************************************" + "\n" 
						+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\n"
						+" Name\t\t ID\t Module ID\t Marks\t Grade\t Remarks\n"
					+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\n";
				Font font = new Font("Segoe UI Semibold", Font.PLAIN, 15);
				op = new JTextArea();
				op.setEditable(false);
				op.setText(boom);
				op.setBackground(new Color(127,255,212));
				op.setFont(font);
				op.setBounds(10, 90, 1150, 80);
				panel.add(op);
					

				vmtext = new JTextArea();
				vmtext.setText(info);
				vmtext.setFont(font);  
				vmtext.setBackground(new Color(127,255,212));
				vmtext.setEditable(false);
				scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setBounds(10, 180, 1150, 400);
				panel.add(scroll);
					
				panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
				spframe.add(panel);
					
					
			}
				
			flag =1;
	
		}
	}
	
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------
}

	
