public class Controller {
    private View view;

    public Controller(View view) {
        this.view = view;
    }
    public void AddPolynomials() {
        view.setResult(Operations.toPolynomial(view.getP1()).toString());
    }
}
