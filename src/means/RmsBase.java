// Decompiled with: CFR 0.151
// Class Version: 3
package means;

import java.util.Hashtable;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import model.PackageObject;

public class RmsBase {

    static RecordStore rs = null;

    public static boolean openRecord(String string) {
        boolean bl = false;
        try {
            rs = RecordStore.openRecordStore(string, true);
            bl = true;
        } catch (RecordStoreFullException recordStoreFullException) {
            recordStoreFullException.printStackTrace();
        } catch (RecordStoreNotFoundException recordStoreNotFoundException) {
            recordStoreNotFoundException.printStackTrace();
        } catch (RecordStoreException recordStoreException) {
            recordStoreException.printStackTrace();
        }
        return bl;
    }

    public static boolean colseRecord(String string) {
        boolean bl = false;
        try {
            rs = RecordStore.openRecordStore(string, true);
            bl = true;
        } catch (RecordStoreFullException recordStoreFullException) {
            recordStoreFullException.printStackTrace();
        } catch (RecordStoreNotFoundException recordStoreNotFoundException) {
            recordStoreNotFoundException.printStackTrace();
        } catch (RecordStoreException recordStoreException) {
            recordStoreException.printStackTrace();
        }
        return bl;
    }

    public static boolean addRecord(String string, byte[] byArray) {
        boolean bl = false;
        try {
            rs = RecordStore.openRecordStore(string, true);
            int n = rs.addRecord(byArray, 0, byArray.length);
            rs.closeRecordStore();
            bl = true;
        } catch (RecordStoreFullException recordStoreFullException) {
            recordStoreFullException.getMessage();
        } catch (RecordStoreException recordStoreException) {
            recordStoreException.getMessage();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return bl;
    }

    public static boolean setRecord(String string, int n, byte[] byArray) {
        boolean bl = false;
        RecordEnumeration recordEnumeration = null;
        try {
            rs = RecordStore.openRecordStore(string, false);
            recordEnumeration = rs.enumerateRecords(null, null, false);
            rs.setRecord(n, byArray, 0, byArray.length);
            bl = true;
            rs.closeRecordStore();
        } catch (RecordStoreFullException recordStoreFullException) {
            recordStoreFullException.getMessage();
        } catch (RecordStoreException recordStoreException) {
            recordStoreException.getMessage();
        } catch (SecurityException securityException) {
            securityException.getMessage();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return bl;
    }

    public static int getNumOfRecords(String string) {
        try {
            rs = RecordStore.openRecordStore(string, true);
            return rs.getNumRecords();
        } catch (Exception exception) {
            return 0;
        }
    }

    public static Object[] getRecords(String string) {
        Object[] objectArray = null;
        try {
            rs = RecordStore.openRecordStore(string, false);
            RecordEnumeration recordEnumeration = rs.enumerateRecords(null, null, false);
            objectArray = new Object[rs.getNumRecords()];
            for (int i = 0; i < objectArray.length; ++i) {
                int n = recordEnumeration.previousRecordId();
                objectArray[i] = rs.getRecord(n);
            }
            rs.closeRecordStore();
        } catch (Exception exception) {
            // empty catch block
        }
        return objectArray;
    }

    public static Hashtable getRecordsHT(String string) {
        Hashtable hashtable = null;
        Object var2_2 = null;
        try {
            rs = RecordStore.openRecordStore(string, true);
            RecordEnumeration recordEnumeration = rs.enumerateRecords(null, null, false);
            int n = rs.getNumRecords();
            hashtable = new Hashtable(n);
            for (int i = 0; i < n; ++i) {
                byte[] byArray = recordEnumeration.nextRecord();
                PackageObject packageObject = new PackageObject(byArray);
                hashtable.put(new Integer(i), packageObject);
            }
            rs.closeRecordStore();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return hashtable;
    }

    public static Object getRecord(String string, int n) {
        byte[] byArray = null;
        try {
            rs = RecordStore.openRecordStore(string, false);
            RecordEnumeration recordEnumeration = rs.enumerateRecords(null, null, false);
            byArray = rs.getRecord(n);
            rs.closeRecordStore();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return byArray;
    }

public static int getIndex(final String s, final int n) {
        final PackageObject packageObject = null;
        try {
            RmsBase.rs = RecordStore.openRecordStore(s, false);
            final RecordEnumeration enumerateRecords = RmsBase.rs.enumerateRecords(null, null, false);
            for (int i = 0; i < getNumOfRecords(s); ++i) {
                final int nextRecordId = enumerateRecords.nextRecordId();
                if (packageObject.intId == n) {
                    return nextRecordId;
                }
            }
        }
        catch (Exception ex) {}
        return 1;
    }

    public static boolean deletRecord(String string) {
        boolean bl = false;
        try {
            RecordStore.deleteRecordStore(string);
            bl = true;
        } catch (RecordStoreFullException recordStoreFullException) {
            recordStoreFullException.printStackTrace();
        } catch (RecordStoreNotFoundException recordStoreNotFoundException) {
        } catch (RecordStoreException recordStoreException) {
            recordStoreException.printStackTrace();
        }
        return bl;
    }
}
