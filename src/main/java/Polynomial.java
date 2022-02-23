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
    public int getDegree() {
        try {
            return monomialList.get(0).getPow(); // the monomials are sorted from biggest to lowest,
            // so the degree of the polynomial is the first power in the list
        } catch (Exception e) {
            return -1; // the Exception is going to be thrown only when the list is empty,
            // so we have no monomial and we return -1
        }
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
        p1.addMonomial(new Monomial(2,0));
        p1.addMonomial(new Monomial(1,4));
        p1.addMonomial(new Monomial(6,2));

        Polynomial p2 = new Polynomial();
        p2.addMonomial(new Monomial(6,0));
        p2.addMonomial(new Monomial(1,2));

        System.out.println(p1);
        System.out.println(p2);
//        System.out.println("SUM:" + addPolynomials(p1,p2));
//        System.out.println("SUB:" + subPolynomials(p1,p2));
//        System.out.println("MUL:" + mulPolynomials(p1,p2));
        divPolynomials(p1,p2);


//        Monomial a = new Monomial(5,3);
//        Monomial b = new Monomial(2,2);
//        System.out.println(a + "*" + b + "=" + Monomial.mul(a,b));

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
    private static Polynomial mulPolynomialMonomial(Polynomial p, Monomial m) {
        Polynomial result = new Polynomial();
        for (Monomial monomial :
                p.getMonomialList()) {
            result.addMonomial(new Monomial(
                    m.getCoef() * monomial.getCoef(),
                    m.getPow() + monomial.getPow()
            ));
        }
        return result;
    }
    public static Polynomial divPolynomials(Polynomial p1, Polynomial p2) {
        if (p2.getDegree() == -1) //Means that we have no monomials in p2
            return null;

        //P(X) = B(X)*Q(X) + R(X)
        Polynomial P = new Polynomial(p1.getMonomialList()); // DIVIDEND
        Polynomial B = new Polynomial(p2.getMonomialList()); // DIVISOR
        Polynomial Q = new Polynomial(); // QUOTIENT
//        Polynomial R = new Polynomial(); // REMAINDER

        while (P.getDegree() > B.getDegree()) {
            Monomial q = new Monomial(
                    P.monomialList.get(0).getCoef() / B.monomialList.get(0).getCoef(),
                    P.monomialList.get(0).getPow() - B.monomialList.get(0).getPow()
            );
            Q.addMonomial(q);
            P = subPolynomials(P,mulPolynomialMonomial(B,q));
        }
        return Q;
    }
}