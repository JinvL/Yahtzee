package yahtzee;
import java.util.Scanner;

public class Yahtzee {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		boolean tf=true;
		Speler[] spelers = Spel.hoeveelSpelers(scanner);
	
		for(int r=1;r<14;r++) {
			for(Speler s: spelers) {
				int i=0;
				s.dobbelen(scanner);
				do {
				tf= s.nogEenRol(scanner);
				i++;
				} while ((tf) && (i<2));
				s.kiesJeScoreveld(scanner);
				if(r==13) {
					s.scoreblad.berekenTotaal();
				}
			}
		}
		System.out.println("\n\t**********************\n\tHet spel is afgelopen!\n\t**********************");
		if (spelers.length==1) {
			System.out.println("Bedankt voor het spelen :D  Zie hieronder je eindresultaat: ");
			spelers[0].scoreblad.toonScoreblad();
		} else {
			Spel.hoogste(spelers);
			System.out.println("Toets 'e' om jullie uiteindelijke scorebladen te zien: ");
			String input=scanner.next();
			if(input=="e") {
				for(int i=0;i<spelers.length;i++) {
					System.out.println("\n"+spelers[i].spelerNaam+" dit is je eindresultaat:");
					spelers[i].scoreblad.toonScoreblad();
				}
			}
		}
		scanner.close();
	}
}
//eventueel nog toe te voegen:
//- melding geven als niet aan de conditie van de gekozen optie wordt voldaan en score dus nul wordt
//	(en mogelijkheid om keuze aan te passen)
//- Yahtzee bonus toevoegen
//- Tournooi mogelijkheid, dus t/m 5 spellen met dezelfde spelers.
