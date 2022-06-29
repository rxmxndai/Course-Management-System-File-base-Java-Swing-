

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class TutorFileManager {
	
	
	File tutorFile = new File("Tutor.txt");
	
	
	// Array Lists
	protected ArrayList<String> tutoridarr;
	protected ArrayList<String> tutormodulearr;
	protected ArrayList<String> tutorcoursearr;
	protected ArrayList<String> tutorsemarr;
	protected ArrayList<String> tutornamearr;
	protected ArrayList<String> tutorcontactarr;
	protected ArrayList<String> tutoremailarr;
	protected ArrayList<String> tutoraddressarr;
	protected ArrayList<String> tutorpasswordarr;
	protected ArrayList<String> tutorlevelarr;
	
	
	
	//-------------------------------------------------------------------------------------------------
	
	public void readTutorFile() {
	
		tutoridarr = new ArrayList<String>();
		tutornamearr = new ArrayList<String>();
		tutorcontactarr = new ArrayList<String>();
		tutoremailarr = new ArrayList<String>();
		tutoraddressarr = new ArrayList<String>();
		tutorpasswordarr = new ArrayList<String>();
		tutormodulearr = new ArrayList<String>();
		tutorcoursearr = new ArrayList<String>();
		tutorlevelarr = new ArrayList<String>();
		tutorsemarr = new ArrayList<String>();
		
		try(Scanner in = new Scanner(tutorFile)){
			while(in.hasNextLine()){
				String curLine = in.nextLine();
				String[] splitted = curLine.split("\t");
				
				String tutid = splitted[0].trim();
		        String tutname = splitted[1].trim();
	 	        String tutpass = splitted[2].trim();
		        String tutaddress = splitted[3].trim();
		        String tutemail = splitted[4].trim();
		        String tutphone = splitted[5].trim();  
		        String tutmoduleassigned = splitted[6].trim();
		        String tutorcourse = splitted[7].trim();
		        String tutorlevel = splitted[8].trim();
		        String tutorsemesterr = splitted[9].trim();
		        
		         
				tutoridarr.add(tutid);
				tutornamearr.add(tutname);
				tutorpasswordarr.add(tutpass);
				tutoraddressarr.add(tutaddress);
				tutoremailarr.add(tutemail);
				tutorcontactarr.add(tutphone);
				tutormodulearr.add(tutmoduleassigned);
				tutorcoursearr.add(tutorcourse);
				tutorlevelarr.add(tutorlevel);
				tutorsemarr.add(tutorsemesterr);
				
			}
			
		} catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "File is not available to read" );
		}
	}
	
	
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	public void writeTutorFile() {
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(tutorFile, false)))){
			
			
			// writing in to the file (overwrites)
			
			for (int j=0; j<tutoridarr.size(); j++) {
				out.println(tutoridarr.get(j) +"\t"+ tutornamearr.get(j) + "\t" + 
			tutorpasswordarr.get(j) + "\t" + tutoraddressarr.get(j) + "\t"+ 
						tutoremailarr.get(j)  + "\t" + tutorcontactarr.get(j) + "\t"  + 
			tutormodulearr.get(j) + "\t" + tutorcoursearr.get(j) +
						"\t" + tutorlevelarr.get(j) + "\t" + tutorsemarr.get(j));
			}
			
		
		
		} catch(IOException e){
			JOptionPane.showMessageDialog(null, "Error locating file");
		}
	}
	
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

}
