package formula;

public final class Exponential extends UnaryOperation {

  public Exponential(Formula operand) {
    super(operand);
  }

  @Override
  protected double operate(double operandValue) {
    return Math.exp(operandValue);
  }

  @Override
  protected String name() {
    return "exp";
  }

  @Override
  public Formula derivative() {
    return chainRule(this);
  }
}
