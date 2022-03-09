import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.Assert.assertTrue;

class OperationsTest {

    @Test
    void addPolynomials() {
        Polynomial p1 = Operations.toPolynomial("x^2+2x+1");
        Polynomial p2 = Operations.toPolynomial("-x^2+2x+1");
        Polynomial sum = Operations.toPolynomial("4x+2");
        assertTrue(sum.toString().equals(Operations.addPolynomials(p1, p2).toString()));
    }

    @Test
    void subPolynomials() {
        Polynomial p1 = Operations.toPolynomial("x^2+2x+1");
        Polynomial p2 = Operations.toPolynomial("-x^2+2x+1");
        Polynomial sub = Operations.toPolynomial("2x^2");
        assertTrue(sub.toString().equals(Operations.subPolynomials(p1, p2).toString()));
    }

    @Test
    void mulPolynomials() {
        Polynomial p1 = Operations.toPolynomial("x+1");
        Polynomial p2 = Operations.toPolynomial("x-1");
        Polynomial mul = Operations.toPolynomial("x^2-1");
        assertTrue(mul.toString().equals(Operations.mulPolynomials(p1, p2).toString()));
    }

    @Test
    void divPolynomials() {
        Polynomial p1 = Operations.toPolynomial("4x^2+x+2");
        Polynomial p2 = Operations.toPolynomial("x^2");
        Polynomial Q = Operations.toPolynomial("4");
        Polynomial R = Operations.toPolynomial("x+2");
        assertTrue(Q.toString().equals(Operations.divPolynomials(p1, p2).getFirst().toString()) &&
                R.toString().equals(Operations.divPolynomials(p1, p2).getLast().toString()));
    }

    @Test
    void derPolynomial() {
        Polynomial p1 = Operations.toPolynomial("3x^3+2x^2+x+1");
        Polynomial der = Operations.toPolynomial("9x^2+4x+1");
        assertTrue(der.toString().equals(Operations.derPolynomial(p1).toString()));
    }

    @Test
    void intPolynomial() {
        Polynomial p1 = Operations.toPolynomial("9x^2+4x+1");
        Polynomial integral = Operations.toPolynomial("3x^3+2x^2+x");
        assertTrue(integral.toString().equals(Operations.intPolynomial(p1).toString()));
    }

    @Test
    void toPolynomial() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(2,2));
        p.addMonomial(new Monomial(3,3));
        p.addMonomial(new Monomial(4,0));
        Polynomial toVerify = Operations.toPolynomial("4+3x^3+2x^2");
        assertTrue(p.toString().equals(toVerify.toString()));
    }
}