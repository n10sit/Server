package server.model.players.skills;

import server.model.players.*;
import server.Config;
import server.util.Misc;

/**
* @Author Sanity
*/

public class Woodcutting {
	
	Client c;
	
	public final int VALID_AXE[] = {1349,1353,1361,1355,1357,1359,6739}; //iron steel black mith addy rune drag
	private final int[] AXE_REQS =  {1,   6,   11,  21,  31,  41,  61};
	private int logType;
	private int exp;
	@SuppressWarnings("unused")
	private int levelReq;
	private int axeType;
	//private final int EMOTE = 875;
	
	public Woodcutting(Client c) {
		this.c = c;
	}
	
	public int getEmote(int axe) {
		switch(axe) {
		case 1349:
			return 877;
		case 1353:
			return 875;
		case 1361:
			return 873;
		case 1355:
			return 871;
		case 1357:
			return 869;
		case 1359:
			return 867;
		case 6739:
			return 2846;
		default:
			return -1;
		}
	}
	
	public void startWoodcutting(int logType, int levelReq, int exp) {
		if (goodAxe() > 0) {
			c.turnPlayerTo(c.objectX, c.objectY);
			if (c.playerLevel[c.playerWoodcutting] >= levelReq) {
				this.logType = logType;
				this.exp = exp;
				this.levelReq = levelReq;
				this.axeType = goodAxe();
				c.wcTimer = getWcTimer();
				c.startAnimation(getEmote(axeType));
			} else {
				c.getPA().resetVariables();
				c.startAnimation(65535);
				c.sendMessage("You need a woodcutting level of " + levelReq + " to cut this tree.");
			}		
		} else {
			c.startAnimation(65535);
			c.sendMessage("You need an axe to cut this tree.");
			c.getPA().resetVariables();
		}
	}
	
	public void resetWoodcut() {
		this.logType = -1;
		this.exp = -1;
		this.levelReq = -1;
		this.axeType = -1;
		c.wcTimer = -1;	
	}
	
	public void cutWood() {
		if (c.getItems().addItem(logType,1)) {
			c.startAnimation(getEmote(axeType));
			c.sendMessage("You get some logs.");
			c.getPA().addSkillXP(exp * Config.WOODCUTTING_EXPERIENCE, c.playerWoodcutting);
			c.getPA().refreshSkill(c.playerWoodcutting);
			c.wcTimer = getWcTimer();
		} else {
			c.getPA().resetVariables();
		}
	}
	
	public int goodAxe() {
		for (int j = VALID_AXE.length - 1; j >= 0; j--) {
			if (c.playerEquipment[c.playerWeapon] == VALID_AXE[j]) {
				if (c.playerLevel[c.playerWoodcutting] >= AXE_REQS[j])
					return VALID_AXE[j];
			}		
		}
		for (int i = 0; i < c.playerItems.length; i++) {
			for (int j = VALID_AXE.length - 1; j >= 0; j--) {
				if (c.playerItems[i] == VALID_AXE[j] + 1) {
					if (c.playerLevel[c.playerWoodcutting] >= AXE_REQS[j])
						return VALID_AXE[j];
				}
			}		
		}
		return - 1;
	}
	
	
	
	public int getWcTimer() {
		int time = Misc.random(5);
		return time;
	}

}