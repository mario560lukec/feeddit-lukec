package ag04.lukec.feeddit.FeedditWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ClanakLoader implements ApplicationListener<ContextRefreshedEvent> {

	private ClanakRepository clanakRepository;

	@Autowired
	public void setClanakRepository(ClanakRepository clanakRepository) {
		this.clanakRepository = clanakRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		Clanak clanak1 = new Clanak();
		clanak1.setKorisnikId(1);
		clanak1.setDatum("20.01.2018.");
		clanak1.setGlasovi(0);
		clanak1.setNaslov("Treće mjesto na svijetu i bogata novčana nagrada sigurno će utješiti Čilića");
		clanak1.setAutor("Silvijo Škrlec");
		clanak1.setLink("https://www.tportal.hr/sport/clanak/trece-mjesto-na-svijetu-i-bogata-novcana-nagrada-sigurno-ce-utjesiti-cilica-foto-20180128");

		clanakRepository.save(clanak1);

		Clanak clanak2 = new Clanak();
		clanak2.setKorisnikId(1);
		clanak2.setDatum("19.01.2018.");
		clanak2.setGlasovi(0);
		clanak2.setNaslov("Samsung galaxy s9 stiže 25.veljače");
		clanak2.setAutor("Ivan Hruškovec");
		clanak2.setLink("https://www.24sata.hr/tech/sada-je-sluzbeno-samsung-ce-25-veljace-otkriti-galaxy-s9-557856");

		clanakRepository.save(clanak2);

		Clanak clanak3 = new Clanak();
		clanak3.setKorisnikId(1);
		clanak3.setDatum("21.01.2018.");
		clanak3.setGlasovi(0);
		clanak3.setNaslov("Mercedes otkrio datum prikazivanja novog F1 bolida");
		clanak3.setAutor("fermen");
		clanak3.setLink("http://www.gp1.hr/2018/01/mercedes-otkrio-datum-prikazivanja-novog-bolida/");

		clanakRepository.save(clanak3);

		Clanak clanak4 = new Clanak();
		clanak4.setKorisnikId(1);
		clanak4.setDatum("19.01.2018.");
		clanak4.setGlasovi(0);
		clanak4.setNaslov("Stipe Miočić:'Sanjam Poljud i 50.000 ljudi'");
		clanak4.setAutor("Marko Šnidarić");
		clanak4.setLink("https://www.24sata.hr/sport/miocic-ekskluzivno-za-24sata-sanjam-poljud-i-50-000-ljudi-558138");

		clanakRepository.save(clanak4);
		
		Clanak clanak5 = new Clanak();
		clanak5.setKorisnikId(1);
		clanak5.setDatum("20.01.2018.");
		clanak5.setGlasovi(0);
		clanak5.setNaslov("Sve o novoj Kiji Stinger");
		clanak5.setAutor("Dubravko Kolarić");
		clanak5.setLink("https://www.24sata.hr/tech/vozili-smo-kiju-koja-je-bolja-od-mercedesa-bmw-a-i-audija-558409");

		clanakRepository.save(clanak5);
		
		Clanak clanak6 = new Clanak();
		clanak6.setKorisnikId(2);
		clanak6.setDatum("17.01.2018.");
		clanak6.setGlasovi(0);
		clanak6.setNaslov("Na eBayu više se neće plaćati putem PayPala");
		clanak6.setAutor("Ivan Pavić");
		clanak6.setLink("https://www.24sata.hr/tech/kraj-suradnje-na-ebayu-vise-se-nece-placati-putem-paypala-559140");

		clanakRepository.save(clanak6);
		
		Clanak clanak7 = new Clanak();
		clanak7.setKorisnikId(3);
		clanak7.setDatum("04.02.2018.");
		clanak7.setGlasovi(0);
		clanak7.setNaslov("Ricciardo: 'Verstappen je prvi timski kolega koji mi je zaista izazov'");
		clanak7.setAutor("Ante Vetma");
		clanak7.setLink("https://www.f1puls.com/24562/ricciardo-verstappen-je-prvi-timski-kolega-koji-mi-je-zaista-izazov-anketa/");

		clanakRepository.save(clanak7);
		
		Clanak clanak8 = new Clanak();
		clanak8.setKorisnikId(3);
		clanak8.setDatum("04.02.2018.");
		clanak8.setGlasovi(0);
		clanak8.setNaslov("Originalno rješenje krize Reala: 'Klonirajte Modrića!'");
		clanak8.setAutor("Jakov David Milić");
		clanak8.setLink("http://www.goal.com/hr/vijesti/legenda-reala-i-liverpoola-klonirajte-modrica/2owcxch8bqyf1cw8bcr5ap0ca");

		clanakRepository.save(clanak8);
	}
	

}
