package jpabook.jpashop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class JpashopApplication {
	public static void main(String[] args) {
		//db 경로 jdbc:h2:~/jpashop
		//jdbc:h2:tcp://localhost/~/jpashop
		SpringApplication.run(JpashopApplication.class, args);
		Hello hello = new Hello();
		hello.setData("hello!!");
		String data = hello.getData();

		System.out.println("data = " + data);
		log.info("hello = {}", data);
	}
}