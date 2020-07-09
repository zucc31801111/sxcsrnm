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

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JSplitPane;
import javax.swing.JButton;

public class FrmUserMain extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btchangepwd;
	private JButton btorder;
	private JButton btaddress;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUserMain frame = new FrmUserMain();
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
	public FrmUserMain() {
		setTitle("用户界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("生鲜网超欢迎您");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		label.setBounds(0, -2, 196, 47);
		contentPane.add(label);
		
		btchangepwd = new JButton("修改密码");
		btchangepwd.setBounds(700, 13, 113, 27);
		contentPane.add(btchangepwd);
		
		btorder = new JButton("订单查询");
		btorder.setBounds(700, 50, 113, 27);
		contentPane.add(btorder);
		
		btaddress = new JButton("地址管理");
		btaddress.setBounds(700, 87, 113, 27);
		contentPane.add(btaddress);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		btchangepwd.addActionListener(this);
		btorder.addActionListener(this);
		btaddress.addActionListener(this);
		/*this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});*/
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btchangepwd) {
				FrmUserChangePwd changepwd=new FrmUserChangePwd();
				changepwd.setVisible(true);
		}
		else if(e.getSource() == this.btaddress)
		{
			
		}
		else if(e.getSource() == this.btorder) {
			
		}
	}
	
	
	
}
