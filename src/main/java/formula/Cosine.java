package formula;

public final class Cosine extends UnaryOperation {

  public Cosine(Formula operand) {
    super(operand);
  }

  @Override
  protected double operate(double operandValue) {
    return Math.cos(operandValue);
  }

  @Override
  protected String name() {
    return "cos";
  }

  @Override
  public Formula derivative() {
    return chainRule(new Opposite(new Sine(operand)));
  }
}
