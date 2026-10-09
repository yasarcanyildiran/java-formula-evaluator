package formula;

import java.util.Objects;

public sealed abstract class UnaryOperation implements Formula
  permits Opposite, Cosine, Sine, Exponential, Logarithm
{
  protected final Formula operand;

  protected UnaryOperation(Formula operand) {
    this.operand = operand;
  }

  /**
   * Apply the operation to the value of the operand.
   *
   * @param operandValue the value of the operand
   * @return the value of the operation applied to the operand
   */
  protected abstract double operate(double operandValue);

  /**
   * @return the name of the operation, as used by {@code toString}
   */
  protected abstract String name();

  /**
   * Chain rule: the derivative of u(f) is u'(f) * f'.
   *
   * @param outerDerivative the formula of u'(f)
   * @return the formula of the derivative of this operation
   */
  protected Formula chainRule(Formula outerDerivative) {
    return new Multiplication(outerDerivative, operand.derivative());
  }

  @Override
  public double eval(double xValue) {
    return operate(operand.eval(xValue));
  }

  @Override
  public String toString() {
    return name() + "(" + operand + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) return false;
    UnaryOperation other = (UnaryOperation) obj;
    return operand.equals(other.operand);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getClass(), operand);
  }
}
