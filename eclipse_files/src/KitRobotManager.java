import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import DeviceGraphicsDisplay.ConveyorGraphicsDisplay;
import DeviceGraphicsDisplay.DeviceGraphicsDisplay;
import DeviceGraphicsDisplay.KitRobotGraphicsDisplay;
import GUI.NetworkingButtonListener;
import GUI.OverlayPanel;
import Networking.Client;
import Networking.Request;
import Utils.Constants;
import Utils.Location;


public class KitRobotManager extends Client implements ActionListener{
	// Temp values. Feel free to change
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	
	private Timer timer;
	
	public KitRobotManager() {
		clientName = Constants.KIT_ROBOT_MNGR_CLIENT;
		
		initStreams();
		initGUI();
	
		timer = new Timer(Constants.TIMER_DELAY,this);
		timer.start();
		
		initDevices();
	}
	
	public void initGUI() {
		JLabel label = new JLabel("Kit Robot Manager");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.PLAIN, 40));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVisible(true);
		add(label);
		
		OverlayPanel panel = new OverlayPanel();
		panel.add(new JLabel("hello"));
		add(panel, BorderLayout.SOUTH);
		panel.setVisible(true);
		
		JButton newKit = new JButton("New Kit");
		newKit.addActionListener(new NetworkingButtonListener(Constants.CONVEYOR_MAKE_NEW_KIT_COMMAND, Constants.CONVEYOR_TARGET, writer));
		panel.add(newKit);  

		
		
	}
	
	public void initDevices() {
		addDevice(Constants.CONVEYOR_TARGET, new ConveyorGraphicsDisplay(this, new Location(0,0)));
		addDevice(Constants.KIT_ROBOT_TARGET, new KitRobotGraphicsDisplay(this,new Location(0,0)));
	}
	
	@Override
	public void receiveData(Request req) {
		devices.get(req.getTarget()).receiveData(req);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Client.setUpJFrame(frame, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		KitRobotManager mngr = new KitRobotManager();
		frame.add(mngr);
		mngr.setVisible(true);
		frame.validate();
	}
	
	@Override
	public void paintComponent(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		
		g.drawImage(Constants.CLIENT_BG_IMAGE, 0, 0, this);
		
		for(DeviceGraphicsDisplay device : devices.values()) {
			device.draw(this, g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		repaint();
	}
}
