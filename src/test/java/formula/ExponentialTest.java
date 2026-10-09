package formula;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class ExponentialTest {

  private static final Double EPSILON = 1e-6;

  @Test
  void testEval() {
    Formula f = new Exponential(new Variable());
    assertThat(f.eval(0)).isCloseTo(1, within(EPSILON));
    assertThat(f.eval(1)).isCloseTo(Math.E, within(EPSILON));
    assertThat(f.eval(-1)).isCloseTo(1 / Math.E, within(EPSILON));
  }

  @Test
  void testDerivative() {
    Formula f = new Exponential(new Variable());
    assertThat(f.derivative().eval(0)).isCloseTo(1, within(EPSILON));
    assertThat(f.derivative().eval(1)).isCloseTo(Math.E, within(EPSILON));
    assertThat(f.derivative()).isEqualTo(
        new Multiplication(new Exponential(new Variable()), new Constant(1)));

    Formula g = new Exponential(new Multiplication(new Constant(3), new Variable()));
    assertThat(g.derivative().eval(0)).isCloseTo(3, within(EPSILON));
  }

  @Test
  void testToString() {
    Formula f = new Exponential(new Variable());
    assertThat(f.toString()).isEqualTo("exp(x)");
  }

  @Test
  void testEquals() {
    Formula f = new Exponential(new Variable());
    Formula g = new Exponential(new Variable());
    Formula h = new Exponential(new Constant(1));
    Formula i = new Logarithm(new Variable());
    assertThat(f).isEqualTo(g);
    assertThat(f).isNotEqualTo(h);
    assertThat(f).isNotEqualTo(i);
  }
}
