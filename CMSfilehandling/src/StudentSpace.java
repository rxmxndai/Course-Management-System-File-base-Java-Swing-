
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class StudentSpace  { 
	 
	Student student = new Student(); 
	Module module = new Module();
	Course course = new Course();
	
	CourseManager coursemanager = new CourseManager(); 
	ModuleManager modulemanager = new ModuleManager();
	
	
	Tutor tutor = new Tutor();
	TutorFileManager tutormanager = new TutorFileManager();
	
	StudentFileManager studentmanager = new StudentFileManager();


	@SuppressWarnings("unused")
	private String studentID, moduleID, moduleName, studentModule, studentCourse, moduleLevel, studentSemester, moduleSemester;
	private String studentName, studentLevel;
	
	
	private JFrame sdframe; 
	private JTextArea op, vmtext;
	private JPanel panel;

	
	
	@SuppressWarnings("unused")
	private JScrollPane scroll;
	
	 
	 
	
		
	//---------------------------------------------STUDENT DASHBOARD----------------------------------------------------------------------------------------------------------------------------
	
	 
	protected void studentDash(String name) {
		
		this.studentName = name.toUpperCase();
		
		
		sdframe = new JFrame();
		sdframe.setVisible(true);
		sdframe.setSize(750, 480);
		sdframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		sdframe.setTitle("Students dashboard");
        
		JPanel dd = new JPanel(); 
		dd.setBackground(new Color(0x123456));
		dd.setOpaque(true);
		dd.setLayout(null);
		
		ImageIcon headerimg = null;
		
		try {
			headerimg  = new ImageIcon(getClass().getResource("/p/student.jpg"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Picture not found!  " + e, null, JOptionPane.INFORMATION_MESSAGE);
		}
        
		if (headerimg != null ) {
			JLabel p = new JLabel();
			p.setBounds(480, 120, 200, 200);
			Image scaled =  headerimg.getImage().getScaledInstance(p.getWidth(), p.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon sc = new ImageIcon(scaled);
			p.setIcon(sc);
			p.setOpaque(true);
			p.setBackground(Color.green);
			dd.add(p);
			sdframe.setIconImage(headerimg.getImage());
		}
		
		
		
		
		JButton coursebutton = new JButton("View Courses");
		coursebutton.setBounds(20, 30, 180, 60);
		coursebutton.setFocusable(false);
		coursebutton.addActionListener(e -> 
		{
			course.viewCourse(); 
		});
		dd.add(coursebutton);
		
		JButton modulebutton = new JButton("View Modules");
		modulebutton.setBounds(250, 30, 180, 60);
		modulebutton.setFocusable(false);
		modulebutton.addActionListener(e -> module.viewModule());
		dd.add(modulebutton);
		
		JButton mod = new JButton("Enrolled Modules");
		mod.setBounds(480, 30, 200, 60);
		mod.setFocusable(false);
		mod.addActionListener(e -> viewMyModules(studentName));
		dd.add(mod);
		
		JButton tutorbutton = new JButton("Tutor in my level");
		tutorbutton.setBounds(20, 120, 180, 60);
		tutorbutton.setFocusable(false);
		tutorbutton.addActionListener(e-> {
			sdframe.dispose();
			viewTutorasStudent(studentName);
		});
		dd.add(tutorbutton);
		
		
		JButton studentbutton = new JButton("Students in my level"); 
		studentbutton.setBounds(250, 120, 180, 60);
		studentbutton.setFocusable(false);
		studentbutton.addActionListener(e-> {
			sdframe.dispose();
			viewStudentsasStudent(studentName);
		});
		dd.add(studentbutton);
		
		JButton managestudentbutton = new JButton("Settings");
		managestudentbutton.setBounds(20, 210, 180, 60);
		managestudentbutton.setFocusable(false);
		managestudentbutton.addActionListener(e-> student.studentsSettings(studentName));
		dd.add(managestudentbutton);
		
		
		JButton viewresultbutton = new JButton("View My Result");
		viewresultbutton.setFocusable(false);
		viewresultbutton.setBounds(250, 210, 180, 60);
		viewresultbutton.addActionListener(e-> viewMyResult(studentName));                
		dd.add(viewresultbutton);
		
		
		JButton signoutbutton = new JButton("Sign out");
		signoutbutton.setBounds(580, 350, 100, 40);
		signoutbutton.setBackground(Color.cyan);
		signoutbutton.setFocusable(false);
		signoutbutton.addActionListener(e -> {
			sdframe.dispose();
			new SignIn();
		});
		dd.add(signoutbutton);
		
		
		
		dd.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		sdframe.add(dd);
		sdframe.setVisible(true);
		
		
	}
	
	
	

	
	
	
	
	//---------------------------------------------VIEW STUDENTS'S RESULT STUDENTS VIEW-------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	private void viewMyResult(String myname) {
		 
		 studentmanager.readStudentFile();
		 studentmanager.readMarksFile();

		 	
		 	this.studentName = myname;
		 	
		 	
		 	for (int  i=0; i< studentmanager.studentnamearr.size(); i++ ) {
				
				if (studentmanager.studentnamearr.get(i).equalsIgnoreCase(studentName)) {
					this.studentLevel = studentmanager.studentlevelarr .get(i);
					this.studentSemester = studentmanager.studentsemarr.get(i);
					this.studentCourse = studentmanager.studentcoursearr.get(i);
				}
		 	}	
		 	
		 	
			
			JFrame spframe = new JFrame();
			spframe.setSize(920, 700);
			spframe.setTitle("Students Grade Remarks dash");
			spframe.setResizable(false);
			
			panel = new JPanel();
			panel.setBackground(new Color(0x123456));
			panel.setLayout(null);

			JButton sinfoback = new JButton("Go back");
			sinfoback.setBounds(20, 580, 90, 40);
			sinfoback.setBackground(Color.cyan);
			sinfoback.setFocusable(false);
			sinfoback.addActionListener(e->spframe.dispose());
			
			
			op = new JTextArea();
			vmtext = new JTextArea();
			
			
			Font font = new Font("Segoe UI Semibold", Font.PLAIN, 15);
			Font font1 = new Font("Segoe UI Semibold", Font.PLAIN, 20);
			String info = "";
			
			for (int  i=0; i< studentmanager.markssid.size(); i++ ) {
				
				if (studentmanager.markssl.get(i).equals(studentLevel) && studentmanager.markssname.get(i).equals(studentName) && studentmanager.marksssem.get(i).equalsIgnoreCase(studentSemester)) {
				
					info = info.concat(
							studentmanager.markssmid.get(i) +"\t   " +
									studentmanager.markssmarks.get(i) +  "\t "+  
									studentmanager.markssgrade.get(i) +"\t   " +
									studentmanager.markssremarks.get(i) +"\n " 
							);
				
				}
			}
			
			
			
			String m = " Name       :   " + studentName + "\n Level        :   " + studentLevel + "\n Semester  :   " + studentSemester +  "\t\t Course   :   " + studentCourse;
			JTextArea top = new JTextArea();
			top.setEditable(false);
			top.setText(m);
			top.setFont(font1);
			top.setBounds(10, 30, 890, 100);
			top.setBackground(new Color(127,255,212));
			panel.add(top);
			
			String boom = "********************************************** RESULT***************************************************************************" + "\n" 
					+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\n"
					+" Module ID \t Marks\t Grade\t Remarks\n"
			 + "-----------------------------------------------------------------------------------------------------------------------------------------------------\n";
			
			op = new JTextArea();
			op.setEditable(false);

			op.setBackground(new Color(127,255,212));
			op.setText(boom);
			op.setFont(font);
			op.setBounds(10, 140, 890, 70);
			panel.add(op);
			
			
			Font font2 = new Font("Segoe UI Semibold", Font.PLAIN, 15);
			vmtext.setText(info);
			vmtext.setFont(font2); 
			vmtext.setBackground(new Color(127,255,212));
			vmtext.setEditable(false);
			JScrollPane scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setBounds(10,210, 890, 200);
			panel.add(scroll);
				
			
			
			panel.add(sinfoback);
			panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
			spframe.add(panel);
			spframe.setVisible(true);
	 }
	
	 
	 
	 
	 
	 
	 
	 
	//---------------------------------------------STUDENTS LIST FROM STUDENT'S LEVEL AND COURSE---------------------------------------------------------------------------------------------------------------------------------
	
	 
	 private void viewStudentsasStudent(String name) {
		 
		 
		 studentmanager.readStudentFile();
		 
		this.studentName = name;
		
		JFrame vsframe = new JFrame();
		vsframe.setSize(900, 650);
		vsframe.setTitle("Students List (STUDENT'S VIEW)");
		vsframe.setResizable(false);
		
		
		JPanel vspanel = new JPanel();
		vspanel.setBackground(new Color(0x123456));
		vspanel.setOpaque(true);
		vspanel.setLayout(null);

		
		
		Font font = new Font("Segoe UI Semibold", Font.PLAIN, 15);
		String yotext = "Students Information in my level";
		JTextArea toplabel = new JTextArea(yotext);
		toplabel.setBounds(05, 05, 870, 40);
		toplabel.setForeground(Color.white);
		toplabel.setBackground(new Color(0x123456));
		toplabel.setEditable(false);
		toplabel.setFont(font);
		vspanel.add(toplabel);
		
		JTextArea vttext = new JTextArea();
		vttext.setBackground(new Color(176,196,222));
		
		String info="";
		
		// module id from  (tutor's file)
		
				for (int  i=0; i< studentmanager.studentnamearr.size(); i++ ) {
					if (studentmanager.studentnamearr.get(i).equals(studentName)) {
						this.studentCourse = studentmanager.studentcoursearr.get(i);
						this.studentLevel = studentmanager.studentlevelarr.get(i);	
						this.studentSemester = studentmanager.studentsemarr.get(i);
					}
					
				}
				
				for (int  i=0; i< studentmanager.studentnamearr.size(); i++ ) { 
					
					if (studentmanager.studentcoursearr.get(i).equals(studentCourse) && studentmanager.studentlevelarr.get(i).equals(studentLevel) &&
							studentmanager.studentsemarr.get(i).equals(studentSemester)) {
						
						info = info.concat(
								"Student Name\t\t:   "+ studentmanager.studentnamearr.get(i) + "\n" + 
								" Student ID\t\t:   "+studentmanager.studentidarr.get(i) +"\n" + 
								" Student level\t\t:   "+ studentmanager.studentlevelarr.get(i) +  "\n" +
								" Student phone NO\t:   "+studentmanager.studentcontactarr.get(i) + "\n" +
								" Student email\t\t:   "+studentmanager.studentemailarr.get(i) +"\n"+
								" Student address\t:   "+studentmanager.studentaddressarr.get(i) + "\n" +
								" Student course\t:   "+studentmanager.studentcoursearr.get(i) + "\n" +
								" Student Semester\t:   " + studentmanager.studentsemarr.get(i) + 
								
								"\n****************************************************\n"); 
					}
				}

		vttext.setText(info);
		vttext.setEditable(false);
		vttext.setFont(font);
		JScrollPane scroll = new JScrollPane(vttext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(05, 60, 870,450);
		vspanel.add(scroll);



		JButton vtback = new JButton("Go back");
		vtback.setBounds(10, 560, 100, 40);
		vtback.setBackground(Color.cyan);
		vtback.setFocusable(false);
		vtback.addActionListener(e->
		{
			vsframe.dispose();
			studentDash(studentName);
		});
		vspanel.add(vtback);
		vspanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		vsframe.add(vspanel);
		vsframe.setVisible(true);
	}
	

	 
	 
	 
	//---------------------------------------------VIEW TUTOR AS STUDENTS---------------------------------------------------------------------------------------------------------------------------------
		
	 
	 
	 
	 
	 
	private void viewTutorasStudent(String name) {
		
		
		studentmanager.readStudentFile();
		tutormanager.readTutorFile();
		
		
		this.studentName = name;

		JFrame vtframe = new JFrame();
		vtframe.setSize(900, 700);
		vtframe.setTitle("Tutor List (STUDENT'S VIEW)");
		vtframe.setResizable(false);
		
		
		JPanel vtpanel = new JPanel();
		vtpanel.setBackground(new Color(0x123456));
		vtpanel.setLayout(null);
		
		
		// top label
		Font font = new Font("Segoe UI Semibold", Font.PLAIN, 15);
		String yotext = "Tutors Information";
		JTextArea toplabel = new JTextArea(yotext);
		toplabel.setBounds(05, 05, 870, 40);
		toplabel.setForeground(Color.white);
		toplabel.setBackground(new Color(0x123456));
		toplabel.setEditable(false);
		toplabel.setFont(font);
		vtpanel.add(toplabel);
		
		JTextArea vttext = new JTextArea();
		vttext.setBackground(new Color(176,196,222));
		
		String info = "";
		
		for (int  i=0; i< studentmanager.studentnamearr.size(); i++ ) {
			if (studentmanager.studentnamearr.get(i).equals(studentName)) {
				this.studentCourse = studentmanager.studentcoursearr.get(i);
				this.studentLevel = studentmanager.studentlevelarr.get(i);	
				this.studentSemester = studentmanager.studentsemarr.get(i);
			}
			
		}
		
		for (int  i=0; i< tutormanager.tutornamearr.size(); i++ ) { 
			if (tutormanager.tutorcoursearr.get(i).equals(studentCourse) && tutormanager.tutorlevelarr.get(i).equals(studentLevel) && tutormanager.tutorsemarr.get(i).equals(studentSemester)) {

				info = info.concat(
				        " Tutor ID\t\t:   " + tutormanager.tutoridarr.get(i) + "\n" + 
						" Tutor Name\t\t:   " +		tutormanager.tutornamearr.get(i) + "\n" +
						" Tutor contact\t\t:   " +		tutormanager.tutorcontactarr.get(i) + "\n" + 
						" Tutor email\t\t:   " +		tutormanager.tutoremailarr.get(i) + "\n" + 
						" Tutor address\t\t:   " +		tutormanager.tutoraddressarr.get(i) + "\n" + 
						" Course\t\t:   " +		tutormanager.tutorcoursearr.get(i) + "\n" + 
						" Level\t\t:   " +		tutormanager.tutorlevelarr.get(i) +  "\n" + 
						" Semester\t\t:   " + 	tutormanager.tutorsemarr.get(i) +"\n" + 
						"\n*****************************************************" + " \n\n");
			
			}
		}
		
		
		
		vttext.setText(info);
		vttext.setEditable(false);
		vttext.setFont(font);
		JScrollPane scroll = new JScrollPane(vttext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(05, 60, 870, 500);
		vtpanel.add(scroll);


		JButton vtback = new JButton("Go back");
		vtback.setBounds(20, 610, 100, 40);
		vtback.setBackground(Color.cyan);
		vtback.setFocusable(false);
		vtback.addActionListener(e-> {
			studentDash(studentName);
			vtframe.dispose();
		});
		vtpanel.add(vtback);
		vtpanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		vtframe.add(vtpanel);
		vtframe.setVisible(true);
	
	}
	
	
	
	
	
	
	
	
	//---------------------------------------------ENROLLED MODULESE----------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	private void viewMyModules(String name) {
		
		
		
		studentmanager.readStudentFile();
		modulemanager.readModuleFile();
		
		this.studentName = name;
		
		JFrame f1 = new JFrame();
		f1.setSize(900, 700);
		f1.setTitle("Enrolled Module list (Students'S VIEW)");
		f1.setResizable(false);
		
		 
		JPanel vspanel = new JPanel();
		vspanel.setLayout(null);
		vspanel.setBackground(new Color(0x123456));

		Font font = new Font("Segoe UI Semibold", Font.BOLD, 20);
		JTextArea toplabel = new JTextArea("Student Name:   " + studentName);
		toplabel.setBounds(05, 20, 870, 150);
		toplabel.setEditable(false);
		toplabel.setFont(font);
		toplabel.setForeground(Color.BLACK);
		toplabel.setBackground(new Color(176,196,222));
		vspanel.add(toplabel);
		
		JTextArea vttext = new JTextArea();

		String info="\n  Module ID\tSemester\tModule Name\n";	
		int flag = 0;
		
		Font font1 = new Font("Segoe UI Semibold", Font.PLAIN, 18);
		while (flag == 0) {
			
			
			// get student level and course name of student
			for (int i=0; i < studentmanager.studentidarr.size(); i++ ) {
				
				if (studentmanager.studentnamearr.get(i).equalsIgnoreCase(studentName)) {
					
					this.studentLevel = studentmanager.studentlevelarr.get(i);
					this.studentCourse = studentmanager.studentcoursearr.get(i);
					this.studentSemester = studentmanager.studentsemarr.get(i);
				}
			}
					
					
			// getting module name from id
			for (int p = 0; p< modulemanager.moduleidarr.size(); p++) {
						
				if (modulemanager.modulelevelarr.get(p).equalsIgnoreCase(studentLevel) && 
						modulemanager.modulecoursearr.get(p).equalsIgnoreCase(studentCourse)  && modulemanager.modulesemarr.get(p).equalsIgnoreCase(studentSemester) ) {
					
					this.moduleSemester = modulemanager.modulesemarr.get(p);
					this.moduleID =  modulemanager.moduleidarr.get(p);
					this.moduleName = modulemanager.modulenamearr.get(p);
					this.moduleLevel = modulemanager.modulelevelarr.get(p);

					info = info.concat(
							"\n  " + 
							moduleID + "\t" + moduleSemester +"\t" + moduleName +
							"\n"  
							);
					flag = 1;
					
				}
			}
					
		}
	 
		
		toplabel.setText("  Student Name   :   " + studentName + "\n\n" + "  Course\t   :   " + studentCourse + "\n\n" + 
						"  Level\t   :   " + studentLevel);
		
		
		vttext.setText(info);
		vttext.setBackground(new Color(176,196,222));
		vttext.setEditable(false);
		vttext.setFont(font1);
		JScrollPane sc = new JScrollPane(vttext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sc.setBounds(05, 180, 870, 380);
		
		vspanel.add(sc);
		 

		JButton vtback = new JButton("Go back");
		vtback.setBounds(10, 580, 100, 40);
		vtback.setBackground(Color.cyan);
		vtback.setFocusable(false);
		vtback.addActionListener(e->f1.dispose());
		vspanel.add(vtback);
		
		
		vspanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		f1.add(vspanel);
		f1.setVisible(true);
		
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public static void main(String[] args) {
		StudentSpace sc= new StudentSpace();
		sc.studentDash("AAKASH BADAL");
	}	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------
}
