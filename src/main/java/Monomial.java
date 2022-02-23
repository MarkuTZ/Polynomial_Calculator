public class Monomial {
    private double coef;
    private int pow;

    public Monomial(double coef, int pow) {
        this.coef = coef;
        this.pow = pow;
    }
    public Monomial(Monomial copy) {
        this.coef = copy.getCoef();
        this.pow = copy.getPow();
    }

    public int getPow() {
        return pow;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }
}
