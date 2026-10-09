package formula;

import java.util.Objects;

public sealed abstract class BinaryOperation implements Formula
  permits Addition, Subtraction, Multiplication, Division
{
  protected final Formula left;
  protected final Formula right;

  protected BinaryOperation(Formula left, Formula right) {
    this.left = left;
    this.right = right;
  }

  /**
   * Combine the values of the two operands.
   *
   * @param leftValue the value of the left operand
   * @param rightValue the value of the right operand
   * @return the value of the operation applied to the two operands
   */
  protected abstract double operate(double leftValue, double rightValue);

  /**
   * @return the symbol of the operation, as used by {@code toString}
   */
  protected abstract String symbol();

  @Override
  public double eval(double xValue) {
    return operate(left.eval(xValue), right.eval(xValue));
  }

  @Override
  public String toString() {
    return "(" + left + " " + symbol() + " " + right + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) return false;
    BinaryOperation other = (BinaryOperation) obj;
    return left.equals(other.left) && right.equals(other.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getClass(), left, right);
  }
}
