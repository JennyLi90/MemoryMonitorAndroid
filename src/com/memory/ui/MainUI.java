package com.memory.ui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class MainUI extends JFrame{

private Boolean flag=false;

 MainUI(){
	    
		
	}
 public void ScrollPaneTest(){
     JPanel panel = new JPanel();
     panel.setLayout(new BorderLayout());
     JTextArea text = new JTextArea();
     text.setEditable(true);
     text.setAutoscrolls(true);
     Dimension d = new Dimension(20, 20);
     text.setMinimumSize(d);
     
     JScrollPane pane = new JScrollPane(text);
//Code fragment below doesn't work. 
//     pane.add(text);
     panel.add(pane,BorderLayout.CENTER);
     

     this.setLayout(new BorderLayout());
     this.add(panel,BorderLayout.CENTER);
     this.setVisible(true);
 }
	
	public static void main(String args[])
	{
		String adbPath="/Users/jenny/Downloads/adt/sdk/platform-tools/adb";
		//MainUI testui=new MainUI();
		//testui.ScrollPaneTest();
		JFrame frame=new JFrame("Memory Monitor");
		
		JButton stopBtn=new JButton("Stop");
		stopBtn.setBounds(30,50,80,30);
		stopBtn.setLocation(200, 200);
		
		frame.add(stopBtn);
		
		
	
		//add scroll 
		
		Container contentPane=frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		
		JPanel resultPanel=new JPanel();
		//resultPanel.setBounds(100,100,400,100);
		resultPanel.setLocation(100,100);;
		resultPanel.setLayout(new GridLayout(1,1));
		resultPanel.setBorder(BorderFactory.createTitledBorder( "Memory Result "));
		
		JTextArea resultText=new JTextArea(10,25);
		
		//resultText.setBounds();
		resultText.setColumns(35);
		resultText.setLineWrap(true);
		resultText.setRows(35);
		resultText.setWrapStyleWord(true);
		resultText.setEditable(false);
		resultText.setAutoscrolls(true);
		
		
		JScrollPane scroll=new JScrollPane(resultText);
		
		
		resultPanel.add(scroll);
		contentPane.add(resultPanel);
		
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		 
		//frame.add(scroll);
		//add(resultPanel);
		//frame.add(resultText);
		frame.pack();
		//frame.show();
		frame.setSize(600,600);
		frame.setLayout(null);
		frame.setVisible(true);
		String command=adbPath+" shell dumpsys meminfo| grep com.pixlr.express";
		
		Process process;
		while(true){
		try {
			
			process = Runtime.getRuntime().exec(command);
			InputStreamReader ir=new InputStreamReader(process.getInputStream());
			LineNumberReader input= new LineNumberReader(ir);
			String line;
			line=input.readLine();
			//resultText.append(System.get);
			resultText.append(line);
			//resultText.setCaretPosition(resultText.getDocument().getLength()-1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		resultText.append("\n");
		}
		
		/*startBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("");
				String command="/Users/jenny/Downloads/adt/sdk/platform-tools/adb shell dumpsys meminfo| grep com.pixlr.express";
				
					Process process;
					try {
						
						process = Runtime.getRuntime().exec(command);
						InputStreamReader ir=new InputStreamReader(process.getInputStream());
						LineNumberReader input= new LineNumberReader(ir);
						String line;
						line=input.readLine();
						//resultText.append(System.get);
						resultText.append(line);
						//resultText.setCaretPosition(resultText.getDocument().getLength()-1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				resultText.append("\n");
			}
			
		}
		);*/
		
	
		
	
	}

}
