package pers.wh.test.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author wanghui
 * @date 2019/5/8 12:31
 */
@Configuration
@EnableDubbo(scanBasePackages = "pers.wh.test.dubbo")
@PropertySource("classpath:application.properties")
public class ProviderConfiguration {
}
