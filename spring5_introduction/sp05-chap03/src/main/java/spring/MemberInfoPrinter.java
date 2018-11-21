package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {
	@Autowired
	private MemberDao memDao;
	@Autowired
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if(member == null) {
			System.out.println("데이터 없음\n");
			return;
		}
		printer.print(member);
		System.out.println();
	}

//	public void setMemberDao(MemberDao memDao) {
//		this.memDao = memDao;
//	}
//
//	public void setPrinter(MemberPrinter printer) {
//		this.printer = printer;
//	}
}
