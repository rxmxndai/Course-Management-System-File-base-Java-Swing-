

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ModuleManager {
	
	File moduleFile = new File("Module.txt"); 
	
	
	protected ArrayList<String> moduleidarr, modulenamearr, modulelevelarr, modulecoursearr, modulestatusarr, modulesemarr;
	protected  ArrayList<String> uniquelevelarr, uniquemodulesem, uniquecoursename;
	
	
	

	//--------------------------------------------------------READ MODULE FILE AND STORES ALL THE COLUMNS IN MODULE'S ARRAY------------------------------------------------------------//
	
	
	
	public void readModuleFile() {
		
		moduleidarr = new ArrayList<String>();
		modulenamearr = new ArrayList<String>();
		modulecoursearr = new ArrayList<String>();
		modulelevelarr = new ArrayList<String>();
		uniquecoursename = new ArrayList<String>();
		uniquelevelarr = new ArrayList<String>();
		modulesemarr = new ArrayList<String>();
		modulestatusarr = new ArrayList<String>();
		uniquemodulesem = new ArrayList<String>();
		
		try {
			Scanner in = new Scanner(moduleFile);
			while (in.hasNext()){
				String curLine = in.nextLine();
				String[] splitted = curLine.split("\t");
			
				String moduleid = splitted[0].trim();
		        String modulename = splitted[1].trim();
		        String modulelevel = splitted[2].trim();
		        String modulecourse = splitted[3].trim();
		        String modulesem = splitted[4].trim();
		        String status = splitted[5].trim();
		        
				moduleidarr.add(moduleid);
				modulenamearr.add(modulename);
				modulelevelarr.add(modulelevel);
				modulecoursearr.add(modulecourse);
				modulesemarr.add(modulesem);
				modulestatusarr.add(status);

				
			
				for (String each:modulecoursearr) {
					if (!uniquecoursename.contains(each)) {
						uniquecoursename.add(each);
					}
				}
				for (String each: modulelevelarr) {
					if (!uniquelevelarr.contains(each)) {
						uniquelevelarr.add(each);
					}
				}
				for (String each: modulesemarr) {
					if (!uniquemodulesem.contains(each)) {
						uniquemodulesem.add(each);
					}
				}
				
				
				 
			}
			in.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error locating file");
		}
	}



	// -------------------------------------------------------write to module file---------------------------------------------------------


	public void writeModuleFile() {
		
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(moduleFile, false)))){
			
			for (int j=0; j<moduleidarr.size(); j++) {
				out.println(moduleidarr.get(j) +"\t"+ modulenamearr.get(j) +"\t"+
			modulelevelarr.get(j) + "\t" + modulecoursearr.get(j) 
				+ "\t" + modulesemarr.get(j) + "\t" + modulestatusarr.get(j));
			}

		} catch(IOException e){
			JOptionPane.showMessageDialog(null, "Error locating file");
		}
	}
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
}
