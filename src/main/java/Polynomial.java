import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class Polynomial {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private final List<Monomial> monomialList;

    public Polynomial() {
        this.monomialList = new LinkedList<>();
    }

    public Polynomial(List<Monomial> monomialList) {
        this.monomialList = new LinkedList<>();
        for (Monomial monomial :
                monomialList) {
            addMonomial(new Monomial(monomial));
        }
    }

    public List<Monomial> getMonomialList() {
        return monomialList;
    }

    public void addMonomial(Monomial newMonomial) {
        if (newMonomial.getCoef() == 0)
            return;

        Monomial temp = new Monomial(newMonomial);
        if (monomialList.size() == 0) {
            monomialList.add(temp);
            return;
        }
        for (Monomial monomial : monomialList) {
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
        for (Monomial mon : monomialList) {
            if (mon.getCoef() == Math.rint(mon.getCoef())) //Check if the coefficient is an integer, so we won't print Y.0 only Y
                buff += (mon.getCoef() < 0 ? "" : "+") + (int) mon.getCoef();
            else
                buff += (mon.getCoef() < 0 ? "" : "+") + df.format(mon.getCoef());

            if (mon.getPow() == 1) // Getting rid of "aX^0" and "aX^1", replacing them with the more natural "a" and "aX"
                buff += "X";
            else if (mon.getPow() > 1)
                buff += "X^" + mon.getPow();
        }
        buff = buff.replace("-1X","-X").replace("+1X","+X");
        return buff;
    }
}