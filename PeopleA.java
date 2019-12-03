package LOGIN;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;


public class PeopleA extends JFrame{
	private JTextField NameInput, AgeInput, NumInput, AdressInput, MailInput;
	private JButton Save;
	public PeopleA() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ButtonListener listener=new ButtonListener();//이벤트 생성
		
		
		setTitle("주소록 추가");
		setSize(450,450);

		
		
		JPanel p=new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(205,247,189));
		
		JLabel Title=new JLabel("주소록 추가");
		Title.setBounds(170,50,150,20);
		Title.setFont(new Font("Monospaced",Font.BOLD, 20));//주소록 추가 라벨 생성
		p.add(Title);
		
		JLabel Name=new JLabel("이름");
		Name.setBounds(50,100,50,25);
		p.add(Name);//이름 라벨 생성
		
		NameInput=new JTextField(20);
		NameInput.setBounds(150,100,200,25);
		NameInput.addActionListener(listener);
		p.add(NameInput);//이름 입력 필드 생성 및 이벤트 처리
		
		JLabel Age=new JLabel("나이");
		Age.setBounds(50,140,50,25);
		p.add(Age);//나이 라벨 생성
		
		AgeInput=new JTextField(20);
		AgeInput.setBounds(150,140,200,25);
		AgeInput.addActionListener(listener);
		p.add(AgeInput);//나이 입력 필드 생성 및 이벤트 처리
		
		
		JLabel Num=new JLabel("전화번호(-제외)");
		Num.setBounds(50,180,100,25);
		p.add(Num);//전화번호 라벨 생성
		
		NumInput=new JTextField(20);
		NumInput.setBounds(150,180,200,25);
		p.add(NumInput);
		NumInput.addActionListener(listener);//전화번호 입력 필드 생성 및 이벤트 처리
		
		JLabel Adress=new JLabel("주소");
		Adress.setBounds(50,220,100,25);
		p.add(Adress);//주소 라벨 생성
		
		AdressInput=new JTextField(20);
		AdressInput.setBounds(150,220,200,25);
		p.add(AdressInput);
		AdressInput.addActionListener(listener);//주소 입력 필드 생성 및 이벤트 처리

		JLabel Mail =new JLabel("메일");
		Mail.setBounds(50,260,100,25);
		p.add(Mail);//메일 라벨 생성
		
		MailInput=new JTextField(20);
		MailInput.setBounds(150,260,200,25);
		p.add(MailInput);
		MailInput.addActionListener(listener);//메일 입력 필드 생성 및 이벤트 처리
		
			
		
		Save=new JButton("저장");
		Save.setBounds(180,300,90,40);
		p.add(Save);
		Save.addActionListener(listener);
		Save.setBackground(Color.WHITE);//저장버튼 생성 및 이벤트 처리
		
		
		
		add(p);
		
		setVisible(true);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==Save) {
				try {
					String s;
					String[] array;
					BufferedReader User=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\주소록.txt"));
					
					BufferedWriter NUser=new BufferedWriter(new FileWriter("C:\\Users\\Grace\\eclipse-workspace\\Diary\\주소록.txt",true));//기존 파일에 이어적음
					NUser.write(NameInput.getText()+"/");
					NUser.write(AgeInput.getText()+"/");
					NUser.write(NumInput.getText()+"/");
					NUser.write(AdressInput.getText()+"/");
					NUser.write(MailInput.getText()+"\r\n");
					NUser.write("\r\n");
					User.close();
					NUser.close();
					JOptionPane.showMessageDialog(null, "주소록에 추가되었습니다.");
					dispose();
					
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "주소록 등록에 실패하였습니다.");
				}
			}
		}
		
	}
}

