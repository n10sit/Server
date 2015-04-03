package server.model.players.companies;

import java.util.*;

import server.model.players.*;

public class BobsAxes {
	
	public BobsAxes() {
		Company.companies.add(new Company(2, 350000000, 700000, 0.02, "Bob's Axes"));
	}
	
	public Map<String, Integer> investors;

}
