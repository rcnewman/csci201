package DeviceGraphics;

import Networking.Request;
import Utils.Location;
import agent.data.*;

public class BinGraphics implements DeviceGraphics  {
	
	private PartGraphics part; // Type of part found in bin
	//private int partNumber; // Number of parts in bin
	//private Location binLocation;
	private boolean isFull;
	private Bin bin;
	
	// Constructor
	/*public BinGraphics (PartGraphics parts, int partNum, Bin b) {
		part = parts;
		partNumber = partNum;
		isFull = true;
		bin = b;
	}*/
	
	public BinGraphics (Bin b) {
		isFull = true;
		bin = b;
	}
	
	/**
	 * Used in order to receive parts from a feeder's purge
	 * 
	 * @param parts - Part type
	 * @param partNum - Number of parts
	 */
	public void receiveParts(PartGraphics parts, int partNum) {
		part = parts;
		//partNumber = partNum;
	}
	
	
	public PartGraphics getPart() {
		return part;
	}
	
	
	/*public int getQuantity() {
		return partNumber;
	}*/
	
	/**
	 * Empties out the bin during purge
	 */
	public void setEmpty() {
		//partNumber = 0;
		part = null;
	}
	
	
	/*public void setLocation(Location newLocation) {
		binLocation = newLocation;
	}*/
	
	
	/*public Location getLocation() {
		return binLocation;
	}*/

	public void setFull(boolean f) {
		isFull = f;
	}
	
	public Bin getBin() {
		return bin;
	}

	@Override
	public void receiveData(Request req) {
		// TODO Auto-generated method stub
		
	}
}
