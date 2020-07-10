package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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

import model.CommodityInformation;
import model.FreshCategory;
import starter.SXCSUtil;
import util.BaseException;

public class FrmCommodityList extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
	private JMenu menu_Commodity=new JMenu("商品管理");
	private JMenu menu_Shopcar=new JMenu("购物车");
	
	private JMenuItem  menuItem_CommodityAdd=new JMenuItem("添加商品至购物车");
	private JMenuItem  menuItem_ShopcarLook=new JMenuItem("查看购物车");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCommodityList frame = new FrmCommodityList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private Object tblCategoryTitle[]=FreshCategory.tblCategoryTitle;
	private Object tblCategoryData[][];
	DefaultTableModel tabCategoryModel=new DefaultTableModel();
	private JTable dataTableCategory=new JTable(tabCategoryModel);
	
	
	private Object tblCommodityTitle[]=CommodityInformation.tblCommodityTitle;
	private Object tblCommodityData[][];
	DefaultTableModel tabCommodityModel=new DefaultTableModel();
	private JTable dataTableCommodity=new JTable(tabCommodityModel);
	
	private FreshCategory curCategory=null;
	List<FreshCategory> allCategory=null;
	List<CommodityInformation> categoryCommodity=null;

	private void reloadFreshCategoryTable(){
		try {
			allCategory=SXCSUtil.commodityManager.loadFreshCategory();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCategoryData =  new Object[allCategory.size()][FreshCategory.tblCategoryTitle.length];
		for(int i=0;i<allCategory.size();i++){
			for(int j=0;j<FreshCategory.tblCategoryTitle.length;j++)
				tblCategoryData[i][j]=allCategory.get(i).getCell(j);
		}
		tabCategoryModel.setDataVector(tblCategoryData,tblCategoryTitle);
		this.dataTableCategory.validate();
		this.dataTableCategory.repaint();
	}
	private void reloadCommodityInformationTabel(int planIdx){
		if(planIdx<0) return;
		curCategory=allCategory.get(planIdx);
		try {
			categoryCommodity=SXCSUtil.commodityManager.loadCommodity(curCategory);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCommodityData =new Object[categoryCommodity.size()][CommodityInformation.tblCommodityTitle.length];
		for(int i=0;i<categoryCommodity.size();i++){
			for(int j=0;j<CommodityInformation.tblCommodityTitle.length;j++)
				tblCommodityData[i][j]=categoryCommodity.get(i).getCell(j);
		}
		
		tabCommodityModel.setDataVector(tblCommodityData,tblCommodityTitle);
		this.dataTableCommodity.validate();
		this.dataTableCommodity.repaint();
	}
	/**
	 * Create the frame.
	 */
	public FrmCommodityList() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("商品列表");
			
			menu_Commodity.setFont(new Font("宋体", Font.PLAIN, 15));
			menuItem_CommodityAdd.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Commodity.add(this.menuItem_CommodityAdd);
		    menuItem_CommodityAdd.addActionListener(this);
		    menubar.add(menu_Commodity);
		    
		    menu_Shopcar.setFont(new Font("宋体", Font.PLAIN, 15));
		    menuItem_ShopcarLook.setFont(new Font("宋体", Font.PLAIN, 15));
			menu_Shopcar.add(this.menuItem_ShopcarLook);
			menuItem_ShopcarLook.addActionListener(this);
		    menubar.add(menu_Shopcar);
		    this.setJMenuBar(menubar);
		    
			this.getContentPane().add(new JScrollPane(this.dataTableCategory), BorderLayout.WEST);
		    this.dataTableCategory.addMouseListener(new MouseAdapter (){

		    	
		    	
				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmCommodityList.this.dataTableCategory.getSelectedRow();
					if(i<0) {
						return;
					}
					FrmCommodityList.this.reloadCommodityInformationTabel(i);
				}
		    	
		    });
		    this.getContentPane().add(new JScrollPane(this.dataTableCommodity), BorderLayout.CENTER);
		    
		    this.reloadFreshCategoryTable();
		    
		    this.setVisible(true);
			
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_CommodityAdd) {
			int i=FrmCommodityList.this.dataTableCommodity.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmShopCarAdd dlg=new FrmShopCarAdd();
			dlg.commodity=this.categoryCommodity.get(i);
			dlg.setVisible(true);
			
		}
		else if(e.getSource()==this.menuItem_ShopcarLook) {
			FrmShopcar dlg=new FrmShopcar();
			dlg.setVisible(true);
		}
	}

}
