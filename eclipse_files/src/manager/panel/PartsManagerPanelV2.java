package manager.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import manager.util.OverlayPanel;
import manager.util.WhiteLabel;
import Utils.Constants;
import factory.PartType;

public class PartsManagerPanelV2 extends JPanel{
	public static final Border PADDING = BorderFactory.createEmptyBorder(20, 20, 20, 20);
	public static final Border FIELD_PADDING = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	public static final Border MEDIUM_PADDING = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	public static final Border BOTTOM_PADDING = BorderFactory.createEmptyBorder(0, 0, 20, 0);
	public static final Border TOP_PADDING = BorderFactory.createEmptyBorder(20, 0, 5, 0);
	public static final Border VERTICAL_PADDING = BorderFactory.createEmptyBorder(10, 0, 10, 0);
	
	JPanel panels;
	OverlayPanel leftPanel;
	PartsListPanel rightPanel;
	
	JLabel leftTitle;
	
	ArrayList<PartType> partTypes = new ArrayList<PartType>();
	
	public PartsManagerPanelV2() {
		setLayout(new BorderLayout());
		setBorder(PADDING);
		
		partTypes = (ArrayList<PartType>) Constants.DEFAULT_PARTTYPES.clone();
		
		JLabel title = new WhiteLabel("Parts Manager");
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setBorder(VERTICAL_PADDING);
		add(title, BorderLayout.NORTH);
		
		panels = new JPanel(new GridLayout(1,2));
		panels.setOpaque(false);
		panels.setVisible(true);
		add(panels);
		
		leftPanel = new OverlayPanel();
		leftPanel.setVisible(true);
		panels.add(leftPanel);
		
		rightPanel = new PartsListPanel();
		rightPanel.setVisible(true);
		rightPanel.setBackground(new Color(0, 0, 0, 30));
		
		// TODO: make scrolling work!
		// rightPanel.setPreferredSize(new Dimension(500,500));
		//JScrollPane jsp = new JScrollPane(rightPanel);
		
		panels.add(rightPanel);

		
		setUpLeftPanel();
	}
	
	public void setUpLeftPanel() {
		setUpLeftPanel(null);
	}
	
	public void setUpLeftPanel(PartType pt) {
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		leftPanel.setAlignmentX(LEFT_ALIGNMENT);
		leftPanel.setBorder(PADDING);
		
		leftTitle = new WhiteLabel("Create a New Part");
		leftTitle.setFont(new Font("Arial", Font.PLAIN, 20));
		leftTitle.setAlignmentX(0);
		leftPanel.add(leftTitle);
		
		JPanel namePanel = new JPanel();
		namePanel.setBorder(TOP_PADDING); 
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
		namePanel.setOpaque(false);
		namePanel.setVisible(true);
		namePanel.setAlignmentX(0);
		leftPanel.add(namePanel);
		
			WhiteLabel nameLabel = new WhiteLabel("Name");
			nameLabel.setLabelSize(100, 25);
			namePanel.add(nameLabel);
			
			JTextField nameField = new JTextField("name");
			nameField.setMaximumSize(new Dimension(200, 25));
			nameField.setBorder(FIELD_PADDING);
			namePanel.add(nameField);
		
		JPanel numPanel = new JPanel();
		numPanel.setBorder(TOP_PADDING); 
		numPanel.setLayout(new BoxLayout(numPanel, BoxLayout.LINE_AXIS));
		numPanel.setOpaque(false);
		numPanel.setVisible(true);
		numPanel.setAlignmentX(0);
		leftPanel.add(numPanel);
		
			WhiteLabel numLabel = new WhiteLabel("Part no.");
			numLabel.setLabelSize(100, 25);
			numPanel.add(numLabel);
			
			JTextField numField = new JTextField("23");
			numField.setMaximumSize(new Dimension(200, 25));
			numField.setBorder(FIELD_PADDING);
			numPanel.add(numField);
		
		JPanel descPanel = new JPanel();
		descPanel.setBorder(TOP_PADDING); 
		descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.LINE_AXIS));
		descPanel.setOpaque(false);
		descPanel.setVisible(true);
		descPanel.setAlignmentX(0);
		leftPanel.add(descPanel);
		
			WhiteLabel descLabel = new WhiteLabel("Description");
			descLabel.setLabelSize(100, 25);
			descPanel.add(descLabel);
			
			JTextArea descField = new JTextArea("Description...");
			descField.setMinimumSize(new Dimension(200, 100));
			descField.setMaximumSize(new Dimension(200, 100));
			descField.setPreferredSize(new Dimension(200, 100));
			descField.setBorder(FIELD_PADDING);
			descPanel.add(descField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(TOP_PADDING); 
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setOpaque(false);
		buttonPanel.setVisible(true);
		buttonPanel.setAlignmentX(0);
		leftPanel.add(buttonPanel);
		
			WhiteLabel fakeLabel = new WhiteLabel("");
			fakeLabel.setLabelSize(100, 25);
			buttonPanel.add(fakeLabel);
			
			JButton submitButton = new JButton("Submit >");
			submitButton.setMinimumSize(new Dimension (200, 25));
			submitButton.setMaximumSize(new Dimension (200, 25));
			submitButton.setPreferredSize(new Dimension (200, 25));
			submitButton.setAlignmentX(0);
			buttonPanel.add(submitButton);
	}
	
	@Override
	public void paintComponent(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		g.drawImage(Constants.CLIENT_BG_IMAGE, 0, 0, this);
	}
}
