package server.model.players;

import server.Server;

public class DialogueHandler {

	private Client c;
	
	public DialogueHandler(Client client) {
		this.c = client;
	}
	
	/**
	 * Handles all talking
	 * @param dialogue The dialogue you want to use
	 * @param npcId The npc id that the chat will focus on during the chat
	 */
	public void sendDialogues(int dialogue, int npcId) {
		c.talkingNpc = npcId;
		switch(dialogue) {
		case 0:
			c.talkingNpc = -1;
			c.getPA().removeAllWindows();
			c.nextChat = 0;
			break;
		case 1:
			sendStatement("You found a hidden tunnel! Do you want to enter it?");
			c.dialogueAction = 1;
			c.nextChat = 2;
			break;
		case 2:
			sendOption2("Yea! I'm fearless!",  "No way! That looks scary!");
			c.dialogueAction = 1;
			c.nextChat = 0;
			break;
		case 3:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel");
			c.nextChat = 4;
		break;
		case 5:
			sendNpcChat4("Hello adventurer...", "My name is Kolodion, the master of this mage bank.", "Would you like to play a minigame in order ", 
						"to earn points towards recieving magic related prizes?", c.talkingNpc, "Kolodion");
			c.nextChat = 6;
		break;
		case 6:
			sendNpcChat4("The way the game works is as follows...", "You will be teleported to the wilderness,", 
			"You must kill mages to recieve points,","redeem points with the chamber guardian.", c.talkingNpc, "Kolodion");
			c.nextChat = 15;
		break;
		case 11:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I can assign you a slayer task suitable to your combat level.", 
			"Would you like a slayer task?", c.talkingNpc, "Duradel");
			c.nextChat = 12;
		break;
		case 12:
			sendOption2("Yes I would like a slayer task.", "No I would not like a slayer task.");
			c.dialogueAction = 5;
		break;
		case 13:
			sendNpcChat4("Hello!", "My name is Duradel and I am a master of the slayer skill.", "I see I have already assigned you a task to complete.", 
			"Would you like me to give you an easier task?", c.talkingNpc, "Duradel");
			c.nextChat = 14;
		break;
		case 14:
			sendOption2("Yes I would like an easier task.", "No I would like to keep my task.");
			c.dialogueAction = 6;
		break;
		case 15:
			sendOption2("Yes I would like to play", "No, sounds too dangerous for me.");
			c.dialogueAction = 7;
		break;
		case 16:
			sendOption2("I would like to reset my barrows brothers.", "I would like to fix all my barrows");
			c.dialogueAction = 8;
		break;
		case 17:
			sendOption5("Air", "Mind", "Water", "Earth", "More");
			c.dialogueAction = 10;
			c.dialogueId = 17;
			c.teleAction = -1;
		break;
		case 18:
			sendOption5("Fire", "Body", "Cosmic", "Astral", "More");
			c.dialogueAction = 11;
			c.dialogueId = 18;
			c.teleAction = -1;
		break;
		case 19:
			sendOption5("Nature", "Law", "Death", "Blood", "More");
			c.dialogueAction = 12;
			c.dialogueId = 19;
			c.teleAction = -1;
		break;
		case 20:
			sendNpcChat4("Hello!", "Welcome to the Arena.", "I am your guide to the game.", "What do you want to know?", c.talkingNpc, "Wise Old Man");
			c.nextChat = 21;
		break;
		case 21:
			sendOption5("How do I play?", "What are points?", "How do I train the Prayer skill?", "What are all these people here for?", "What should I do first?");
			c.dialogueAction = 21;
		break;
		case 22:
			sendNpcChat4("There are two main modes, Player versus NPC", "and Player vs Player.", "Vanakka is in charge of PvN.", "He will set you up to fight", c.talkingNpc, "Wise Old Man");
			c.nextChat = 23;
		break;
		case 23:
			sendNpcChat4("your next NPC opponent.", "To do PvP, simply challenge another player in the Arena lobby.", "PvP gets you more points, and you can", "only set stakes against Players.", c.talkingNpc, "Wise Old Man");
			c.nextChat = 21;
		break;
		case 24:
			sendNpcChat4("Points are the Arena's main currency.", "They cannot be traded from player to player.", "You use points to buy equipment and supplies.", "Points are gained from winning duels.", c.talkingNpc, "Wise Old Man");
			c.nextChat = 21;
		break;
		case 25:
			sendNpcChat4("Speak with the Priest to train prayer.", "He will teach you his holy knowledge,", "for a price, of course.", "", c.talkingNpc, "Wise Old Man");
			c.nextChat = 21;
		break;
		case 26:
			sendNpcChat4("","Most of the people here are shop owners.", "Vanakka runs PvN and the Priest trains Prayer.", "", c.talkingNpc, "Wise Old Man");
			c.nextChat = 21;
		break;
		case 27:
			sendNpcChat4("I would suggest buying some equipment,","then you should talk to Vanakka", "to participate in some duels.", "Have fun!", c.talkingNpc, "Wise Old Man");
			c.nextChat = 21;
		break;
		case 28:
			sendNpcChat4("", "Would you like to fight in a NPC duel?", "", "", c.talkingNpc, "Vannaka");
			c.nextChat = 29;
		break;
		case 29:
			sendOption2("Yes!","How does this work?");
			c.dialogueAction = 29;
		break;
		case 30:
			sendNpcChat4("I will set you up against the next NPC","in the current bracket.", "When you lose, you have to start all over.", "Try to become the arena champion!", c.talkingNpc, "Vannaka");
			c.nextChat = 29;
		break;
		case 31:
			sendNpcChat4("Welcome to the Arena!","It is time to choose a class.", "You do not always have to play as this class,", "but you will get a starter set for the class.", c.talkingNpc, "Wise Old Man");
			c.nextChat = 32;
		break;
		case 32:
			sendOption3("Archer","Mage", "Warrior");
			c.dialogueAction = 32;
			c.nextChat = 20;
		break;
		case 33:
			sendNpcChat4("Hello.","Would you like to pay for my holy", "knowledge? My prices are very reasonable!", "I'm also the only priest around here.", c.talkingNpc, "Priest");
			c.nextChat = 34;
		break;
		case 34:
			sendOption2("Yes!","No thanks.");
			c.dialogueAction = 34;
		break;
		case 35:
			sendNpcChat4("","How much knowledge would you like?", "", "", c.talkingNpc, "Priest");
			c.nextChat = 36;
		break;
		case 36:
			sendOption5("1000 XP - 25 Points", "10000 XP - 50 Points", "25000 XP - 100 Points", "50000 XP - 175 Points", "More options");
			c.dialogueAction = 36;
		break;
		case 37:
			sendOption5("100000 XP - 300 Points", "250000 XP - 500 Points", "500000 XP - 800 Points", "1000000 XP - 1500 Points", "Never mind");
			c.dialogueAction = 37;
		break;
		case 38:
			sendNpcChat4("Hello!","I am the melee armor and weapons salesman.", "Would you like to purchase something?", "Everything I sell comes in sets.", c.talkingNpc, "Horvik");
			c.nextChat = 39;
		break;
		case 39:
			sendOption5("Iron set - 50 Points", "Steel set - 75 Points", "Black set - 100 points", "Mithril set - 150 points", "More options");
			c.dialogueAction = 39;
		break;
		case 40:
			sendOption5("White set - 200 Points", "Adamant set - 250 Points", "Rune set - 400 points", "Rock-shell set - 400 points", "More options");
			c.dialogueAction = 40;
		break;
		case 41:
			sendOption5("Void melee set - 500 Points", "Penance set - 750 Points", "Rogue set - 1000 points", "Dragon set - 1500 points", "More options");
			c.dialogueAction = 41;
		break;
		case 42:
			sendOption5("Torag's set - 2750 Points", "Dharok's set - 2750 Points", "Guthan's set - 2750 points", "Verac's set - 2750 points", "More options");
			c.dialogueAction = 42;
		break;
		case 43:
			sendOption5("Bandos' set - 3500 Points", "Gilded set - 5000 Points", "Statius' set - 6000 points", "Vesta's set - 6000 points ","3rd age melee set - 8000 points");
			c.dialogueAction = 43;
		break;
		case 44:
			sendOption5("PvN Kills","PvN Deaths","PvP Kills","PvP Deaths","");
			c.dialogueAction = 44;
		break;
		case 45:
			sendNpcChat4("Hello!","I am the ranged armor and weapons salesman.", "Would you like to purchase something?", "Everything I sell comes in sets.", c.talkingNpc, "Bow and Arrow Salesman");
			c.nextChat = 46;
		break;
		case 46:
			sendOption5("Leather set - 50 Points", "Snakeskin set - 100 Points", "Green d'hide set - 250 points", "Spined set - 350 points", "More options");
			c.dialogueAction = 46;
		break;
		case 47:
			sendOption5("Void range set - 450 Points", "Blue d'hide set - 500 Points", "Red d'hide set - 650 points", "Black d'hide set - 850 points", "More options");
			c.dialogueAction = 47;
		break;
		case 48:
			sendOption5("Zamorak d'hide set - 1000 Points", "Guthix d'hide set - 1000 Points", "Saradomin d'hide set - 1000 points", "Karil's set - 2000 points", "More options");
			c.dialogueAction = 48;
		break;
		case 49:
			sendOption5("Armadyl's set - 3500 points", "Morrigan's set - 5000 points", "3rd Age range set - 7500 points", "Level 1 arrows - 5 points", "More options");
			c.dialogueAction = 49;
		break;
		case 50:
			sendOption5("Level 2 arrows - 25 points", "Regular bow set - 25 Points", "", "Never mind", "Back to the beginning");
			c.dialogueAction = 50;
		break;
		case 51:
			sendNpcChat4("Hello!","I am the magic weapons and armor salesman.", "Would you like to purchase something?", "Everything I sell comes in sets.", c.talkingNpc, "Betty");
			c.nextChat = 52;
		break;
		case 52:
			sendOption5("Blue robe set - 25 Points", "Staff set - 25 Points", "Black mystic set - 100 points", "Blue mystic set - 100 points", "More options");
			c.dialogueAction = 52;
		break;
		case 53:
			sendOption5("White mystic set - 100 Points", "Splitbark set - 250 Points", "Enchanted set - 325 points", "Void mage set - 450 points", "More options");
			c.dialogueAction = 53;
		break;
		case 54:
			sendOption5("Skeletal set - 500 Points", "Infinity set - 1000 Points", "Ahrim's set - 2000 points", "Zuriel's set - 4000 points", "More options");
			c.dialogueAction = 54;
		break;
		case 55:
			sendOption5("3rd age mage set - 6000 points", "Level 1 runes - 5 points", "Level 2 runes - 25 points", "", "More options");
			c.dialogueAction = 55;
		break;
		case 56:
			sendOption5("", "", "", "Never mind", "Back to the beginning");
			c.dialogueAction = 56;
		break;
		case 57:
			sendNpcChat4("Hello!","I am the food salesman.", "Would you like to purchase something?", "Everything I sell comes in sets.", c.talkingNpc, "Fisherman");
			c.nextChat = 58;
		break;
		case 58:
			sendOption5("Level 1 Food - 5 Points", "Level 2 Food - 25 Points", "", "", "Never mind");
			c.dialogueAction = 58;
		break;
		case 59:
			sendNpcChat4("Hello!","I am the potions salesman.", "Would you like to purchase some potions?", "", c.talkingNpc, "Apothecary");
			c.nextChat = 60;
		break;
		case 60:
			sendOption2("Yes", "No");
			c.dialogueAction = 60;
		break;
		case 61:
			sendNpcChat4("Hi, I'm Bob.","There is a far more dangerous world outside of here.", "We need your help to protect it.", "What do you say?", c.talkingNpc, "Bob");
			c.nextChat = 62;
		break;
		case 62:
			sendOption2("Yes", "No");
			c.dialogueAction = 62;
		break;
		case 63:
			sendNpcChat4("","Your current task is: Defeat "+Server.npcHandler.getNpcListName(c.playerJob)+"", "Are you ready to go?", "", c.talkingNpc, "Bob");
			c.nextChat = 64;
		break;
		case 64:
			sendOption2("Yes", "No");
			c.dialogueAction = 64;
		break;
		case 65:
			sendNpcChat4("Hello! The name's Captain Shanks.","I run a little betting service here at the Arena.", "Do you feel like placing a little wager?", "Or are ya too chicken?", c.talkingNpc, "Captain Shanks");
			c.nextChat = 66;
		break;
		case 66:
			sendOption2("Sure, why not?", "How does this work?");
			c.dialogueAction = 66;
		break;
		case 67:
			sendNpcChat4("It's real simple. You choose from 2 fights.","You pick who you think will win, and bet some points.", "If you win, you get double your bet.", "If you lose, well, tough luck!", c.talkingNpc, "Captain Shanks");
			c.nextChat = 66;
		break;
		case 68:
			sendOption2(c.fightOne[0].name + " Vs. " + c.fightOne[1].name, c.fightTwo[0].name + " Vs. " + c.fightTwo[1].name);
			c.dialogueAction = 68;
		break;
		case 69:
			sendOption5("1 point", "5", "10", "25", "50");
			c.dialogueAction = 69;
		break;
		case 70:
			sendNpcChat4("","Who do you think will win?", "", "", c.talkingNpc, "Captain Shanks");
			if (c.fight == 1)
				c.nextChat = 71;
			else if (c.fight == 2)
				c.nextChat = 72;
		break;
		case 71:
			sendOption2(c.fightOne[0].name, c.fightOne[1].name);
			c.dialogueAction = 71;
		break;
		case 72:
			sendOption2(c.fightTwo[0].name, c.fightTwo[1].name);
			c.dialogueAction = 72; //do these 2 in clickingbuttons
		break;
		case 73:
			sendNpcChat4("","Place your bet!", "", "", c.talkingNpc, "Captain Shanks");
			c.nextChat = 69;
		break;
		case 74:
			sendNpcChat4("",c.winner.name + " Defeated " + c.loser.name,"You win! Come again soon!", "", c.talkingNpc, "Captain Shanks");
			c.nextChat = -1;
			c.dialogueAction = -1;
			c.getPA().clearArenaBetting();
		break;
		case 75:
			sendNpcChat4("",c.winner.name + " Defeated " + c.loser.name,"You lose! Better luck next time!", "", c.talkingNpc, "Captain Shanks");
			c.nextChat = -1;
			c.dialogueAction = -1;
			c.getPA().clearArenaBetting();
		break;
		case 76:
			sendNpcChat4("Hello. I'm Advisor Ghrim.","I can send you to a dungeon.","You should be able to kill a level 200 monster.", "Do you want to go?", c.talkingNpc, "Advisor Ghrim");
			c.nextChat = 77;
		break;
		case 77:
			sendOption2("Yes!", "No thanks.");
			c.dialogueAction = 77;
		break;
		case 78:
			sendOption2("Continue to Room 2", "Go home");
			c.dialogueAction = 78;
		break;
		case 79:
			sendNpcChat4("Hey. You see those trees up by the temple?","You know, you can bring me those logs,","and I'll buy them from you. I can even sell you", "an axe. What do you say?", c.talkingNpc, "Forester");
			c.nextChat = 80;
		break;
		case 80:
			sendOption2("Can I buy an axe?", "I'd like to sell my logs.");
			c.dialogueAction = 80;
		break;
		case 81:
			sendNpcChat4("Bah! These rocks are worthless! No ores!","I know where the ores are. I can show ya.","I can sell ya a pickaxe. And, I hear there's a fellow there", "who'll buy yer ores off ya!", c.talkingNpc, "Nurmof");
			c.nextChat = 82;
		break;
		case 82:
			sendOption2("Can you send me to the mine?", "I'd like to buy a pickaxe.");
			c.dialogueAction = 82;
		break;
		case 83:
			sendNpcChat4("","Hey kid. You bring me ores,","I'll buy em from ya.","", c.talkingNpc, "Dwarf");
			c.nextChat = 84;
		break;
		case 84:
			sendOption2("I've got ores to sell.", "Never mind.");
			c.dialogueAction = 84;
		break;
		}
	}
	
	/*
	 * Information Box
	 */
	
	public void sendStartInfo(String text, String text1, String text2, String text3, String title) {
		c.getPA().sendFrame126(title, 6180);
		c.getPA().sendFrame126(text, 6181);
		c.getPA().sendFrame126(text1, 6182);
		c.getPA().sendFrame126(text2, 6183);
		c.getPA().sendFrame126(text3, 6184);
		c.getPA().sendFrame164(6179);
	}
	
	/*
	 * Options
	 */
	
	@SuppressWarnings("unused")
	private void sendOption(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2470);
	 	c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126("Click here to continue", 2473);
		c.getPA().sendFrame164(13758);
	}	
	
	private void sendOption2(String s, String s1) {
		c.getPA().sendFrame126("Select an Option", 2460);
		c.getPA().sendFrame126(s, 2461);
		c.getPA().sendFrame126(s1, 2462);
		c.getPA().sendFrame164(2459);
	}
	
	private void sendOption3(String s, String s1, String s2) {
		c.getPA().sendFrame126("Select an Option", 2470);
		c.getPA().sendFrame126(s, 2471);
		c.getPA().sendFrame126(s1, 2472);
		c.getPA().sendFrame126(s2, 2473);
		c.getPA().sendFrame164(2469);
	}
	
	public void sendOption4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame126("Select an Option", 2481);
		c.getPA().sendFrame126(s, 2482);
		c.getPA().sendFrame126(s1, 2483);
		c.getPA().sendFrame126(s2, 2484);
		c.getPA().sendFrame126(s3, 2485);
		c.getPA().sendFrame164(2480);
	}
	
	public void sendOption5(String s, String s1, String s2, String s3, String s4) {
		c.getPA().sendFrame126("Select an Option", 2493);
		c.getPA().sendFrame126(s, 2494);
		c.getPA().sendFrame126(s1, 2495);
		c.getPA().sendFrame126(s2, 2496);
		c.getPA().sendFrame126(s3, 2497);
		c.getPA().sendFrame126(s4, 2498);
		c.getPA().sendFrame164(2492);
	}

	/*
	 * Statements
	 */
	
	private void sendStatement(String s) { // 1 line click here to continue chat box interface
		c.getPA().sendFrame126(s, 357);
		c.getPA().sendFrame126("Click here to continue", 358);
		c.getPA().sendFrame164(356);
	}
	
	/*
	 * Npc Chatting
	 */
	
	@SuppressWarnings("unused")
	private void sendNpcChat1(String s) {
		
	}
	
	private void sendNpcChat4(String s, String s1, String s2, String s3, int ChatNpc, String name) {
		c.getPA().sendFrame200(4901, 591);
		c.getPA().sendFrame126(name, 4902);
		c.getPA().sendFrame126(s, 4903);
		c.getPA().sendFrame126(s1, 4904);
		c.getPA().sendFrame126(s2, 4905);
		c.getPA().sendFrame126(s3, 4906);
		c.getPA().sendFrame75(ChatNpc, 4901);
		c.getPA().sendFrame164(4900);
	}
	
	/*
	 * Player Chating Back
	 */
	
	@SuppressWarnings("unused")
	private void sendPlayerChat1(String s) {
		c.getPA().sendFrame200(969, 591);
		c.getPA().sendFrame126(c.playerName, 970);
		c.getPA().sendFrame126(s, 971);
		c.getPA().sendFrame185(969);
		c.getPA().sendFrame164(968);
	}
	
	@SuppressWarnings("unused")
	private void sendPlayerChat2(String s, String s1) {
		c.getPA().sendFrame200(974, 591);
		c.getPA().sendFrame126(c.playerName, 975);
		c.getPA().sendFrame126(s, 976);
		c.getPA().sendFrame126(s1, 977);
		c.getPA().sendFrame185(974);
		c.getPA().sendFrame164(973);
	}
	
	@SuppressWarnings("unused")
	private void sendPlayerChat3(String s, String s1, String s2) {
		c.getPA().sendFrame200(980, 591);
		c.getPA().sendFrame126(c.playerName, 981);
		c.getPA().sendFrame126(s, 982);
		c.getPA().sendFrame126(s1, 983);
		c.getPA().sendFrame126(s2, 984);
		c.getPA().sendFrame185(980);
		c.getPA().sendFrame164(979);
	}
	
	@SuppressWarnings("unused")
	private void sendPlayerChat4(String s, String s1, String s2, String s3) {
		c.getPA().sendFrame200(987, 591);
		c.getPA().sendFrame126(c.playerName, 988);
		c.getPA().sendFrame126(s, 989);
		c.getPA().sendFrame126(s1, 990);
		c.getPA().sendFrame126(s2, 991);
		c.getPA().sendFrame126(s3, 992);
		c.getPA().sendFrame185(987);
		c.getPA().sendFrame164(986);
	}
}
