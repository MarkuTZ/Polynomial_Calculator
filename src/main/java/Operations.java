import java.util.LinkedList;
import java.util.regex.Pattern;

public interface Operations {
    static Polynomial addPolynomials(Polynomial p1, Polynomial p2) {
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
    static Polynomial subPolynomials(Polynomial p1, Polynomial p2) {
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
    static Polynomial mulPolynomials(Polynomial p1, Polynomial p2) {
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
    static LinkedList<Polynomial> divPolynomials(Polynomial p1, Polynomial p2) {
        if (p2.getDegree() == -1) //Means that we have no monomials in p2
            return null;

        //P(X) = B(X)*Q(X) + R(X)
        Polynomial P = new Polynomial(p1.getMonomialList()); // DIVIDEND
        Polynomial B = new Polynomial(p2.getMonomialList()); // DIVISOR
        Polynomial Q = new Polynomial(); // QUOTIENT

        while (P.getDegree() > B.getDegree()) {
            Monomial q = new Monomial(
                    P.getMonomialList().get(0).getCoef() / B.getMonomialList().get(0).getCoef(),
                    P.getMonomialList().get(0).getPow() - B.getMonomialList().get(0).getPow()
            );
            Q.addMonomial(q);
            P = subPolynomials(P,mulPolynomialMonomial(B,q));
        }
        LinkedList<Polynomial> pairOfPolynomials = new LinkedList<>();
        pairOfPolynomials.add(Q);
        pairOfPolynomials.add(P);
        return pairOfPolynomials;
    }
    static Polynomial toPolynomial (String input) {
        Polynomial newP = new Polynomial();

        input = input.replace("-","+-").toLowerCase();
        input = input.replace("-x","-1x");
        input = input.replace("+x","+1x");
        String[] mon = input.split(Pattern.quote("+"));

        for (String m :
                mon) {
            String[] coefAndPow = m.split("x");
            if (m.contains("x")) {
                if (coefAndPow.length == 1) {
                    // coef * X ==> (coef, 1)
                    newP.addMonomial(new Monomial(Double.parseDouble(coefAndPow[0]),1));
                } else {
                    newP.addMonomial(new Monomial(Double.parseDouble(coefAndPow[0]),Integer.parseInt(coefAndPow[1].substring(1))));
                }
            } else { // Free term
                newP.addMonomial(new Monomial(Double.parseDouble(coefAndPow[0]), 0));
            }
        }

        return newP;
    }
}
