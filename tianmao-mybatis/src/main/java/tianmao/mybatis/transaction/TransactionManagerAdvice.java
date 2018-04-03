package tianmao.mybatis.transaction;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * 事物管理器
 *
 * @author roach
 * @date 2017/11/21
 */
@Configuration
public class TransactionManagerAdvice {

    @Autowired
    private DataSourceTransactionManager transactionManager;

    // 创建事务通知
    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() {
        Properties properties = new Properties();
        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("register*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("send*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("create*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("destroy*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        return new TransactionInterceptor(transactionManager, properties);
    }

    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*Service", "*ServiceImpl");
        creator.setProxyTargetClass(true);
        return creator;
    }

}