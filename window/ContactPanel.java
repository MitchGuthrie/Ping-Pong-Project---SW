package window;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ContactPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5936159790902109534L;

	// Constructor
	public ContactPanel(ContactControl cc) {
		JLabel lblMainMenu = new JLabel("Main Menu", JLabel.CENTER);

		JTextArea contactList = new JTextArea(5, 30);
		contactList.setText("Welcome to Pong!\r\n");
		JPanel contactAreaBuffer = new JPanel();
		contactAreaBuffer.add(contactList);

		// Create the deleteContact button.
		JButton playButton = new JButton("Play Game");
		playButton.setBounds(10, 11, 234, 23);
		playButton.addActionListener(cc);

		JPanel deleteButtonBuffer = new JPanel();
		deleteButtonBuffer.setLayout(null);
		deleteButtonBuffer.add(playButton);

		// Arrange the components in a grid.
		JPanel grid = new JPanel(new GridLayout(4, 1, 5, 5));
		grid.add(lblMainMenu);
		grid.add(contactAreaBuffer);
		grid.add(deleteButtonBuffer);

		// Create the AddContact button.
		JButton logoutButton = new JButton("Log Out");
		logoutButton.setBounds(10, 45, 234, 23);
		logoutButton.addActionListener(cc);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		deleteButtonBuffer.add(logoutButton);
		this.add(grid);
	}

	public void deleteContact(String contactName) {

	}

	public void addContact(String contactName) {

	}
}
