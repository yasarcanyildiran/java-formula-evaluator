package formula;

public final class Division extends BinaryOperation {

  public Division(Formula left, Formula right) {
    super(left, right);
  }

  @Override
  protected double operate(double leftValue, double rightValue) {
    return leftValue / rightValue;
  }

  @Override
  protected String symbol() {
    return "/";
  }

  @Override
  public Formula derivative() {
    return new Division(
        new Subtraction(
            new Multiplication(left.derivative(), right),
            new Multiplication(left, right.derivative())),
        new Multiplication(right, right));
  }
}
