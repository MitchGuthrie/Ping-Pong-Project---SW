package game;

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
		JLabel label = new JLabel("Contact", JLabel.CENTER);

		JTextArea contactList = new JTextArea(5, 30);
		contactList.setText("Person One\nPerson Two\n");
		JPanel contactAreaBuffer = new JPanel();
		contactAreaBuffer.add(contactList);

		// Create the deleteContact button.
		JButton deleteButton = new JButton("Delete Conact");
		deleteButton.addActionListener(cc);

		// Create the AddContact button.
		JButton addButton = new JButton("Add Contact");
		addButton.addActionListener(cc);

		// Create the AddContact button.
		JButton logoutButton = new JButton("Log Out");
		logoutButton.addActionListener(cc);

		JPanel deleteButtonBuffer = new JPanel();
		deleteButtonBuffer.add(deleteButton);
		deleteButtonBuffer.add(addButton);
		deleteButtonBuffer.add(logoutButton);

		// Arrange the components in a grid.
		JPanel grid = new JPanel(new GridLayout(4, 1, 5, 5));
		grid.add(label);
		grid.add(contactAreaBuffer);
		grid.add(deleteButtonBuffer);
		this.add(grid);
	}

	public void deleteContact(String contactName) {

	}

	public void addContact(String contactName) {

	}
}
