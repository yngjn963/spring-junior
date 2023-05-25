package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링이 뜰 때 Configuration을 읽는다.
public class SpringConfig {
//    private DataSource dataSource; // application.properties에 등록된 정보로 DataSource 빈을 스프링이 생성해 준다.
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    @PersistenceContext // 1번 방법
//    private EntityManager em;
//
//    //2번 방법: DI
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository; // 스프링데이터 JPA가 구현체를 만들고, 해당 구현체가 등록된다.

    @Autowired // 생성자가 하나일 경우 생략 가능하다.
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean // 스프링 빈에 등록
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository); // SpringDataJpaMemberRepository 인터페이스를 만들어 두어, 해당 스프링데이터 JPA가 구현체를 Bean으로 등록.
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//        /*
//        스프링을 왜 쓰는가?
//        객체 지향, 다형성을 편리하게 구현할 수 있도록 스프링 컨테이너가 지원한다. (Dependency Injection 등을 통해)
//
//        애플리케이션 조립(어셈블리)하는 설정만 수정하면 실제 애플리케이션 소스는 수정하지 않아도 된다.
//
//        개방-폐쇄 원칙(OCP, Open-Closed Principle)
//        확장에는 열려있고, 수정, 변경에는 닫혀있다.
//        스프링의 DI (Dependencies Injection)을 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.
//         */
//    }
    /*
    XML로 설정하는 방식도 있지만 최근에는 잘 사용하지 않는다.

    DI에는 필드 주입, setter 주입, 생성자 주입 이렇게 3가지 방법이 있다. 의존관계가 실행중에 동적으로 변하는 경우(서버가 실행되어 있는 런타임 상태에서 변하는 경우)는 거의 없으므로 생성자 주입을 권장한다.
    필드 주입: @Autowired private MemberService memberService;
    - 필드에 바로 주입.
    - 단점: 필드를 바꿔줄 수 있는 방법이 없다.
    setter 주입: setter 메소드에 @Autowired
    - 단점: setter 메소드가 public 접근제어자로 열려 있어, 한 번 주입(로딩) 이후에 보통 변하지 않는 빈에 대해 변경될 위험 소지가 있다.
    생성자 주입: 스프링 컨테이너에 올라가고, 애플리케이션이 조립(조립이라는 표현을 사용한다.)되는 시점에 한 번 들어오고 끝.

    실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
    그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
    = 데이터베이스와 연동하는 리포지토리로 변경하는 과정에서 기존의 서비스나 리포지토리 변경없이 하는데 적용한다.

    주의: @Autowired를 통한 DI는 helloController, MemberService 등과 같이 스프링이 관리하는 객체 에서만 동작한다.
    스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.
    */
}
