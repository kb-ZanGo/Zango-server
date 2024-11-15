package kb.zango;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("kb.zango.domain.diary.mapper")
public class ZangoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZangoApplication.class, args);
    }

}
