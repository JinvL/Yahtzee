package yahtzee;

import java.util.Arrays;

class Scoreblad {
	int enen=-1;
	int tweeën=-1;
	int drieën=-1;
	int vieren=-1;
	int vijven=-1;
	int zessen=-1;
	int bovenblad=-2;
	int bonus=-2;
	int bovenbladinclBonus=-2;
	
	int threeOfAKind=-1;
	int fourOfAKind=-1;
	int fullHouse=-1;
	int kleineStraat=-1;
	int groteStraat=-1;
	int yahtzee=-1;
	int chance=-1;
	int benedenblad=-2;
	int totaalTotaal=-2;

	
	String[] opties={"Enen","Tweeën","Drieën","Vieren","Vijven","Zessen","Totaal","Bonus","Totaal+bonus","Three of a kind","Four of a kind","Full House","Kleine straat","Grote straat","Yahtzee","Kans","Totaal","TOTAAL"};
	int[] scores={enen,tweeën,drieën,vieren,vijven,zessen,bovenblad,bonus,bovenbladinclBonus,threeOfAKind,fourOfAKind,fullHouse,kleineStraat,groteStraat,yahtzee,chance,benedenblad,totaalTotaal};
	String[] spelerOpties={"Enen","Tweeën","Drieën","Vieren","Vijven","Zessen","Three of a kind","Four of a kind","Full House","Kleine straat","Grote straat","Yahtzee","Kans"};
	int[] spelerScores={enen,tweeën,drieën,vieren,vijven,zessen,threeOfAKind,fourOfAKind,fullHouse,kleineStraat,groteStraat,yahtzee,chance}; 
	
	void toonScoreblad() {
		System.out.println("=========================================");
		System.out.printf("%1$3s %2$-15s %3$2s %4$-8s %5$s %6$-5s %7$s","|","Opties","|","Ingevuld","|","Score","|");
		System.out.println("\n=========================================");
		for(int i=0,b=1;i<opties.length;i++) {
			if ((i<6) || (i>8 && i<16)) {
				System.out.printf("%2d",b);
				b++;
			} else {
				System.out.printf("%2s","  ");
			}
			String optie = opties[i];
			int score = scores[i];
			if (score<-1) {
				System.out.printf("%1$s %2$-15s %3$2s %4$-8s %5$s %6$-5s %7$s\n","|",optie,"|","  ---","|","","|");
			} else if(score==-1) {
				System.out.printf("%1$s %2$-15s %3$2s %4$-8s %5$s %6$-5s %7$s\n","|",optie,"|","","|","","|");
			} else {
				System.out.printf("%1$s %2$-15s %3$2s %4$-8s %5$s %6$-5s %7$s\n","|",optie,"|"," check","|"," "+score,"|");
			}
		}
		System.out.println("=========================================");
	}
	
	void checkBoven(int invoer, Dobbelsteen[] stenen) {
		spelerScores[invoer-1]=0;
		int score=0;
		for(Dobbelsteen d:stenen) {
			if (d.waarde==invoer) {
				score+=invoer;
			}
		}
		spelerScores[invoer-1]=score;
		System.out.println("Je punten zijn bij je "+spelerOpties[invoer-1]+" genoteerd!");
	}
	
	void checkThreeOfAKind(Dobbelsteen[] stenen) {
		spelerScores[6]=0;
		int counter=0;
		int[] waarden = new int[stenen.length];
		for(int i=0;i<stenen.length;i++) {
			waarden[i]=stenen[i].waarde;
		}
		Arrays.sort(waarden);
		if((waarden[0]==waarden[1]) && (waarden[1]==waarden[2])) {
			counter++;
		}
		if((waarden[1]==waarden[2]) && (waarden[2]==waarden[3])) {
			counter++;
		}
		if((waarden[2]==waarden[3]) && (waarden[3]==waarden[4])) {
			counter++;
		}
		if (counter>0) {
			for(Dobbelsteen d:stenen) {
				spelerScores[6]+=d.waarde;
			}
		}
		System.out.println("Je punten zijn bij je Three of a Kind genoteerd!");		
	}	
	
	
	void checkFourOfAKind(Dobbelsteen[] stenen) {
		spelerScores[7]=0;
		int counter=0;
		int[] waarden = new int[stenen.length];
		for(int i=0;i<stenen.length;i++) {
			waarden[i]=stenen[i].waarde;
		}
		Arrays.sort(waarden);
		if((waarden[0]==waarden[1]) && (waarden[1]==waarden[2])&&(waarden[2]==waarden[3])) {
			counter++;
		}
		if((waarden[1]==waarden[2]) && (waarden[2]==waarden[3])&&(waarden[3]==waarden[4])) {
			counter++;
		}
		if (counter>0) {
			for(Dobbelsteen d:stenen) {
				spelerScores[7]+=d.waarde;
			}
		}
		System.out.println("Je punten zijn bij je Three of a Kind genoteerd!");		
	}		
	
	void checkFullHouse(Dobbelsteen[] stenen) {
		spelerScores[8]=0;
		int counter=0;
		int[] waarden = new int[stenen.length];
		for(int i=0;i<stenen.length;i++) {
			waarden[i]=stenen[i].waarde;
		}
		Arrays.sort(waarden);
		if((waarden[0]==waarden[1]) && (waarden[1]==waarden[2])&&(waarden[3]==waarden[4])) {
			counter++;
		}
		if((waarden[0]==waarden[1]) && (waarden[2]==waarden[3])&&(waarden[3]==waarden[4])) {
			counter++;
		}

		if (counter>0) {
			spelerScores[8]=25;
		}
		System.out.println("Je punten zijn bij je Full House genoteerd!");
	}	
	
	void checkKleineStraat(Dobbelsteen[] stenen) {
		spelerScores[9]=0;
		int counter=0;
		for(int i1=0;i1<stenen.length;i1++) {
			for(int i2=0;i2<stenen.length;i2++) {
				for(int i3=0;i3<stenen.length;i3++) {
					for(int i4=0;i4<stenen.length;i4++) {
						if((stenen[i1].waarde-stenen[i2].waarde==1) && (stenen[i2].waarde-stenen[i3].waarde==1) && (stenen[i3].waarde-stenen[i4].waarde==1)) {
							counter++;
						}
					}
				}
			}
		}
		if (counter>1) {
			spelerScores[9]=30;
		} 
		System.out.println("Je punten zijn bij je Kleine Straat genoteerd!");
	}	
	
	void checkGroteStraat(Dobbelsteen[] stenen) {
		spelerScores[10]=0;
		int counter=0;
		for(int i1=0;i1<stenen.length;i1++) {
			for(int i2=0;i2<stenen.length;i2++) {
				for(int i3=0;i3<stenen.length;i3++) {
					for(int i4=0;i4<stenen.length;i4++) {
						for(int i5=0;i5<stenen.length;i5++) {
							if((stenen[i1].waarde-stenen[i2].waarde==1) && (stenen[i2].waarde-stenen[i3].waarde==1) && (stenen[i3].waarde-stenen[i4].waarde==1) && (stenen[i4].waarde-stenen[i5].waarde==1)) {
								counter++;
							}
						}
					}
				}
			}
		}
		if (counter==1) {
			spelerScores[10]=40;
		}
		System.out.println("Je punten zijn bij je Grote Straat genoteerd!");
	}	
	
	void checkYahtzee(Dobbelsteen[] stenen) {
		spelerScores[11]=0;
		if((stenen[0].waarde==stenen[1].waarde) && (stenen[1].waarde==stenen[2].waarde) && (stenen[2].waarde==stenen[3].waarde) && (stenen[3].waarde==stenen[4].waarde)) {
			spelerScores[11]=50;
		} 
		System.out.println("Je punten zijn bij Yahtzee genoteerd!");
	}	
	
	void checkChance(Dobbelsteen[] stenen) {
		spelerScores[12]=0;
		for(Dobbelsteen d:stenen) {
			spelerScores[12]+=d.waarde;
		}
	}
	
	void arraysBijwerken() {
		enen=spelerScores[0];
		tweeën=spelerScores[1];
		drieën=spelerScores[2];
		vieren=spelerScores[3];
		vijven=spelerScores[4];
		zessen=spelerScores[5];
		threeOfAKind=spelerScores[6];
		fourOfAKind=spelerScores[7];
		fullHouse=spelerScores[8];
		kleineStraat=spelerScores[9];
		groteStraat=spelerScores[10];
		yahtzee=spelerScores[11];
		chance=spelerScores[12];
		
		scores[0]=enen;
		scores[1]=tweeën;
		scores[2]=drieën;
		scores[3]=vieren;
		scores[4]=vijven;
		scores[5]=zessen;
		scores[6]=bovenblad;
		scores[7]=bonus;
		scores[8]=bovenbladinclBonus;
		scores[9]=threeOfAKind;
		scores[10]=fourOfAKind;
		scores[11]=fullHouse;
		scores[12]=kleineStraat;
		scores[13]=groteStraat;
		scores[14]=yahtzee;
		scores[15]=chance;
		scores[16]=benedenblad;
		scores[17]=totaalTotaal;
	}
	
	void berekenTotaal() {
		arraysBijwerken();
		bovenblad=enen+tweeën+drieën+vieren+vijven+zessen;
		if(bovenblad>62) {
			bonus=35;
		} else {
			bonus=0;
		}
		bovenbladinclBonus=bovenblad+bonus;
		benedenblad = threeOfAKind+fourOfAKind+fullHouse+kleineStraat+groteStraat+yahtzee+chance;
		totaalTotaal=bovenbladinclBonus+benedenblad;
		arraysBijwerken();
	}
}
