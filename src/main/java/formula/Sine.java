package formula;

public final class Sine extends UnaryOperation {

  public Sine(Formula operand) {
    super(operand);
  }

  @Override
  protected double operate(double operandValue) {
    return Math.sin(operandValue);
  }

  @Override
  protected String name() {
    return "sin";
  }

  @Override
  public Formula derivative() {
    return chainRule(new Cosine(operand));
  }
}
