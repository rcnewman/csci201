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

import DeviceGraphicsDisplay.*;
import GUI.NetworkingButtonListener;
import GUI.OverlayPanel;
import Networking.Client;
import Networking.Request;
import Utils.Constants;
import Utils.Location;


public class PartsRobotManager extends Client implements ActionListener{
	// Temp values. Feel free to change
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	
	private Timer timer;
	
	public PartsRobotManager() {
		super();
		clientName = Constants.PARTS_ROBOT_MNGR_CLIENT;
		
		initStreams();
		initGUI();
		initDevices();
	}
	
	public void initGUI() {
		JLabel label = new JLabel("Parts Robot Manager");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.PLAIN, 40));
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label);
		
		OverlayPanel panel = new OverlayPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setVisible(true);
		
		JButton testButton = new JButton("Test button");
		// test feeder command
		testButton.addActionListener(new NetworkingButtonListener("Testing", Constants.FEEDER_TARGET, writer));
		panel.add(testButton);
		
		timer = new Timer(Constants.TIMER_DELAY, this);
		timer.start();
	}
	
	public void initDevices() {
		// example:
//		addDevice(Constants.LANE_TARGET, new LaneGraphicsDisplay(this, new Location(400, 100), 0));
//		addDevice(Constants.LANE_TARGET, new LaneGraphicsDisplay(this, new Location(400, 100), 1));
	}
	
	@Override
	public void receiveData(Request req) {
		devices.get(req.getTarget()).receiveData(req);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Client.setUpJFrame(frame, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		PartsRobotManager mngr = new PartsRobotManager();
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
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		
	}
}