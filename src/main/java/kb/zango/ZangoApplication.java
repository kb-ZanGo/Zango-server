package kb.zango;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@MapperScan("kb.zango.domain.**.mapper")
public class ZangoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZangoApplication.class, args);
    }

}
