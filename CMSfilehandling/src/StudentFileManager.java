

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class StudentFileManager {
	
	File studentFile =  new File("student.txt");
	File marksFile =  new File("marks.txt");
	

	
	// student arraylist
	protected ArrayList<String> studentpasswordarr;
	protected ArrayList<String> studentcontactarr, studentcoursearr, markssid, markssname, markssmid, markssmname, markssgrade, markssremarks, studentsemarr;
	protected ArrayList<String> studentidarr, markssl, marksssem, markssmarks ;
	protected ArrayList<String> studentnamearr;
	protected ArrayList<String> studentlevelarr;
	protected  ArrayList<String>  studentemailarr, studentaddressarr;
	

	
	
	
	//-----------------------------------READING FROM STUDENT FILE--------------------------------------------------------------------------
	
	
	
	
	void readStudentFile() {
		
		studentidarr = new ArrayList<String>();
		studentnamearr = new ArrayList<String>();
		studentlevelarr = new ArrayList<String>();
		studentemailarr = new ArrayList<String>();
		studentaddressarr = new ArrayList<String>();
		studentcontactarr = new ArrayList<String>();
		studentpasswordarr = new ArrayList<String>();
		studentcoursearr = new ArrayList<String>();
		studentsemarr = new ArrayList<String>();
		
		// student
		
		try(Scanner in = new Scanner(studentFile)){
		
		
			while(in.hasNextLine()){
				String curLine = in.nextLine();
				String[] splitted = curLine.split("\t");
				
				String sid = splitted[0].trim();
				String sname = splitted[1].trim();
				String spass = splitted[2].trim();
				String saddress = splitted[3].trim(); 
				String semail = splitted[4].trim();
				String sphone = splitted[5].trim();
				String sl = splitted[6].trim();
				String courses = splitted[7].trim();
				String sem = splitted[8].trim();
				
				studentidarr.add(sid);
				studentnamearr.add(sname);
				studentpasswordarr.add(spass);
				studentaddressarr.add(saddress);
				studentemailarr.add(semail);
				studentcontactarr.add(sphone);
				studentlevelarr.add(sl);
				studentcoursearr.add(courses);
				studentsemarr.add(sem);
			
			}
		
		
		} catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "STUDENT File is not available to read" );
		}
	}



	 
	//---------------------------------------------READING FROM MARKS file----------------------------------------------------------------------------
	
	void readMarksFile() {
	
		markssid = new ArrayList<String>();
		markssname = new ArrayList<String>();
		markssl = new ArrayList<String>();
		markssmid = new ArrayList<String>();
		markssmname = new ArrayList<String>();
		markssgrade = new ArrayList<String>();
		markssremarks = new ArrayList<String>();
		marksssem = new ArrayList<String>();
		markssmarks = new ArrayList<String>();
		
		// for marks file
		try (Scanner read = new Scanner(marksFile)) {
			while (read.hasNext()) {
			String curline = read.nextLine();
			
			String[] split = curline.split("\t");
			
			String studentkoid = split[0].trim();
			String studentkoname = split[1].trim();
			String studentkolevel = split[2].trim();
			String studentkomoduleid = split[3].trim();
			String studentkomodulename = split[4].trim();
			String studentkomarks = split[5].trim();
			String studentkograde = split[6].trim();
			String studentkoremarks = split[7].trim();
			String sem = split[8].trim();
			
			
			markssid.add(studentkoid);
			markssname.add(studentkoname);
			markssl.add(studentkolevel);
			markssmid.add(studentkomoduleid);
			markssmname.add(studentkomodulename);
			markssmarks.add(studentkomarks);
			markssgrade.add(studentkograde);
			markssremarks.add(studentkoremarks);
			marksssem.add(sem);
		
		}
		
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "MARKS File is not available to read" );
		}

	}

	
	//---------------------------------------------writing to student file----------------------------------------------------------------------------
	
	
	
	void writeStudentFile() {
	
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(studentFile, false)))){
			
			for (int j=0; j<studentidarr.size(); j++) {
				out.println(studentidarr.get(j) +"\t"+ studentnamearr.get(j) + "\t" +  studentpasswordarr.get(j) + "\t"  
						+ studentaddressarr.get(j) +"\t" + studentemailarr.get(j) +  "\t" +  studentcontactarr.get(j) 
						+ "\t" + studentlevelarr.get(j) + "\t" + studentcoursearr.get(j) + "\t" + studentsemarr.get(j));
			}
			
		} catch(IOException e){
			JOptionPane.showMessageDialog(null, "Error locating STUDENT file");
		}
		
	}
	
	
	//---------------------------------------------writing to marks file----------------------------------------------------------------------------
	
	
	void writeMarksFile() {
		try {
			PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(marksFile, false)));
								
			for  (int p = 0; p <markssid.size(); p++) {
				write.println(markssid.get(p) + "\t" +
						markssname.get(p) + "\t" +
						markssl.get(p) + "\t" +
						markssmid.get(p) + "\t" +
						markssmname.get(p) + "\t" +
						markssmarks.get(p) + "\t" +
						markssgrade.get(p) + "\t" +
						markssremarks.get(p) + "\t" +
						marksssem.get(p)

					);	 
			}
							
			write.close();
			
		} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "There is no Marks file.");
						
		}
	}
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	

}
