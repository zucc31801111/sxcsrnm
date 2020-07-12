package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DeliveryAddressList;
import model.UserShopcar;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FrmSetTime extends JFrame implements ActionListener{
	public List<UserShopcar> shopcar=null;
	public DeliveryAddressList address=null;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btconf = new JButton("确实");
	private JButton btesc = new JButton("取消");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSetTime frame = new FrmSetTime();
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
	public FrmSetTime() {
		setTitle("时间设置");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("送达时间");
		lblNewLabel.setBounds(72, 38, 75, 42);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(161, 47, 104, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		btconf.setBounds(55, 110, 113, 27);
		contentPane.add(btconf);
		
		
		btesc.setBounds(214, 110, 113, 27);
		contentPane.add(btesc);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		btconf.addActionListener(this);
		btesc.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btconf){
			
				String time=this.textField.getText();	
				try {
					SXCSUtil.userManager.jiesuan(shopcar, time, address);
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
