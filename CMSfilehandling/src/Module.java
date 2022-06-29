

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

public class Module {
	
	TutorFileManager tm = new TutorFileManager();
	CourseManager c = new CourseManager(); 
	ModuleManager mm = new ModuleManager();
	
	
	private String moduleName, courseName, moduleSemester, moduleStatus;
	private String moduleID;
	private String moduleLevel;
	

	private JFrame vmframe;
	private JPanel panel;
	private JTextArea toplabel, vmtext; 
	private JScrollPane scroll;

	
	 
	
	Module() {
		
	}

 
	
	//------------------------------------------------------------------Module Menu -----------------------------------------------------------------------------------------------------------------------
	


	protected void moduleMenu() {
		
		vmframe  = new JFrame();
		vmframe.setSize(600, 500);
		vmframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		vmframe.setTitle("Module management dashboard");
		vmframe.setResizable(false);
		
		JPanel panel = new JPanel(); 
		panel.setBackground(new Color(0x123456));
		panel.setOpaque(true);
		panel.setLayout(null);
		
		JButton cm = new JButton("Create Module");
		cm.setBounds(20, 30, 180, 60);
		cm.setFocusable(false);
		cm.addActionListener(e -> {
			vmframe.dispose();
			createModule();
		});
		
		JButton mm = new JButton("Modify Module");
		mm.setBounds(20, 120, 180, 60);
		mm.setFocusable(false);
		mm.addActionListener(e -> {
			vmframe.dispose();
			modifyModule();
		});
		
		JButton rm = new JButton("Rename Module");
		rm.setBounds(20, 210, 180, 60);
		rm.setFocusable(false);
		rm.addActionListener(e -> {
			vmframe.dispose();
			renameModule();
		});
		
		JButton dm = new JButton("Delete Module");
		dm.setBounds(20, 300, 180, 60);
		dm.setFocusable(false);
		dm.addActionListener(e -> {
			vmframe.dispose();
			deleteModule();
		});
		
		JButton mback = new JButton("Go back");
		mback.setBackground(Color.cyan);
		mback.setBounds(300, 30, 90, 40);
		mback.setFocusable(false);
		mback.addActionListener(e -> {
			vmframe.dispose();
		});
		panel.add(cm); 
		panel.add(mm);
		panel.add(rm);
		panel.add(dm); 
		panel.add(mback);
		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		vmframe.add(panel);
		vmframe.setVisible(true);
	}
	
	
	
	
//---------------------------------------------------------------CREATE MODULE GUI----------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	protected void createModule() {
		
		c.readCourseFile();
		mm.readModuleFile();
		
		JFrame cmframe = new JFrame();
		cmframe.setTitle("Create module GUI"); 
		cmframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		cmframe.setSize(600, 650);
		cmframe.setResizable(false);
		
		
		
		JPanel jp = new JPanel();
		jp.setBackground(new Color(0x123456));
		jp.setLayout(null);
		jp.setBounds(0, 0, 490, 290);
		
		
		JLabel midlabel = new JLabel("New Module ID:");
		midlabel.setBounds(20, 30, 100, 40);
		midlabel.setForeground(Color.white);
		JTextField midtext = new JTextField();
		midtext.setBounds(200, 30, 200, 40);
		
		JLabel mnlabel = new JLabel("New Module Name:");
		mnlabel.setBounds(20, 100, 120, 40);
		mnlabel.setForeground(Color.white);
		JTextField mntext = new JTextField();
		mntext.setBounds(200, 100, 200, 40);
		
		
		JLabel myear = new JLabel("Module Level: :");
		myear.setBounds(20, 170, 120, 40);
		myear.setForeground(Color.white);
		
		String[] elements = {"3", "4", "5", "6"};
		JComboBox<String> myeartext = new JComboBox<String>(elements);
		myeartext.setBounds(200, 170, 200, 40);
		jp.add(myeartext);
		
		
		JLabel cidlabel = new JLabel("Course Name to add in:");
		cidlabel.setBounds(20, 240, 200, 40);
		cidlabel.setForeground(Color.white);
	
		ArrayList<String> elements1 = new ArrayList<String>();
		JComboBox<String> sd = new JComboBox<String>();
		sd.setBounds(200, 240, 200, 40);
		jp.add(sd); 
		for (int o=0; o< c.courseidarr.size(); o++) {
			if (!elements1.contains(c.coursenamearr.get(o)) ) {
				elements1.add(c.coursenamearr.get(o));
				sd.addItem(c.coursenamearr.get(o));
			} 
		}
		
		
		
		JLabel cidl = new JLabel("Semester");
		cidl.setBounds(20, 320, 200, 40);
		cidl.setForeground(Color.white);
		jp.add(cidl);
		
		String[] el= {"1", "2"};
		JComboBox<String> mt = new JComboBox<String>(el);
		mt.setBounds(200, 320, 120, 40);
		jp.add(mt);

		JButton create = new JButton("create");
		create.setBounds(200, 400, 90, 40);
		create.setBackground(Color.cyan);
		create.setFocusable(false);
		create.addActionListener(e->{
			String mname = mntext.getText().toUpperCase();
			String mid = midtext.getText().toUpperCase();
			String cname = (String) sd.getSelectedItem();
			String ml = (String) myeartext.getSelectedItem();
			String ms = (String) mt.getSelectedItem();
			creatingModule(mid, mname, cname, ml, ms);
		});
		
		JButton cmback = new JButton("Go back");
		cmback.setBounds(310, 400, 90, 40);
		cmback.setFocusable(false);
		cmback.addActionListener(e-> {
			cmframe.dispose();
			moduleMenu();
		});
		
		jp.add(midlabel);
		jp.add(midtext);
		jp.add(mnlabel);
		jp.add(mntext);
		jp.add(myear);
		jp.add(myeartext);
		jp.add(cidlabel);
		jp.add(create);
		jp.add(cmback);
		
		jp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		cmframe.add(jp);
		cmframe.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
// ------------------------------------------------------------------------------------------------CREATING MODULE --------------------------------------------------------------------------------------------------
	
	private void creatingModule(String mid, String mname, String cname, String my, String ms) {
		
		c.readCourseFile();
		mm.readModuleFile();
		
		this.moduleID = mid;
		this.moduleName = mname;
		this.courseName = cname;
		this.moduleLevel = my;
		this.moduleSemester = ms;
		this.moduleStatus = "I";
		
		int flag = 0;
		while (flag == 0) {
			
			if (mm.moduleidarr.contains(moduleID) || mm.modulenamearr.contains(moduleName)) {
				JOptionPane.showMessageDialog(null, "Module NAME / ID already exist");
				flag = 1;
			}
			
			else if (!mm.moduleidarr.contains(moduleID) && !mm.modulenamearr.contains(moduleName) ) {
				
				mm.moduleidarr.add(moduleID);
				mm.modulenamearr.add(moduleName);
				mm.modulecoursearr.add(courseName);
				mm.modulelevelarr.add(moduleLevel);
				mm.modulesemarr.add(moduleSemester);
				mm.modulestatusarr.add(moduleStatus);
				
				//////////////////////////////////////////////
				mm.writeModuleFile();
				//////////////////////////////////////////////
				
				JOptionPane.showMessageDialog(null, "Module successfully created!");
				
				flag = 1;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
///----------------------------------------------------------------------------------------------------VIEW MODULE GUI---------------------------------------------------------------------------------------------
	
	
	public void viewModule () {

		mm.readModuleFile();
		
		vmframe = new JFrame();
		vmframe.setSize(1000, 700);
		vmframe.setTitle("All Modules List");
		vmframe.setResizable(false);
		
		
		panel = new JPanel();
		panel.setBackground(new Color(0x123456));
		panel.setBounds(0, 0, 1000, 700);
		panel.setLayout(null);
		
		
		// top label
		Font font = new Font("Segoe UI Semibold", Font.PLAIN, 15);
		toplabel = new JTextArea("Module ID \t Level \tSemester \tStatus \tModule Name");
		toplabel.setFont(font);
		toplabel.setBounds(05, 70, 970, 40);
		toplabel.setForeground(Color.BLACK);
		toplabel.setBackground(new Color(176,196,222));
		toplabel.setEditable(false);
		panel.add(toplabel);
		
		JButton vmback = new JButton("Go back");
		vmback.setBounds(10, 610, 100, 30);
		vmback.setFocusable(false);
		vmback.setBackground(Color.cyan);
		vmback.addActionListener(e-> {
			vmframe.dispose();
		});
		
		panel.add(vmback);
		

		JComboBox<String> sd = new JComboBox<String>();
		sd.setBounds(20, 10, 200, 50);
		panel.add(sd);


		for (int i=0; i < mm.uniquecoursename.size(); i++) {			
			String x = mm.uniquecoursename.get(i);
			sd.addItem(x);
			sd.addActionListener(e-> {
				panel.remove(scroll);
				actionPerformed((String) sd.getSelectedItem());});
			panel.add(sd);
		}
		
		
				
				
		vmtext = new JTextArea();
		scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.add(scroll);

		panel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		vmframe.add(panel);
		vmframe.setVisible(true);
	}
	 
	
	
	
	
	
	
/// ------------------------------------------------------------------------------------------------------------ACTION PERFORMED-----------------------------------------------------------------------------------
	
	
	private void actionPerformed(String e) {
		
		vmtext.setText(null);
		vmtext.setBackground(new Color(176,196,222));
		
		
		String info="";
		Font font = new Font("Segoe UI Semibold", Font.PLAIN, 15);

		
		if (mm.uniquecoursename.contains(e)) {
			
			for (int  i=0; i< mm.moduleidarr.size(); i++ ) {
				if (mm.modulecoursearr.get(i).equals(e)) {
					info = info.concat(mm.moduleidarr.get(i) + "\tLevel  " +  mm.modulelevelarr.get(i) +  "\t Sem "+ mm.modulesemarr.get(i) +  "\t" + 
				mm.modulestatusarr.get(i)  + "\t" + mm.modulenamearr.get(i) +  "\n");
				}
			}
			
			vmtext.setText(info);
			vmtext.setFont(font);
			vmtext.setEditable(false);		
			scroll = new JScrollPane(vmtext, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setBounds(05, 120, 970, 480);
			panel.add(scroll);
			vmframe.add(panel);

		}
		else {
			JOptionPane.showMessageDialog(null, "Something went wrong!");
		}
	}

	
	
	
	
	
	
	
	
	
//-------------------------------------------------------------------------------------------RENAME MODULE -------------------------------------------------------------------------------------------------
	
	
	
	
	
	// renaming module
	
	
	protected void renameModule() {

		mm.readModuleFile();
		
		JFrame rmframe = new JFrame();
		rmframe.setTitle("Rename module GUI");
		rmframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		rmframe.setSize(600, 400);
		rmframe.setResizable(false);
		
		
		JPanel jp = new JPanel();
		jp.setBackground(new Color(0x123456));
		jp.setLayout(null);
		jp.setBounds(0, 0, 600, 400);
		
		JLabel l = new JLabel("Module Name:");
		l.setBounds(20, 100,120, 40);
		l.setForeground(Color.white);
		jp.add(l);
		
		JTextField t = new JTextField();
		t.setBounds(200, 100, 350, 40);
		t.setEditable(false);
		jp.add(t);
		
		JLabel omidlabel = new JLabel("Old Module ID:");
		omidlabel.setBounds(20, 30, 120, 40);
		omidlabel.setForeground(Color.white);
		
		
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> sd = new JComboBox<String>();
		sd.setBounds(200, 30, 200, 40);
		for (int o=0; o< mm.moduleidarr.size(); o++) {
			if (!elements.contains(mm.moduleidarr.get(o)) ) {
				elements.add(mm.moduleidarr.get(o));
				sd.addItem(mm.moduleidarr.get(o));
			} 
		}
		
		
		// modulename from module id
		sd.addActionListener(e -> {
			for (int o=0; o< mm.moduleidarr.size(); o++) {
				if (mm.moduleidarr.get(o).equalsIgnoreCase((String) sd.getSelectedItem()) ) {
					t.setText(mm.modulenamearr.get(o)); 
				} 
			}
			
		});

		jp.add(sd);
		
		JLabel nmnlabel = new JLabel("New Module Name:");
		nmnlabel.setBounds(20, 170, 120, 40);
		nmnlabel.setForeground(Color.white);
		JTextField nmntext = new JTextField();
		nmntext.setBounds(200, 170, 200, 40);
		
		JButton rename = new JButton("Rename");
		rename.setBounds(200,240, 90, 40);
		rename.setBackground(Color.cyan);
		rename.addActionListener(e -> {
			String changefromid = (String) sd.getSelectedItem();
			String changeto = nmntext.getText().toUpperCase();
			renamingModule(changefromid, changeto);
		});
		
		JButton rmback = new JButton("Go back");
		rmback.setBounds(310,240, 90, 40);
		rmback.setFocusable(false);
		rmback.addActionListener(e-> {
			rmframe.dispose();
			moduleMenu();
		});
		
		
		jp.add(omidlabel);
		jp.add(nmnlabel);
		jp.add(nmntext);
		jp.add(rename);
		jp.add(rmback);
		
		jp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		rmframe.add(jp);
		rmframe.setVisible(true);
	}
	
	
	
	
	
// --------------------------------------------------------------------------RENAMES MODULE IN MODULE FILE-----------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	private void renamingModule(String oldmid, String newmn) {
		
		mm.readModuleFile();
		
		
		this.moduleName = newmn;
		int flag = 0;
		
		 
		
		
		while(flag == 0){
			
			if (!mm.moduleidarr.contains(oldmid) ) { 
				JOptionPane.showMessageDialog(null, "No such module NAME ID to rename");
				flag = 1;
			}
			else if (mm.modulenamearr.contains(newmn)) {
				JOptionPane.showMessageDialog(null, "you entered already existing mdodule name");
				flag = 1;
			}
			else if(mm.moduleidarr.contains(oldmid) ) {
				for (int i=0; i<mm.moduleidarr.size(); i++) {
					if (mm.moduleidarr.get(i).equalsIgnoreCase(oldmid)) {
						mm.modulenamearr.set(i, moduleName);
					}
				}
				
				//////////////////////////////////////////////
				mm.writeModuleFile();
				//////////////////////////////////////////////
				
				JOptionPane.showMessageDialog(null, "Module successfully Renamed!");
				flag = 1;
			} 
		}
	}
	
	
	
	
	
	
//--------------------------------------MODIFY MODULE GUI--------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	protected void modifyModule () {
		
		
		mm.readModuleFile();
		
		JFrame mmframe = new JFrame();
		mmframe.setTitle("Modify module GUI (ADMIN)");
		mmframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		mmframe.setSize(700, 500);
		mmframe.setResizable(false);
		
		
		JPanel jp = new JPanel();
		jp.setBackground(new Color(0x123456));
		jp.setLayout(null);
		jp.setBounds(0, 0, 490, 500);
		
		
		JLabel omidlabel = new JLabel("Module ID:");
		omidlabel.setBounds(20, 30, 100, 40);
		omidlabel.setForeground(Color.white);
		
		
		JLabel nmnlabel = new JLabel("Module Name:");
		nmnlabel.setBounds(20, 170, 180, 40);
		nmnlabel.setForeground(Color.white);
		JTextField nmntext = new JTextField();
		nmntext.setEditable(false);
		nmntext.setBounds(200, 170, 400, 40);
		
		
		

		JLabel nmilabel = new JLabel("Course-Semseter-level-Status");
		nmilabel.setBounds(20, 100, 300, 40);
		nmilabel.setForeground(Color.white);
		JTextField nmitext = new JTextField();
		nmitext.setBounds(250, 100, 200, 40);
		
		
		ArrayList<String> midcheck = new ArrayList<String>();
		JComboBox<String> midcombo = new JComboBox<String>();
		midcombo.setBounds(200, 30, 200, 40);
		for (int o=0; o< mm.moduleidarr.size(); o++) {
			if (!midcheck.contains(mm.moduleidarr.get(o)) ) {
				midcheck.add(mm.moduleidarr.get(o));
				midcombo.addItem(mm.moduleidarr.get(o));
			} 
		}
		jp.add(midcombo);

		// modulename from module id
		midcombo.addActionListener(e -> {
			for (int o=0; o< mm.moduleidarr.size(); o++) {
				if (mm.moduleidarr.get(o).equalsIgnoreCase((String) midcombo.getSelectedItem()) ) {
					nmntext.setText(mm.modulenamearr.get(o)); 
					nmitext.setText(mm.modulecoursearr.get(o) + "-" +mm.modulesemarr.get(o) + "-" + mm.modulelevelarr.get(o) + "-" + mm.
							modulestatusarr.get(o));
					nmitext.setEditable(false);
				} 
			}
			
		});



		
		
		JLabel myear = new JLabel("Module Level: :");
		myear.setBounds(20, 240, 200, 40);
		myear.setForeground(Color.white);
		
		String[] elements = {"3", "4", "5", "6"};
		JComboBox<String> myeartext = new JComboBox<String>(elements);
		myeartext.setBounds(200, 240, 200, 40);
		jp.add(myeartext);
		
		

		JLabel cidl = new JLabel("Semester");
		cidl.setBounds(20, 320, 200, 40);
		cidl.setForeground(Color.white);
		jp.add(cidl);
		
		String[] el= {"1", "2"};
		JComboBox<String> mt = new JComboBox<String>(el);
		mt.setBounds(200, 320, 120, 40);
		jp.add(mt);
		
		

		JLabel ci = new JLabel("Status");
		ci.setBounds(350, 320, 90, 40);
		ci.setForeground(Color.white);
		jp.add(ci);
		
		String[] statuscombo = {"A", "I"};
		JComboBox<String> stcombo= new JComboBox<String>(statuscombo);
		stcombo.setBounds(430, 320, 120, 40);
		jp.add(stcombo);

		JButton modify = new JButton("Modify");
		modify.setBounds(200, 400, 90, 40);
		modify.setBackground(Color.cyan);
		modify.setFocusable(false);
		modify.addActionListener(e->{
			String omid = (String) midcombo.getSelectedItem();
			String nmname = nmntext.getText().toUpperCase();
			String level = (String) myeartext.getSelectedItem();
			String ms = (String) mt.getSelectedItem();
			String status = (String) stcombo.getSelectedItem();
			
			modifyingModule(omid, nmname, level, ms, status);
		});
		
		JButton mmback = new JButton("Go back");
		mmback.setBounds(310, 400, 90, 40);
		mmback.setFocusable(false);
		mmback.addActionListener(e-> {
			mmframe.dispose();
			moduleMenu();
		});
		
		
		jp.add(omidlabel);
		jp.add(nmilabel);
		jp.add(nmitext);
		jp.add(nmnlabel);
		jp.add(nmntext);
		jp.add(myear);
		jp.add(myeartext);

		jp.add(modify);
		jp.add(mmback);
		
		jp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		mmframe.add(jp);
		mmframe.setVisible(true);
		
	}
	
	
	
	
	
	
	
//--------------------------------------MODIFY  MODULE IN MODULE FILE-----------------------------------------------------------------------------------------------------
	
	
	
	// modifying module id and level in module file
	private void modifyingModule(String omid, String nmn, String level, String ms, String status) {
		

		mm.readModuleFile();
		
		
		
		this.moduleID = omid;
		this.moduleName = nmn;
		this.moduleLevel = level;
		this.moduleSemester = ms;
		this.moduleStatus = status;
		

		int flag = 0;
		
		
		while(flag == 0){

			if ( mm.moduleidarr.contains(omid) ){			
				for (int i=0; i < mm.moduleidarr.size(); i++) {
					if (mm.moduleidarr.get(i).equals(omid)) {
						mm.moduleidarr.set(i, moduleID);
						mm.modulenamearr.set(i, moduleName);
						mm.modulelevelarr.set(i, moduleLevel);	
						mm.modulesemarr.set(i, moduleSemester);
						mm.modulestatusarr.set(i, moduleStatus);
					}
				}
				
				
				//////////////////////////////////////////////
				mm.writeModuleFile();
				//////////////////////////////////////////////
				
				
				modifyModuleInfoInTutorFile (omid, level, moduleID);
				
				
				flag = 1;
			} 
		}
	}
	

//---------------------------------------------------------------------- modifying module id and level in tutor file ------------------------------------------------------------------------------------
	

	
	private void modifyModuleInfoInTutorFile (String omid, String level, String nmid) {
		
		mm.readModuleFile();
		tm.readTutorFile();

		
		this.moduleID = nmid;
		this.moduleLevel = level;
		
		int flag = 0;
		
		
		while(flag == 0){
			
		
			for (int i=0; i<tm.tutoridarr.size(); i++) {
				
				if (tm.tutormodulearr.get(i).equalsIgnoreCase(omid)) {

					tm.tutormodulearr.set(i,moduleID);
					mm.modulesemarr.set(i, moduleSemester);
						
					}
				}
				
				/////////////////////////////////////////////////////////////////////////
				mm.writeModuleFile();
				//////////////////////////////////////////////////////////////////////////
				
				JOptionPane.showMessageDialog(null, "Module successfully Modified");
				
				flag = 1;
			} 
		}

	

	
	
	
	
	

	
	
	
//----------------------------------------------------------------------DELETE MODULE GUI----------------------------------------------------------------------
		
	
	

	protected void deleteModule() {
		
		mm.readModuleFile();
		c.readCourseFile();
		
		JFrame dmframe = new JFrame();
		dmframe.setTitle("Delete module GUI");
		dmframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		dmframe.setSize(700, 500);
		dmframe.setResizable(false);
		
		JPanel jp = new JPanel();
		jp.setBackground(new Color(0x123456));
		jp.setLayout(null);
		jp.setBounds(0, 0, 490, 400);
		
		
		JLabel dcidlabel = new JLabel("Course Name: ");
		dcidlabel.setBounds(20, 30, 100, 40);
		dcidlabel.setForeground(Color.white);
		// COURSE NAME combo box
		ArrayList<String> epp = new ArrayList<String>();
		JComboBox<String> sd1 = new JComboBox<String>();
		sd1.setBounds(200, 30, 200, 40);
		for (int o=0; o< c.coursenamearr.size(); o++) {
			if (!epp.contains(c.coursenamearr.get(o)) ) {
				epp.add(c.coursenamearr.get(o));
				sd1.addItem(c.coursenamearr.get(o));
			} 
		}

		jp.add(sd1);

		
		// modulename 		// modulename
		JTextField jk = new JTextField();
		jk.setEditable(false);
		jk.setBounds(200, 170, 400, 40);
		jp.add(jk);
		

		
		JLabel dmidlabel = new JLabel("Module ID: ");
		dmidlabel.setBounds(20, 100, 100, 40);
		dmidlabel.setForeground(Color.white);
		
		// module id combo box
		ArrayList<String> elements = new ArrayList<String>();
		JComboBox<String> sd = new JComboBox<String>();
		sd.setBounds(200, 100, 200, 40);
		for (int o=0; o< mm.moduleidarr.size(); o++) {
			if (!elements.contains(mm.moduleidarr.get(o)) ) {
				elements.add(mm.moduleidarr.get(o));
				sd.addItem(mm.moduleidarr.get(o));
			} 
		}
		sd.addActionListener(e -> {
			for (int o=0; o< mm.moduleidarr.size(); o++) {
				if (mm.moduleidarr.get(o).equalsIgnoreCase((String) sd.getSelectedItem()) ) {
					jk.setText(mm.modulenamearr.get(o)); 
				} 
			}
			
		});

		jp.add(sd);
		
		JLabel dmnamelabel = new JLabel("Module Name: ");
		dmnamelabel.setBounds(20, 170, 100, 40);
		dmnamelabel.setForeground(Color.white);
		

		
		
		JButton delete = new JButton("Delete");
		delete.setBounds(200, 240, 90, 40);
		delete.setBackground(Color.cyan);
		delete.setFocusable(false);
		delete.addActionListener(e -> {
			String cid = (String) sd1.getSelectedItem();
			String mid = (String) sd.getSelectedItem(); 
			String mname = jk.getText();
			deletingModule(cid, mid, mname);
		});
		
		JButton dmback = new JButton("Go back");
		dmback.setBounds(310, 240, 90, 40);
		dmback.setFocusable(false);
		dmback.addActionListener(e-> {
			dmframe.dispose();
			moduleMenu();
		});
		
		
		jp.add(dcidlabel);
		jp.add(dmidlabel);
		jp.add(dmnamelabel);
		jp.add(delete);
		jp.add(dmback);
		
		
		jp.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.CYAN));
		dmframe.add(jp);
		dmframe.setVisible(true);
	}
	
	
	
	
	
	
//-------------------------------------------------------------------------DELETING MODULE IN MODULE FILE---------------------------------------------------------------------------
	
	
	
	
	
	
	
	private void deletingModule(String cname, String mid, String mname) {
		
		
		mm.readModuleFile();
		c.readCourseFile();
		
		
		this.courseName = cname;
		this.moduleID = mid;
		this.moduleName = mname;
		
		
		
		int flag = 2;
				
		for (int i=0; i<mm.modulecoursearr.size(); i++) {
			if (mm.modulecoursearr.get(i).equalsIgnoreCase(courseName) &&  mm.moduleidarr.get(i).equalsIgnoreCase(moduleID) &&
					mm.modulenamearr.get(i).equalsIgnoreCase(moduleName)) {			
				flag = 1;
				break;
			}
		}
		
		
		if (flag == 2) {
			JOptionPane.showMessageDialog(null, "Module and Course do not match");
		}
		

		
		
		while(flag == 1) {
			
			
			for (int i=0; i<mm.modulecoursearr.size(); i++) {
				
	
				if (mm.modulecoursearr.get(i).equalsIgnoreCase(courseName) &&  mm.moduleidarr.get(i).equalsIgnoreCase(moduleID)
						&& mm.modulenamearr.get(i).equalsIgnoreCase(moduleName) && mm.modulestatusarr.get(i).equals("I")){			
					
					mm.moduleidarr.remove(i);
					mm.modulenamearr.remove(i);
					mm.modulecoursearr.remove(i);
					mm.modulelevelarr.remove(i);
					mm.modulesemarr.remove(i);
					mm.modulestatusarr.remove(i);
					
							 
					//////////////////////////////////////////////
					mm.writeModuleFile();
					//////////////////////////////////////////////
					JOptionPane.showMessageDialog(null, "Module successfully deleted!");
							
					flag = 3;
					break;
				}	
				flag = 0;
			}
			
		}
		
		if (flag == 0) {
			JOptionPane.showMessageDialog(null, "Module is currently active cannot delete now!");
		}
	}
	

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
}
