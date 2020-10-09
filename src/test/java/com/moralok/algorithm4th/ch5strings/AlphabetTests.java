package com.moralok.algorithm4th.ch5strings;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

/**
 * @author moralok
 * @since 2020/10/5
 */
class AlphabetTests {

    private final String abra = "edu/princeton/cs/algs4/data/abra.txt";

    @Test
    void testAlphabet() {
        int[]  encoded1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        StdOut.println(decoded1);

        int[]  encoded2 = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        StdOut.println(decoded2);

        int[]  encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        StdOut.println(decoded3);
    }

    @Test
    void testCount() {
        String source = "ABCDR";
        In in = new In(abra);
        Alphabet alphabet = new Alphabet(source);
        int R = alphabet.radix();
        int[] count = new int[R];
        String s = in.readAll();
        int N = s.length();
        for (int i = 0; i < N; i++) {
            if (alphabet.contains(s.charAt(i))) {
                count[alphabet.toIndex(s.charAt(i))]++;
            }
        }
        for (int i = 0; i < R; i++) {
            StdOut.println(alphabet.toChar(i) + " " + count[i]);
        }
    }
}
