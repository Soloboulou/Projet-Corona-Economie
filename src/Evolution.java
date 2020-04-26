
public class Evolution {
 //on fait la version tout le monde à 1, des liens établis
	public static double[] evolve(Entreprise[] ini) {
		int N = ini.length ;
		double[] R = new double[N] ;
		for (int i=0;i<N;i++) { 
			R[i]=calcul(ini,i);
		}
		return R ;
	}
	
	public static double calcul(Entreprise[] ini, int i) { //pas de 0.1
		double x = ini[i].santefin ;
		if (x==0) {return x ;}
		Reseau R = ini[i].reseau ;
		for (int j=0; j<R.Nc;j++) {
			if (ini[R.clients[j]-1].santefin == 0) {
				x+= (-0.1)*R.poidsc[j] ;
			}
		}
		for (int j=0; j<R.Nf;j++) {
			if (ini[R.fournisseurs[j]-1].santefin == 0) {
				x+= (-0.1)*R.poidsf[j] ;
			}
		}
		if(x<0) {return 0 ;}
		return x ;
	}
	
	
}

	
	
	

