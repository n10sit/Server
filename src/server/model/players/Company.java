package server.model.players;

import java.util.Arrays;
import java.util.List;

import server.model.players.companies.*;

@SuppressWarnings("unused")
public class Company {
	public Integer id;
  	public Integer value;
	public Integer shares;
	public Double growthRate;
  	public String name;

  	public Company() {}
  	public Company(int id, int value, int shares, Double growthRate, String name) {
  			this.id = id;
    		this.value = value;
    		this.shares = shares;
    		this.growthRate = growthRate;
    		this.name = name;
  	}
  	//4*3=12
  	//12/3=4
  	public static List<Company> companies;
  	
  	/*public static void setList() {
  		companies = Arrays.asList(
  					new Company(1, 100000000, 200000, 0.02, "Ordan's Ores"),
  					new Company(2, 350000000, 700000, 0.02, "Bob's Axes")
  					);
  	}*/
  	
}
