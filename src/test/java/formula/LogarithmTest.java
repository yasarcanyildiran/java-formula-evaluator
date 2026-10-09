package formula;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class LogarithmTest {

  private static final Double EPSILON = 1e-6;

  @Test
  void testEval() {
    Formula f = new Logarithm(new Variable());
    assertThat(f.eval(1)).isCloseTo(0, within(EPSILON));
    assertThat(f.eval(Math.E)).isCloseTo(1, within(EPSILON));
    assertThat(f.eval(-1)).isNaN();
  }

  @Test
  void testDerivative() {
    Formula f = new Logarithm(new Variable());
    assertThat(f.derivative().eval(1)).isCloseTo(1, within(EPSILON));
    assertThat(f.derivative().eval(4)).isCloseTo(0.25, within(EPSILON));
    assertThat(f.derivative()).isEqualTo(new Multiplication(
        new Division(new Constant(1), new Variable()), new Constant(1)));
  }

  @Test
  void testToString() {
    Formula f = new Logarithm(new Variable());
    assertThat(f.toString()).isEqualTo("ln(x)");
    Formula g = new Logarithm(new Exponential(new Variable()));
    assertThat(g.toString()).isEqualTo("ln(exp(x))");
  }

  @Test
  void testEquals() {
    Formula f = new Logarithm(new Variable());
    Formula g = new Logarithm(new Variable());
    Formula h = new Logarithm(new Constant(1));
    Formula i = new Exponential(new Variable());
    assertThat(f).isEqualTo(g);
    assertThat(f).isNotEqualTo(h);
    assertThat(f).isNotEqualTo(i);
  }
}
