package vad.nu.java.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Tomas Gradin
 */
public class OptionalTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // isPresent()
    @Test
    public void emptyTest() {
        Optional<String> s = Optional.empty();
        assertFalse(s.isPresent());
    }

    @Test
    public void nullableEmptyTest() {
        Optional<String> s = Optional.ofNullable(null);
        assertFalse(s.isPresent());
    }

    @Test
    public void nullableValueTest() {
        Optional<String> s = Optional.ofNullable("X");
        assertTrue(s.isPresent());
    }

    // Test of null in non-nullable
    @Test
    public void nullValueFailTest() {
        thrown.expect(NullPointerException.class);

        @SuppressWarnings("unused")
        Optional<String> s = Optional.of(null);
    }

    // .get()
    @Test
    public void getEmptyTest() {
        thrown.expect(NoSuchElementException.class);

        Optional<String> s = Optional.empty();
        s.get();
    }

    @Test
    public void getValueTest() {
        Optional<String> s = Optional.of("A");
        assertEquals(s.get(), "A");
    }

    // .orElse()
    @Test
    public void orElseEmptyTest() {
        Optional<String> s = Optional.of("A");
        assertEquals(s.orElse("B"), "A");
    }

    @Test
    public void orElseValueTest() {
        Optional<String> s = Optional.empty();
        assertEquals(s.orElse("B"), "B");
    }

    // .equals()

    @Test
    public void equalsTest() {
        Integer a = new Integer(1234);
        Integer b = new Integer(1234);


        Optional<Integer> i1 = Optional.of(a);
        Optional<Integer> i2 = Optional.of(b);
        Optional<String> s1 = Optional.of("X");
        Optional<String> s2 = Optional.of("X");

        assertNotSame(a, b);
        assertEquals(i1, i2);
        assertEquals(s1, s1);
        assertEquals(s1, s2);
    }

    @Test
    public void emptyEqualsTest() {
        Optional<String> s1 = Optional.empty();
        Optional<String> s2 = Optional.empty();

        assertEquals(s1, s1);

        assertEquals(s1, s2);
        assertEquals(s2, s1);
    }

    @Test
    public void notEqualsTest() {
        Optional<String> s1 = Optional.of("X");
        Optional<String> s2 = Optional.of("Y");

        assertFalse(s1.equals(s2));
    }

    @Test
    public void nullEqualsTest() {
        Optional<String> s1 = Optional.ofNullable(null);
        Optional<String> s2 = Optional.ofNullable(null);
        Optional<String> s3 = Optional.empty();

        assertEquals(s1, s2);
        assertEquals(s2, s1);

        assertEquals(s3, s1);
        assertEquals(s1, s3);
    }

    @Test
    public void nullNotEqualsTest() {
        Optional<String> s1 = Optional.of("X");
        Optional<String> s2 = Optional.ofNullable(null);
        Optional<String> s3 = Optional.empty();

        assertFalse(s1.equals(s2));
        assertFalse(s2.equals(s1));
        assertFalse(s1.equals(s3));
        assertFalse(s3.equals(s1));
    }

    @Test
    public void oneEmptyEqualsTest() {
        Optional<String> s1 = Optional.ofNullable("A");
        Optional<String> s2 = Optional.empty();

        assertFalse(s1.equals(s2));
        assertFalse(s1.equals(null));
    }

    // .hashCode()
    @Test
    public void hashTest() {
        String x = "X";

        Optional<String> s1 = Optional.of(x);
        Optional<String> s2 = Optional.empty();
        Optional<String> s3 = Optional.ofNullable(null);

        assertTrue(s1.hashCode() == x.hashCode());

        assertFalse(s1.hashCode() == s2.hashCode());
        assertTrue(s2.hashCode() == s3.hashCode());
        assertTrue(s2.hashCode() == 0);
    }

    // toString()

    @Test
    public void toStringEmptyTest() {
        Optional<String> s = Optional.of("A");
        assertEquals(s.toString(), "Optional[A]");
    }

    @Test
    public void toStringValueTest() {
        Optional<String> s = Optional.empty();
        assertEquals(s.toString(), "Optional.empty");
    }
}
