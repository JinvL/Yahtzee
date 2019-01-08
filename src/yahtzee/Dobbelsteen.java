package yahtzee;

import java.util.Random;

class Dobbelsteen {
	int waarde;
	
	int rollen() {
		Random random = new Random();
		waarde = random.nextInt(6)+1;
		return waarde;
	}
}