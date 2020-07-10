package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import model.AdminInformation;
import model.UserInf;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class FrmRegister extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField= new JPasswordField(20);
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private  JButton button;
	private  JButton btSec;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegister frame = new FrmRegister();
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
	
	public FrmRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("账号");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(63, 23, 72, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("姓名");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(63, 77, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("密码");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(63, 208, 72, 18);
		contentPane.add(label_2);
		
		JLabel lblNewLabel = new JLabel("性别");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(63, 139, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel label_3 = new JLabel("手机号");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(63, 275, 72, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("邮箱");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(63, 326, 72, 18);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("城市");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(63, 385, 72, 18);
		contentPane.add(label_5);
		
		button = new JButton("确认");
		
		button.setBounds(82, 478, 113, 27);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(149, 22, 136, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(149, 71, 136, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(149, 138, 136, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		passwordField.setBounds(149, 207, 136, 24);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(149, 274, 136, 24);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(149, 325, 136, 24);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(154, 384, 131, 24);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		btSec = new JButton("退出");
		btSec.setBounds(248, 478, 113, 27);
		contentPane.add(btSec);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		button.addActionListener(this);
		btSec.addActionListener(this);

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.button) {
			UserInf user=new UserInf();
			user.setUser_id(this.textField.getText());
			user.setUser_name(this.textField_1.getText());
			user.setUser_sex(this.textField_2.getText());
			String pwd=new String(this.passwordField.getPassword());
			user.setUser_pwd(pwd);
			user.setUser_phone(this.textField_4.getText());
			user.setUser_mail(this.textField_5.getText());
			user.setUser_city(this.textField_6.getText());
			try {
				UserInf.currentLoginUser= SXCSUtil.userManager.createUser(user);
				FrmLogin login=new FrmLogin();
				login.setVisible(true);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			
			}
		else if(e.getSource() == this.btSec) {
			FrmLogin login=new FrmLogin();
			login.setVisible(true);
			this.setVisible(false);
		}
		 
			}
}
