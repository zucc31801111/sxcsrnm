package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.UserInf;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class FrmUserChangePwd extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private JButton btconfirm;
	private JButton btesc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUserChangePwd frame = new FrmUserChangePwd();
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
	
	public FrmUserChangePwd() {
		setBackground(Color.WHITE);
		setTitle("修改密码");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("账  号");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(105, 23, 54, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("原密码");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(105, 63, 54, 23);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("新密码");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(105, 105, 54, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("确认密码");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		label_3.setBounds(105, 140, 72, 29);
		contentPane.add(label_3);
		
		btconfirm = new JButton("确认");
		btconfirm.setFont(new Font("宋体", Font.PLAIN, 15));
		btconfirm.setBounds(82, 217, 93, 23);
		contentPane.add(btconfirm);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(187, 66, 105, 21);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(187, 25, 105, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(184, 106, 108, 21);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(187, 146, 105, 21);
		contentPane.add(passwordField_2);
		
		btesc = new JButton("退出");
		btesc.setFont(new Font("宋体", Font.PLAIN, 15));
		btesc.setBounds(254, 215, 93, 23);
		contentPane.add(btesc);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		btconfirm.addActionListener(this);
		btesc.addActionListener(this);
		/*this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});*/
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btconfirm) {
			String userid=this.textField.getText();
			String  oldPwd =new String(this.passwordField.getPassword());
			String  newPwd1=new String(this.passwordField_1.getPassword());
			String  newPwd2=new String(this.passwordField_2.getPassword());
			try {
				SXCSUtil.userManager.changeUserPwd(userid, oldPwd, newPwd1, newPwd2);
					this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			}
		else if(e.getSource() == this.btesc) {
			this.setVisible(false);
		}
	
			
	
	}
}
