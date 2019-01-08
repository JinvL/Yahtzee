package yahtzee;

import java.util.Scanner;

class Speler {
	String spelerNaam;
	Scoreblad scoreblad = new Scoreblad();
	Dobbelsteen dobbelA = new Dobbelsteen();
	Dobbelsteen dobbelB = new Dobbelsteen();
	Dobbelsteen dobbelC = new Dobbelsteen();
	Dobbelsteen dobbelD = new Dobbelsteen();
	Dobbelsteen dobbelE = new Dobbelsteen();
	
	Dobbelsteen[] dobbelstenen = {dobbelA, dobbelB, dobbelC, dobbelD, dobbelE};
	
	Speler(String spelerNaam) {
		this.spelerNaam=spelerNaam;
	}
	
	void dobbelen(Scanner scanner) {
		System.out.print("\n"+spelerNaam+", zie hier je huidige scoreblad: \n");
		scoreblad.toonScoreblad();
		System.out.print("Toets 'd' om te dobbelen: ");
		String s = scanner.next();
		scanner.nextLine();
		while(!(s.equalsIgnoreCase("d"))) {
			System.out.println("Dit is geen geldige invoer. Toets d om te dobbelen: ");
			s=scanner.next();
		}
		for(Dobbelsteen steen : dobbelstenen) {
			steen.rollen();
		}
		toonWorp();
	}
	
	void toonWorp() {
		System.out.println("Je huidige worp is:\n");
		System.out.print("Dobbelsteen:");
		for (char c=65;c<(dobbelstenen.length+65);c++) {
			System.out.print("\t"+c);
		}
		System.out.print("\n       Worp:");
		for (int i=0;i<dobbelstenen.length; i++) {
			System.out.print("\t"+dobbelstenen[i].waarde);
		}
		System.out.println("\n");
	}
	
	boolean nogEenRol(Scanner scanner) {
		String s;
		System.out.println("Wil je nog een keer dobbelen, "+spelerNaam+"?\nZo ja, geef aan met welke dobbelstenen je opnieuw wil gooien (A, B, C, D, E).\nZo nee, toets 'nee'.");
		s=scanner.nextLine();
		s=s.replaceAll("\\s", "g");
		s=s.replaceAll(",", "g");
		s=s.toUpperCase();
		if(s.equals("NEE")) {
			return false;
		} else {
			System.out.print("Ok, we rollen dobbelsteen ");
			char[] chars = s.toCharArray();
			for(int i=0;i<chars.length;i++) {
				switch(chars[i]) {
				case 'A':
					System.out.print("A");
					printen(i,chars.length);
					dobbelA.rollen();
					break;
				case 'B':
					System.out.print("B");
					printen(i,chars.length);
					dobbelB.rollen();
					break;
				case 'C':
					System.out.print("C");
					printen(i,chars.length);
					dobbelC.rollen();
					break;
				case 'D':
					System.out.print("D");
					printen(i,chars.length);
					dobbelD.rollen();
					break;
				case 'E':
					System.out.print("E");
					printen(i,chars.length);
					dobbelE.rollen();
					break;
				default:
				}
			}
			System.out.print("opnieuw!\n");
			toonWorp();
			return true;
		}
	}
	
	void kiesJeScoreveld(Scanner scanner) {
		System.out.print("\nWaar op je scoreblad wil je deze worp plaatsen?\nKies een nummer uit de linker kolom waarvan nog geen score is ingevuld: ");
		int invoer=scanner.nextInt();
		while((invoer>13)||(invoer<1)) {
			System.out.print("\nDit is geen geldige invoer. Probeer het opnieuw: ");
			invoer=scanner.nextInt();
		}
		while(scoreblad.spelerScores[invoer-1]>-1) {
			System.out.print("\nJe hebt deze optie al gebruikt. Probeer het opnieuw: ");
			invoer=scanner.nextInt();
		}
		if(invoer<7) {
			scoreblad.checkBoven(invoer, dobbelstenen);
		}
		switch (invoer) {
		case 7:
			scoreblad.checkThreeOfAKind(dobbelstenen);
			break;
		case 8:
			scoreblad.checkFourOfAKind(dobbelstenen);
			break;
		case 9:
			scoreblad.checkFullHouse(dobbelstenen);
			break;
		case 10:
			scoreblad.checkKleineStraat(dobbelstenen);
			break;
		case 11:
			scoreblad.checkGroteStraat(dobbelstenen);
			break;
		case 12:
			scoreblad.checkYahtzee(dobbelstenen);
			break;
		case 13:
			scoreblad.checkChance(dobbelstenen);
			break;
		}
		scoreblad.arraysBijwerken();
	}
	
	void printen(int i, int length) {
		if(i<(length-2)) {
			System.out.print(", ");
		} else if(i==(length-2)) {
			System.out.print(" en ");
		} else {
			System.out.print(" ");
		}
	}
}
