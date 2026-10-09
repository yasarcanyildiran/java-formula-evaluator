package formula;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class MultiplicationTest {

  private static final Double EPSILON = 1e-6;

  @Test
  void testEval() {
    Formula f = new Multiplication(new Variable(), new Constant(3));
    assertThat(f.eval(0)).isCloseTo(0, within(EPSILON));
    assertThat(f.eval(10)).isCloseTo(30, within(EPSILON));
    assertThat(f.eval(-Math.PI)).isCloseTo(-3 * Math.PI, within(EPSILON));
  }

  @Test
  void testDerivative() {
    Formula f = new Multiplication(new Variable(), new Constant(3));
    assertThat(f.derivative().eval(0)).isCloseTo(3, within(EPSILON));
    assertThat(f.derivative().eval(10)).isCloseTo(3, within(EPSILON));
    assertThat(f.derivative()).isEqualTo(new Addition(
        new Multiplication(new Constant(1), new Constant(3)),
        new Multiplication(new Variable(), new Constant(0))));

    Formula square = new Multiplication(new Variable(), new Variable());
    assertThat(square.derivative().eval(0)).isCloseTo(0, within(EPSILON));
    assertThat(square.derivative().eval(5)).isCloseTo(10, within(EPSILON));
  }

  @Test
  void testToString() {
    Formula f = new Multiplication(new Variable(), new Constant(3));
    assertThat(f.toString()).isEqualTo("(x * 3.0)");
    Formula g = new Multiplication(new Addition(new Variable(), new Constant(1)), f);
    assertThat(g.toString()).isEqualTo("((x + 1.0) * (x * 3.0))");
  }

  @Test
  void testEquals() {
    Formula f = new Multiplication(new Variable(), new Constant(3));
    Formula g = new Multiplication(new Variable(), new Constant(3));
    Formula h = new Multiplication(new Constant(3), new Variable());
    Formula i = new Addition(new Variable(), new Constant(3));
    assertThat(f).isEqualTo(g);
    assertThat(f).isNotEqualTo(h);
    assertThat(f).isNotEqualTo(i);
  }
}
