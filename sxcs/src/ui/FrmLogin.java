package ui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.FrmRegister;
import control.AdminManager;
import control.UserManager;
import model.AdminInformation;
import model.UserInf;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmLogin extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField=new JTextField(20);
	private JPasswordField passwordField= new JPasswordField(20);
	private JButton buttonEsc;
	private JButton buttonReg;
	private JButton buttonLog;
	private JRadioButton radioButtonAdmin;
	private JRadioButton radioButtonUser;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmLogin frame = new FrmLogin();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		//super(f,s,b);
		setTitle("登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("账号");
		label.setFont(new Font("宋体", Font.PLAIN, 25));
		label.setBounds(64, 37, 58, 37);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("密码");
		label_1.setFont(new Font("宋体", Font.PLAIN, 25));
		label_1.setBounds(64, 94, 58, 27);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(136, 44, 182, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		

		passwordField = new JPasswordField();
		passwordField.setBounds(136, 98, 182, 27);
		contentPane.add(passwordField);
		
		
		radioButtonUser = new JRadioButton("用户");
		radioButtonUser.setFont(new Font("宋体", Font.PLAIN, 15));
		radioButtonUser.setBounds(64, 145, 100, 27);
		contentPane.add(radioButtonUser);
		
		radioButtonAdmin = new JRadioButton("管理员");
		radioButtonAdmin.setFont(new Font("宋体", Font.PLAIN, 15));
		radioButtonAdmin.setBounds(176, 145, 116, 27);
		contentPane.add(radioButtonAdmin);
		
		buttonLog = new JButton("登录");
		buttonLog.setFont(new Font("宋体", Font.PLAIN, 18));
		buttonLog.setBounds(51, 198, 113, 27);
		contentPane.add(buttonLog);
		
		buttonReg = new JButton("注册");
		buttonReg.setFont(new Font("宋体", Font.PLAIN, 18));
		buttonReg.setBounds(176, 198, 113, 27);
		contentPane.add(buttonReg);
		
		 buttonEsc = new JButton("退出");
		buttonEsc.setFont(new Font("宋体", Font.PLAIN, 18));
		buttonEsc.setBounds(305, 198, 113, 27);
		contentPane.add(buttonEsc);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		buttonLog.addActionListener(this);
		buttonReg.addActionListener(this);
		buttonEsc.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.buttonLog) {
			if(this.radioButtonUser.isSelected()) {
			String userid=this.textField.getText();
			String pwd=new String(this.passwordField.getPassword());
			try {
				UserInf.currentLoginUser= SXCSUtil.userManager.login(userid, pwd);
					
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmUserMain user =new FrmUserMain();
			user.setVisible(true);
			this.setVisible(false);
			}
			else if(this.radioButtonAdmin.isSelected()){
				String userid=this.textField.getText();
				String pwd=new String(this.passwordField.getPassword());
				try {
					AdminInformation.currentLoginUser=SXCSUtil.adminManager.login(userid, pwd);
						
				}
			catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误提示",JOptionPane.ERROR_MESSAGE);
					return;
					}
				FrmAdminMain admin =new FrmAdminMain();
				admin.setVisible(true);
				this.setVisible(false);
				}
		  
			}
		
		 else if (e.getSource() == this.buttonEsc) {
			System.exit(0);
		}
	else if(e.getSource()== this.buttonReg){
		FrmRegister reg=new FrmRegister();
		reg.setVisible(true);
		this.setVisible(false);
	}
	}
}
