package stackStuff.Expressions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfixToPostTest {
    @Test
    public void testSimpleAddition() {
        String[] input = {"3", "+", "5"};
        try {
            String[] result = InfixToPost.InfixConvert(input);
            assertArrayEquals(new String[]{"3", "5", "+"}, result);
        } catch (Exception e) {
            fail("Exception should not be thrown for valid input.");
        }
    }
}