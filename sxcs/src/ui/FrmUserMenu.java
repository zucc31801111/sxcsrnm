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

import model.Menu;
import model.Recommended;
import starter.SXCSUtil;
import util.BaseException;

public class FrmUserMenu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	 
	private JMenuBar menubar=new JMenuBar(); ;
	private JMenu menu_Commodity=new JMenu("商品管理");
	private JMenu menu_Shopcar=new JMenu("购物车");
	
	private JMenuItem  menuItem_CommodityAdd=new JMenuItem("添加商品至购物车");
	private JMenuItem  menuItem_ShopcarLook=new JMenuItem("查看购物车");
		
		 private Object tblMenuTitle[]=Menu.tblMenuTitle;
			private Object tblMenuData[][];
			DefaultTableModel tabMenuModel=new DefaultTableModel();
			private JTable dataTableMenu=new JTable(tabMenuModel);
			
			
			private Object tblRecommendedTitle[]=Recommended.tblRecommendedTitle;
			private Object tblRecommendedData[][];
			DefaultTableModel tabRecommendedModel=new DefaultTableModel();
			private JTable dataTableRecommended=new JTable(tabRecommendedModel);
			
			private Menu curMenu=null;
			List<Menu> allMenu=null;
			List<Recommended> allRecommended=null;
	
			
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUserMenu frame = new FrmUserMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void reloadMenuTable(){
		try {
			allMenu=SXCSUtil.commodityManager.loadMenu();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblMenuData =  new Object[allMenu.size()][Menu.tblMenuTitle.length];
		for(int i=0;i<allMenu.size();i++){
			for(int j=0;j<Menu.tblMenuTitle.length;j++)
				tblMenuData[i][j]=allMenu.get(i).getCell(j);
		}
		tabMenuModel.setDataVector(tblMenuData,tblMenuTitle);
		this.dataTableMenu.validate();
		this.dataTableMenu.repaint();
	}
	
	private void reloadRecommendedTabel(int planIdx){
		if(planIdx<0) return;
		curMenu=allMenu.get(planIdx);
		try {
			allRecommended=SXCSUtil.commodityManager.loadRecommendedCommodity(curMenu);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblRecommendedData =new Object[allRecommended.size()][Recommended.tblRecommendedTitle.length];
		for(int i=0;i<allRecommended.size();i++){
			for(int j=0;j<Recommended.tblRecommendedTitle.length;j++)
				tblRecommendedData[i][j]=allRecommended.get(i).getCell(j);
		}
		
		tabRecommendedModel.setDataVector(tblRecommendedData,tblRecommendedTitle);
		this.dataTableRecommended.validate();
		this.dataTableRecommended.repaint();
	}
	
	
	
	
	public FrmUserMenu() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("推荐菜谱列表");
			

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
		    
			this.getContentPane().add(new JScrollPane(this.dataTableMenu), BorderLayout.WEST);
		    this.dataTableMenu.addMouseListener(new MouseAdapter (){

		    	
		    	
				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmUserMenu.this.dataTableMenu.getSelectedRow();
					if(i<0) {
						return;
					}
					FrmUserMenu.this.reloadRecommendedTabel(i);
				}
		    	
		    });
		    this.getContentPane().add(new JScrollPane(this.dataTableRecommended), BorderLayout.CENTER);
		    
		    this.reloadMenuTable();
		    
		    this.setVisible(true);	
			
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_CommodityAdd) {
			int i=FrmUserMenu.this.dataTableRecommended.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmShopCarAddtwo dlg=new FrmShopCarAddtwo();
			dlg.recommended=this.allRecommended.get(i);
			dlg.setVisible(true);
			
		}
		else if(e.getSource()==this.menuItem_ShopcarLook) {
			FrmShopcar dlg=new FrmShopcar();
			dlg.setVisible(true);
		}
		
	}

}
