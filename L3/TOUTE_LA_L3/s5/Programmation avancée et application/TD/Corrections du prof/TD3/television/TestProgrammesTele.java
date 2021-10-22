package up.mi.jgm.td03.television;

import java.util.ArrayList;
import java.util.List;

public class TestProgrammesTele {

	public static void main(String[] args) {
		List<Programme> programmes = new ArrayList<Programme>();
		programmes.add(new Journal(12,13,"Gilles Bouleau"));
		programmes.add(new Fiction(20,22,"Star Wars","George Lucas",true));
		programmes.add(new Fiction(22,00,"Star Wars: The Last Jedi","Rian Johnson",false));
		programmes.add(new Reportage(13,14,Theme.ACTUALITE,"L'Universite de Paris"));
		programmes.add(new Divertissement(16, "Nagui", "N'oubliez pas les paroles"));
		
		for(Programme p : programmes)
			System.out.println(p);
	}

}
