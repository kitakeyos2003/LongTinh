// 
// Decompiled by Procyon v0.5.36
// 
package storage;

public class MassiveRunner {

    public static void assertTrue(final boolean b) {
        if (!b) {
            fail("Expected true but found false");
        }
    }

    public static void assertNotNull(final byte[] data) {
        if (data == null) {
            fail("Expected not null");
        }
    }

    public static void assertEquals(final int i, final int i1) {
        if (i != i1) {
            fail("Expected " + i + " but found " + i1);
        }
    }

    public static byte[] createTestData(final int length, final byte fill) {
        final byte[] set = new byte[length];
        for (int i = 0; i < length; ++i) {
            set[i] = fill;
        }
        return set;
    }

    public static void fail(final String message) {
        System.err.println(message);
        System.exit(1);
    }

    public static void byteArrayEquals(final byte[] a1, final byte[] a2) {
        if (a1 != null && a2 != null) {
            if (a1.length != a2.length) {
                fail("Expected length: " + a1.length + ", actual length: " + a2.length);
            } else {
                for (int i = 0; i < a1.length; ++i) {
                    if (a1[i] != a2[i]) {
                        fail("Expected " + a1[i] + " but was " + a2[i]);
                    }
                }
            }
        } else if (a1 != null || a2 != null) {
            fail("One array is null and the other is not");
        }
    }
}
