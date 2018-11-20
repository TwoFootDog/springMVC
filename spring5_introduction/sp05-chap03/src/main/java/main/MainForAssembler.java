package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import assembler.Assembler;
import spring.ChangePasswordService;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

public class MainForAssembler {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("���ɾ �Է��ϼ���:");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("�����մϴ�");
				break;
			}
			if(command.equalsIgnoreCase("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if(command.startsWith("chhange ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
//			printHelp();
			
		}
	}
	
	private static Assembler assembler = new Assembler();
	
	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			printHelp();
			return;
		}
		
		MemberRegisterService regSvc = assembler.getMemberRegisterService();
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if (!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("��ȣȭ Ȯ���� ��ġ���� �ʽ��ϴ�\n");
			return;
		}
		
		try {
			regSvc.regist(req);
			System.out.println("����߽��ϴ�\n");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�̹� �����ϴ� �����Դϴ�");
		}
	}
	
	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("��ȣ�� �����߽��ϴ�\n");
		} catch (MemberNotFoundException e) {
			// TODO: handle exception
			System.out.println("�������� �ʴ� �̸����Դϴ�\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("�̸��ϰ� ��ȣ�� ��ġ���� �ʽ��ϴ�\n");
		}
	}
	
	private static void printHelp() {
		System.out.println();
		System.out.println("�߸��� ���ɾ��Դϴ�. �Ʒ� ���ɾ� ������ Ȯ���ϼ���.");
		System.out.println("���ɾ� ���� : ");
		System.out.println("new �̸��� �̸� ��ȣ ��ȣȮ��");
		System.out.println("change �̸��� ������ ������");
		System.out.println();
	}

}