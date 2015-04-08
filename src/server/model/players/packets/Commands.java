package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.npcs.NPCHandler;
import server.model.npcs.NPCRanks;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.util.Misc;


/**
 * Commands
 **/
public class Commands implements PacketType {

	
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
	String playerCommand = c.getInStream().readString();
	if(Config.SERVER_DEBUG)
		Misc.println(c.playerName+" playerCommand: "+playerCommand);
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			}
		}

		if (playerCommand.equals("md")) {
			c.sendMessage(""+NPCHandler.manDeaths);
		}
		
		if (playerCommand.equals("gro")) {
			NPCRanks.getRandomOpponents(c);
		}
		
		

		if (playerCommand.startsWith("yell")) {
                	String rank = "";
                 	String Message = playerCommand.substring(4).toLowerCase();
                	if (c.playerRights >= 0) {
                    		rank = "[Player]["+ c.playerName +"]:";
                	} else if (c.playerRights >= 1) {
                    		rank = "@blu@[Mod]@bla@["+ c.playerName +"]:";
               		} else if (c.playerRights >= 2) {
                    		rank = "@yel@[Admin]@bla@["+ c.playerName +"]:";
                	} else if (c.playerRights >= 3) {
                    		rank = "@red@[Owner]@bla@["+ c.playerName +"] : ";
                	} else if (c.playerRights >= 4) {
                    		rank = "[Player]["+ c.playerName +"]:";
                	}        
                	for (int j = 0; j < PlayerHandler.players.length; j++) {
                		if (PlayerHandler.players[j] != null) {
                			Client c2 = (Client)PlayerHandler.players[j]; 
					c2.sendMessage(rank+Message);
		   		}
			}
            	}

		

		if(c.playerRights >= 0) {
			
			if (playerCommand.equalsIgnoreCase("players")) {
				c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+ " players online.");
			}
			if (playerCommand.equalsIgnoreCase("commands")) {
				c.sendMessage("Your current commands - ::players, ::changepassword - More coming soon.");
			}
			if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
				c.playerPass = playerCommand.substring(15);
				c.sendMessage("Your password is now: " + c.playerPass);			
			}	
			
			if (playerCommand.startsWith("setlevel") && c.playerRights >= 3) {
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.sendMessage("Take off your shit idiot..");
						return;
					}
				}
				try {
				String[] args = playerCommand.split(" ");
				int skill = Integer.parseInt(args[1]);
				int level = Integer.parseInt(args[2]);
				if (level > 99)
					level = 99;
				else if (level < 0)
					level = 1;
				c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
				c.getPA().refreshSkill(skill);
				} catch (Exception e){}
			}
			if (playerCommand.equalsIgnoreCase("master") && c.playerRights >= 3) {
				for (int i = 0; i < 21; i++) {
					c.playerXP[i] = 15000000;
					c.playerLevel[i] = 99;
					c.getPA().refreshSkill(i);
				}
			}
			if (playerCommand.equals("spec") && c.playerRights >= 3) {
				c.specAmount = 10.0;
				c.getItems().updateSpecialBar();
			}
			
			if (playerCommand.equals("empty")) {
				c.getItems().removeAllItems();
			}

			if(playerCommand.startsWith("pnpc")) {
				int npc = Integer.parseInt(playerCommand.substring(5));
				if(npc < 9999) {
					c.npcId2 = npc;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
				}
			}
			if(playerCommand.startsWith("unpc")) {
				c.isNpc = false;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}

			if (playerCommand.startsWith("object") && c.playerRights >= 3) {
				String[] args = playerCommand.split(" ");				
				c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
			}
			if (playerCommand.equals("armadyl") && c.playerRights >= 3) {
				c.getPA().movePlayer(2905, 3611, 4);			
			}
			if (playerCommand.equals("saradomin") && c.playerRights >= 3) {
				c.getPA().movePlayer(2905, 3611, 8);			
			}
			if (playerCommand.equals("bandos") && c.playerRights >= 3) {
				c.getPA().movePlayer(2905, 3611, 12);			
			}
			
			if (playerCommand.startsWith("tele") && c.playerRights >= 3) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
			}
			
					
			if(c.playerRights >= 3) {
			
			}
			if (playerCommand.startsWith("task") && c.playerRights >= 3) {
				c.taskAmount = -1;
				c.slayerTask = 0;
			}
			
			if (playerCommand.startsWith("starter") && c.playerRights >= 3) {
				c.getDH().sendDialogues(100, 945);			
			}
			if (playerCommand.equalsIgnoreCase("mypos") && c.playerRights >= 3) {
				c.sendMessage("X: "+c.absX);
				c.sendMessage("Y: "+c.absY);
				c.sendMessage("H: "+c.heightLevel);
			}

			if (playerCommand.startsWith("reloaddrops") && c.playerRights >= 3) {
				Server.npcDrops = null;
				Server.npcDrops = new server.model.npcs.NPCDrops();
				for (int j = 0; j < PlayerHandler.players.length; j++) {
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client)PlayerHandler.players[j];
						c2.sendMessage("[" + c.playerName + "] " + "NPC Drops have been reloaded.");
					}
				}

			}
			if (playerCommand.startsWith("reloadshops") && c.playerRights >= 3) {
				Server.shopHandler = new server.world.ShopHandler();
			}
			
			if (playerCommand.startsWith("fakels") && c.playerRights >= 3) {
				int item = Integer.parseInt(playerCommand.split(" ")[1]);
				Server.clanChat.handleLootShare(c, item, 1);
			}
			
			if (playerCommand.startsWith("interface") && c.playerRights >= 3) {
				String[] args = playerCommand.split(" ");
				c.getPA().showInterface(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("gfx") && c.playerRights >= 3) {
				String[] args = playerCommand.split(" ");
				c.gfx0(Integer.parseInt(args[1]));
			}
			if (playerCommand.startsWith("update") && c.playerRights >= 3) {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				PlayerHandler.updateSeconds = a;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}
			
			if (playerCommand.startsWith("item") && c.playerRights >= 3) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20000) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						} else {
							c.sendMessage("No such item.");
						}
					} else {
						c.sendMessage("Use as ::pickup 995 200");
					}
				} catch(Exception e) {
					
				}
			}
			
			if (playerCommand.equals("Vote") && c.playerRights >= 3) {
				for (int j = 0; j < PlayerHandler.players.length; j++)
					if (PlayerHandler.players[j] != null) {
						Client c2 = (Client)PlayerHandler.players[j];
						c2.getPA().sendFrame126("www.google.ca", 12000);
					}
			}


			if (playerCommand.equalsIgnoreCase("debug") && c.playerRights >= 3) {
				Server.playerExecuted = true;
			}
			
			if (playerCommand.startsWith("interface") && c.playerRights >= 3) {
				try {	
					String[] args = playerCommand.split(" ");
					int a = Integer.parseInt(args[1]);
					c.getPA().showInterface(a);
				} catch(Exception e) {
					c.sendMessage("::interface ####"); 
				}
			}
			
			if(playerCommand.startsWith("www") && c.playerRights >= 3) {
				c.getPA().sendFrame126(playerCommand,0);			
			}
			
			
			if (playerCommand.startsWith("xteleto") && c.playerRights >= 2) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (PlayerHandler.players[i] != null) {
						if (PlayerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(PlayerHandler.players[i].getX(), PlayerHandler.players[i].getY(), PlayerHandler.players[i].heightLevel);
						}
					}
				}			
			}
			

			
			if(playerCommand.startsWith("npc") && c.playerRights >= 3) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if(newNPC > 0) {
						Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					
				}			
			}
			
			if(playerCommand.startsWith("npc2") && c.playerRights >= 3) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(5));
					if(newNPC > 0) {
						Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 0, 0, 0, 0, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					
				}			
			}

			if(playerCommand.startsWith("man") && c.playerRights >= 3) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if(newNPC > 0) {
					      //Server.npcHandler.spawnNpc(c, n, x,    y,    0, 0, 10, 7, 7, 7, false, false);
						Server.npcHandler.spawnNpc(c, 1, 2912, 3612, 0, 0, 10, 2, 5, 5, true, true);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					
				}			
			}
			
			
			if (playerCommand.startsWith("ipban") && c.playerRights >= 2) { // use as ::ipban name
				try {
					String playerToBan = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(PlayerHandler.players[i] != null) {
							if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToBanList(PlayerHandler.players[i].connectedFrom);
								Connection.addIpToFile(PlayerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP banned the user: "+PlayerHandler.players[i].playerName+" with the host: "+PlayerHandler.players[i].connectedFrom);
								PlayerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') { // use as ::ban name
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(PlayerHandler.players[i] != null) {
							if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								PlayerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			
			if (playerCommand.startsWith("unban") && c.playerRights >= 2) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
			if (playerCommand.startsWith("anim") && c.playerRights >= 3) {
				String[] args = playerCommand.split(" ");
				c.startAnimation(Integer.parseInt(args[1]));
				c.getPA().requestUpdates();
			}
			
			if (playerCommand.startsWith("mute") && c.playerRights >= 1) {
				try {	
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(PlayerHandler.players[i] != null) {
							if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("ipmute") && c.playerRights >= 1) {
				try {	
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(PlayerHandler.players[i] != null) {
							if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToMuteList(PlayerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "+PlayerHandler.players[i].playerName);
								Client c2 = (Client)PlayerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("unipmute") && c.playerRights >= 1) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(PlayerHandler.players[i] != null) {
							if(PlayerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.unIPMuteUser(PlayerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "+PlayerHandler.players[i].playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}
			if (playerCommand.startsWith("unmute") && c.playerRights >= 1) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

		}
	}
}
		
		
		
		
		
		
		

