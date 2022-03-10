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

    public static Monomial mul(Monomial a, Monomial b) {
        return new Monomial(
                a.getCoef() * b.getCoef(),
                a.getPow() + b.getPow()
        );
    }

    public int getPow() {
        return pow;
    }

    public void setPow(int pow) {
        this.pow = pow;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    @Override
    public String toString() {
        return coef + "X^" + pow;
    }
}
