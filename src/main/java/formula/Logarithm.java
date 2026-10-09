package formula;

public final class Logarithm extends UnaryOperation {

  public Logarithm(Formula operand) {
    super(operand);
  }

  @Override
  protected double operate(double operandValue) {
    return Math.log(operandValue);
  }

  @Override
  protected String name() {
    return "ln";
  }

  @Override
  public Formula derivative() {
    return chainRule(new Division(new Constant(1), operand));
  }
}
