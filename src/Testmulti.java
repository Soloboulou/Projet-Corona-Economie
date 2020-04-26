
public class Testmulti {
	public static void main(String[] args) {
		Univers u = new Univers(3) ;
		String[] L = {"pain","farine","humain"}; //introduisons une règle du genre "la faillite des humains est exogène"
		int[] effectifs = {59,60,61} ;
		for(int i = 0; i<3;i++) {u.ajoutersect(new Secteur(L[i],u.coordonneesect[i],effectifs[i]));}
		u.creerlien(2, 1, 1, 0.5);
		u.creerlien(3, 2, 0.5, 1);
		u.creerlien(1,3,1,1);
		u.creerlien(3,1,0.5,0.5);
		u.secteurs[0].membres[0].etat = false ; //perturbation
		u.secteurs[0].membres[0].santefin = 0 ; //perturbation
		u.initialiservect();
		u.evolutotale(105);
	}
}
