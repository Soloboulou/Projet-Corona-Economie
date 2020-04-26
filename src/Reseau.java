
public class Reseau {
	int[] fournisseurs; //les fournisseurs
	double[] poidsf ; //les ratio dans l'approvisionnement
	int Nf ;
	int[] clients; //les clients
	double[] poidsc ; //les ratios dans les ventes
	int Nc ;
	final public static int M = 50 ; //le nombre max de clients/fournisseurs, à optimiser, ça nique de la mémoire
	
	
	public Reseau() {
		fournisseurs = new int[M];
		poidsf = new double[M] ;
		clients = new int[M];
		poidsc = new double[M] ;
		Nf = 0;
		Nc = 0;
	}
}
