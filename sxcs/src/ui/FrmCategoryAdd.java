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
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmCategoryAdd extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textName;
	private JTextField textDescribe;
	private JButton btconfirm = new JButton("确认");
	private JButton btesc = new JButton("退出");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCategoryAdd frame = new FrmCategoryAdd();
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
	public FrmCategoryAdd() {
		setTitle("添加分类");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("类别名称");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(43, 38, 100, 47);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("分类描述");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(43, 101, 100, 47);
		contentPane.add(label_1);
		
		textName = new JTextField();
		textName.setBounds(142, 49, 113, 24);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textDescribe = new JTextField();
		textDescribe.setBounds(142, 112, 113, 24);
		contentPane.add(textDescribe);
		textDescribe.setColumns(10);
		
		
		btconfirm.setBounds(43, 185, 113, 27);
		contentPane.add(btconfirm);
		
		
		btesc.setBounds(197, 185, 113, 27);
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
		if(e.getSource()==this.btconfirm){
			
			String categoryName =this.textName.getText();
			String categoryDescribe =this.textDescribe.getText();
			try {
				SXCSUtil.commodityManager.addCategory(categoryName, categoryDescribe);
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
