package algorithm;

public class ROT13 {
    public static String rot13(String str) {
        String res;
        char[] values = str.toCharArray();
        for (int i = 0; i < values.length; i++) {
            char letter = values[i];
            if (letter >= 'a' && letter <= 'z') {
                if (letter > 'm') {
                    letter -= 13;
                } else {
                    letter += 13;
                }
            } else if (letter >= 'A' && letter <= 'Z') {
                if (letter > 'M') {
                    letter -= 13;
                } else {
                    letter += 13;
                }
            }
            values[i] = letter;
        }
        res =  new String(values);
        return res;
    }
}
