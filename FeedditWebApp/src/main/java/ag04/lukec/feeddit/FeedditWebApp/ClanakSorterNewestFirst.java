package ag04.lukec.feeddit.FeedditWebApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class ClanakSorterNewestFirst implements Comparator<Clanak> {

	@Override
	public int compare(Clanak c1, Clanak c2) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		
		LocalDate date1 = LocalDate.parse(c1.getDatum(), formatter);
		LocalDate date2 = LocalDate.parse(c2.getDatum(), formatter);
		
		if(date1.isBefore(date2))
			return 1;
		else if(date1.isAfter(date2))
			return -1;
		else
			return 0;
	}
}