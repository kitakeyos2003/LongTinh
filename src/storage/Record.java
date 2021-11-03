// 
// Decompiled by Procyon v0.5.36
// 
package storage;

import java.util.Vector;
import javax.microedition.rms.RecordStoreException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

class Record {

    private static final String CLASS_NAME = "Record";
    int recordId;
    int multiplexer;
    int size;
    byte[][] items;
    boolean dirty;

    public Record(final int recordId, final int multiplexer) {
        this.recordId = recordId;
        this.multiplexer = multiplexer;
        this.items = new byte[multiplexer][];
    }

    public String getSerializableClassName() {
        return "bm.storage.Record";
    }

    public int getRecordId() {
        return this.recordId;
    }

    public void setRecordId(final int recordId) {
        this.recordId = recordId;
    }

    public void serialize(final DataOutputStream out) throws Exception {
        try {
            out.writeByte(1);
            out.writeInt(this.multiplexer);
            out.writeInt(this.size);
            for (int i = 0; i < this.multiplexer; ++i) {
                this.writeNullableBlob(out, this.items[i]);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void writeNullableBlob(final DataOutputStream out, final byte[] data) throws Exception {
        try {
            out.writeInt(data.length);
            for (int j = 0; j < data.length; ++j) {
                out.writeByte(data[j]);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private byte[] readNullableBlob(final DataInputStream in) throws Exception {
        byte[] data = null;
        try {
            final int dataLength = in.readInt();
            if (dataLength > 0) {
                data = new byte[dataLength];
                for (int j = 0; j < data.length; ++j) {
                    data[j] = in.readByte();
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return data;
    }

    public void deserialize(final DataInputStream in) throws RecordStoreException, Exception {
        try {
            in.readByte();
            this.multiplexer = in.readInt();
            if (this.multiplexer < 1 || this.multiplexer > 100) {
                throw new RecordStoreException("15153Bad multiplexed record (invalid multiplexer)");
            }
            this.size = in.readInt();
            if (this.size < 0 || this.size > this.multiplexer) {
                throw new RecordStoreException("15153Bad multiplexed record (size too large)");
            }
            if (this.items == null || this.items.length != this.multiplexer) {
                this.items = new byte[this.multiplexer][];
            }
            for (int i = 0; i < this.multiplexer; ++i) {
                this.items[i] = this.readNullableBlob(in);
            }
        } catch (RecordStoreException e) {
            throw e;
        } catch (Exception e2) {
            throw e2;
        }
    }

    public void set(final int index, final byte[] data, final int offset, final int length) throws Exception {
        if (index >= 0 && index < this.multiplexer) {
            if (this.items[index] == null) {
                ++this.size;
            }
            System.arraycopy(data, offset, this.items[index] = new byte[length], 0, length);
            this.dirty = true;
            return;
        }
        throw new Exception("15150");
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public void setDirty(final boolean dirty) {
        this.dirty = dirty;
    }

    public void delete(final int index) throws Exception {
        if (index >= 0 && index < this.multiplexer) {
            if (this.items[index] != null) {
                --this.size;
            }
            this.items[index] = null;
            this.dirty = true;
            return;
        }
        throw new Exception("15151");
    }

    public byte[] get(final int index) throws Exception {
        if (index >= 0 && index < this.multiplexer) {
            return this.items[index];
        }
        throw new Exception("15152");
    }

    public int getSize(final int index) throws Exception {
        if (index >= 0 && index < this.multiplexer) {
            return (this.items[index] != null) ? this.items[index].length : 0;
        }
        throw new Exception("15152");
    }

    public Vector getValidIds(final int baseId) {
        final Vector list = new Vector(this.multiplexer);
        for (int i = 0; i < this.multiplexer; ++i) {
            if (this.items[i] != null) {
                list.addElement(new Integer(baseId + i));
            }
        }
        return list;
    }

    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("Record{").append("recordId=").append(this.recordId).append(", multiplexer=").append(this.multiplexer).append(", size=").append(this.size).append(", items={");
        for (int i = 0; i < this.multiplexer; ++i) {
            if (i > 0) {
                buffer.append(", ");
            }
            buffer.append((this.items[i] != null) ? this.items[i].length : 0);
        }
        buffer.append("}, dirty=").append(this.dirty).append('}');
        return buffer.toString();
    }

    public boolean isNull(final int offset) {
        if (offset > -1 && offset < this.multiplexer) {
            return this.items[offset] == null;
        }
        throw new IllegalArgumentException("Record.isNull( " + offset + ")");
    }
}
