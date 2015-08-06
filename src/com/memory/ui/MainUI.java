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
private static Boolean flag=true;



public static Boolean getFlag() {
	return flag;
}
public static void setFlag(Boolean flag) {
	MainUI.flag = flag;
}
MainUI(){
}

public void getMemory()
{
	}
 public void runCommand(JButton stopBtn,JTextArea resultText,String command){
 
 
 Process process;
	while(flag){
	try {
		System.out.println(""+flag);
		process = Runtime.getRuntime().exec(command);
		InputStreamReader ir=new InputStreamReader(process.getInputStream());
		LineNumberReader input= new LineNumberReader(ir);
		String line;
		line=input.readLine();
		resultText.append(line);
		resultText.append("\n");
		
		if(stopBtn.getText().equals("Stop")){
		stopBtn.addActionListener(new ActionListener()
		{
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e)
			{
				
				System.out.println("click");
				
			
				setFlag(false);
				stopBtn.setVisible(false);
				stopBtn.setEnabled(false);
				
			}
			
		}
		);
		}
		
	
	
		
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	
	}
 }

	public static void main(String args[])
	{
		MainUI ui=new MainUI();
		String adbPath="/Users/jenny/Downloads/adt/sdk/platform-tools/adb";
		//MainUI testui=new MainUI();
		//testui.ScrollPaneTest();
		JFrame frame=new JFrame("Memory Monitor");
		
		JButton stopBtn=new JButton("Stop");
		stopBtn.setBounds(30,50,80,30);
		stopBtn.setLocation(200, 200);
		
		
		frame.add(stopBtn);
		
		flag=true;
		//add scroll 
		
		Container contentPane=frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(stopBtn,BorderLayout.PAGE_START);
		
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
		frame.setSize(600,800);
		frame.setLayout(null);
		frame.setVisible(true);
		String command=adbPath+" shell dumpsys meminfo| grep com.pixlr.express";
		ui.runCommand(stopBtn, resultText, command);
	
		
	
		
	
	}

}
