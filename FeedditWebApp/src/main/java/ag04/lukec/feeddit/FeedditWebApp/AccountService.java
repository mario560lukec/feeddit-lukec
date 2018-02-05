package ag04.lukec.feeddit.FeedditWebApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	private String username;
	private Boolean ulogiran = false;
	private Integer idUlogiranogUsera;
	private Boolean sortNone;
	private Map<Integer, ArrayList<Long>> pozitivniGlasovi = new HashMap<Integer, ArrayList<Long>>();
	private Map<Integer, ArrayList<Long>> negativniGlasovi = new HashMap<Integer, ArrayList<Long>>();
	private Boolean noviLogin;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getUlogiran() {
		return ulogiran;
	}

	public void setUlogiran(Boolean ulogiran) {
		this.ulogiran = ulogiran;
		ArrayList<Long> positiveValues = pozitivniGlasovi.get(idUlogiranogUsera);
		if (positiveValues == null) {
			positiveValues = new ArrayList<Long>();
			pozitivniGlasovi.put(idUlogiranogUsera, positiveValues);
		}

		ArrayList<Long> negativeValues = negativniGlasovi.get(idUlogiranogUsera);
		if (negativeValues == null) {
			negativeValues = new ArrayList<Long>();
			negativniGlasovi.put(idUlogiranogUsera, negativeValues);
		}
	}

	public List<Account> getAllAccounts() {
		List<Account> listOfAccounts = new ArrayList<>();
		accountRepository.findAll().forEach(listOfAccounts::add);
		return listOfAccounts;
	}

	public Account getAccountById(Integer id) {
		return accountRepository.findOne(id);
	}

	public void addAccount(Account account) {
		accountRepository.save(account);
	}

	public void updateAccount(Account account) {
		accountRepository.save(account);
	}

	public void deleteAccount(Integer id) {
		accountRepository.delete(id);
	}

	public Integer getIdUlogiranogUsera() {
		return idUlogiranogUsera;
	}

	public void setIdUlogiranogUsera(Integer idUlogiranogUsera) {
		this.idUlogiranogUsera = idUlogiranogUsera;
	}


	public void addPositiveVote(Long id) {

		ArrayList<Long> lista = pozitivniGlasovi.get(idUlogiranogUsera);
		lista.add(id);

		pozitivniGlasovi.put(idUlogiranogUsera, (ArrayList<Long>) lista);

	}

	public void addNegativeVote(Long id) {

		ArrayList<Long> lista = negativniGlasovi.get(idUlogiranogUsera);
		lista.add(id);

		negativniGlasovi.put(idUlogiranogUsera, (ArrayList<Long>) lista);

	}

	public Boolean checkIfPositive(Long id) {
		ArrayList<Long> lista = pozitivniGlasovi.get(idUlogiranogUsera);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).compareTo(id) == 0)
				return true;
		}

		return false;
	}

	public Boolean checkIfNegative(Long id) {
		ArrayList<Long> lista = negativniGlasovi.get(idUlogiranogUsera);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).compareTo(id) == 0)
				return true;
		}

		return false;
	}

	public void deleteFromPositive(Long id) {
		ArrayList<Long> lista = pozitivniGlasovi.get(idUlogiranogUsera);
		lista.remove(id);
		pozitivniGlasovi.put(idUlogiranogUsera, lista);

	}
	
	public void deleteFromNegative(Long id) {
		ArrayList<Long> lista = negativniGlasovi.get(idUlogiranogUsera);
		lista.remove(id);
		negativniGlasovi.put(idUlogiranogUsera, lista);

	}

	public Boolean getSortNone() {
		return sortNone;
	}

	public void setSortNone(Boolean sortNone) {
		this.sortNone = sortNone;
	}

	public Boolean getNoviLogin() {
		return noviLogin;
	}

	public void setNoviLogin(Boolean noviLogin) {
		this.noviLogin = noviLogin;
	}
	
	
	
}
