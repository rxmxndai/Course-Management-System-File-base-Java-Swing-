

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
import javax.swing.WindowConstants;










public class Student{
	
	//--------CLASS'S OBJECTS-----------------
	ModuleManager modulemanager = new ModuleManager();
	
	CourseManager coursemanager = new CourseManager();
	TutorFileManager tutormanager = new TutorFileManager();
	
	StudentFileManager studentmanager = new StudentFileManager();
	
	
	private String studentID;  
	private String studentName;
	private String studentSemester;
	private String studentPassword;
	private String studentLevel;
	private String studentMarks;
	private String studentGrade;
	private String  studentremarks = "";
	private String studentAddress;
	private String studentContact;
	private String studentEmail; 
	private String studentCourse;
	
	private String  moduleID;
	private String	moduleCourse;
	private String  moduleName;
	private String moduleLevel;
	
	private String courseID;
	private String	 courseName;
	
	private String  tutorName;
	
	
	
	private JScrollPane scroll;

	private JFrame sinfoframe;
	private JPanel vspanel;
	private  JTextArea vmtext, toplabel;

	
	
	Font font = new Font("Segoe UI Semibold", Font.BOLD, 16);
	
	 
	
	Student () {	
		
	}
	

	

	

	
	
//----------------------------------------------STUDENT MENU --------------------------------------
	
	
	protected void studentMenu() {
		
		JFrame moduleMenuframe = new JFrame();
		moduleMenuframe.setSize(500, 500);
		moduleMenuframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		moduleMenuframe.setTitle("Student management dashboard");
		moduleMenuframe.setResizable(false);
		
		
		JPanel panel = new JPanel(); 
		panel.setBackground(new Color(0x123456));
		panel.setOpaque(true);
		panel.setLayout(null);
		
		JButton cm = new JButton("Add Student");
		cm.setBounds(20, 30, 150, 50);
		cm.setFocusable(false);
		cm.addActionListener(e -> {
			moduleMenuframe.dispose();
			addStudent();
		});
		
		JButton mm = new JButton("Manage student");
		mm.setBounds(20, 120, 150, 50);
		mm.setFocusable(false);
		mm.addActionListener(e -> {
			moduleMenuframe.dispose();
			manageStudent();
		});
		
		JButton dm = new JButton("Remove Student");
		dm.setBounds(20, 210, 150, 50);
		dm.setFocusable(false);
		dm.addActionListener(e -> {
			moduleMenuframe.dispose();
			removeStudent();
		});
		
		JButton mback = new JButton("Go back");
		mback.setBounds(250, 30, 90, 40);
		mback.setBackground(Color.cyan);
		mback.setFocusable(false);
		mback.addActionListener(e -> {
			moduleMenuframe.dispose();
		});
		panel.add(cm); 
		panel.add(mm);
		panel.add(dm); 
		panel.add(mback);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		moduleMenuframe.add(panel);
		moduleMenuframe.setVisible(true);
	}

	 
	
	
 // -----------------------------------------------------------------STUDENTS LIST GUI------------------------------------------------------------------------------------------
	
	

	
	/// all students info 
	protected void viewStudents() {
		
		
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		studentmanager.readStudentFile();
		
		sinfoframe = new JFrame();
		sinfoframe.setSize(900, 700);
		sinfoframe.setTitle("All Students List");
		sinfoframe.setResizable(false);
		
		vspanel = new JPanel();
		vspanel.setBackground(new Color(0x123456));
		vspanel.setLayout(null);


		String yotext = "All Students Information";
		toplabel = new JTextArea(yotext);
		toplabel.setForeground(Color.white);
		toplabel.setBackground(new Color(0x123456));
		toplabel.setBounds(05, 20, 300, 40);
		toplabel.setEditable(false);
		toplabel.setFont(font);
		vspanel.add(toplabel);
		
		
		
		//		course name
		JLabel s = new JLabel();
		s.setForeground(Color.white);
		s.setBackground(new Color(0x123456));
		s.setBounds(550, 20, 100, 40);
		vspanel.add(s);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// course id
		JLabel deletecid = new JLabel("Course ID: ");
		deletecid.setBounds(20, 30, 120, 40);
		deletecid.setForeground(Color.white);
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> courseidcombo = new JComboBox<String>();
		courseidcombo.setBounds(300, 20, 200, 40);
		for (int o=0; o< coursemanager.courseidarr.size(); o++) {
			if (!elements.contains(coursemanager.courseidarr.get(o)) ) {
				elements.add(coursemanager.courseidarr.get(o));
				courseidcombo.addItem(coursemanager.courseidarr.get(o));
			} 
		}
		
		// action listener for selecting particular course
		courseidcombo.addActionListener(e-> {
			for (int o=0; o< coursemanager.coursenamearr.size(); o++) {
				if (coursemanager.courseidarr.get(o).equals((String)courseidcombo.getSelectedItem())) {
					s.setText(coursemanager.coursenamearr.get(o));
				} 
			}
			
			
			vspanel.remove(scroll);
			String cid = ((String)courseidcombo.getSelectedItem());
			showStudentsOfthisCourse(cid);
			
		});
		vspanel.add(courseidcombo);
		
		
		
		
		
		
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		vmtext = new JTextArea();
		vmtext.setBackground(new Color(176,196,222));
		
		String info= null;
		info ="";
		for (int  i=0; i< studentmanager.studentidarr.size(); i++ ) {
			info = info.concat(
					"Student Name\t\t:   "+ studentmanager.studentnamearr.get(i) + "\n" + 
					" Student ID\tutor\t:   "+ studentmanager.studentidarr.get(i) +"\n" + 
					" Student level\tutor\t:   "+  studentmanager.studentlevelarr.get(i) +  "\n" +
					" Student phone NO\t:   "+ studentmanager.studentcontactarr.get(i) + "\n" +
					" Student email\tutor\t:   "+ studentmanager.studentemailarr.get(i) +"\n"+
					" Student address\t:   "+ studentmanager.studentaddressarr.get(i) + "\n" +
					" Student course\t:   "+ studentmanager.studentcoursearr.get(i) + "\n" +
					" Student Semester\t:   " +  studentmanager.studentsemarr.get(i) + 
					
					"\n****************************************************\n"); 
	
		}
		
		vmtext.setText(info);
		vmtext.setEditable(false);
		vmtext.setFont(font);
		scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(05, 90, 870, 500);
		vspanel.add(scroll);


		JButton vtback = new JButton("Go back");
		vtback.setBounds(10, 600, 100, 40);
		vtback.setFocusable(false);
		vtback.setBackground(Color.cyan);
		vtback.addActionListener(e->sinfoframe.dispose());
		vspanel.add(vtback);
		
		
		vspanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		sinfoframe.add(vspanel);
		sinfoframe.setVisible(true);
	}
	
	
	
	
	
	
	
//-----------------------------------------------select student from course id--------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	

	private void showStudentsOfthisCourse(String id) {
		
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		String info = "";
		
		this.courseID = id;
		
		
		int flag =0;
		while (flag == 0) {
			if (courseID.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Fill up the course ID textfield" );
				flag =1;
			}
			else {
				
				for (int  i=0; i< coursemanager.courseidarr.size(); i++ ) {
					if (coursemanager.courseidarr.get(i).equals(courseID)) {
						this.courseName = coursemanager.coursenamearr.get(i);
					}
				}
				
				
				for (int  i=0; i< studentmanager.studentidarr.size(); i++ ) {
					if (studentmanager.studentcoursearr.get(i).equals(courseName)) {
						info = info.concat(
								"Student Name\t\t:   "+ studentmanager.studentnamearr.get(i) + "\n" + 
								" Student ID\tutor\t:   "+ studentmanager.studentidarr.get(i) +"\n" + 
								" Student level\tutor\t:   "+  studentmanager.studentlevelarr.get(i) +  "\n" +
								" Student phone NO\t:   "+ studentmanager.studentcontactarr.get(i) + "\n" +
								" Student email\tutor\t:   "+ studentmanager.studentemailarr.get(i) +"\n"+
								" Student address\t:   "+ studentmanager.studentaddressarr.get(i) + "\n" +
								" Student course\t:   "+ studentmanager.studentcoursearr.get(i) + "\n" +
								" Student Semester\t:   " +  studentmanager.studentsemarr.get(i) + 
								
								"\n****************************************************\n"); 
					}
				}
				vmtext.setText(null);
				vmtext.setText(info);
				vmtext.setEditable(false);
				vmtext.setFont(font);
				scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setBounds(05, 90, 870, 500);
				vspanel.add(scroll);
			
				sinfoframe.add(vspanel);
				
				flag=1;
			}
		}
			
	}
	

	
	
// ---------------------------------------------------------------ADD STUDENT GUI----------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	private void addStudent() {
		
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		sinfoframe = new JFrame();
		sinfoframe.setTitle("Add student GUI (ADMIN)"); 
		sinfoframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		sinfoframe.setSize(500, 600);
		sinfoframe.setResizable(false);
		
		JPanel pp = new JPanel();
		pp.setBackground(new Color(0x123456));
		pp.setLayout(null);
		
		JLabel ntid = new JLabel("Student ID:");
		ntid.setBounds(20, 30, 150, 40);
		ntid.setForeground(Color.white);
		JTextField ntidtext = new JTextField();
		ntidtext.setBounds(200, 30, 200, 40);
		
		
		JLabel ntname = new JLabel("Student Full Name:");
		ntname.setBounds(20, 90, 150, 40);
		ntname.setForeground(Color.white);
		JTextField ntnametext = new JTextField("");
		ntnametext.setBounds(200, 90, 200, 40);
		
		
		
		JLabel clevel = new JLabel("Level: ");
		clevel.setBounds(20, 150, 150, 40);
		clevel.setForeground(Color.white);
		
		String[] elements = {"3", "4", "5", "6"};
		JComboBox<String> leveltext = new JComboBox<String>(elements);
		leveltext.setBounds(200, 150, 200, 40);
		pp.add(leveltext);
		
		
		
		JLabel contact = new JLabel("Contact NO:");
		contact.setBounds(20, 210, 150, 40);
		contact.setForeground(Color.white);
		JTextField contacttext = new JTextField("");
		contacttext.setBounds(200, 210, 200, 40);
		
		
		JLabel email = new JLabel("Email:");
		email.setBounds(20, 270, 150, 40);
		email.setForeground(Color.white);
		JTextField emailtext = new JTextField("");
		emailtext.setBounds(200, 270, 200, 40);
		
		
		JLabel address = new JLabel("Full Address :");
		address.setBounds(20, 330, 150, 40);
		address.setForeground(Color.white);
		JTextField addresstext = new JTextField("");
		addresstext.setBounds(200, 330, 200, 50);
		
		
		JLabel cname = new JLabel("Course name:");
		cname.setBounds(20, 390, 150, 40);
		cname.setForeground(Color.white);
		
		// course name combo box
		ArrayList<String> el = new ArrayList<String>();
		JComboBox<String> coursenamecombo = new JComboBox<String>();
		coursenamecombo.setBounds(200, 390, 200, 40);
		pp.add(coursenamecombo);
		
		for (int o=0; o< coursemanager.coursenamearr.size(); o++) {
			if (!el.contains(coursemanager.coursenamearr.get(o)) ) {
				el.add(coursemanager.coursenamearr.get(o));
				coursenamecombo.addItem(coursemanager.coursenamearr.get(o));
			} 
		}

		String[] clevelcombo= {"1", "2"};
		JComboBox<String> mt = new JComboBox<String>(clevelcombo);
		mt.setBounds(20, 450, 100, 40);
		pp.add(mt);
		
		JButton create = new JButton("Add Student");
		create.setBounds(200, 450, 100, 40);
		create.setBackground(Color.cyan);
		create.setFocusable(false);
		create.addActionListener(e->{
			String sid = ntidtext.getText().toUpperCase();
			String sname = ntnametext.getText().toUpperCase();
			String slevel = (String) leveltext.getSelectedItem();
			String scontact = contacttext.getText().toUpperCase();
			String semail = emailtext.getText().toLowerCase();
			String saddress = addresstext.getText().toUpperCase();
			String cnamee = (String) coursenamecombo.getSelectedItem();
			String ms = (String) mt.getSelectedItem();
			
			addingStudent(sid, sname, slevel,  scontact, semail, saddress, cnamee, ms);
		});
		
		JButton cmback = new JButton("Go back");
		cmback.setBounds(310, 450, 90, 40);
		cmback.setFocusable(false);
		cmback.addActionListener(e-> {
			sinfoframe.dispose(); 
			studentMenu();
		});
		
		pp.add(ntid);
		pp.add(ntidtext);
		pp.add(ntname);
		pp.add(clevel);
		pp.add(ntnametext);
		pp.add(leveltext);
		pp.add(cname);
		pp.add(contact);
		pp.add(contacttext);
		pp.add(email);
		pp.add(emailtext);
		pp.add(address);
		pp.add(addresstext);
		pp.add(create);
		pp.add(cmback);
		pp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		sinfoframe.add(pp);
		sinfoframe.setVisible(true);
	}
	
	
	
	
	
//------------------------------------------------------ADDING STUDENTS IN STUDENT FILE-----------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	private void addingStudent(String sid, String sname, String slevel, String scontact, String semail, String saddress, String cname, String ms ) {
		
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		this.studentID = sid;
		this.studentName = sname;
		this.studentLevel = slevel;
		this.studentContact = scontact;
		this.studentEmail = semail;
		this.studentAddress = saddress;
		this.studentCourse = cname;
		this.studentSemester = ms;
		
	
		int FLAG = 0;
		
		while (FLAG == 0) {

			
			if (studentmanager.studentidarr.contains(studentID) ) {
				JOptionPane.showMessageDialog(null, "Theres another student with the same ID");
				FLAG =1;
			}
			
			else if (!studentmanager.studentidarr.contains(studentID) && !studentmanager.studentemailarr.contains(studentEmail) ) {
				
				studentmanager.studentidarr.add(studentID);
				studentmanager.studentnamearr.add(studentName);
				studentmanager.studentlevelarr.add(studentLevel);
				studentmanager.studentcontactarr.add(studentContact);
				studentmanager.studentemailarr.add(studentEmail);
				studentmanager.studentaddressarr.add(studentAddress);
				studentmanager.studentpasswordarr.add(studentName);
				studentmanager.studentcoursearr.add(studentCourse);
				studentmanager.studentsemarr.add(studentSemester);
				
				
				//////////////////////////////////////////////////////////////////////////////////////////
				studentmanager.writeStudentFile();
				//////////////////////////////////////////////////////////////////////////////////////////
				
				
				JOptionPane.showMessageDialog(null, "Student successfully enrolled in  " + studentCourse + "-Level " + studentLevel );
				FLAG =1;
			}
			
			else if (studentmanager.studentemailarr.contains(studentEmail)) {
				JOptionPane.showMessageDialog(null, "This email is already in use by another tutor.");
				FLAG =1;
			}
		}
	}
	
	
	
	
// -----------------------------------MANAGE STUDENTS GUI---------------------------------------------------------------------------------------------------------
	
	
	
	
	private void manageStudent() {
		
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		
		
		sinfoframe = new JFrame();
		sinfoframe.setTitle("Manage Students Info GUI");
		sinfoframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		sinfoframe.setSize(500, 500);
		sinfoframe.setResizable(false);
		
		JPanel pp = new JPanel();
		pp.setBackground(new Color(0x123456));
		pp.setLayout(null);
		
		
		JLabel nsn = new JLabel("Student Name:");
		nsn.setBounds(20, 150, 150, 40);
		nsn.setForeground(Color.white);
		JTextField nsnt = new JTextField();
		nsnt.setBounds(200, 150, 200, 40);
		
		
		JLabel sls = new JLabel("course-level-semester:");
		sls.setBounds(20, 90, 150, 40);
		sls.setForeground(Color.white);
		JTextField slst = new JTextField();
		slst.setBounds(200, 90, 200, 40);
		
		
		JLabel osidl = new JLabel("Student ID to modify:");
		osidl.setBounds(20, 30, 150, 40);
		osidl.setForeground(Color.white);
		
		// studentID combo box
		ArrayList<String> ed = new ArrayList<String>();
		JComboBox<String> studentidcombo = new JComboBox<String>();
		studentidcombo.setBounds(200, 30, 200, 40);
		for (int o=0; o< studentmanager.studentidarr.size(); o++) {
			if (!ed.contains(studentmanager.studentidarr.get(o)) ) {
				ed.add(studentmanager.studentidarr.get(o));
				studentidcombo.addItem(studentmanager.studentidarr.get(o));
			} 
		}
		studentidcombo.addActionListener(e-> {
			String tutn = "";
			for (int i=0; i< studentmanager.studentidarr.size(); i++) {
				if (studentmanager.studentidarr.get(i).equalsIgnoreCase((String) studentidcombo.getSelectedItem())) {
					tutn = studentmanager.studentnamearr.get(i);
					slst.setText(studentmanager.studentcoursearr.get(i) + "-" + studentmanager.studentlevelarr.get(i) + "-" + studentmanager.studentsemarr.get(i) );
					slst.setEditable(false);
				}
			}
			nsnt.setText(tutn);
			nsnt.setEditable(false);
		});

		pp.add(studentidcombo);
		

		
		

		
		
		JLabel nsc = new JLabel("Course:");
		nsc.setBounds(20, 210, 150, 40);
		nsc.setForeground(Color.white);
		
		// course name combo box
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> coursenamecombo = new JComboBox<String>();
		coursenamecombo.setBounds(200, 210, 200, 40);
		pp.add(coursenamecombo);
		for (int o=0; o< coursemanager.coursenamearr.size(); o++) {
			if (!elements.contains( coursemanager.coursenamearr.get(o)) ) {
				elements.add( coursemanager.coursenamearr.get(o));
				coursenamecombo.addItem( coursemanager.coursenamearr.get(o));
			} 
		}
		
		
		
		JLabel nsl = new JLabel("Level:");
		nsl.setBounds(20, 270, 150, 40);
		nsl.setForeground(Color.white);
		
		
		
		String[] elem = {"3", "4", "5", "6"};
		JComboBox<String> nslt = new JComboBox<String>(elem);
		nslt.setBounds(120, 270, 80, 40);
		pp.add(nslt);
		
		
		
		JLabel sem = new JLabel("Semester:");
	    sem.setBounds(220, 270, 80, 40);
		sem.setForeground(Color.white);
		pp.add(sem);
		
		
		
		String[] em = {"1", "2"};
		JComboBox<String> semm = new JComboBox<String>(em);
		semm.setBounds(320, 270, 80, 40);
		pp.add(semm);
		
		
		
		JButton modify = new JButton("Modify");
		modify.setBounds(200, 330, 90, 40);
		modify.setBackground(Color.cyan);
		modify.setFocusable(false);
		modify.addActionListener(e->{
			String oldsid =	(String) studentidcombo.getSelectedItem();
			String newsname = nsnt.getText().toUpperCase();
			String newsc = (String) coursenamecombo.getSelectedItem();
			String newsl = (String) nslt.getSelectedItem();
			String seme = (String) semm.getSelectedItem();
			modifyingStudent(oldsid, newsname, newsc, newsl, seme);
		});
		
		
		JButton mtback = new JButton("Go back");
		mtback.setBounds(310, 330, 90, 40);
		mtback.setFocusable(false);
		mtback.addActionListener (e-> {
			sinfoframe.dispose(); 
			studentMenu();
		});
		
		
		pp.add(osidl);
		pp.add(sls);
		pp.add(slst);
		pp.add(nsn);
		pp.add(nsnt);
		pp.add(nsc);
		pp.add(nsl);
		pp.add(nslt);
		pp.add(modify);
		pp.add(mtback);
		pp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		sinfoframe.add(pp);
		sinfoframe.setVisible(true);
	}




	
	
	//---------------------------------------------MODIFYINHG STUDENTS IN STUDENT FILE----------------------------------------------------------------------------------------------------------------------------------
	
	
	private void modifyingStudent(String oldsid, String newsname, String newsc, String newsl, String sem) {
		
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		this.studentName = newsname;
		this.studentLevel = newsl;
		this.studentCourse = newsc;
		this.studentSemester = sem;
		
		
		int flag = 0;
		while(flag == 0){

				
			for (int i=0; i< studentmanager.studentidarr.size(); i++) {
				if (studentmanager.studentidarr.get(i).equalsIgnoreCase(oldsid)) {
					studentmanager.studentnamearr.set(i, studentName);
					studentmanager.studentlevelarr.set(i, studentLevel);
					studentmanager.studentpasswordarr.set(i, studentName);
					studentmanager.studentcoursearr.set(i, studentCourse);
					studentmanager.studentsemarr.set(i, studentSemester);
				}
			}
			
			///////////////////////////////////////////////////////////////
			studentmanager.writeStudentFile();
			///////////////////////////////////////////////////////////////
			
			JOptionPane.showMessageDialog(null, "Student info successfully modified");
			
			
			
			flag = 1;
		} 
	}

	
	
	
	
	
	//--------------------------------------------STUDENT SETTINGS GUI---------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	

	
	
	
	
	


	
	protected void studentsSettings(String name) {
		
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		
		this.studentName = name; 
		
		
		for (int i=0; i< studentmanager.studentidarr.size(); i++) {
			if (studentmanager.studentnamearr.get(i).equals(studentName)) {
				this.studentID = studentmanager.studentidarr.get(i);
				this.studentEmail = studentmanager.studentemailarr.get(i);
				this.studentContact = studentmanager.studentcontactarr.get(i);
				this.studentAddress = studentmanager.studentaddressarr.get(i);
				this.studentPassword = studentmanager.studentpasswordarr.get(i);
			}
		}
		
		
		sinfoframe = new JFrame();
		sinfoframe.setTitle("Change Settings GUI students");
		sinfoframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		sinfoframe.setSize(500, 500);
		sinfoframe.setResizable(false);
		
		JPanel pp = new JPanel();
		pp.setBackground(new Color(0x123456));
		pp.setLayout(null);
		
		
		JLabel osidl = new JLabel("Your ID: ");
		osidl.setBounds(20, 30, 150, 40);
		osidl.setForeground(Color.white);
		
		
		JTextField osidtext = new JTextField(studentID);
		osidtext.setEditable(false);
		osidtext.setBounds(200, 30, 200, 40);
		
		
		JLabel nsc = new JLabel("Password: :");
		nsc.setBounds(20, 90, 150, 40);
		nsc.setForeground(Color.white);
		JTextField nsct = new JTextField(studentPassword);
		nsct.setEditable(false);
		nsct.setBounds(200, 90, 200, 40);
		
		
		JLabel sls = new JLabel("New Email: ");
		sls.setBounds(20, 150, 150, 40);
		sls.setForeground(Color.white);
		JTextField slst = new JTextField(studentEmail);
		slst.setBounds(200, 150, 200, 40);
		
		
		JLabel nsn = new JLabel("New Contact NO: ");
		nsn.setBounds(20, 210, 150, 40);
		nsn.setForeground(Color.white);
		JTextField nsnt = new JTextField(studentContact);
		nsnt.setBounds(200, 210, 200, 40);
		 
		JLabel nsl = new JLabel("New Address");
		nsl.setBounds(20, 270, 150, 40);
		nsl.setForeground(Color.white);
		JTextField nslt = new JTextField(studentAddress);
		nslt.setBounds(200, 270, 200, 40);
		
		
		JLabel nsll = new JLabel("New Password: ");
		nsll.setBounds(20, 330, 150, 40);
		nsll.setForeground(Color.white);
		JTextField nsllt = new JTextField();
		nsllt.setBounds(200, 330, 200, 40);

		JButton modify = new JButton("Change");
		modify.setBounds(200, 390, 90, 40);
		modify.setBackground(Color.cyan);
		modify.setFocusable(false);
		modify.addActionListener(e->{
			String oldsid = osidtext.getText().toUpperCase();
			String newEmail = slst.getText().toUpperCase();
			String contact = nsnt.getText().toUpperCase();
			String oldpassword = nsct.getText();
			String address = nslt.getText().toUpperCase();
			String newpass = nsllt.getText();
			changingInfo(oldsid, oldpassword, newEmail, address, contact, newpass);
		});
		
		JButton mtback = new JButton("Go back");
		mtback.setBounds(310, 390, 90, 40);
		mtback.setFocusable(false);
		mtback.addActionListener(e-> {
			sinfoframe.dispose(); 
		});
		
		
		pp.add(osidl);
		pp.add(osidtext);
		pp.add(nsc);
		pp.add(nsct);
		pp.add(sls);
		pp.add(slst);
		pp.add(nsn);
		pp.add(nsnt);
		pp.add(nsl);
		pp.add(nslt);
		pp.add(nsll);
		pp.add(nsllt);
		pp.add(modify);
		pp.add(mtback);
		pp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		sinfoframe.add(pp);
		sinfoframe.setVisible(true);
		
	}
	
	
	
	//---------------------------------------------MODIFYINHG STUDENTS IN STUDENT FILE----------------------------------------------------------------------------------------------------------------------------------
	
	
	
	private void changingInfo(String oldid, String oldpass, String nemail, String naddress, String contact, String newpass) {
		
		
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		this.studentAddress = naddress;
		this.studentContact = contact;
		this.studentEmail = nemail;
	
		int flag = 0;
		while(flag == 0){
			 
			
			if (oldid.isEmpty() || oldpass.isEmpty() || nemail.isEmpty() || naddress.isEmpty() || contact.isEmpty() || newpass.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill up all the text fields");
				flag =1;
			}

			else if(studentmanager.studentidarr.contains(oldid)){			
				for (int i=0; i<studentmanager.studentidarr.size(); i++) {
					if (studentmanager.studentidarr.get(i).equalsIgnoreCase(oldid) && studentmanager.studentpasswordarr.get(i).equals(oldpass)) {

						studentmanager.studentemailarr.set(i, studentEmail);
						studentmanager.studentpasswordarr.set(i, newpass);
						studentmanager.studentaddressarr.set(i, studentAddress);
						studentmanager.studentcontactarr.set(i, studentContact);
					}
				}
				
				//------------------------------------------------------
				studentmanager.writeStudentFile();
				//------------------------------------------------------
				JOptionPane.showMessageDialog(null, "Changes applied");
				
				flag = 1;
			} 
		}
	}
	
	
	
	
	//---------------------------------------------REMOVE STUDENT GUI----------------------------------------------------------------------------------------------------------------------------------
	
	

	
	

	private void removeStudent() {
		
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		sinfoframe = new JFrame();
		sinfoframe.setTitle("Remove Student GUI");
		sinfoframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		sinfoframe.setSize(500, 500);
		sinfoframe.setResizable(false);
		
		JPanel pp = new JPanel();
		pp.setBackground(new Color(0x123456));
		pp.setLayout(null);
		
		
		JLabel sid = new JLabel("Student ID:");
		sid.setBounds(20, 30, 150, 40);
		sid.setForeground(Color.white);
		
		// studentID combo box
		ArrayList<String> ed = new ArrayList<String>();
		JComboBox<String> sd1 = new JComboBox<String>();
		sd1.setBounds(200, 30, 200, 40);
		for (int o=0; o< studentmanager.studentidarr.size(); o++) {
			if (!ed.contains(studentmanager.studentidarr.get(o)) ) {
				ed.add(studentmanager.studentidarr.get(o));
				sd1.addItem(studentmanager.studentidarr.get(o));
			} 
		}
		
		pp.add(sd1);
		
		
		JLabel sn = new JLabel("Student Name:");
		sn.setBounds(20, 90, 150, 40);
		sn.setForeground(Color.white);
		JTextField sntext = new JTextField();
		sntext.setEditable(false);
		sntext.setBounds(200, 90, 200, 40);	
		
		
		JLabel sl = new JLabel("Student level:");
		sl.setBounds(20, 150, 150, 40);
		sl.setForeground(Color.white);
		JTextField sltext = new JTextField();
		sltext.setEditable(false);
		sltext.setBounds(200, 150, 200, 40);	
		
		
		JLabel sc = new JLabel("Faculty:");
		sc.setBounds(20, 210, 150, 40);
		sc.setForeground(Color.white);
		JTextField sctext = new JTextField();
		sctext.setEditable(false);
		sctext.setBounds(200, 210, 200, 40);
		
		/// combobox add action listener to write student name, studentlevel, studentcourse
		sd1.addActionListener(e->{
			String sidd = (String) sd1.getSelectedItem();
			for (int p = 0; p< studentmanager.studentnamearr.size(); p++) {
				if (studentmanager.studentidarr.get(p).equals(sidd)) {
					this.studentLevel = studentmanager.studentlevelarr.get(p);
					this.studentCourse = studentmanager.studentcoursearr.get(p);
					this.studentName = studentmanager.studentnamearr.get(p);
				}
			}
			
			sntext.setText(studentName);
			sltext.setText(studentLevel);
			sctext.setText(studentCourse);
			
		});
		
		JButton remove = new JButton("Remove");
		remove.setBounds(200, 270, 90, 40);
		remove.setBackground(Color.cyan);
		remove.setFocusable(false);
		remove.addActionListener(e -> {
			String todeleteid = (String) sd1.getSelectedItem();
			String todeletename = sntext.getText().toUpperCase(); 
			String todeleteslevel = sltext.getText();
			String todeletecoursefrom = sctext.getText().toUpperCase();
			removingStudent(todeleteid, todeletename, todeleteslevel, todeletecoursefrom);
		});
		 
		JButton rsback = new JButton("Go back");
		rsback.setBounds(310, 270, 90, 40);
		rsback.setFocusable(false);
		rsback.addActionListener(e-> {
			sinfoframe.dispose(); 
			studentMenu();
		});
		
		pp.add(sid);
		pp.add(sntext);
		pp.add(sn);
		pp.add(sl);
		pp.add(sltext);
		pp.add(sc);
		pp.add(sctext);
		pp.add(remove);
		pp.add(rsback);
		pp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		sinfoframe.add(pp);
		sinfoframe.setVisible(true);
	}


	
	
	//--------------------------------------------REMOVING STUDENTS IN STUDENT FILE----------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	private void removingStudent(String sid, String sname, String level, String course) {
		
		
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		this.studentID = sid;
		this.studentName = sname;
		this.studentLevel = level;
		this.studentCourse = course;
		
		int flag = 0;
		
		while(flag == 0){
	
				
				int i = 0;
				
				for (i=0; i< studentmanager.studentnamearr.size(); i++) {
			
					if (studentmanager.studentcoursearr.get(i).equalsIgnoreCase(studentCourse)  && studentmanager.studentnamearr.get(i).equalsIgnoreCase(studentName) && 
							studentmanager.studentidarr.get(i).equals(studentID) && studentmanager.studentlevelarr.get(i).equals(studentLevel)) {
						
						studentmanager.studentidarr.remove(i);
						studentmanager.studentnamearr.remove(i);
						studentmanager.studentlevelarr.remove(i);
						studentmanager.studentcontactarr.remove(i);
						studentmanager.studentemailarr.remove(i);
						studentmanager.studentaddressarr.remove(i);
						studentmanager.studentcoursearr.remove(i);
						studentmanager.studentpasswordarr.remove(i);
						studentmanager.studentsemarr.remove(i);
					}
				}
						
				///////////////////////////////////////////////////////////////////////////
				studentmanager.writeStudentFile();
				/////////////////////////////////////////////////////////////////////////////
				
				JOptionPane.showMessageDialog(null, "Student removed successfully");
				
				flag =1;
			}
				
		}
	
	
	
	
	
	//------------------------------------------GRADE STUDENTS GUI----------------------------------------------------------------------------------------------------------------------------------
	

	
	protected void gradeStudents(String tname) {
		
		studentmanager.readStudentFile();
		studentmanager.readMarksFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		tutormanager.readTutorFile();
		
		
		this.tutorName = tname;
		
		sinfoframe = new JFrame();
		sinfoframe.setSize(900, 600);
		sinfoframe.setTitle("GRADE STUDENTS Tutor's dash");
		sinfoframe.setResizable(false);
		
		vspanel = new JPanel();
		vspanel.setBackground(new Color(0x123456));
		vspanel.setOpaque(true);
		vspanel.setLayout(null);
		
		JLabel sid = new JLabel("Student ID:");
		sid.setBounds(10, 10, 100, 40);
		sid.setForeground(Color.white);
		
		
		JLabel slevel = new JLabel("Student Level:");
		slevel.setForeground(Color.white);
		slevel.setBounds(10, 60, 100, 40);
		
		JTextField sl1 = new JTextField();
		sl1.setEditable(false);
		sl1.setBounds(100, 60, 200, 40);
		vspanel.add(sl1);
		
		
		
		
		// studentID combo box
		ArrayList<String> ed = new ArrayList<String>();
		JComboBox<String> sd1 = new JComboBox<String>();
		sd1.setBounds(100, 10, 200, 40);
		for (int o=0; o< studentmanager.studentidarr.size(); o++) {
			if (!ed.contains(studentmanager.studentidarr.get(o)) ) {
				ed.add(studentmanager.studentidarr.get(o));
				sd1.addItem(studentmanager.studentidarr.get(o));
			} 
		}
		sd1.addActionListener(e -> {
			for (int p = 0; p<studentmanager.studentnamearr.size(); p++) {
				if (studentmanager.studentidarr.get(p).equals((String) sd1.getSelectedItem())) {
					this.studentLevel = studentmanager.studentlevelarr.get(p);
				}
			}
			sl1.setText(studentLevel);
			viewingstdinfo((String) sd1.getSelectedItem());
		});

		vspanel.add(sd1);

		
		JLabel smodule = new JLabel("Module ID: ");
		smodule.setForeground(Color.white);
		smodule.setBounds(10, 110, 100, 40);
		
		
		JLabel ml = new JLabel();
		ml.setForeground(Color.white);
		ml.setBackground(new Color(0x123456));
		ml.setBounds(350, 110, 200, 40);
		vspanel.add(ml);
		
		JLabel grades = new JLabel("Grade: ");
		grades.setForeground(Color.white);
		JTextField gradetext = new JTextField();
		grades.setBounds(220, 160, 100, 40);
		gradetext.setBounds(310, 160, 100, 40);
		vspanel.add(grades);
		vspanel.add(gradetext);
		
		
		// studentID combo box
		JComboBox<String> tutormodulecombo = new JComboBox<String>();
		tutormodulecombo.setBounds(100, 110, 200, 40);	
		
		for (int o=0; o < tutormanager.tutornamearr.size(); o++) {
			if ((tutormanager.tutornamearr.get(o).equals(tutorName)) ) {
				tutormodulecombo.addItem(tutormanager.tutormodulearr.get(o));
			} 
		} 
		tutormodulecombo.addActionListener(e-> {
			for (int d=0; d< modulemanager.moduleidarr.size(); d++) {
				if(modulemanager.moduleidarr.get(d).equalsIgnoreCase((String) tutormodulecombo.getSelectedItem())) {
					this.moduleLevel = modulemanager.modulelevelarr.get(d);
					this.moduleCourse = modulemanager.modulecoursearr.get(d);
				}
			}
		
			ml.setText( "Level: " + moduleLevel + "    Course: " + moduleCourse);
		});
		vspanel.add(tutormodulecombo);
		
		
		
		
		
		JLabel marks = new JLabel("Marks: ");
		marks.setForeground(Color.white);
		
		JComboBox<Integer> mttextcombo = new JComboBox<Integer>();
		
		for (int i=0; i<101; i++) {
			mttextcombo.addItem(i);
		}
		marks.setBounds(10, 160, 100, 40);
		mttextcombo.setBounds(100, 160, 100, 40);
		mttextcombo.addActionListener(e-> {
			
			
			int getMarks = (int) mttextcombo.getSelectedItem();
			
			String graded;
			
			if(getMarks >= 75){
				graded = "A";
			}else if(getMarks < 75 && getMarks >= 55){
				graded = "B";
			}else if(getMarks < 55 && getMarks >= 40){
				graded = "C";
			}else if(getMarks < 40 ){
				graded = "F";
			}else {
				graded = "F";
			}
			
			gradetext.setText(graded);
			gradetext.setEditable(false);
		});
		
		

		
		
		
		JLabel remarks = new JLabel("Remarks: ");
		remarks.setForeground(Color.white);
		JTextField remarkstext = new JTextField();
		remarks.setBounds(10, 210, 100, 40);
		remarkstext.setBounds(100, 210, 400, 80);

		JButton submit = new JButton("Grade");
		submit.setBounds(520, 235, 100, 50);
		submit.setBackground(Color.cyan);
		submit.setFocusable(false);
		submit.addActionListener(e -> {
			String id = (String) sd1.getSelectedItem();
			String level = sl1.getText();
			String mid = (String) tutormodulecombo.getSelectedItem();
			String grade = gradetext.getText();
			String markks = Integer.toString((int) mttextcombo.getSelectedItem()) ;
			String remarkss = remarkstext.getText().toUpperCase();
			nowPutResult(id, level, mid, grade, remarkss, tname, markks);
		});
  		
		
		
		JButton sinfoback = new JButton("Go back");
		sinfoback.setBounds(750, 20, 100, 30);
		sinfoback.setFocusable(false);
		sinfoback.addActionListener(e->sinfoframe.dispose());
		
		vmtext = new JTextArea();
		vmtext.setBackground(new Color(176,196,222));
		vmtext.setBounds(05, 320, 870, 200);
		
		vspanel.add(sid);
		vspanel.add(smodule);
		vspanel.add(slevel);
		vspanel.add(marks);
		vspanel.add(mttextcombo);
		vspanel.add(remarks);
		vspanel.add(remarkstext);
		vspanel.add(submit);
		vspanel.add(sinfoback);
		vspanel.add(vmtext);
		
		
		vspanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		sinfoframe.add(vspanel);
		sinfoframe.setVisible(true);
	}
	
	


	
	
	
	
	
	
	//--------------------------------------------INDIVIDUAL STUDENT INFO--------------------------------------------------------------------------------------------------------------------------
	
	
		private void viewingstdinfo(String id) {
			
			
			studentmanager.readStudentFile();
			studentmanager.readMarksFile();
			coursemanager.readCourseFile();
			modulemanager.readModuleFile();
			
			
			String info = "";
			this.studentID = id;
			int flag =0;
			while (flag == 0) {
				if (studentID.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fill up the student ID textfield" );
					flag =1;
				}
				
				else if (!studentmanager.studentidarr.contains(studentID)) {
					JOptionPane.showMessageDialog(null, "No student with id: " + studentID);
					flag =1;
				}
				else {
					
					for (int  i=0; i< studentmanager.studentidarr.size(); i++ ) {
						if (studentmanager.studentidarr.get(i).equals(studentID)) {
							info = info.concat(
									"Student Name:\tutor\tutor"+ studentmanager.studentnamearr.get(i) + "\n" + 
									" Student ID:\tutor\tutor"+ studentmanager.studentidarr.get(i) +"\n" + 
									" Student level:\tutor\tutor"+ studentmanager.studentlevelarr.get(i) +  "\n" +
									" Student phone NO:\tutor"+ studentmanager.studentcontactarr.get(i) + "\n" +
									" Student email:\tutor\tutor"+ studentmanager.studentemailarr.get(i) +"\n"+
									" Student address:\tutor"+ studentmanager.studentaddressarr.get(i) + "\n" +
									" Student course:\tutor"+ studentmanager.studentcoursearr.get(i)) + "\n";
							break;
						}
					}
					
					Font font = new Font("Segoe UI Semibold", Font.PLAIN, 20);
					vmtext.setText(info);
					vmtext.setBackground(new Color(176,196,222));
					vmtext.setEditable(false);
					vmtext.setFont(font);
					
					vmtext.setBounds(05, 320, 870, 200);
					vmtext.setEditable(false);
					vspanel.add(vmtext);
					sinfoframe.add(vspanel);
					
					flag =1;
		
				}
			}
				
			
			
			
			
		}


	
	
	
		
		
		
		
		
		
		//--------------------------------------------PUTTING MARKS GRADE AND REMARKS IN MARKS FILE---------------------------------------------------------------------------------------------------------------------------------
		
	
		private void nowPutResult(String stdid, String level, String stdmid, String grade, String remarks, String tname, String markks ) {
			
			
			studentmanager.readStudentFile();
			coursemanager.readCourseFile();
			modulemanager.readModuleFile();
			studentmanager.readMarksFile();
			
			this.studentremarks = remarks;
			this.studentID = stdid;
			this.studentLevel = level;
			this.moduleID = stdmid;
			this.studentGrade = grade;
			this.studentMarks = markks;
		
			int flag =0;
			int processFurther = 0;
			
			if (stdid.isEmpty() || level.isEmpty() || stdmid.isEmpty() || grade.isEmpty() || remarks.isEmpty() || markks.isEmpty() ) {
			
				JOptionPane.showMessageDialog(null,  "Fill up all text fields please"  );
				flag =1;
				processFurther =1;
			}
				
				
			// module ko id bata level thahapaune
			
			for (int  j = 0; j < modulemanager.modulenamearr.size(); j++ ) {
				if (modulemanager.moduleidarr.get(j).equals(moduleID)) {
					this.moduleLevel = modulemanager.modulelevelarr.get(j);
				}
			}
			
			
			// student level ra teacher ko module ko level check garni

			if (!moduleLevel.equals(studentLevel)  && flag == 0) {
				JOptionPane.showMessageDialog(null,  "Cannot grade! Student level and your module's level do not match: " + moduleID );
				flag =1;
			}	

			
			else if (moduleLevel.equals(studentLevel ) && flag==0 ) {
				
				
				// get info from module file 
				for (int  j = 0; j < modulemanager.modulenamearr.size(); j++ ) {

					if (modulemanager.moduleidarr.get(j).equals(moduleID)) {
						this.moduleCourse = modulemanager.modulecoursearr.get(j);
						this.moduleLevel = modulemanager.modulelevelarr.get(j);
						this.moduleName = modulemanager.modulenamearr.get(j);
						this.studentSemester = modulemanager.modulesemarr.get(j);
					}
				}
			
			
			while (flag == 0 ) {
				
					// get info from student file
					for (int  i=0; i< studentmanager.studentidarr.size(); i++ ) {
						
						if (studentmanager.studentidarr.get(i).equals(studentID)) {
							
							this.studentName = studentmanager.studentnamearr.get(i);
							this.studentLevel = studentmanager.studentlevelarr.get(i);
							this.studentCourse = studentmanager.studentcoursearr.get(i);
							break;
						}
					}
							
					
					
					// if module id and student id already exist
					for  (int p = 0; p <studentmanager.markssid.size(); p++) {
								
						if (studentmanager.markssid.get(p).equals(studentID) && studentmanager.markssmid.get(p).equals(moduleID)) {
							JOptionPane.showMessageDialog(null,  "Student already graded in this module");
							studentmanager.markssid.set(p, studentID);
							studentmanager.markssname.set(p, studentName);
							studentmanager.markssl.set(p, studentLevel);
							studentmanager.markssmid.set(p, moduleID);
							studentmanager.markssmname.set(p, moduleName);
							studentmanager.markssmarks.set(p, studentMarks);
							studentmanager.markssgrade.set(p, studentGrade);
							studentmanager.markssremarks.set(p, studentremarks);
							studentmanager.marksssem.set(p, studentSemester);
							
							processFurther = 1;
							flag =1;
							break;
						}
					}
					
					
					if  (processFurther == 0 && moduleLevel.equals(studentLevel) && moduleCourse.equals(studentCourse) ) {
						
						studentmanager.markssid.add(studentID);
						studentmanager.markssname.add(studentName);
						studentmanager.markssl.add(studentLevel);
						studentmanager.markssmid.add(moduleID);
						studentmanager.markssmname.add(moduleName);
						studentmanager.markssmarks.add(studentMarks);
						studentmanager.markssgrade.add(studentGrade);
						studentmanager.markssremarks.add(studentremarks);
						studentmanager.marksssem.add(studentSemester);
						
						
						//////////////////////////////////////////////////////////////////////////
						studentmanager.writeMarksFile();
						/////////////////////////////////////////////////////////////////////////
						
						JOptionPane.showMessageDialog(null, "Student  " + studentID +  "  Graded in module " + moduleName  );
					}

					
					flag = 1;								
				}
			}
				
		}
		
		
		
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		
	}
