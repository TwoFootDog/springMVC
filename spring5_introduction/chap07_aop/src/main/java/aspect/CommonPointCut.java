package aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCut {

	@Pointcut("execution(public * chap07..*(..))")
	public void commonTarget() {

	}
}
