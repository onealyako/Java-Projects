import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
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
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("User\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Opening study planner for user. Click to continue!");
				dispose();
                
                Student sonny = new Student("Sonny", "Malick", "Sonny S. Malick", 12345);
				StudentWindow main = new StudentWindow(sonny);
				main.setVisible(true);
			}
		});
		btnNewButton.setBounds(34, 78, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Admin\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Opening planner for admin. Click to continue!");
				dispose();
				AdminWindow main = new AdminWindow();
				main.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(188, 78, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblStudentPlannerLogin = new JLabel("Student Planner Login");
		lblStudentPlannerLogin.setBounds(97, 24, 193, 16);
		contentPane.add(lblStudentPlannerLogin);
	}

}
