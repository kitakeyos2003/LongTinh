// 
// Decompiled by Procyon v0.5.36
// 
package storage;

import java.util.Vector;
import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStoreFullException;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import javax.microedition.rms.InvalidRecordIDException;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import javax.microedition.rms.RecordStoreNotOpenException;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStore;
import java.util.Hashtable;

public class Store {

    public static final String CLASS_NAME = "Store";
    static Boolean useCompression;
    static boolean burstMode;
    private static Hashtable map;
    RecordStore rs;
    Record currentRecord;
    private StoreInfo info;
    private int openCount;
    private Listener listener;
    private RecordLocator locator;

    static {
        Store.map = new Hashtable(10);
    }

    public static boolean useCompression() {
        if (Store.useCompression == null) {
            final long rsSizeLimit = getRecordStoreSizeAvailable();
            Store.useCompression = new Boolean(rsSizeLimit > 0L && rsSizeLimit < 1000000L);
        }
        return Store.useCompression.booleanValue();
    }

    public static long getRecordStoreSizeAvailable() {
        return 0L;
    }

    public Store(final StoreInfo info) {
        this.info = info;
        this.locator = new RecordLocator(info.multiplexer);
    }

    public static boolean exists(final String name) {
        return StoreInfo.get(name) != null;
    }

    public static Store get(final String name, final int multiplexer) {
        final Hashtable map = Store.map;
        if (map.containsKey(name)) {
            return (Store) map.get(name);
        }
        StoreInfo info = StoreInfo.get(name);
        if (info == null) {
            info = new StoreInfo(name, multiplexer);
        }
        final Store store = new Store(info);
        map.put(name, store);
        return store;
    }

    public static boolean recordStoreExists(final String name) {
        final String[] names = RecordStore.listRecordStores();
        if (names != null) {
            for (int count = names.length, i = 0; i < count; ++i) {
                if (names[i].equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static synchronized void safeDeleteRecordStore(final String name) throws RecordStoreException, Exception {
        safeCloseRecordStore(name);
        try {
            RecordStore.deleteRecordStore(name);
        } catch (RecordStoreNotFoundException ex) {
        } catch (RecordStoreException e) {
            throw new RecordStoreException("15106/" + ((Throwable) e).getMessage());
        }
        Store.map.remove(name);
        final StoreInfo info = StoreInfo.get(name);
        if (info != null && info.recordId != null) {
            info.delete();
        }
    }

    public static synchronized void safeCloseRecordStore(final String name) throws RecordStoreException, Exception {
        try {
            if (Store.map.containsKey(name)) {
                final Store store = (Store) Store.map.get(name);
                store.flush();
                store.openCount = 0;
                store.rs = null;
            }
            final RecordStore rs = RecordStore.openRecordStore(name, false);
            try {
                while (true) {
                    rs.closeRecordStore();
                }
            } catch (RecordStoreNotOpenException e3) {
            } catch (RecordStoreException e) {
                throw new RecordStoreException("15107/" + ((Throwable) e).getMessage());
            }
        } catch (RecordStoreNotFoundException ex) {
        } catch (RecordStoreException e2) {
            throw new RecordStoreException("15107/" + ((Throwable) e2).getMessage());
        }
    }

    public static synchronized void smartShutdown() {
        final Hashtable map = Store.map;
        final int count = map.size();
        if (count > 0) {
            int index = 1;
            final Enumeration i = map.elements();
            while (i.hasMoreElements()) {
                ++index;
                final Store store = (Store) i.nextElement();
                try {
                    store.shutdown();
                } catch (Exception e) {
                    System.out.println("Error!! smartShutdown");
                }
            }
            map.clear();
        }
    }

    public static synchronized void shutdownAll() {
        final String[] names = RecordStore.listRecordStores();
        if (names != null) {
            for (int count = names.length, i = 0; i < count; ++i) {
                try {
                    safeCloseRecordStore(names[i]);
                } catch (RecordStoreException e) {
                    System.out.println("Error!! smartShutdown:" + ((Throwable) e).getMessage());
                } catch (Exception e2) {
                    System.out.println("Error!! smartShutdown:" + e2.getMessage());
                }
            }
        }
    }

    public Listener getListener() {
        return this.listener;
    }

    public synchronized void setListener(final Listener listener) {
        this.listener = listener;
    }

    public boolean isDamaged() {
        return this.info.damaged;
    }

    public synchronized void setDamaged(final boolean damaged) throws RecordStoreException, Exception {
        this.info.damaged = damaged;
        StoreInfo.flush();
    }

    public synchronized int addRecord(final byte[] data) throws RecordStoreException, Exception {
        return this.addRecord(data, 0, data.length);
    }

    public synchronized int addRecord(final byte[] data, final int offset, final int length) throws RecordStoreException, Exception {
        final StoreInfo info = this.info;
        final int assignedRecordId = info.nextId;
        final RecordLocator locator = this.locator;
        try {
            this.open();
            locator.translate(assignedRecordId);
            this.makeCurrent(locator.recordId, true);
            this.currentRecord.set(locator.offset, data, offset, length);
            if (!Store.burstMode) {
                this.flush();
            }
            info.addRecord(length);
            return assignedRecordId;
        } catch (RecordStoreException e) {
            throw new RecordStoreException("15101/" + ((Throwable) e).getMessage());
        } catch (Exception e2) {
            throw new Exception("15101/" + e2.getMessage());
        } finally {
            this.close();
        }
    }

    private void makeCurrent(final int recordId, final boolean adding) throws RecordStoreException, Exception {
        if (this.currentRecord == null || this.currentRecord.getRecordId() != recordId) {
            if (this.currentRecord != null && this.currentRecord.getRecordId() > 0) {
                this.flush();
            }
            try {
                this.loadRecord(recordId, adding);
            } catch (RecordStoreException e) {
                if (!adding) {
                    throw e;
                }
                this.currentRecord = new Record(recordId, this.info.multiplexer);
            }
        }
        if (this.currentRecord == null) {
            this.currentRecord = new Record(recordId, this.info.multiplexer);
        }
    }

    private void loadRecord(final int recordId, final boolean adding) throws RecordStoreException, Exception {
        try {
            final byte[] data = this.rs.getRecord(recordId);
            final ByteArrayInputStream bais = new ByteArrayInputStream(data);
            final DataInputStream in = new DataInputStream(useCompression() ? bais : bais);
            if (this.currentRecord == null) {
                this.currentRecord = new Record(recordId, this.info.multiplexer);
            } else {
                this.currentRecord.setRecordId(recordId);
            }
            this.currentRecord.deserialize(in);
        } catch (InvalidRecordIDException e) {
            this.currentRecord = null;
            if (!adding) {
                throw new InvalidRecordIDException("15111/" + ((Throwable) e).getMessage());
            }
        } catch (RecordStoreException e2) {
            this.currentRecord = null;
            throw new RecordStoreException("15111/" + ((Throwable) e2).getMessage());
        }
    }

    public synchronized void flush() throws RecordStoreException, Exception {
        if (this.currentRecord != null && this.currentRecord.isDirty()) {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream out = new DataOutputStream(baos);
            try {
                this.open();
                this.currentRecord.serialize(out);
                out.flush();
                final byte[] data = baos.toByteArray();
                if (this.currentRecord.getRecordId() > 0 && this.currentRecord.getRecordId() < this.rs.getNextRecordID()) {
                    this.rs.setRecord(this.currentRecord.getRecordId(), data, 0, data.length);
                } else {
                    this.currentRecord.setRecordId(this.rs.addRecord(data, 0, data.length));
                }
                this.currentRecord.setDirty(false);
            } catch (RecordStoreFullException e) {
                throw new RecordStoreFullException("15102/" + ((Throwable) e).getMessage());
            } catch (Exception e2) {
                throw new Exception("15102/" + e2.getMessage());
            } finally {
                this.close();
            }
            this.close();
        }
    }

    public synchronized void close() throws RecordStoreException {
        final RecordStore rs = this.rs;
        int openCount = this.openCount;
        if (openCount > 0) {
            if (--openCount < 0) {
                openCount = 0;
            }
            if (openCount == 0) {
                try {
                    if (rs != null) {
                        this.flush();
                        rs.closeRecordStore();
                    }
                } catch (RecordStoreNotOpenException ex) {
                } catch (Exception e) {
                    throw new RecordStoreException("15107/" + e.getMessage());
                } finally {
                    this.rs = null;
                    if (this.listener != null) {
                        this.listener.rsClose();
                    }
                }
                this.rs = null;
                if (this.listener != null) {
                    this.listener.rsClose();
                }
            }
            this.openCount = openCount;
        }
    }

    public synchronized void open() throws RecordStoreException, Exception {
        this.open(true);
    }

    public synchronized void open(final boolean createIfNecessary) throws Exception, RecordStoreFullException {
        if (this.rs == null) {
            this.openCount = 0;
        }
        if (this.openCount == 0) {
            try {
                this.rs = RecordStore.openRecordStore(this.info.name, createIfNecessary);
            } catch (RecordStoreFullException e) {
                throw new RecordStoreFullException(String.valueOf(15100) + ((Throwable) e).getMessage());
            } catch (RecordStoreException e2) {
                throw new RecordStoreException(String.valueOf(15100) + ((Throwable) e2).getMessage());
            }
        }
        ++this.openCount;
    }

    public String getName() {
        return this.info.name;
    }

    public synchronized void shutdown() throws RecordStoreException, Exception {
        this.flush();
        final RecordStore rs = this.rs;
        if (rs != null) {
            try {
                while (true) {
                    rs.closeRecordStore();
                }
            } catch (RecordStoreNotOpenException e) {
                this.openCount = 0;
                this.rs = null;
                if (this.listener != null) {
                    this.listener.rsClose();
                }
            } catch (RecordStoreException ex) {
            }
        }
    }

    public synchronized void drop() throws Exception, RecordStoreException {
        final String name = this.info.name;
        final short multiplexer = this.info.multiplexer;
        this.shutdown();
        safeDeleteRecordStore(this.info.name);
        if (this.info != null) {
            this.info.delete();
            this.info = new StoreInfo(name, multiplexer);
        }
    }

    public long getLastModified() {
        return this.info.lastModified;
    }

    public int getNextRecordId() {
        return this.info.nextId;
    }

    public synchronized void deleteRecord(final int recordId) throws Exception, InvalidRecordIDException, RecordStoreException {
        Label_0259:
        {
            try {
                this.open();
                final RecordLocator locator = this.locator;
                locator.translate(recordId);
                this.makeCurrent(locator.recordId, false);
                if (!this.currentRecord.isNull(locator.offset)) {
                    final int length = this.currentRecord.getSize(locator.offset);
                    this.currentRecord.delete(locator.offset);
                    this.flush();
                    if (this.currentRecord.size == 0 && this.currentRecord.getRecordId() < this.rs.getNextRecordID() - 1) {
                        this.rs.deleteRecord(this.currentRecord.getRecordId());
                        this.currentRecord = null;
                    }
                    this.info.deleteRecord(length);
                    break Label_0259;
                }
                throw new InvalidRecordIDException(String.valueOf(15103) + Integer.toString(recordId));
            } catch (InvalidRecordIDException e) {
                throw new InvalidRecordIDException(String.valueOf(15103) + ((Throwable) e).getMessage());
            } catch (RecordStoreException e2) {
                throw new RecordStoreException(String.valueOf(15103) + ((Throwable) e2).getMessage());
            } catch (Exception e3) {
                throw new Exception(String.valueOf(15103) + e3.getMessage());
            } finally {
                this.close();
            }
        }
        this.close();
    }

    public synchronized byte[] getRecord(final int recordId) throws Exception, RecordStoreException {
        try {
            this.open();
            final RecordLocator locator = this.locator;
            locator.translate(recordId);
            this.makeCurrent(locator.recordId, false);
            return this.currentRecord.get(locator.offset);
        } catch (InvalidRecordIDException e) {
            throw e;
        } catch (Exception e2) {
            throw new Exception(String.valueOf(15103) + e2.getMessage());
        } finally {
            this.close();
        }
    }

    public synchronized int getRecord(final int recordId, final byte[] buffer, final int offset) throws Exception, RecordStoreException {
        final byte[] data = this.getRecord(recordId);
        System.arraycopy(data, 0, buffer, offset, data.length);
        return data.length;
    }

    public synchronized int getRecordSize(final int recordId) throws Exception, RecordStoreException {
        try {
            this.open();
            final RecordLocator locator = this.locator;
            locator.translate(recordId);
            this.makeCurrent(locator.recordId, false);
            if (this.currentRecord.isNull(locator.offset)) {
                throw new Exception(String.valueOf(15110) + Integer.toString(recordId));
            }
            return this.currentRecord.getSize(locator.offset);
        } catch (Exception e) {
            throw new Exception(String.valueOf(15110) + Integer.toString(recordId));
        } finally {
            this.close();
        }
    }

    public int getNumRecords() {
        return this.info.count;
    }

    public StoreEnumeration enumerateRecords() throws Exception, RecordStoreException {
        try {
            this.open();
            return new StoreEnumeration(this, this.rs.enumerateRecords((RecordFilter) null, (RecordComparator) null, false));
        } catch (RecordStoreException e) {
            throw e;
        } catch (Exception e2) {
            throw new Exception(String.valueOf(15105) + e2.getMessage());
        }
    }

    public int getMultiplexer() {
        return this.info.multiplexer;
    }

    synchronized Vector mapValidRecordIds(final int recordId) throws Exception, RecordStoreException {
        try {
            this.open();
            this.makeCurrent(recordId, false);
            int id;
            if (this.info.multiplexer > 1) {
                id = (recordId - 1) * this.info.multiplexer + 1;
            } else {
                id = recordId;
            }
            return this.currentRecord.getValidIds(id);
        } catch (RecordStoreException e) {
            throw new RecordStoreException(String.valueOf(15104) + ((Throwable) e).getMessage());
        } catch (Exception e2) {
            throw new Exception(String.valueOf(15104) + e2.getMessage());
        } finally {
            this.close();
        }
    }

    public static boolean isBurstMode() {
        return Store.burstMode;
    }

    public static synchronized void setBurstMode(final boolean burstMode) throws RecordStoreException, Exception {
        if (Store.burstMode != burstMode && !(Store.burstMode = burstMode)) {
            StoreInfo.flush();
        }
    }

    public long getSize() {
        return this.info.size;
    }

    public synchronized void packCopy() throws Exception, RecordStoreException {
        this.shutdown();
        this.open();
        RecordStore pack = null;
        try {
            final byte[] empty = {0};
            pack = RecordStore.openRecordStore(String.valueOf(this.info.name) + "_bak", true);
            final int count = this.rs.getNextRecordID();
            for (int i = 1; i < count; ++i) {
                try {
                    final byte[] data = this.rs.getRecord(i);
                    pack.addRecord(data, 0, data.length);
                } catch (InvalidRecordIDException e2) {
                    pack.addRecord(empty, 0, 1);
                    pack.deleteRecord(i);
                }
            }
            this.drop();
            this.open();
            for (int i = 1; i < count; ++i) {
                try {
                    final byte[] data = pack.getRecord(i);
                    this.rs.addRecord(data, 0, data.length);
                } catch (InvalidRecordIDException e2) {
                    this.rs.addRecord(empty, 0, 1);
                    this.rs.deleteRecord(i);
                }
            }
        } catch (RecordStoreException e) {
            throw new RecordStoreException(String.valueOf(15109) + ((Throwable) e).getMessage());
        } finally {
            if (pack != null) {
                try {
                    pack.closeRecordStore();
                } catch (Exception ex) {
                }
                try {
                    RecordStore.deleteRecordStore(String.valueOf(this.info.name) + "_bak");
                } catch (Exception ex2) {
                }
            }
        }
        if (pack != null) {
            try {
                pack.closeRecordStore();
            } catch (Exception ex3) {
            }
            try {
                RecordStore.deleteRecordStore(String.valueOf(this.info.name) + "_bak");
            } catch (Exception ex4) {
            }
        }
    }

    public void setRecord(final int recordId, final byte[] data) throws Exception, RecordStoreException {
        this.setRecord(recordId, data, 0, data.length);
    }

    public synchronized void setRecord(final int recordId, final byte[] data, final int offset, final int length) throws Exception, RecordStoreException {
        Label_0198:
        {
            try {
                this.open();
                final RecordLocator locator = this.locator;
                locator.translate(recordId);
                this.makeCurrent(locator.recordId, false);
                if (!this.currentRecord.isNull(locator.offset)) {
                    final int currentSize = this.currentRecord.getSize(locator.offset);
                    this.currentRecord.set(locator.offset, data, offset, length);
                    if (!Store.burstMode) {
                        this.flush();
                    }
                    this.info.updateRecord(length - currentSize);
                    break Label_0198;
                }
                throw new RecordStoreException(String.valueOf(15112) + Integer.toString(recordId));
            } catch (RecordStoreException e) {
                throw new RecordStoreException("15103/" + ((Throwable) e).getMessage());
            } catch (Exception e2) {
                throw new Exception("15103/" + e2.getMessage());
            } finally {
                this.close();
            }
        }
        this.close();
    }

    public static void stopStoreSystem() {
        try {
            StoreInfo.flush();
        } catch (RecordStoreException ex) {
        } catch (Exception ex2) {
        }
    }

    public static void startStoreSystem() {
        StoreInfo.load();
    }

    private class RecordLocator {

        int multiplexer;
        int recordId;
        int offset;

        public RecordLocator(final int multiplexer) {
            this.multiplexer = multiplexer;
        }

        public void translate(final int id) {
            final int multiplexer = this.multiplexer;
            if (multiplexer > 1) {
                final int zeroBasedId = id - 1;
                this.recordId = zeroBasedId / multiplexer + 1;
                this.offset = zeroBasedId % multiplexer;
            } else {
                this.recordId = id;
                this.offset = 0;
            }
        }

        public String toString() {
            return "RecordLocator{multiplexer=" + this.multiplexer + ", recordId=" + this.recordId + ", offset=" + this.offset + '}';
        }
    }

    public interface Listener {

        void rsOpen();

        void rsClose();
    }
}
