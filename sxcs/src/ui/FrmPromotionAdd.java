package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import starter.SXCSUtil;
import util.BaseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FrmPromotionAdd extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textid;
	private JTextField textprice;
	private JTextField textsum;
	private JTextField textstarttime;
	private JTextField textendtime;
	private JButton btconf = new JButton("确实");
	private JButton btesc = new JButton("取消");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPromotionAdd frame = new FrmPromotionAdd();
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
	public FrmPromotionAdd() {
		setTitle("添加促销商品");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("商品编号");
		label.setBounds(69, 10, 66, 28);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("促销价格");
		lblNewLabel.setBounds(69, 48, 54, 30);
		contentPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("促销数量");
		label_1.setBounds(69, 88, 54, 40);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("开始时间");
		label_2.setBounds(69, 138, 66, 28);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("结束时间");
		label_3.setBounds(69, 176, 60, 39);
		contentPane.add(label_3);
		
		textid = new JTextField();
		textid.setBounds(145, 14, 108, 21);
		contentPane.add(textid);
		textid.setColumns(10);
		
		textprice = new JTextField();
		textprice.setBounds(145, 53, 108, 21);
		contentPane.add(textprice);
		textprice.setColumns(10);
		
		textsum = new JTextField();
		textsum.setBounds(145, 98, 108, 21);
		contentPane.add(textsum);
		textsum.setColumns(10);
		
		textstarttime = new JTextField();
		textstarttime.setBounds(145, 142, 108, 21);
		contentPane.add(textstarttime);
		textstarttime.setColumns(10);
		
		textendtime = new JTextField();
		textendtime.setBounds(145, 185, 108, 21);
		contentPane.add(textendtime);
		textendtime.setColumns(10);
		
		
		btconf.setBounds(54, 228, 93, 23);
		contentPane.add(btconf);
		
		
		btesc.setBounds(192, 228, 93, 23);
		contentPane.add(btesc);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		btconf.addActionListener(this);
		btesc.addActionListener(this);
	}


@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==this.btconf){
		
		 int commodityid=0;
		 int sum=0;
		 float price=0;
		 String starttime=null;
		 String endtime=null;
		try {
	     commodityid=Integer.parseInt(this.textid.getText());
		 price=(float) Double.parseDouble(this.textprice.getText());
		 sum=Integer.parseInt(this.textsum.getText());
		 starttime=this.textstarttime.getText();
		 endtime=this.textendtime.getText();
		}
		catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "数字不能为空", "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			SXCSUtil.commodityManager.addPromotion(commodityid, price, sum, starttime, endtime);
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