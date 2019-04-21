package game;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import database.Database;
import networking.PongClient;
import window.ContactControl;
import window.ContactPanel;
import window.CreateAccountControl;
import window.CreateAccountPanel;
import window.InitialControl;
import window.InitialPanel;
import window.LoginControl;
import window.LoginPanel;

public class ClientGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PongClient chat;
	public static Database pro = new Database();

	// Constructor
	public ClientGUI() {

		this.setTitle("Client");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// card layout initialization
		CardLayout cardLayout = new CardLayout();
		JPanel container = new JPanel(cardLayout);

		this.chat = new PongClient();
		InitialControl initialControl = new InitialControl(container);
		LoginControl loginControl = new LoginControl(container, chat);
		CreateAccountControl accountControl = new CreateAccountControl(container, chat);
		ContactControl contactControl = new ContactControl(container);

		chat.setLoginControl(loginControl);
		chat.setCreateAccountControl(accountControl);

		// Create the four views.
		JPanel view1 = new InitialPanel(initialControl);
		JPanel view2 = new LoginPanel(loginControl);
		JPanel view3 = new CreateAccountPanel(accountControl);
		JPanel view4 = new ContactPanel(contactControl);

		// Add views to the card layout
		container.add(view1, "1");
		container.add(view2, "2");
		container.add(view3, "3");
		container.add(view4, "4");

		cardLayout.show(container, "1");

		// Add layout to JFrame.
		getContentPane().add(container, BorderLayout.CENTER);

		this.setSize(555, 331);
		this.setVisible(true);

	}

	public static void main(String[] args) {

		new ClientGUI();
	}
}
