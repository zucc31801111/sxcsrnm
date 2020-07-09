package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.CommodityInformation;
import model.FreshCategory;
import model.UserInf;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrmPurchase extends JFrame implements ActionListener {
	public CommodityInformation commodity=null;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton button = new JButton("确认");
	private  JButton btSec = new JButton("退出");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPurchase frame = new FrmPurchase();
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
	public FrmPurchase() {
		setTitle("采购信息");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("采购数量");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(43, 48, 91, 44);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("采购状态");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(43, 105, 91, 36);
		contentPane.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(164, 57, 113, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(164, 105, 113, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		button.setBounds(38, 186, 113, 27);
		contentPane.add(button);
		
		
		btSec.setBounds(204, 186, 113, 27);
		contentPane.add(btSec);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		
		button.addActionListener(this);
		btSec.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e) {
   if(e.getSource()==this.button){
			
			int purchase=0;
			String purchaseState=null;
			try {
				purchaseState =this.textField_1.getText();
			    purchase =Integer.parseInt(this.textField.getText());
				
			}
			catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "数量不能为空", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
			
			SXCSUtil.commodityManager.purchaseCommodity(commodity, purchase, purchaseState);
					this.setVisible(false);
			} catch (BaseException  e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == this.btSec) {
			this.setVisible(false);
		}
		 
			}
	

}
