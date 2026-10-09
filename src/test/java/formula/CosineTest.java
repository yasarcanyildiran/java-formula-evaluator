package formula;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class CosineTest {

  private static final Double EPSILON = 1e-6;

  @Test
  void testEval() {
    Formula f = new Cosine(new Variable());
    assertThat(f.eval(0)).isCloseTo(1, within(EPSILON));
    assertThat(f.eval(Math.PI / 2)).isCloseTo(0, within(EPSILON));
    assertThat(f.eval(Math.PI)).isCloseTo(-1, within(EPSILON));
  }

  @Test
  void testDerivative() {
    Formula f = new Cosine(new Variable());
    assertThat(f.derivative().eval(0)).isCloseTo(0, within(EPSILON));
    assertThat(f.derivative().eval(Math.PI / 2)).isCloseTo(-1, within(EPSILON));
    assertThat(f.derivative()).isEqualTo(
        new Multiplication(new Opposite(new Sine(new Variable())), new Constant(1)));
  }

  @Test
  void testToString() {
    Formula f = new Cosine(new Variable());
    assertThat(f.toString()).isEqualTo("cos(x)");
    Formula g = new Cosine(new Sine(new Variable()));
    assertThat(g.toString()).isEqualTo("cos(sin(x))");
  }

  @Test
  void testEquals() {
    Formula f = new Cosine(new Variable());
    Formula g = new Cosine(new Variable());
    Formula h = new Cosine(new Constant(1));
    Formula i = new Sine(new Variable());
    assertThat(f).isEqualTo(g);
    assertThat(f).isNotEqualTo(h);
    assertThat(f).isNotEqualTo(i);
  }
}
