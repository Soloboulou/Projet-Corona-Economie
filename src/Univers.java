
public class Univers {
	//on construit l'univers à l'aide des secteurs, on initialise alors un gros vecteur désordonné d'entreprises pour l'évolution
	// on reconstruit les secteurs à la fin pour l'analyse (pas besoin de mettre à jour)
	
	public int M ; //le nombre de secteurs possibles
	public Secteur[] secteurs ;
	public int S ;//nombre de secteurs réel
	public int[] coordonneesect ;  //permet d'ailleurs d'avoir accès au nombre d'entreprise déjà créées
	public Entreprise[] vecteurentre ;
	
	public Univers(int nbsect)  {
		M = nbsect ;
		secteurs = new Secteur[M] ;
		S = 0 ;
		coordonneesect = new int[M+1] ;
		coordonneesect[0] = 1 ;
	}
	
	public void ajoutersect(Secteur s) {
		if (S==this.M) {System.out.print("erreur trop de secteurs");}
		this.secteurs[S] = s ;
		S+=1 ;
		coordonneesect[S]= coordonneesect[S-1] + s.N ;
	}
	
	public void creerlien(int sectp, int secta, double ipr, double iac) { //entre 1 et M les int, on crée deux liens
		iac = iac/2.0 ; //on crée 2 liens
		ipr = ipr/2.0 ; //on crée 2 liens
		int q ;  //support du hachage
		if (this.secteurs[sectp-1].N > this.secteurs[secta-1].N) {  //on se casse pas le cul version facile : on remplit
			for (int i= 0; i<this.secteurs[sectp-1].N ; i++) { //le producteur lié au client l'acheteur lié au fournisseur
				q = melange1(this.secteurs[secta-1].N,i) ;
				this.secteurs[sectp-1].membres[i].creerlienc(this.secteurs[secta-1].membres[q], ipr);
				this.secteurs[secta-1].membres[q].creerlienf(this.secteurs[sectp-1].membres[i], iac);
				q = melange2(this.secteurs[secta-1].N,i) ;
				this.secteurs[sectp-1].membres[i].creerlienc(this.secteurs[secta-1].membres[q], ipr);
				this.secteurs[secta-1].membres[q].creerlienf(this.secteurs[sectp-1].membres[i], iac);
			}
		}
		else { 
			for (int i= 0; i<this.secteurs[secta-1].N ; i++) {
				q = melange1(this.secteurs[sectp-1].N,i) ;
				this.secteurs[sectp-1].membres[q].creerlienc(this.secteurs[secta-1].membres[i], ipr);
				this.secteurs[secta-1].membres[i].creerlienf(this.secteurs[sectp-1].membres[q], iac);
				q = melange2(this.secteurs[sectp-1].N,i) ;
				this.secteurs[sectp-1].membres[q].creerlienc(this.secteurs[secta-1].membres[i], ipr);
				this.secteurs[secta-1].membres[i].creerlienf(this.secteurs[sectp-1].membres[q], iac);
			}
		}
	}
	
	public int melange1 (int petit, int grand) { //grand varie ,pas  le petit
		return ((grand+1)*(grand+1))%petit ;
	}
	
	public int melange2 (int petit, int grand) { //grand varie ,pas  le petit
		return (grand*grand*grand)%petit ;
	}
	
	public void initialiservect() {
		this.vecteurentre = new Entreprise[this.coordonneesect[this.S]-1] ;
		for (int i = 0; i< this.S ; i++) {
			for (int j = 0 ; j< this.secteurs[i].N ;j++) {
				vecteurentre[this.coordonneesect[i]+j-1] = this.secteurs[i].membres[j] ;
			}
		}
	}
	
	public void resume() { //validé
		double x ; //proportion de faillites
		double p ; //santé des restants
		System.out.println("résumé des faillites") ;
		for(int i = 0; i<this.S;i++) {
			x = 0 ;
			p = 0;
			for(int j=0;j<this.secteurs[i].N;j++) {
				if(this.vecteurentre[this.coordonneesect[i]+j-1].etat==true) {
					x+=1;
					p+=this.vecteurentre[this.coordonneesect[i]+j-1].santefin ;
				}
			}
			p = p/x ;
			x = x/this.secteurs[i].N ;
			x = 100*(1-x) ;
			System.out.println(this.secteurs[i].id+"   taux de faillite:  "+x+ "%    sante moyenne des restants:  "+p) ;
		}
	}
	
	public void evolutotale(int n) {
		for (int i=0;i<n;i++) {this.evolve();}	
		System.out.println("apres "+n+" coups:") ;
		this.resume();
	}
	
	public void evolve() {
		double[] R = Evolution.evolve(this.vecteurentre) ;
		for (int i = 0; i<this.vecteurentre.length;i++) {
			if (this.vecteurentre[i].etat== true) {
				this.vecteurentre[i].santefin = R[i] ;
				if (R[i]==0) {this.vecteurentre[i].etat = false ;}
			}
		}
	}
}
