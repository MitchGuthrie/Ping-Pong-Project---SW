package account;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import game.Game;
import networking.GameClient;
import networking.GameServer;

public class ServerGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1881931124569959751L;
	private JLabel status;
	private JLabel statusColored;
	private String[] labels = { "Port #", "Timeout" };
	private JTextField[] textFields = new JTextField[labels.length];
	private JTextArea log;
	private ChatServer server;
	private boolean serverStarted = false;
	public static GameServer socketServer;
	public static GameClient socketClient;
	public static Game game;

	public ServerGUI(String title) {
		int i = 0;

		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel north = new JPanel(new FlowLayout());

		status = new JLabel("Status: ");
		statusColored = new JLabel("Not Connected");
		statusColored.setForeground(Color.red);

		// add status
		north.add(status);
		north.add(statusColored);

		JPanel south = new JPanel(new FlowLayout());

		JButton listen = new JButton("Listen");
		listen.addActionListener(new EventHandler());

		JButton close = new JButton("Close");
		close.addActionListener(new EventHandler());

		JButton stop = new JButton("Stop");
		stop.addActionListener(new EventHandler());

		JButton quit = new JButton("Quit");
		quit.addActionListener(new EventHandler());

		// add buttons
		south.add(listen);
		south.add(close);
		south.add(stop);
		south.add(quit);

		// JPanels
		JPanel center = new JPanel(new BorderLayout());
		JPanel centerTextFields = new JPanel(new GridLayout(labels.length + 1, 2, 5, 5));

		for (i = 0; i < labels.length; i++) {
			JPanel jp = new JPanel();
			JLabel jlabel = new JLabel(labels[i]);
			textFields[i] = new JTextField(15);

			if (labels[i].equals("Client ID")) {
				textFields[i].setEditable(false);
			}

			jp.add(jlabel);
			jp.add(textFields[i]);
			centerTextFields.add(jp);
		}

		center.add(centerTextFields, BorderLayout.NORTH);

		JPanel centerTextArea = new JPanel(new BorderLayout());
		JLabel logAreaLabel = new JLabel("Server Log Below", JLabel.CENTER);

		centerTextArea.add(logAreaLabel, BorderLayout.NORTH);

		log = new JTextArea(5, 20);
		log.setEditable(true);

		JScrollPane logPane = new JScrollPane(log);

		// add
		centerTextArea.add(logPane, BorderLayout.CENTER);
		center.add(centerTextArea, BorderLayout.CENTER);
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(center, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);

		this.setSize(500, 415);
		this.setVisible(true);

		server = new ChatServer();
		server.setLog(log);
		server.setStatus(statusColored);

	}

	public static void main(String[] args) {
		String name;
		if (args.length < 1) {
			name = "Server";
		} else {
			name = args[0];
		}

		new ServerGUI(name);
	}

	// nested Event Handler Class
	private class EventHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals("Quit")) {
				System.exit(0);
			} else if (event.getActionCommand().equals("Listen")) {
				if (textFields[0].getText().isEmpty() || textFields[1].getText().isEmpty()) {
					log.append("Port Number/timeout not entered before pressing Listen \n");
					serverStarted = false;
					try {
						server.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					statusColored.setText("Not Connected");
					statusColored.setForeground(Color.red);

				} else {
					try {
						server.setPort(Integer.parseInt(textFields[0].getText()));
						server.setTimeout(Integer.parseInt(textFields[1].getText()));
						server.listen();
					} catch (IOException e) {
						e.printStackTrace();
					}
					serverStarted = true;
				}
			} else if (event.getActionCommand().equals("Close") || event.getActionCommand().equals("Stop")) {
				if (!serverStarted) {
					log.append("Server not currently started \n");
				} else if (event.getActionCommand().equals("Stop")) {
					server.stopListening();
				} else if (event.getActionCommand().equals("Close")) {
					try {
						server.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
}
