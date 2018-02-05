package ag04.lukec.feeddit.FeedditWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class AccountLoader implements ApplicationListener<ContextRefreshedEvent> {
 
    private AccountRepository accountRepository;
 
    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
 
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
 
    	Account account = new Account("Mario", "lozinka1");
    	accountRepository.save(account);
    	
    	Account account2 = new Account("Ana", "lozinka2");
    	accountRepository.save(account2);
    	
    	Account account3 = new Account("Ivan", "lozinka3");
    	accountRepository.save(account3);
    }

}
