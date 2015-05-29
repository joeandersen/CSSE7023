package festival.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;

import festival.Event;

/**
 * The controller for the Festival Planner.
 */
public class PlannerController {

	// the model that is being controlled
	private PlannerModel model;
	// the view that is being controlled
	private PlannerView view;

	// REMOVE THIS LINE AND DECLARE ANY ADDITIONAL VARIABLES YOU REQUIRE HERE

	/**
	 * Initialises the Controller for the Festival Planner.
	 */
	public PlannerController(PlannerModel model, PlannerView view) {
		this.model = model;
		this.view = view;
		int i=0;
		for (@SuppressWarnings("unused") Event event: model.getLineUp()){
			view.addAdditionListener(new AdditionActionListener(), i);
			i++;
		}
	}

	private class AdditionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			Event myEvent  = (Event)((JButton)e.getSource()).getClientProperty("event");
			int result = model.addEventToPlan(myEvent);
			System.out.println(""+result);
			if(result==0){
				view.updatePlan();
			}
		}
	}
}
