package formula;

public final class Subtraction extends BinaryOperation {

  public Subtraction(Formula left, Formula right) {
    super(left, right);
  }

  @Override
  protected double operate(double leftValue, double rightValue) {
    return leftValue - rightValue;
  }

  @Override
  protected String symbol() {
    return "-";
  }

  @Override
  public Formula derivative() {
    return new Subtraction(left.derivative(), right.derivative());
  }
}
