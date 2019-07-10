import org.junit.jupiter.api.Test;

public class ExceptionsCauses {

  public void methodThatThrowsExceptionA() throws ExceptionA {
    throw new ExceptionA("throw A");
  }

  public void methodThatThrowsBIfA() throws ExceptionB {
    try {
      methodThatThrowsExceptionA();
    } catch (ExceptionA exceptionA) {
//      exceptionA.printStackTrace();
      throw new ExceptionB("Throwing B", exceptionA);
    }
  }

  public void methodThatThrowsCIfB() throws ExceptionC {
    try {
      methodThatThrowsBIfA();
    } catch (final ExceptionB exceptionB) {
      throw new ExceptionC("Throwing C", exceptionB);
    }
  }

  @Test
  void throwsExceptionC() throws ExceptionC {
    methodThatThrowsCIfB();
  }
}
