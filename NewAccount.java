package LOGIN;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


import java.awt.*;

public class NewAccount extends JFrame{
	private JButton Save;
	private JTextField NIDInput, NPASSWORDInput, NameInput, SchoolInput, MajorInput, NumInput, PhoneInput, MailInput;
	public NewAccount() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ButtonListener2 listener2=new ButtonListener2();//이벤트 생성
		
		
		setTitle("Creat Account");
		setSize(450,580);
		
		
		JPanel p=new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(205,247,189));
		
		JLabel Title=new JLabel("회원가입");
		Title.setBounds(200,50,150,20);
		Title.setFont(new Font("Monospaced",Font.BOLD, 20));//회원가입 라벨 생성
		p.add(Title);
		
		JLabel NID=new JLabel("아이디");
		NID.setBounds(60,100,50,25);
		p.add(NID);//ID라벨 생성
		
		NIDInput=new JTextField(20);
		NIDInput.setBounds(150,100,200,25);
		NIDInput.addActionListener(listener2);
		p.add(NIDInput);//ID입력 필드 생성 및 이벤트 처리
		
		JLabel NPASSWORD=new JLabel("패스워드");
		NPASSWORD.setBounds(60,140,50,25);
		p.add(NPASSWORD);//패스워드 라벨 생성
		
		NPASSWORDInput=new JTextField(20);
		NPASSWORDInput.setBounds(150,140,200,25);
		NPASSWORDInput.addActionListener(listener2);
		p.add(NPASSWORDInput);//패스워드 입력 필드 생성 및 이벤트 처리
		
		
		JLabel Name=new JLabel("이름");
		Name.setBounds(60,180,100,25);
		p.add(Name);//이름 라벨 생성
		
		NameInput=new JTextField(20);
		NameInput.setBounds(150,180,200,25);
		p.add(NameInput);
		NameInput.addActionListener(listener2);//이름 입력 필드 생성 및 이벤트 처리
		
		JLabel School=new JLabel("학교");
		School.setBounds(60,220,100,25);
		p.add(School);//학교 라벨 생성
		
		SchoolInput=new JTextField(20);
		SchoolInput.setBounds(150,220,200,25);
		p.add(SchoolInput);
		SchoolInput.addActionListener(listener2);//학교 입력 필드 생성 및 이벤트 처리

		JLabel Major=new JLabel("전공");
		Major.setBounds(60,260,100,25);
		p.add(Major);//전공 라벨 생성
		
		MajorInput=new JTextField(20);
		MajorInput.setBounds(150,260,200,25);
		p.add(MajorInput);
		MajorInput.addActionListener(listener2);//전공 입력 필드 생성 및 이벤트 처리
		
		JLabel Num=new JLabel("학번");
		Num.setBounds(60,300,100,25);
		p.add(Num);//학번 라벨 생성
		
		NumInput=new JTextField(20);
		NumInput.setBounds(150,300,200,25);
		p.add(NumInput);
		NumInput.addActionListener(listener2);//학번 입력 필드 생성 및 이벤트 처리
		
		JLabel Phone=new JLabel("전화번호");
		Phone.setBounds(60,340,100,25);
		p.add(Phone);//전화번호 라벨 생성
		
		PhoneInput=new JTextField(20);
		PhoneInput.setBounds(150,340,200,25);
		p.add(PhoneInput);
		PhoneInput.addActionListener(listener2);//전화번호 입력 필드 생성 및 이벤트 처리
		
		JLabel Mail=new JLabel("이메일");
		Mail.setBounds(60,380,100,25);
		p.add(Mail);//이메일 라벨 생성
		
		MailInput=new JTextField(20);
		MailInput.setBounds(150,380,200,25);
		p.add(MailInput);
		MailInput.addActionListener(listener2);	//이메일 입력 필드 생성 및 이벤트 처리
		
		Save=new JButton("저장");
		Save.setBounds(180,450,90,40);
		Save.setBackground(Color.WHITE);
		p.add(Save);
		Save.addActionListener(listener2);//저장 버튼 생성 및 이벤트 처리
		
		
		
		add(p);
		
		setVisible(true);
	}
	
	private class ButtonListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==Save) {
				try {
					int count=0;
					String s;
					String[] array;	
					String NID=NIDInput.getText();
					BufferedReader User=new BufferedReader(new FileReader("C:\\Users\\Grace\\eclipse-workspace\\Diary\\회원명단.txt"));//경로 설정 필요
					while((s=User.readLine())!=null) {
						array=s.split("/");
						if(NID.equals(array[0])) {
							JOptionPane.showMessageDialog(null, "중복된 아이디입니다.");//아이디 중복 여부 확인
							count++;
							break;
						}
					}
					if(count==0) {
					BufferedWriter NUser=new BufferedWriter(new FileWriter("C:\\Users\\Grace\\eclipse-workspace\\Diary\\회원명단.txt",true));//경로 설정 필요
					NUser.write(NIDInput.getText()+"/");
					NUser.write(NPASSWORDInput.getText()+"/");
					NUser.write(NameInput.getText()+"/");
					NUser.write(SchoolInput.getText()+"/");
					NUser.write(MajorInput.getText()+"/");
					NUser.write(NumInput.getText()+"/");
					NUser.write(PhoneInput.getText()+"/");
					NUser.write(MailInput.getText()+"\r\n");//입력한 정보 '회원명단.txt'에 넣음
					User.close();
					NUser.close();
					JOptionPane.showMessageDialog(null, "회원가입을 축하합니다.");
					dispose();}
					
					
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.");
				}
			}
		}
		
	}


}
