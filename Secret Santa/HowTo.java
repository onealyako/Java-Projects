import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JButton;

public class HowTo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HowTo frame = new HowTo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HowTo() {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 229);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWhatIsThe = new JLabel("What is the Secret Santa Generator?");
		lblWhatIsThe.setForeground(Color.YELLOW);
		lblWhatIsThe.setBounds(172, 11, 232, 16);
		contentPane.add(lblWhatIsThe);
		
		JTextPane txtpnHi = new JTextPane();
		txtpnHi.setForeground(Color.RED);
		txtpnHi.setBackground(Color.GREEN);
		txtpnHi.setText("The Secret Santa Generator is very simple to use. You input your name and the people that are participating in your Secret Santa session.\n\nThe generator will tell you who your specific Secret Santa is. ");
		txtpnHi.setBounds(172, 39, 232, 128);
		contentPane.add(txtpnHi);
		
		JButton button = new JButton("<- Back");
		button.setBounds(0, 6, 117, 29);
		contentPane.add(button);
		
		JButton btnLetsGenerate = new JButton("Let's Generate!");
		btnLetsGenerate.setBounds(220, 172, 117, 29);
		contentPane.add(btnLetsGenerate);
	}
}
