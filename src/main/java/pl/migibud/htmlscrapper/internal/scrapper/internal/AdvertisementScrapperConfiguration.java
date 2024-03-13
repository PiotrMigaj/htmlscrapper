package pl.migibud.htmlscrapper.internal.scrapper.internal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration(proxyBeanMethods = false)
@ComponentScan
@EnableAsync
class AdvertisementScrapperConfiguration {

    @Bean(name = "threadPoolTaskExecutor")
    ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(6);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
