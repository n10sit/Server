package server.model.players.companies;

import java.util.*;

import server.model.players.*;

@SuppressWarnings("unused")
public class BobsAxes {
	
	private Integer id;
	private Integer value;
	private Integer shares;
	private Double growthRate;
  	private String name;
  	private Map<Player, Integer> investors;
	
  	public BobsAxes() {}
	public BobsAxes(int id, int value, int shares, double growthRate, String name, Map<Player, Integer> investors) {
		this.id = id;
		this.value = value;
		this.shares = shares;
		this.growthRate = growthRate;
		this.name = name;
		this.investors = investors;
	}

}
