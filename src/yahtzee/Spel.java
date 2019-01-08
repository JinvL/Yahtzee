package yahtzee;

import java.util.Scanner;

class Spel {
	
	static Speler[] hoeveelSpelers(Scanner scanner) {
		int aantalSpelers=0;
		System.out.println("Welkom bij Yahtzee!");
		System.out.print("\nJe kan met 1 of meerdere mensen spelen. Met hoeveel spelers wil je spelen? ");
		aantalSpelers=scanner.nextInt();
		while (aantalSpelers<1) {	
			System.out.print("\nDit is geen geldige invoer. Geef een heel getal op van minimaal 1: ");
			aantalSpelers=scanner.nextInt();
		}
		return spelersAanmaken(aantalSpelers, scanner);
	}
	
	static Speler[] spelersAanmaken(int aantalSpelers, Scanner scanner) {
		String naam;
		Speler[] spelersArray = new Speler[aantalSpelers];
		for(int i=0;i<aantalSpelers;i++) {
			if(aantalSpelers==1) {
				System.out.print("Wat is je naam? ");
			} else {
				System.out.print("Speler "+(i+1)+", wat is je naam? ");
			}
			naam = scanner.next();
			spelersArray[i] = new Speler(naam);
		}
		System.out.println("Ok, we gaan van start!\n");
		return spelersArray;
	}
	
	static void hoogste(Speler[] spelers) {
		int hoogste=0;
		int counter=0;
		for(int i=0;i<spelers.length;) {					//wat is de hoogste score van alle spelers?
			if(spelers[i].scoreblad.totaalTotaal>hoogste) {
				hoogste=spelers[i].scoreblad.totaalTotaal;
			}
		}
		for(int i=0;i<spelers.length;) {				//hoeveel spelers delen deze score?
			if(spelers[i].scoreblad.totaalTotaal==hoogste) {
				counter++;
			}
		}
		int[] index = new int[counter];		
		for(int i=0,k=0;i<spelers.length;) {				//welk(e) indexnummer(s) van de spelers[] heeft(/hebben) de hoogste score?
			if(spelers[i].scoreblad.totaalTotaal==hoogste) {
				index[k]=i;
				k++;
			}
		}
		if(counter==1) {
			eenWinnaar(index, spelers);
		} else if(counter>1) {
			meerdereWinnaars(index, spelers);
		}
	}
	
	static void eenWinnaar(int[] index, Speler[] spelers) {
		System.out.println("Het lijkt erop dat "+spelers[index[0]].spelerNaam+" gewonnen heeft.\nVan harte gefeliciteerd!\n:D :D :D :D :D");
	}
	
	static void meerdereWinnaars(int[] index, Speler[] spelers) {
		System.out.println("We hebben een gedeelde eerste plek!");
		for(int a=0;a<index.length;a++) {
			System.out.print(spelers[index[a]].spelerNaam);
			if(index.length-a==1) {
				System.out.print(" en ");
			} else {
				System.out.print(", ");
			}
		}
		System.out.print("hebben gezamelijk gewonnen:\nGefeliciteerd!");
	}
}
