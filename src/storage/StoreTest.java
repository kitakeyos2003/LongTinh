// 
// Decompiled by Procyon v0.5.36
// 
package storage;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStore;

public class StoreTest {

    public static void testStoreBurst2() {
        try {
            final Store store = Store.get("test2burst", 2);
            Store.setBurstMode(true);
            store.open(true);
            store.addRecord(createTestData(200, (byte) 1));
            store.addRecord(createTestData(100, (byte) 2));
            store.addRecord(createTestData(100, (byte) 3));
            store.addRecord(createTestData(100, (byte) 4));
            store.addRecord(createTestData(100, (byte) 5));
            store.addRecord(createTestData(100, (byte) 6));
            store.addRecord(createTestData(100, (byte) 7));
            store.addRecord(createTestData(100, (byte) 8));
            store.addRecord(createTestData(100, (byte) 9));
            store.addRecord(createTestData(100, (byte) 10));
            Store.setBurstMode(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testStore2() {
        try {
            final Store store = Store.get("test2", 2);
            store.open(true);
            store.addRecord(createTestData(200, (byte) 1));
            store.addRecord(createTestData(100, (byte) 2));
            store.addRecord(createTestData(100, (byte) 3));
            store.addRecord(createTestData(100, (byte) 4));
            store.addRecord(createTestData(100, (byte) 5));
            store.addRecord(createTestData(100, (byte) 6));
            store.addRecord(createTestData(100, (byte) 7));
            store.addRecord(createTestData(100, (byte) 8));
            store.addRecord(createTestData(100, (byte) 9));
            store.addRecord(createTestData(100, (byte) 10));
            store.setRecord(2, createTestData(150, (byte) 2));
            store.setRecord(4, createTestData(91, (byte) 4));
            store.deleteRecord(3);
            store.deleteRecord(9);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testStoreBurst1() {
        try {
            final Store store = Store.get("test1burst", 1);
            Store.setBurstMode(true);
            store.open(true);
            store.addRecord(createTestData(200, (byte) 1));
            store.addRecord(createTestData(100, (byte) 2));
            store.addRecord(createTestData(100, (byte) 3));
            store.addRecord(createTestData(100, (byte) 4));
            store.addRecord(createTestData(100, (byte) 5));
            store.addRecord(createTestData(100, (byte) 6));
            store.addRecord(createTestData(100, (byte) 7));
            store.addRecord(createTestData(100, (byte) 8));
            store.addRecord(createTestData(100, (byte) 9));
            store.addRecord(createTestData(100, (byte) 10));
            Store.setBurstMode(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testInitialGetRecordSize() {
        final Store store = Store.get("testInitialGetRS", 1);
        try {
            final byte[] data = createTestData(10, (byte) 1);
            final RecordStore rs = RecordStore.openRecordStore("testInitialGetRS", true);
            rs.addRecord(data, 0, data.length);
            rs.closeRecordStore();
            store.open(true);
            try {
                store.getRecordSize(1);
            } catch (Exception ex) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testInitialGetSet() {
        final Store store = Store.get("testInitialGetSet", 1);
        try {
            store.open(true);
            try {
                store.getRecord(1);
            } catch (InvalidRecordIDException ex) {
            }
            try {
                store.setRecord(1, createTestData(10, (byte) 1));
            } catch (InvalidRecordIDException ex2) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testInitialSet() {
        final Store store = Store.get("testInitialSet", 1);
        try {
            store.open(true);
            try {
                store.setRecord(1, createTestData(10, (byte) 1));
            } catch (InvalidRecordIDException ex) {
            }
            store.addRecord(createTestData(200, (byte) 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testStore1() {
        try {
            final Store store = Store.get("test1", 1);
            store.open(true);
            store.addRecord(createTestData(200, (byte) 1));
            store.addRecord(createTestData(100, (byte) 2));
            store.addRecord(createTestData(100, (byte) 3));
            store.addRecord(createTestData(100, (byte) 4));
            store.addRecord(createTestData(100, (byte) 5));
            store.addRecord(createTestData(100, (byte) 6));
            store.addRecord(createTestData(100, (byte) 7));
            store.addRecord(createTestData(100, (byte) 8));
            store.addRecord(createTestData(100, (byte) 9));
            store.addRecord(createTestData(100, (byte) 10));
            store.setRecord(2, createTestData(150, (byte) 2));
            store.setRecord(4, createTestData(91, (byte) 4));
            store.deleteRecord(3);
            store.deleteRecord(9);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] createTestData(final int length, final byte fill) {
        final byte[] set = new byte[length];
        for (int i = 0; i < length; ++i) {
            set[i] = fill;
        }
        return set;
    }
}
