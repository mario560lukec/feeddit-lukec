package ag04.lukec.feeddit.FeedditWebApp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DodajClanakController {

	@Autowired
	private ClanakService clanakService;

	@Autowired
	private AccountService accountService;

	/*
	 * Prikazuje ekran za unos novog clanka.
	 */
	@RequestMapping(value = "/addNewArticle", method = RequestMethod.GET)
	public String accountForm(Model model) {
		if (accountService.getUlogiran() == true) {
			model.addAttribute("clanak", new Clanak());
			model.addAttribute("user", accountService.getUsername());

			return "addNewArticle";
		} else
			return "nedozvoljenPristup";
	}

	/**
	 * Provjerava jesu li su svi podaci uneseni i je li je link ispravan. 
	 * Ukoliko je sve ispravno uneseno, u bazu se sprema novi clanak.
	 * 
	 */
	@RequestMapping(value = "/addNewArticle", params = "publish", method = RequestMethod.POST)
	public String addArticle(@ModelAttribute Clanak clanak, Model model) {

		model.addAttribute("clanak", clanak);

		String[] schemes = {"http", "https"};
		UrlValidator urlValidator = new UrlValidator(schemes);
		
		if (clanak.getNaslov().isEmpty() || clanak.getAutor().isEmpty() || clanak.getLink().isEmpty()) {
			model.addAttribute("error", "Unesite sve podatke!");
			model.addAttribute("user", accountService.getUsername());
			return null;
		}
		else if( urlValidator.isValid(clanak.getLink()) == false ) {
			model.addAttribute("error", "Unesite ispravan link!");
			model.addAttribute("user", accountService.getUsername());
			return null;
		} else {

			LocalDateTime datum = LocalDateTime.now();
			String datumString = datum.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));

			clanak.setDatum(datumString);
			clanak.setGlasovi(0);
			clanak.setKorisnikId(accountService.getIdUlogiranogUsera());

			clanakService.addClanak(clanak);

			return "redirect:/homeScreen";

		}

	}

	@RequestMapping(value = "/addNewArticle", params = "cancel", method = RequestMethod.POST)
	public String cancel() {

		return "redirect:/homeScreen";

	}
	
	@RequestMapping(value = "/logoutFromNew", method = RequestMethod.POST)
	public String logoutFromNew() {

		return "redirect:/login";

	}
	
	@RequestMapping(value = "/getUserFromNew", method = RequestMethod.POST)
	public String gotToUser() {

		return "redirect:/userArticles";

	}
}
