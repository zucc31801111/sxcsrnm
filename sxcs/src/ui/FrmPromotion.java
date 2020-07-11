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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.FreshCategory;
import model.Promotion;
import starter.SXCSUtil;
import util.BaseException;
import javax.swing.JButton;

public class FrmPromotion extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JMenuBar promotionbar=new JMenuBar(); 
    private JMenu promotion_Manager=new JMenu("促销管理");
    private JMenuItem  promotionItem_Flash=new JMenuItem("刷新");
    private JMenuItem  promotionItem_Add =new JMenuItem("增加促销商品");
    private JMenuItem  promotionItem_Delete =new JMenuItem("删除促销商品");
    

	/**
	 * Launch the application.
	 */
    private Object tblPromotionTitle[]=Promotion.tblPromotionTitle;
	private Object tblPromotionData[][];
	DefaultTableModel tabPromotionModel=new DefaultTableModel();
	private JTable dataTablePromotion=new JTable(tabPromotionModel);
	
	private Promotion curPromotion=null;
	List<Promotion> allPromotion=null;
	/**
	 * Create the frame.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPromotion frame = new FrmPromotion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void reloadPromotionTable(){
		
		try {
			allPromotion=SXCSUtil.commodityManager.loadPromotion();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPromotionData =  new Object[allPromotion.size()][Promotion.tblPromotionTitle.length];
		for(int i=0;i<allPromotion.size();i++){
			for(int j=0;j<Promotion.tblPromotionTitle.length;j++)
				tblPromotionData[i][j]=allPromotion.get(i).getCell(j);
		}
		tabPromotionModel.setDataVector(tblPromotionData,tblPromotionTitle);
		this.dataTablePromotion.validate();
		this.dataTablePromotion.repaint();
	}
	
	
	public FrmPromotion() {
		
		setFont(new Font("Dialog", Font.PLAIN, 30));
		setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("限时促销系统");
 
			promotion_Manager.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
			
			promotionItem_Flash.setFont(new Font("宋体", Font.PLAIN, 15));
		    promotion_Manager.add(this.promotionItem_Flash);
		    promotionItem_Flash.addActionListener(this);
			promotionItem_Add.setFont(new Font("宋体", Font.PLAIN, 15));
		    promotion_Manager.add(this.promotionItem_Add);
		    promotionItem_Add.addActionListener(this);
		    promotionItem_Delete.setFont(new Font("宋体", Font.PLAIN, 15));
		    promotion_Manager.add(this.promotionItem_Delete);
		    promotionItem_Delete.addActionListener(this);
		    promotionbar.add(promotion_Manager);	
		    
		    
		    this.setJMenuBar(promotionbar);
		    
		    FrmPromotion.this.reloadPromotionTable();
		    this.getContentPane().add(new JScrollPane(this.dataTablePromotion), BorderLayout.CENTER);
		    this.dataTablePromotion.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmPromotion.this.dataTablePromotion.getSelectedRow();
					if(i<0) {
						return;
					}		
				}
		    	
		    });
		    
		    this.setVisible(true);
			
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.promotionItem_Add){
			FrmPromotionAdd dlg=new FrmPromotionAdd();
			dlg.setVisible(true);

		}
		else if(e.getSource()==this.promotionItem_Delete) {
			int i=FrmPromotion.this.dataTablePromotion.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				SXCSUtil.commodityManager.deletePromotion(this.allPromotion.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
       else if(e.getSource()==this.promotionItem_Flash) { 
    	   this.reloadPromotionTable();
	
		}
		
}
}
