package festival.gui;

import javax.swing.*;

import festival.Event;

import java.awt.*;
import java.util.Iterator;

/**
 * The view for the Festival Planner.
 */
@SuppressWarnings("serial")
public class PlannerView extends JFrame {

	// the model of the Festival Planner
	private PlannerModel model;
	private JTextArea display; // displays the current plan
	private Font font= new Font("SanSerif",Font.BOLD,20); //sets the font
	private JButton[] eventInButtons;
	private JButton[] eventOutButtons;
	private JTextField outDisplay;
	private JTextField inDisplay;

	// REMOVE THIS LINE AND DECLARE ANY ADDITIONAL VARIABLES YOU REQUIRE HERE

	/**
	 * Creates a new Festival Planner window.
	 */
	public PlannerView(PlannerModel model) {
		this.model = model;
		
		setTitle("Festival Planner");
		setBounds(400,200,1250,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		try {
			this.model.loadLineUp();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(c, "Error Loading Line Up: "+e.getMessage());
			System.exit(0);
		}
		try {
			this.model.loadTimeTable();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(c, "Error Loading Shuttle Timetable: "+e.getMessage());
			System.exit(0);
		}
		addPlanOutput(c);
		addPlanInput(c);
	}

	

	private void addPlanOutput(Container c) {
		
		JPanel p = new JPanel();
		
		outDisplay = new JTextField("Current Plan",15);
		outDisplay.setFont(font);
		outDisplay.setEditable(false);
		outDisplay.setBackground(Color.WHITE);
		
		
		Iterator<Event> eventsIterator = model.getPlan().iterator();	
		
		int i = 0;
		while (eventsIterator.hasNext()){
			i++;
			eventsIterator.next();
		}

		p.setLayout(new GridLayout(i+1,1));
		p.add(outDisplay,"North");
		if(i==0){
			eventOutButtons = new JButton [1];
			eventOutButtons[0] = new JButton("Current Plan Is Empty");
			eventOutButtons[0].setFont(font);
			eventOutButtons[0].setBackground(Color.WHITE);
			
			p.add(eventOutButtons[0]);
		} else {
			
			eventOutButtons = new JButton [i];
			eventsIterator = model.getLineUp().iterator();
			i=0;
			while (eventsIterator.hasNext()) {
				
				eventOutButtons[i] = new JButton(eventsIterator.next().toString());
				eventOutButtons[i].setFont(font);
				eventOutButtons[i].setBackground(Color.WHITE);
				p.add(eventOutButtons[i]);
				i++;
			}
		}
		c.add(p,"West");
		
	}

	private void addPlanInput(Container c) {
		Iterator<Event> eventsIterator = model.getLineUp().iterator();	
		JPanel p = new JPanel();
		
		inDisplay = new JTextField("Events in Line Up",15);
		inDisplay.setFont(font);
		inDisplay.setEditable(false);
		inDisplay.setBackground(Color.WHITE);
		p.add(inDisplay,"North");
		
		int i = 0;
		while (eventsIterator.hasNext()){
			i++;
			eventsIterator.next();
		}

		p.setLayout(new GridLayout(i+1,1));
		eventInButtons = new JButton [i];
		eventsIterator = model.getLineUp().iterator();
		i=0;
		while (eventsIterator.hasNext()) {
			
			eventInButtons[i] = new JButton(eventsIterator.next().toString());
			eventInButtons[i].setFont(font);
			eventInButtons[i].setBackground(Color.WHITE);
			p.add(eventInButtons[i]);
			i++;
		}
		
		c.add(p,"East");
		
	}

}
