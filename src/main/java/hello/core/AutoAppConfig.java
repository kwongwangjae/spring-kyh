package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
	basePackages = "hello.core.member",
	basePackageClasses = AutoAppConfig.class,
	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)//AppConfig.class를 없애기 위해서 예외처리를 해줌 이건 @Component와 무엇이 다른가 , basePackages 어디서 부터 찾는가? basePackageClasses는 이 클래스의 패키지부터 찾는다.
//근데 지정을 안하면?? 이것을 붙인 패키지부터 시작 그렇다면 1번라인인 package hello.core;가 기준임 -> 설정정보 클래스의 위치를 프로젝트 최상단에 두는 것이다.
public class AutoAppConfig {

}
