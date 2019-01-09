import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtWelcomeToYour;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtWelcomeToYour = new JTextField();
		txtWelcomeToYour.setText("Welcome to Your Secret Santa Generator");
		txtWelcomeToYour.setBounds(91, 6, 265, 26);
		contentPane.add(txtWelcomeToYour);
		txtWelcomeToYour.setColumns(10);
		
		JButton btnHowDoesThis = new JButton("How does this work?");
		btnHowDoesThis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HowTo how = new HowTo();
				how.setVisible(true);
			}
		});
		btnHowDoesThis.setBounds(113, 73, 189, 29);
		contentPane.add(btnHowDoesThis);
		
		JLabel lblReadMore = new JLabel("Read More");
		lblReadMore.setBounds(177, 56, 106, 16);
		contentPane.add(lblReadMore);
	}
}
