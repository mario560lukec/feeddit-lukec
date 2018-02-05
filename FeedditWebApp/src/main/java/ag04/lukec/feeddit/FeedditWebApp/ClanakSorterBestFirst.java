package ag04.lukec.feeddit.FeedditWebApp;

import java.util.Comparator;

public class ClanakSorterBestFirst implements Comparator<Clanak> {

	@Override
	public int compare(Clanak c1, Clanak c2) {

		if(c1.getGlasovi() < c2.getGlasovi())
			return 1;
		else if(c1.getGlasovi() > c2.getGlasovi())
			return -1;
		else
			return 0;
	}
}

