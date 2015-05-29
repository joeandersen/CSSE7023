package festival.gui;

import javax.swing.*;

import festival.Event;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * The view for the Festival Planner.
 */
@SuppressWarnings("serial")
public class PlannerView extends JFrame {

	// the model of the Festival Planner
	private PlannerModel model;
	private Font font= new Font("SanSerif",Font.BOLD,20); //sets the font
	private JButton[] eventInButtons; //the lineup
	private JButton[] eventOutButtons;// the plan
	private JTextField outDisplay;
	private JTextField inDisplay;
	private JPanel planPanel;
	private JPanel lineUpPanel;
	private Container c;
	

	// REMOVE THIS LINE AND DECLARE ANY ADDITIONAL VARIABLES YOU REQUIRE HERE

	/**
	 * Creates a new Festival Planner window.
	 */
	public PlannerView(PlannerModel model) {
		this.model = model;
		
		setTitle("Festival Planner");
		setBounds(400,200,1000,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c = getContentPane();
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
		addPlanOutput();
		addPlanInput();
	}

	

	private void addPlanOutput() {
		
		planPanel = new JPanel();
		
		outDisplay = new JTextField("Current Plan",9);
		outDisplay.setFont(font);
		outDisplay.setEditable(false);
		outDisplay.setBackground(Color.WHITE);
		
		
		Iterator<Event> eventsIterator = model.getPlan().iterator();	
		
		int i = 0;
		while (eventsIterator.hasNext()){
			i++;
			eventsIterator.next();
		}

		planPanel.setLayout(new GridLayout(i+1,1));
		planPanel.add(outDisplay,"North");
		if(i==0){
			eventOutButtons = new JButton [1];
			eventOutButtons[0] = new JButton("Current Plan Is Empty");
			eventOutButtons[0].setFont(font);
			eventOutButtons[0].setBackground(Color.WHITE);
			
			//planPanel.add(eventOutButtons[0]);
			planPanel.add(new JTextField("Current Plan Is Empty"));
		} else {
			
			eventOutButtons = new JButton [i];
			eventsIterator = model.getPlan().iterator();
			i=0;
			while (eventsIterator.hasNext()) {
				
				eventOutButtons[i] = new JButton(eventsIterator.next().toString());
				eventOutButtons[i].setFont(font);
				eventOutButtons[i].setPreferredSize(new Dimension(400,1) );
				eventOutButtons[i].setBackground(Color.WHITE);
				planPanel.add(eventOutButtons[i]);
				i++;
			}
		}
		c.add(planPanel,"West");
		
	}

	private void addPlanInput() {
		Iterator<Event> eventsIterator = model.getLineUp().iterator();	
		lineUpPanel = new JPanel();
		
		inDisplay = new JTextField("Events in Line Up",15);
		inDisplay.setFont(font);
		inDisplay.setEditable(false);
		inDisplay.setBackground(Color.WHITE);
		lineUpPanel.add(inDisplay,"North");
		
		int i = 0;
		while (eventsIterator.hasNext()){
			i++;
			eventsIterator.next();
		}

		lineUpPanel.setLayout(new GridLayout(i+1,1));
		eventInButtons = new JButton [i];
		eventsIterator = model.getLineUp().iterator();
		i=0;
		while (eventsIterator.hasNext()) {
			Event myEvent = eventsIterator.next();
			eventInButtons[i] = new JButton(myEvent.toString());
			eventInButtons[i].setFont(font);
			eventInButtons[i].setBackground(Color.WHITE);
			eventInButtons[i].putClientProperty("event",myEvent);
			lineUpPanel.add(eventInButtons[i]);
			i++;
		}
	
		c.add(lineUpPanel,"East");
		
	}
	
	
	public void addAdditionListener(ActionListener pl, int n) {
		eventInButtons[n].addActionListener(pl);
	}



	public void updatePlan() {
		planPanel.removeAll();

		
		addPlanOutput();

		planPanel.updateUI();
		planPanel.revalidate();
	}


	

}
