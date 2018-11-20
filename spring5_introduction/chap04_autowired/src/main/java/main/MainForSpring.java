package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.VersionPrinter;
import spring.WrongIdPasswordException;

public class MainForSpring {

	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
//		ctx = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);
//		ctx = new AnnotationConfigApplicationContext(AppConfigImport.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("���ɾ �Է��ϼ���:");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("�����մϴ�");
				break;
			}
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if (command.equalsIgnoreCase("list")) {
				processListCommand();
				continue;
			} else if (command.startsWith("info ")) { 
				processInfoCommand(command.split(" "));
				continue;
			} else if (command.equals("version")) {
				processVersionCommand();
				continue;
			}
			printHelp();
		}

	}
	


	private static void processNewCommand(String[] arg) {
		if (arg.length != 5) {
			printHelp();
			return;
		}
		
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
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
		
		ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		
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
	
	private static void processListCommand() {
		// TODO Auto-generated method stub
		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}
	
	private static void processInfoCommand(String[] arg) { 
		if (arg.length != 2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}
	
	private static void processVersionCommand() {
		VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
		versionPrinter.print();
	}

}