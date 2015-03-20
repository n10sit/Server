package server.model.npcs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.util.Misc;

public class PlayerStats {

	@SuppressWarnings("unused")
	private Client c;
	
	public PlayerStats(Client Client) {
		this.c = Client;
	}
	
	//public String first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth = "";
	//public int firstk, secondk, thirdk, fourthk, fifthk, sixthk, seventhk, eighthk, ninthk, tenthk = 0;
	Map<String,Integer> theKills = new HashMap<String,Integer>();
	public Object[] theKillsObject;
	Map<String,Integer> theDeaths = new HashMap<String,Integer>();
	public Object[] theDeathsObject;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doKillsHS(boolean pvp) {
		if (!theKills.containsValue(null))
			theKills.clear();
		NPCRanks.clearList();
		NPCRanks.setList();
		int which = 0;
		for (int i = 0; i < PlayerHandler.players.length; i++) {
			if (PlayerHandler.players[i] != null) {
				Client c2 = (Client)PlayerHandler.players[i];
				if (!pvp) { //pvn
					which = (int)c2.npcKills;
				} else {
					which = (int)c2.playerKills;
				}
				theKills.put(Misc.ucFirst(c2.playerName),which);
			}
		}
		if (!pvp) { //pvn
			for (int i = 0; i < NPCRanks.opponents.size(); i++) {
				if (NPCRanks.opponents.get(i) != null) {
					theKills.put(NPCRanks.opponents.get(i).name, NPCRanks.opponents.get(i).kills);
				}
			}
		}
		theKillsObject = theKills.entrySet().toArray();
	    Arrays.sort(theKillsObject, new Comparator() {
	        public int compare(Object o1, Object o2) {
	            return ((Map.Entry<String, Integer>) o2).getValue().compareTo(
	                    ((Map.Entry<String, Integer>) o1).getValue());
	        }
	    });
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doDeathsHS(boolean pvp) {
		if (!theDeaths.containsValue(null))
			theDeaths.clear();
		NPCRanks.clearList();
		NPCRanks.setList();
		int which = 0;
		for (int i = 0; i < PlayerHandler.players.length; i++) {
			if (PlayerHandler.players[i] != null) {
				Client c2 = (Client)PlayerHandler.players[i];
				if (!pvp) { //pvn
					which = (int)c2.npcDeaths;
				} else {
					which = (int)c2.playerDeaths;
				}
				theDeaths.put(Misc.ucFirst(c2.playerName),which);
			}
		}
		if (!pvp) {
			for (int i = 0; i < NPCRanks.opponents.size(); i++) {
				if (NPCRanks.opponents.get(i) != null) {
					theDeaths.put(NPCRanks.opponents.get(i).name, NPCRanks.opponents.get(i).deaths);
				}
			}
		}
		theDeathsObject = theDeaths.entrySet().toArray();
	    Arrays.sort(theDeathsObject, new Comparator() {
	        public int compare(Object o1, Object o2) {
	            return ((Map.Entry<String, Integer>) o2).getValue().compareTo(
	                    ((Map.Entry<String, Integer>) o1).getValue());
	        }
	    });
	}
	
}


