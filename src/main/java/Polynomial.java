import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private List<Monomial> monomialList;

    public Polynomial() {
        this.monomialList = new ArrayList<Monomial>();
    }
    public Polynomial(List<Monomial> monomialList) {
        this.monomialList = monomialList;
    }
    public List<Monomial> getMonomialList() {
        return monomialList;
    }
    public void addMonomial (Monomial newMonomial) {
        if (monomialList.size() == 0) {
            monomialList.add(newMonomial);
            return;
        }
        for (Monomial monomial: monomialList) {
            if (newMonomial.getPow() > monomial.getPow()) {
                monomialList.add(monomialList.indexOf(monomial), newMonomial);
                return;
            }
            if (newMonomial.getPow() == monomial.getPow()) {
                if ((monomial.getCoef() + newMonomial.getCoef()) != 0)
                    monomial.setCoef(monomial.getCoef() + newMonomial.getCoef());
                else
                    monomialList.remove(monomial);
                return;
            }
        }
        monomialList.add(newMonomial);
    }
    @Override
    public String toString() {
        String buff = "Polynomial = ";
        for (Monomial mon: monomialList) {
            buff += (mon.getCoef() < 0 ? " " : " +") + mon.getCoef() + "X^" + mon.getPow();
        }
        return buff;
    }
    public static Polynomial addPolynomials(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial:
                p1.getMonomialList()) {
            result.addMonomial(monomial);
        }
        for (Monomial monomial:
                p2.getMonomialList()) {
            result.addMonomial(monomial);
        }
        return result;
    }

    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(2,3));
        p.addMonomial(new Monomial(3,4));
        p.addMonomial(new Monomial(5,2));

        Polynomial p2 = new Polynomial();
        p2.addMonomial(new Monomial(3,3));
        p2.addMonomial(new Monomial(-3,4));
        p2.addMonomial(new Monomial(-2,2));

        System.out.println(p);
        System.out.println(p2);
        System.out.println(addPolynomials(p,p2));

    }
}
