package server.model.players;

import java.io.*;
import java.util.*;

import server.model.players.companies.*;
import server.util.Misc;

@SuppressWarnings("unused")
public class Company {
	public Integer id;
  	public Integer value;
	public Integer shares;
	public Double growthRate;
  	public String name;
  	//public Object[][] investors;

  	public Company() {}
  	public Company(int id, int value, int shares, Double growthRate, String name/*, Object[][] investors*/) {
  			this.id = id;
    		this.value = value;
    		this.shares = shares;
    		this.growthRate = growthRate;
    		this.name = name;
    		//this.investors = investors;
    		
  	}
  	//4*3=12
  	//12/3=4
  	public static List<Company> companies;
  	
  	public static boolean loadCompany(Company company) {
  		
  		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		BufferedReader file = null;
		boolean fileFound = false;
		try {
			file = new BufferedReader(new FileReader("./Data/characters/"+company.name+".txt"));
			fileFound = true;
		} catch(FileNotFoundException f1) {
			f1.printStackTrace();
		}
		try {
			line = file.readLine();
		} catch(IOException ioexception) {
			Misc.println(company.name+": error loading file.");
			return false;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				if (token.equals("company-name")) {
					company.name = token2;
				} else if (token.equals("company-id")) {
					company.id = Integer.parseInt(token2);
				} else if (token.equals("company-value")) {
					company.value = Integer.parseInt(token2);
				} else if (token.equals("company-shares")) {
					company.shares = Integer.parseInt(token2);
				} else if (token.equals("company-rate")) {
					company.growthRate = Double.parseDouble(token2);
				}
			}
			try {
				line = file.readLine();
			} catch(IOException ioexception1) { 
				EndOfFile = true; 
			}
		}
		try { 
			file.close(); 
		} catch(IOException ioexception) {
			
		}
  		return true;
  	}
  	
  	public static boolean saveCompany(Company company) {
  		if (company == null) {
  			return false;
  		}
  		BufferedWriter file = null;
  		try {
  			file = new BufferedWriter(new FileWriter("./Data/stocks/"+company.name+".txt"));
  			
  			file.write("company-name = ", 0, 15);
  			file.write(company.name, 0, company.name.length());
  			file.newLine();
  			file.write("company-id = ", 0, 13);
  			file.write(Integer.toString(company.id), 0, Integer.toString(company.id).length());
  			file.newLine();
  			file.write("company-value = ", 0, 16);
  			file.write(Integer.toString(company.value), 0, Integer.toString(company.value).length());
  			file.newLine();
  			file.write("company-shares = ", 0, 17);
  			file.write(Integer.toString(company.shares), 0, Integer.toString(company.shares).length());
  			file.newLine();
  			file.write("company-rate = ", 0, 15);
  			file.write(Double.toString(company.growthRate), 0, Double.toString(company.growthRate).length());
  			file.newLine();
  			file.newLine();
  			file.close();
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return true;
  	}
  	
  	/*public static void setList() {
  		companies = Arrays.asList(
  					new Company(1, 100000000, 200000, 0.02, "Ordan's Ores"),
  					new Company(2, 350000000, 700000, 0.02, "Bob's Axes")
  					);
  	}*/
  	
}
