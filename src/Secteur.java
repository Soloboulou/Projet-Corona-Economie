
public class Secteur {
	public String id ;  //nom du secteur
	public Entreprise[] membres ; //liste d'entreprise membres
	public int N ; //nombre d'entreprises dans le secteur
	final public static int M = 100 ; //nombre max d'entreprise par secteur
	
	
 	public Secteur(String nom) {
 		this.id = nom ;
 		this.membres = new Entreprise[M] ;
 		this.N = 0 ;
 	}
 	
 	public void ajouterm(Entreprise a) {
 		if (this.N ==M -1) {
 			System.out.print( "Secteur complet" + this.id) ;
 		}
 		this.membres[this.N]= a ;
 		this.N += 1 ;
 	}
 	
 	public Secteur(String nom, int debutordre, int nbentreprise) {
 		this.id = nom ;
 		this.membres = new Entreprise[M] ;
 		this.N = 0 ;
 		for (int i=0;i<nbentreprise;i++) {
 			this.ajouterm(new Entreprise(debutordre+ i));
 		}
 	}
}
