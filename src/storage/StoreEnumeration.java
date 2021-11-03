// 
// Decompiled by Procyon v0.5.36
// 
package storage;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordStoreException;
import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;

public class StoreEnumeration {

    private Store store;
    private RecordEnumeration enumeration;
    private Vector buffer;

    public StoreEnumeration(final Store store, final RecordEnumeration enumeration) {
        this.store = store;
        this.enumeration = enumeration;
    }

    public boolean hasNext() throws Exception, RecordStoreException {
        this.feed();
        return this.buffer != null && this.buffer.size() > 0;
    }

    public int numRecords() {
        return this.store.getNumRecords();
    }

    public int nextId() throws Exception, RecordStoreException {
        this.feed();
        if (this.buffer != null && this.buffer.size() > 0) {
            int id = (int) ((Integer) this.buffer.firstElement()).intValue();
            this.buffer.removeElementAt(0);
            return id;
        }
        throw new Exception("15200");
    }

    private void feed() throws Exception, RecordStoreException {
        try {
            while (this.buffer == null || this.buffer.size() == 0) {
                if (!this.enumeration.hasNextElement()) {
                    break;
                }
                final int id = this.enumeration.nextRecordId();
                this.buffer = this.store.mapValidRecordIds(id);
            }
        } catch (InvalidRecordIDException e) {
            throw new InvalidRecordIDException(String.valueOf(15201) + ((Throwable) e).getMessage());
        }
    }

    public byte[] next() throws Exception, RecordStoreException {
        return this.store.getRecord(this.nextId());
    }

    public void destroy() throws Exception, RecordStoreException {
        this.enumeration.destroy();
        this.store.close();
    }
}
