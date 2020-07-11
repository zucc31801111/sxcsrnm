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

import model.CommodityInformation;
import model.FreshCategory;
import model.Menu;
import model.Recommended;
import starter.SXCSUtil;
import util.BaseException;

public class FrmMenu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_Manager=new JMenu("菜谱管理");
    private JMenuItem  menuItem_Flash=new JMenuItem("刷新");
    private JMenuItem  menuItem_MenuAdd=new JMenuItem("添加菜谱");
    private JMenuItem  menuItem_MenuDelete=new JMenuItem("删除菜谱");
    private JMenuItem  menuItem_MenuCommodityAdd=new JMenuItem("添加菜谱商品");
    private JMenuItem  menuItem_MenuCommodityDelete=new JMenuItem("删除菜谱商品");
	/**
	 * Launch the application.
	 */
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
					FrmMenu frame = new FrmMenu();
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
	
	/**
	 * Create the frame.
	 */
	public FrmMenu() {
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("菜谱管理系统");
			menu_Manager.setFont(new Font("新宋体", Font.PLAIN, 15));
			menuItem_MenuAdd.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_MenuAdd);
		    menuItem_MenuAdd.addActionListener(this);
		    menuItem_Flash.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_Flash);
		    menuItem_Flash.addActionListener(this);
		    menuItem_MenuDelete.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_MenuDelete);
		    menuItem_MenuDelete.addActionListener(this);
		    menuItem_MenuCommodityAdd.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_MenuCommodityAdd);
		    menuItem_MenuCommodityAdd.addActionListener(this);
		    menuItem_MenuCommodityDelete.setFont(new Font("宋体", Font.PLAIN, 15));
		    menu_Manager.add(menuItem_MenuCommodityDelete);
		    menuItem_MenuCommodityDelete.addActionListener(this);
		    menubar.add(menu_Manager);
		    this.setJMenuBar(menubar);
		    this.getContentPane().add(new JScrollPane(this.dataTableMenu), BorderLayout.WEST);
		    this.dataTableMenu.addMouseListener(new MouseAdapter (){

		    	
		    	
				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmMenu.this.dataTableMenu.getSelectedRow();
					if(i<0) {
						return;
					}
					FrmMenu.this.reloadRecommendedTabel(i);
				}
		    	
		    });
		    this.getContentPane().add(new JScrollPane(this.dataTableRecommended), BorderLayout.CENTER);
		    
		    this.reloadMenuTable();
		    
		    this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuItem_MenuAdd){
			FrmMenuAdd dlg=new FrmMenuAdd();
			dlg.setVisible(true);

		}
		else if(e.getSource()==this.menuItem_Flash) {
			this.reloadMenuTable();
			this.reloadRecommendedTabel(0);
		}
		else if(e.getSource()==this.menuItem_MenuDelete) {
			if(this.curMenu==null) {
				JOptionPane.showMessageDialog(null, "请选择菜谱", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				SXCSUtil.commodityManager.deleteMenu(this.curMenu);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
        else if(e.getSource()==this.menuItem_MenuCommodityAdd) {
        	if(this.curMenu==null) {
				JOptionPane.showMessageDialog(null, "请选择菜谱", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
        	FrmRecommendedCommodityAdd dlg =new FrmRecommendedCommodityAdd();
        	dlg.curMenu=curMenu;
        	dlg.setVisible(true);
        	
        	
		}
        else if(e.getSource()==this.menuItem_MenuCommodityDelete) {
        	int i=FrmMenu.this.dataTableRecommended.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择推荐商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				SXCSUtil.commodityManager.deleteRecommendedCommodity(this.allRecommended.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
	
}
		
	}

}
