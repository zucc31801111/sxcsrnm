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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JSplitPane;
import javax.swing.JButton;

public class FrmUserMain extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_Manager=new JMenu("个人信息管理");
    private JMenu menu_AddressManager=new JMenu("地址管理");
    private JMenu menu_CommodityOrderManager=new JMenu("商品订单管理");
    private JMenu menu_CommodityManager=new JMenu("商品列表");
    private JMenu menu_MenuManager=new JMenu("推荐菜谱");
    
    private JMenuItem  menuItem_ChangePwd=new JMenuItem("修改密码");
    private JMenuItem  menuItem_Address =new JMenuItem("地址信息表");
    private JMenuItem  menuItem_CommodityOrder=new JMenuItem("订单表");
    private JMenuItem  menuItem_Commodity=new JMenuItem("商品列表");
    private JMenuItem  menuItem_Menu=new JMenuItem("菜谱列表");

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

	public FrmUserMain() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("用户系统");
		
			menu_Manager.setFont(new Font("新宋体", Font.PLAIN, 15));
		    menuItem_ChangePwd.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_ChangePwd);
		    menuItem_ChangePwd.addActionListener(this);
		    menubar.add(menu_Manager);
		    	
		    menu_AddressManager.setFont(new Font("新宋体", Font.PLAIN, 15));
		    menuItem_Address.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_AddressManager.add(menuItem_Address);
		    menuItem_Address.addActionListener(this);
		    menubar.add(menu_AddressManager);
		    
		    menu_CommodityOrderManager.setFont(new Font("新宋体", Font.PLAIN, 15));
		    menuItem_CommodityOrder.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_CommodityOrderManager.add(menuItem_CommodityOrder);
		    menuItem_CommodityOrder.addActionListener(this);
		    menubar.add(menu_CommodityOrderManager);
		    
		    menu_CommodityManager.setFont(new Font("新宋体", Font.PLAIN, 15));
		    menuItem_Commodity.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_CommodityManager.add(menuItem_Commodity);
		    menuItem_Commodity.addActionListener(this);
		    menubar.add(menu_CommodityManager);
		    
		    menu_MenuManager.setFont(new Font("新宋体", Font.PLAIN, 15));
		    menuItem_Menu.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_MenuManager.add(menuItem_Menu);
		    menuItem_Menu.addActionListener(this);
		    menubar.add(menu_MenuManager);
		    
		    this.setJMenuBar(menubar);
		    
		/*this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});*/
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.menuItem_ChangePwd) {
				FrmUserChangePwd dlg=new FrmUserChangePwd();
				dlg.setVisible(true);
		}
		else if(e.getSource() == this.menuItem_Address)
		{
			FrmAddress dlg=new FrmAddress();
			dlg.setVisible(true);
		}
		else if(e.getSource() == this.menuItem_CommodityOrder) {
			
		}
       else if(e.getSource() == this.menuItem_Commodity) {
    	   FrmCommodityList dlg=new FrmCommodityList();
    	   dlg.setVisible(true);
		}
      else if(e.getSource() == this.menuItem_Menu) {
    	  FrmUserMenu dlg=new FrmUserMenu();
   	   dlg.setVisible(true);
}
	}
	
	
	
}
