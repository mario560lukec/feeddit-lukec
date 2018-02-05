package ag04.lukec.feeddit.FeedditWebApp;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeScreenController {

	@Autowired
	private ClanakService clanakService;

	@Autowired
	AccountService accountService;

	private Integer brojStranica = 1;
	private Integer trenutnaStranica = 1;

	private Boolean dodanPozitivanGlas = false;
	private Boolean dodanNegativanGlas = false;
	private Boolean sortirajNajstarijiPrvo = false;
	private Boolean sortirajNajnovijiPrvo = false;
	private Boolean sortirajBestFirst = false;
	private Boolean sortirajWorstFirst = false;

	private List<Clanak> lista;

	/*
	 * Provjerava kriterije sortiranja clanaka, te ih u odabranom redosljedu prikazuje na ekranu.
	 * Prikazuju se clanci svih korisnika.
	 * Svakom clanku moguce je dodjeliti pozitivan ili negativan glas.
	 */
	@RequestMapping(value = "/homeScreen", method = RequestMethod.GET)
	public String homeScreenWelcome(Model model) {
		if (accountService.getUlogiran() == true) {
			String user = accountService.getUsername();
			model.addAttribute("user", user);

			lista = clanakService.getAllClanak();

			if (accountService.getSortNone() == true) {
				sortirajNajstarijiPrvo = false;
				sortirajNajnovijiPrvo = false;
				sortirajBestFirst = false;
				sortirajWorstFirst = false;
			}
			else {
				if (sortirajNajstarijiPrvo)
					Collections.sort(lista, new ClanakSorterOldestFirst());
				if (sortirajNajnovijiPrvo)
					Collections.sort(lista, new ClanakSorterNewestFirst());
				if (sortirajBestFirst)
					Collections.sort(lista, new ClanakSorterBestFirst());
				if (sortirajWorstFirst)
					Collections.sort(lista, new ClanakSorterWorstFirst());
			}
			Integer velicinaBaze = lista.size();
			Integer bs = velicinaBaze / 4;

			if ((bs * 4) == velicinaBaze)
				brojStranica = bs;
			else
				brojStranica = bs + 1;

			if (trenutnaStranica > brojStranica)
				trenutnaStranica = brojStranica;
			if(trenutnaStranica == 0)
				trenutnaStranica = 1;

			model.addAttribute("page", trenutnaStranica);
			model.addAttribute("allPages", this.brojStranica);

			
			Integer brojClanka = (trenutnaStranica - 1) * 4;
			if (brojClanka <= lista.size() - 1)
				model.addAttribute("clanak1", lista.get(brojClanka++));
			else
				model.addAttribute("clanak1", new Clanak());
			if (brojClanka <= lista.size() - 1)
				model.addAttribute("clanak2", lista.get(brojClanka++));
			else
				model.addAttribute("clanak2", new Clanak());
			if (brojClanka <= lista.size() - 1)
				model.addAttribute("clanak3", lista.get(brojClanka++));
			else
				model.addAttribute("clanak3", new Clanak());
			if (brojClanka <= lista.size() - 1)
				model.addAttribute("clanak4", lista.get(brojClanka));
			else
				model.addAttribute("clanak4", new Clanak());

			if (dodanPozitivanGlas)
				model.addAttribute("msg", "Vaš glas je uspješno zaprimljen.");
			if (dodanNegativanGlas)
				model.addAttribute("msg", "Vaš glas je uspješno opozvan.");

			dodanPozitivanGlas = false;
			dodanNegativanGlas = false;
			accountService.setSortNone(false);

			return "homeScreen";
		} else
			return "nedozvoljenPristup";
	}

	/*
	 * Prikazuje iducih 4 clanka
	 */
	@RequestMapping(value = "/homeScreen", params = "right", method = RequestMethod.POST)
	public String nextPage(Model model) {

		if (trenutnaStranica > 0 && trenutnaStranica < brojStranica)
			trenutnaStranica++;

		return "redirect:/homeScreen";

	}

	/*
	 * Prikazuje prethodnih 4 clanka
	 */
	@RequestMapping(value = "/homeScreen", params = "left", method = RequestMethod.POST)
	public String previousPage(Model model) {

		if (trenutnaStranica > 1 && trenutnaStranica <= brojStranica)
			trenutnaStranica--;

		return "redirect:/homeScreen";

	}

	@RequestMapping(value = "/homeScreen", params = "like1", method = RequestMethod.POST)
	public String like1Article(Model model) {

		Clanak clanak = lista.get((trenutnaStranica - 1) * 4);
		dodanPozitivanGlas = true;
		return likeClanak(clanak);

	}

	@RequestMapping(value = "/homeScreen", params = "like2", method = RequestMethod.POST)
	public String like2Article(Model model) {

		Clanak clanak = lista.get((trenutnaStranica - 1) * 4 + 1);
		dodanPozitivanGlas = true;
		return likeClanak(clanak);
	}

	@RequestMapping(value = "/homeScreen", params = "like3", method = RequestMethod.POST)
	public String like3Article(Model model) {

		Clanak clanak = lista.get((trenutnaStranica - 1) * 4 + 2);
		dodanPozitivanGlas = true;
		return likeClanak(clanak);

	}

	@RequestMapping(value = "/homeScreen", params = "like4", method = RequestMethod.POST)
	public String like4Article(Model model) {

		Clanak clanak = lista.get((trenutnaStranica - 1) * 4 + 3);
		dodanPozitivanGlas = true;
		return likeClanak(clanak);
	}

	@RequestMapping(value = "/homeScreen", params = "dislike1", method = RequestMethod.POST)
	public String dislike1Article(Model model) {

		Clanak clanak = lista.get((trenutnaStranica - 1) * 4);
		dodanNegativanGlas = true;
		return dislikeClanak(clanak);

	}

	@RequestMapping(value = "/homeScreen", params = "dislike2", method = RequestMethod.POST)
	public String dislike2Article(Model model) {

		Clanak clanak = lista.get((trenutnaStranica - 1) * 4 + 1);
		dodanNegativanGlas = true;
		return dislikeClanak(clanak);

	}

	@RequestMapping(value = "/homeScreen", params = "dislike3", method = RequestMethod.POST)
	public String dislike3Article(Model model) {

		Clanak clanak = lista.get((trenutnaStranica - 1) * 4 + 2);
		dodanNegativanGlas = true;
		return dislikeClanak(clanak);

	}

	@RequestMapping(value = "/homeScreen", params = "dislike4", method = RequestMethod.POST)
	public String dislike4Article(Model model) {

		Clanak clanak = lista.get((trenutnaStranica - 1) * 4 + 3);
		dodanNegativanGlas = true;
		return dislikeClanak(clanak);

	}	

	private String dislikeClanak(Clanak clanak) {
		if (accountService.checkIfNegative(clanak.getClanakId())) {
			return "redirect:/homeScreen";
		} else {
			if (accountService.checkIfPositive(clanak.getClanakId())) {
				clanak.oduzmiGlas();
				clanak.oduzmiGlas();
				accountService.deleteFromPositive(clanak.getClanakId());
			} else
				clanak.oduzmiGlas();

			clanakService.updateClanak(clanak);
			accountService.addNegativeVote(clanak.getClanakId());

		}
		return "redirect:/homeScreen";
	}

	private String likeClanak(Clanak clanak) {
		if (accountService.checkIfPositive(clanak.getClanakId())) {
			return "redirect:/homeScreen";
		} else {
			if (accountService.checkIfNegative(clanak.getClanakId())) {
				clanak.dodajGlas();
				clanak.dodajGlas();
				accountService.deleteFromNegative(clanak.getClanakId());
			} else
				clanak.dodajGlas();

			clanakService.updateClanak(clanak);
			accountService.addPositiveVote(clanak.getClanakId());

		}
		return "redirect:/homeScreen";
	}

	@RequestMapping(value = "/addNewArticle", method = RequestMethod.POST)
	public String newArticle() {

		return "redirect:/addNewArticle";

	}

	@RequestMapping(value = "/GoToUserArticles", method = RequestMethod.POST)
	public String userArticles() {

		return "redirect:/userArticles";

	}

	/*
	 * Provjerava koji je nacin sortiranja odabran za prikaz clanaka.
	 */
	@RequestMapping(value = "/homeScreen", method = RequestMethod.POST)
	public String sort(@RequestParam(value = "filter") String nacin) {

		if (nacin.compareTo("najstarijiPrvo") == 0) {
			if (sortirajNajnovijiPrvo == true || sortirajBestFirst == true || sortirajWorstFirst == true) {
				sortirajNajnovijiPrvo = false;
				sortirajBestFirst = false;
				sortirajWorstFirst = false;
			}
			sortirajNajstarijiPrvo = true;
			trenutnaStranica = 1;
		}

		if (nacin.compareTo("najnovijiPrvo") == 0) {
			if (sortirajNajstarijiPrvo == true || sortirajBestFirst == true || sortirajWorstFirst == true) {
				sortirajNajstarijiPrvo = false;
				sortirajBestFirst = false;
				sortirajWorstFirst = false;
			}
			sortirajNajnovijiPrvo = true;
			trenutnaStranica = 1;
		}

		if (nacin.compareTo("najpozitivniji") == 0) {
			if (sortirajNajstarijiPrvo == true || sortirajNajnovijiPrvo == true || sortirajWorstFirst == true) {
				sortirajNajstarijiPrvo = false;
				sortirajNajnovijiPrvo = false;
				sortirajWorstFirst = false;
			}
			sortirajBestFirst = true;
			trenutnaStranica = 1;
		}

		if (nacin.compareTo("najnegativniji") == 0) {
			if (sortirajNajstarijiPrvo == true || sortirajNajnovijiPrvo == true || sortirajBestFirst == true) {
				sortirajNajstarijiPrvo = false;
				sortirajNajnovijiPrvo = false;
				sortirajBestFirst = false;
			}
			sortirajWorstFirst = true;
			trenutnaStranica = 1;
		}

		return "redirect:/homeScreen";

	}

	@RequestMapping(value = "/logoutFromHome", method = RequestMethod.POST)
	public String logoutFromHome() {

		return "redirect:/login";

	}
	
	@RequestMapping(value = "/getUserFromHome", method = RequestMethod.POST)
	public String gotToUser() {

		return "redirect:/userArticles";

	}
}
