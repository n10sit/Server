package server.model.npcs;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.util.Misc;
import server.world.map.VirtualWorld;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;

public class NPCHandler {
	public static int maxNPCs = 10000;
	public static int maxListedNPCs = 10000;
	public static int maxNPCDrops = 10000;
	public static NPC npcs[] = new NPC[maxNPCs];
	public static NPCList NpcList[] = new NPCList[maxListedNPCs];

	public static int manKills = 0, manDeaths = 0, impKills = 0, impDeaths = 0, wizardKills = 0, wizardDeaths = 0, warriorKills = 0, warriorDeaths = 0, 
	goblinKills = 0, goblinDeaths = 0, ratKills = 0, ratDeaths = 0, orcKills = 0, orcDeaths = 0, bearKills = 0, bearDeaths = 0, womanKills = 0,
	womanDeaths = 0, pirateKills = 0, pirateDeaths = 0, tribesmanKills = 0, tribesmanDeaths = 0, scorpionKills = 0, scorpionDeaths = 0, 
	moldredKills = 0, moldredDeaths = 0, knightKills = 0, knightDeaths = 0, melzarKills = 0, melzarDeaths = 0, monkKills = 0, monkDeaths = 0, 
	pickaxeKills = 0, pickaxeDeaths = 0, battleKills = 0, battleDeaths = 0, cyclopsKills = 0, cyclopsDeaths = 0, iceKills = 0, iceDeaths = 0,
	ogreKills = 0, ogreDeaths = 0, dogKills = 0, dogDeaths = 0, heroKills = 0, heroDeaths = 0, toughKills = 0, toughDeaths = 0,
	lesarkusKills = 0, lesarkusDeaths = 0, devereKills = 0, devereDeaths = 0, daythKills = 0, daythDeaths = 0, stickKills = 0, stickDeaths = 0, elfKills = 0,
	elfDeaths = 0, warlordKills = 0, warlordDeaths = 0, titanKills = 0, titanDeaths = 0, arzKills = 0, arzDeaths = 0;

	public int lastJobGiven = -1;
	
	public static long lastEat;
	
	public int[] jobs = {50, 1160, 2550, 2554, 2558, 2562, 3200, 2881, 2882, 2883};
	
	public int[] lowDungeon = {73, 749, 1600, 1995};
	public int[] medDungeon = {1219, 1330, 1698, 1993};
	public int[] highDungeon = {1608, 1610, 1828, 1961};
	public int[] bossDungeon = {54};
	public boolean bossAlive = false;
	
	public static String npcChampion = "Arzinian";

	public NPCHandler() {
		for(int i = 0; i < maxNPCs; i++) {
			npcs[i] = null;
		}
		for(int i = 0; i < maxListedNPCs; i++) {
			NpcList[i] = null;
		}
		try {
			loadNPCList("./Data/CFG/npc.cfg");
			loadAutoSpawn("./Data/CFG/spawn-config.cfg");
			loadNPCStats();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	public static boolean loadNPCStats() throws IOException {
		String line = "";
		String token = "";
		String token2 = "";
		boolean endOfFile = false;
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader("./Data/npcstats/npcstats.txt"));
		} catch (FileNotFoundException one) {
			System.out.println("file not found 1");
			return false;
		}
		try {
			line = file.readLine();
		} catch (IOException two) {
			System.out.println("error loading 2");
			file.close();
			return false;
		} 
		while (endOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				if (token.equals("man-kills")) {
					manKills = Integer.parseInt(token2);
				} else if (token.equals("man-deaths")) {
					manDeaths = Integer.parseInt(token2);
				} else if (token.equals("imp-kills")) {
					impKills = Integer.parseInt(token2);
				} else if (token.equals("imp-deaths")) {
					impDeaths = Integer.parseInt(token2);
				} else if (token.equals("wizard-kills")) {
					wizardKills = Integer.parseInt(token2);
				} else if (token.equals("wizard-deaths")) {
					wizardDeaths = Integer.parseInt(token2);
				} else if (token.equals("warrior-kills")) {
					warriorKills = Integer.parseInt(token2);
				} else if (token.equals("warrior-deaths")) {
					warriorDeaths = Integer.parseInt(token2);
				} else if (token.equals("goblin-kills")) {
					goblinKills = Integer.parseInt(token2);
				} else if (token.equals("goblin-deaths")) {
					goblinDeaths = Integer.parseInt(token2);
				} else if (token.equals("rat-kills")) {
					ratKills = Integer.parseInt(token2);
				} else if (token.equals("rat-deaths")) {
					ratDeaths = Integer.parseInt(token2);
				} else if (token.equals("orc-kills")) {
					orcKills = Integer.parseInt(token2);
				} else if (token.equals("orc-deaths")) {
					orcDeaths = Integer.parseInt(token2);
				} else if (token.equals("bear-kills")) {
					bearKills = Integer.parseInt(token2);
				} else if (token.equals("bear-deaths")) {
					bearDeaths = Integer.parseInt(token2);
				} else if (token.equals("woman-kills")) {
					womanKills = Integer.parseInt(token2);
				} else if (token.equals("woman-deaths")) {
					womanDeaths = Integer.parseInt(token2);
				} else if (token.equals("pirate-kills")) {
					pirateKills = Integer.parseInt(token2);
				} else if (token.equals("pirate-deaths")) {
					pirateDeaths = Integer.parseInt(token2);
				} else if (token.equals("scorpion-kills")) {
					scorpionKills = Integer.parseInt(token2);
				} else if (token.equals("scorpion-deaths")) {
					scorpionDeaths = Integer.parseInt(token2);
				} else if (token.equals("tribesman-kills")) {
					tribesmanKills = Integer.parseInt(token2);
				} else if (token.equals("tribesman-deaths")) {
					tribesmanDeaths = Integer.parseInt(token2);
				} else if (token.equals("moldred-kills")) {
					moldredKills = Integer.parseInt(token2);
				} else if (token.equals("moldred-deaths")) {
					moldredDeaths = Integer.parseInt(token2);
				} else if (token.equals("knight-kills")) {
					knightKills = Integer.parseInt(token2);
				} else if (token.equals("knight-deaths")) {
					knightDeaths = Integer.parseInt(token2);
				} else if (token.equals("melzar-kills")) {
					melzarKills = Integer.parseInt(token2);
				} else if (token.equals("melzar-deaths")) {
					melzarDeaths = Integer.parseInt(token2);
				} else if (token.equals("monk-kills")) {
					monkKills = Integer.parseInt(token2);
				} else if (token.equals("monk-deaths")) {
					monkDeaths = Integer.parseInt(token2);
				} else if (token.equals("pickaxe-kills")) {
					pickaxeKills = Integer.parseInt(token2);
				} else if (token.equals("pickaxe-deaths")) {
					pickaxeDeaths = Integer.parseInt(token2);
				} else if (token.equals("battle-kills")) {
					battleKills = Integer.parseInt(token2);
				} else if (token.equals("battle-deaths")) {
					battleDeaths = Integer.parseInt(token2);
				} else if (token.equals("cyclops-kills")) {
					cyclopsKills = Integer.parseInt(token2);
				} else if (token.equals("cyclops-deaths")) {
					cyclopsDeaths = Integer.parseInt(token2);
				} else if (token.equals("ice-kills")) {
					iceKills = Integer.parseInt(token2);
				} else if (token.equals("ice-deaths")) {
					iceDeaths = Integer.parseInt(token2);
				} else if (token.equals("ogre-kills")) {
					ogreKills = Integer.parseInt(token2);
				} else if (token.equals("ogre-deaths")) {
					ogreDeaths = Integer.parseInt(token2);
				} else if (token.equals("dog-kills")) {
					dogKills = Integer.parseInt(token2);
				} else if (token.equals("dog-deaths")) {
					dogDeaths = Integer.parseInt(token2);
				} else if (token.equals("hero-kills")) {
					heroKills = Integer.parseInt(token2);
				} else if (token.equals("hero-deaths")) {
					heroDeaths = Integer.parseInt(token2);
				} else if (token.equals("tough-kills")) {
					toughKills = Integer.parseInt(token2);
				} else if (token.equals("tough-deaths")) {
					toughDeaths = Integer.parseInt(token2);
				} else if (token.equals("lesarkus-kills")) {
					lesarkusKills = Integer.parseInt(token2);
				} else if (token.equals("lesarkus-deaths")) {
					lesarkusDeaths = Integer.parseInt(token2);
				} else if (token.equals("devere-kills")) {
					devereKills = Integer.parseInt(token2);
				} else if (token.equals("devere-deaths")) {
					devereDeaths = Integer.parseInt(token2);
				} else if (token.equals("dayth-kills")) {
					daythKills = Integer.parseInt(token2);
				} else if (token.equals("dayth-deaths")) {
					daythDeaths = Integer.parseInt(token2);
				} else if (token.equals("stick-kills")) {
					stickKills = Integer.parseInt(token2);
				} else if (token.equals("stick-deaths")) {
					stickDeaths = Integer.parseInt(token2);
				} else if (token.equals("elf-kills")) {
					elfKills = Integer.parseInt(token2);
				} else if (token.equals("elf-deaths")) {
					elfDeaths = Integer.parseInt(token2);
				} else if (token.equals("warlord-kills")) {
					warlordKills = Integer.parseInt(token2);
				} else if (token.equals("warlord-deaths")) {
					warlordDeaths = Integer.parseInt(token2);
				} else if (token.equals("titan-kills")) {
					titanKills = Integer.parseInt(token2);
				} else if (token.equals("titan-deaths")) {
					titanDeaths = Integer.parseInt(token2);
				} else if (token.equals("arz-kills")) {
					arzKills = Integer.parseInt(token2);
				} else if (token.equals("arz-deaths")) {
					arzDeaths = Integer.parseInt(token2);
				} else if (token.equals("champion")) {
					npcChampion = token2;
				} else if (token.equals("[EOF]")) {
					System.out.println("eof");
					endOfFile = true;
					try { 
						//System.out.println("eof, closing");
						file.close(); 
					} catch(IOException four) { 
						System.out.println("error 44");
						return false;
					} 
				}
				//break;
			} else {
				try { 
					file.close(); 
				} catch(IOException four) { 
					System.out.println("error 4");
					return false;
				} 
			}
			try {
				line = file.readLine();
			} catch(IOException ioexception1) { 
				endOfFile = true; 
			}
		}
		//makeList();
		System.out.println("Loaded npc stats.");
		return true;
	}

	public static boolean saveNPCStats() {
		BufferedWriter file = null;
		try {
			file = new BufferedWriter(new FileWriter("./Data/npcstats/npcstats.txt"));
			file.write("man-kills = ", 0, 12);
			file.write(Integer.toString(manKills), 0, Integer.toString(manKills).length());
			file.newLine();
			file.write("man-deaths = ", 0, 13);
			file.write(Integer.toString(manDeaths), 0, Integer.toString(manDeaths).length());
			file.newLine();
			file.write("imp-kills = ", 0, 12);
			file.write(Integer.toString(impKills), 0, Integer.toString(impKills).length());
			file.newLine();
			file.write("imp-deaths = ", 0, 13);
			file.write(Integer.toString(impDeaths), 0, Integer.toString(impDeaths).length());
			file.newLine();
			file.write("wizard-kills = ", 0, 15);
			file.write(Integer.toString(wizardKills), 0, Integer.toString(wizardKills).length());
			file.newLine();
			file.write("wizard-deaths = ", 0, 16);
			file.write(Integer.toString(wizardDeaths), 0, Integer.toString(wizardDeaths).length());
			file.newLine();
			file.write("warrior-kills = ", 0, 16);
			file.write(Integer.toString(warriorKills), 0, Integer.toString(warriorKills).length());
			file.newLine();
			file.write("warrior-deaths = ", 0, 17);
			file.write(Integer.toString(warriorDeaths), 0, Integer.toString(warriorDeaths).length());
			file.newLine();
			file.write("goblin-kills = ", 0, 15);
			file.write(Integer.toString(goblinKills), 0, Integer.toString(goblinKills).length());
			file.newLine();
			file.write("goblin-deaths = ", 0, 16);
			file.write(Integer.toString(goblinDeaths), 0, Integer.toString(goblinDeaths).length());
			file.newLine();
			file.write("rat-kills = ", 0, 12);
			file.write(Integer.toString(ratKills), 0, Integer.toString(ratKills).length());
			file.newLine();
			file.write("rat-deaths = ", 0, 13);
			file.write(Integer.toString(ratDeaths), 0, Integer.toString(ratDeaths).length());
			file.newLine();
			file.write("orc-kills = ", 0, 12);
			file.write(Integer.toString(orcKills), 0, Integer.toString(orcKills).length());
			file.newLine();
			file.write("orc-deaths = ", 0, 13);
			file.write(Integer.toString(orcDeaths), 0, Integer.toString(orcDeaths).length());
			file.newLine();
			file.write("bear-kills = ", 0, 13);
			file.write(Integer.toString(bearKills), 0, Integer.toString(bearKills).length());
			file.newLine();
			file.write("bear-deaths = ", 0, 14);
			file.write(Integer.toString(bearDeaths), 0, Integer.toString(bearDeaths).length());
			file.newLine();
			file.write("woman-kills = ", 0, 14);
			file.write(Integer.toString(womanKills), 0, Integer.toString(womanKills).length());
			file.newLine();
			file.write("woman-deaths = ", 0, 15);
			file.write(Integer.toString(womanDeaths), 0, Integer.toString(womanDeaths).length());
			file.newLine();
			file.write("pirate-kills = ", 0, 15);
			file.write(Integer.toString(pirateKills), 0, Integer.toString(pirateKills).length());
			file.newLine();
			file.write("pirate-deaths = ", 0, 16);
			file.write(Integer.toString(pirateDeaths), 0, Integer.toString(pirateDeaths).length());
			file.newLine();
			file.write("scorpion-kills = ", 0, 17);
			file.write(Integer.toString(scorpionKills), 0, Integer.toString(scorpionKills).length());
			file.newLine();
			file.write("scorpion-deaths = ", 0, 18);
			file.write(Integer.toString(scorpionDeaths), 0, Integer.toString(scorpionDeaths).length());
			file.newLine();
			file.write("tribesman-kills = ", 0, 18);
			file.write(Integer.toString(tribesmanKills), 0, Integer.toString(tribesmanKills).length());
			file.newLine();
			file.write("tribesman-deaths = ", 0, 19);
			file.write(Integer.toString(tribesmanDeaths), 0, Integer.toString(tribesmanDeaths).length());
			file.newLine();
			file.write("moldred-kills = ", 0, 16);
			file.write(Integer.toString(moldredKills), 0, Integer.toString(moldredKills).length());
			file.newLine();
			file.write("moldred-deaths = ", 0, 17);
			file.write(Integer.toString(moldredDeaths), 0, Integer.toString(moldredDeaths).length());
			file.newLine();
			file.write("knight-kills = ", 0, 15);
			file.write(Integer.toString(knightKills), 0, Integer.toString(knightKills).length());
			file.newLine();
			file.write("knight-deaths = ", 0, 16);
			file.write(Integer.toString(knightDeaths), 0, Integer.toString(knightDeaths).length());
			file.newLine();
			file.write("melzar-kills = ", 0, 15);
			file.write(Integer.toString(melzarKills), 0, Integer.toString(melzarKills).length());
			file.newLine();
			file.write("melzar-deaths = ", 0, 16);
			file.write(Integer.toString(melzarDeaths), 0, Integer.toString(melzarDeaths).length());
			file.newLine();
			file.write("monk-kills = ", 0, 13);
			file.write(Integer.toString(monkKills), 0, Integer.toString(monkKills).length());
			file.newLine();
			file.write("monk-deaths = ", 0, 14);
			file.write(Integer.toString(monkDeaths), 0, Integer.toString(monkDeaths).length());
			file.newLine();
			file.write("pickaxe-kills = ", 0, 16);
			file.write(Integer.toString(pickaxeKills), 0, Integer.toString(pickaxeKills).length());
			file.newLine();
			file.write("pickaxe-deaths = ", 0, 17);
			file.write(Integer.toString(pickaxeDeaths), 0, Integer.toString(pickaxeDeaths).length());
			file.newLine();
			file.write("battle-kills = ", 0, 15);
			file.write(Integer.toString(battleKills), 0, Integer.toString(battleKills).length());
			file.newLine();
			file.write("battle-deaths = ", 0, 16);
			file.write(Integer.toString(battleDeaths), 0, Integer.toString(battleDeaths).length());
			file.newLine();
			file.write("cyclops-kills = ", 0, 16);
			file.write(Integer.toString(cyclopsKills), 0, Integer.toString(cyclopsKills).length());
			file.newLine();
			file.write("cyclops-deaths = ", 0, 17);
			file.write(Integer.toString(cyclopsDeaths), 0, Integer.toString(cyclopsDeaths).length());
			file.newLine();
			file.write("ice-kills = ", 0, 12);
			file.write(Integer.toString(iceKills), 0, Integer.toString(iceKills).length());
			file.newLine();
			file.write("ice-deaths = ", 0, 13);
			file.write(Integer.toString(iceDeaths), 0, Integer.toString(iceDeaths).length());
			file.newLine();
			file.write("ogre-kills = ", 0, 13);
			file.write(Integer.toString(ogreKills), 0, Integer.toString(ogreKills).length());
			file.newLine();
			file.write("ogre-deaths = ", 0, 14);
			file.write(Integer.toString(ogreDeaths), 0, Integer.toString(ogreDeaths).length());
			file.newLine();
			file.write("dog-kills = ", 0, 12);
			file.write(Integer.toString(dogKills), 0, Integer.toString(dogKills).length());
			file.newLine();
			file.write("dog-deaths = ", 0, 13);
			file.write(Integer.toString(dogDeaths), 0, Integer.toString(dogDeaths).length());
			file.newLine();
			file.write("hero-kills = ", 0, 13);
			file.write(Integer.toString(heroKills), 0, Integer.toString(heroKills).length());
			file.newLine();
			file.write("hero-deaths = ", 0, 14);
			file.write(Integer.toString(heroDeaths), 0, Integer.toString(heroDeaths).length());
			file.newLine();
			file.write("tough-kills = ", 0, 14);
			file.write(Integer.toString(toughKills), 0, Integer.toString(toughKills).length());
			file.newLine();
			file.write("tough-deaths = ", 0, 15);
			file.write(Integer.toString(toughDeaths), 0, Integer.toString(toughDeaths).length());
			file.newLine();
			file.write("lesarkus-kills = ", 0, 17);
			file.write(Integer.toString(lesarkusKills), 0, Integer.toString(lesarkusKills).length());
			file.newLine();
			file.write("lesarkus-deaths = ", 0, 18);
			file.write(Integer.toString(lesarkusDeaths), 0, Integer.toString(lesarkusDeaths).length());
			file.newLine();
			file.write("devere-kills = ", 0, 15);
			file.write(Integer.toString(devereKills), 0, Integer.toString(devereKills).length());
			file.newLine();
			file.write("devere-deaths = ", 0, 16);
			file.write(Integer.toString(devereDeaths), 0, Integer.toString(devereDeaths).length());
			file.newLine();
			file.write("dayth-kills = ", 0, 14);
			file.write(Integer.toString(daythKills), 0, Integer.toString(daythKills).length());
			file.newLine();
			file.write("dayth-deaths = ", 0, 15);
			file.write(Integer.toString(daythDeaths), 0, Integer.toString(daythDeaths).length());
			file.newLine();
			file.write("stick-kills = ", 0, 14);
			file.write(Integer.toString(stickKills), 0, Integer.toString(stickKills).length());
			file.newLine();
			file.write("stick-deaths = ", 0, 15);
			file.write(Integer.toString(stickDeaths), 0, Integer.toString(stickDeaths).length());
			file.newLine();
			file.write("elf-kills = ", 0, 12);
			file.write(Integer.toString(elfKills), 0, Integer.toString(elfKills).length());
			file.newLine();
			file.write("elf-deaths = ", 0, 13);
			file.write(Integer.toString(elfDeaths), 0, Integer.toString(elfDeaths).length());
			file.newLine();
			file.write("warlord-kills = ", 0, 16);
			file.write(Integer.toString(warlordKills), 0, Integer.toString(warlordKills).length());
			file.newLine();
			file.write("warlord-deaths = ", 0, 17);
			file.write(Integer.toString(warlordDeaths), 0, Integer.toString(warlordDeaths).length());
			file.newLine();
			file.write("titan-kills = ", 0, 14);
			file.write(Integer.toString(titanKills), 0, Integer.toString(titanKills).length());
			file.newLine();
			file.write("titan-deaths = ", 0, 15);
			file.write(Integer.toString(titanDeaths), 0, Integer.toString(titanDeaths).length());
			file.newLine();
			file.write("arz-kills = ", 0, 12);
			file.write(Integer.toString(arzKills), 0, Integer.toString(arzKills).length());
			file.newLine();
			file.write("arz-deaths = ", 0, 13);
			file.write(Integer.toString(arzDeaths), 0, Integer.toString(arzDeaths).length());
			file.newLine();
			file.write("champion = ", 0, 11);
			file.write(npcChampion, 0, npcChampion.length());
			file.newLine();
			file.write("[EOF]", 0, 5);
			file.newLine();
			file.newLine();
			file.close();
		} catch (IOException seven) {
			System.out.println("error writing 7");
			return false;
		}
		System.out.println("Saved npc stats.");
		return true;
	}
	
	public void multiAttackGfx(int i, int gfx) {
		if (npcs[i].projectileId < 0)
			return;
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client)PlayerHandler.players[j];
				if (c.heightLevel != npcs[i].heightLevel)
					continue;
				if (PlayerHandler.players[j].goodDistance(c.absX, c.absY, npcs[i].absX, npcs[i].absY, 15)) {
					int nX = NPCHandler.npcs[i].getX() + offset(i);
					int nY = NPCHandler.npcs[i].getY() + offset(i);
					int pX = c.getX();
					int pY = c.getY();
					int offX = (nY - pY)* -1;
					int offY = (nX - pX)* -1;
					c.getPA().createPlayersProjectile(nX, nY, offX, offY, 50, getProjectileSpeed(i), npcs[i].projectileId, 43, 31, -c.getId() - 1, 65);					
				}
			}		
		}
	}
	
	public boolean switchesAttackers(int i) {
		switch(npcs[i].npcType) {
			case 2551:
			case 2552:
			case 2553:
			case 2559:
			case 2560:
			case 2561:
			case 2563:
			case 2564:
			case 2565:
			case 2892:
			case 2894:
			return true;
		
		}
	
		return false;
	}
	
	public void multiAttackDamage(int i) {
		int max = getMaxHit(i);
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				Client c = (Client)PlayerHandler.players[j];
				if (c.isDead || c.heightLevel != npcs[i].heightLevel)
					continue;
				if (PlayerHandler.players[j].goodDistance(c.absX, c.absY, npcs[i].absX, npcs[i].absY, 15)) {
					if (npcs[i].attackType == 2) {
						if (!c.prayerActive[16]) {
							if (Misc.random(500) + 200 > Misc.random(c.getCombat().mageDef())) {
								int dam = Misc.random(max);
								c.dealDamage(dam);
								c.handleHitMask(dam);							
							} else {
								c.dealDamage(0);
								c.handleHitMask(0);							
							}
						} else {
							if (Misc.random(500) + 200 > Misc.random(c.getCombat().mageDef())) {
								int dam = Misc.random(max);
								dam = (int)(dam/2);
								c.dealDamage(dam);
								c.handleHitMask(dam);							
							} else {
								c.dealDamage(0);
								c.handleHitMask(0);							
							}
						}
					} else if (npcs[i].attackType == 1) {
						if (!c.prayerActive[17]) {
							if (Misc.random(500) + 200 > Misc.random(c.getCombat().calculateRangeDefence())) {
								int dam = Misc.random(max);
								c.dealDamage(dam);
								c.handleHitMask(dam);							
							} else {
								c.dealDamage(0);
								c.handleHitMask(0);
							}
						} else {
							if (Misc.random(500) + 200 > Misc.random(c.getCombat().calculateRangeDefence())) {
								int dam = Misc.random(max);
								dam = (int)(dam/2);
								c.dealDamage(dam);
								c.handleHitMask(dam);							
							} else {
								c.dealDamage(0);
								c.handleHitMask(0);
							}							
						}
					}
					if (npcs[i].endGfx > 0) {
						c.gfx0(npcs[i].endGfx);					
					}
				}
				c.getPA().refreshSkill(3);
			}		
		}
	}
	
	public int getClosePlayer(int i) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (j == npcs[i].spawnedBy)
					return j;
				if (goodDistance(PlayerHandler.players[j].absX, PlayerHandler.players[j].absY, npcs[i].absX, npcs[i].absY, 2 + distanceRequired(i) + followDistance(i)) || isFightCaveNpc(i)) {
					if ((PlayerHandler.players[j].underAttackBy <= 0 && PlayerHandler.players[j].underAttackBy2 <= 0) || PlayerHandler.players[j].inMulti())
						if (PlayerHandler.players[j].heightLevel == npcs[i].heightLevel)
							return j;
				}
			}	
		}
		return 0;
	}
	
	public int getCloseRandomPlayer(int i) {
		ArrayList<Integer> players = new ArrayList<Integer>();
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null) {
				if (goodDistance(PlayerHandler.players[j].absX, PlayerHandler.players[j].absY, npcs[i].absX, npcs[i].absY, 2 + distanceRequired(i) + followDistance(i)) || isFightCaveNpc(i)) {
					if ((PlayerHandler.players[j].underAttackBy <= 0 && PlayerHandler.players[j].underAttackBy2 <= 0) || PlayerHandler.players[j].inMulti())
						if (PlayerHandler.players[j].heightLevel == npcs[i].heightLevel)
							players.add(j);
				}
			}	
		}
		if (players.size() > 0)
			return players.get(Misc.random(players.size() -1));
		else
			return 0;
	}
	
	public int npcSize(int i) {
		switch (npcs[i].npcType) {
		case 144:
			return 1;
		case 2883:
		case 2882:
		case 2881:
			return 3;
		}
		return 0;
	}
	
	public boolean isAggressive(int i) {
		if (npcs[i].MaxHP > 0)
			return true;
		return false;
	}
	
	public boolean isFightCaveNpc(int i) {
		switch (npcs[i].npcType) {
			case 2627:
			case 2630:
			case 2631:
			case 2741:
			case 2743: 
			case 2745:
			return true;		
		}
		return false;
	}
	
	/**
	* Summon npc, barrows, etc
	**/

	public void spawnOpponent(final Client c, final int npc, final int hp, final int max, final int att, final int def) {
		Timer timer = new Timer();
		int delayTime = 2000; // Milliseconds to wait before running delayed task
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				spawnNpc(c, npc, 2912, 3612, 0, 0, hp, max, att, def, true, true);
			}			
		}, delayTime);
	}

	public void spawnNpc(Client c, int npcType, int x, int y, int heightLevel, int WalkingType, int HP, int maxHit, int attack, int defence, boolean attackPlayer, boolean headIcon) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if(slot == -1) {
			Misc.println("No Free Slot");
			return;		// no free slot found
		}
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		newNPC.spawnedBy = c.getId();
		if(headIcon) 
			c.getPA().drawHeadicon(1, slot, 0, 0);
		if(attackPlayer) {
			if(c != null) {
				if (c.goodDistance(newNPC.absX, newNPC.absY, c.absX, c.absY, 20)) {
					newNPC.underAttack = true;
					newNPC.killerId = c.playerId;
				}
			}
		}
		npcs[slot] = newNPC;
		//System.out.println("Spawned: "+npcType+" "+x+" "+y+" "+heightLevel+" "+WalkingType+" "+HP+" "+maxHit+" "+attack+" "+defence+"");
	}
	
	public void spawnNpc2(int npcType, int x, int y, int heightLevel, int WalkingType, int HP, int maxHit, int attack, int defence) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}
		if(slot == -1) {
			//Misc.println("No Free Slot");
			return;		// no free slot found
		}
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		npcs[slot] = newNPC;
	}
	
	
	
	/**
	* Emotes
	**/
	
	public static int getAttackEmote(int i) {
		switch(NPCHandler.npcs[i].npcType) {
		case 1961:
			return 1926;
		case 1828:
			return 1793;
		case 1608:
			return 1512;
		case 1993:
			return 2039;
		case 1698:
			return 422;
		case 1330:
			return 75;
		case 1219:
			return 1273;
		case 1995:
			return 2011;
		case 1600:
			return 227;
		case 749:
			return 123;
		case 73:
			return 299;
		case 1160:
			return 1177;
		case 2554:
			return 6945;
		case 102:
			return 309;
		case 191:
			return 408;
		case 1593:
			return 75;
			case 709:
			return 169;
			case 1851:
			return 1840;
			case 221:
			return 128;
			case 1184:
			return 2080;
			case 1096:
			return 284;
			case 1540:
			return 123;
			case 938:
			return 265;
			case 374:
			return 359;
			case 116:
			return 128;
			case 1536:
			return 185;
			case 144:
			return 246;
			case 71:
			return 230;
			case 15:
			case 247:
			case 21:
			case 1906:
			case 477:
			case 192:
			case 184:
			case 237:
			case 145:
			return 451;
			case 2550:
				if (npcs[i].attackType == 0)
					return 7060;
				else
					return 7063;	
			case 2892:
			case 2894:
			return 2868;
			case 2627:
			return 2621;
			case 2630:
			return 2625;
			case 2631:
			return 2633;
			case 2741:
			return 2637;
			case 2746:
			return 2637;
			case 2607:
			return 2611;
			case 2743://360
			return 2647;
			//bandos gwd
			case 2551:
			case 2552:
			case 2553:
			return 6154;
			//end of gwd
			//arma gwd
			case 2558:
			return 3505;
			case 2560:
			return 6953;
			case 2559:
			return 6952;
			case 2561:
			return 6954;
			//end of arma gwd
			//sara gwd
			case 2562:
			return 6964;
			case 2563:
			return 6376;
			case 2564:
			return 7018;
			case 2565:
			return 7009;
			//end of sara gwd
			case 13: //wizards
			case 753:
			case 277:
			return 711;
			
			case 190:
			case 913:
				return 811;
			
			case 103:
			case 655:
			return 123;
			
			case 1624:
			return 1557;
			
			case 1648:
			return 1590;
			
			case 2783: //dark beast
			return 2733;
			
			case 1615: //abby demon
			return 1537;
			
			case 1613: //nech
			return 1528;
			
			case 1610: case 1611: //garg
			return 1519;
			
			case 1616: //basilisk
			return 1546;
			
			case 90: //skele
			return 260;
			
			case 50://drags
			case 53:
			case 54:
			case 55:
			case 941:
			case 1590:
			case 1591:
			case 1592:
			return 80;
			
			case 124: //earth warrior
			return 390;
			
			case 803: //monk
			return 422;
			
			case 52: //baby drag
			return 25;			

			case 58: //Shadow Spider
            case 59: //Giant Spider
            case 60: //Giant Spider
            case 61: //Spider
            case 62: //Jungle Spider
            case 63: //Deadly Red Spider
            case 64: //Ice Spider
            case 134:
			return 143;	
			
			case 105: //Bear
            case 106:  //Bear
			return 41;
			
			case 412:
			case 78:
			return 30;
			
			case 2033: //rat
			case 86:
			case 88:
			return 138;	
			
			case 2031: // bloodworm
			return 2070;
			
			case 101: // goblin
			return 309;	
			
			case 81: // cow
			return 0x03B;
			
			
			
			case 41: // chicken
			return 55;	
			
			case 9: // guard
			case 32: // guard
			case 20: // paladin
			return 451;	
			
			case 1338: // dagannoth
			case 1340:
			case 1342:
			return 1341;
		
			case 19: // white knight
			return 406;
			
			case 110:
			case 111: // ice giant
			case 112:
			case 117:
			return 128;
			
			case 2452:
			return 1312;
			
			case 2889:
			return 2859;
			
			case 118:
			case 119:
			return 99;
			
			case 82://Lesser Demon
            case 83://Greater Demon
            case 84://Black Demon
            case 1472://jungle demon
			return 64;
			
			case 1267:
			case 1265:
			return 1312;
			
			case 125: // ice warrior
			case 178:
			return 451;
			
			case 1153: //Kalphite Worker
            case 1154: //Kalphite Soldier
            case 1155: //Kalphite guardian
            case 1156: //Kalphite worker
            case 1157: //Kalphite guardian
			return 1184;
			
			case 123:
			case 122:
			return 164;
			
			case 2028: // karil
			return 2075;
					
			case 2025: // ahrim
			return 729;
			
			case 2026: // dharok
			return 2067;
			
			case 2027: // guthan
			return 2080;
			
			case 2029: // torag
			return 0x814;
			
			case 2030: // verac
			return 2062;
			
			case 2881: //supreme
			return 2855;
			
			case 2882: //prime
			return 2854;
			
			case 2883: //rex
			return 2851;
			
			case 3200:
			return 3146;
			
			case 2745:
			if (npcs[i].attackType == 2)
			return 2656;
			else if (npcs[i].attackType == 1)
			return 2652;
			else if (npcs[i].attackType == 0)
			return 2655;
			
			
			default:
			return 0x326;		
		}
	}	

	
	public int getDeadEmote(int i) {
		switch(npcs[i].npcType) {
		case 1961:
			return 1929;
		case 1828:
			return 1795;
		case 1608:
			return 1513;
		case 1993:
			return 2038;
		case 1330:
			return 78;
		case 1219:
			return 1272;
		case 1995:
			return 2013;
		case 1600:
			return 228;
		case 749:
			return 126;
		case 73:
			return 302;
		case 1160:
			return 1180;
		case 2554:
			return 6946;
		case 102:
			return 313;
		case 1593:
			return 78;
			case 709:
			return 172;
			case 1851:
			return 1841;
			case 221:
			return 131;
			case 1096:
			return 287;
			case 1540:
			return 126;
			case 938:
			return 268;
			case 374:
			return 361;
			case 116:
			return 131;
			case 1536:
			return 188;
			case 144:
			return 248;
			case 71:
			return 233;
			//sara gwd
			case 2562:
			return 6965;
			case 2563:
			return 6377;
			case 2564:
			return 7016;
			case 2565:
			return 7011;
			//bandos gwd
			case 2551:
			case 2552:
			case 2553:
			return 6156;
			case 2550:
			return 7062;
			case 2892:
			case 2894:
			return 2865;
			case 1612: //banshee
			return 1524;
			case 2558:
			return 3503;
			case 2559:
			case 2560:
			case 2561:
			return 6956;
			case 2607:
			return 2607;
			case 2627:
			return 2620;
			case 2630:
			return 2627;
			case 2631:
			return 2630;
			case 2738:
			return 2627;
			case 2741:
			return 2638;
			case 2746:
			return 2638;
			case 2743:
			return 2646;
			case 2745:
			return 2654;
			
			case 3777:
			case 3778:
			case 3779:
			case 3780:
			return -1;
			
			case 3200:
			return 3147;
			
			case 2035: //spider
			return 146;
			
			case 2033:
			case 86:
			case 88: //rat
			return 141;
			
			case 2031: // bloodvel
			return 2073;
			
			case 101: //goblin
			return 313;
			
			case 81: // cow
			return 0x03E;
			
			case 41: // chicken
			return 57;
			
			case 1338: // dagannoth
			case 1340:
			case 1342:
			return 1342;
			
			case 2881:
			case 2882:
			case 2883:
			return 2856;
			
			case 111: // ice giant
			return 131;
			
			case 125: // ice warrior
			return 843;
			
			case 751://Zombies!!
			return 302;
			
			case 1626:
            case 1627:
            case 1628:
            case 1629:
            case 1630:
            case 1631:
            case 1632: //turoth!
            return 1597;
			
			case 1616: //basilisk
            return 1548;
			
			case 1653: //hand
            return 1590;
			
			case 82://demons
			case 83:
			case 84:
			return 67;
			
			case 1605://abby spec
			return 1508;
			
			case 51://baby drags
			case 52:
			case 1589:
			case 3376:
			return 28;
			
			case 1610:
			case 1611:
			return 1518;
			
			case 1618:
			case 1619:
			return 1553;
			
			case 1620: case 1621:
			return 1563;
			
			case 2783:
			return 2732;
			
			case 1615:
			return 1538;
			
			case 1624:
			return 1558;
			
			case 1613:
			return 1530;
			
			case 1633: case 1634: case 1635: case 1636:
			return 1580;
			
			case 1648: case 1649: case 1650: case 1651: case 1652: case 1654: case 1655: case 1656: case 1657:
			return 1590;
			
			case 100:
			return 313;
			
			case 105:
			case 106:
			return 44;
			
			case 412:
			case 78:
			return 36;
			
			case 122:
			case 123:
			return 167;
			
			case 58: case 59: case 60: case 61: case 62: case 63: case 64: case 134:
			return 146;
			
			case 1153: case 1154: case 1155: case 1156: case 1157:
			return 1190;
			
			case 103: case 104:
			return 123;
			
			case 118: case 119:
			return 102;
			
			
			case 50://drags
			case 53:
			case 54:
			case 55:
			case 941:
			case 1590:
			case 1591:
			case 1592:
			return 92;
			
			
			default:
			return 2304;
		}
	}
	
	/**
	* Attack delays
	**/
	public int getNpcDelay(int i) {
		switch(npcs[i].npcType) {
			case 2025:
			case 2028:
			return 7;
			
			case 2745:
			return 8;
			
			case 2558:
			case 2559:
			case 2560:
			case 2561:
			case 2550:
			return 6;
			//saradomin gw boss
			case 2562:
			return 2;
			
			default:
			return 5;
		}
	}
	
	/**
	* Hit delays
	**/
	public int getHitDelay(int i) {
		switch(npcs[i].npcType) {
			case 2881:
			case 2882:
			case 3200:
			case 2892:
			case 2894:
			return 3;
			
			case 2743:
			case 2631:
			case 2558:
			case 2559:
			case 2560:
			return 3;
			
			case 2745:
			if (npcs[i].attackType == 1 || npcs[i].attackType == 2)
				return 5;
			else
				return 2;
			
			case 2025:
			return 4;
			case 2028:
			return 3;

			default:
			return 2;
		}
	}
		
	/**
	* Npc respawn time
	**/
	public int getRespawnTime(int i) {
		switch(npcs[i].npcType) {
			case 2881:
			case 2882:
			case 2883:
			case 2558:
			case 2559:
			case 2560:
			case 2561:
			case 2562:
			case 2563:
			case 2564:
			case 2550:
			case 2551:
			case 2552:
			case 2553:
			return 100;
			case 3777:
			case 3778:
			case 3779:
			case 3780:
			return 500;
			default:
			return 25;
		}
	}
	
	
	
	
	public void newNPC(int npcType, int x, int y, int heightLevel, int WalkingType, int HP, int maxHit, int attack, int defence) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found

		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.heightLevel = heightLevel;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.maxHit = maxHit;
		newNPC.attack = attack;
		newNPC.defence = defence;
		npcs[slot] = newNPC;
	}

	public void newNPCList(int npcType, String npcName, int combat, int HP) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found

		NPCList newNPCList = new NPCList(npcType);
		newNPCList.npcName = npcName;
		newNPCList.npcCombat = combat;
		newNPCList.npcHealth = HP;
		NpcList[slot] = newNPCList;
	}

	

	public void process() {
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] == null) continue;
			npcs[i].clearUpdateFlags();
			
		}
                
		for (int i = 0; i < maxNPCs; i++) {
			if (npcs[i] != null) {
				if (npcs[i].actionTimer > 0) {
					npcs[i].actionTimer--;
				}
				
				if (npcs[i].freezeTimer > 0) {
					npcs[i].freezeTimer--;
				}
				
				if (npcs[i].hitDelayTimer > 0) {
					npcs[i].hitDelayTimer--;
				}
				
				if (npcs[i].hitDelayTimer == 1) {
					npcs[i].hitDelayTimer = 0;
					applyDamage(i);
				}
				
				if(npcs[i].attackTimer > 0) {
					npcs[i].attackTimer--;
				}
					
				if(npcs[i].spawnedBy > 0) { // delete summons npc
					if(PlayerHandler.players[npcs[i].spawnedBy] == null
					|| PlayerHandler.players[npcs[i].spawnedBy].heightLevel != npcs[i].heightLevel	
					/*|| PlayerHandler.players[npcs[i].spawnedBy].respawnTimer > 0 
					|| !PlayerHandler.players[npcs[i].spawnedBy].goodDistance(npcs[i].getX(), npcs[i].getY(), PlayerHandler.players[npcs[i].spawnedBy].getX(), PlayerHandler.players[npcs[i].spawnedBy].getY(), 2000)*/) {
							
						
						npcs[i] = null;
					}
				}
				if (npcs[i] == null) continue;
				
				/**
				* Attacking player
				**/
				if (isAggressive(i) && !npcs[i].underAttack && !npcs[i].isDead && !switchesAttackers(i)) {
					npcs[i].killerId = getCloseRandomPlayer(i);
				} else if (isAggressive(i) && !npcs[i].underAttack && !npcs[i].isDead && switchesAttackers(i)) {
					npcs[i].killerId = getCloseRandomPlayer(i);
				}
				
				if (System.currentTimeMillis() - npcs[i].lastDamageTaken > 5000)
					npcs[i].underAttackBy = 0;
				
				if((npcs[i].killerId > 0 || npcs[i].underAttack) && !npcs[i].walkingHome && retaliates(npcs[i].npcType)) {
					if(!npcs[i].isDead) {
						int p = npcs[i].killerId;
						if(PlayerHandler.players[p] != null) {
							Client c = (Client) PlayerHandler.players[p];					
							followPlayer(i, c.playerId);
							if (npcs[i] == null) continue;
							if(npcs[i].attackTimer == 0) {
								attackPlayer(c, i);
							}
						} else {
							npcs[i].killerId = 0;
							npcs[i].underAttack = false;
							npcs[i].facePlayer(0);
						}
					}
				}
				
				
		
				/**
				* Random walking and walking home
				**/
				if (npcs[i] == null) continue;
				if((!npcs[i].underAttack || npcs[i].walkingHome) && npcs[i].randomWalk && !npcs[i].isDead) {
					npcs[i].facePlayer(0);
					npcs[i].killerId = 0;	
					if(npcs[i].spawnedBy == 0) {
						if((npcs[i].absX > npcs[i].makeX + Config.NPC_RANDOM_WALK_DISTANCE) || (npcs[i].absX < npcs[i].makeX - Config.NPC_RANDOM_WALK_DISTANCE) || (npcs[i].absY > npcs[i].makeY + Config.NPC_RANDOM_WALK_DISTANCE) || (npcs[i].absY < npcs[i].makeY - Config.NPC_RANDOM_WALK_DISTANCE)) {
							npcs[i].walkingHome = true;
						}
					}

					if (npcs[i].walkingHome && npcs[i].absX == npcs[i].makeX && npcs[i].absY == npcs[i].makeY) {
						npcs[i].walkingHome = false;
					} else if(npcs[i].walkingHome) {
						npcs[i].moveX = GetMove(npcs[i].absX, npcs[i].makeX);
			      		npcs[i].moveY = GetMove(npcs[i].absY, npcs[i].makeY);
						npcs[i].getNextNPCMovement(i); 
						npcs[i].updateRequired = true;
					}
					if(npcs[i].walkingType == 1) {
						if(Misc.random(3)== 1 && !npcs[i].walkingHome) {
							int MoveX = 0;
							int MoveY = 0;			
							int Rnd = Misc.random(9);
							if (Rnd == 1) {
								MoveX = 1;
								MoveY = 1;
							} else if (Rnd == 2) {
								MoveX = -1;
							} else if (Rnd == 3) {
								MoveY = -1;
							} else if (Rnd == 4) {
								MoveX = 1;
							} else if (Rnd == 5) {
								MoveY = 1;
							} else if (Rnd == 6) {
								MoveX = -1;
								MoveY = -1;
							} else if (Rnd == 7) {
								MoveX = -1;
								MoveY = 1;
							} else if (Rnd == 8) {
								MoveX = 1;
								MoveY = -1;
							}
										
							if (MoveX == 1) {
								if (npcs[i].absX + MoveX < npcs[i].makeX + 1) {
									npcs[i].moveX = MoveX;
								} else {
									npcs[i].moveX = 0;
								}
							}
							
							if (MoveX == -1) {
								if (npcs[i].absX - MoveX > npcs[i].makeX - 1)  {
									npcs[i].moveX = MoveX;
								} else {
									npcs[i].moveX = 0;
								}
							}
							
							if(MoveY == 1) {
								if(npcs[i].absY + MoveY < npcs[i].makeY + 1) {
									npcs[i].moveY = MoveY;
								} else {
									npcs[i].moveY = 0;
								}
							}
							
							if(MoveY == -1) {
								if(npcs[i].absY - MoveY > npcs[i].makeY - 1)  {
									npcs[i].moveY = MoveY;
								} else {
									npcs[i].moveY = 0;
								}
							}
								

							int x = (npcs[i].absX + npcs[i].moveX);
							int y = (npcs[i].absY + npcs[i].moveY);
							if (VirtualWorld.I(npcs[i].heightLevel, npcs[i].absX, npcs[i].absY, x, y, 0))
								npcs[i].getNextNPCMovement(i);
							else
							{
								npcs[i].moveX = 0;
								npcs[i].moveY = 0;
							} 
							npcs[i].updateRequired = true;
						}
					}
				}
		
				
				if (npcs[i].isDead == true) {
					if (npcs[i].actionTimer == 0 && npcs[i].applyDead == false && npcs[i].needRespawn == false) {
						npcs[i].updateRequired = true;
						npcs[i].facePlayer(0);
						npcs[i].killedBy = getNpcKillerId(i);
						npcs[i].animNumber = getDeadEmote(i); // dead emote
						npcs[i].animUpdateRequired = true;
						npcs[i].freezeTimer = 0;
						npcs[i].applyDead = true;
						killedBarrow(i);
						killedBoss(i);
						duel(i);
						didJob(i);
						if (isFightCaveNpc(i))
							killedTzhaar(i);
						npcs[i].actionTimer = 4; // delete time
						resetPlayersInCombat(i);
					} else if (npcs[i].actionTimer == 0 && npcs[i].applyDead == true &&  npcs[i].needRespawn == false) {						
						npcs[i].needRespawn = true;
						npcs[i].actionTimer = getRespawnTime(i); // respawn time
						dropItems(i); // npc drops items!
						appendSlayerExperience(i);
						appendKillCount(i);
						npcs[i].absX = npcs[i].makeX;
						npcs[i].absY = npcs[i].makeY;				
						npcs[i].HP = npcs[i].MaxHP;
						npcs[i].animNumber = 0x328;
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;
						if (npcs[i].npcType >= 2440 && npcs[i].npcType <= 2446) {
							Server.objectManager.removeObject(npcs[i].absX, npcs[i].absY);
						}
						if (npcs[i].npcType == 2745) {
							handleJadDeath(i);
						}
					} else if (npcs[i].actionTimer == 0 && npcs[i].needRespawn == true) {					
						if(npcs[i].spawnedBy > 0) {
							npcs[i] = null;
						} else {
							int old1 = npcs[i].npcType;
							int old2 = npcs[i].makeX;
							int old3 = npcs[i].makeY;
							int old4 = npcs[i].heightLevel;
							int old5 = npcs[i].walkingType;
							int old6 = npcs[i].MaxHP;
							int old7 = npcs[i].maxHit;
							int old8 = npcs[i].attack;	
							int old9 = npcs[i].defence;
							
							npcs[i] = null;
							newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9);
						}
					}
				}
			}
		}
	}
       
	public boolean getsPulled(int i) {
		switch (npcs[i].npcType) {
			case 2550:
				if (npcs[i].firstAttacker > 0)
					return false;
			break;
		}
		return true;
	}
	   
	public boolean multiAttacks(int i) {
		switch (npcs[i].npcType) {
			case 2558:
			return true;
			case 2562:
			if (npcs[i].attackType == 2)
				return true;
			case 2550:
			if (npcs[i].attackType == 1)
				return true;	
			default:
			return false;
		}
	
	
	}
	
	/**
	* Npc killer id?
	**/
	
	public int getNpcKillerId(int npcId) {
		int oldDamage = 0;
		int killerId = 0;
		for (int p = 1; p < Config.MAX_PLAYERS; p++)  {	
			if (PlayerHandler.players[p] != null) {
				if(PlayerHandler.players[p].lastNpcAttacked == npcId) {
					if(PlayerHandler.players[p].totalDamageDealt > oldDamage) {
						oldDamage = PlayerHandler.players[p].totalDamageDealt;
						killerId = p;
					}
					PlayerHandler.players[p].totalDamageDealt = 0;
				}	
			}
		}				
		return killerId;
	}
		
	/**
	 * 
	 */
	private void killedBarrow(int i) {
		Client c = (Client)PlayerHandler.players[npcs[i].killedBy];
		if(c != null) {
			for(int o = 0; o < c.barrowsNpcs.length; o++){
				if(npcs[i].npcType == c.barrowsNpcs[o][0]) {
					c.barrowsNpcs[o][1] = 2; // 2 for dead
					c.barrowsKillCount++;
				}
			}
		}
	}
	
	private void killedBoss(int i) {
		final Client c = (Client)PlayerHandler.players[npcs[i].killedBy];
		if(c != null) {
			if (npcs[i].npcType == 54 && bossAlive) {
				bossAlive = false;
				Timer timer = new Timer();
				c.sendMessage("You have 1 minute to finish up before you are automatically teleported out!");
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						c.sendMessage("Teleporting!");
						c.getPA().movePlayer(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
					}			
				}, 60000);
			}
		}
	}
	
	private void killedTzhaar(int i) {
		final Client c2 = (Client)PlayerHandler.players[npcs[i].spawnedBy];
		c2.tzhaarKilled++;
		//System.out.println("To kill: " + c2.tzhaarToKill + " killed: " + c2.tzhaarKilled);
		if (c2.tzhaarKilled == c2.tzhaarToKill) {
			//c2.sendMessage("STARTING EVENT");
			c2.waveId++;
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer c) {
					if (c2 != null) {
						Server.fightCaves.spawnNextWave(c2);
					}	
					c.stop();
				}
			}, 7500);
			
		}
	}
	
	public void handleJadDeath(int i) {
		Client c = (Client)PlayerHandler.players[npcs[i].spawnedBy];
		c.getItems().addItem(6570,1);
		c.sendMessage("Congratulations on completing the fight caves minigame!");
		c.getPA().resetTzhaar();
		c.waveId = 300;
	}
	
	
	/**
	* Dropping Items!
	**/
	
	public boolean rareDrops(int i) {
		return Misc.random(NPCDrops.dropRarity.get(npcs[i].npcType)) == 0;
	}
	
	
	public void dropItems(int i) {
		//long start = System.currentTimeMillis();
		Client c = (Client)PlayerHandler.players[npcs[i].killedBy];
		if(c != null) {
			if (npcs[i].npcType == 912 || npcs[i].npcType == 913 || npcs[i].npcType == 914)
				c.magePoints += 1;
			if (NPCDrops.constantDrops.get(npcs[i].npcType) != null) {
				for (int item : NPCDrops.constantDrops.get(npcs[i].npcType)) {
					Server.itemHandler.createGroundItem(c, item, npcs[i].absX, npcs[i].absY, 1, c.playerId);
					//if (c.clanId >= 0)
						//Server.clanChat.handleLootShare(c, item, 1);
				}	
			}
			
			if (NPCDrops.dropRarity.get(npcs[i].npcType) != null) {
				if (rareDrops(i)) {
					int random = Misc.random(NPCDrops.rareDrops.get(npcs[i].npcType).length-1);
					Server.itemHandler.createGroundItem(c, NPCDrops.rareDrops.get(npcs[i].npcType)[random][0], npcs[i].absX, npcs[i].absY, NPCDrops.rareDrops.get(npcs[i].npcType)[random][1], c.playerId);
					if (c.clanId >= 0)
						Server.clanChat.handleLootShare(c, NPCDrops.rareDrops.get(npcs[i].npcType)[random][0], NPCDrops.rareDrops.get(npcs[i].npcType)[random][1]);
				} else {
					int random = Misc.random(NPCDrops.normalDrops.get(npcs[i].npcType).length-1);
					Server.itemHandler.createGroundItem(c, NPCDrops.normalDrops.get(npcs[i].npcType)[random][0], npcs[i].absX, npcs[i].absY, NPCDrops.normalDrops.get(npcs[i].npcType)[random][1], c.playerId);
					Server.clanChat.handleLootShare(c, NPCDrops.normalDrops.get(npcs[i].npcType)[random][0], NPCDrops.normalDrops.get(npcs[i].npcType)[random][1]);
				}
			}	
			
		}
		//System.out.println("Took: " + (System.currentTimeMillis() - start));
	}
	
	public void appendKillCount(int i) {
		Client c = (Client)PlayerHandler.players[npcs[i].killedBy];
		if(c != null) {
			int[] kcMonsters = {122,49,2558,2559,2560,2561,2550,2551,2552,2553,2562,2563,2564,2565};
			for (int j : kcMonsters) {
				if (npcs[i].npcType == j) {
					if (c.killCount < 20) {
						c.killCount++;
						c.sendMessage("Killcount: " + c.killCount);
					} else {
						c.sendMessage("You already have 20 kill count");
					}
					break;
				}
			}
		}	
	}
	
	
	
	
	
	//id of bones dropped by npcs
	public int boneDrop(int type) {
		switch (type) {
			case 1://normal bones
			case 9:
			case 100:
			case 12:
			case 17:
			case 803:
			case 18:
			case 81:
			case 101:
			case 41:
			case 19:
			case 90:
			case 75:
			case 86:
			case 78:
			case 912:
			case 913:
			case 914:
			case 1648:
			case 1643:
			case 1618:
			case 1624:
			case 181:
			case 119:
			case 49:
			case 26:
			case 1341:
			return 526;
			case 117:
			return 532;//big bones
			case 50://drags
			case 53:
			case 54:
			case 55:
			case 941:
			case 1590:
			case 1591:
			case 1592:
			return 536;
			case 84:
			case 1615:
			case 1613:
			case 82:
			case 3200:
			return 592;
			case 2881:
			case 2882:
			case 2883:
			return 6729;
			default:
			return -1;
		}	
	}

	public int getStackedDropAmount(int itemId, int npcId) {
		switch (itemId) {
			case 995:
				switch (npcId) {
					case 1:
					return 50+ Misc.random(50);
					case 9:
					return 133 + Misc.random(100);
					case 1624:
					return 1000 + Misc.random(300);
					case 1618:
					return 1000 + Misc.random(300);
					case 1643:
					return 1000 + Misc.random(300);
					case 1610:
					return 1000 + Misc.random(1000);
					case 1613:
					return 1500 + Misc.random(1250);
					case 1615:
					return 3000;
					case 18:
					return 500;
					case 101:
					return 60;
					case 913:
					case 912:
					case 914:
					return 750 + Misc.random(500);
					case 1612:
					return 250 + Misc.random(500);
					case 1648:
					return 250 + Misc.random(250);
					case 90:
					return 200;
					case 82:
					return 1000 + Misc.random(455);
					case 52:
					return 400 + Misc.random(200);
					case 49:
					return 1500 + Misc.random(2000);
					case 1341:
					return 1500 + Misc.random(500);
					case 26:
					return 500 + Misc.random(100);
					case 20:
					return 750 + Misc.random(100);
					case 21: 
					return 890 + Misc.random(125);
					case 117:
					return 500 + Misc.random(250);
					case 2607:
					return 500 + Misc.random(350);
				}			
			break;
			case 11212:
			return 10 + Misc.random(4);
			case 565:
			case 561:
			return 10;
			case 560:
			case 563:
			case 562:
			return 15;
			case 555:
			case 554:
			case 556:
			case 557:
			return 20;
			case 892:
			return 40;
			case 886:
			return 100;
			case 6522:
			return 6 + Misc.random(5);
			
		}
	
		return 1;
	}
	
	/**
	* Slayer Experience
	**/	
	
	public void didJob(int i) {
		Client c = (Client)PlayerHandler.players[npcs[i].killedBy];
		if(c != null) {
			if (c.playerJob == npcs[i].npcType) {
				c.playerJob = -1;
				c.sendMessage("You've saved the world from disaster! Congratulations! You have earned 100 points.");
				c.points += 100;
				c.refreshInfo();
				c.getPA().addSkillXP(Misc.random(40000)+40000, c.playerSlayer);
				c.getPA().spellTeleport(2852, 2954, 0);
			}
		}
	}

	public int pointsForDifficulty(int i) {
		switch(i) {
			case 1: //man
			case 709: //imp
			return Misc.random(2)+1; //1-3
			case 13: //wizard
			case 192: //dark warrior
			return Misc.random(5)+1; //1-6
			case 88: //rat
			case 102: //goblin
			return Misc.random(7)+1; //1-8
			case 71: //orc
			case 106: //bear
			return Misc.random(11)+1; //1-12
			case 15: //warrior woman
			case 184: //pirate
			return Misc.random(13)+1; //1-14
			case 144: //king scorpion
			case 191: //tribesman
			return Misc.random(16)+1; //1-17
			case 247: //moldred
			case 237: //renegade knight
			return Misc.random(14)+5; //5-19
			case 753: //melzar
			case 190: //zammy monk
			return Misc.random(17)+5; //5-22
			case 1536: //pickaxe
			case 913: //battle mage
			return Misc.random(19)+5; //5-24
			case 116: //cyclops
			case 145: //ice warrior
			return Misc.random(17)+10; //10-27
			case 374: //ogre
			case 1593: //wild dog
			return Misc.random(19)+10; //10-29
			case 21: //hero
			return Misc.random(17)+15; //15-32
			case 1906: //tough guy
			return Misc.random(20)+15; //15-35
			case 277: //fire warrior of lesarkus
			return Misc.random(24)+15; //15-39
			case 938: //randolph devere
			return Misc.random(23)+20; //20-43
			case 1540: //treus dayth
			return Misc.random(27)+20; //20-47
			case 1096: //stick
			return Misc.random(30)+20; //20-50
			case 1184: //elf warrior
			return Misc.random(30)+25; //25-55
			case 477: //khazard warlord
			return Misc.random(35)+25; //25-60
			case 221: //black knight titan
			return Misc.random(40)+25; //25-65
			case 1851: //arzinian avatar of strength
			return 75; //75
			default:
			return 0;
		}
	}

	public void duel(int i) {
		int pointsGiven = 0;
		Client c = (Client)PlayerHandler.players[npcs[i].killedBy];
		if(c != null) {
			if (c.npcDuelingWith != null) {
				if (c.npcDuelingWith.id == npcs[i].npcType) {
					updateDeaths(c.npcDuelingWith.id);
					c.npcKills++;
					c.npcStreak++;
					pointsGiven = pointsForDifficulty(c.npcDuelingWith.id);
					c.points += pointsGiven;
					c.sendMessage("You have defeated "+Misc.ucFirst(getNpcListName(c.npcDuelingWith.id)).replace("_"," ")+"!");
					c.sendMessage("You have received "+pointsGiven+" points for your victory.");
					c.getPA().movePlayer(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
					c.currentSeed++;
					if (c.betsMade >= 5) {
						c.killsBeforeBet++;
						if (c.killsBeforeBet >= 10) {
							c.sendMessage("You are now eligible to bet on more arena matches.");
							c.killsBeforeBet = 0;
							c.betsMade = 0;
						}
					}
					if (c.npcDuelingWith.id == 1851) {
						c.sendMessage("You are the arena champion! Congratulations!");
						c.currentSeed = 0;
						npcChampion = Misc.ucFirst(c.playerName);
					}
					if (c.npcKills >= 10 && c.npcRank == 0) {
						c.sendMessage("Congratulations! You have achieved the Novice rank in the PvN Arena!");
						c.npcRank++;
					}
					if (c.npcKills >= 50 && c.npcRank == 1) {
						c.sendMessage("Congratulations! You have achieved the Veteran rank in the PvN Arena!");
						c.npcRank++;
					}
					if (c.npcKills >= 200 && c.npcRank == 2) {
						c.sendMessage("Congratulations! You have achieved the Reknowned rank in the PvN Arena!");
						c.npcRank++;
					}
					if (c.npcKills >= 500 && c.npcRank == 3) {
						c.sendMessage("Congratulations! You have achieved the Hero rank in the PvN Arena!");
						c.npcRank++;
					}
					if (c.npcKills >= 1000 && c.npcRank == 4 && c.combatLevel == 126 && c.npcDuelingWith.id == 1851) {
						c.sendMessage("Congratulations! You have achieved the Legend rank in the PvN Arena!");
						c.sendMessage("You have been blessed with the ability of catspeech!");
						c.npcRank++;
					}
					c.refreshInfo();
					c.npcDuelingWith = null;
					c.getPA().brawl(NPCRanks.getRandomOpponent(), NPCRanks.getRandomOpponent(), false);
					//System.out.println(NPCRanks.getRandomOpponent().name+" "+NPCRanks.getRandomOpponent().name);
				}
			}
		}
	}

	public void updateDeaths(int i) {
		switch(i) {
			case 1: //man
			manDeaths++;
			break;
			case 709:
			impDeaths++;
			break;
			case 13:
			wizardDeaths++;
			break;
			case 192:
				warriorDeaths++;
				break;
			case 88:
			ratDeaths++;
			break;
			case 102:
				goblinDeaths++;
				break;
			case 71:
			orcDeaths++;
			break;
			case 106:
				bearDeaths++;
				break;
			case 15:
			womanDeaths++;
			break;
			case 184:
				pirateDeaths++;
				break;
			case 144:
			scorpionDeaths++;
			break;
			case 191:
				tribesmanDeaths++;
				break;
			case 247:
			moldredDeaths++;
			break;
			case 237:
				knightDeaths++;
				break;
			case 753:
			melzarDeaths++;
			break;
			case 190:
				monkDeaths++;
				break;
			case 1536:
			pickaxeDeaths++;
			break;
			case 913:
				battleDeaths++;
				break;
			case 116:
			cyclopsDeaths++;
			break;
			case 145:
				iceDeaths++;
				break;
			case 374:
			ogreDeaths++;
			break;
			case 1593:
				dogDeaths++;
				break;
			case 21:
			heroDeaths++;
			break;
			case 1906:
			toughDeaths++;
			break;
			case 277:
			lesarkusDeaths++;
			break;
			case 938:
			devereDeaths++;
			break;
			case 1540:
			daythDeaths++;
			break;
			case 1096:
			stickDeaths++;
			break;
			case 1184:
			elfDeaths++;
			break;
			case 477:
			warlordDeaths++;
			break;
			case 221:
			titanDeaths++;
			break;
			case 1851:
			arzDeaths++;
			break;
			default:
			System.out.println("hi");
			break;
		}
		saveNPCStats();
	}

	public void updateKills(int i) {
		switch(i) {
			case 1: //man
			manKills++;
			break;
			case 709:
			impKills++;
			break;
			case 13:
			wizardKills++;
			break;
			case 192:
				warriorKills++;
				break;
			case 88:
			ratKills++;
			break;
			case 102:
				goblinKills++;
				break;
			case 71:
			orcKills++;
			break;
			case 106:
				bearKills++;
				break;
			case 15:
			womanKills++;
			break;
			case 184:
				pirateKills++;
				break;
			case 144:
			scorpionKills++;
			break;
			case 191:
				tribesmanKills++;
				break;
			case 247:
			moldredKills++;
			break;
			case 237:
				knightKills++;
				break;
			case 753:
			melzarKills++;
			break;
			case 190:
				monkKills++;
				break;
			case 1536:
			pickaxeKills++;
			break;
			case 913:
				battleKills++;
				break;
			case 116:
			cyclopsKills++;
			break;
			case 145:
				iceKills++;
				break;
			case 374:
			ogreKills++;
			break;
			case 1593:
				dogKills++;
				break;
			case 21:
			heroKills++;
			break;
			case 1906:
			toughKills++;
			break;
			case 277:
			lesarkusKills++;
			break;
			case 938:
			devereKills++;
			break;	
			case 1540:
			daythKills++;
			break;
			case 1096:
			stickKills++;
			break;
			case 1184:
			elfKills++;
			break;
			case 477:
			warlordKills++;
			break;
			case 221:
			titanKills++;
			break;
			case 1851:
			arzKills++;
			break;
			default:
			System.out.println("hi2");
			break;
		}
		saveNPCStats();
	}

	public void appendSlayerExperience(int i) {
		Client c = (Client)PlayerHandler.players[npcs[i].killedBy];
		if(c != null) {
			if (c.slayerTask == npcs[i].npcType){
				c.taskAmount--;
				c.getPA().addSkillXP(npcs[i].MaxHP * Config.SLAYER_EXPERIENCE, 18);
				if (c.taskAmount <= 0) {
					c.getPA().addSkillXP((npcs[i].MaxHP * 8) * Config.SLAYER_EXPERIENCE, 18);
					c.slayerTask = -1;
					c.sendMessage("You completed your slayer task. Please see a slayer master to get a new one.");
				}
			}
		}
	}
	
	/**
	 *	Resets players in combat
	 */
	
	public void resetPlayersInCombat(int i) {
		for (int j = 0; j < PlayerHandler.players.length; j++) {
			if (PlayerHandler.players[j] != null)
				if (PlayerHandler.players[j].underAttackBy2 == i)
					PlayerHandler.players[j].underAttackBy2 = 0;
		}
	}
	
	
	/**
	* Npc Follow Player
	**/
	
	public int GetMove(int Place1,int Place2) { 
		if ((Place1 - Place2) == 0) {
            return 0;
		} else if ((Place1 - Place2) < 0) {
			return 1;
		} else if ((Place1 - Place2) > 0) {
			return -1;
		}
        	return 0;
   	 }
	
	public boolean followPlayer(int i) {
		switch (npcs[i].npcType) {
			case 2892:
			case 2894:
			return false;
		}
		return true;
	}
	
	public void followPlayer(int i, int playerId) {
		if (PlayerHandler.players[playerId] == null) {
			return;
		}
		if (PlayerHandler.players[playerId].respawnTimer > 0) {
			npcs[i].facePlayer(0);
			npcs[i].randomWalk = true; 
	      	npcs[i].underAttack = false;	
			return;
		}
		
		if (!followPlayer(i)) {
			npcs[i].facePlayer(playerId);
			return;
		}
		
		int playerX = PlayerHandler.players[playerId].absX;
		int playerY = PlayerHandler.players[playerId].absY;
		npcs[i].randomWalk = false;
		if (goodDistance(npcs[i].getX(), npcs[i].getY(), playerX, playerY, distanceRequired(i)))
			return;
		if((npcs[i].spawnedBy > 0) || ((npcs[i].absX < npcs[i].makeX + Config.NPC_FOLLOW_DISTANCE) && (npcs[i].absX > npcs[i].makeX - Config.NPC_FOLLOW_DISTANCE) && (npcs[i].absY < npcs[i].makeY + Config.NPC_FOLLOW_DISTANCE) && (npcs[i].absY > npcs[i].makeY - Config.NPC_FOLLOW_DISTANCE))) {
			if(npcs[i].heightLevel == PlayerHandler.players[playerId].heightLevel) {
				if(PlayerHandler.players[playerId] != null && npcs[i] != null) {
					if(playerY < npcs[i].absY) {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if(playerY > npcs[i].absY) {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if(playerX < npcs[i].absX) {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if(playerX > npcs[i].absX)  {
						npcs[i].moveX = GetMove(npcs[i].absX, playerX);
						npcs[i].moveY = GetMove(npcs[i].absY, playerY);
					} else if(playerX == npcs[i].absX || playerY == npcs[i].absY) {
						int o = Misc.random(3);
						switch(o) {
							case 0:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY+1);
							break;
							
							case 1:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY-1);
							break;
							
							case 2:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX+1);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY);
							break;
							
							case 3:
							npcs[i].moveX = GetMove(npcs[i].absX, playerX-1);
							npcs[i].moveY = GetMove(npcs[i].absY, playerY);
							break;
						}	
					}
					npcs[i].facePlayer(playerId);
					if (checkClipping(i))
						npcs[i].getNextNPCMovement(i);
					else {
						npcs[i].moveX = 0;
						npcs[i].moveY = 0;
					}
					npcs[i].facePlayer(playerId);
			      	npcs[i].updateRequired = true;
				}	
			}
		} else {
			npcs[i].facePlayer(0);
			npcs[i].randomWalk = true; 
		   	npcs[i].underAttack = false;	
		}
	}
	
	
	public boolean checkClipping(int i) {
		NPC npc = npcs[i];
		int size = npcSize(i);
		
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (!VirtualWorld.I(npc.heightLevel, npc.absX + x, npc.absY + y, npc.absX + npc.moveX, npc.absY + npc.moveY, 0))
					return false;				
			}
		}
		return true;
	}
	
	/**
	* load spell
	**/
	public void loadSpell2(int i) {
		npcs[i].attackType = 3;
		int random = Misc.random(3);
		if (random == 0) {
			npcs[i].projectileId = 393; //red
			npcs[i].endGfx = 430;
		} else if (random == 1) {
			npcs[i].projectileId = 394; //green
			npcs[i].endGfx = 429;
		} else if (random == 2) {
			npcs[i].projectileId = 395; //white
			npcs[i].endGfx = 431;
		} else if (random == 3) {
			npcs[i].projectileId = 396; //blue
			npcs[i].endGfx = 428;
		}
	}
	
	public void loadSpell(int i) {
		switch(npcs[i].npcType) {
			case 13:
			npcs[i].projectileId = 91;
			npcs[i].attackType = 2;
			npcs[i].endGfx = 92;
			break;
			case 753:
			npcs[i].projectileId = 127;
			npcs[i].attackType = 2;
			npcs[i].endGfx = 128;
			break;
			case 190:
				npcs[i].projectileId = -1;
				npcs[i].attackType = 2;
				npcs[i].endGfx = 78;
				break;
			case 913:
				npcs[i].projectileId = -1;
				npcs[i].attackType = 2;
				npcs[i].endGfx = 76;
				break;
			case 277:
			npcs[i].projectileId = 130;
			npcs[i].attackType = 2;
			npcs[i].endGfx = 131;
			break;
			case 2892:
			npcs[i].projectileId = 94;
			npcs[i].attackType = 2;
			npcs[i].endGfx = 95;
			break;
			case 2894:
			npcs[i].projectileId = 298;
			npcs[i].attackType = 1;
			break;
			case 50:
			int random = Misc.random(4);
			if (random == 0) {
				npcs[i].projectileId = 393; //red
				npcs[i].endGfx = 430;
				npcs[i].attackType = 3;
			} else if (random == 1) {
				npcs[i].projectileId = 394; //green
				npcs[i].endGfx = 429;
				npcs[i].attackType = 3;
			} else if (random == 2) {
				npcs[i].projectileId = 395; //white
				npcs[i].endGfx = 431;
				npcs[i].attackType = 3;
			} else if (random == 3) {
				npcs[i].projectileId = 396; //blue
				npcs[i].endGfx = 428;
				npcs[i].attackType = 3;
			} else if (random == 4) {
				npcs[i].projectileId = -1; //melee
				npcs[i].endGfx = -1;
				npcs[i].attackType = 0;				
			}			
			break;
			case 54:
				random = Misc.random(1);
				if (random == 0) {
					npcs[i].projectileId = 393; //red
					npcs[i].endGfx = 430;
					npcs[i].attackType = 3;
				} else if (random == 1) {
					npcs[i].projectileId = -1; //melee
					npcs[i].endGfx = -1;
					npcs[i].attackType = 0;				
				}			
				break;
			//arma npcs
			case 2561:
				npcs[i].attackType = 0;
			break;
			case 2560:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 1190;
			break;
			case 2559:
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1203;
			break;
			case 2558:
				random = Misc.random(1);
				npcs[i].attackType = 1 + random;
				if (npcs[i].attackType == 1) {
					npcs[i].projectileId = 1197;				
				} else {
					npcs[i].attackType = 2;
					npcs[i].projectileId = 1198;
				}	
			break;
			//sara npcs
			case 2562: //sara
				random = Misc.random(1);
				if (random == 0) {
					npcs[i].attackType = 2;
					npcs[i].endGfx = 1224;
					npcs[i].projectileId = -1;
				} else if (random == 1)
					npcs[i].attackType = 0;
			break;
			case 2563: //star
				npcs[i].attackType = 0;
			break;
			case 2564: //growler
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1203;
			break;
			case 2565: //bree
				npcs[i].attackType = 1;
				npcs[i].projectileId = 9;
			break;
			//bandos npcs
			case 2550:
				random = Misc.random(2);
				if (random == 0 || random == 1)
					npcs[i].attackType = 0;
				else {
					npcs[i].attackType = 1;
					npcs[i].endGfx = 1211;
					npcs[i].projectileId = 288;
				}
			break;
			case 2551:
				npcs[i].attackType = 0;
			break;
			case 2552:
				npcs[i].attackType = 2;
				npcs[i].projectileId = 1203;
			break;
			case 2553:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 1206;
			break;
			case 2025:
			npcs[i].attackType = 2;
			int r = Misc.random(3);
			if(r == 0) {
				npcs[i].gfx100(158);
				npcs[i].projectileId = 159;
				npcs[i].endGfx = 160;
			}
			if(r == 1) {
				npcs[i].gfx100(161);
				npcs[i].projectileId = 162;
				npcs[i].endGfx = 163;
			}
			if(r == 2) {
				npcs[i].gfx100(164);
				npcs[i].projectileId = 165;
				npcs[i].endGfx = 166;
			}
			if(r == 3) {
				npcs[i].gfx100(155);
				npcs[i].projectileId = 156;
			}
			break;
			case 2881://supreme
				npcs[i].attackType = 1;
				npcs[i].projectileId = 298;
			break;
			
			case 2882://prime
				npcs[i].attackType = 2;
				npcs[i].projectileId = 162;
				npcs[i].endGfx = 477;
			break;
			
			case 2028:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 27;
			break;
			
			case 3200:
			int r2 = Misc.random(1);
			if (r2 == 0) {
				npcs[i].attackType = 1;
				npcs[i].gfx100(550);
				npcs[i].projectileId = 551;
				npcs[i].endGfx = 552;
			} else {
				npcs[i].attackType = 2;
				npcs[i].gfx100(553);
				npcs[i].projectileId = 554;
				npcs[i].endGfx = 555;
			}
			break;
			case 2745:
			int r3 = 0;
			if (goodDistance(npcs[i].absX, npcs[i].absY, PlayerHandler.players[npcs[i].spawnedBy].absX, PlayerHandler.players[npcs[i].spawnedBy].absY, 1))
				r3 = Misc.random(2);
			else
				r3 = Misc.random(1);
			if (r3 == 0) {
				npcs[i].attackType = 2;
				npcs[i].endGfx = 157;
				npcs[i].projectileId = 448;
			} else if (r3 == 1) {
				npcs[i].attackType = 1;
				npcs[i].endGfx = 451;
				npcs[i].projectileId = -1;
			} else if (r3 == 2) {
				npcs[i].attackType = 0;
				npcs[i].projectileId = -1;
			}			
			break;
			case 2743:
				npcs[i].attackType = 2;
				npcs[i].projectileId = 445;
				npcs[i].endGfx = 446;
			break;
			
			case 2631:
				npcs[i].attackType = 1;
				npcs[i].projectileId = 443;
			break;
		}
	}
		
	/**
	* Distanced required to attack
	**/	
	public int distanceRequired(int i) {
		switch(npcs[i].npcType) {
			case 2025:
			case 2028:
			return 6;
			case 50:
			case 2562:
			case 54:
			return 2;
			case 2881://dag kings
			case 2882:
			case 3200://chaos ele
			case 2743:
			case 2631:
			case 2745:
			case 13:
			case 753:
			case 190:
			case 913:
			case 277:
			return 8;
			case 2883://rex
			return 1;
			case 2552:
			case 2553:
			case 2556:
			case 2557:
			case 2558:
			case 2559:
			case 2560:
			case 2564:
			case 2565:
			return 9;
			//things around dags
			case 2892:
			case 2894:
			return 10;
			default:
			return 1;
		}
	}
	
	public int followDistance(int i) {
		switch (npcs[i].npcType) {
			case 2550:
			case 2551:
			case 2562:
			case 2563:
			return 8;
			case 2883:
			return 4;
			case 2881:
			case 2882:
			return 1;
		
		}
		return 0;
		
	
	}
	
	public int getProjectileSpeed(int i) {
		switch(npcs[i].npcType) {
			case 2881:
			case 2882:
			case 3200:
			return 85;
			
			case 2745:
			return 130;
			
			case 50:
			case 54:
			return 90;
			
			case 2025:
			return 85;
			
			case 2028:
			return 80;
			
			default:
			return 85;
		}
	}
	
	/**
	*NPC Attacking Player
	**/
	
	public void attackPlayer(Client c, int i) {
		if(npcs[i] != null) {
			if (npcs[i].isDead)
				return;
			if (!npcs[i].inMulti() && npcs[i].underAttackBy > 0 && npcs[i].underAttackBy != c.playerId) {
				npcs[i].killerId = 0;
				return;
			}
			if (!npcs[i].inMulti() && (c.underAttackBy > 0 || (c.underAttackBy2 > 0 && c.underAttackBy2 != i))) {
				npcs[i].killerId = 0;
				return;
			}
			if (npcs[i].heightLevel != c.heightLevel) {
				npcs[i].killerId = 0;
				return;
			}
			npcs[i].facePlayer(c.playerId);
			boolean special = false;//specialCase(c,i);
			if(goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(), c.getY(), distanceRequired(i)) || special) {
				if(c.respawnTimer <= 0) {
					npcs[i].facePlayer(c.playerId);
					npcs[i].attackTimer = getNpcDelay(i);
					npcs[i].hitDelayTimer = getHitDelay(i);
					npcs[i].attackType = 0;
					if (special)
						loadSpell2(i);
					else
						loadSpell(i);
					if (npcs[i].attackType == 3)
						npcs[i].hitDelayTimer += 2;
					if (multiAttacks(i)) {
						multiAttackGfx(i, npcs[i].projectileId);
						startAnimation(getAttackEmote(i), i);
						npcs[i].oldIndex = c.playerId;
						return;
					}
					if(npcs[i].projectileId > 0) {
						int nX = NPCHandler.npcs[i].getX() + offset(i);
						int nY = NPCHandler.npcs[i].getY() + offset(i);
						int pX = c.getX();
						int pY = c.getY();
						int offX = (nY - pY)* -1;
						int offY = (nX - pX)* -1;
						c.getPA().createPlayersProjectile(nX, nY, offX, offY, 50, getProjectileSpeed(i), npcs[i].projectileId, 43, 31, -c.getId() - 1, 65);
					}
					c.underAttackBy2 = i;
					c.singleCombatDelay2 = System.currentTimeMillis();
					npcs[i].oldIndex = c.playerId;
					startAnimation(getAttackEmote(i), i);
					c.getPA().removeAllWindows();
				} 
			}			
		}
	}
	
	public int offset(int i) {
		switch (npcs[i].npcType) {
			case 50:
			case 54:
			return 2;
			case 2881:
			case 2882:
			return 1;
			case 2745:
			case 2743:
			return 1;		
		}
		return 0;
	}
	
	public boolean specialCase(Client c, int i) { //responsible for npcs that much 
		if (goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(), c.getY(), 8) && !goodDistance(npcs[i].getX(), npcs[i].getY(), c.getX(), c.getY(), distanceRequired(i)))
			return true;
		return false;
	}
	
	public boolean retaliates(int npcType) {
		return npcType < 3777 || npcType > 3780 && !(npcType >= 2440 && npcType <= 2446);
	}
	
	public void applyDamage(int i) {
		if(npcs[i] != null) {
			if(PlayerHandler.players[npcs[i].oldIndex] == null) {
				return;
			}
			if (npcs[i].isDead)
				return;
			Client c = (Client) PlayerHandler.players[npcs[i].oldIndex];
			if (multiAttacks(i)) {
				multiAttackDamage(i);
				return;
			}
			if (c.playerIndex <= 0 && c.npcIndex <= 0)
				if (c.autoRet == 1)
					c.npcIndex = i;
			if(c.attackTimer <= 3 || c.attackTimer == 0 && c.npcIndex == 0 && c.oldNpcIndex == 0) {
				c.startAnimation(c.getCombat().getBlockEmote());
			}
			if(c.respawnTimer <= 0) {	
				int damage = 0;
				if(npcs[i].attackType == 0) {
					damage = Misc.random(npcs[i].maxHit);
					if (10 + Misc.random(c.getCombat().calculateMeleeDefence()) > Misc.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
					}				
					if(c.prayerActive[18]) { // protect from melee
						damage = (int)(damage/2);
					}				
					if (c.playerLevel[3] - damage < 0) { 
						damage = c.playerLevel[3];
					}
				}
				
				if(npcs[i].attackType == 1) { // range
					damage = Misc.random(npcs[i].maxHit);
					if (10 + Misc.random(c.getCombat().calculateRangeDefence()) > Misc.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
					}					
					if(c.prayerActive[17]) { // protect from range
						damage = (int)(damage/2);
					}				
					if (c.playerLevel[3] - damage < 0) { 
						damage = c.playerLevel[3];
					}
				}
				
				if(npcs[i].attackType == 2) { // magic
					damage = Misc.random(npcs[i].maxHit);
					boolean magicFailed = false;
					if (10 + Misc.random(c.getCombat().mageDef()) > Misc.random(NPCHandler.npcs[i].attack)) {
						damage = 0;
						magicFailed = true;
					}				
					if(c.prayerActive[16]) { // protect from magic
						damage = (int)(damage/2);
					}				
					if (c.playerLevel[3] - damage < 0) { 
						damage = c.playerLevel[3];
					}
					if (damage == 0) {
						magicFailed = true;
					}
					if(npcs[i].endGfx > 0 && (!magicFailed || isFightCaveNpc(i))) {
						c.gfx100(npcs[i].endGfx);
					} else {
						c.gfx100(85);
					}
				}
				
				if (npcs[i].attackType == 3) { //fire breath
					int anti = c.getPA().antiFire();
					if (anti == 0) {
						damage = Misc.random(30) + 10;
						c.sendMessage("You are badly burnt by the dragon fire!");
					} else if (anti == 1)
						damage = Misc.random(20);
					else if (anti == 2)
						damage = Misc.random(5);
					if (c.playerLevel[3] - damage < 0)
						damage = c.playerLevel[3];
					c.gfx100(npcs[i].endGfx);
				}
				handleSpecialEffects(c, i, damage);
				c.logoutDelay = System.currentTimeMillis(); // logout delay
				//c.setHitDiff(damage);
				c.handleHitMask(damage);
				c.playerLevel[3] -= damage;
				c.getPA().refreshSkill(3);
				c.updateRequired = true;
				//c.setHitUpdateRequired(true);	
				
			}
		}
	}
	
	public void handleSpecialEffects(Client c, int i, int damage) {
		if (npcs[i].npcType == 2892 || npcs[i].npcType == 2894) {
			if (damage > 0) {
				if (c != null) {
					if (c.playerLevel[5] > 0) {
						c.playerLevel[5]--;
						c.getPA().refreshSkill(5);
						c.getPA().appendPoison(12);
					}
				}			
			}	
		}
	
	}
		
		

	public void startAnimation(int animId, int i) {
		npcs[i].animNumber = animId;
		npcs[i].animUpdateRequired = true;
		npcs[i].updateRequired = true;
	}
	
	public boolean goodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
		  for (int j = 0; j <= distance; j++) {
			if ((objectX + i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if ((objectX - i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if (objectX == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			}
		  }
		}
		return false;
	}
	
      
	public int getMaxHit(int i) {
		switch (npcs[i].npcType) {
			case 2558:
				if (npcs[i].attackType == 2)
					return 28;
				else
					return 68;
			case 2562:
				return 31;
			case 2550:
				return 36;
		}
		return 1;
	}
	
	
	public boolean loadAutoSpawn(String FileName) throws IOException {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			Misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			Misc.println(FileName+": error loading file.");
			characterfile.close();
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
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("spawn")) {
					newNPC(Integer.parseInt(token3[0]), Integer.parseInt(token3[1]), Integer.parseInt(token3[2]), Integer.parseInt(token3[3]), Integer.parseInt(token3[4]), getNpcListHP(Integer.parseInt(token3[0])), Integer.parseInt(token3[5]), Integer.parseInt(token3[6]), Integer.parseInt(token3[7]));
				
				}
			} else {
				if (line.equals("[ENDOFSPAWNLIST]")) {
					characterfile.close(); 
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}

	public int getNpcListHP(int npcId) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == npcId) {
					return NpcList[i].npcHealth;
				}
			}
		}
		return 0;
	}
	
	public String getNpcListName(int npcId) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == npcId) {
					return NpcList[i].npcName;
				}
			}
		}
		return "nothing";
	}

	public boolean loadNPCList(String FileName) throws IOException {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			Misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			Misc.println(FileName+": error loading file.");
			characterfile.close();
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
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("npc")) {
					newNPCList(Integer.parseInt(token3[0]), token3[1], Integer.parseInt(token3[2]), Integer.parseInt(token3[3]));
				}
			} else {
				if (line.equals("[ENDOFNPCLIST]")) {
					characterfile.close();
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}
	

}
