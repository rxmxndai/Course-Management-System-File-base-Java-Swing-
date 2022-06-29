
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




public class Course  {
	
	
	// NECESSARY CLASS OBJECTS
	ModuleManager modulemanager = new ModuleManager();
	TutorFileManager tutormanager = new TutorFileManager();
	StudentFileManager studentmanager = new StudentFileManager();
	CourseManager coursemanager = new CourseManager();
	
	
	
	// NECESSARY COURSES STRINGS
	private String courseID;
	private String courseName;



 
	Course () {
		// empty constructor
	}
	
	
	Course (String id, String name) {
		this.courseID =  id;
		this.courseName = name;
	}
	
	
	

	// <------------------------------------------------Course Menu (Management) GUI----------------------------------------------------->
	void courseMenu() {
		
		coursemanager.readCourseFile();
		
		JFrame courseMenuframe = new JFrame();
		courseMenuframe.setSize(600, 600);
		courseMenuframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		courseMenuframe.setTitle("Course Management (ADMIN)");
		courseMenuframe.setResizable(false);

        
		JPanel panel = new JPanel(); 
		panel.setOpaque(true);
		panel.setBackground(new Color(0x123456));
		panel.setLayout(null);
		
		JButton createCourseB = new JButton("Create Course");
		createCourseB.setFocusable(false);
		createCourseB.setBounds(20, 30, 180, 60); 
		createCourseB.addActionListener(e -> {
			courseMenuframe.dispose();
			createCourse();
		});
		
		JButton modifyCourseB = new JButton("Modify Course");
		modifyCourseB.setFocusable(false);
		modifyCourseB.setBounds(20, 120, 180, 60);
		modifyCourseB.addActionListener(e -> {
			courseMenuframe.dispose();
			modifyCourse();
		});
		
		JButton cancelCourseB = new JButton("Cancel Course");
		cancelCourseB.setFocusable(false);
		cancelCourseB.setBounds(300, 120, 180, 60);
		cancelCourseB.addActionListener(e -> { 
			courseMenuframe.dispose();
			cancelCourse(); 
		});
		panel.add(cancelCourseB);
		
		JButton renameCourseB = new JButton("Rename Course");
		renameCourseB.setFocusable(false);
		renameCourseB.setBounds(20, 210, 180, 60);
		renameCourseB.addActionListener(e -> {
			courseMenuframe.dispose();
			renameCourse();
		});
		
		JButton viewCancelledCourseB = new JButton("View cancelled Course");
		viewCancelledCourseB.setFocusable(false);
		viewCancelledCourseB.setBounds(300, 210, 180, 60);
		viewCancelledCourseB.addActionListener(e -> {
			courseMenuframe.dispose();
			viewCancelledCourse();
		});
		panel.add(viewCancelledCourseB);
		
		
		
		JButton deleteCourseB = new JButton("Delete Course");
		deleteCourseB.setFocusable(false);
		deleteCourseB.setBounds(20, 300, 180, 60);
		deleteCourseB.addActionListener(e -> { 
			courseMenuframe.dispose();
			deleteCourse(); 
		});
		
		
		JButton addBackCancelledCourseB = new JButton("Addback Cancelled Course");
		addBackCancelledCourseB.setFocusable(false);
		addBackCancelledCourseB.setBounds(300, 300, 200, 60);
		addBackCancelledCourseB.addActionListener(e -> { 
			courseMenuframe.dispose();
			addBackCancelledCourse(); 
		});
		panel.add(addBackCancelledCourseB);
		
		
		
		
		JButton goBack = new JButton("Go back");
		goBack.setFocusable(false);
		goBack.setBackground(Color.cyan);
		goBack.setBounds(300, 30, 90, 40);
		goBack.addActionListener(e -> {
			courseMenuframe.dispose();
		});
		
		
		panel.add(createCourseB); 
		panel.add(modifyCourseB);
		panel.add(renameCourseB);
		panel.add(deleteCourseB); 
		panel.add(goBack);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		courseMenuframe.add(panel);
		courseMenuframe.setVisible(true);
	}
	
	
	
	
	
	
	//---------------------------------------------VIEW COURSES GUI---------------------------------------------------------------------------
	
	
	
	
	
	
	void viewCourse() {
		
		coursemanager.readCourseFile();
		
		JFrame viewCourseFrame = new JFrame();
		viewCourseFrame.setSize(700, 600);
		viewCourseFrame.setTitle("Courses List");
		viewCourseFrame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x123456));
		panel.setLayout(null);
		
		
		JTextArea toplabelTA = new JTextArea();
		String idNameS = "\tCourse ID \t\t Course Name\n\n\n";
		
		Font headFont = new Font("Segoe UI Semibold", Font.BOLD, 20);
		coursemanager.uniquecoursename = new ArrayList<String>();

		for (String each: coursemanager.coursenamearr) {
			if (!coursemanager.uniquecoursename.contains(each)) {
				coursemanager.uniquecoursename.add(each);
			}
		}
		for (String each: coursemanager.courseidarr) {
			if (!coursemanager.uniquecidarr.contains(each)) {
				coursemanager.uniquecidarr.add(each);
			}
		}
		for (int i=0; i<coursemanager.uniquecoursename.size(); i++) {
			String x = coursemanager.uniquecoursename.get(i);
			String y = coursemanager.uniquecidarr.get(i);
			
			idNameS = idNameS.concat("\t" +  y + " \t\t " + x + "\n\n");
				
		}
		
		toplabelTA.setText(idNameS);
		toplabelTA.setFont(headFont);
		toplabelTA.setBackground(new Color(176,196,222));
		JScrollPane scrollViewCourse = new JScrollPane(toplabelTA, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollViewCourse.setBounds(5, 5, 675, 450);
		panel.add(scrollViewCourse);
		
		JButton viewCourseBackB = new JButton("Go back");
		viewCourseBackB.setBounds(10, 500, 100, 40);
		viewCourseBackB.setBackground(Color.cyan);
		viewCourseBackB.setFocusable(false);
		viewCourseBackB.addActionListener(e->{
			viewCourseFrame.dispose(); 

		});
		panel.add(viewCourseBackB);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		
		viewCourseFrame.add(panel);
		viewCourseFrame.setVisible(true);
	}
	
	

	//--------------------------------------------------CREATE COURSE GUI--------------------------------------------------------------------------------
	
	
	void createCourse() {
		
		JFrame createCourseFrame = new JFrame();
		createCourseFrame.setTitle("Create course GUI (ADMIN)");
		createCourseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createCourseFrame.setSize(500, 300);
		createCourseFrame.setResizable(false);
		
		JPanel jp = new JPanel();
		jp.setBackground(new Color(0x123456));
		jp.setLayout(null);
		jp.setBounds(0, 0, 490, 290);
		
		
		JLabel yearlabel = new JLabel("Course ID: :");
		yearlabel.setBounds(20, 30, 100, 40);
		yearlabel.setForeground(Color.white);
		jp.add(yearlabel);
		
		JTextField yearText = new JTextField();
		yearText.setBounds(120, 30, 200, 40);
		jp.add(yearText);
		
		JLabel courseNamelabel = new JLabel("Course Name:");
		courseNamelabel.setBounds(20, 100, 100, 40);
		courseNamelabel.setForeground(Color.white);
		jp.add(courseNamelabel);
		
		JTextField courseNameText = new JTextField();
		courseNameText.setBounds(120, 100, 200, 40);
		jp.add(courseNameText);


		JButton create = new JButton("create");
		create.setFocusable(false);
		create.setBackground(Color.cyan);
		create.setBounds(120, 170, 90, 40);
		create.addActionListener(e->{
			String cname = courseNameText.getText().toUpperCase();
			String cid	= yearText.getText().toUpperCase();
			creatingCourse(cname, cid);
		});
		jp.add(create);
		
		JButton ccback = new JButton("Go back");
		ccback.setFocusable(false);
		ccback.setBounds(230, 170, 90, 40);
		ccback.addActionListener(e-> {
			courseMenu();
			createCourseFrame.dispose();
		});
		jp.add(ccback);
		
		jp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		createCourseFrame.add(jp);
		createCourseFrame.setVisible(true);
		
	}
	
	
	

	
	
	//--------------------------------WRITING TO COURSE FILE---------------------------------------------------------------------------------------------------
	
	void creatingCourse(String cname, String cid){
		
		coursemanager.readCourseFile();

		this.courseName = cname;
		this.courseID = cid;
		
		
		int FLAG = 0;
		
		while (FLAG == 0) {
			if (coursemanager.uniquecoursename.contains(courseName)  ||  (coursemanager.uniquecidarr.contains(courseID)) ) {
				JOptionPane.showMessageDialog(null, "Course NAME/ID already exist");
				FLAG = 1;
			}
			
			
			else if (!coursemanager.courseidarr.contains(courseID) && !coursemanager.coursenamearr.contains(courseName)) {
				
				for (int i=0; i<coursemanager.courseidarr.size(); i++) {
					
					if (!coursemanager.courseidarr.get(i).equals(courseID) && !coursemanager.coursenamearr.get(i).equals(courseName)) {
						coursemanager.coursenamearr.add(courseName);
						coursemanager.courseidarr.add(courseID);
						
					
						//----------WRITE TO COURSE FILE--------------
						//---------------------------------------------
						coursemanager.writeCourseFile();
						// -------------------------------------------
						JOptionPane.showMessageDialog(null, "Course successfully created!");
						FLAG = 1;
						break;
					}
					
					else {
						JOptionPane.showMessageDialog(null, "EXISTS ALREADY!, NOT ADDED.");
						FLAG  = 1;
					}
				}
			}
		}
	}
	
	
	
	
	
	
	//--------------------------------------------------------------------MODIFY COURSE GUI-----------------------------------------------------------------------------------------------------------
	
	
	void modifyCourse() {
		
		coursemanager.readCourseFile();
		
		JFrame modifyCourseFrame = new JFrame();
		modifyCourseFrame.setTitle("Modify Course GUI (ADMIN)");
		modifyCourseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		modifyCourseFrame.setSize(600, 400);
		modifyCourseFrame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x123456));
		panel.setBounds(0,0, 600, 400);
		panel.setLayout(null);
		
		JLabel oldCidL = new JLabel("Old course ID:");
		oldCidL.setBounds(20, 30, 120, 40);
		oldCidL.setForeground(Color.white);
		
		
		JLabel cNameL = new JLabel("Course Name:");
		cNameL.setBounds(20, 90, 120, 40);
		cNameL.setForeground(Color.white);
		
		JTextField newCidTF = new JTextField();
		newCidTF.setEditable(false);
		newCidTF.setBounds(200, 90, 200, 40);
		
		
		
		
		//------------------------- course id combo box----------------------------------------------------------
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> courseIDcombo = new JComboBox<String>();
		courseIDcombo.setBounds(200, 30, 200, 40);
		for (int o=0; o< coursemanager.courseidarr.size(); o++) {
			if (!elements.contains(coursemanager.courseidarr.get(o)) ) {
				elements.add(coursemanager.courseidarr.get(o));
				courseIDcombo.addItem(coursemanager.courseidarr.get(o));
			} 
		}
		
		//-------------------------Action Listener for courseID combo box----------------------------------------------------
		courseIDcombo.addActionListener(e->{
			for (int i=0; i<coursemanager.courseidarr.size(); i++) {
				if (coursemanager.courseidarr.get(i).equalsIgnoreCase((String) courseIDcombo.getSelectedItem())) {
					//--------------------displays coursename according to courseid---------------------------------------------
					newCidTF.setText(coursemanager.coursenamearr.get(i));
				}
			}
			
		});
		
		panel.add(courseIDcombo);
		

		JLabel newcidlabel = new JLabel("New Course ID:");
		newcidlabel.setBounds(20, 150, 120, 40);
		newcidlabel.setForeground(Color.white);
		JTextField newcidtext = new JTextField();
		newcidtext.setBounds(200, 150, 200, 40);

		JButton modify = new JButton("Modify");
		modify.setBounds(200, 210, 80, 40);
		modify.setBackground(Color.cyan);
		modify.setFocusable(false);
		modify.addActionListener(e->{
			String oldcid = (String) courseIDcombo.getSelectedItem();
			String newcname = newCidTF.getText().toUpperCase();
			String newcid = newcidtext.getText().toUpperCase();
			modifyingCourse(oldcid, newcname, newcid);
		});
		
		JButton mcback = new JButton("Go back");
		mcback.setFocusable(false);
		mcback.setBounds(320, 210, 80, 40);
		mcback.addActionListener(e->{
			courseMenu();
			modifyCourseFrame.dispose();
		});
		
		panel.add(oldCidL);
		panel.add(newcidlabel);
		panel.add(newcidtext);
		panel.add(cNameL);
		panel.add(newCidTF);

		panel.add(modify);
		panel.add(mcback);
		
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		modifyCourseFrame.add(panel);
		modifyCourseFrame.setVisible(true);
		
	}
	
	
	
	//------------------------------------------------------------Modifying INFORMATION IN COURSE FILE-----------------------------------------------------------------------------------------------------
	
	void modifyingCourse(String oldcid, String newcname, String newcid) {
		
		
		coursemanager.readCourseFile();
		
		this.courseID = newcid;
		this.courseName = newcname;
		int flag = 0;
		
		while(flag == 0){
			
			
			//----------CONDITION FOR CHECKING OF ALREADY EXISTING COURSE ID----------------------------
			if (coursemanager.courseidarr.contains(courseID) ) {
				JOptionPane.showMessageDialog(null, "Theres a course similar to your new course ID ");
				flag = 1;
			}
			
			// ----if course do not exist----writes to course arrays and then to course file
			
			else if(!coursemanager.courseidarr.contains(courseID) ) {	
				
				
				// ------if new course do not exist in course file then the place whereold course id exist is replaced by new course id
				for (int i=0; i<coursemanager.courseidarr.size(); i++) {
					if (coursemanager.courseidarr.get(i).equals(oldcid) && !coursemanager.courseidarr.contains(courseID)) {
						coursemanager.courseidarr.set(i, courseID);
					}
				}
						
						
				//-------writes to course file----------------------------
				coursemanager.writeCourseFile();
				//-------------------------------------------------------
				
				JOptionPane.showMessageDialog(null, "Course successfully Modified");
				
			}
			flag = 1;
		}
	}
	


	
	
	
	// 0--------------------------------------------------------RENAME COURSE GUI-------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	void renameCourse() {
		
		
		coursemanager.readCourseFile();
		
		JFrame renameCourseFrame = new JFrame();
		renameCourseFrame.setTitle("Rename course GUI (ADMIN)");
		renameCourseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		renameCourseFrame.setSize(600, 400);
		renameCourseFrame.setResizable(false);
		
		
		JPanel pp = new JPanel();
		pp.setBackground(new Color(0x123456));
		pp.setBounds(0,0, 600, 400);
		pp.setLayout(null);
		
		//		course name
		JLabel oldcnamelabel = new JLabel("Course Name");
		oldcnamelabel.setForeground(Color.white);
		oldcnamelabel.setBounds(20, 90, 120, 40);
		pp.add(oldcnamelabel);
		
		JTextField s = new JTextField();
		s.setEditable(false);
		s.setBounds(200, 90, 200, 40);
		pp.add(s);
		
		
		// course id
		JLabel deletecid = new JLabel("Course ID: ");
		deletecid.setBounds(20, 30, 120, 40);
		deletecid.setForeground(Color.white);
		pp.add(deletecid);
		
		
		//--------------------COURSE ID COMBO------------------------------
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> courseIDcombo = new JComboBox<String>();
		courseIDcombo.setBounds(200, 30, 200, 40);
		for (int o=0; o< coursemanager.courseidarr.size(); o++) {
			if (!elements.contains(coursemanager.courseidarr.get(o)) ) {
				elements.add(coursemanager.courseidarr.get(o));
				courseIDcombo.addItem(coursemanager.courseidarr.get(o));
			} 
		}
		
		//--------------------COURSE ID COMBO ACTION LISTENER------------------------------
		courseIDcombo.addActionListener(e-> {
			for (int o=0; o< coursemanager.coursenamearr.size(); o++) {
				if (coursemanager.courseidarr.get(o).equals((String)courseIDcombo.getSelectedItem())) {
					s.setText(coursemanager.coursenamearr.get(o));
				} 
			}
		});
		pp.add(courseIDcombo);


		
		JLabel newcnamelabel = new JLabel("New Course Name:");
		newcnamelabel.setBounds(20, 150, 120, 40);
		newcnamelabel.setForeground(Color.white);
		JTextField newcnametext = new JTextField();
		newcnametext.setBounds(200, 150, 200, 40);
		
		
		JButton rename = new JButton("Rename");
		rename.setBounds(200, 210, 90, 40);
		rename.setBackground(Color.cyan);
		rename.addActionListener(e -> {
			String changefromid = (String) courseIDcombo.getSelectedItem();
			String changefromname = s.getText();
			String changeto = newcnametext.getText().toUpperCase();;
			renamingCourse(changefromid, changefromname, changeto);
		});
		 
		JButton rcback = new JButton("Go back");
		rcback.setBounds(310, 210, 90, 40);
		rcback.setFocusable(false);
		rcback.addActionListener(e-> {
			courseMenu();
			renameCourseFrame.dispose();
		
		});
		
		pp.add(oldcnamelabel);
		pp.add(newcnamelabel);
		pp.add(newcnametext);
		pp.add(rename);
		pp.add(rcback);
		
		pp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		renameCourseFrame.add(pp);
		renameCourseFrame.setVisible(true);

	}
	
	
	
	
	//--------------------------------------------------------------------RENAMING COURSE IN COPURSE FILE----------------------------------------------------------------------------------------------------------------------------------
	
	
	
	void renamingCourse(String cid, String oldcname, String newcname) {
		
		coursemanager.readCourseFile();
		
		int FLAG = 0;
		
		while(FLAG == 0){ 
			
			

			// rename course in arraylist and course file

			for (int i=0; i<coursemanager.courseidarr.size(); i++) {
				
				
				
				// --------CHECKS IF COURSE ID AND NAME ARE VALID TO RENAME-------------------------------- 
				if (coursemanager.courseidarr.get(i).equals(cid) && !coursemanager.coursenamearr.get(i).equals(newcname)) {
						
					coursemanager.coursenamearr.set(i, newcname);
						
					
					//-----------------------WRITING TO COURSE FILE--------------------------------------------------------------
					coursemanager.writeCourseFile();
					//-----------------------------------------------------------------------------------------------------------
					
					
					//----------------------- change in course files----------------------
					renameCourseInModuleFile(cid, oldcname, newcname);
					//--------------------------------------------------------------------
					// -----------------------change in tutor file------------------------
					renameCourseInTutorFile(oldcname, newcname);
					//--------------------------------------------------------------------
					// -----------------------change in student file----------------------
					renameCourseInStudentFile(oldcname, newcname);
					//--------------------------------------------------------------------
						
					FLAG = 1;
					break;
				}
			}

				FLAG=1;
			}
	}
	 
	
	
	
	// ---------------------------------------------------------RENAME COURSE in module file	-------------------------------------------------------------------------------------------------------------
	
	void renameCourseInModuleFile(String cid, String oldcname, String newcname) {
		//  rename course in arraylist and module file
		
		modulemanager.readModuleFile();
			
		for (int i=0; i< modulemanager.moduleidarr.size(); i++) {
			  // RENAME WHERE COURSE NAME MATCH  
			if ( modulemanager.modulecoursearr.get(i).equals(oldcname)) {
						
				modulemanager.modulecoursearr.set(i, newcname);
			}
		}
					
		//--------WRITING TO MODULE FILE-------------------------
		modulemanager.writeModuleFile();
		//-------------------------=====-------------------------
		
	}
	
	

	
	

	
	// --------------------------------------------RENAME COURSE IN TUTOR FILE-------------------------------------------------------------------------------------------------------------------------
	
	
	
	void renameCourseInTutorFile(String oldcoursename, String newcname) {
		
		coursemanager.readCourseFile();
		
		tutormanager.readTutorFile();
		


		this.courseName = newcname;
		
		int flag = 0;
		
		
		while(flag == 0) {
			
			if(tutormanager.tutorcoursearr.contains(oldcoursename)){	
				// RENAME COURSE WHERE OLD COURSENAME MATCH
				for (int i=0; i< tutormanager.tutoridarr.size(); i++) {
					if ( tutormanager.tutorcoursearr.get(i).equalsIgnoreCase(oldcoursename)) {

						 tutormanager.tutorcoursearr.set(i, courseName);
						
					}
				}
				
				//-------------WRITING TO TUTOR FILE-------------------------------
				tutormanager.writeTutorFile();
				//----------------------------------------------------------------
			} 
			
			flag = 1;
			

		}
	}
	
	
	
	//-------------------------------------------------------------RENAMING COURSE IN STUDENTS FILE-------------------------------------------------
	
	
	void renameCourseInStudentFile(String oldcname, String newsc) {

		studentmanager.readStudentFile();

		this.courseName = newsc;
		
		int flag = 0;
		while(flag == 0){
			 
		
			for (int i=0; i<studentmanager.studentidarr.size(); i++) {
				// CONDITION WHERE COURSE NAME MATCH
				if (studentmanager.studentcoursearr.get(i).equalsIgnoreCase(oldcname)) {
					studentmanager.studentcoursearr.set(i, courseName);
				}
			}
			
			//-----------------------WRITING TO STUDENT FILE--------------------------------------
			studentmanager.writeStudentFile();
			//--=---------------------------------------------------------------------------------
			
			flag = 1;
			
			JOptionPane.showMessageDialog(null, "COURSE SUCCESSFULLY RENAMED");
				
		}		
	}
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-------------------------------------------------------DELETE COURSE GUI----------------------------------------------------------------------------------------------
	
	void deleteCourse() {
		
		coursemanager.readCourseFile();
		
		JFrame deleteCourseFrame = new JFrame();
		deleteCourseFrame.setTitle("Delete course GUI (ADMIN)");
		deleteCourseFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		deleteCourseFrame.setSize(600, 400);
		deleteCourseFrame.setResizable(false);
		
		
		
		
		JPanel PANEL = new JPanel();
		PANEL.setBackground(new Color(0x123456));
		PANEL.setBounds(0,0, 600, 400);
		PANEL.setLayout(null);
		
		
		//		course name
		JLabel oldcnamelabel = new JLabel("Course Name");
		oldcnamelabel.setForeground(Color.white);
		oldcnamelabel.setBounds(20, 90, 120, 40);
		PANEL.add(oldcnamelabel);
		
		JTextField s = new JTextField();
		s.setBounds(200, 90, 200, 40);
		PANEL.add(s);
		
		
		// course id
		JLabel deletecid = new JLabel("Course ID: ");
		deletecid.setBounds(20, 30, 120, 40);
		deletecid.setForeground(Color.white);
		
		
		
		// ------COURSE ID COMBO BOX=========================================================
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> COURSEIDCOMBO = new JComboBox<String>();
		COURSEIDCOMBO.setBounds(200, 30, 200, 40);
		for (int o=0; o< coursemanager.courseidarr.size(); o++) {
			if (!elements.contains(coursemanager.courseidarr.get(o)) ) {
				elements.add(coursemanager.courseidarr.get(o));
				COURSEIDCOMBO.addItem(coursemanager.courseidarr.get(o));
			} 
		}
		
		// ------COURSE ID COMBO BOX ACTION LISTENER=============================================
		COURSEIDCOMBO.addActionListener(e-> {
			for (int o=0; o< coursemanager.coursenamearr.size(); o++) {
				if (coursemanager.courseidarr.get(o).equals((String)COURSEIDCOMBO.getSelectedItem())) {
					s.setText(coursemanager.coursenamearr.get(o));
				} 
			}
		});
		PANEL.add(COURSEIDCOMBO);

		

		
		JButton delete = new JButton("Delete");
		delete.setBounds(200, 150, 90, 40);
		delete.setBackground(Color.cyan);
		delete.setFocusable(false);
		delete.addActionListener(e -> {
			String todeleteid = (String) COURSEIDCOMBO.getSelectedItem();
			String todeletename = s.getText();
			// ----------CALLL FOR FUNCTION TO DELETE FROM COURSE FILE-------
			deletingCourse(todeleteid, todeletename);
		});
		
		JButton dcback = new JButton("Go back");
		dcback.setBounds(310, 150, 90, 40);
		dcback.setFocusable(false);
		dcback.addActionListener(e->{
			courseMenu();
			deleteCourseFrame.dispose();
		});
		
		PANEL.add(deletecid);
		PANEL.add(delete);
		PANEL.add(dcback);
		
		PANEL.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		deleteCourseFrame.add(PANEL);
		deleteCourseFrame.setVisible(true);
	}
	
	
	
	
	
	//--------------------------------------------------deleting from course file--------------------------------------------------------------------------------------------------------------------
	
	
	
	
	void deletingCourse(String did, String dname) {
		
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		
		this.courseID = did;
		this.courseName = dname;
		
		int flag = 0;
		
		
		
		//----- CONDITION TO CHECK IF MODULE IS ACTIVE IN THAT COURSE -------- IF YES --- FLAG = 2 ----
		for (int i=0; i< modulemanager.modulecoursearr.size(); i++) {
			if (modulemanager.modulecoursearr.get(i).equalsIgnoreCase(courseName) && modulemanager.modulestatusarr.get(i).equalsIgnoreCase("A")) {	
				flag = 2;
				break;
			}
		}
		
		
		
		/// ----------IF MODULE INACTIVE IN THAT COURSE----DELETES IT     ------
		while(flag == 0) {
			
					
			for (int i=0; i<coursemanager.coursenamearr.size(); i++) {
					// -------IF  COURSE ID MATCHES REMOVE THAT ID AND NAME IN THAT INDEX---------------------------	
				if (coursemanager.courseidarr.get(i).equalsIgnoreCase(courseID)) {
	
					coursemanager.courseidarr.remove(i);
					coursemanager.coursenamearr.remove(i);
							break;
							
				}
			}
				
			
						
			//------writing to COURSE FILE----------------------------------
			coursemanager.writeCourseFile();
			//-----------------------------------------------------------------
			
			deleteFromModuleFile(courseName); 
			
			flag = 1;	
			break;
	
		}
		
		
		 // if modules are active in that course
		if (flag == 2) {   
			JOptionPane.showMessageDialog(null, "ERROR! There are module currently active in this course");
		}
	}

	
	
	
	
	//----------------------------------------------------- deleting from module file -----------------------------------------------------------------------------------------
	
	
	
	void deleteFromModuleFile(String cname) {
		
		modulemanager.readModuleFile();
		
		this.courseName = cname;
		
		
		int FLAG = 0;
		
		
		while(FLAG == 0) {
			
			
			for (int i=0; i<modulemanager.modulecoursearr.size(); i++) {
				// ----ALL THE MODULES ARE DELETED IN THAT COURSE----------------
				if (modulemanager.modulecoursearr.get(i).equalsIgnoreCase(courseName)) {	
					modulemanager.moduleidarr.remove(i);
					modulemanager.modulenamearr.remove(i);
					modulemanager.modulecoursearr.remove(i);
					modulemanager.modulelevelarr.remove(i);
					modulemanager.modulesemarr.remove(i);  
					modulemanager.modulestatusarr.remove(i);
					break;
				}
			}
					
							 
			//-----------WRITING TO MODULE FILE---------------------
			modulemanager.writeModuleFile();
			//------------------------------------------------------
			
			JOptionPane.showMessageDialog(null, "Course successfully deleted!");
							
			FLAG = 1;
		}
		
	}
	
	
	
	
	
	
	
	
	//----------------------------------------------CANCEL COURSE GUI---------------------------------------------------------------
	
	
	
	void cancelCourse() {
		
		JFrame deleteCourseFrame = new JFrame();
		deleteCourseFrame.setTitle("Cancel course GUI (ADMIN)");
		deleteCourseFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		deleteCourseFrame.setSize(600, 400);
		deleteCourseFrame.setResizable(false);
		
		
		
		
		JPanel PANEL = new JPanel();
		PANEL.setBackground(new Color(0x123456));
		PANEL.setBounds(0,0, 600, 400);
		PANEL.setLayout(null);
		
		
		//		course name
		JLabel oldcnamelabel = new JLabel("Course Name");
		oldcnamelabel.setForeground(Color.white);
		oldcnamelabel.setBounds(20, 90, 120, 40);
		PANEL.add(oldcnamelabel);
		
		JTextField s = new JTextField();
		s.setBounds(200, 90, 200, 40);
		PANEL.add(s);
		
		
		// course id
		JLabel deletecid = new JLabel("Course ID: ");
		deletecid.setBounds(20, 30, 120, 40);
		deletecid.setForeground(Color.white);
		
		
		
		// ---------------------course id combo box-------------------------------------
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> COURSEIDCOMBO = new JComboBox<String>();
		COURSEIDCOMBO.setBounds(200, 30, 200, 40);
		for (int o=0; o< coursemanager.courseidarr.size(); o++) {
			if (!elements.contains(coursemanager.courseidarr.get(o)) ) {
				elements.add(coursemanager.courseidarr.get(o));
				COURSEIDCOMBO.addItem(coursemanager.courseidarr.get(o));
			} 
		}
		
		//course id combo box action listener-------------------------------------
		COURSEIDCOMBO.addActionListener(e-> {
			for (int o=0; o< coursemanager.coursenamearr.size(); o++) {
				if (coursemanager.courseidarr.get(o).equals((String)COURSEIDCOMBO.getSelectedItem())) {
					// displaying course name on click of course id----------------
					s.setText(coursemanager.coursenamearr.get(o));
				} 
			}
		});
		PANEL.add(COURSEIDCOMBO);

		

		
		JButton delete = new JButton("Cancel");
		delete.setBounds(200, 150, 90, 40);
		delete.setBackground(Color.cyan);
		delete.setFocusable(false);
		delete.addActionListener(e -> {
			String tocancelid = (String) COURSEIDCOMBO.getSelectedItem();
			String tocancelname = s.getText();
			//----------------- call for canceling the course method---------------
			cancellingCourse(tocancelid, tocancelname);
		});
		
		JButton dcback = new JButton("Go back");
		dcback.setBounds(310, 150, 90, 40);
		dcback.setFocusable(false);
		dcback.addActionListener(e->{
			courseMenu();
			deleteCourseFrame.dispose();
		});
		
		PANEL.add(deletecid);
		PANEL.add(delete);
		PANEL.add(dcback);
		
		PANEL.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		deleteCourseFrame.add(PANEL);
		deleteCourseFrame.setVisible(true);
	}
	
	
	
	
	//----------------------------------------------CANCELING THE COURSE AND WRITING IN CANCELLED COURSE . TXT FILE---------------------------------------------------
	
	
	
	void cancellingCourse(String id, String name) {
		
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		coursemanager.readCancelledCourse();
		studentmanager.readStudentFile();
		
		
		
		this.courseID = id;
		this.courseName = name;
		
		int flag = 0;
		
		
		//-----CHECK FOR STUDENT IN THAT COURSE------IF STUDENT EXIST IN THAT COURSE----FLAG = 2
		for (int p=0; p< studentmanager.studentidarr.size(); p++) {
			if (studentmanager.studentcoursearr.get(p).equals(name)) {
				flag = 2;
				break;
			}
		}
		
		//------------------------- if course not active--------------------------------------------
		while(flag == 0) {
			
					
			for (int i=0; i<coursemanager.coursenamearr.size(); i++) {
				
				// WHERE ID MATCHES, REMOVES FROM COURSE FILE AND STORES THE SAME INFO IN CANCELLED COURSE FILE
				
				if (coursemanager.courseidarr.get(i).equalsIgnoreCase(courseID)) {
							
					coursemanager.cancelledcourseidarr.add(coursemanager.courseidarr.get(i));
					coursemanager.cancelledcoursenamearr.add(coursemanager.coursenamearr.get(i));
					
					coursemanager.courseidarr.remove(i);
					coursemanager.coursenamearr.remove(i);
							
					break;
							
				}
			}
				
			
			
			
			//---------writing to course file--------------------------------------------
			coursemanager.writeCourseFile();
			//---------------------------------------------------------------------------
			
			//--------------cancelled course file----------------------------------------
			coursemanager.writeCancelledCourseFile();
			//---------------------------------------------------------------------------
		
			
			// cancels module-> makes all module inactive-------------------------------
			cancellingModule(name);
			
			flag = 1;	
			break;
	
		}
		
		
		if (flag == 2) {
			JOptionPane.showMessageDialog(null, "Students are currently in this course. Cannot Cancel");
		}
		
	}
	
	
	
	//------------------------------------------------------DISPLAY CANCELLED COURSES-----------------------------------------------------------------------
	
	
	
	
	void viewCancelledCourse() {
		
		coursemanager.readCourseFile();
		coursemanager.readCancelledCourse();
		
		
		JFrame viewCourseFrame = new JFrame();
		viewCourseFrame.setSize(700, 600);
		viewCourseFrame.setTitle("Courses List");
		viewCourseFrame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x123456));
		panel.setLayout(null);
		
		
		JTextArea jj = new JTextArea();
		
		String z = "\tCourse ID \t\tCourse Name\n\n\n";
		
		Font font1 = new Font("Segoe UI Semibold", Font.BOLD, 20);

		for (int i=0; i< coursemanager.cancelledcoursenamearr.size(); i++) {
			String x = coursemanager.cancelledcoursenamearr.get(i);
			String y = coursemanager.cancelledcourseidarr.get(i);
			
			z = z.concat("\t" +  y + " \t\t " + x + "\n\n");
				
		}
		
		
		jj.setText(z);
		jj.setFont(font1);
		jj.setBackground(new Color(176,196,222));
		JScrollPane scroll12 = new JScrollPane(jj, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll12.setBounds(5, 5, 675, 450);
		panel.add(scroll12);
		
		JButton vcback = new JButton("Go back");
		vcback.setBounds(10, 500, 100, 40);
		vcback.setBackground(Color.cyan);
		vcback.setFocusable(false);
		vcback.addActionListener(e->{
			viewCourseFrame.dispose(); 
			courseMenu();

		});
		panel.add(vcback);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		
		viewCourseFrame.add(panel);
		viewCourseFrame.setVisible(true);
	}
	
	
	
	
	
	//-------------------------------------ADDING BACK CANCELLED COURSES  GUI ---------------------------------------------------------------------------
	
	
	
	
	void addBackCancelledCourse() {
		
		
		coursemanager.readCancelledCourse();
		
		
		JFrame deleteCourseFrame = new JFrame();
		deleteCourseFrame.setTitle("Add back course GUI (ADMIN)");
		deleteCourseFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		deleteCourseFrame.setSize(600, 400);
		deleteCourseFrame.setResizable(false);
		
		
		
		
		JPanel PANEL = new JPanel();
		PANEL.setBackground(new Color(0x123456));
		PANEL.setBounds(0,0, 600, 400);
		PANEL.setLayout(null);
		
		
		//		course name
		JLabel oldcnamelabel = new JLabel("Course Name");
		oldcnamelabel.setForeground(Color.white);
		oldcnamelabel.setBounds(20, 90, 120, 40);
		PANEL.add(oldcnamelabel);
		
		JTextField s = new JTextField();
		s.setBounds(200, 90, 200, 40);
		PANEL.add(s);
		
		
		// course id
		JLabel deletecid = new JLabel("Course ID: ");
		deletecid.setBounds(20, 30, 120, 40);
		deletecid.setForeground(Color.white);
		
		
		// --- cancelled course id combo box---------------------------------------------------
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> cancelledCourseIDcombo = new JComboBox<String>();
		cancelledCourseIDcombo.setBounds(200, 30, 200, 40);
		for (int o=0; o< coursemanager.cancelledcourseidarr.size(); o++) {
			if (!elements.contains(coursemanager.cancelledcourseidarr.get(o)) ) {
				elements.add(coursemanager.cancelledcourseidarr.get(o));
				cancelledCourseIDcombo.addItem(coursemanager.cancelledcourseidarr.get(o));
			} 
		}
		
		// ------------cancelled course id combo action listener-=---------------------------------
		cancelledCourseIDcombo.addActionListener(e-> {
			for (int o=0; o< coursemanager.cancelledcoursenamearr.size(); o++) {
				if (coursemanager.cancelledcourseidarr.get(o).equals((String)cancelledCourseIDcombo.getSelectedItem())) {
					s.setText(coursemanager.cancelledcoursenamearr.get(o));
				} 
			}
		});
		PANEL.add(cancelledCourseIDcombo);

		

		
		JButton delete = new JButton("Add Back");
		delete.setBounds(200, 150, 90, 40);
		delete.setBackground(Color.cyan);
		delete.setFocusable(false);
		delete.addActionListener(e -> {
			String addbackid = (String) cancelledCourseIDcombo.getSelectedItem();
			String addbackname = s.getText();
			// ----------------------call for method that adds the cancelled course back in course file--------------------
			
			addingBackCancelledCourse(addbackid, addbackname);
		
		});
		
		JButton dcback = new JButton("Go back");
		dcback.setBounds(310, 150, 90, 40);
		dcback.setFocusable(false);
		dcback.addActionListener(e->{
			courseMenu();
			deleteCourseFrame.dispose();
		});
		
		PANEL.add(deletecid);
		PANEL.add(delete);
		PANEL.add(dcback);
		
		PANEL.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		deleteCourseFrame.add(PANEL);
		deleteCourseFrame.setVisible(true);
	}
	
	
	
	//----------------------------------ADDING BACK COURSES FROM CANCELLED COURSE FILE --------------------------------------------------------------------------------------------------------------------
	
	
	void addingBackCancelledCourse(String id, String name) {
		
		
		coursemanager.readCancelledCourse();
		coursemanager.readCourseFile();
		
		int done =1;
		
		for (int i=0; i<coursemanager.cancelledcourseidarr.size(); i++) {
			if (coursemanager.cancelledcourseidarr.get(i).equals(id) && !coursemanager.courseidarr.contains(id) && !coursemanager.coursenamearr.contains(name)) {
				
				coursemanager.courseidarr.add(id);
				coursemanager.coursenamearr.add(name);
				
				coursemanager.cancelledcourseidarr.remove(i);
				coursemanager.cancelledcoursenamearr.remove(i);
				done=0;
				break;
			}
		}
		
		
		if (done == 1) {
			JOptionPane.showMessageDialog(null, "Course already exist with same name or id");
		}
		
		
		else {
			///////course file
		
			//-------------------WRITING TO COURSE FILE AFTER ADDING THE COURSE----------------------------------
			coursemanager.writeCourseFile();
			//---------------------------------------------------
			
			
			
			//-----------------WRITING TO cancelled course file AFTER REMOVING THE COURSE TO BE ADDED -----------------
			coursemanager.writeCancelledCourseFile();
			//--------------------------------------------------------------------
			
			addingBackModules(name);
			
		}
	
		
		
	}
	
	

	
	
	// -------------------cancelling module id in module file---------making the modules in that course inactive--------------------------------------------------------
	
	
	
		void cancellingModule(String coursename) {
			
			coursemanager.readCourseFile();
			modulemanager.readModuleFile();
			
			
			this.courseName = coursename;
			
			

			int flag = 0;
			
			
			while(flag == 0){
			
				for (int i=0; i < modulemanager.moduleidarr.size(); i++) {
					// set status of that modukle to inactive
					if (modulemanager.modulecoursearr.get(i).equals(courseName)) {
						modulemanager.modulestatusarr.set(i, "I");
					}
				}
				
				
				//-----writing to module file after setting their status to inactive-----------------------
				modulemanager.writeModuleFile();
				//----------------------------------------------------------------------------------------
					
				JOptionPane.showMessageDialog(null, "Course successfully Cancelled");
				flag = 1;
			} 
		}
		
		
		
		
		
		
		
		//--------------------------ADDING BACK MODULES THAT IS MAKING THE STATUS OF MODULES ACTIVE------------------------------------------------------------------------
		
		
		
		
		void addingBackModules(String coursename) {
			
			coursemanager.readCourseFile();
			modulemanager.readModuleFile();
			
			
			this.courseName = coursename;
			
			

			int flag = 0;
			
			
			while(flag == 0){
			
				for (int i=0; i < modulemanager.moduleidarr.size(); i++) {
					// SETTING STATUS OF MODULE AS ACTIVE IN THAT COURSE WHICH IS BEING ADDED
					if (modulemanager.modulecoursearr.get(i).equals(courseName)) {
						modulemanager.modulestatusarr.set(i, "A");
					}
				}
				
				
				//------------------------ WRITING TO UPDATED MODULE FILE------------------------
				modulemanager.writeModuleFile();
				//------------------------------------------------------------------------
					
				JOptionPane.showMessageDialog(null, "Course successfully added back.");
				flag = 1;
			} 
		}
		
	

}
