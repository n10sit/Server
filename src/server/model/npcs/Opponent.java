package server.model.npcs;

public class Opponent {
	public Integer id;
  	public Integer kills;
	public Integer deaths;
  	public String name;
  	public Integer powerLevel;
  	public Integer foodId;
  	public Integer foodAmt;

  	Opponent() {}
  	public Opponent(int id, int kills, int deaths, String name, int powerLevel, int foodId, int foodAmt) {
  			this.id = id;
    		this.kills = kills;
    		this.deaths = deaths;
    		this.name = name;
    		this.powerLevel = powerLevel;
    		this.foodId = foodId;
    		this.foodAmt = foodAmt;
  	}

  	public String toString() {
    		return "" + id + ", " + kills + ", " + deaths+ ", " + name + ", " + powerLevel + "";
  	}
  	
  	
  	
}