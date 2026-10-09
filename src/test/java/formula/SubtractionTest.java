package formula;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class SubtractionTest {

  private static final Double EPSILON = 1e-6;

  @Test
  void testEval() {
    Formula f = new Subtraction(new Variable(), new Constant(3));
    assertThat(f.eval(0)).isCloseTo(-3, within(EPSILON));
    assertThat(f.eval(10)).isCloseTo(7, within(EPSILON));
    assertThat(f.eval(-Math.PI)).isCloseTo(-Math.PI - 3, within(EPSILON));
  }

  @Test
  void testDerivative() {
    Formula f = new Subtraction(new Variable(), new Constant(3));
    assertThat(f.derivative().eval(0)).isCloseTo(1, within(EPSILON));
    assertThat(f.derivative().eval(10)).isCloseTo(1, within(EPSILON));
    assertThat(f.derivative()).isEqualTo(new Subtraction(new Constant(1), new Constant(0)));
  }

  @Test
  void testToString() {
    Formula f = new Subtraction(new Variable(), new Constant(3));
    assertThat(f.toString()).isEqualTo("(x - 3.0)");
    Formula g = new Subtraction(new Constant(1), f);
    assertThat(g.toString()).isEqualTo("(1.0 - (x - 3.0))");
  }

  @Test
  void testEquals() {
    Formula f = new Subtraction(new Variable(), new Constant(3));
    Formula g = new Subtraction(new Variable(), new Constant(3));
    Formula h = new Subtraction(new Constant(3), new Variable());
    Formula i = new Addition(new Variable(), new Constant(3));
    assertThat(f).isEqualTo(g);
    assertThat(f).isNotEqualTo(h);
    assertThat(f).isNotEqualTo(i);
  }
}
