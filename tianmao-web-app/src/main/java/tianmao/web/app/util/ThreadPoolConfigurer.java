package tianmao.web.app.util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池 配置
 *
 * @author roach
 * @date 2017/12/24
 */
@Configuration
public class ThreadPoolConfigurer {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);//线程池维护线程的最少数量
        threadPoolTaskExecutor.setQueueCapacity(100);//队列最大长度
        threadPoolTaskExecutor.setMaxPoolSize(100);//最大线程数
        return threadPoolTaskExecutor;
    }

}
