package ui;
/*
 * 查询对话框
 * 
 */


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONException;

import vo.DoubanVo;

import doubanAPI.DoubanAPI;

public class DoubanUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComboBox jComboBox=null;
	JTextField jTextField=null;
	JButton jButton=null;
	public DoubanUI() {
		setLocation(300, 150);
		setSize(1000, 500);
		setVisible(true);
		setTitle("豆瓣书籍查询系统");
		init();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(new FlowLayout());
		jComboBox=new JComboBox();
		jComboBox.addItem("索书号");
		jComboBox.addItem("ISBN");
		jTextField=new JTextField(20);
		jButton=new JButton("搜索");
		add(jComboBox);
		add(jTextField);
		add(jButton);
		
		jButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String url;
		String result;
		if(jComboBox.getSelectedIndex()==0){
				int book_id;
				try {
					book_id=Integer.parseInt(jTextField.getText());
					url="https://api.douban.com/v2/book/"+book_id;
					DoubanAPI analysisData=new DoubanAPI();
					result=analysisData.getApiData(url);
					DoubanVo book=analysisData.GetBookInfo(result);
					JOptionPane.showMessageDialog(this, "书名："+book.getTitle()+"\n"+
														"作者："+book.getAuthor()+"\n"+
														"价格："+book.getPrice()+"\n"+
														"页数："+book.getPages()+"\n"+
														"出版社："+book.getPublisher()+"\n"+
														"ISBN："+book.getIsbn10()+"\n"+
														"图书介绍："+book.getSummary()+"\n"+
														"图片地址:"+book.getImage(), "查询结果", JOptionPane.DEFAULT_OPTION);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "没找到！请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
				} 
		}
		else{
			JOptionPane.showMessageDialog(this, "没找到！请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
		}
	}


}
