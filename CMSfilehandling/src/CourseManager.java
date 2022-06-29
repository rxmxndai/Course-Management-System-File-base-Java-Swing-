

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CourseManager {
	
	
	// FILES TO BE READ AND WRITTEN
	File courseFile = new File("Course.txt");
	File cancelledCourse = new File("CancelledCourse.txt");
	
	
	
	
	//--------COURSE ARRAYS
	protected ArrayList<String> coursenamearr, courseidarr;
	
	protected ArrayList<String> uniquecidarr, uniquecoursename, cancelledcourseidarr, cancelledcoursenamearr;
	
	
	//---------------------READ COURSE FILE AND STORES ALL THE COLUMNS IN COURSE'S ARRAY-------------------------//
	
	public void readCourseFile() {
		courseidarr = new ArrayList<String>();
		coursenamearr = new ArrayList<String>();

		uniquecoursename = new ArrayList<String>();
		uniquecidarr = new ArrayList<String>();

		 
		try { 
			Scanner in = new Scanner(courseFile);
			
			while (in.hasNext()){
				
				String curLine = in.nextLine();
				String[] splitted = curLine.split("\t\t");
				
				String courseid = splitted[0].trim();
		        String coursename = splitted[1].trim();

		        
				courseidarr.add(courseid);
				coursenamearr.add(coursename);

				
				for (String each: coursenamearr) {
					if (!uniquecoursename.contains(each)) {
						uniquecoursename.add(each);
					}
				}
				for (String each: courseidarr) {
					if (!uniquecidarr.contains(each)) {
						uniquecidarr.add(each);
					}
				}

				
			}
			
			in.close();
		
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error locating Course file");
		}
	}
	
	
	//---------------------WRITES ALL THE DATA STORED IN COURSE'S ARRAY TO COURSE FILE-------------------------//
	
	public void writeCourseFile() {
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(courseFile, false)))){
			

			for (int j=0; j< courseidarr.size(); j++) {
				out.println( courseidarr.get(j) + "\t\t" + coursenamearr.get(j));
			}
			
			
		} catch(IOException e){
			JOptionPane.showMessageDialog(null, "Error locating file");
		}
	}
	
	

	//---------------------READS ALL THE DATA STORED IN CANCELLED COURSE FILE AND STORES IN CANCELLED COURSE'S ARRAY-------------------------//
	
	public void readCancelledCourse() {
	
		cancelledcourseidarr = new ArrayList<String> ();
		cancelledcoursenamearr = new ArrayList<String> ();
		
		
		try {
		
			Scanner read = new Scanner(cancelledCourse);
			
			while (read.hasNext()) {
				String curline = read.nextLine();
				String[] splitted = curline.split("\t\t");
				
				String cid = splitted[0].trim();
				String cname = splitted[1].trim();
				
				
				cancelledcourseidarr.add(cid);
				cancelledcoursenamearr.add(cname);
				
				}
				read.close();
				
			
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error locating Cancelled course file");
		} catch (Exception ed) {
			JOptionPane.showMessageDialog(null, ed);
		}
 
	}


	//---------------------WRITES ALL THE DATA STORED IN CANCELLED COURSE'S ARRAY TO CANCELLED COURSE FILE-------------------------//
	
	
	public void writeCancelledCourseFile() {
		
		try {
			PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(cancelledCourse, false)));
							
			for (int j=0; j<cancelledcourseidarr.size(); j++) {
					write.println( cancelledcourseidarr.get(j)  + "\t\t" +  cancelledcoursenamearr.get(j));
			}
							
			write.close();
					
							
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "There is no file");
		}
		
	}
	
	
	

}
