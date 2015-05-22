package festival.gui;

import javax.swing.*;
import java.awt.*;

/**
 * The view for the Festival Planner.
 */
@SuppressWarnings("serial")
public class PlannerView extends JFrame {

	// the model of the Festival Planner
	private PlannerModel model;

	// REMOVE THIS LINE AND DECLARE ANY ADDITIONAL VARIABLES YOU REQUIRE HERE

	/**
	 * Creates a new Festival Planner window.
	 */
	public PlannerView(PlannerModel model) {
		this.model = model;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// REMOVE THIS LINE AND COMPLETE THIS METHOD
	}

	// REMOVE THIS LINE AND ADD YOUR OWN METHODS ETC HERE
}
