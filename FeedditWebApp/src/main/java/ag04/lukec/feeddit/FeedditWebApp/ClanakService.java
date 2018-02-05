package ag04.lukec.feeddit.FeedditWebApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClanakService {

	@Autowired 
	private ClanakRepository clanakRepository;
	
	public List<Clanak> getAllClanak() {
		List<Clanak> listOfClanak = new ArrayList<>();
		clanakRepository.findAll().forEach(listOfClanak::add);
		return listOfClanak;
	}

	public Clanak getClanaktById(Long id) {
		return clanakRepository.findOne(id);
	}

	public void addClanak(Clanak clanak) {
		clanakRepository.save(clanak);
	}

	public void updateClanak(Clanak clanak) {
		clanakRepository.save(clanak);
	}

	public void deleteClanak(Long id) {
		clanakRepository.delete(id);
	}
	
	public Integer getNuberOfPages() {
		List<Clanak> lista = getAllClanak();

		Integer velicinaBaze = lista.size();
		Integer brojStranica = velicinaBaze / 3;

		if (brojStranica * 3 == velicinaBaze)
			return brojStranica;
		else
			return brojStranica + 1;

	}
}
