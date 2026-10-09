package formula;

public final class Multiplication extends BinaryOperation {

  public Multiplication(Formula left, Formula right) {
    super(left, right);
  }

  @Override
  protected double operate(double leftValue, double rightValue) {
    return leftValue * rightValue;
  }

  @Override
  protected String symbol() {
    return "*";
  }

  @Override
  public Formula derivative() {
    return new Addition(
        new Multiplication(left.derivative(), right),
        new Multiplication(left, right.derivative()));
  }
}
