package ag04.lukec.feeddit.FeedditWebApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String toLogin() {
		return "redirect:/login";
	}
	
	/*
	 * Prikazuje login ekran.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String accountForm(Model model) {
		model.addAttribute("account", new Account());
		if(accountService.getUlogiran() == true) {
			model.addAttribute("loggedOut", "You have been logged out.");
			accountService.setUlogiran(false);
		}
		return "login";
	}

	/*
	 * Uzima unese podatke te provjerava jesu li uneseni username i password u bazi korisnika.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String accountSubmit(@ModelAttribute Account account, Model model) {

		model.addAttribute("account", account);
		List<Account> listaAccounta = accountService.getAllAccounts();

		if (account.getUsername().isEmpty() || account.getPassword().isEmpty()) {
			model.addAttribute("error", "Lozinka i korisničko ime su obavezna polja.");
			return null;
		}

		for (int i = 0; i < listaAccounta.size(); i++) {
			if (account.getUsername().compareTo( listaAccounta.get(i).getUsername() ) == 0 && account.getPassword().compareTo(listaAccounta.get(i).getPassword()) == 0) {
				
				accountService.setUsername(account.getUsername());
				accountService.setIdUlogiranogUsera(listaAccounta.get(i).getIdUsera());
				accountService.setUlogiran(true);
				accountService.setSortNone(true);
				accountService.setNoviLogin(true);
		        return "redirect:/homeScreen";
			}
		}

		if (accountService.getUlogiran() == false) {
			model.addAttribute("error", "Korisničko ime ili lozinka su neispravni.");
			return null;
		}
		else
			return null;
	}
	
}
