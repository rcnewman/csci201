package DeviceGraphicsDisplay;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JComponent;

import DeviceGraphics.DeviceGraphics;
import Networking.Client;
import Networking.Request;
import Utils.Constants;
import Utils.Location;
import factory.data.PartType;

/**
 * @author Vansh Jain
 * 
 */
public class NestGraphicsDisplay extends DeviceGraphicsDisplay {	
	// max number of parts this Nest holds
	private static final int MAX_PARTS=8;
	// width and height of the nest
	private static final int NEST_WIDTH=45; 
	private static final int NEST_HEIGHT=80;
	// width and height of a part
	private static final int PART_WIDTH=20, PART_HEIGHT=21;
	
	// the LaneManager (client) which talks to the Server
	private Client client;
	// the id of this nest
	private int nestID;
	
	private Location nestLocation;
	
	//boolean if the nest is full
	private boolean isFull;
	// dynamically stores the parts currently in the Nest
	private ArrayList<PartGraphicsDisplay> partsInNest = new ArrayList<PartGraphicsDisplay>();

	/**
	 * Default constructor
	 */
	public NestGraphicsDisplay(Client c, int id) {
		client = c;
		nestID = id;
		
		isFull = true;
		nestLocation = new Location(600, 100 + nestID * 75);
		
		// Begin V0 requirements
		for (int i = 0; i < 8; i++) {
			PartGraphicsDisplay temp = new PartGraphicsDisplay(PartType.A);
			if(i < 4) {
				temp.setLocation(new Location((nestLocation.getX()+i*20),(nestLocation.getY()+1)));
			} else {
				temp.setLocation(new Location((nestLocation.getX()+(i-4)*20),(nestLocation.getY()+23))); 
			}
			partsInNest.add(temp);
		}
	}
	
	public void receivePart(PartGraphicsDisplay p) {
		
	}
	
//	public void givePartToPartsRobot(PartGraphicsDisplay) {		
//	}
	
	public void purge() {
		
	}
	
	private void movePartsUp() {
		
	}

	/**
	 * Handles drawing of NestGraphicsDisplay objects
	 */
	public void draw(JComponent c, Graphics2D g) {		
		g.drawImage(Constants.NEST_IMAGE, nestLocation.getX(), nestLocation.getY(), c);
		for(PartGraphicsDisplay part : partsInNest) {
			part.draw(c,g);
		}
	}

	/**
	 * Processes requests targeted at the NestGraphicsDisplay
	 */
	public void receiveData(Request req) {
		if (req.getCommand().equals(Constants.NEST_RECEIVE_PART_COMMAND)) {
			// TODO code to handle command
			// send a request back to the server saying done
			client.sendData(new Request(Constants.NEST_RECEIVE_PART_COMMAND + Constants.DONE_SUFFIX, Constants.NEST_TARGET, null));
		} else if (req.getCommand().equals(Constants.NEST_GIVE_TO_PART_ROBOT_COMMAND + Constants.DONE_SUFFIX)) {
			// TODO code to handle command
			// send a request back to the server saying done
			client.sendData(new Request(Constants.NEST_GIVE_TO_PART_ROBOT_COMMAND + Constants.DONE_SUFFIX, Constants.NEST_TARGET, null));
		} else if (req.getCommand().equals(Constants.NEST_PURGING_DONE_COMMAND + Constants.DONE_SUFFIX)) {
			// TODO code to handle command
			// send a request back to the server saying done
			client.sendData(new Request(Constants.NEST_PURGING_DONE_COMMAND + Constants.DONE_SUFFIX, Constants.NEST_TARGET, null));
		}
		
	}

	/**
	 * Allows another object to set the NestGraphicsDisplay location
	 */
	public void setLocation(Location newLocation) {
		// TODO Auto-generated method stub
		
	}
}
