package application;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TurnAround extends JDialog {

	private static final long serialVersionUID = 1L;

	public TurnAround(JFrame parent, String title, String message) {
		super(parent, title);
		// set the position of the window
		Point p = new Point(10, 440);
		setLocation(p.x, p.y);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{120, 0};
		gridBagLayout.rowHeights = new int[]{24, 33, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		// Create a message
		JPanel messagePane = new JPanel();
		messagePane.add(new JLabel(message));
		// get content pane, which is usually the
		// Container of all the dialog's components.
		GridBagConstraints gbc_messagePane = new GridBagConstraints();
		gbc_messagePane.fill = GridBagConstraints.BOTH;
		gbc_messagePane.insets = new Insets(0, 0, 5, 0);
		gbc_messagePane.gridx = 0;
		gbc_messagePane.gridy = 0;
		getContentPane().add(messagePane, gbc_messagePane);

		// Create a button
		JPanel buttonPane = new JPanel();
		JButton button = new JButton("Close me");
		buttonPane.add(button);
		// set action listener on the button
		button.addActionListener(new MyActionListener());
		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.fill = GridBagConstraints.BOTH;
		gbc_buttonPane.gridx = 0;
		gbc_buttonPane.gridy = 1;
		getContentPane().add(buttonPane, gbc_buttonPane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}


	public JRootPane createRootPane() {
		JRootPane rootPane = new JRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
		Action action = new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				System.out.println("escaping..");
				setVisible(false);
				dispose();
			}
		};
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, "ESCAPE");
		rootPane.getActionMap().put("ESCAPE", action);
		return rootPane;
	}

	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose();
		}
	}

}