// 
// Decompiled by Procyon v0.5.36
// 
package storage;

import javax.microedition.rms.RecordEnumeration;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import java.util.Enumeration;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

public class StoreInfo {

    static final String RS_NAME = "sys_store_info";
    static Hashtable cache;
    private static ByteArrayOutputStream baos;
    private static DataOutputStream out;
    String name;
    int count;
    long size;
    int nextId;
    short multiplexer;
    long lastModified;
    Integer recordId;
    boolean dirty;
    boolean damaged;

    static {
        load();
    }

    static synchronized void flush() throws RecordStoreException, Exception {
        Exception e = null;
        final Enumeration i = StoreInfo.cache.elements();
        while (i.hasMoreElements()) {
            final StoreInfo info = (StoreInfo) i.nextElement();
            if (info.dirty) {
                try {
                    info.save();
                } catch (Exception e2) {
                    e = new Exception(String.valueOf(15250) + e2.getMessage());
                }
            }
        }
        if (e != null) {
            throw e;
        }
    }

    static synchronized void load() {
        RecordStore rs = null;
        RecordEnumeration re = null;
        byte[] buffer = new byte[256];
        try {
            rs = RecordStore.openRecordStore("sys_store_info", true);
            StoreInfo.cache = new Hashtable(rs.getNumRecords());
            re = rs.enumerateRecords((RecordFilter) null, (RecordComparator) null, false);
            while (re.hasNextElement()) {
                final int recordId = re.nextRecordId();
                final int recordSize = rs.getRecordSize(recordId);
                if (buffer.length < recordSize) {
                    buffer = new byte[recordSize + 64];
                }
                rs.getRecord(recordId, buffer, 0);
                final StoreInfo info = new StoreInfo();
                info.deserialize(new DataInputStream(new ByteArrayInputStream(buffer)));
                info.recordId = new Integer(recordId);
                StoreInfo.cache.put(info.name, info);
            }
        } catch (RecordStoreException e) {
            System.out.println("Error!! smartShutdown:" + ((Throwable) e).getMessage());
        } catch (Exception e2) {
            System.out.println("Error!! smartShutdown:" + e2.getMessage());
        } finally {
            if (re != null) {
                try {
                    re.destroy();
                } catch (Exception ex) {
                }
            }
            if (rs != null) {
                try {
                    rs.closeRecordStore();
                } catch (Exception ex2) {
                }
            }
        }
        if (re != null) {
            try {
                re.destroy();
            } catch (Exception ex3) {
            }
        }
        if (rs != null) {
            try {
                rs.closeRecordStore();
            } catch (Exception ex4) {
            }
        }
    }

    public static StoreInfo get(final String name) {
        return (StoreInfo) StoreInfo.cache.get(name);
    }

    public static synchronized void set(final StoreInfo info) throws RecordStoreException, Exception {
        final StoreInfo current = (StoreInfo) StoreInfo.cache.get(info.name);
        if (current != null) {
            info.recordId = current.recordId;
        }
        info.save();
        if (current != null) {
            StoreInfo.cache.put(info.name, info);
        }
        flush();
    }

    public StoreInfo() {
        this.nextId = 1;
        this.multiplexer = 1;
    }

    public StoreInfo(final String name) {
        this.nextId = 1;
        this.multiplexer = 1;
        this.name = name;
        StoreInfo.cache.put(name, this);
    }

    public StoreInfo(final String name, final short multiplexer) {
        this.nextId = 1;
        this.multiplexer = 1;
        this.name = name;
        this.multiplexer = multiplexer;
        StoreInfo.cache.put(name, this);
    }

    public StoreInfo(final String name, final int multiplexer) {
        this.nextId = 1;
        this.multiplexer = 1;
        this.name = name;
        this.multiplexer = (short) multiplexer;
        StoreInfo.cache.put(name, this);
    }

    public String getSerializableClassName() {
        return "bm.storage.StoreInfo";
    }

    public void serialize(final DataOutputStream out) throws Exception {
        out.writeByte(2);
        out.writeUTF(this.name);
        out.writeInt(this.count);
        out.writeLong(this.size);
        out.writeInt(this.nextId);
        out.writeShort(this.multiplexer);
        out.writeLong(this.lastModified);
        out.writeBoolean(this.damaged);
    }

    public void deserialize(final DataInputStream in) throws Exception {
        final byte version = in.readByte();
        this.name = in.readUTF();
        this.count = in.readInt();
        this.size = in.readLong();
        this.nextId = in.readInt();
        this.multiplexer = in.readShort();
        this.lastModified = in.readLong();
        if (version > 1) {
            this.damaged = in.readBoolean();
        } else {
            this.damaged = false;
        }
    }

    public void addRecord(final int length) throws Exception, RecordStoreException {
        ++this.count;
        this.size += length;
        ++this.nextId;
        this.lastModified = System.currentTimeMillis();
        this.dirty = true;
        if (!Store.burstMode) {
            this.save();
        }
    }

    public void save() throws Exception, RecordStoreException {
        RecordStore rs = null;
        try {
            rs = RecordStore.openRecordStore("sys_store_info", true);
            if (StoreInfo.out != null) {
                StoreInfo.baos.reset();
            } else {
                StoreInfo.baos = new ByteArrayOutputStream();
                StoreInfo.out = new DataOutputStream(StoreInfo.baos);
            }
            this.serialize(StoreInfo.out);
            final byte[] data = StoreInfo.baos.toByteArray();
            if (this.recordId != null) {
                rs.setRecord((int) this.recordId.intValue(), data, 0, data.length);
            } else {
                this.recordId = new Integer(rs.addRecord(data, 0, data.length));
                StoreInfo.cache.put(this.name, this);
            }
            this.dirty = false;
        } catch (RecordStoreException ex) {
        } finally {
            if (rs != null) {
                try {
                    rs.closeRecordStore();
                } catch (Exception ex2) {
                }
            }
        }
        if (rs != null) {
            try {
                rs.closeRecordStore();
            } catch (Exception ex3) {
            }
        }
    }

    public void delete() {
        if (this.recordId != null) {
            RecordStore rs = null;
            try {
                rs = RecordStore.openRecordStore("sys_store_info", true);
                rs.deleteRecord((int) this.recordId.intValue());
            } catch (RecordStoreException ex) {
            } finally {
                if (rs != null) {
                    try {
                        rs.closeRecordStore();
                    } catch (Exception ex2) {
                    }
                }
            }
            if (rs != null) {
                try {
                    rs.closeRecordStore();
                } catch (Exception ex3) {
                }
            }
        }
        StoreInfo.cache.remove(this.name);
    }

    public void deleteRecord(final int length) throws Exception {
        --this.count;
        this.lastModified = System.currentTimeMillis();
        this.size -= length;
        this.dirty = true;
        if (!Store.burstMode) {
            this.save();
        }
    }

    public void updateRecord(final int difference) throws Exception, RecordStoreException {
        this.size += difference;
        this.lastModified = System.currentTimeMillis();
        this.dirty = true;
        if (!Store.burstMode) {
            this.save();
        }
    }

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final StoreInfo storeInfo = (StoreInfo) o;
        return this.count == storeInfo.count && this.lastModified == storeInfo.lastModified && this.multiplexer == storeInfo.multiplexer && this.nextId == storeInfo.nextId && this.size == storeInfo.size && this.name.equals(storeInfo.name);
    }

    public int hashCode() {
        int result = this.name.hashCode();
        result = 31 * result + this.count;
        result = 31 * result + (int) (this.size ^ this.size >>> 32);
        result = 31 * result + this.nextId;
        result = 31 * result + this.multiplexer;
        result = 31 * result + (int) (this.lastModified ^ this.lastModified >>> 32);
        return result;
    }
}
