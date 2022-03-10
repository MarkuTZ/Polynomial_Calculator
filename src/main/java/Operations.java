import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Operations {
    static Polynomial addPolynomials(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial :
                p1.getMonomialList()) {
            result.addMonomial(monomial);
        }
        for (Monomial monomial :
                p2.getMonomialList()) {
            result.addMonomial(monomial);
        }
        return result;
    }

    static Polynomial subPolynomials(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Monomial monomial :
                p1.getMonomialList()) {
            result.addMonomial(monomial);
        }
        for (Monomial monomial :
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

        //P(X) = B(X)*Q(X) + R(X)
        Polynomial P = new Polynomial(p1.getMonomialList()); // DIVIDEND
        Polynomial B = new Polynomial(p2.getMonomialList()); // DIVISOR
        Polynomial Q = new Polynomial(); // QUOTIENT
        LinkedList<Polynomial> pairOfPolynomials = new LinkedList<>();

        while (P.getDegree() >= B.getDegree()) {
            Monomial q = new Monomial(
                    P.getMonomialList().get(0).getCoef() / B.getMonomialList().get(0).getCoef(),
                    P.getMonomialList().get(0).getPow() - B.getMonomialList().get(0).getPow()
            );
            Q.addMonomial(q);
            P = subPolynomials(P, mulPolynomialMonomial(B, q));
        }
        pairOfPolynomials.add(Q);
        pairOfPolynomials.add(P);
        return pairOfPolynomials;
    }

    static Polynomial derPolynomial(Polynomial p) {
        Polynomial result = new Polynomial();
        for (Monomial m :
                p.getMonomialList()) {
            result.addMonomial(new Monomial(
                    m.getCoef() * m.getPow(),
                    m.getPow() - 1));
        }
        return result;
    }

    static Polynomial intPolynomial(Polynomial p) {
        Polynomial result = new Polynomial();
        for (Monomial m :
                p.getMonomialList()) {
            result.addMonomial(new Monomial(
                   m.getCoef() / (m.getPow() + 1),
                   m.getPow() + 1));
        }
        return result;
    }

    static Polynomial toPolynomial(String input) {
        Polynomial newP = new Polynomial();

        Pattern p = Pattern.compile("([-+]?[0-9]*\\.?[0-9]*)x(\\^(\\d+))?|([-+]?[0-9]*\\.?[0-9]+)");
        input = input.replace(" ", "").toLowerCase().replace(",",".");
        Matcher matcher = p.matcher(input);

        while (matcher.find()) {
            if (matcher.group(4) == null)
                if (matcher.group(3) == null)
                    newP.addMonomial(new Monomial(
                            (matcher.group(1).equals("") || matcher.group(1).equals("+")) ? 1 : matcher.group(1).equals("-") ? -1 : Double.parseDouble(matcher.group(1)),
                            1));
                else
                    newP.addMonomial(new Monomial(
                            (matcher.group(1).equals("") || matcher.group(1).equals("+")) ? 1 : matcher.group(1).equals("-") ? -1 : Double.parseDouble(matcher.group(1)),
                            Integer.parseInt(matcher.group(3))));
            else
                newP.addMonomial(new Monomial(
                        Double.parseDouble(matcher.group(4)),
                        0));
        }

        return newP;
    }
}
