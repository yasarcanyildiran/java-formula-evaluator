package formula;

public final class Opposite extends UnaryOperation {

  public Opposite(Formula operand) {
    super(operand);
  }

  @Override
  protected double operate(double operandValue) {
    return -operandValue;
  }

  @Override
  protected String name() {
    return "-";
  }

  @Override
  public Formula derivative() {
    return new Opposite(operand.derivative());
  }
}
