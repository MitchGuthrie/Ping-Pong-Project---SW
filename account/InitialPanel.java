package account;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InitialPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2492622466026537207L;

	// Constructor
	public InitialPanel(InitialControl ic) {
		JLabel label = new JLabel("Account Information", JLabel.CENTER);

		// Create the Login button
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(ic);

		JPanel loginButtonBuffer = new JPanel();
		loginButtonBuffer.add(loginButton);

		// Create the Create Account button
		JButton createButton = new JButton("Create");
		createButton.addActionListener(ic);

		JPanel createButtonBuffer = new JPanel();
		createButtonBuffer.add(createButton);

		// Use grid
		JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
		grid.add(label);
		grid.add(loginButtonBuffer);
		grid.add(createButtonBuffer);
		this.add(grid);
	}
}
