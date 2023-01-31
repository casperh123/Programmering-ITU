package bfst22.roman;

/* Parses generalized Roman numerals. Allows more than three Cs, Xs, and Is in a row */
public class Roman {
  public static Integer fromRoman(String x) {
    x = x + "$";
    int value = 0;
    int pos = 0;

    while (x.charAt(pos) == 'M') {
      value += 1000;
      pos++;
    }
    while (x.charAt(pos) == 'D') {
      value += 500;
      pos++;
    }
    if (x.charAt(pos) == 'C') {
      if (x.charAt(pos + 1) == 'M') {
        value += 900;
        pos += 2;
      }
    }
    while (x.charAt(pos) == 'C') {
      value += 100;
      pos++;
    }
    while (x.charAt(pos) == 'L') {
      value += 50;
      pos++;
    }
    if (x.charAt(pos) == 'X') {
      if (x.charAt(pos + 1) == 'C') {
        value += 90;
        pos += 2;
      }
    }
    while (x.charAt(pos) == 'X') {
      value += 10;
      pos++;
    }
    while (x.charAt(pos) == 'V') {
      value += 5;
      pos++;
    }
    if (x.charAt(pos) == 'I') {
      if (x.charAt(pos + 1) == 'X') {
        value += 9;
        pos += 2;
      }
    }
    while (x.charAt(pos) == 'I') {
      value += 1;
      pos++;
    }
    if (pos < x.length() - 1)
      return null;
    return value;
  }
}
