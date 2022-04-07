package com.hs1;

import java.util.Arrays;

public class Security {
    private final int shift;

    public Security(int shift) {
        this.shift = shift;
    }

    /*
    To obfuscate, I'm creating converting each item in the json string to an array of each character's unicode code
    then I am shifting the code by a random number that will be used to un-obfuscate the string from the file later.
    A constraint here would be a larger data set causing machine to run out of memory
    */
    public String encode(String clearText) {
        int[] encodedOutput = new int[clearText.length()];

        //loop through our string and shift it the random number of bits
        for (int i = 0; i < clearText.length(); i++) {
            int characterNumVal = Character.codePointAt(clearText, i);
            encodedOutput[i] = shiftVal(characterNumVal, false);
        }

        return Arrays.toString(encodedOutput);
    }

    public String decode(String encodedStr) {
        StringBuilder decodedString = new StringBuilder();
        int[] encodedArr = convertEncodedStringToArr(encodedStr);

        for (int j : encodedArr) {
            int t = shiftVal(j, true);
            decodedString.append((char) t);
        }

        return decodedString.toString();
    }

    public int shiftVal(int val, boolean isEncrypt) {
        return isEncrypt ? val >> this.shift : val << this.shift;
    }

    public int[] convertEncodedStringToArr(String encodedStr) {

        //we need to get string contents back into an array for decoding
        return Arrays.stream(encodedStr.replaceAll("\\[", "")
                        .replaceAll("]", "")
                        .split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
