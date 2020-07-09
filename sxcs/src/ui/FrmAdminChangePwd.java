package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.AdminInformation;
import model.UserInf;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class FrmAdminChangePwd extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
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
					FrmAdminChangePwd frame = new FrmAdminChangePwd();
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
	public FrmAdminChangePwd() {
		setTitle("修改密码");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("账号");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(96, 35, 76, 45);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("原密码");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(96, 101, 87, 45);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("新密码");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(96, 173, 87, 45);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("确认密码");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(96, 257, 87, 45);
		contentPane.add(label_3);
		
		textField = new JTextField();
		textField.setBounds(204, 43, 139, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btconfirm = new JButton("确认");
		btconfirm.setFont(new Font("宋体", Font.PLAIN, 20));
		btconfirm.setBounds(84, 337, 129, 45);
		contentPane.add(btconfirm);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(204, 101, 139, 36);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(204, 185, 139, 36);
		contentPane.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(204, 269, 139, 36);
		contentPane.add(passwordField_2);
		
		btesc = new JButton("退出");
		btesc.setFont(new Font("宋体", Font.PLAIN, 20));
		btesc.setBounds(292, 337, 129, 45);
		contentPane.add(btesc);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		btconfirm.addActionListener(this);
		btesc.addActionListener(this);
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btconfirm) {
			String adminid=this.textField.getText();
			String  oldPwd =new String(this.passwordField.getPassword());
			String  newPwd1=new String(this.passwordField_1.getPassword());
			String  newPwd2=new String(this.passwordField_2.getPassword());
			try {
				AdminInformation.currentLoginUser= SXCSUtil.adminManager.changeAdminPwd(adminid, oldPwd, newPwd1, newPwd2);
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
