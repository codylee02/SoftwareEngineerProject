package tests;

import com.hs1.Security;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecurityTests {
    private static Security security;
    String expectedEncodedStr = "[672, 832, 840, 920, 256, 840, 920, 256, 776, 256, 672, 808, 920, 928, 256, 664, 928, 912, 840, 880, 824, 264]";
    String expectedDecodedStr = "This is a Test String!";

    @BeforeAll
    static void setUp() {
        security = new Security(3);
    }

    @Test
    @DisplayName("Encode will shift characters in array by correct values")
    public void testEncode() {
        assertEquals(expectedEncodedStr, security.encode(expectedDecodedStr));
    }

    @Test
    @DisplayName("Decode will shift characters back to original values")
    public void testDecode() {
        assertEquals(expectedDecodedStr, security.decode(expectedEncodedStr));
    }

    @Test
    @DisplayName("It should take a string and decode it")
    public void testGetEncodedDataFromFile() {
        int[] expectedArr = {672, 832, 840, 920, 256, 840, 920, 256, 776, 256, 672, 808, 920, 928, 256, 664, 928, 912, 840, 880, 824, 264};
        assertArrayEquals(expectedArr, security.convertEncodedStringToArr(expectedEncodedStr));
    }

    @Test
    @DisplayName("Will shift values the correct number of times depending on if it is encoding or decoding")
    public void testShiftVal() {
        assertEquals(84, security.shiftVal(672, true));
        assertEquals(672, security.shiftVal(84, false));
    }
}
