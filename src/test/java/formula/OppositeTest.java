package formula;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class OppositeTest {

  private static final Double EPSILON = 1e-6;

  @Test
  void testEval() {
    Formula f = new Opposite(new Variable());
    assertThat(f.eval(0)).isCloseTo(0, within(EPSILON));
    assertThat(f.eval(10)).isCloseTo(-10, within(EPSILON));
    assertThat(f.eval(-Math.PI)).isCloseTo(Math.PI, within(EPSILON));
  }

  @Test
  void testDerivative() {
    Formula f = new Opposite(new Variable());
    assertThat(f.derivative().eval(0)).isCloseTo(-1, within(EPSILON));
    assertThat(f.derivative().eval(10)).isCloseTo(-1, within(EPSILON));
    assertThat(f.derivative()).isEqualTo(new Opposite(new Constant(1)));
  }

  @Test
  void testToString() {
    Formula f = new Opposite(new Variable());
    assertThat(f.toString()).isEqualTo("-(x)");
    Formula g = new Opposite(new Addition(new Variable(), new Constant(1)));
    assertThat(g.toString()).isEqualTo("-((x + 1.0))");
  }

  @Test
  void testEquals() {
    Formula f = new Opposite(new Variable());
    Formula g = new Opposite(new Variable());
    Formula h = new Opposite(new Constant(1));
    Formula i = new Sine(new Variable());
    assertThat(f).isEqualTo(g);
    assertThat(f).isNotEqualTo(h);
    assertThat(f).isNotEqualTo(i);
  }
}
