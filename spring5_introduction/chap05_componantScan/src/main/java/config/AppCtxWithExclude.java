package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import spring.ManualBean;
import spring.MemberDao;
import spring.MemberPrinter;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;

@Configuration
/*
 * @ComponentScan(basePackages = { "spring" }, excludeFilters = @Filter(type =
 * FilterType.REGEX, pattern = "spring\\..*Dao"))
 */
@ComponentScan(basePackages = { "spring" }, excludeFilters = {
		@Filter(type = FilterType.ASPECTJ, pattern = "spring.*Dao"),
		@Filter(type = FilterType.ANNOTATION, classes = ManualBean.class) })
public class AppCtxWithExclude {
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}

	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}

	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}

	@Bean
	public VersionPrinter versionPrinter() {
		return new VersionPrinter();
	}
}
