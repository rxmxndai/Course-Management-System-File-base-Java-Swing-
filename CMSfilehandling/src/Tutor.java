

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




public class Tutor  {
	
	 
	
	
	Course course = new Course();
	CourseManager coursemanager = new CourseManager();
	
	Module module = new Module();
	ModuleManager modulemanager = new ModuleManager();

	Student student = new Student();
	
	TutorFileManager tutormanager = new TutorFileManager();
	
	 
	
	
	
	// scrollpane
	private JScrollPane scroll;
	// Strings
	private String tutorID;
	private String tutorName;
	private String tutorlevel;
	private String tutorPassword;
	private String tutorSemester;
	private String tutorContact;
	private String tutorEmail;
	private String tutorAddress;
	
	private String courseName;
	private String courseID;
	
	private String tutormoduleID;	
	
	
	
	//frames
	private static JFrame frame;
	// panels
	private static JPanel panel;
	// textAreas
	private static JTextArea vttext, toplabel;

	
	


		 
	private Font font = new Font("Segoe UI Semibold", Font.BOLD, 16);
	
	
	Tutor(){ 
		
	}
	

//---------------------------------------tutor menu -------------------------------------------------------------------------------------
	
	// tutor Menu 
	
	
	protected void tutorMenu () {
		
		JFrame tutorMenuframe = new JFrame();
		tutorMenuframe.setSize(500, 400);
		tutorMenuframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		tutorMenuframe.setTitle("Tutor management dashboard");
		tutorMenuframe.setResizable(false);
		
		
		JPanel panel = new JPanel(); 
		panel.setBackground(new Color(0x123456));
		panel.setOpaque(true);
		panel.setLayout(null);
		
		JButton ct = new JButton("Add Tutor");
		ct.setBounds(20, 30, 150, 50);
		ct.setFocusable(false); 
		ct.addActionListener(e -> {
			tutorMenuframe.dispose();
			addTutor();
		});
		
		JButton mt = new JButton("Modify Tutor");
		mt.setBounds(20, 120, 150, 50);
		mt.setFocusable(false);
		mt.addActionListener(e -> {
			tutorMenuframe.dispose();
			modifyTutor();
		});
		
		JButton dt = new JButton("Delete Tutor");
		dt.setBounds(20, 210, 150, 50);
		dt.setFocusable(false);
		dt.addActionListener(e -> {
			tutorMenuframe.dispose();
			deleteTutor();
		});
		
		JButton tback = new JButton("Go back");
		tback.setBounds(250, 30, 90, 40);
		tback.setBackground(Color.cyan);
		tback.setFocusable(false);
		tback.addActionListener(e -> {
			tutorMenuframe.dispose();
		});
		panel.add(ct);  
		panel.add(mt);
		panel.add(dt); 
		panel.add(tback);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		tutorMenuframe.add(panel);
		tutorMenuframe.setVisible(true);
	}
	
	
	
	/////////////////////tutor's list/////////////////////////////////////////////////////////////////////////////
	
	
	// tutor list and info
	
	public void viewTutor() {
		
		tutormanager.readTutorFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		
		frame = new JFrame();
		frame.setSize(900, 700);
		frame.setTitle("Tutor List");
		frame.setResizable(false);
		
		
		panel = new JPanel();
		panel.setBackground(new Color(0x123456));
		panel.setLayout(null);
		 
		
		//		course name
		JLabel s = new JLabel();
		s.setForeground(Color.white);
		s.setBackground(new Color(0x123456));
		s.setBounds(550, 20, 100, 40);
		panel.add(s);
		

		// course id
		JLabel deletecid = new JLabel("Course ID: ");
		deletecid.setBounds(20, 30, 120, 40);
		deletecid.setForeground(Color.white);
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> sd = new JComboBox<String>();
		sd.setBounds(300, 20, 200,40);
		for (int o=0; o< coursemanager.courseidarr.size(); o++) {
			if (!elements.contains(coursemanager.courseidarr.get(o)) ) {
				elements.add(coursemanager.courseidarr.get(o));
				sd.addItem(coursemanager.courseidarr.get(o));
			} 
		}
		
		// action listener for selecting particular course
		sd.addActionListener(e-> {
			for (int o=0; o< coursemanager.coursenamearr.size(); o++) {
				if (coursemanager.courseidarr.get(o).equals((String)sd.getSelectedItem())) {
					s.setText(coursemanager.coursenamearr.get(o));
				} 
			}
			
			
			panel.remove(scroll);
			String cid = ((String)sd.getSelectedItem());
			showTutorofthisCourse(cid);
			
		});
		panel.add(sd);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////;

		
		String yotext = "All Tutors Information";
		toplabel = new JTextArea(yotext);
		toplabel.setBounds(05, 30, 870, 40);
		toplabel.setForeground(Color.white);
		toplabel.setBackground(new Color(0x123456));
		toplabel.setEditable(false);
		toplabel.setFont(font);
		panel.add(toplabel);
	
		vttext = new JTextArea();
		vttext.setBackground(new Color(176,196,222));
		
		String info="";
		
		
		for (int  i=0; i< tutormanager.tutoridarr.size(); i++ ) {
			info = info.concat(
			        " Tutor ID\t:   " + tutormanager.tutoridarr.get(i) + "\n" + 
					" Tutor Name\t:   " +		tutormanager.tutornamearr.get(i) + "\n" +
					" Tutor contact\t:   " +		tutormanager.tutorcontactarr.get(i) + "\n" + 
					" Tutor email\t:   " +		tutormanager.tutoremailarr.get(i) + "\n" + 
					" Tutor address\t:   " +		tutormanager.tutoraddressarr.get(i) + "\n" + 
					" Course\t:   " +		tutormanager.tutorcoursearr.get(i) + "\n" + 
					" Level\t:   " +		tutormanager.tutorlevelarr.get(i) +  "\n" + 
					" Semester\t:   " + 	tutormanager.tutorsemarr.get(i) +"\n" + 
					" Module\t:   " +		tutormanager.tutormodulearr.get(i)+ 
					"\n*****************************************************" + " \n\n");
		}
		
		vttext.setText(info);
		vttext.setEditable(false);
		vttext.setFont(font);
		
		scroll = new JScrollPane(vttext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(05, 90, 870, 500);
		panel.add(scroll);
		
		
		


		JButton vtback = new JButton("Go back");
		vtback.setBounds(10, 600, 100, 40);
		vtback.setBackground(Color.cyan);
		vtback.setFocusable(false);
		vtback.addActionListener(e->frame.dispose());
		panel.add(vtback);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		
		frame.add(panel);
		frame.setVisible(true);
	}
	
	

	
	//----------------------------------------tutor list of particular course----------------------------------------------------------------------------------------------------
	
	
	private void showTutorofthisCourse(String cid) {
		
		tutormanager.readTutorFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		
		String info = "";
		
		this.courseID = cid;
		
		
		int flag =0;
		while (flag == 0) {
			if (courseID.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Fill up the course ID textfield" );
				flag =1;
			}
			
			else if (!coursemanager.courseidarr.contains(courseID)) {
				JOptionPane.showMessageDialog(null, "No Course with id: " + courseID);
				flag =1;
			}
			else {
				
				for (int  i=0; i< coursemanager.courseidarr.size(); i++ ) {
					if (coursemanager.courseidarr.get(i).equals(courseID)) {
						this.courseName = coursemanager.coursenamearr.get(i);
					}
				}
				
				
				for (int  i=0; i< tutormanager.tutoridarr.size(); i++ ) {
					if (tutormanager.tutorcoursearr.get(i).equals(courseName)) {
						info = info.concat(
						        " Tutor ID\t:   " + tutormanager.tutoridarr.get(i) + "\n" + 
								" Tutor Name\t:   " +		tutormanager.tutornamearr.get(i) + "\n" +
								" Tutor contact\t:   " +		tutormanager.tutorcontactarr.get(i) + "\n" + 
								" Tutor email\t:   " +		tutormanager.tutoremailarr.get(i) + "\n" + 
								" Tutor address\t:   " +		tutormanager.tutoraddressarr.get(i) + "\n" + 
								" Course\t:   " +		tutormanager.tutorcoursearr.get(i) + "\n" + 
								" Level\t:   " +		tutormanager.tutorlevelarr.get(i) +  "\n" + 
								" Semester\t:   " + 	tutormanager.tutorsemarr.get(i) +"\n" + 
								" Module\t:   " +		tutormanager.tutormodulearr.get(i)+ 
								"\n*****************************************************" + " \n\n");
					}
				}
				vttext.setText(null);
				vttext.setText(info);
				vttext.setEditable(false);
				vttext.setFont(font);
				scroll = new JScrollPane(vttext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setBounds(05, 90, 870, 500);
				panel.add(scroll);
			
				frame.add(panel);
				
				flag=1;
			}
		}
			
	}
	
	

	
	//-----------------------------------------------------ADD TUTOR GUI----------------------------------------------------------------------------------------------------------
	
	
	protected void addTutor() {
		
		tutormanager.readTutorFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		frame = new JFrame();
		frame.setTitle("Add tutor GUI (ADMIN)"); 
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setResizable(false);
		
		
		JPanel jp = new JPanel();
		jp.setBackground(new Color(0x123456));
		jp.setLayout(null);
		
		
		JLabel ntid = new JLabel("New Tutor ID:");
		ntid.setBounds(20, 30, 150, 40);
		ntid.setForeground(Color.white);
		JTextField ntidtext = new JTextField();
		ntidtext.setBounds(200, 30, 200, 40);
		
		
		JLabel ntname = new JLabel("New Tutor Full Name:");
		ntname.setBounds(20, 90, 150, 40);
		ntname.setForeground(Color.white);
		
		JTextField ntnametext = new JTextField();
		ntnametext.setBounds(200, 90, 200, 40);
		
		
		JLabel contact = new JLabel("Tutor's Contact:");
		contact.setBounds(20, 150, 150, 40);
		contact.setForeground(Color.white);
		
		JTextField contacttext = new JTextField();
		contacttext.setBounds(200, 150, 200, 40);
		
		
		JLabel email = new JLabel("Tutor's email:");
		email.setBounds(20, 210, 150, 40);
		email.setForeground(Color.white);

		JTextField emailtext = new JTextField();
		emailtext.setBounds(200, 210, 200, 40);
		
		
		JLabel address = new JLabel("Tutor's Full Address:");
		address.setBounds(20, 270, 150, 40);
		address.setForeground(Color.white);
		
		JTextField addresstext = new JTextField();
		addresstext.setBounds(200, 270, 200, 40);
		
		JLabel cid = new JLabel("Course Name:");
		cid.setBounds(20, 390, 150, 40);
		cid.setForeground(Color.white);
		
		
		JTextArea sdd = new JTextArea();
		sdd.setEditable(false);
		sdd.setBounds(200, 390, 60, 40);
		jp.add(sdd);

		
		
		JLabel mid = new JLabel("Module ID:");
		mid.setBounds(20, 330, 150, 40);
		mid.setForeground(Color.white);
		
		
		JTextArea mt = new JTextArea();
		mt.setEditable(false);
		mt.setBounds(270, 390, 60, 40);
		jp.add(mt);
		
		ArrayList<String> ed = new ArrayList<String>();
		JComboBox<String> midcombo = new JComboBox<String>();
		midcombo.setBounds(200, 330, 200, 40);
		for (int o=0; o< modulemanager.moduleidarr.size(); o++) {
			if (!ed.contains(modulemanager.moduleidarr.get(o)) ) {
				ed.add(modulemanager.moduleidarr.get(o));
				midcombo.addItem(modulemanager.moduleidarr.get(o));
			} 
		}
		
		// midcombo action listener
		midcombo.addActionListener(e-> {
			for (int i=0; i< modulemanager.moduleidarr.size(); i++) {
				if (modulemanager.moduleidarr.get(i).equalsIgnoreCase( (String) midcombo.getSelectedItem())) {
					sdd.setText(modulemanager.modulecoursearr.get(i));
					mt.setText(modulemanager.modulesemarr.get(i));
				}
			}
			
			
			
		});

		jp.add(midcombo);
		
		
		
		JButton crr = new JButton("search");
		crr.setBounds(430, 30, 100, 40);
		crr.setBackground(Color.cyan);
		crr.setFocusable(false);
		crr.addActionListener(e-> {
			for (int i=0; i< tutormanager.tutoridarr.size(); i++ ) {
				if ( tutormanager.tutoridarr.get(i).equals (ntidtext.getText().toUpperCase() ) ) {
					ntnametext.setText(tutormanager.tutornamearr.get(i));
					ntnametext.setEditable(false);
					contacttext.setText(tutormanager.tutorcontactarr.get(i));
					contacttext.setEditable(false);
					emailtext.setText(tutormanager.tutoremailarr.get(i));
					emailtext.setEditable(false);
					addresstext.setText(tutormanager.tutoraddressarr.get(i));
					addresstext.setEditable(false);
					break;
				}
			}
			
		});
		
		jp.add(crr);
		
		
		JButton create = new JButton("create");
		create.setBounds(200, 450, 100, 40);
		create.setBackground(Color.cyan);
		create.setFocusable(false);
		create.addActionListener(e->{
			String ntiid = ntidtext.getText().toUpperCase();
			String ntnname = ntnametext.getText().toUpperCase();
			String ntcontact = contacttext.getText().toUpperCase();
			String ntemail = emailtext.getText().toLowerCase();
			String ntaddress = addresstext.getText().toUpperCase();
			String miid = (String) midcombo.getSelectedItem();
			String coursename =sdd.getText();
			String sem = mt.getText();
			
			addingTutor(ntiid, ntnname, ntcontact, ntemail, ntaddress, miid, coursename, sem);
		});
		
		JButton cmback = new JButton("Go back");
		cmback.setBounds(310, 450, 90, 40);
		cmback.setFocusable(false);
		cmback.addActionListener(e-> {
			frame.dispose();
			tutorMenu ();
		});
		
		jp.add(ntid);
		jp.add(ntidtext);
		jp.add(ntname);
		jp.add(ntnametext);
		jp.add(mid);
		jp.add(cid);
		jp.add(contact);
		jp.add(contacttext);
		jp.add(email);
		jp.add(emailtext);
		jp.add(address);
		jp.add(addresstext);
		jp.add(create);
		jp.add(cmback);
		jp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		frame.add(jp);
		frame.setVisible(true);
	}
	
	
	
	
	
//-----------------------------ADDING TUTOR IN TUTOR FILE---------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	private void addingTutor(String ntid, String ntname, String ntcontact, String ntemail, String ntaddress, String mid, String cname, String sem) {
		
		
		tutormanager.readTutorFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		
		this.tutorID = ntid.toUpperCase();
		this.tutorName = ntname.toUpperCase();
		this.tutorContact = ntcontact.toUpperCase();
		this.tutorEmail = ntemail.toLowerCase();
		this.tutorAddress = ntaddress.toUpperCase();
		this.tutormoduleID = mid.toUpperCase();
		this.courseName = cname.toUpperCase();
		this.tutorSemester = sem;
	
		
		for (int i=0; i< modulemanager.moduleidarr.size(); i++) {
			if (modulemanager.moduleidarr.get(i).equals(tutormoduleID) && modulemanager.modulecoursearr.get(i).equals(courseName)) {
				this.tutorlevel = modulemanager.modulelevelarr.get(i);
			}
		}
		
		
		int flag =0;
		
		while (flag == 0 ) {

				// if tutor id / name exists already
				if (tutormanager.tutoridarr.contains(tutorID) && !tutormanager.tutornamearr.contains(tutorName) ) {
					JOptionPane.showMessageDialog(null, "Tutor id already exist ");
					flag =1;
				}
				
				// checks tutor email valid or not
				else if (tutorEmail.length() < 10  ) {
					JOptionPane.showMessageDialog(null, "Tutor email invalid");
					flag =1;
				}
				
				// checks tutor contact valid or not
				else if ( tutorContact.length() > 10  ) {
					JOptionPane.showMessageDialog(null, "Tutor contact invalid");
					flag =1;
				}
				
				// checks for empty strings passed through add tutor button
				else if ( tutormoduleID.length() == 0 || tutorName.length() ==0 || tutorID.length() == 0 ||
						tutorContact.length() == 0 || tutorEmail.length() ==0  || tutorAddress.length() == 0 ) {
					JOptionPane.showMessageDialog(null, "Please fill all text fields");
					flag =1;
				}
				
				
				// finally if tutor email, id does not exist, create one account and store it in array and file
				
				else  {
					
					
					for (int i=0; i< tutormanager.tutoraddressarr.size(); i++) {
						if (tutormanager.tutoridarr.get(i).equals(tutorID) && tutormanager.tutormodulearr.get(i).equals(tutormoduleID)) {
							JOptionPane.showMessageDialog(null, "Tutor already in the same module");
							flag =1;
							break;
						}
					}

					if (flag == 0) {
						
						tutormanager.tutoridarr.add(tutorID);
						tutormanager.tutornamearr.add(tutorName);
						tutormanager.tutorcontactarr.add(tutorContact);
						tutormanager.tutoremailarr.add(tutorEmail);
						tutormanager.tutoraddressarr.add(tutorAddress);
						tutormanager.tutorpasswordarr.add(tutorName);
						tutormanager.tutormodulearr.add(tutormoduleID);
						tutormanager.tutorcoursearr.add(courseName);
						tutormanager.tutorlevelarr.add(tutorlevel);
						tutormanager.tutorsemarr.add(tutorSemester);		
						
						////////////////////////////////////////////////////////////////////////////////////////////////
						tutormanager.writeTutorFile(); 
						////////////////////////////////////////////////////////////////////////////////////////////////
						
						
						JOptionPane.showMessageDialog(null, "Tutor Sucessfully added in " + courseName );
					}
					
					flag =1;
					break;
				}
				
			}
	}

	
	
	
	//--------------------------------------------------MODIFYING TUTOR BY ADMIN-----------------------------------------------------------------------------
	
	protected void modifyTutor() {
		
		tutormanager.readTutorFile(); 
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		frame = new JFrame();
		frame.setTitle("Modify Tutor GUI");
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setResizable(false);
		
		

		JPanel pp = new JPanel();
		pp.setBackground(new Color(0x123456));
		pp.setLayout(null);
		
		
		
		JLabel tutornamel = new JLabel("Tutor Name:");
		tutornamel.setBounds(20, 150, 150, 40);
		tutornamel.setForeground(Color.white);
		JTextField tutornametf = new JTextField();
		tutornametf.setBounds(200, 150, 200, 40);
		
		
		
		JLabel tutidmodify = new JLabel("Tutor ID to modify:");
		tutidmodify.setBounds(20, 30, 150, 40);
		tutidmodify.setForeground(Color.white);
		
		
		// details
		ArrayList<String> elet = new ArrayList<String>();
		JComboBox<String> prevMoudleCombo = new JComboBox<String>();
		prevMoudleCombo.setBounds(200, 90, 200, 40);


		// tut iod and details
		ArrayList<String> ele = new ArrayList<String>();
		JComboBox<String> tutidcombo = new JComboBox<String>();
		tutidcombo.setBounds(200, 30, 200, 40);
		pp.add(tutidcombo);
		for (int o=0; o< tutormanager.tutoridarr.size(); o++) {
			if (!ele.contains(tutormanager.tutoridarr.get(o)) ) {
				ele.add(tutormanager.tutoridarr.get(o));
				tutidcombo.addItem(tutormanager.tutoridarr.get(o));
			} 
		}
		
		
		
		
		tutidcombo.addActionListener(e-> {
			String tutn = "";
			for (int i=0; i< tutormanager.tutoridarr.size(); i++) {
				if (tutormanager.tutoridarr.get(i).equalsIgnoreCase((String) tutidcombo.getSelectedItem())) {
					tutn = tutormanager.tutornamearr.get(i);
				}
			}
			tutornametf.setText(tutn);
			tutornametf.setEditable(false);
			
			prevMoudleCombo.removeAllItems();
			
			////////////////////////////////////////////////
			for (int o=0; o< tutormanager.tutoridarr.size(); o++) {
				if (!elet.contains(tutormanager.tutormodulearr.get(o)) && tutormanager.tutoridarr.get(o).equals( tutidcombo.getSelectedItem()) ) {
					elet.add(tutormanager.tutormodulearr.get(o));
					prevMoudleCombo.addItem(tutormanager.tutormodulearr.get(o));
				} 
			}
			pp.add(prevMoudleCombo); 

		});
		
		
		
		JLabel ntil = new JLabel("Previous module/s:");
		ntil.setBounds(20, 90, 150, 40);
		ntil.setForeground(Color.white);

		
		
		
		
		JTextArea sdp = new JTextArea();
		sdp.setEditable(false);
		sdp.setBounds(200, 270, 100, 40);
		pp.add(sdp);
		
		JTextArea mt = new JTextArea();
		mt.setEditable(false);
		mt.setBounds(320, 270, 100, 40);
		pp.add(mt);
		
		
		JLabel ntcl = new JLabel("New Tutor Module ID:");
		ntcl.setBounds(20, 210, 150, 40);
		ntcl.setForeground(Color.white);
		
		ArrayList<String> ed = new ArrayList<String>();
		JComboBox<String> moduleIDcombo = new JComboBox<String>();
		moduleIDcombo.setBounds(200, 210, 200, 40);
		for (int o=0; o< modulemanager.moduleidarr.size(); o++) {
			if (!ed.contains(modulemanager.moduleidarr.get(o)) ) {
				ed.add(modulemanager.moduleidarr.get(o));
				moduleIDcombo.addItem(modulemanager.moduleidarr.get(o));
			} 
		}
		
		moduleIDcombo.addActionListener(e->{
			for (int i=0; i< modulemanager.moduleidarr.size(); i++) {
				if (modulemanager.moduleidarr.get(i).equalsIgnoreCase( (String) moduleIDcombo.getSelectedItem())) {
					sdp.setText(modulemanager.modulecoursearr.get(i));
					mt.setText(modulemanager.modulesemarr.get(i));
				}
			}
		});
 
		pp.add(moduleIDcombo);

		
		JLabel cid = new JLabel("New course assign: ");
		cid.setBounds(20, 270, 150, 40);
		cid.setForeground(Color.white);

		JButton modify = new JButton("Modify");
		modify.setBounds(200, 330, 90, 40);
		modify.setBackground(Color.cyan);
		modify.setFocusable(false);
		modify.addActionListener(e->{
			
			String oldid = (String) tutidcombo.getSelectedItem();
			String newtname = tutornametf.getText().toUpperCase();
			String ntmid = (String) moduleIDcombo.getSelectedItem();
			String cname = (String) sdp.getText();
			String sem = mt.getText();
			String prevmid = (String) prevMoudleCombo.getSelectedItem();
			
			modifyingTutor(oldid, newtname, ntmid, cname, sem, prevmid);
		});
		
		JButton mtback = new JButton("Go back");
		mtback.setBounds(310, 330, 90, 40);
		mtback.setFocusable(false);
		mtback.addActionListener(e-> {
			frame.dispose();
			tutorMenu ();
		});
		
		
		pp.add(tutidmodify);
		pp.add(ntil);
		pp.add(tutornamel);
		pp.add(tutornametf);
		pp.add(ntcl);
		pp.add(cid);
		pp.add(modify);
		pp.add(mtback);
		pp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		frame.add(pp);
		frame.setVisible(true);
	}
	
	
	
	
	
	//------------------------------------------------MODIFY TUTOR IN TUTOR FILE-------------------------------------------------------------------------------
	
	private void modifyingTutor(String otid,  String ntname, String mid, String cname, String sem, String prevmid) {
		
		
		
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		tutormanager.readTutorFile();
		
		this.tutorID = otid;
		this.tutorName = ntname; 
		this.tutormoduleID = mid;
		this.courseName = cname;
		this.tutorSemester = sem;

		int flag = 0;
		for (int i=0; i< modulemanager.moduleidarr.size(); i++) {
			if (modulemanager.moduleidarr.get(i).equalsIgnoreCase(tutormoduleID)) {
				this.tutorlevel = modulemanager.modulelevelarr.get(i);
			}
		}
		
		while(flag == 0){
			
	
			for (int i=0; i<tutormanager.tutoridarr.size(); i++) {
				
				if (tutormanager.tutoridarr.get(i).equalsIgnoreCase(otid) && tutormanager.tutormodulearr.get(i).equals(prevmid)) {
					
					tutormanager.tutoridarr.set(i, tutorID);
					tutormanager.tutornamearr.set(i, tutorName);
					tutormanager.tutorpasswordarr.set(i, tutorName);
					tutormanager.tutormodulearr.set(i, tutormoduleID);
					tutormanager.tutorcoursearr.set(i, courseName); 
					tutormanager.tutorlevelarr.set(i, tutorlevel);
					tutormanager.tutorsemarr.set(i, tutorSemester);	
					break;
				}
			}
			
			
			/////////////////////////////////////////////////////////
			tutormanager.writeTutorFile();
			//////////////////////////////////////////////////////////
			
			
			JOptionPane.showMessageDialog(null, "Tutor info updated!");
				
			flag = 1;
		}
	}
	
	
	
	
	//-------------------------------------------------------------DELETE TUTOR GUI----------------------------------------------------------------------------------------------

	protected void deleteTutor () {
		
		tutormanager.readTutorFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		
		frame = new JFrame();
		frame.setTitle("Remove Tutor GUI");
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setResizable(false);
		
		JPanel pp = new JPanel();
		pp.setBackground(new Color(0x123456));
		pp.setLayout(null);
		
		// tutor name
		JLabel tn = new JLabel("Tutor Name");
		tn.setBounds(20, 100, 120, 50);
		tn.setForeground(Color.white);
		
		JTextField tntext = new JTextField();
		tntext.setEditable(false);
		tntext.setBounds(200, 100, 200, 50);
		
		JLabel tid = new JLabel("Tutor ID:");
		tid.setBounds(20, 30, 120, 50);
		tid.setForeground(Color.white);
		
		
		
		// details
		ArrayList<String> elet = new ArrayList<String>();
		JComboBox<String> prevModules = new JComboBox<String>();
		prevModules.setBounds(200, 250, 200, 40);
		pp.add(prevModules);
		
		
		////tutor id///
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> sd = new JComboBox<String>();
		sd.setBounds(200, 30, 200, 50);
		for (int o=0; o< tutormanager.tutoridarr.size(); o++) {
			if (!elements.contains(tutormanager.tutoridarr.get(o)) ) {
				elements.add(tutormanager.tutoridarr.get(o));
				sd.addItem(tutormanager.tutoridarr.get(o));
			} 
		}
		sd.addActionListener(e -> {
			for (int o=0; o< tutormanager.tutoridarr.size(); o++) {
				if (tutormanager.tutoridarr.get(o).equalsIgnoreCase((String) sd.getSelectedItem()) ) {
					tntext.setText(tutormanager.tutornamearr.get(o)); 
				} 
			}
			
			for (int o=0; o< tutormanager.tutoridarr.size(); o++) {
				if (!elet.contains(tutormanager.tutormodulearr.get(o)) && tutormanager.tutoridarr.get(o).equals( sd.getSelectedItem()) ) {
					elet.add(tutormanager.tutormodulearr.get(o));
					prevModules.addItem(tutormanager.tutormodulearr.get(o));
				} 
			}
			pp.add(prevModules); 
			
		});

		pp.add(sd);


		
		JButton delete = new JButton("Remove tutor");
		delete.setBounds(200, 170, 100, 50);
		delete.setBackground(Color.cyan);
		delete.setFocusable(false);
		delete.addActionListener(e -> {
			String todeleteid = (String ) sd.getSelectedItem();
			String todeletename = tntext.getText().toUpperCase(); 
			String todeleteMid = (String )prevModules.getSelectedItem();
			
			removingTutor(todeleteid, todeletename, todeleteMid);
		});
		
		JButton dtback = new JButton("Go back");
		dtback.setBounds(310, 170, 90, 50);
		dtback.setFocusable(false);
		dtback.addActionListener(e-> {
			frame.dispose();
			tutorMenu ();
		});
		
		pp.add(tid);
		pp.add(tn);
		pp.add(tntext);
		pp.add(delete);
		pp.add(dtback);
		pp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		frame.add(pp);
		frame.setVisible(true);
	}
	
	
	
	
	
	
	//---------------------------------------------------------DELETING COURSE RELATED TUTORS------------------------------------------------------------------------------------------------------
	
	
	private void removingTutor(String did, String dname, String mid) {
		
		
		tutormanager.readTutorFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		
		this.tutorID = did;
		this.tutorName = dname;
		
		int flag = 0;
		while(flag == 0){
			if(tutormanager.tutornamearr.contains(tutorName) && tutormanager.tutoridarr.contains(tutorID)) {			
				
				for (int i=0; i<tutormanager.tutornamearr.size(); i++) {
					
					if (tutormanager.tutornamearr.get(i).equalsIgnoreCase(tutorName) && tutormanager.tutoridarr.get(i).equals(tutorID) && tutormanager.tutormodulearr.get(i).equals(mid)) {
						tutormanager.tutoridarr.remove(i);
						tutormanager.tutornamearr.remove(i);
						tutormanager.tutorcontactarr.remove(i);
						tutormanager.tutoremailarr.remove(i);
						tutormanager.tutoraddressarr.remove(i);
						tutormanager.tutorpasswordarr.remove(i);
						tutormanager.tutormodulearr.remove(i);
						tutormanager.tutorcoursearr.remove(i);
						tutormanager.tutorlevelarr.remove(i);
						tutormanager.tutorsemarr.remove(i);
					} 
				}
				////////////////////////////////////////////////
				tutormanager.writeTutorFile();
				/////////////////////////////////////////////
				
				JOptionPane.showMessageDialog(null, "Tutor successfully removed from module !" + mid);
				
				
				flag = 1;
			}
			
	
		}
	
	}	
	
	
	
	
	
	
	//---------------------------------------------settings gui tutor---------------------------------------------------------------------------------------------------
	
	
	protected void tutorSettings(String name) { 
		
		
		tutormanager.readTutorFile();
		
		this.tutorName = name;
		
		for (int i=0; i< tutormanager.tutoridarr.size(); i++) {
			
			if (tutormanager.tutornamearr.get(i).equalsIgnoreCase(tutorName)) {
				
				this.tutorID = tutormanager.tutoridarr.get(i);
				this.tutorEmail = tutormanager.tutoremailarr.get(i);
				this.tutorPassword = tutormanager.tutorpasswordarr.get(i);
				this.tutorContact = tutormanager.tutorcontactarr.get(i);
				this.tutorAddress = tutormanager.tutoraddressarr.get(i);
			}
			
		}
		
		JFrame msframe = new JFrame();
		msframe.setTitle("Change Settings GUI tutor");
		msframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		msframe.setSize(500, 500);
		msframe.setResizable(false);
		
		JPanel pp = new JPanel();
		pp.setBackground(new Color(0x123456));
		pp.setLayout(null);
		
		
		JLabel osidl = new JLabel("Your ID: ");
		osidl.setBounds(20, 30, 150, 40);
		osidl.setForeground(Color.white);
		JTextField osidtext = new JTextField(tutorID);
		osidtext.setEditable(false);
		osidtext.setBounds(200, 30, 200, 40);
		
		 
		JLabel nsc = new JLabel("Password: :");
		nsc.setBounds(20, 90, 150, 40);
		nsc.setForeground(Color.white);
		JTextField nsct = new JTextField(tutorPassword);
		nsct.setEditable(false);
		nsct.setBounds(200, 90, 200, 40);
		
		
		JLabel nsid = new JLabel("New Email: ");
		nsid.setBounds(20, 150, 150, 40);
		nsid.setForeground(Color.white);
		JTextField nsidt = new JTextField(tutorEmail);
		nsidt.setBounds(200, 150, 200, 40);
		
		
		JLabel nsn = new JLabel("New Contact NO: ");
		nsn.setBounds(20, 210, 150, 40);
		nsn.setForeground(Color.white);
		JTextField nsnt = new JTextField(tutorContact);
		nsnt.setBounds(200, 210, 200, 40);
		
		JLabel nsl = new JLabel("New Address");
		nsl.setBounds(20, 270, 150, 40);
		nsl.setForeground(Color.white);
		JTextField nslt = new JTextField(tutorAddress);
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
			String newEmail = nsidt.getText().toLowerCase();
			String contact = nsnt.getText().toUpperCase();
			String oldpassword = nsct.getText();
			String address = nslt.getText().toUpperCase();
			String newpass = nsllt.getText();
			updateTutorInfo(oldsid, oldpassword, newEmail, address, contact, newpass);
		});
		
		JButton mtback = new JButton("Go back");
		mtback.setBounds(310, 390, 90, 40);
		mtback.setFocusable(false);
		mtback.addActionListener(e-> {
			msframe.dispose(); 
		});
		
		
		pp.add(osidl);
		pp.add(osidtext);
		pp.add(nsc);
		pp.add(nsct);
		pp.add(nsid);
		pp.add(nsidt);
		pp.add(nsn);
		pp.add(nsnt);
		pp.add(nsl);
		pp.add(nslt);
		pp.add(nsll);
		pp.add(nsllt);
		pp.add(modify);
		pp.add(mtback);
		pp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		msframe.add(pp);
		msframe.setVisible(true);
		
	}
	
	
	
	
	
	// ----------------------------------------------------------updating settings in tutor file--------------------------------------------------------------------------------------------------------
	
	
	
	private void updateTutorInfo(String oldid, String oldpass, String nemail, String naddress, String contact, String newpass) {
		
		tutormanager.readTutorFile();
		coursemanager.readCourseFile();
		modulemanager.readModuleFile();
		
		
		this.tutorAddress = naddress;
		this.tutorContact = contact;
		this.tutorEmail = nemail;
	
		int flag = 0;
		while(flag == 0){
			 
			
			if (oldid.isEmpty() || oldpass.isEmpty() || nemail.isEmpty() || naddress.isEmpty() || contact.isEmpty() || newpass.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill up all the text fields");
				flag =1;
			}
			
			else if (!tutormanager.tutorpasswordarr.contains(oldpass)) {
				JOptionPane.showMessageDialog(null, "Password do not match");
				flag =1;
			}
			
			
			else if(tutormanager.tutoridarr.contains(oldid)){			
				for (int i=0; i<tutormanager.tutoridarr.size(); i++) {
					if (tutormanager.tutoridarr.get(i).equalsIgnoreCase(oldid) && tutormanager.tutorpasswordarr.get(i).equals(oldpass)) {

						tutormanager.tutoremailarr.set(i, tutorEmail);
						tutormanager.tutorpasswordarr.set(i, newpass);
						tutormanager.tutoraddressarr.set(i, tutorAddress);
						tutormanager.tutorcontactarr.set(i, tutorContact);
					}
				}
				////////////////////////////////////////////////////////////////////
				tutormanager.writeTutorFile();
				////////////////////////////////////////////////////////////////////
				
				
				JOptionPane.showMessageDialog(null, "Tutor info updated!");
				flag = 1;
			} 
		}
	}
	 
	
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	

}
