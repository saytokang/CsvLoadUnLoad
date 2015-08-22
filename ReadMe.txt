Spring-mybatis 소스는 
spring framework을 이용한 개발에서 template으로 사용하기 위해 작성되었음.

각 프로젝트에서 수정해야 할 부분은.

1. DB 연결.
	/resources/configuration/mybatis/config.properties

1.1 Transaction 처리
	2개 이상의 table 에 영향(insert, update, delete)의 경우 해당.
	/resources/spring/db_transaction_aop-context.xml
	위 파일에서 package 명을 수정.(x.y.z)
	예를 들면 이런 부분 :  <aop:pointcut id="requiredTx" expression="execution(* x.y.z..*Impl.*(..)) "/>

2. package명 
	resources/spring/root-context.xml,
	WEB-INF/spring/appServlet/servlet-context.xml
	component-scan 부분 수정.
	
3. json 처리
	예제 프로그램으로
	http://localhost/sampleJson.do
	
4. security login 처리
	/spring/security-context.xml
	관련 package는  /x/y/z/security
	web.xml에 filter 추가.
	login.jsp / welcom.jsp (로그인 후 성공)
	
	
	
	
	
	

