package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.CommodityInformation;
import starter.SXCSUtil;
import util.BaseException;

public class FrmCommodityChange extends JFrame implements ActionListener {
	public  CommodityInformation commodity1=null;
	private JPanel contentPane;
	private JTextField textcommodity;
	private JTextField textprice;
	private JTextField textvip;
	private JTextField textspec;
	private  JButton btconfirm;
	private  JButton btesc;
	private JTextField textDesc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCommodityChange frame = new FrmCommodityChange();
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
	public FrmCommodityChange() {
			setTitle("商品修改");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 404, 439);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel label = new JLabel("商品名称");
			label.setFont(new Font("宋体", Font.PLAIN, 20));
			label.setBounds(30, 19, 86, 30);
			contentPane.add(label);
			
			JLabel label_1 = new JLabel("价格");
			label_1.setFont(new Font("宋体", Font.PLAIN, 20));
			label_1.setBounds(30, 87, 86, 39);
			contentPane.add(label_1);
			
			JLabel label_2 = new JLabel("会员价");
			label_2.setFont(new Font("宋体", Font.PLAIN, 20));
			label_2.setBounds(30, 158, 86, 39);
			contentPane.add(label_2);
			
			JLabel label_3 = new JLabel("规格");
			label_3.setFont(new Font("宋体", Font.PLAIN, 20));
			label_3.setBounds(30, 221, 86, 39);
			contentPane.add(label_3);
			
			JLabel label_4 = new JLabel("描述");
			label_4.setFont(new Font("宋体", Font.PLAIN, 20));
			label_4.setBounds(30, 283, 72, 18);
			contentPane.add(label_4);
			
			textcommodity = new JTextField();
			textcommodity.setBounds(130, 22, 107, 24);
			contentPane.add(textcommodity);
			textcommodity.setColumns(10);
			
			textprice = new JTextField();
			textprice.setBounds(130, 94, 107, 24);
			contentPane.add(textprice);
			textprice.setColumns(10);
			
			 textvip = new JTextField();
			 textvip.setBounds(130, 161, 107, 24);
			contentPane.add(textvip);
			 textvip.setColumns(10);
			
			 textspec = new JTextField();
			 textspec.setBounds(130, 228, 107, 24);
			contentPane.add(textspec);
			textspec.setColumns(10);
			
			textDesc = new JTextField();
			textDesc.setBounds(130, 282, 107, 24);
			contentPane.add(textDesc);
			textDesc.setColumns(10);
			
			btconfirm = new JButton("确认");
			btconfirm.setBounds(30, 326, 113, 27);
			contentPane.add(btconfirm);
			
			btesc = new JButton("退出");
			btesc.setBounds(194, 326, 113, 27);
			contentPane.add(btesc);
				
			double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
			this.setLocation((int) (width - this.getWidth()) / 2,
					(int) (height - this.getHeight()) / 2);
			
			btconfirm.addActionListener(this);
			btesc.addActionListener(this);
			
		
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btconfirm){
			
			double price=0;
			double vipprice=0;
			String commodity=null;
			String commodityspec=null;
			String commoditydesc=null;
			try {
		    commodity =this.textcommodity.getText();
		    price=Double.parseDouble(this.textprice.getText());
		    vipprice= Double.parseDouble(this.textvip.getText());
			commodityspec =this.textspec.getText();
			commoditydesc=this.textDesc.getText();
			}
			catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "价格不能为空", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
			
			SXCSUtil.commodityManager.changeCommodity(commodity, price, vipprice, commodityspec,commoditydesc,commodity1);
					this.setVisible(false);
			} catch (BaseException  e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource() == this.btesc) {
			
			this.setVisible(false);
		}

		
	}
}


