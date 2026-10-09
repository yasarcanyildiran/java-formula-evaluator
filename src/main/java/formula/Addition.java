package formula;

public final class Addition extends BinaryOperation {

  public Addition(Formula left, Formula right) {
    super(left, right);
  }

  @Override
  protected double operate(double leftValue, double rightValue) {
    return leftValue + rightValue;
  }

  @Override
  protected String symbol() {
    return "+";
  }

  @Override
  public Formula derivative() {
    return new Addition(left.derivative(), right.derivative());
  }
}
