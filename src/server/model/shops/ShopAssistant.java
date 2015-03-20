package server.model.shops;

import server.Config;
import server.Server;
import server.model.items.Item;
import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.world.ShopHandler;

public class ShopAssistant {

	private Client c;
	
	public ShopAssistant(Client client) {
		this.c = client;
	}
	
	/**
	*Shops
	**/
	
	public void openShop(int ShopID){		
		c.getItems().resetItems(3823);
		resetShop(ShopID);
		c.isShopping = true;
		c.myShopId = ShopID;
		c.getPA().sendFrame248(3824, 3822);
		c.getPA().sendFrame126(ShopHandler.ShopName[ShopID], 3901);
	}

	public void buySet(String set) {
		int price = 0;
		boolean itemsStacked = false;
		int[] itemsToAdd = new int[10];
		switch(set) {
			case "iron":
				price = 50;
				itemsToAdd[0] = 1067;
				itemsToAdd[1] = 1115;
				itemsToAdd[2] = 1153;
				itemsToAdd[3] = 1191;
				itemsToAdd[4] = 1309;
				itemsToAdd[5] = 1323;
				itemsToAdd[6] = 1059;
				itemsToAdd[7] = 1061;
			break;
			case "steel":
				price = 75;
				itemsToAdd[0] = 1069;
				itemsToAdd[1] = 1119;
				itemsToAdd[2] = 1157;
				itemsToAdd[3] = 1193;
				itemsToAdd[4] = 1311;
				itemsToAdd[5] = 1325;
				itemsToAdd[6] = 1059;
				itemsToAdd[7] = 1061;
			break;
			case "black":
				price = 100;
				itemsToAdd[0] = 1077;
				itemsToAdd[1] = 1125;
				itemsToAdd[2] = 1165;
				itemsToAdd[3] = 1195;
				itemsToAdd[4] = 1313;
				itemsToAdd[5] = 1327;
				itemsToAdd[6] = 1059;
				itemsToAdd[7] = 1061;
			break;
			case "mithril":
				price = 150;
				itemsToAdd[0] = 1071;
				itemsToAdd[1] = 1121;
				itemsToAdd[2] = 1159;
				itemsToAdd[3] = 1197;
				itemsToAdd[4] = 1315;
				itemsToAdd[5] = 1329;
				itemsToAdd[6] = 1059;
				itemsToAdd[7] = 1061;
			break;
			case "white":
				price = 200;
				itemsToAdd[0] = 6625;
				itemsToAdd[1] = 6617;
				itemsToAdd[2] = 6623;
				itemsToAdd[3] = 6633;
				itemsToAdd[4] = 6609;
				itemsToAdd[5] = 6611;
				itemsToAdd[6] = 6629;
				itemsToAdd[7] = 6619;
			break;
			case "adamant":
				price = 250;
				itemsToAdd[0] = 1073;
				itemsToAdd[1] = 1123;
				itemsToAdd[2] = 1161;
				itemsToAdd[3] = 1199;
				itemsToAdd[4] = 1317;
				itemsToAdd[5] = 1331;
				itemsToAdd[6] = 1059;
				itemsToAdd[7] = 1061;
			break;
			case "rune":
				price = 400;
				itemsToAdd[0] = 1079;
				itemsToAdd[1] = 1127;
				itemsToAdd[2] = 1163;
				itemsToAdd[3] = 1201;
				itemsToAdd[4] = 1319;
				itemsToAdd[5] = 1333;
				itemsToAdd[6] = 1059;
				itemsToAdd[7] = 1061;
			break;
			case "rockshell":
				price = 400;
				itemsToAdd[0] = 6128;
				itemsToAdd[1] = 6129;
				itemsToAdd[2] = 6130;
				itemsToAdd[3] = 6145;
				itemsToAdd[4] = 6151;
			break;
			case "void melee":
				price = 500;
				itemsToAdd[0] = 8839;
				itemsToAdd[1] = 8840;
				itemsToAdd[2] = 8842;
				itemsToAdd[3] = 11665;
			break;
			case "penance":
				price = 750;
				itemsToAdd[0] = 10547;
				itemsToAdd[1] = 10548;
				itemsToAdd[2] = 10551;
				itemsToAdd[3] = 10552;
				itemsToAdd[4] = 10553;
			break;
			case "rogue":
				price = 1000;
				itemsToAdd[0] = 5553;
				itemsToAdd[1] = 5554;
				itemsToAdd[2] = 5555;
				itemsToAdd[3] = 5556;
				itemsToAdd[4] = 5557;
			break;
			case "dragon":
				price = 1500;
				itemsToAdd[0] = 4087;
				itemsToAdd[1] = 3140;
				itemsToAdd[2] = 11335;
				itemsToAdd[3] = 1187;
				itemsToAdd[4] = 7158;
				itemsToAdd[5] = 4587;
				itemsToAdd[6] = 7461;
				itemsToAdd[7] = 11732;
			break;
			case "torag":
				price = 2750;
				itemsToAdd[0] = 4751;
				itemsToAdd[1] = 4749;
				itemsToAdd[2] = 4745;
				itemsToAdd[3] = 4747;
				itemsToAdd[4] = 7462;
				itemsToAdd[5] = 1061;
			break;
			case "dharok":
				price = 2750;
				itemsToAdd[0] = 4716;
				itemsToAdd[1] = 4718;
				itemsToAdd[2] = 4720;
				itemsToAdd[3] = 4722;
				itemsToAdd[4] = 7462;
				itemsToAdd[5] = 1061;
			break;
			case "guthan":
				price = 2750;
				itemsToAdd[0] = 4724;
				itemsToAdd[1] = 4726;
				itemsToAdd[2] = 4728;
				itemsToAdd[3] = 4730;
				itemsToAdd[4] = 7462;
				itemsToAdd[5] = 1061;
			break;
			case "verac":
				price = 2750;
				itemsToAdd[0] = 4753;
				itemsToAdd[1] = 4755;
				itemsToAdd[2] = 4757;
				itemsToAdd[3] = 4759;
				itemsToAdd[4] = 7462;
				itemsToAdd[5] = 1061;
			break;
			case "bandos":
				price = 3500;
				itemsToAdd[1] = 11724;
				itemsToAdd[2] = 11726;
				itemsToAdd[3] = 11728;
				itemsToAdd[4] = 7462;
			break;
			case "gilded":
				price = 5000;
				itemsToAdd[0] = 3481;
				itemsToAdd[1] = 3483;
				itemsToAdd[2] = 3486;
				itemsToAdd[3] = 3488;
			break;
			case "statius":
				price = 6000;
				itemsToAdd[0] = 13884;
				itemsToAdd[1] = 13890;
				itemsToAdd[2] = 13896;
				itemsToAdd[3] = 13902;
			break;
			case "vesta":
				price = 6000;
				itemsToAdd[0] = 13887;
				itemsToAdd[1] = 13893;
				itemsToAdd[2] = 13899;
				itemsToAdd[3] = 13905;
			break;
			case "3rd age melee":
				price = 8000;
				itemsToAdd[0] = 10346;
				itemsToAdd[1] = 10348;
				itemsToAdd[2] = 10350;
				itemsToAdd[3] = 10352;
			break;
			case "leather":
				price = 50;
				itemsToAdd[0] = 1095;
				itemsToAdd[1] = 1129;
				itemsToAdd[2] = 1167;
				itemsToAdd[3] = 1063;
				itemsToAdd[4] = 1061;
				itemsToAdd[5] = 841;
			break;
			case "snakeskin":
				price = 100;
				itemsToAdd[0] = 6324;
				itemsToAdd[1] = 6322;
				itemsToAdd[2] = 6326;
				itemsToAdd[3] = 6330;
				itemsToAdd[4] = 6328;
			break;
			case "green dhide":
				price = 250;
				itemsToAdd[0] = 1099;
				itemsToAdd[1] = 1135;
				itemsToAdd[2] = 1167;
				itemsToAdd[3] = 1065;
				itemsToAdd[4] = 1061;
			break;
			case "spined":
				price = 350;
				itemsToAdd[0] = 6131;
				itemsToAdd[1] = 6133;
				itemsToAdd[2] = 6135;
				itemsToAdd[3] = 6143;
				itemsToAdd[4] = 6149;
			break;
			case "void range":
				price = 450;
				itemsToAdd[0] = 8839;
				itemsToAdd[1] = 8840;
				itemsToAdd[2] = 8842;
				itemsToAdd[3] = 11664;
			break;
			case "blue dhide":
				price = 500;
				itemsToAdd[0] = 2493;
				itemsToAdd[1] = 2499;
				itemsToAdd[2] = 1167;
				itemsToAdd[3] = 2487;
				itemsToAdd[4] = 1061;
			break;
			case "red dhide":
				price = 650;
				itemsToAdd[0] = 2495;
				itemsToAdd[1] = 2501;
				itemsToAdd[2] = 1167;
				itemsToAdd[3] = 2489;
				itemsToAdd[4] = 1061;
			break;
			case "black dhide":
				price = 850;
				itemsToAdd[0] = 2497;
				itemsToAdd[1] = 2503;
				itemsToAdd[2] = 1167;
				itemsToAdd[3] = 2491;
				itemsToAdd[4] = 1061;
			break;
			case "zamorak dhide":
				price = 1000;
				itemsToAdd[0] = 10372;
				itemsToAdd[1] = 10370;
				itemsToAdd[2] = 10374;
				itemsToAdd[3] = 10368;
				itemsToAdd[4] = 1061;
			break;
			case "guthix dhide":
				price = 1000;
				itemsToAdd[0] = 10380;
				itemsToAdd[1] = 10378;
				itemsToAdd[2] = 10382;
				itemsToAdd[3] = 10376;
				itemsToAdd[4] = 1061;
			break;
			case "saradomin dhide":
				price = 1000;
				itemsToAdd[0] = 10388;
				itemsToAdd[1] = 10386;
				itemsToAdd[2] = 10390;
				itemsToAdd[3] = 10384;
				itemsToAdd[4] = 1061;
			break;
			case "karils":
				price = 2000;
				itemsToAdd[0] = 4738;
				itemsToAdd[1] = 4736;
				itemsToAdd[2] = 4732;
				itemsToAdd[3] = 4734;
			break;
			case "armadyl":
				price = 3500;
				itemsToAdd[0] = 11718;
				itemsToAdd[1] = 11720;
				itemsToAdd[2] = 11722;
			break;
			case "morrigan":
				price = 5000;
				itemsToAdd[0] = 13870;
				itemsToAdd[1] = 13873;
				itemsToAdd[2] = 13876;
			break;
			case "3rd age range":
				price = 7500;
				itemsToAdd[0] = 10330;
				itemsToAdd[1] = 10332;
				itemsToAdd[2] = 10334;
				itemsToAdd[3] = 10336;
			break;
			case "lvl 1 arrows":
				price = 5;
				itemsStacked = true;
				itemsToAdd[0] = 884;
				itemsToAdd[1] = 886;
				itemsToAdd[2] = 888;
			break;
			case "lvl 2 arrows":
				price = 25;
				itemsStacked = true;
				itemsToAdd[0] = 890;
				itemsToAdd[1] = 892;
				itemsToAdd[2] = 11212;
			break;
			case "bow":
				price = 25;
				itemsToAdd[0] = 841;
				itemsToAdd[1] = 843;
				itemsToAdd[2] = 849;
				itemsToAdd[3] = 853;
				itemsToAdd[4] = 857;
			break;
			case "blue robe":
				price = 25;
				itemsToAdd[0] = 577;
				itemsToAdd[1] = 579;
				itemsToAdd[2] = 1011;
			break;
			case "staffs":
				price = 25;
				itemsToAdd[0] = 1381;
				itemsToAdd[1] = 1383;
				itemsToAdd[2] = 1385;
				itemsToAdd[3] = 1387;
			break;
			case "blue mystic":
				price = 100;
				itemsToAdd[0] = 4089;
				itemsToAdd[1] = 4091;
				itemsToAdd[2] = 4093;
				itemsToAdd[3] = 4095;
				itemsToAdd[4] = 4097;
			break;
			case "black mystic":
				price = 100;
				itemsToAdd[0] = 4099;
				itemsToAdd[1] = 4101;
				itemsToAdd[2] = 4103;
				itemsToAdd[3] = 4105;
				itemsToAdd[4] = 4107;
			break;
			case "white mystic":
				price = 100;
				itemsToAdd[0] = 4109;
				itemsToAdd[1] = 4111;
				itemsToAdd[2] = 4113;
				itemsToAdd[3] = 4115;
				itemsToAdd[4] = 4117;
			break;
			case "splitbark":
				price = 250;
				itemsToAdd[0] = 3385;
				itemsToAdd[1] = 3387;
				itemsToAdd[2] = 3389;
				itemsToAdd[3] = 3391;
				itemsToAdd[4] = 3393;
			break;
			case "enchanted":
				price = 325;
				itemsToAdd[0] = 7398;
				itemsToAdd[1] = 7399;
				itemsToAdd[2] = 7400;
			break;
			case "void mage":
				price = 450;
				itemsToAdd[0] = 8839;
				itemsToAdd[1] = 8840;
				itemsToAdd[2] = 8842;
				itemsToAdd[3] = 11663;
			break;
			case "skeletal":
				price = 500;
				itemsToAdd[0] = 6137;
				itemsToAdd[1] = 6139;
				itemsToAdd[2] = 6141;
				itemsToAdd[3] = 6147;
				itemsToAdd[4] = 6153;
			break;
			case "infinity":
				price = 1000;
				itemsToAdd[0] = 6916;
				itemsToAdd[1] = 6918;
				itemsToAdd[2] = 6920;
				itemsToAdd[3] = 6922;
				itemsToAdd[4] = 6924;
			break;
			case "ahrims":
				price = 2000;
				itemsToAdd[0] = 4708;
				itemsToAdd[1] = 4710;
				itemsToAdd[2] = 4712;
				itemsToAdd[3] = 4714;
			break;
			case "zuriel":
				price = 4000;
				itemsToAdd[0] = 13858;
				itemsToAdd[1] = 13861;
				itemsToAdd[2] = 13864;
				itemsToAdd[3] = 13867;
			break;
			case "3rd age mage":
				price = 6000;
				itemsToAdd[0] = 10338;
				itemsToAdd[1] = 10340;
				itemsToAdd[2] = 10342;
				itemsToAdd[3] = 10344;
			break;
			case "lvl 1 runes":
				price = 5;
				itemsStacked = true;
				itemsToAdd[0] = 554;
				itemsToAdd[1] = 555;
				itemsToAdd[2] = 556;
				itemsToAdd[3] = 557;
				itemsToAdd[4] = 558;
				itemsToAdd[5] = 559;
			break;
			case "lvl 2 runes":
				price = 25;
				itemsStacked = true;
				itemsToAdd[0] = 560;
				itemsToAdd[1] = 561;
				itemsToAdd[2] = 562;
				itemsToAdd[3] = 563;
				itemsToAdd[4] = 564;
				itemsToAdd[5] = 565;
				itemsToAdd[6] = 566;
			break;
			case "lvl 1 food":
				price = 5;
				itemsStacked = true;
				itemsToAdd[0] = 380;
			break;
			case "lvl 2 food":
				price = 25;
				itemsStacked = true;
				itemsToAdd[0] = 392;
			break;
			case "pots":
				price = 25;
				itemsStacked = true;
				itemsToAdd[0] = 2429;
				itemsToAdd[1] = 2433;
				itemsToAdd[2] = 2435;
				itemsToAdd[3] = 2445;
				itemsToAdd[4] = 114;
				itemsToAdd[5] = 3041;
			break;
			default:
				System.out.println("unknown set");
			break;
		}
		if (price > c.points) {
			c.sendMessage("You don't have enough points.");
			return;
		} else {
			c.points -= price;
			c.refreshInfo();
			for (int i = 0; i < itemsToAdd.length; i++) {
				if (itemsStacked)
					c.getItems().addItem(itemsToAdd[i], 1000);
				else
					c.getItems().addItem(itemsToAdd[i], 1);
			}
		}
		c.getPA().closeAllWindows();
		c.dialogueAction = -1;
	}
	
	public void updatePlayerShop() {
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].isShopping == true && PlayerHandler.players[i].myShopId == c.myShopId && i != c.playerId) {
					PlayerHandler.players[i].updateShop = true;
				}
			}
		}
	}
	
	
	public void updateshop(int i){
		resetShop(i);
	}
	
	public void resetShop(int ShopID) {
		synchronized(c) {
			int TotalItems = 0;
			for (int i = 0; i < ShopHandler.MaxShopItems; i++) {
				if (ShopHandler.ShopItems[ShopID][i] > 0) {
					TotalItems++;
				}
			}
			if (TotalItems > ShopHandler.MaxShopItems) {
				TotalItems = ShopHandler.MaxShopItems;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(3900);
			c.getOutStream().writeWord(TotalItems);
			int TotalCount = 0;
			for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
				if (ShopHandler.ShopItems[ShopID][i] > 0 || i <= ShopHandler.ShopItemsStandard[ShopID]) {
					if (ShopHandler.ShopItemsN[ShopID][i] > 254) {
						c.getOutStream().writeByte(255); 					
						c.getOutStream().writeDWord_v2(ShopHandler.ShopItemsN[ShopID][i]);	
					} else {
						c.getOutStream().writeByte(ShopHandler.ShopItemsN[ShopID][i]);
					}
					if (ShopHandler.ShopItems[ShopID][i] > Config.ITEM_LIMIT || ShopHandler.ShopItems[ShopID][i] < 0) {
						ShopHandler.ShopItems[ShopID][i] = Config.ITEM_LIMIT;
					}
					c.getOutStream().writeWordBigEndianA(ShopHandler.ShopItems[ShopID][i]);
					TotalCount++;
				}
				if (TotalCount > TotalItems) {
					break;
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();	
		}
	}
	
	
	public double getItemShopValue(int ItemID, int Type, int fromSlot) {
		double ShopValue = 1;
		double TotPrice = 0;
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					ShopValue = Server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}
		
		TotPrice = ShopValue;

		if (ShopHandler.ShopBModifier[c.myShopId] == 1) {
			TotPrice *= 1; 
			TotPrice *= 1;
			if (Type == 1) {
				TotPrice *= 1; 
			}
		} else if (Type == 1) {
			TotPrice *= 1; 
		}
		return TotPrice;
	}
	
	public int getItemShopValue(int itemId) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == itemId) {
					return (int)Server.itemHandler.ItemList[i].ShopValue;
				}
			}	
		}
		return 0;
	}
	
	
	public int getValue(int item) {
		switch(item) {
		//begin melee weapons
		case 4151: //whip
			return 500;
		case 4153: //g maul
			return 250;
		case 6528: //obby maul
			return 200;
		case 11694:
		case 11696:
		case 11698:
		case 11700: //godswords
			return 750;
		case 11716: //zammy spear
			return 400;
		case 11730: //saradomin sword
			return 600;
		case 15037: //chaotic rapier
			return 600;
		case 15038: //chaotic longsword
			return 600;
		case 15039: //chaotic maul
			return 700;
		//end melee weapons
			
		//begin range weapons/bows
		case 861: //magic short
			return 100;
		case 11235: //dark bow
			return 750;
		//end range weapons/bows
			
		//begin magic weapons/staves
		case 4675: //ancient staff
			return 250;
		case 15040: //chaotic staff
			return 400;
		//end magic weapons/staves
			
		//jewelry
		case 1725:
		case 1727:
		case 1729:
		case 1478: //str mag def acc
			return 25;
		case 1731: //power
			return 50;
		case 1704: //glory
			return 150;
		case 6585: //fury
			return 750;
		case 6731:
		case 6733:
		case 6735:
		case 6737: //war seer arch bers
			return 400;
		//end jewelry
			
			//misc
		case 3840:
		case 3842:
		case 3844: //god books
			return 50;
		case 8844: //bronze def
			return 1;
		case 8845: //iron
			return 3;
		case 8846: //steel
			return 5;
		case 8847: //black
			return 10;
		case 8848: //mithril
			return 15;
		case 8849: //adamant
			return 25;
		case 8850: //rune
			return 50;
		case 1540: //anti drag shield
			return 25;
		case 11283: //dfs
			return 600;
			//end misc
			
			//axes
		case 1349:
			return 10;
		case 1353:
			return 25;
		case 1361:
			return 40;
		case 1355: 
			return 60;
		case 1357:
			return 90;
		case 1359:
			return 150;
		case 6739:
			return 400;
			//end axes
			
			//logs
		case 1511:
		case 1521:
		case 1519:
		case 1517:
			return 1;
		case 1515:
		case 1513:
			return 2;
			//end logs
			
			//pickaxes
		case 1267:
			return 10;
		case 1269:
			return 25;
		case 1273:
			return 40;
		case 1271:
			return 90;
		case 1275:
			return 150;
			//end pickaxes
			
			//ores
		case 440:
		case 453:
		case 444:
		case 447:
			return 1;
		case 449:
		case 451:
			return 2;
			//end ores
			
			default:
				return 0;
				
			
		}
	}
	
	
	/**
	*buy item from shop (Shop Price)
	**/
	
	public void buyFromShopPrice(int removeId, int removeSlot){
		/*int ShopValue = (int)Math.floor(getItemShopValue(removeId, 0, removeSlot));
		ShopValue *= 1.15;
		String ShopAdd = "";
		if (c.myShopId >= 17) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " points.");
			return;
		}
		if (c.myShopId == 15) {
			c.sendMessage("This item current costs " + c.getItems().getUntradePrice(removeId) + " coins.");
			return;
		}
		if (ShopValue >= 1000 && ShopValue < 1000000) {
			ShopAdd = " (" + (ShopValue / 1000) + "K)";
		} else if (ShopValue >= 1000000) {
			ShopAdd = " (" + (ShopValue / 1000000) + " million)";
		}
		c.sendMessage(c.getItems().getItemName(removeId)+": currently costs "+ShopValue+" coins"+ShopAdd);*/
		if (c.myShopId == 14) {
			c.sendMessage("Skillcapes cost 9 points.");
			return;
		}
		c.sendMessage(c.getItems().getItemName(removeId)+": currently costs "+getValue(removeId)+" points.");
	}
	
	/**
	*Sell item to shop (Shop Price)
	**/
	public void sellToShopPrice(int removeId, int removeSlot) {
		if (c.myShopId == 14) {
			c.sendMessage("You can't sell skillcapes.");
			return;
		}
		boolean IsIn = false;
		if (ShopHandler.ShopSModifier[c.myShopId] > 1) {
			for (int j = 0; j <= ShopHandler.ShopItemsStandard[c.myShopId]; j++) {
				if (removeId == (ShopHandler.ShopItems[c.myShopId][j] - 1)) {
					IsIn = true;
					break;
				}
			}
		} else {
			IsIn = true;
		}
		if (IsIn == false) {
			c.sendMessage("You can't sell "+c.getItems().getItemName(removeId).toLowerCase()+" to this store.");
		} else {
			c.sendMessage(c.getItems().getItemName(removeId)+": shop will buy for "+getValue(removeId)/2+" points.");
		}
	}
	
	
	
	public boolean sellItem(int itemID, int fromSlot, int amount) {
		if (c.myShopId == 14)
			return false;
		if (amount > 0 && itemID == (c.playerItems[fromSlot] - 1)) {
			if (ShopHandler.ShopSModifier[c.myShopId] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= ShopHandler.ShopItemsStandard[c.myShopId]; i++) {
					if (itemID == (ShopHandler.ShopItems[c.myShopId][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false) {
					c.sendMessage("You can't sell "+c.getItems().getItemName(itemID).toLowerCase()+" to this store.");
					return false;
				}
			}
			if (amount > c.playerItemsN[fromSlot] && (Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == true || Item.itemStackable[(c.playerItems[fromSlot] - 1)] == true)) {
				amount = c.playerItemsN[fromSlot];
			} else if (amount > c.getItems().getItemAmount(itemID) && Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == false && Item.itemStackable[(c.playerItems[fromSlot] - 1)] == false) {
				amount = c.getItems().getItemAmount(itemID);
			}
			for (int i = amount; i > 0; i--) {
				if (Item.itemIsNote[itemID] == false) {
					c.getItems().deleteItem(itemID, c.getItems().getItemSlot(itemID), 1);
				} else {
					c.getItems().deleteItem(itemID, fromSlot, 1);
				}
				c.points += getValue(itemID)/2;
				c.refreshInfo();
				addShopItem(itemID, 1);
			}
			c.getItems().resetItems(3823);
			resetShop(c.myShopId);
			updatePlayerShop();
			return true;
		}
		return true;
	}
	
	public boolean addShopItem(int itemID, int amount) {
		boolean Added = false;
		if (amount <= 0) {
			return false;
		}
		if (Item.itemIsNote[itemID] == true) {
			itemID = c.getItems().getUnnotedItem(itemID);
		}
		for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
			if ((ShopHandler.ShopItems[c.myShopId][i] - 1) == itemID) {
				ShopHandler.ShopItemsN[c.myShopId][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
				if (ShopHandler.ShopItems[c.myShopId][i] == 0) {
					ShopHandler.ShopItems[c.myShopId][i] = (itemID + 1);
					ShopHandler.ShopItemsN[c.myShopId][i] = amount;
					ShopHandler.ShopItemsDelay[c.myShopId][i] = 0;
					break;
				}
			}
		}
		return true;
	}
	
	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if (c.myShopId == 14) {
			skillBuy(itemID);
			return false;
		}
		if (amount > 0) {
			if (amount > ShopHandler.ShopItemsN[c.myShopId][fromSlot]) {
				amount = ShopHandler.ShopItemsN[c.myShopId][fromSlot];
			}
			for (int i = amount; i > 0; i--) {
				if (c.points < getValue(itemID)) {
					c.sendMessage("You don't have enough points.");
					break;
				}
				if (c.points >= getValue(itemID)) {
					if (c.getItems().freeSlots() > 0) {
						c.points -= getValue(itemID);
						c.refreshInfo();
						c.getItems().addItem(itemID, 1);
						ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
						ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
						if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
							ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
						}
					} else {
						c.sendMessage("You don't have enough space in your inventory.");
						break;
					}
				} else {
					c.sendMessage("You don't have enough points.");
					break;
				}
			}
			c.getItems().resetItems(3823);
			resetShop(c.myShopId);
			updatePlayerShop();
			return true;
		}
		return false;
	}	
	
		
		public void openSkillCape() {
			int capes = get99Count();
			if (capes > 1)
				capes = 1;
			else
				capes = 0;
			c.myShopId = 14;
			setupSkillCapes(capes, get99Count());		
		}
		
		public int[] skillCapes = {9747,9753,9750,9768,9756,9759,9762,9801,9807,9783,9798,9804,9780,9795,9792,9774,9771,9777,9786,9810,9765};
		public int get99Count() {
			int count = 0;
			for (int j = 0; j < c.playerLevel.length; j++) {
				if (c.getLevelForXP(c.playerXP[j]) >= 99) {
					count++;				
				}			
			}		
			return count;
		}
		
		public void setupSkillCapes(int capes, int capes2) {
			synchronized(c) {
				c.getItems().resetItems(3823);
				c.isShopping = true;
				c.myShopId = 14;
				c.getPA().sendFrame248(3824, 3822);
				c.getPA().sendFrame126("Skillcape Shop", 3901);
				
				int TotalItems = 0;
				TotalItems = capes2;
				if (TotalItems > ShopHandler.MaxShopItems) {
					TotalItems = ShopHandler.MaxShopItems;
				}
				c.getOutStream().createFrameVarSizeWord(53);
				c.getOutStream().writeWord(3900);
				c.getOutStream().writeWord(TotalItems);
				@SuppressWarnings("unused")
				int TotalCount = 0;
				for (int i = 0; i < 21; i++) {
					if (c.getLevelForXP(c.playerXP[i]) < 99)
						continue;
					c.getOutStream().writeByte(1);
					c.getOutStream().writeWordBigEndianA(skillCapes[i] + 2);
					TotalCount++;
				}
				c.getOutStream().endFrameVarSizeWord();
				c.flushOutStream();	
			}
		}
		
		public void skillBuy(int item) {
			int nn = get99Count();
			if (nn > 1)
				nn = 1;
			else
				nn = 0;			
			for (int j = 0; j < skillCapes.length; j++) {
				if (skillCapes[j] == item || skillCapes[j]+1 == item) {
					if (c.getItems().freeSlots() > 1) {
						if (c.points >= 9) {
							if (c.getLevelForXP(c.playerXP[j]) >= 99) {
								c.points -= 9;
								c.refreshInfo();
								c.getItems().addItem(skillCapes[j] + nn,1);
								c.getItems().addItem(skillCapes[j] + 2,1);
							} else {
								c.sendMessage("You must have 99 in the skill of the cape you're trying to buy.");
							}
						} else {
							c.sendMessage("You need 9 points to buy this item.");
						}
					} else {
						c.sendMessage("You must have at least 1 inventory space to buy this item.");					
					}				
				}
					
			}
			c.getItems().resetItems(3823);			
		}
		
		


}

