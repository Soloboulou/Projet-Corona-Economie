
public class Entreprise {
	public Reseau reseau ;
	public int ordre ; //ordre dans la liste de toutes les entreprises,
	public boolean etat ; //true c'est en vie false , c'est faillite
	public double santefin ; //0 c'est la faillite
	
	public Entreprise( int ordre) {
		reseau = new Reseau() ;
		this.ordre = ordre ;
		etat = true ;
		santefin = 1 ; //pour l'instant
	}
	
	public void creerlienc(Entreprise c, double p) {
		this.reseau.clients[this.reseau.Nc]= c.ordre ;
		this.reseau.poidsc[this.reseau.Nc] = p ;
		this.reseau.Nc+=1 ;	
	}
	public void creerlienf(Entreprise f, double p) {
		this.reseau.fournisseurs[this.reseau.Nf]= f.ordre ;
		this.reseau.poidsf[this.reseau.Nf] = p ;
		this.reseau.Nf+=1 ;
		
	}
}
