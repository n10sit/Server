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
  	public Map<Player, Integer> investors;

  	public Company() {}
  	public Company(int id, int value, int shares, Double growthRate, String name, Map<Player, Integer> investors) {
  			this.id = id;
    		this.value = value;
    		this.shares = shares;
    		this.growthRate = growthRate;
    		this.name = name;
    		this.investors = investors;
  	}
  	//4*3=12
  	//12/3=4
  	public static List<Company> companies;
  	
  	public boolean loadCompanies() {
  		return false;
  	}
  	
  	public boolean saveCompany(Company company) {
  		if (company == null) {
  			return false;
  		}
  		BufferedWriter file = null;
  		try {
  			file = new BufferedWriter(new FileWriter("./Data/stocks/"+company.name+".txt"));
  			
  			file.write("company - name = ", 0, 17);
  			file.write(company.name, 0, company.name.length());
  			file.newLine();
  			file.write("company - id = ", 0, 15);
  			file.write(Integer.toString(company.id), 0, Integer.toString(company.id).length());
  			file.newLine();
  			file.write("company - value = ", 0, 18);
  			file.write(Integer.toString(company.value), 0, Integer.toString(company.value).length());
  			file.newLine();
  			file.write("company - shares = ", 0, 19);
  			file.write(Integer.toString(company.shares), 0, Integer.toString(company.shares).length());
  			file.newLine();
  			file.write("company - rate = ", 0, 17);
  			file.write(Double.toString(company.growthRate), 0, Double.toString(company.growthRate).length());
  			file.newLine();
  			file.write("[INVESTORS]", 0, 11);
  			file.newLine();
  			ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream("./Data/stocks/"+company.name+".txt"));
  			oos.writeObject(company.investors);
  			oos.close();
  			file.newLine();
  			file.close();
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return false;
  	}
  	
  	/*public static void setList() {
  		companies = Arrays.asList(
  					new Company(1, 100000000, 200000, 0.02, "Ordan's Ores"),
  					new Company(2, 350000000, 700000, 0.02, "Bob's Axes")
  					);
  	}*/
  	
}
