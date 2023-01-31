package bfst22.factorial;

class BadUserException extends RuntimeException {}

public class Factorial {
  int factorial(int n) throws BadUserException {
    if (n < 0) throw new BadUserException();
    int res = 1;
    for (int i = 1 ; i <= n ; i++) {
      res = res * i;
    }
    return res;
  }
}
