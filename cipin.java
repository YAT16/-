package classroom1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JFrame;

public class cipin extends JFrame {
	private static final long serialVersionUID = 1L;
	public static TreeMap<String,Integer> map;
	public static String[] str;
       public static void main(String[] args) {
		System.out.println("------英文词频统计------");
		System.out.println("1.指定单词词频统计功能");
		System.out.println("2.高频词统计");
		System.out.println("3.统计该文本所有单词数量及词频数，将结果输入到result.txt");
		System.out.println("请输入编号：");
		map = new TreeMap<String,Integer>();

	    Scanner	sc=new Scanner(System.in);
	    int s=sc.nextInt();
	  //读入文件Word.txt
	    String line = "Word.txt";
		File file = new File(line);
		InputStreamReader f = null;						
		try {				 
			f = new InputStreamReader(new FileInputStream(file), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(f);//读入缓冲流													
			//一次读入一行，读入不为空时 进行单词统计				 				  
		        try {
					while((line=reader.readLine())!=null){
						line = line.toLowerCase();//忽略大小写
						String[] str = line.split("[^a-zA-Z]");//去掉特殊字符
						
						for(int i = 0; i<str.length; i++){
							String word = str[i].trim();
							if(map.containsKey(word) &&  word.length() != 0){
								map.put(word, map.get(word)+1);
							}else{
								map.put(word, 1);
							}			        		
					    }
                    }
				} catch (IOException e) {
					e.printStackTrace();
				}
		        // 统计了所有的单词的个数
		        //迭代器
				// Iterator<String>   iterator=map.keySet().iterator();
				//while(iterator.hasNext()){
				//	System.out.println(iterator.next());
               //  }
		       // for(Entry<String,Integer> entry: map.entrySet()) {										    					    	
			   // 	System.out.println(entry.getKey()+"=="+entry.getValue());					    
				//    }
	   if(s==1) {
		 //1.指定单词词频统计功能
		System.out.println("请输入你要查找的单词,用逗号隔开");
		String s1=sc.next();
		//调用类的方法
		cipin2 c2=new cipin2();
		 str=c2.a(map, s1);//S1是查指定的单词，map是前面是已经统计好的
		cipin s3=new cipin(); 
		s3.setVisible(true);
	   }else if(s==2) {
		   //2.高频词统计功能
		   
	   }else if(s==3) {
		   //3.统计该文本所有单词数量及词频数，将结果输入到result.txt
		   //调用类的方法2
		   cipin2 c2=new cipin2();//声明一个类对象
		   c2.c(map);
	   }
	   
	}
       
       
       
       
       public cipin(){
			super();
			setTitle("柱形图");
			setBounds(3, 200, 450, 450);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		  public void paint(Graphics g){
			int Width = getWidth();
			int Height = getHeight();
			int leftMargin = 50;//柱形图左边界
			int topMargin = 50;//柱形图上边界
			Graphics2D g2 = (Graphics2D) g;
			int ruler = Height-topMargin;
			int rulerStep = ruler/20;//将当前的高度评分为20个单位
			g2.setColor(Color.WHITE);//绘制白色背景
			g2.fillRect(0, 0, Width, Height);//绘制矩形图
			g2.setColor(Color.LIGHT_GRAY);
			for(int i=0;i<rulerStep;i++){
				g2.drawString((400-20*i)+"个", 8, topMargin+rulerStep*i);//绘制Y轴上的数据
			}
			g2.setColor(Color.pink);
			int m=0;
			for(int i = 0;i<str.length;i++){
				int value = map.get(str[i]);
				int step = (m+1)*40;//设置每隔柱形图的水平间隔为40
				g2.fillRoundRect(leftMargin+step*2,Height-value, 40, value, 40, 10);//绘制每个柱状条
				g2.drawString(str[i], leftMargin+step*2, Height-value-5);	//标识每个柱状条		
				m++;
			}
		  }     

}
