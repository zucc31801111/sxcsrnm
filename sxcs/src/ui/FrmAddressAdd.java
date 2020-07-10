package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FrmAddressAdd extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btconfirm = new JButton("确认");
	private JButton btesc = new JButton("取消");
	private JTextField textprovince;
	private JTextField textcity;
	private JTextField textarea;
	private JTextField textaddress;
	private JTextField textname;
	private JTextField textphone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmAddressAdd frame = new FrmAddressAdd();
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
	public FrmAddressAdd() {
		setTitle("增添地址");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("省份");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(95, 30, 95, 32);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("市");
		label.setFont(new Font("Dialog", Font.PLAIN, 18));
		label.setBounds(95, 75, 95, 39);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("区/县");
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(95, 127, 95, 31);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("详细地址");
		label_2.setFont(new Font("宋体", Font.PLAIN, 18));
		label_2.setBounds(95, 171, 95, 31);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("收货人");
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		label_3.setBounds(95, 234, 95, 31);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("收获手机号");
		label_4.setFont(new Font("宋体", Font.PLAIN, 18));
		label_4.setBounds(95, 293, 95, 31);
		contentPane.add(label_4);
		
		textprovince = new JTextField();
		textprovince.setBounds(196, 34, 116, 24);
		contentPane.add(textprovince);
		textprovince.setColumns(10);
		
		textcity = new JTextField();
		textcity.setBounds(196, 82, 116, 24);
		contentPane.add(textcity);
		textcity.setColumns(10);
		
		textarea = new JTextField();
		textarea.setBounds(196, 130, 116, 24);
		contentPane.add(textarea);
		textarea.setColumns(10);
		
		textaddress = new JTextField();
		textaddress.setBounds(196, 174, 116, 24);
		contentPane.add(textaddress);
		textaddress.setColumns(10);
		
		textname = new JTextField();
		textname.setBounds(196, 237, 116, 24);
		contentPane.add(textname);
		textname.setColumns(10);
		
		textphone = new JTextField();
		textphone.setBounds(196, 296, 116, 24);
		contentPane.add(textphone);
		textphone.setColumns(10);
		
		
		btconfirm.setBounds(77, 368, 113, 27);
		contentPane.add(btconfirm);
		
		
		btesc.setBounds(258, 368, 113, 27);
		contentPane.add(btesc);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		btconfirm.addActionListener(this);
		btesc.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btconfirm){
			
			 String province=null;
			 String city=null;
			 String area=null;
			 String address=null;
			 String name=null;
			 String phone=null;
				 province=this.textprovince.getText();
				 city=this.textcity.getText();
				 area=this.textarea.getText();
				 address=this.textaddress.getText();
				 name=this.textname.getText();
				 phone=this.textphone.getText();
			 try {
					SXCSUtil.userManager.addAddress(province, city, area, address, name, phone);
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 
		}
	else if(e.getSource()==this.btesc) { 
		   this.setVisible(false);
		   return;

		}
}
}