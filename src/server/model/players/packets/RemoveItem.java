package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;


/**
 * Remove Item
 **/
public class RemoveItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int interfaceId = c.getInStream().readUnsignedWordA();
		int removeSlot = c.getInStream().readUnsignedWordA();
		int removeId = c.getInStream().readUnsignedWordA();
		switch(interfaceId) {
			
			case 1688:
			c.getItems().removeItem(removeId, removeSlot);
			break;
			
			case 5064:
			c.getItems().bankItem(removeId, removeSlot, 1);
			break;
			
			case 5382:
			c.getItems().fromBank(removeId, removeSlot, 1);
			break;
			
			case 3900:
			c.getShops().buyFromShopPrice(removeId, removeSlot);
			break;
			
			case 3823:
			c.getShops().sellToShopPrice(removeId, removeSlot);
			break;
			
			case 3322:
			if(c.duelStatus <= 0) { 
                c.getTradeAndDuel().tradeItem(removeId, removeSlot, 1);
           	} else {
				c.getTradeAndDuel().stakeItem(removeId, removeSlot, 1);
			}
			break;
			
			case 3415:
			if(c.duelStatus <= 0) { 
				c.getTradeAndDuel().fromTrade(removeId, removeSlot, 1);
           	} 
			break;
			
			case 6669:
			c.getTradeAndDuel().fromDuel(removeId, removeSlot, 1);
			break;
			
			case 1119:
			case 1120:
			case 1121:
			case 1122:
			case 1123:
				c.getSmithing().readInput(c.playerLevel[c.playerSmithing], Integer.toString(removeId), c, 1);
			break;
		}
	}
			
}
