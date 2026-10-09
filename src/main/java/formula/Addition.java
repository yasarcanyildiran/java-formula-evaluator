package formula;

public record Addition(Formula left, Formula right) implements Formula {

  @Override
  public double eval(double xValue) {
    return left.eval(xValue) + right.eval(xValue);
  }

  @Override
  public Formula derivative() {
    return new Addition(left.derivative(), right.derivative());
  }

  @Override
  public String toString() {
    return "(" + left + " + " + right + ")";
  }
}
