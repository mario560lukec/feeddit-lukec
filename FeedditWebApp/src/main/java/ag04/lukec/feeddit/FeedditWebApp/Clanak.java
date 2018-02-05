package ag04.lukec.feeddit.FeedditWebApp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Clanak {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long clanakId;
	private String datum;
	private Integer korisnikId;
	private String naslov;
	private String link;
	private String autor;
	private Integer glasovi=0;
	
	
	public Long getClanakId() {
		return clanakId;
	}

	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum2) {
		this.datum = datum2;
	}
	public Integer getKorisnikId() {
		return korisnikId;
	}
	public void setKorisnikId(Integer korisnikId) {
		this.korisnikId = korisnikId;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Integer getGlasovi() {
		return glasovi;
	}
	public void setGlasovi(Integer glasovi) {
		this.glasovi = glasovi;
	}
	
	public void dodajGlas() {
		glasovi++;
	}
	
	public void oduzmiGlas() {
		glasovi--;
	}
	
}
