package DeviceGraphics;

import java.util.ArrayList;

import Utils.Location;

import factory.data.PartType;

public class KitGraphics {
	
	ArrayList<PartGraphics> parts; // parts currently in the kit
	ArrayList<PartType> partTypes; // part types required to make kit
	Location kitLocation;
	
	Boolean isFull; //Says whether or not the kit is full

	// ***********
	public KitGraphics () {
	isFull = false;
	}
	
	
	/**
	 * set the part types required to build kit
	 * 
	 * @param kitDesign - parts required to build the kit
	 */
	public void setPartTypes(ArrayList<PartType> kitDesign) {
		partTypes = kitDesign;
	}
	
	
	public void addPart (PartGraphics newPart) {
		parts.add(newPart);
	}
	
	
	public void setLocation (Location newLocation) {
		kitLocation = newLocation;
	}
	
	
	public Location getLocation () {
		return kitLocation;
	}
	
	//If true, set isFull boolean to true
	public void setFull (Boolean full) {
		isFull = full;
	}
}
