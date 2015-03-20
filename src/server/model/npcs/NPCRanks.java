package server.model.npcs;

import java.util.*;

import server.model.players.Client;

public class NPCRanks {
	
	

	public static List<Opponent> opponents;

	public static void setList() {
		opponents = Arrays.asList(
				new Opponent(1, NPCHandler.manKills, NPCHandler.manDeaths, "Man", 1, -1, -1),
				new Opponent(709, NPCHandler.impKills, NPCHandler.impDeaths, "Imp", 2, -1, -1),
				new Opponent(13, NPCHandler.wizardKills, NPCHandler.wizardDeaths, "Wizard", 5, -1, -1),
				new Opponent(192, NPCHandler.warriorKills, NPCHandler.warriorDeaths, "Dark warrior", 4, -1, -1),
				new Opponent(88, NPCHandler.ratKills, NPCHandler.ratDeaths, "Dungeon rat", 6, -1, -1),
				new Opponent(102, NPCHandler.goblinKills, NPCHandler.goblinDeaths, "Goblin", 5, -1, -1),
				new Opponent(71, NPCHandler.orcKills, NPCHandler.orcDeaths, "Orc", 7, -1, -1),
				new Opponent(106, NPCHandler.bearKills, NPCHandler.bearDeaths, "Bear", 7, -1, -1),
				new Opponent(15, NPCHandler.womanKills, NPCHandler.womanDeaths, "Warrior woman", 8, -1, -1),
				new Opponent(184, NPCHandler.pirateKills, NPCHandler.pirateDeaths, "Pirate", 8, -1, -1),
				new Opponent(144, NPCHandler.scorpionKills, NPCHandler.scorpionDeaths, "King scorpion", 9, -1, -1),
				new Opponent(191, NPCHandler.tribesmanKills, NPCHandler.tribesmanDeaths, "Tribesman", 11, -1, -1),
				new Opponent(247, NPCHandler.moldredKills, NPCHandler.moldredDeaths, "Sir Moldred", 14, 333, 5),
				new Opponent(237, NPCHandler.knightKills, NPCHandler.knightDeaths, "Renegade Knight", 16, 333, 5),
				new Opponent(753, NPCHandler.melzarKills, NPCHandler.melzarDeaths, "Melzar the Mad", 17, 333, 5),
				new Opponent(190, NPCHandler.monkKills, NPCHandler.monkDeaths, "Monk of Zamorak", 20, 333, 5),
				new Opponent(1536, NPCHandler.pickaxeKills, NPCHandler.pickaxeDeaths, "Possessed Pickaxe", 24, -1, -1),
				new Opponent(913, NPCHandler.battleKills, NPCHandler.battleDeaths, "Battle Mage", 30, 361, 8),
				new Opponent(116, NPCHandler.cyclopsKills, NPCHandler.cyclopsDeaths, "Cyclops", 36, 361, 8),
				new Opponent(145, NPCHandler.iceKills, NPCHandler.iceDeaths, "Ice Warrior", 45, 361, 8),
				new Opponent(374, NPCHandler.ogreKills, NPCHandler.ogreDeaths, "Ogre", 52, 361, 8),
				new Opponent(1593, NPCHandler.dogKills, NPCHandler.dogDeaths, "Wild Dog", 55, 361, 8),
				new Opponent(21, NPCHandler.heroKills, NPCHandler.heroDeaths, "Hero", 75, 385, 14),
				new Opponent(1906, NPCHandler.toughKills, NPCHandler.toughDeaths, "Tough guy", 75, 385, 14),
				new Opponent(277, NPCHandler.lesarkusKills, NPCHandler.lesarkusDeaths, "Lesarkus", 78, 385, 14),
				new Opponent(938, NPCHandler.devereKills, NPCHandler.devereDeaths, "Randolph Devere", 78, 385, 18),
				new Opponent(1540, NPCHandler.daythKills, NPCHandler.daythDeaths, "Treus Dayth", 80, 385, 18),
				new Opponent(1096, NPCHandler.stickKills, NPCHandler.stickDeaths, "Stick", 90, 385, 18),
				new Opponent(1184, NPCHandler.elfKills, NPCHandler.elfDeaths, "Elf warrior", 94, 385, 22),
				new Opponent(477, NPCHandler.warlordKills, NPCHandler.warlordDeaths, "Khazard Warlord", 99, 391, 24),
				new Opponent(221, NPCHandler.titanKills, NPCHandler.titanDeaths, "Black knight titan", 110, 391, 26),
				new Opponent(1851, NPCHandler.arzKills, NPCHandler.arzDeaths, "Arzinian", 175, 391, 28)
		    );
	}
	
	public static void clearList() {
		setList();
		opponents.equals(Collections.emptyList());
	}

	public static void doByKills() {
		clearList();
		setList();
    	Collections.sort(opponents, new Comparator<Opponent>() {
      		public int compare(Opponent o1, Opponent o2) {
        		return o1.kills.compareTo(o2.kills);
      		}
    	});	
	}
	
	
	public static void doByDeaths() {
		clearList();
		setList();
    	Collections.sort(opponents, new Comparator<Opponent>() {
      		public int compare(Opponent o1, Opponent o2) {
        		return o1.deaths.compareTo(o2.deaths);
      		}
    	});
	}

	public static void getRandomOpponents(Client c) {
		clearList();
		setList();
		Opponent opp1 = null; Opponent opp2 = null; Opponent opp3 = null; Opponent opp4 = null;
		Collections.shuffle(opponents);
		opp1 = opponents.get(0);
		opp2 = opponents.get(1);
		opp3 = opponents.get(2);
		opp4 = opponents.get(3);
	
		c.fightOne[0] = opp1;
		c.fightOne[1] = opp2;
		c.fightTwo[0] = opp3;
		c.fightTwo[1] = opp4;
		
	}
	
	public static Opponent getRandomOpponent() {
		clearList();
		setList();
		Collections.shuffle(opponents);
		return opponents.get(0);
	}
	
	public static Opponent getOpponentById(int id) {
		clearList();
		setList();
  		for (int x = 0; x < opponents.size(); x++) {
  			if (id == opponents.get(x).id) {
  				return opponents.get(x);
  			}
  		}
		return null;
  	}
	
	/**private static void printList(String type) {
		//List<Opponent> listToUse = opponents.subList(11, 21);

    		System.out.println(" " + type + ":");

    		for (Opponent o : listToUse) {
      			System.out.println("\t" + o);
    		}

    		System.out.println();
  	}**/
}