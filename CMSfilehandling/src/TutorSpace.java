
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class TutorSpace {

	
	AdminControl adminController = new AdminControl();
	
	Course course = new Course();
	
	Module module = new Module();  
	ModuleManager mm = new ModuleManager();
	
	Tutor tutor = new Tutor(); 
	TutorFileManager tm = new TutorFileManager();
	
	Student student = new Student();
	StudentFileManager sm = new StudentFileManager();
 
	 

	  
	private String tutorName = "", tutorlevel, moduleID, moduleSemester;
	

	private JScrollPane scroll;
	private JFrame vsframe;
	private JTextArea op, vmtext;
	private JPanel panel;
	private String courseName;
	


	
	Font font = new Font("Segoe UI Semibold", Font.BOLD, 16);
	
	

//---------------------------------------------tutor dash-------------------------------------------------------------------------------------
	
	
	protected void TutorDash(String name) {
		
		
		
		this.tutorName = name;
		
		JFrame tutordashframe = new JFrame();
		tutordashframe.setVisible(true);
		tutordashframe.setSize(800, 600);
		tutordashframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		tutordashframe.setTitle("Tutor dashboard");
		

		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x123456));
		panel.setOpaque(true);
		panel.setLayout(null);
		
		
		
		ImageIcon headerimg = null;
		
		try {
			headerimg  = new ImageIcon(getClass().getResource("/p/tutor.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Picture not found!  " + e, null, JOptionPane.INFORMATION_MESSAGE);
		}
        
		if (headerimg != null ) {
			JLabel p = new JLabel();
			p.setBounds(500, 120, 200, 200);
			Image scaled =  headerimg.getImage().getScaledInstance(p.getWidth(), p.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon sc = new ImageIcon(scaled);
			p.setIcon(sc);
			p.setOpaque(true);
			p.setBackground(Color.green);
			panel.add(p);
			tutordashframe.setIconImage(headerimg.getImage());
		}
		
		
		
		
		// 
		JButton coursebutton = new JButton("View Courses");
		coursebutton.setFocusable(false);
		coursebutton.setBounds(20, 20, 200, 70);
		coursebutton.addActionListener(e -> course.viewCourse());
		panel.add(coursebutton);
		
		JButton modulebutton = new JButton("View Modules");
		modulebutton.setFocusable(false);
		modulebutton.setBounds(250, 20, 200, 70);
		modulebutton.addActionListener(e -> module.viewModule());
		panel.add(modulebutton);
		
		
		JButton m = new JButton("Enrolled Modules");
		m.setFocusable(false);
		m.setBounds(500, 20, 200, 70);
		m.addActionListener(e -> viewMyModule(tutorName));
		panel.add(m);
		

		//
		JButton tutorbutton = new JButton("View Tutors");
		tutorbutton.setFocusable(false);
		tutorbutton.setBounds(20, 120, 200, 70);
		tutorbutton.addActionListener(e-> viewTutorastutor(tutorName));
		panel.add(tutorbutton);
		
		
		JButton studentbutton = new JButton("View Students");
		studentbutton.setFocusable(false);
		studentbutton.setBounds(250, 120, 200, 70);
		studentbutton.addActionListener(e-> viewStudentsastutor(tutorName));
		panel.add(studentbutton);
		
		
		//
		JButton managestudentbutton = new JButton("Grade Students");
		managestudentbutton.setFocusable(false);
		managestudentbutton.setBounds(20, 220, 200, 70);
		managestudentbutton.addActionListener(e-> student.gradeStudents(tutorName));
		panel.add(managestudentbutton);
		
		
		JButton viewresultbutton = new JButton("View Result");
		viewresultbutton.setFocusable(false);
		viewresultbutton.setBounds(250, 220, 200, 70);
		viewresultbutton.addActionListener(e->adminController.viewIndividualResult());
		panel.add(viewresultbutton);
		
		
		
		//
		JButton mmr = new JButton("View My Module Result");
		mmr.setFocusable(false);
		mmr.setBounds(20, 320, 200, 70);
		mmr.addActionListener(e-> viewMymoduleResult(tutorName));
		panel.add(mmr);
		
		JButton mr = new JButton("Settings");
		mr.setFocusable(false);
		mr.setBounds(250, 320, 200, 70);
		mr.addActionListener(e-> tutor.tutorSettings(tutorName));
		panel.add(mr);
		
		
		//
		JButton signoutbutton = new JButton("Sign out");
		signoutbutton.setBackground(Color.cyan);
		signoutbutton.setFocusable(false);
		signoutbutton.setBounds(600, 340, 100, 50);
		signoutbutton.addActionListener(e -> {
			tutordashframe.dispose();
			new SignIn();
		});
		panel.add(signoutbutton);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		tutordashframe.add(panel);
		tutordashframe.setVisible(true);
		
		
	}
	
	
	
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	// tutor's module only
	private void viewMyModule(String name) {
		
		mm.readModuleFile();
		tm.readTutorFile();
		
		this.tutorName = name;
		
		JFrame f1 = new JFrame();
		f1.setSize(900, 700);
		f1.setTitle("Enrolled Module list (TUTOR'S VIEW)");
		f1.setResizable(false);
		 
		 
		JPanel vspanel = new JPanel();
		vspanel.setLayout(null);
		vspanel.setBackground(new Color(0x123456));

		Font font = new Font("Segoe UI Semibold", Font.BOLD, 20);
		JTextArea toplabel = new JTextArea("Tutor Name:   " + tutorName);
		toplabel.setBounds(05, 20, 870, 90);
		toplabel.setEditable(false);
		toplabel.setFont(font);
		toplabel.setForeground(Color.BLACK);
		toplabel.setBackground(new Color(176,196,222));
		vspanel.add(toplabel);
		
		JTextArea vttext = new JTextArea();
		
		String modulec = null;
		String info="";	
		int flag = 0;
		
		Font font1 = new Font("Segoe UI Semibold", Font.PLAIN, 18);
		while (flag == 0) {
		
			for (int i=0; i< tm.tutoridarr.size(); i++ ) {
				
				if (tm.tutornamearr.get(i).equalsIgnoreCase(tutorName)) {
					
					String moduleid = tm.tutormodulearr.get(i);
					modulec = tm.tutorcoursearr.get(i);
					
					String modulename = "";
					// getting module name from id
					for (int p = 0; p< mm.moduleidarr.size(); p++) {
						
						if (mm.moduleidarr.get(p).equalsIgnoreCase(moduleid)) {
							
							modulename = mm.modulenamearr.get(p);

						}
					} 
				
					info = info.concat(
							"\n" + 
							" Level: " + tm.tutorlevelarr.get(i) + "\t" + "Sem " + tm.tutorsemarr.get(i) +
							"\t" + tm.tutormodulearr.get(i) + "\t" + modulename +
							"\n"  
							);
					flag = 1;
					
				}
			}
			

			flag = 1;
		}
		
		toplabel.setText("Tutor Name  : " + tutorName + "\n" + "Course          : " + modulec);
		
		
		vttext.setText(info);
		vttext.setBackground(new Color(176,196,222));
		vttext.setEditable(false);
		vttext.setFont(font1);
		JScrollPane sc = new JScrollPane(vttext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sc.setBounds(05, 120, 870, 420);
		
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
		

// ****************************************************************************************************************************************************************************************
	
	
	private void viewTutorastutor(String name) {

		
		mm.readModuleFile();
		tm.readTutorFile();
		
		
		this.tutorName = name;
		
		vsframe = new JFrame();
		vsframe.setSize(1000, 700);
		vsframe.setTitle("Tutor list (TUTOR'S VIEW)");
		vsframe.setResizable(false);
		
		 
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0x123456));

		Font font4 = new Font("Segoe UI Semibold", Font.BOLD, 23);
		String yotext = "Tutors Information in my Module";
		JTextArea toplabel = new JTextArea(yotext);
		toplabel.setBounds(05, 20, 350, 40);
		toplabel.setEditable(false);
		toplabel.setFont(font4);
		toplabel.setForeground(Color.white);
		toplabel.setBackground(new Color(0x123456));
		panel.add(toplabel);
		  
		JLabel mdoulenaam = new JLabel(); 
		mdoulenaam.setBounds(500, 20, 380, 40);
		mdoulenaam.setForeground(Color.white);
		panel.add(mdoulenaam);
		
		// module of teacher combobox
		JComboBox<String> tutmoduleidcombo = new JComboBox<String>();
		tutmoduleidcombo.setBounds(400, 20, 80, 40);
		panel.add(tutmoduleidcombo);
		for (int o=0; o< tm.tutormodulearr.size(); o++) {
			if (tm.tutornamearr.get(o).equalsIgnoreCase(tutorName)) {
				tutmoduleidcombo.addItem(tm.tutormodulearr.get(o));
			}
		}
		tutmoduleidcombo.addActionListener(e->{
			
			for (int o=0; o< mm.moduleidarr.size(); o++) {
				if (mm.moduleidarr.get(o).equalsIgnoreCase((String) tutmoduleidcombo.getSelectedItem()) ) {
					mdoulenaam.setText(mm.modulenamearr.get(o)); 
				}  
			}
			
			panel.remove(scroll);
			String mid = (String) tutmoduleidcombo.getSelectedItem();
			
			String sem = "";
			
			for (int p=0; p< tm.tutoridarr.size(); p++) {
				if (tm.tutormodulearr.get(p).equals(mid)) {
					sem = tm.tutorsemarr.get(p);
				}
			}
			selectedTutorsFromModule(mid, sem);
		});


		JButton vtback = new JButton("Go back");
		vtback.setBounds(10, 580, 100, 40);
		vtback.setBackground(Color.cyan);
		vtback.setFocusable(false);
		vtback.addActionListener(e->vsframe.dispose());
		panel.add(vtback);
		
		vmtext = new JTextArea();
		scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		
		
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		vsframe.add(panel);
		vsframe.setVisible(true);
		
	}

	
	
	
	// ******************************************************************************************************************************************

	private void selectedTutorsFromModule(String mid, String sem) {
		
		mm.readModuleFile();
		tm.readTutorFile();
		
		
		this.moduleID = mid;
		this.moduleSemester = sem;
		
		String info="";

		int flag = 0;
		
		
		while (flag == 0) {
		
			for (int i=0; i< tm.tutoridarr.size(); i++ ) {
				
				if (tm.tutormodulearr.get(i).equalsIgnoreCase(moduleID) && tm.tutorsemarr.get(i).equalsIgnoreCase(moduleSemester)) {

					info = info.concat(
					        " Tutor ID\t:   " + tm.tutoridarr.get(i) + "\n" + 
							" Tutor Name\t:   " +		tm.tutornamearr.get(i) + "\n" +
							" Tutor contact\t:   " +		tm.tutorcontactarr.get(i) + "\n" + 
							" Tutor email\t:   " +		tm.tutoremailarr.get(i) + "\n" + 
							" Tutor address\t:   " +		tm.tutoraddressarr.get(i) + "\n" + 
							" Course\t:   " +		tm.tutorcoursearr.get(i) + "\n" + 
							" Level\t:   " +		tm.tutorlevelarr.get(i) +  "\n" + 
							" Semester\t:   " + 	tm.tutorsemarr.get(i) +"\n" + 
							" Module\t:   " +		tm.tutormodulearr.get(i)+ 
							"\n*****************************************************" + " \n\n");
					flag = 1;
					
				}
			}
			

			flag = 1;
		}
		
		
		vmtext.setText(null);
		vmtext.setText(info);
		vmtext.setBackground(new Color(176,196,222));
		vmtext.setEditable(false);
		vmtext.setFont(font);
		scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(05, 70, 970, 480);
		panel.add(scroll);
		
		vsframe.add(panel);
		vsframe.setVisible(true);
	}
	
	
	
	
	
	
	// *******************************************************************************************************************
	

	// Students as TUTOR
	
	private void viewStudentsastutor(String name) {
		
		
		mm.readModuleFile();
		tm.readTutorFile();
		sm.readStudentFile();
		
		
		this.tutorName = name;

		
		
		vsframe = new JFrame();
		vsframe.setSize(1000, 700);
		vsframe.setTitle("Students List (Tutor's view)");
		vsframe.setResizable(false);		
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0x123456));
		
		
		JLabel sl = new JLabel(); 
		sl.setBounds(500, 20, 380, 40);
		sl.setForeground(Color.white);
		panel.add(sl);

		String yotext = "Students Information in Module: ";
		JTextArea toplabel = new JTextArea(yotext);
		toplabel.setForeground(Color.white);
		toplabel.setBackground(new Color(0x123456));
		toplabel.setBounds(05, 20, 300, 40);
		toplabel.setEditable(false);
		toplabel.setFont(font);
		vsframe.add(toplabel);
		
		
		
		// module of teacher combobox
				JComboBox<String> tutmodulecombo = new JComboBox<String>();
				tutmodulecombo.setBounds(350, 20, 120, 40);
				panel.add(tutmodulecombo);
				
				
				for (int o=0; o<  tm.tutormodulearr.size(); o++) {
					if (tm.tutornamearr.get(o).equalsIgnoreCase(tutorName)) {
						tutmodulecombo.addItem(tm.tutormodulearr.get(o));
					}
				}
				
				 
				// to get module name
				tutmodulecombo.addActionListener(e->{
					
					for (int o=0; o< mm.moduleidarr.size(); o++) {
						if (mm.moduleidarr.get(o).equalsIgnoreCase((String) tutmodulecombo.getSelectedItem()) ) {
							sl.setText(mm.modulenamearr.get(o)); 
						} 
					}
					
					panel.remove(scroll);
					String mid = (String) tutmodulecombo.getSelectedItem();
					String sem = "";
					for (int p=0; p< tm.tutoridarr.size(); p++) {
						if (tm.tutormodulearr.get(p).equals(mid)) {
							sem = tm.tutorsemarr.get(p);
						}
					}
					 selectedStudentsFromModule(mid, sem);
				});
		
		
		vmtext = new JTextArea();
		scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		

		JButton vtback = new JButton("Go back");
		vtback.setBounds(10, 600, 100, 40);
		vtback.setBackground(Color.cyan);
		vtback.setFocusable(false);
		vtback.addActionListener(e->vsframe.dispose());
		panel.add(vtback);
		
		
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		vsframe.add(panel);
		vsframe.setVisible(true);
		

	}
	
	
	// *****************************************************************************************************************************************************************
	
	
	private void selectedStudentsFromModule(String mid, String sem) {
		
		this.tutorlevel = null;
		this.moduleID = mid; 
		this.moduleSemester = sem;
		
		
		vmtext.setText(null);
		
		String info="s";
		
		// module id from gere (tutor's file)
		
		for (int  i=0; i< tm.tutornamearr.size(); i++ ) {
			if ( tm.tutormodulearr.get(i).equals(moduleID)) {
					this.tutorlevel = tm.tutorlevelarr.get(i);
					this.courseName = tm.tutorcoursearr.get(i);
			}
		}

		
		
		int flag = 0;

		
		while (flag == 0) {
			
			
			// fill in info
		
			for (int i=0; i< sm.studentidarr.size(); i++ ) {
				
				if ( sm.studentlevelarr.get(i).equalsIgnoreCase(tutorlevel) && 
						 sm.studentcoursearr.get(i).equalsIgnoreCase(courseName) && 
						 sm.studentsemarr.get(i).equalsIgnoreCase(moduleSemester) ) {

					info = info.concat(
							"Student Name\t\t:   "+ sm.studentnamearr.get(i) + "\n" + 
							" Student ID\t\t:   "+ sm.studentidarr.get(i) +"\n" + 
							" Student level\t\t:   "+ sm.studentlevelarr.get(i) +  "\n" +
							" Student phone NO\t:   "+ sm.studentcontactarr.get(i) + "\n" +
							" Student email\t\t:   "+ sm.studentemailarr.get(i) +"\n"+
							" Student address\t:   "+ sm.studentaddressarr.get(i) + "\n" +
							" Student course\t:   "+ sm.studentcoursearr.get(i) + "\n" +
							" Student Semester\t:   " +  sm.studentsemarr.get(i) + 
							
							"\n****************************************************\n");
				}
			}
			
			
			
			vmtext.setText(info);
			vmtext.setBackground(new Color(176,196,222));
			vmtext.setEditable(false);
			vmtext.setFont(font);
			scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setBounds(05, 70, 970, 500);
			panel.add(scroll);

			vsframe.add(panel);

			flag=1;

		}
	}

	
	
	
	
	
	
	
	
	
	
	// ***************************************************************************************************************************************************************************************************************
	
	

	//// module's result all
	private void viewMymoduleResult(String name) {
		
		this.tutorName = name;

		vsframe = new JFrame();
		vsframe.setSize(920, 700);
		vsframe.setTitle("Students result of my module");
		vsframe.setBackground(Color.green);
		vsframe.setResizable(false);
		
		panel = new JPanel();
		panel.setBackground(new Color(0x123456));
		panel.setLayout(null);
		
		
		String tutmid = "";
		
		op = new JTextArea();
		op.setBackground(new Color(127,255,212));
		vmtext = new JTextArea();
		scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);
		
		 
		JComboBox<String> sd = new JComboBox<String>();
		sd.setBounds(10, 20, 150, 50);
		panel.add(sd);
		
		// we get tutor's module for each loop
		for (int j=0; j< tm.tutornamearr.size(); j++) {
				
			if (tm.tutornamearr.get(j).equalsIgnoreCase(tutorName)) {
				tutmid = tm.tutormodulearr.get(j);
				String x1 = tutmid;
				sd.addItem(x1);
				sd.setBackground(new Color(230,230,250));
				sd.addActionListener(e-> {
					panel.remove(scroll);
					actionPerformed((String)sd.getSelectedItem());
				});
				panel.add(sd);		
				
			}
		}	


		JButton sinfoback = new JButton("Go back");
		sinfoback.setBounds(20, 580, 90, 40);
		sinfoback.setBackground(Color.cyan);
		sinfoback.setFocusable(false);
		sinfoback.addActionListener(e->vsframe.dispose());

		panel.add(sinfoback);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		vsframe.add(panel);
		vsframe.setVisible(true);
	}
	

	 
	
	// *******************************************************************************************************************
	
	
	private void actionPerformed(String x) {
		
		sm.readMarksFile();
		
		this.moduleID = x;
		
		
		vmtext.setText(null);
		
		font = new Font("Segoe UI Semibold", Font.BOLD, 15);
		String info = "";
			
		for (int  i=0; i< sm.markssid.size(); i++ ) {
				
			if (sm.markssmid.get(i).equalsIgnoreCase(moduleID)) {

				info = info.concat(
						sm.markssname.get(i)+ "  \t " +
								sm.markssid.get(i) +"\t " +
								sm.markssmarks.get(i) +"\t " +
								sm.markssgrade.get(i) +"\t " +
								sm.markssremarks.get(i) +"\n " 
				);
				
			}
		}

		

		String boom = "\tRESULT for Module:   " +moduleID+ "\n" 
				+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\n"
				+" Name\t\t ID\t Marks\t Grade\t Remarks\n"
			+ "-----------------------------------------------------------------------------------------------------------------------------------------------------\n";

		op.setEditable(false);
		op.setBackground(new Color(127,255,212));
		op.setText(boom);
		op.setFont(font);
		op.setBounds(10, 80, 890, 70);
		panel.add(op);
		

		vmtext.setText(info);
		vmtext.setFont(font); 
		vmtext.setBackground(new Color(127,255,212));
		vmtext.setEditable(false);
		
		scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 150, 890, 400);
		panel.add(scroll);
		
		
		vsframe.add(panel);
	
	
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		TutorSpace ts = new TutorSpace();
		ts.TutorDash("PRAKASH KHADKA");
	}

}
