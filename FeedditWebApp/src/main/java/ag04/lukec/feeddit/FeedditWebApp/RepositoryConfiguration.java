package ag04.lukec.feeddit.FeedditWebApp;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"ag04.lukec.feeddit.FeedditWebApp"})
@EnableJpaRepositories(basePackages = {"ag04.lukec.feeddit.FeedditWebApp"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}