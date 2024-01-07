public class Terrain {
    private boolean estInnondee = false;
    private int nbrTourInonder = 0;
    private Monster lanceur = null;

    public boolean isEstInnondee() {
        return estInnondee;
    }

    public void setEstInnondee(boolean estInnondee) {
        this.estInnondee = estInnondee;
    }

    public int getNbrTourInonder() {
        return nbrTourInonder;
    }

    public void setNbrTourInonder(int nbrTourInonder) {
        this.nbrTourInonder = nbrTourInonder;
    }

    public Monster getLanceur() {
        return lanceur;
    }

    public void setLanceur(Monster lanceur) {
        this.lanceur = lanceur;
    }
}
