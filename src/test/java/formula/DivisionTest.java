package formula;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class DivisionTest {

  private static final Double EPSILON = 1e-6;

  @Test
  void testEval() {
    Formula f = new Division(new Variable(), new Constant(4));
    assertThat(f.eval(0)).isCloseTo(0, within(EPSILON));
    assertThat(f.eval(10)).isCloseTo(2.5, within(EPSILON));
    assertThat(f.eval(-Math.PI)).isCloseTo(-Math.PI / 4, within(EPSILON));
    Formula inverse = new Division(new Constant(1), new Variable());
    assertThat(inverse.eval(0)).isInfinite();
  }

  @Test
  void testDerivative() {
    Formula f = new Division(new Variable(), new Constant(4));
    assertThat(f.derivative().eval(0)).isCloseTo(0.25, within(EPSILON));
    assertThat(f.derivative().eval(10)).isCloseTo(0.25, within(EPSILON));
    assertThat(f.derivative()).isEqualTo(new Division(
        new Subtraction(
            new Multiplication(new Constant(1), new Constant(4)),
            new Multiplication(new Variable(), new Constant(0))),
        new Multiplication(new Constant(4), new Constant(4))));

    Formula inverse = new Division(new Constant(1), new Variable());
    assertThat(inverse.derivative().eval(1)).isCloseTo(-1, within(EPSILON));
    assertThat(inverse.derivative().eval(2)).isCloseTo(-0.25, within(EPSILON));
  }

  @Test
  void testToString() {
    Formula f = new Division(new Variable(), new Constant(4));
    assertThat(f.toString()).isEqualTo("(x / 4.0)");
    Formula g = new Division(new Addition(new Variable(), new Constant(1)), f);
    assertThat(g.toString()).isEqualTo("((x + 1.0) / (x / 4.0))");
  }

  @Test
  void testEquals() {
    Formula f = new Division(new Variable(), new Constant(4));
    Formula g = new Division(new Variable(), new Constant(4));
    Formula h = new Division(new Constant(4), new Variable());
    Formula i = new Multiplication(new Variable(), new Constant(4));
    assertThat(f).isEqualTo(g);
    assertThat(f).isNotEqualTo(h);
    assertThat(f).isNotEqualTo(i);
  }
}
