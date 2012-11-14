package DeviceGraphicsDisplay;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JComponent;

import Networking.Request;
import Utils.Constants;
import Utils.Location;
import factory.PartType;

public class KitGraphicsDisplay extends DeviceGraphicsDisplay {
	private static final int MAX_PARTS = 8;
	
	private Location kitLocation;

	// RotationPart
	public  int  degreeCountDown;
	private int degreeStep;
	
	
	private int rotationAxisX;
	private int rotationAxisY;
	
	private int position;
	
	private boolean rotating;
	
	private ArrayList<PartGraphicsDisplay> parts = new ArrayList<PartGraphicsDisplay>();
	
	private AffineTransform trans = new AffineTransform();

	public KitGraphicsDisplay() {
		
		kitLocation = Constants.KIT_LOC;
		position = 0;
		degreeCountDown=0;
		degreeStep = 1;
		rotationAxisX = 220;
		rotationAxisY = 40;
		trans.translate(0, 200);
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setLocation(Location newLocation) {
		kitLocation = newLocation;
	}

	public Location getLocation() {
		return kitLocation;
	}

	public void draw(JComponent c, Graphics2D g) {
		g.drawImage(Constants.KIT_IMAGE, kitLocation.getX(), kitLocation.getY(), c);
		
		for(PartGraphicsDisplay part : parts) {
			g.drawImage(Constants.PART_IMAGE, part.getLocation().getX(), part.getLocation().getY(), c);
		}

	}

	public void receiveData(Request req) {
		if(req.getCommand().equals(Constants.KIT_UPDATE_PARTS_LIST_COMMAND)) {
			PartType type = (PartType) req.getData();
			receivePart(new PartGraphicsDisplay(type));
		}
	}

	// Drawing using AffineTransform part

	public void receivePart(PartGraphicsDisplay pgd) {
		parts.add(pgd);
		
		// set location of the part
		if ((parts.size() % 2) == 1) {
			 pgd.setLocation(new Location(kitLocation.getX() + 5, kitLocation.getY() + (20 * (parts.size() -1) / 2)));
		
		} else {
			pgd.setLocation(new Location(kitLocation.getX() + 34, kitLocation.getY() + (20 * (parts.size()-2) / 2)));
		}
		
		if (parts.size() == MAX_PARTS) {
			parts.clear();
		}
		
	}
	
	
	public void setDegreeCountDown(int degreeCountDown){
		this.degreeCountDown=degreeCountDown;
		
		if(this.degreeCountDown<0)
		{
			this.degreeStep=-1;
		}
		else 
		{
			this.degreeStep=1;
		}
	}
	

	public void drawRotate(JComponent c, Graphics2D g) {
		rotate();
		checkDegrees();
		g.drawImage(Constants.KIT_IMAGE, trans, null);

	}

	public void rotate() {
		if(rotating){
			trans.rotate(Math.toRadians(degreeStep), rotationAxisX, rotationAxisY);
			degreeCountDown-=degreeStep;
			
		}
	}
	public void checkDegrees(){
		if(rotating)
		{
			if(degreeCountDown==0)
			{
				rotating=false;
			}
		}
	}
	
	
	public void startRotating(){
		rotating = true;
	}

}
