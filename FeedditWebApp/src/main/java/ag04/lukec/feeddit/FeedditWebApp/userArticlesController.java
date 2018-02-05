package ag04.lukec.feeddit.FeedditWebApp;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class userArticlesController {

	@Autowired
	private ClanakService clanakService;

	@Autowired
	AccountService accountService;

	private Integer trenutnaStranica = 1;
	private Integer brojStranica;
	
	private Boolean clanakObrisan = false;
	private List<Clanak> lista;
	
	private Boolean sortirajNajstarijiPrvo = false;
	private Boolean sortirajNajnovijiPrvo = false;
	private Boolean sortirajBestFirst = false;
	private Boolean sortirajWorstFirst = false;
	
	/*
	 * Provjerava kriterije sortiranja clanaka, te ih u odabranom redosljedu prikazuje na ekranu.
	 * Prikazuju se clanci ulogiranog korisnika.
	 * Korisnik može obrisati svoje željene clanke.
	 */
	@RequestMapping(value = "/userArticles", method = RequestMethod.GET)
	public String getUserArticles(Model model) {
		
		if(accountService.getUlogiran() == true) {
		
			String user = accountService.getUsername();
			model.addAttribute("user", user);
			
			lista = clanakService.getAllClanak().stream().filter(clanak -> clanak.getKorisnikId().equals(accountService.getIdUlogiranogUsera())).collect(Collectors.toList());
			if(accountService.getNoviLogin())
				trenutnaStranica = 1;
			
			if(sortirajNajstarijiPrvo)
				Collections.sort(lista, new ClanakSorterOldestFirst());
			if(sortirajNajnovijiPrvo)
				Collections.sort(lista, new ClanakSorterNewestFirst());
			if(sortirajBestFirst)
				Collections.sort(lista, new ClanakSorterBestFirst());
			if(sortirajWorstFirst)
				Collections.sort(lista, new ClanakSorterWorstFirst());
			
			Integer velicinaBaze = lista.size();
			Integer bs = velicinaBaze / 4;

			if ((bs * 4) == velicinaBaze)
				brojStranica = bs;
			else
				brojStranica = bs + 1;

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

			if(clanakObrisan)
				model.addAttribute("msg", "Clanak uspješno obrisan.");
			clanakObrisan=false;
			
			accountService.setNoviLogin(false);
			
			return "userArticles";
		}
		else 
			return "nedozvoljenPristup";
		
	}
	
	/*
	 * Prikazuje iducih 4 clanka
	 */
	@RequestMapping(value = "/userArticles", params = "right", method = RequestMethod.POST)
	public String nextPage(Model model) {

		if (trenutnaStranica > 0 && trenutnaStranica < brojStranica)
			trenutnaStranica++;

		return "redirect:/userArticles";

	}

	/*
	 * Prikazuje prethodna 4 clanka
	 */
	@RequestMapping(value = "/userArticles", params = "left", method = RequestMethod.POST)
	public String previousPage(Model model) {

		if (trenutnaStranica > 1 && trenutnaStranica <= brojStranica)
			trenutnaStranica--;

		return "redirect:/userArticles";

	}
	
	@RequestMapping(value = "/userArticles",params = "delete1", method = RequestMethod.POST)
	public String deleteArticle1(Model model) {
		
		Integer idZaObrisati;
		idZaObrisati = (trenutnaStranica - 1) * 4;
		
		Long idClankaZaObrisati = lista.get(idZaObrisati).getClanakId();
		clanakService.deleteClanak(idClankaZaObrisati);

		clanakObrisan=true;
		
		if(trenutnaStranica > 1 && trenutnaStranica == brojStranica)
			trenutnaStranica = 1;
		
		return "redirect:/userArticles";
		
	}
	
	@RequestMapping(value = "/userArticles",params = "delete2", method = RequestMethod.POST)
	public String deleteArticle2(Model model) {
		
		Integer idZaObrisati;
		idZaObrisati = (trenutnaStranica - 1) * 4 + 1;
		
		Long idClankaZaObrisati = lista.get(idZaObrisati).getClanakId();
		clanakService.deleteClanak(idClankaZaObrisati);

		clanakObrisan=true;
		
		return "redirect:/userArticles";
		
	}
	
	@RequestMapping(value = "/userArticles",params = "delete3", method = RequestMethod.POST)
	public String deleteArticle3(Model model) {
		
		Integer idZaObrisati;
		idZaObrisati = (trenutnaStranica - 1) * 4 + 2;
		
		Long idClankaZaObrisati = lista.get(idZaObrisati).getClanakId();
		clanakService.deleteClanak(idClankaZaObrisati);

		clanakObrisan=true;
		
		return "redirect:/userArticles";
		
	}

	@RequestMapping(value = "/userArticles",params = "delete4", method = RequestMethod.POST)
	public String deleteArticle4(Model model) {
		
		

		Integer idZaObrisati;
		idZaObrisati = (trenutnaStranica - 1) * 4 + 3;
		
		Long idClankaZaObrisati = lista.get(idZaObrisati).getClanakId();
		clanakService.deleteClanak(idClankaZaObrisati);

		clanakObrisan=true;
		
		return "redirect:/userArticles";
		
	}
	
	@RequestMapping(value = "/backToHome", method = RequestMethod.POST)
	public String backToHome() {

		return "redirect:/homeScreen";

	}
	

	/*
	 * Provjerava koji je nacin sortiranja odabran za prikaz clanaka.
	 */
	@RequestMapping(value = "/userArticles", method = RequestMethod.POST)
	public String sortirajClankeKorisnika( @RequestParam(value="filter") String nacin) {
		
		if(nacin.compareTo("najstarijiPrvo") == 0) {
			if(sortirajNajnovijiPrvo == true || sortirajBestFirst == true || sortirajWorstFirst == true) {
				sortirajNajnovijiPrvo = false;
				sortirajBestFirst = false;
				sortirajWorstFirst = false;
			}
			sortirajNajstarijiPrvo = true;
			trenutnaStranica = 1;
		}
		
		if(nacin.compareTo("najnovijiPrvo") == 0) {
			if(sortirajNajstarijiPrvo == true || sortirajBestFirst == true || sortirajWorstFirst == true) {
				sortirajNajstarijiPrvo = false;
				sortirajBestFirst = false;
				sortirajWorstFirst = false;
			}
			sortirajNajnovijiPrvo = true;
			trenutnaStranica = 1;
		}
		
		if(nacin.compareTo("najpozitivniji") == 0) {
			System.out.println("pozitivnoo sort");
			if(sortirajNajstarijiPrvo == true || sortirajNajnovijiPrvo == true || sortirajWorstFirst == true) {
				sortirajNajstarijiPrvo = false;
				sortirajNajnovijiPrvo = false;
				sortirajWorstFirst = false;
			}
			sortirajBestFirst = true;
			trenutnaStranica = 1;
		}
		
		if(nacin.compareTo("najnegativniji") == 0) {
			System.out.println("negativno sort");
			if(sortirajNajstarijiPrvo == true || sortirajNajnovijiPrvo == true || sortirajBestFirst == true) {
				sortirajNajstarijiPrvo = false;
				sortirajNajnovijiPrvo = false;
				sortirajBestFirst = false;
			}
			
			sortirajWorstFirst = true;
			trenutnaStranica = 1;
		}
		
		return "redirect:/userArticles";

	}
	

	@RequestMapping(value = "/logoutFromUser", method = RequestMethod.POST)
	public String logoutFromUser() {

		return "redirect:/login";

	}
}
