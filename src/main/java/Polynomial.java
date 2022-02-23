import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    private List<Monomial> monomialList;

    public Polynomial() {
        this.monomialList = new ArrayList<Monomial>();
    }
    public Polynomial(List<Monomial> monomialList) {
        this.monomialList = new ArrayList<Monomial>();
        for (Monomial monomial :
                monomialList) {
            addMonomial(new Monomial(monomial));
        }
    }
    public List<Monomial> getMonomialList() {
        return monomialList;
    }
    public void addMonomial (Monomial newMonomial) {
        Monomial temp = new Monomial(newMonomial);
        if (monomialList.size() == 0) {
            monomialList.add(temp);
            return;
        }
        for (Monomial monomial: monomialList) {
            if (temp.getPow() > monomial.getPow()) {
                monomialList.add(monomialList.indexOf(monomial), temp);
                return;
            }
            if (temp.getPow() == monomial.getPow()) {
                if ((monomial.getCoef() + temp.getCoef()) != 0)
                    monomial.setCoef(monomial.getCoef() + temp.getCoef());
                else
                    monomialList.remove(monomial);
                return;
            }
        }
        monomialList.add(temp);
    }

    @Override
    public String toString() {
        String buff = "";
        for (Monomial mon: monomialList) {
            buff += (mon.getCoef() < 0 ? " " : " +") + (int)mon.getCoef() + "X^" + mon.getPow();
        }
        return buff;
    }

    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        p1.addMonomial(new Monomial(3,3));
        p1.addMonomial(new Monomial(4,4));
        p1.addMonomial(new Monomial(3,2));

        Polynomial p2 = new Polynomial();
        p2.addMonomial(new Monomial(3,3));
        p2.addMonomial(new Monomial(4,4));
        p2.addMonomial(new Monomial(3,2));

        System.out.println(p1);
        System.out.println(p2);
        System.out.println("SUM:" + addPolynomials(p1,p2));
        System.out.println("SUB:" + subPolynomials(p1,p2));
        System.out.println("MUL:" + mulPolynomials(p1,p2));


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
    public static Polynomial subPolynomials(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial:
                p1.getMonomialList()) {
            result.addMonomial(monomial);
        }
        for (Monomial monomial:
                p2.getMonomialList()) {
            Monomial temp = new Monomial(
                    monomial.getCoef() * -1,
                    monomial.getPow()
            );
            result.addMonomial(temp);
        }
        return result;
    }
    public static Polynomial mulPolynomials(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial1 :
                p1.getMonomialList()) {
            for (Monomial monomial2 :
                    p2.getMonomialList()) {
                result.addMonomial(new Monomial(
                        monomial1.getCoef() * monomial2.getCoef(),
                        monomial1.getPow() + monomial2.getPow()
                ));
            }
        }
        return result;
    }
    public static Polynomial divPolynomials() {
        return null;
    }
}