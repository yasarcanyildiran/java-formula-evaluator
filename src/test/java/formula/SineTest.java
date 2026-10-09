package formula;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class SineTest {

  private static final Double EPSILON = 1e-6;

  @Test
  void testEval() {
    Formula f = new Sine(new Variable());
    assertThat(f.eval(0)).isCloseTo(0, within(EPSILON));
    assertThat(f.eval(Math.PI / 2)).isCloseTo(1, within(EPSILON));
    assertThat(f.eval(-Math.PI / 2)).isCloseTo(-1, within(EPSILON));
  }

  @Test
  void testDerivative() {
    Formula f = new Sine(new Variable());
    assertThat(f.derivative().eval(0)).isCloseTo(1, within(EPSILON));
    assertThat(f.derivative().eval(Math.PI)).isCloseTo(-1, within(EPSILON));
    assertThat(f.derivative()).isEqualTo(
        new Multiplication(new Cosine(new Variable()), new Constant(1)));

    Formula g = new Sine(new Multiplication(new Constant(2), new Variable()));
    assertThat(g.derivative().eval(0)).isCloseTo(2, within(EPSILON));
  }

  @Test
  void testToString() {
    Formula f = new Sine(new Variable());
    assertThat(f.toString()).isEqualTo("sin(x)");
    Formula g = new Sine(new Sine(new Variable()));
    assertThat(g.toString()).isEqualTo("sin(sin(x))");
  }

  @Test
  void testEquals() {
    Formula f = new Sine(new Variable());
    Formula g = new Sine(new Variable());
    Formula h = new Sine(new Constant(1));
    Formula i = new Cosine(new Variable());
    assertThat(f).isEqualTo(g);
    assertThat(f).isNotEqualTo(h);
    assertThat(f).isNotEqualTo(i);
  }
}
