package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.items.GameItem;
import server.model.npcs.NPCRanks;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.util.Misc;

/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		//int actionButtonId = c.getInStream().readShort();
		if (c.isDead)
			return;
		if(c.playerRights == 3)	
			Misc.println(c.playerName+ " - actionbutton: "+actionButtonId);
		switch (actionButtonId){
			//crafting + fletching interface:
			case 150:
				if (c.autoRet == 0)
					c.autoRet = 1;
				else 
					c.autoRet = 0;
			break;

			case 9167:
				if (c.dialogueAction == 32) {
					c.getItems().addItem(1167, 1);
					c.getItems().addItem(1129, 1);
					c.getItems().addItem(1095, 1);
					c.getItems().addItem(1063, 1);
					c.getItems().addItem(1061, 1);
					c.getItems().addItem(841, 1);
					c.getItems().addItem(884, 1000);
					c.chosenClass = true;
				}
				c.getPA().closeAllWindows();
				c.dialogueAction = -1;
			break;

			case 9168:
				if (c.dialogueAction == 32) {
					c.getItems().addItem(579, 1);
					c.getItems().addItem(577, 1);
					c.getItems().addItem(1011, 1);
					c.getItems().addItem(1379, 1);
					c.getItems().addItem(1059, 1);
					c.getItems().addItem(1061, 1);
					c.getItems().addItem(554, 500);
					c.getItems().addItem(555, 500);
					c.getItems().addItem(556, 1000);
					c.getItems().addItem(557, 500);
					c.getItems().addItem(558, 1000);
					c.getItems().addItem(559, 500);
					c.chosenClass = true;
				}
				c.getPA().closeAllWindows();
				c.dialogueAction = -1;
			break;

			case 9169:
				if (c.dialogueAction == 32) {
					c.getItems().addItem(1153, 1);
					c.getItems().addItem(1115, 1);
					c.getItems().addItem(1067, 1);
					c.getItems().addItem(1191, 1);
					c.getItems().addItem(1323, 1);
					c.getItems().addItem(1059, 1);
					c.getItems().addItem(1061, 1);
					c.getItems().addItem(1309, 1);
					c.chosenClass = true;
				}
				c.getPA().closeAllWindows();
				c.dialogueAction = -1;
			break;


			//1st tele option
			case 9190:
			String type = c.playerMagicBook == 0 ? "modern" : "ancient";
				if (c.teleAction == 1) {
					//rock crabs
					c.getPA().spellTeleport(2676, 3715, 0);
				} else if (c.teleAction == 2) {
					//barrows
					c.getPA().spellTeleport(3565, 3314, 0);
				} else if (c.teleAction == 3) {
					//godwars
					c.getPA().spellTeleport(2916, 3612, 0);
					c.getPA().walkableInterface(16210);
				} else if (c.teleAction == 4) {
					//varrock wildy
					c.getPA().spellTeleport(2539, 4716, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3046,9779,0);
				} else if (c.teleAction == 9) {
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, type);	
				}
				
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2845, 4832, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2786, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2398, 4841, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 21) {
					c.getDH().sendDialogues(22, 2253);
				} else if (c.dialogueAction == 36) {
					c.getPA().buyPrayer(1000, 25);
				} else if (c.dialogueAction == 37) {
					c.getPA().buyPrayer(100000, 300);
				} else if (c.dialogueAction == 39) {
					c.getShops().buySet("iron");
				} else if (c.dialogueAction == 40) {
					c.getShops().buySet("white");
				} else if (c.dialogueAction == 41) {
					c.getShops().buySet("void melee");
				} else if (c.dialogueAction == 42) {
					c.getShops().buySet("torag");
				} else if (c.dialogueAction == 43) {
					c.getShops().buySet("bandos");
				} else if (c.dialogueAction == 46) {
					c.getShops().buySet("leather");
				} else if (c.dialogueAction == 47) {
					c.getShops().buySet("void range");
				} else if (c.dialogueAction == 48) {
					c.getShops().buySet("zamorak dhide");
				} else if (c.dialogueAction == 49) {
					c.getShops().buySet("armadyl");
				} else if (c.dialogueAction == 50) {
					c.getShops().buySet("lvl 2 arrows");
				} else if (c.dialogueAction == 52) {
					c.getShops().buySet("blue robe");
				} else if (c.dialogueAction == 53) {
					c.getShops().buySet("white mystic");
				} else if (c.dialogueAction == 54) {
					c.getShops().buySet("skeletal");
				} else if (c.dialogueAction == 55) {
					c.getShops().buySet("3rd age mage");
				} else if (c.dialogueAction == 56) {
					
				} else if (c.dialogueAction == 58) {
					c.getShops().buySet("lvl 1 food");
				} else if (c.dialogueAction == 69) {
					c.playerBet = 1;
					if (c.fight == 1)
						c.getPA().brawl(c.fightOne[0], c.fightOne[1], true);
					else if (c.fight == 2)
						c.getPA().brawl(c.fightTwo[0], c.fightTwo[1], true);
				} else if (c.dialogueAction == 44) {
					c.getPA().killsHSN();
				}
				break;
				//mining - 3046,9779,0
			//smithing - 3079,9502,0

			//2nd tele option
			case 9191:
				if (c.teleAction == 1) {
					//tav dungeon
					c.getPA().spellTeleport(2884, 9798, 0);
				} else if (c.teleAction == 2) {
					//pest control
					c.getPA().spellTeleport(2662, 2650, 0);
				} else if (c.teleAction == 3) {
					//kbd
					c.getPA().spellTeleport(3007, 3849, 0);
				} else if (c.teleAction == 4) {
					//graveyard
					c.getPA().spellTeleport(2978, 3616, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(3079,9502,0);
				} else if (c.teleAction == 9) {
					c.getPA().spellTeleport(3223,3218,0); //Lumbridge
				}
				
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2796, 4818, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2527, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2464, 4834, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 21) {
					c.getDH().sendDialogues(24, 2253);
				} else if (c.dialogueAction == 36) {
					c.getPA().buyPrayer(10000, 50);
				} else if (c.dialogueAction == 37) {
					c.getPA().buyPrayer(250000, 500);
				} else if (c.dialogueAction == 39) {
					c.getShops().buySet("steel");
				} else if (c.dialogueAction == 40) {
					c.getShops().buySet("adamant");
				} else if (c.dialogueAction == 41) {
					c.getShops().buySet("penance");
				} else if (c.dialogueAction == 42) {
					c.getShops().buySet("dharok");
				} else if (c.dialogueAction == 43) {
					c.getShops().buySet("gilded");
				} else if (c.dialogueAction == 46) {
					c.getShops().buySet("snakeskin");
				} else if (c.dialogueAction == 47) {
					c.getShops().buySet("blue dhide");
				} else if (c.dialogueAction == 48) {
					c.getShops().buySet("guthix dhide");
				} else if (c.dialogueAction == 49) {
					c.getShops().buySet("morrigan");
				} else if (c.dialogueAction == 50) {
					c.getShops().buySet("bow");
				} else if (c.dialogueAction == 52) {
					c.getShops().buySet("staffs");
				} else if (c.dialogueAction == 53) {
					c.getShops().buySet("splitbark");
				} else if (c.dialogueAction == 54) {
					c.getShops().buySet("infinity");
				} else if (c.dialogueAction == 55) {
					c.getShops().buySet("lvl 1 runes");
				} else if (c.dialogueAction == 56) {
					
				} else if (c.dialogueAction == 58) {
					c.getShops().buySet("lvl 2 food");
				} else if (c.dialogueAction == 69) {
					c.playerBet = 5;
					if (c.fight == 1)
						c.getPA().brawl(c.fightOne[0], c.fightOne[1], true);
					else if (c.fight == 2)
						c.getPA().brawl(c.fightTwo[0], c.fightTwo[1], true);
				} else if (c.dialogueAction == 44) {
					c.getPA().deathsHSN();
				}
				break;
			//3rd tele option	

			case 9192:
				if (c.teleAction == 1) {
					//slayer tower
					c.getPA().spellTeleport(3428, 3537, 0);
				} else if (c.teleAction == 2) {
					//tzhaar
					c.getPA().spellTeleport(2438, 5168, 0);
					c.sendMessage("To fight Jad, enter the cave.");
				} else if (c.teleAction == 3) {
					//dag kings
					c.getPA().spellTeleport(1910, 4367, 0);
					c.sendMessage("Climb down the ladder to get into the lair.");
				} else if (c.teleAction == 4) {
					//44 portals
					c.getPA().spellTeleport(2975, 3873, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2813,3436,0);
				} else if (c.teleAction == 9) {
					c.getPA().spellTeleport(3214,3424,0); //Varrock
				}
				
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2713, 4836, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					c.getPA().spellTeleport(2162, 4833, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					c.getPA().spellTeleport(2207, 4836, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 21) {
					c.getDH().sendDialogues(25, 2253);
				} else if (c.dialogueAction == 36) {
					c.getPA().buyPrayer(25000, 100);
				} else if (c.dialogueAction == 37) {
					c.getPA().buyPrayer(500000, 800);
				} else if (c.dialogueAction == 39) {
					c.getShops().buySet("black");
				} else if (c.dialogueAction == 40) {
					c.getShops().buySet("rune");
				} else if (c.dialogueAction == 41) {
					c.getShops().buySet("rogue");
				} else if (c.dialogueAction == 42) {
					c.getShops().buySet("guthan");
				} else if (c.dialogueAction == 43) {
					c.getShops().buySet("statius");
				} else if (c.dialogueAction == 46) {
					c.getShops().buySet("green dhide");
				} else if (c.dialogueAction == 47) {
					c.getShops().buySet("red dhide");
				} else if (c.dialogueAction == 48) {
					c.getShops().buySet("saradomin dhide");
				} else if (c.dialogueAction == 49) {
					c.getShops().buySet("3rd age range");
				} else if (c.dialogueAction == 50) {
					
				} else if (c.dialogueAction == 52) {
					c.getShops().buySet("black mystic");
				} else if (c.dialogueAction == 53) {
					c.getShops().buySet("enchanted");
				} else if (c.dialogueAction == 54) {
					c.getShops().buySet("ahrims");
				} else if (c.dialogueAction == 55) {
					c.getShops().buySet("lvl 2 runes");
				} else if (c.dialogueAction == 56) {
					
				} else if (c.dialogueAction == 58) {
					
				} else if (c.dialogueAction == 69) {
					c.playerBet = 10;
					if (c.fight == 1)
						c.getPA().brawl(c.fightOne[0], c.fightOne[1], true);
					else if (c.fight == 2)
						c.getPA().brawl(c.fightTwo[0], c.fightTwo[1], true);
				} else if (c.dialogueAction == 44) {
					c.getPA().killsHSP();
				}
				break;
			//4th tele option
			case 9193:
				if (c.teleAction == 1) {
					//brimhaven dungeon
					c.getPA().spellTeleport(2710, 9466, 0);
				} else if (c.teleAction == 2) {
					//duel arena
					c.getPA().spellTeleport(3366, 3266, 0);
				} else if (c.teleAction == 3) {
					//chaos elemental
					c.getPA().spellTeleport(3295, 3921, 0);
				} else if (c.teleAction == 4) {
					//gdz
					c.getPA().spellTeleport(3288, 3886, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2724,3484,0);
					c.sendMessage("For magic logs, try north of the duel arena.");
				} else if (c.teleAction == 9) {
					c.getPA().spellTeleport(2964,3378,0); //Falador
				}
				
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2660, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 11) {
					//c.getPA().spellTeleport(2527, 4833, 0); astrals here
					c.getRunecrafting().craftRunes(2489);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 12) {
					//c.getPA().spellTeleport(2464, 4834, 0); bloods here
					c.getRunecrafting().craftRunes(2489);
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 21) {
					c.getDH().sendDialogues(26, 2253);
				} else if (c.dialogueAction == 36) {
					c.getPA().buyPrayer(50000, 175);
				} else if (c.dialogueAction == 37) {
					c.getPA().buyPrayer(1000000, 1500);
				} else if (c.dialogueAction == 39) {
					c.getShops().buySet("mithril");
				} else if (c.dialogueAction == 40) {
					c.getShops().buySet("rockshell");
				} else if (c.dialogueAction == 41) {
					c.getShops().buySet("dragon");
				} else if (c.dialogueAction == 42) {
					c.getShops().buySet("verac");
				} else if (c.dialogueAction == 43) {
					c.getShops().buySet("vesta");
				} else if (c.dialogueAction == 46) {
					c.getShops().buySet("spined");
				} else if (c.dialogueAction == 47) {
					c.getShops().buySet("black dhide");
				} else if (c.dialogueAction == 48) {
					c.getShops().buySet("karils");
				} else if (c.dialogueAction == 49) {
					c.getShops().buySet("lvl 1 arrows");
				} else if (c.dialogueAction == 50) {
					c.getPA().closeAllWindows();
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 52) {
					c.getShops().buySet("blue mystic");
				} else if (c.dialogueAction == 53) {
					c.getShops().buySet("void mage");
				} else if (c.dialogueAction == 54) {
					c.getShops().buySet("zuriel");
				} else if (c.dialogueAction == 55) {
					
				} else if (c.dialogueAction == 56) {
					
				} else if (c.dialogueAction == 58) {
					
				} else if (c.dialogueAction == 69) {
					c.playerBet = 25;
					if (c.fight == 1)
						c.getPA().brawl(c.fightOne[0], c.fightOne[1], true);
					else if (c.fight == 2)
						c.getPA().brawl(c.fightTwo[0], c.fightTwo[1], true);
				} else if (c.dialogueAction == 44) {
					c.getPA().deathsHSP();
				}
				break;
			//5th tele option
			case 9194:
				if (c.teleAction == 1) {
					//island
					c.getPA().spellTeleport(2895, 2727, 0);
				} else if (c.teleAction == 2) {
					//last minigame spot
					c.sendMessage("Suggest something for this spot on the forums!");
					c.getPA().closeAllWindows();
				} else if (c.teleAction == 3) {
					//last monster spot
					c.sendMessage("Suggest something for this spot on the forums!");
					c.getPA().closeAllWindows();
				} else if (c.teleAction == 4) {
					//ardy lever
					c.getPA().spellTeleport(3093, 3503, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2812,3463,0);
				} else if (c.teleAction == 9) {
					c.getPA().spellTeleport(3093,3244,0); //Draynor
				}
				if (c.dialogueAction == 10 || c.dialogueAction == 11) {
					c.dialogueId++;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.dialogueAction == 12) {
					c.dialogueId = 17;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.dialogueAction == 21) {
					c.getDH().sendDialogues(27, 2253);
				} else if (c.dialogueAction == 36) {
					c.getDH().sendDialogues(37, -1);
				} else if (c.dialogueAction == 37) {
					c.getPA().closeAllWindows();
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 39) {
					c.getDH().sendDialogues(40, -1);
				} else if (c.dialogueAction == 40) {
					c.getDH().sendDialogues(41, -1);
				} else if (c.dialogueAction == 41) {
					c.getDH().sendDialogues(42, -1);
				} else if (c.dialogueAction == 42) {
					c.getDH().sendDialogues(43, -1);
				} else if (c.dialogueAction == 43) {
					c.getShops().buySet("3rd age melee");
				} else if (c.dialogueAction == 46) {
					c.getDH().sendDialogues(47, -1);
				} else if (c.dialogueAction == 47) {
					c.getDH().sendDialogues(48, -1);
				} else if (c.dialogueAction == 48) {
					c.getDH().sendDialogues(49, -1);
				} else if (c.dialogueAction == 49) {
					c.getDH().sendDialogues(50, -1);
				} else if (c.dialogueAction == 50) {
					c.getDH().sendDialogues(46, -1);
				} else if (c.dialogueAction == 52) {
					c.getDH().sendDialogues(53, -1);
				} else if (c.dialogueAction == 53) {
					c.getDH().sendDialogues(54, -1);
				} else if (c.dialogueAction == 54) {
					c.getDH().sendDialogues(55, -1);
				} else if (c.dialogueAction == 55) {
					c.getDH().sendDialogues(56, -1);
				} else if (c.dialogueAction == 56) {
					c.getDH().sendDialogues(52, -1);
				} else if (c.dialogueAction == 58) {
					c.getPA().closeAllWindows();
					c.dialogueAction = -1;
				} else if (c.dialogueAction == 69) {
					c.playerBet = 50;
					if (c.fight == 1)
						c.getPA().brawl(c.fightOne[0], c.fightOne[1], true);
					else if (c.fight == 2)
						c.getPA().brawl(c.fightTwo[0], c.fightTwo[1], true);
				} else if (c.dialogueAction == 44) {
					
				}
				break;
			
			case 71074:
				if (c.clanId >= 0) {
					if (Server.clanChat.clans[c.clanId].owner.equalsIgnoreCase(c.playerName)) {
						Server.clanChat.sendLootShareMessage(c.clanId, "Lootshare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "on" : "off") + " by the clan leader.");
						Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
					} else
						c.sendMessage("Only the owner of the clan has the power to do that.");
				}	
			break;
			case 34185: case 34184: case 34183: case 34182: case 34189: case 34188: case 34187: case 34186: case 34193: case 34192: case 34191: case 34190:
				if (c.craftingLeather)
					c.getCrafting().handleCraftingClick(actionButtonId);
				if (c.getFletching().fletching)
					c.getFletching().handleFletchingClick(actionButtonId);
			break;
			
			case 15147:
				if (c.smeltInterface) {
					c.smeltType = 2349;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 15151:
				if (c.smeltInterface) {
					c.smeltType = 2351;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			
			case 15159:
				if (c.smeltInterface) {
					c.smeltType = 2353;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			
			case 29017:
				if (c.smeltInterface) {
					c.smeltType = 2359;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 29022:
				if (c.smeltInterface) {
					c.smeltType = 2361;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			
			case 29026:
				if (c.smeltInterface) {
					c.smeltType = 2363;
					c.smeltAmount = 1;
					c.getSmithing().startSmelting(c.smeltType);
				}
			break;
			case 58253:
			//c.getPA().showInterface(15106);
			c.getItems().writeBonus();
			break;
			
			case 59004:
			c.getPA().removeAllWindows();
			break;
			
			case 70212:
				if (c.clanId > -1)
					Server.clanChat.leaveClan(c.playerId, c.clanId);
				else
					c.sendMessage("You are not in a clan.");
			break;
			case 62137:
				if (c.clanId >= 0) {
					c.sendMessage("You are already in a clan.");
					break;
				}
				if (c.getOutStream() != null) {
					c.getOutStream().createFrame(187);
					c.flushOutStream();
				}	
			break;
			
			case 9178:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(3428, 3538, 0, "modern");
				if (c.dialogueAction == 3)		
					c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(3565, 3314, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 4, "modern");
					c.killCount = 0;
				}
					
			break;
			
			case 9179:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2884, 3395, 0, "modern");
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3243, 3513, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2444, 5170, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 12, "modern");
					c.killCount = 0;
				}	
			break;
			
			case 9180:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.KARAMJA_X, Config.KARAMJA_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2471,10137, 0, "modern");	
				if (c.dialogueAction == 3)
					c.getPA().startTeleport(3363, 3676, 0, "modern");
				if (c.dialogueAction == 4)
					c.getPA().startTeleport(2659, 2676, 0, "modern");
				if (c.dialogueAction == 20) {
					c.getPA().startTeleport(2897, 3618, 8, "modern");
					c.killCount = 0;
				}
			break;
			
			case 9181:
				if (c.usingGlory)
					c.getPA().startTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y, 0, "modern");
				if (c.dialogueAction == 2)
					c.getPA().startTeleport(2669,3714, 0, "modern");
				if (c.dialogueAction == 3)	
					c.getPA().startTeleport(2540, 4716, 0, "modern");
				if (c.dialogueAction == 4) {
					c.getPA().startTeleport(3366, 3266, 0, "modern");
					c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
				}
				if (c.dialogueAction == 20) {
					//c.getPA().startTeleport(3366, 3266, 0, "modern");
					//c.killCount = 0;
					c.sendMessage("This will be added shortly");
				}
			break;
			
			case 1093:
			case 1094:
			case 1097:
				if (c.autocastId > 0) {
					c.getPA().resetAutocast();
				} else {
					if (c.playerMagicBook == 1) {
						if (c.playerEquipment[c.playerWeapon] == 4675)
							c.setSidebarInterface(0, 1689);
						else
							c.sendMessage("You can't autocast ancients without an ancient staff.");
					} else if (c.playerMagicBook == 0) {
						if (c.playerEquipment[c.playerWeapon] == 4170) {
							c.setSidebarInterface(0, 12050);
						} else {
							c.setSidebarInterface(0, 1829);
						}	
					}
						
				}		
			break;
			
			case 9157://barrows tele to tunnels
				if(c.dialogueAction == 1) {
					int r = 4;
					//int r = Misc.random(3);
					switch(r) {
						case 0:
							c.getPA().movePlayer(3534, 9677, 0);
							break;
						
						case 1:
							c.getPA().movePlayer(3534, 9712, 0);
							break;
						
						case 2:
							c.getPA().movePlayer(3568, 9712, 0);
							break;
						
						case 3:
							c.getPA().movePlayer(3568, 9677, 0);
							break;
						case 4:
							c.getPA().movePlayer(3551, 9694, 0);
							break;
					}
				} else if (c.dialogueAction == 2) {
					c.getPA().movePlayer(2507, 4717, 0);		
				} else if (c.dialogueAction == 5) {
					c.getSlayer().giveTask();
				} else if (c.dialogueAction == 6) {
					c.getSlayer().giveTask2();
				} else if (c.dialogueAction == 7) {
					c.getPA().startTeleport(3088,3933,0,"modern");
					c.sendMessage("NOTE: You are now in the wilderness...");
				} else if (c.dialogueAction == 8) {
					c.getPA().resetBarrows();
					c.sendMessage("Your barrows have been reset.");
				} else if (c.dialogueAction == 29) {
					c.getPA().duelNPC();
					c.dialogueAction = 0;
					c.getPA().removeAllWindows();
				} else if (c.dialogueAction == 34) {
					c.getDH().sendDialogues(35, 358);
				} else if (c.dialogueAction == 60) {
					c.getShops().buySet("pots");
				} else if (c.dialogueAction == 62) {
					c.getPA().spellTeleport(2852, 2954, 0);
					c.sendMessage("Talk to bob when you want to take on a job.");
				} else if (c.dialogueAction == 64) {
					c.getPA().spellTeleport(3212, 3438, 0);
					Server.npcHandler.spawnNpc(c, c.playerJob, 3210, 3423, 0, 1, 255, 80, 800, 800, true, true);
				} else if (c.dialogueAction == 66) {
					NPCRanks.getRandomOpponents(c);
					c.getDH().sendDialogues(68, -1);
				} else if (c.dialogueAction == 68) {
					c.fight = 1;
					c.getDH().sendDialogues(70, 518);
				} else if (c.dialogueAction == 71) {
					c.chosenToWin = c.fightOne[0];
					c.getDH().sendDialogues(73, 518);
				} else if (c.dialogueAction == 72) {
					c.chosenToWin = c.fightTwo[0];
					c.getDH().sendDialogues(73, 518);
				} else if (c.dialogueAction == 77) {
					c.getPA().spellTeleport(2373, 4700, 0);
					c.getPA().createDungeonOne();
				} else if (c.dialogueAction == 78) {
					c.getPA().createDungeonTwo();
					c.getPA().movePlayer(2420, 4691, 0);
				} else if (c.dialogueAction == 80) {
					c.getShops().openShop(5);
				} else if (c.dialogueAction == 82) {
					c.getPA().movePlayer(3299, 3279, 0);
				} else if (c.dialogueAction == 84) {
					c.getPA().sellOres();
				} else {
					c.dialogueAction = 0;
					c.getPA().removeAllWindows();
				}
				break;
			
			case 9158:
				if (c.dialogueAction == 8) {
					c.getPA().fixAllBarrows();
				} else if (c.dialogueAction == 29) {
					c.getDH().sendDialogues(30, 1597);
				} else if (c.dialogueAction == 66) {
					c.getDH().sendDialogues(67, 518);
				} else if (c.dialogueAction == 68) {
					c.fight = 2;
					c.getDH().sendDialogues(70, 518);
				} else if (c.dialogueAction == 71) {
					c.chosenToWin = c.fightOne[1];
					c.getDH().sendDialogues(73, 518);
				} else if (c.dialogueAction == 72) {
					c.chosenToWin = c.fightTwo[1];
					c.getDH().sendDialogues(73, 518);
				} else if (c.dialogueAction == 78) {
					c.getPA().movePlayer(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
				} else if (c.dialogueAction == 80) {
					c.getPA().sellLogs();
				} else if (c.dialogueAction == 82) {
					c.getShops().openShop(6);
				} else {
					c.dialogueAction = 0;
					c.getPA().removeAllWindows();
				}
				break;
			
			/**Specials**/
			case 29188:
			c.specBarId = 7636; // the special attack text - sendframe126(S P E C I A L  A T T A C K, c.specBarId);
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29163:
			c.specBarId = 7611;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 33033:
			c.specBarId = 8505;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29038:
			c.specBarId = 7486;
			if (c.playerEquipment[c.playerWeapon] != 4153) {
				c.usingSpecial = !c.usingSpecial;
				c.getItems().updateSpecialBar();
			} else {
				c.getCombat().handleGmaulPlayer();
				c.getItems().updateSpecialBar();
			}
			break;
			
			case 29063:
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]) + (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
			break;
			
			case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29138:
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			/**Dueling**/			
			case 26065: // no forfeit
			case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;
			
			case 26066: // no movement
			case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;
			
			case 26069: // no range
			case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;
			
			case 26070: // no melee
			case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;				
			
			case 26071: // no mage
			case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;
				
			case 26072: // no drinks
			case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;
			
			case 26073: // no food
			case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;
			
			case 26074: // no prayer
			case 26047:	
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			break;
			
			case 26076: // obsticals
			case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;
			
			case 2158: // fun weapons
			case 2157:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(9);
			break;
			
			case 30136: // sp attack
			case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;	

			case 53245: //no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectRule(11);
			break;
			
			case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectRule(12);
			break;
			
			case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectRule(13);
			break;
			
			case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectRule(14);
			break;
			
			case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectRule(15);
			break;
			
			case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectRule(16);
			break;
			
			case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectRule(17);
			break;
			
			case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectRule(18);
			break;
			
			case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectRule(19);
			break;
			
			case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectRule(20);
			break;
			
			case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectRule(21);
			break;
			
			
			case 26018:	
			Client o = (Client) PlayerHandler.players[c.duelingWith];
			if(o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}
			
			if(c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if(c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if(o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			
			if(c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;
			
			case 25120:
			if(c.duelStatus == 5) {
				break;
			}
			Client o1 = (Client) PlayerHandler.players[c.duelingWith];
			if(o1 == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}

			c.duelStatus = 4;
			if(o1.duelStatus == 4 && c.duelStatus == 4) {				
				c.getTradeAndDuel().startDuel();
				o1.getTradeAndDuel().startDuel();
				o1.duelCount = 4;
				c.duelCount = 4;
				c.duelDelay = System.currentTimeMillis();
				o1.duelDelay = System.currentTimeMillis();
			} else {
				c.getPA().sendFrame126("Waiting for other player...", 6571);
				o1.getPA().sendFrame126("Other player has accepted", 6571);
			}
			break;
	
			
			case 4169: // god spell charge
			c.usingMagic = true;
			if(!c.getCombat().checkMagicReqs(48)) {
				break;
			}
				
			if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
				c.sendMessage("You still feel the charge in your body!");
				break;
			}
			c.godSpellDelay	= System.currentTimeMillis();
			c.sendMessage("You feel charged with a magical power!");
			c.gfx100(c.MAGIC_SPELLS[48][3]);
			c.startAnimation(c.MAGIC_SPELLS[48][2]);
			c.usingMagic = false;
	        break;
			
			
			case 28164: // item kept on death 
			break;
			
			
			case 152:
			c.isRunning2 = !c.isRunning2;
			int frame = c.isRunning2 == true ? 1 : 0;
			c.getPA().sendFrame36(173,frame);
			break;
			
			case 9154:
			c.logout();
			break;
			
			case 21010:
			c.takeAsNote = true;
			break;

			case 21011:
			c.takeAsNote = false;
			break;
			
			
			//home teleports
			case 4171:
			case 50056:
			c.getPA().spellTeleport(Config.RESPAWN_X, Config.RESPAWN_Y, 0);
			break;
			
			case 50235:
			case 4140:
			
			break;
			
			case 4143:
			case 50245:
			
			break;
			
			case 50253:
			case 4146:
			
			break;
			

			case 51005:
			case 4150:
			
			break;			
			
			case 51013:
			case 6004:
			
			break; 
			
			
			case 51023:
			case 6005:
			//c.getDH().sendOption5("Option 16", "Option 2", "Option 3", "Option 4", "Option 5");
			//c.teleAction = 6;
			break; 
			
			
			case 51031:
			case 29031:
			//c.getDH().sendOption5("Option 17", "Option 2", "Option 3", "Option 4", "Option 5");
			//c.teleAction = 7;
			break; 		

			case 72038:
			case 51039:
			//c.getDH().sendOption5("Option 18", "Option 2", "Option 3", "Option 4", "Option 5");
			//c.teleAction = 8;
			break;
			
	                 
			case 9125: //Accurate
			case 6221: // range accurate
			case 22228: //punch (unarmed)
			case 48010: //flick (whip)
			case 21200: //spike (pickaxe)
			case 1080: //bash (staff)
			case 6168: //chop (axe)
			case 6236: //accurate (long bow)
			case 17102: //accurate (darts)
			case 8234: //stab (dagger)
			c.fightMode = 0;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9126: //Defensive
			case 48008: //deflect (whip)
			case 22229: //block (unarmed)
			case 21201: //block (pickaxe)
			case 1078: //focus - block (staff)
			case 6169: //block (axe)
			case 33019: //fend (hally)
			case 18078: //block (spear)
			case 8235: //block (dagger)
			c.fightMode = 1;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9127: // Controlled
			case 48009: //lash (whip)
			case 33018: //jab (hally)
			case 6234: //longrange (long bow)
			case 6219: //longrange
			case 18077: //lunge (spear)
			case 18080: //swipe (spear)
			case 18079: //pound (spear)
			case 17100: //longrange (darts)
			c.fightMode = 3;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9128: //Aggressive
			case 6220: // range rapid
			case 22230: //kick (unarmed)
			case 21203: //impale (pickaxe)
			case 21202: //smash (pickaxe)
			case 1079: //pound (staff)
			case 6171: //hack (axe)
			case 6170: //smash (axe)
			case 33020: //swipe (hally)
			case 6235: //rapid (long bow)
			case 17101: //repid (darts)
			case 8237: //lunge (dagger)
			case 8236: //slash (dagger)
			c.fightMode = 2;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;	
			
			
			/**Prayers**/
			case 21233: // thick skin
			c.getCombat().activatePrayer(0);
			break;	
			case 21234: // burst of str
			c.getCombat().activatePrayer(1);
			break;	
			case 21235: // charity of thought
			c.getCombat().activatePrayer(2);
			break;	
			case 70080: // range
			c.getCombat().activatePrayer(3);
			break;
			case 70082: // mage
			c.getCombat().activatePrayer(4);
			break;
			case 21236: // rockskin
			c.getCombat().activatePrayer(5);
			break;
			case 21237: // super human
			c.getCombat().activatePrayer(6);
			break;
			case 21238:	// improved reflexes
			c.getCombat().activatePrayer(7);
			break;
			case 21239: //hawk eye
			c.getCombat().activatePrayer(8);
			break;
			case 21240:
			c.getCombat().activatePrayer(9);
			break;
			case 21241: // protect Item
			c.getCombat().activatePrayer(10);
			break;			
			case 70084: // 26 range
			c.getCombat().activatePrayer(11);
			break;
			case 70086: // 27 mage
			c.getCombat().activatePrayer(12);
			break;	
			case 21242: // steel skin
			c.getCombat().activatePrayer(13);
			break;
			case 21243: // ultimate str
			c.getCombat().activatePrayer(14);
			break;
			case 21244: // incredible reflex
			c.getCombat().activatePrayer(15);
			break;	
			case 21245: // protect from magic
			c.getCombat().activatePrayer(16);
			break;					
			case 21246: // protect from range
			c.getCombat().activatePrayer(17);
			break;
			case 21247: // protect from melee
			c.getCombat().activatePrayer(18);
			break;
			case 70088: // 44 range
			c.getCombat().activatePrayer(19);
			break;	
			case 70090: // 45 mystic
			c.getCombat().activatePrayer(20);
			break;				
			case 2171: // retrui
			c.getCombat().activatePrayer(21);
			break;					
			case 2172: // redem
			c.getCombat().activatePrayer(22);
			break;					
			case 2173: // smite
			c.getCombat().activatePrayer(23);
			break;
			case 70092: // chiv
			c.getCombat().activatePrayer(24);
			break;
			case 70094: // turmoil
			c.getCombat().activatePrayer(25);
			c.playerLevel[5] = 95;
			c.gfx100(1224);
			c.startAnimation(3926);
			break;
			
			case 13092:
			Client ot = (Client) PlayerHandler.players[c.tradeWith];
			if(ot == null) {
				c.getTradeAndDuel().declineTrade();
				c.sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.getPA().sendFrame126("Waiting for other player...", 3431);
			ot.getPA().sendFrame126("Other player has accepted", 3431);	
			c.goodTrade= true;
			ot.goodTrade= true;
			
			for (GameItem item : c.getTradeAndDuel().offeredItems) {
				if (item.id > 0) {
					if(ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems.size()) {					
						c.sendMessage(ot.playerName +" only has "+ot.getItems().freeSlots()+" free slots, please remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						ot.sendMessage(c.playerName +" has to remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items or you could offer them "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
						c.goodTrade= false;
						ot.goodTrade= false;
						c.getPA().sendFrame126("Not enough inventory space...", 3431);
						ot.getPA().sendFrame126("Not enough inventory space...", 3431);
							break;
					} else {
						c.getPA().sendFrame126("Waiting for other player...", 3431);				
						ot.getPA().sendFrame126("Other player has accepted", 3431);
						c.goodTrade= true;
						ot.goodTrade= true;
						}
					}	
				}	
				if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
					c.tradeConfirmed = true;
					if(ot.tradeConfirmed) {
						c.getTradeAndDuel().confirmScreen();
						ot.getTradeAndDuel().confirmScreen();
						break;
					}
							  
				}

		
			break;
					
			case 13218:
			c.tradeAccepted = true;
			Client ot1 = (Client) PlayerHandler.players[c.tradeWith];
				if (ot1 == null) {
					c.getTradeAndDuel().declineTrade();
					c.sendMessage("Trade declined as the other player has disconnected.");
					break;
				}
				
				if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
					c.tradeConfirmed2 = true;
					if(ot1.tradeConfirmed2) {	
						c.acceptedTrade = true;
						ot1.acceptedTrade = true;
						c.getTradeAndDuel().giveItems();
						ot1.getTradeAndDuel().giveItems();
						break;
					}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
				}
				
			break;		
			/* Rules Interface Buttons */
			case 125011: //Click agree
				if(!c.ruleAgreeButton) {
					c.ruleAgreeButton = true;
					c.getPA().sendFrame36(701, 1);
				} else {
					c.ruleAgreeButton = false;
					c.getPA().sendFrame36(701, 0);
				}
				break;
			case 125003://Accept
				if(c.ruleAgreeButton) {
					c.getPA().showInterface(3559);
					c.newPlayer = false;
				} else if(!c.ruleAgreeButton) {
					c.sendMessage("You need to click on you agree before you can continue on.");
				}
				break;
			case 125006://Decline
				c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
				break;
			/* End Rules Interface Buttons */
			/* Player Options */
			case 74176:
				if(!c.mouseButton) {
					c.mouseButton = true;
					c.getPA().sendFrame36(500, 1);
					c.getPA().sendFrame36(170,1);
				} else if(c.mouseButton) {
					c.mouseButton = false;
					c.getPA().sendFrame36(500, 0);
					c.getPA().sendFrame36(170,0);					
				}
				break;
			case 74184:
				if(!c.splitChat) {
					c.splitChat = true;
					c.getPA().sendFrame36(502, 1);
					c.getPA().sendFrame36(287, 1);
				} else {
					c.splitChat = false;
					c.getPA().sendFrame36(502, 0);
					c.getPA().sendFrame36(287, 0);
				}
				break;
			case 74180:
				if(!c.chatEffects) {
					c.chatEffects = true;
					c.getPA().sendFrame36(501, 1);
					c.getPA().sendFrame36(171, 0);
				} else {
					c.chatEffects = false;
					c.getPA().sendFrame36(501, 0);
					c.getPA().sendFrame36(171, 1);
				}
				break;
			case 74188:
				if(!c.acceptAid) {
					c.acceptAid = true;
					c.getPA().sendFrame36(503, 1);
					c.getPA().sendFrame36(427, 1);
				} else {
					c.acceptAid = false;
					c.getPA().sendFrame36(503, 0);
					c.getPA().sendFrame36(427, 0);
				}
				break;
			case 74192:
				if(!c.isRunning2) {
					c.isRunning2 = true;
					c.getPA().sendFrame36(504, 1);
					c.getPA().sendFrame36(173, 1);
				} else {
					c.isRunning2 = false;
					c.getPA().sendFrame36(504, 0);
					c.getPA().sendFrame36(173, 0);
				}
				break;
			case 74201://brightness1
				c.getPA().sendFrame36(505, 1);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166, 1);
				break;
			case 74203://brightness2
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 1);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,2);
				break;

			case 74204://brightness3
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 1);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,3);
				break;

			case 74205://brightness4
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 1);
				c.getPA().sendFrame36(166,4);
				break;
			case 74206://area1
				c.getPA().sendFrame36(509, 1);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74207://area2
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 1);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74208://area3
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 1);
				c.getPA().sendFrame36(512, 0);
				break;
			case 74209://area4
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 1);
				break;
			case 168:
                c.startAnimation(855);
            break;
            case 169:
                c.startAnimation(856);
            break;
            case 162:
                c.startAnimation(857);
            break;
            case 164:
                c.startAnimation(858);
            break;
            case 165:
                c.startAnimation(859);
            break;
            case 161:
                c.startAnimation(860);
            break;
            case 170:
                c.startAnimation(861);
            break;
            case 171:
                c.startAnimation(862);
            break;
            case 163:
                c.startAnimation(863);
            break;
            case 167:
                c.startAnimation(864);
            break;
            case 172:
                c.startAnimation(865);
            break;
            case 166:
                c.startAnimation(866);
            break;
            case 52050:
                c.startAnimation(2105);
            break;
            case 52051:
                c.startAnimation(2106);
            break;
            case 52052:
                c.startAnimation(2107);
            break;
            case 52053:
                c.startAnimation(2108);
            break;
            case 52054:
                c.startAnimation(2109);
            break;
            case 52055:
                c.startAnimation(2110);
            break;
            case 52056:
                c.startAnimation(2111);
            break;
            case 52057:
                c.startAnimation(2112);
            break;
            case 52058:
                c.startAnimation(2113);
            break;
            case 43092:
                c.startAnimation(0x558);
            break;
            case 2155:
                c.startAnimation(0x46B);
            break;
            case 25103:
                c.startAnimation(0x46A);
            break;
            case 25106:
                c.startAnimation(0x469);
            break;
            case 2154:
                c.startAnimation(0x468);
            break;
            case 52071:
                c.startAnimation(0x84F);
            break;
            case 52072:
                c.startAnimation(0x850);
            break;
            case 59062:
                c.startAnimation(2836);
            break;
            case 72032:
                c.startAnimation(3544);
            break;
            case 72033:
                c.startAnimation(3543);
            break;
            case 72254:
                c.startAnimation(3866);
            break;
			/* END OF EMOTES */
			case 28166:


				//qc, evo
case 33206:
c.forcedText = "[Quick Chat] My Attack level is " + c.playerLevel[0] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33209:
c.forcedText = "[Quick Chat] My Strength level is " + c.playerLevel[2] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33212:
c.forcedText = "[Quick Chat] My Defence level is " + c.playerLevel[1] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33215:
c.forcedText = "[Quick Chat] My Range level is " + c.playerLevel[4] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33218:
c.forcedText = "[Quick Chat] My Prayer level is " + c.playerLevel[5] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33221:
c.forcedText = "[Quick Chat] My Magic level is " + c.playerLevel[6] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33224:
c.forcedText = "[Quick Chat] My Runecrafting level is " + c.playerLevel[20] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33207:
c.forcedText = "[Quick Chat] My Hitpoints level is " + c.playerLevel[3] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33210:
c.forcedText = "[Quick Chat] My Agility level is " + c.playerLevel[16] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33213:
c.forcedText = "[Quick Chat] My Herblore level is " + c.playerLevel[15] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33216:
c.forcedText = "[Quick Chat] My Thieving level is " + c.playerLevel[17] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33219:
c.forcedText = "[Quick Chat] My Crafting level is " + c.playerLevel[12] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33222:
c.forcedText = "[Quick Chat] My Fletching level is " + c.playerLevel[9] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33208:
c.forcedText = "[Quick Chat] My Mining level is " + c.playerLevel[14] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33211:
c.forcedText = "[Quick Chat] My Smithing level is " + c.playerLevel[13] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33214:
c.forcedText = "[Quick Chat] My Fishing level is " + c.playerLevel[10] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33217:
c.forcedText = "[Quick Chat] My Cooking level is " + c.playerLevel[7] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33220:
c.forcedText = "[Quick Chat] My Firemaking level is " + c.playerLevel[11] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33223:
c.forcedText = "[Quick Chat] My Woodcutting level is " + c.playerLevel[8] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
case 33226:
c.forcedText = "[Quick Chat] My Farming level is " + c.playerLevel[19] + "";
c.forcedChatUpdateRequired = true;
c.updateRequired = true;
break;
			
			case 47130:
				c.forcedText = "[Quick Chat] My Slayer level is " + c.playerLevel[18] + ", and I must slay another " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask) + "";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			break;
			
			case 24017:
				c.getPA().resetAutocast();
				//c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
				c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
				//c.setSidebarInterface(0, 328);
				//c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : 1151);
			break;
		}
		if (c.isAutoButton(actionButtonId))
			c.assignAutocast(actionButtonId);
	}

}
