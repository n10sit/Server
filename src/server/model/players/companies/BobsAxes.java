package server.model.players.companies;

import java.util.*;

import server.model.players.*;

@SuppressWarnings("unused")
public class BobsAxes extends Company {
	
	public BobsAxes() {
		
	}
	
	public void initialize() {
		this.id = 1;
		this.value = 2000000;
		this.shares = 60000;
		this.growthRate = 0.02;
		this.name = "Bob's Axes";
		//this.investors.equals(null);
	}

}
