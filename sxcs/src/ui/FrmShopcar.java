package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Coupon;
import model.DeliveryAddressList;
import model.UserShopcar;
import starter.SXCSUtil;
import util.BaseException;

public class FrmShopcar extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JMenuBar shopcarbar=new JMenuBar(); 
    private JMenu shopcar_Manager=new JMenu("商品管理");
    private JMenu shopcar_Settle=new JMenu("商品结算");
    
    private JMenuItem  shopcarItem_Change =new JMenuItem("修改商品数量");
    private JMenuItem  shopcarItem_Delete =new JMenuItem("删除商品");
    
    private JMenuItem  shopcarItem_Settle =new JMenuItem("结算");
	/**
	 * Launch the application.
	 */
	private Object tblShopcarTitle[]=UserShopcar.tblShopcarTitle;
	private Object tblShopcarData[][];
	DefaultTableModel tabShopcarModel=new DefaultTableModel();
	private JTable dataTableShopcar=new JTable(tabShopcarModel);
	
	private Object tblAddressTitle[]=DeliveryAddressList.tblAddressTitle;
	private Object tblAddressData[][];
	DefaultTableModel tabAddressModel=new DefaultTableModel();
	private JTable dataTableAddress=new JTable(tabAddressModel);
	
    private Object tblCouponTitle[]=Coupon.tblCouponTitle;
	private Object tblCouponData[][];
	DefaultTableModel tabCouponModel=new DefaultTableModel();
	private JTable dataTableCoupon=new JTable(tabCouponModel);
	
	private UserShopcar curShopcar=null;
	List<UserShopcar> allShopcar=null;
	List<DeliveryAddressList> allAddress=null;
	List<Coupon> allCoupon=null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmShopcar frame = new FrmShopcar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
private void reloadShopcarTable(){
		
		try {
			allShopcar=SXCSUtil.userManager.loadShopcar();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopcarData =  new Object[allShopcar.size()][UserShopcar.tblShopcarTitle.length];
		for(int i=0;i<allShopcar.size();i++){
			for(int j=0;j<UserShopcar.tblShopcarTitle.length;j++)
				tblShopcarData[i][j]=allShopcar.get(i).getCell(j);
		}
		tabShopcarModel.setDataVector(tblShopcarData,tblShopcarTitle);
		this.dataTableShopcar.validate();
		this.dataTableShopcar.repaint();
	}
	

private void reloadAddressTable(){
	
	try {
		allAddress=SXCSUtil.userManager.loadAddress();
	} catch (BaseException e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
		return;
	}
	tblAddressData =  new Object[allAddress.size()][DeliveryAddressList.tblAddressTitle.length];
	for(int i=0;i<allAddress.size();i++){
		for(int j=0;j<DeliveryAddressList.tblAddressTitle.length;j++)
			tblAddressData[i][j]=allAddress.get(i).getCell(j);
	}
	tabAddressModel.setDataVector(tblAddressData,tblAddressTitle);
	this.dataTableAddress.validate();
	this.dataTableAddress.repaint();
}

private void reloadCouponTable(){
	try {
		allCoupon=SXCSUtil.adminManager.loadCoupon();
	} catch (BaseException e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
		return;
	}
	tblCouponData =  new Object[allCoupon.size()][Coupon.tblCouponTitle.length];
	for(int i=0;i<allCoupon.size();i++){
		for(int j=0;j<Coupon.tblCouponTitle.length;j++)
			tblCouponData[i][j]=allCoupon.get(i).getCell(j);
	}
	tabCouponModel.setDataVector(tblCouponData,tblCouponTitle);
	this.dataTableCoupon.validate();
	this.dataTableCoupon.repaint();
}

	/**
	 * Create the frame.
	 */
	public FrmShopcar() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("购物车");
		
			shopcar_Manager.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
			shopcarItem_Change.setFont(new Font("宋体", Font.PLAIN, 15));
			shopcar_Manager.add(this.shopcarItem_Change);
			shopcarItem_Change.addActionListener(this);
			shopcarItem_Delete.setFont(new Font("宋体", Font.PLAIN, 15));
			shopcar_Manager.add(this.shopcarItem_Delete);
			shopcarItem_Delete.addActionListener(this);
			shopcarbar.add(shopcar_Manager);
			
			
			shopcar_Settle.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
			shopcarItem_Settle.setFont(new Font("宋体", Font.PLAIN, 15));
			shopcar_Settle.add(this.shopcarItem_Settle);
			shopcarItem_Settle.addActionListener(this);
			shopcarbar.add(shopcar_Settle);
			this.setJMenuBar(shopcarbar);
			
		   FrmShopcar.this.reloadShopcarTable();
		   FrmShopcar.this.reloadAddressTable();
		   FrmShopcar.this.reloadCouponTable();
		   this.getContentPane().add(new JScrollPane(this.dataTableAddress), BorderLayout.CENTER);
		    this.getContentPane().add(new JScrollPane(this.dataTableShopcar), BorderLayout.WEST);
		    this.getContentPane().add(new JScrollPane(this.dataTableCoupon), BorderLayout.EAST);
		    this.dataTableShopcar.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmShopcar.this.dataTableShopcar.getSelectedRow();
					if(i<0) {
						return;
					}		
				}
		    	
		    });
		    
		    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.shopcarItem_Delete){
			int i=FrmShopcar.this.dataTableShopcar.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				SXCSUtil.userManager.deleteShopcar(this.allShopcar.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		else if(e.getSource()==this.shopcarItem_Change) {
			int i=FrmShopcar.this.dataTableShopcar.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmShopcarChange dlg=new FrmShopcarChange();
			dlg.shopcar=this.allShopcar.get(i);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.shopcarItem_Settle) {
			
		}
		
	}

}
