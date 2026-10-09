package formula;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class AdditionTest {

  private static final Double EPSILON = 1e-6;

  @Test
  void testEval() {
    Formula f = new Addition(new Variable(), new Constant(3));
    assertThat(f.eval(0)).isCloseTo(3, within(EPSILON));
    assertThat(f.eval(10)).isCloseTo(13, within(EPSILON));
    assertThat(f.eval(-Math.PI)).isCloseTo(3 - Math.PI, within(EPSILON));
  }

  @Test
  void testDerivative() {
    Formula f = new Addition(new Variable(), new Constant(3));
    assertThat(f.derivative().eval(0)).isCloseTo(1, within(EPSILON));
    assertThat(f.derivative().eval(10)).isCloseTo(1, within(EPSILON));
    assertThat(f.derivative()).isEqualTo(new Addition(new Constant(1), new Constant(0)));
  }

  @Test
  void testToString() {
    Formula f = new Addition(new Variable(), new Constant(3));
    assertThat(f.toString()).isEqualTo("(x + 3.0)");
    Formula g = new Addition(f, new Variable());
    assertThat(g.toString()).isEqualTo("((x + 3.0) + x)");
  }

  @Test
  void testEquals() {
    Formula f = new Addition(new Variable(), new Constant(3));
    Formula g = new Addition(new Variable(), new Constant(3));
    Formula h = new Addition(new Constant(3), new Variable());
    assertThat(f).isEqualTo(g);
    assertThat(f).isNotEqualTo(h);
  }
}
