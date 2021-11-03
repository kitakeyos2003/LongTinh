// 
// Decompiled by Procyon v0.5.36
// 
package base;

import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import javax.microedition.rms.RecordStore;
import javax.microedition.lcdui.Canvas;

public class getSoftKeyCode {

    private int[] intKeyCod;
    private String[] strKeyCodeName;
    private byte[] bytIndex;
    private Canvas canvas;

    public getSoftKeyCode(final Canvas _canvas) {
        this.canvas = _canvas;
        this.strKeyCodeName = new String[]{"SOFT1", "SOFT2", "SELECT", "UP", "DOWN", "LEFT", "RIGHT"};
        this.intKeyCod = new int[]{-6, -7, -5, -1, -2, -3, -4};
    }

    public int[] getKeylCode() {
        if (!this.loadKeyCode()) {
            this.setSoftKeyCode();
            this.saveKeyCode();
        }
        return this.intKeyCod;
    }

    private void saveKeyCode() {
        try {
            RecordStore rs = RecordStore.openRecordStore("KEY", true);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream dos = new DataOutputStream(baos);
            for (int i = 0; i < this.intKeyCod.length; ++i) {
                dos.writeInt(this.intKeyCod[i]);
            }
            if (rs.getNumRecords() == 0) {
                rs.addRecord(baos.toByteArray(), 0, baos.toByteArray().length);
            } else {
                rs.setRecord(1, baos.toByteArray(), 0, baos.toByteArray().length);
            }
            rs.closeRecordStore();
            rs = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean loadKeyCode() {
        boolean type = false;
        try {
            RecordStore rs = RecordStore.openRecordStore("KEY", true);
            if (rs.getNumRecords() != 0) {
                final byte[] buffer = rs.getRecord(1);
                final ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                final DataInputStream dis = new DataInputStream(bais);
                for (int i = 0; i < this.intKeyCod.length; ++i) {
                    this.intKeyCod[i] = dis.readInt();
                }
                type = true;
            }
            rs.closeRecordStore();
            rs = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    private void setSoftKeyCode() {
        try {
            Class.forName("com.nokia.mid.ui.FullCanvas");
            this.intKeyCod = new int[]{-6, -7, -5, -1, -2, -3, -4};
        } catch (Exception ex) {
            final boolean[] _type = new boolean[this.strKeyCodeName.length];
            byte _lenght = (byte) this.strKeyCodeName.length;
            String _strGetKeyName = "SOFT1";
            byte _nameIndex = this.check(_strGetKeyName, -6);
            if (_nameIndex == -1) {
                _nameIndex = this.check(_strGetKeyName, 21);
                if (_nameIndex == -1) {
                    _nameIndex = this.check(_strGetKeyName, -21);
                    if (_nameIndex == -1) {
                        _nameIndex = this.check(_strGetKeyName, -10);
                    }
                }
            }
            if (_nameIndex != -1) {
                _type[_nameIndex] = true;
                --_lenght;
            }
            _strGetKeyName = "SOFT2";
            _nameIndex = this.check(_strGetKeyName, -7);
            if (_nameIndex == -1) {
                _nameIndex = this.check(_strGetKeyName, 22);
                if (_nameIndex == -1) {
                    _nameIndex = this.check(_strGetKeyName, -22);
                    if (_nameIndex == -1) {
                        _nameIndex = this.check(_strGetKeyName, -11);
                    }
                }
            }
            if (_nameIndex != -1) {
                _type[_nameIndex] = true;
                --_lenght;
            }
            _strGetKeyName = "SELECT";
            _nameIndex = this.check(_strGetKeyName, -5);
            if (_nameIndex == -1) {
                _nameIndex = this.check(_strGetKeyName, 20);
                if (_nameIndex == -1) {
                    _nameIndex = this.check(_strGetKeyName, -20);
                }
            }
            if (_nameIndex != -1) {
                _type[_nameIndex] = true;
                --_lenght;
            }
            _strGetKeyName = "UP";
            _nameIndex = this.check(_strGetKeyName, -1);
            if (_nameIndex != -1) {
                _type[_nameIndex] = true;
                --_lenght;
            }
            _strGetKeyName = "DOWN";
            _nameIndex = this.check(_strGetKeyName, -2);
            if (_nameIndex == -1) {
                _nameIndex = this.check(_strGetKeyName, -6);
            }
            if (_nameIndex != -1) {
                _type[_nameIndex] = true;
                --_lenght;
            }
            _strGetKeyName = "LEFT";
            _nameIndex = this.check(_strGetKeyName, -3);
            if (_nameIndex == -1) {
                _nameIndex = this.check(_strGetKeyName, -2);
            }
            if (_nameIndex != -1) {
                _type[_nameIndex] = true;
                --_lenght;
            }
            _strGetKeyName = "RIGHT";
            _nameIndex = this.check(_strGetKeyName, -4);
            if (_nameIndex == -1) {
                _nameIndex = this.check(_strGetKeyName, -5);
            }
            if (_nameIndex != -1) {
                _type[_nameIndex] = true;
                --_lenght;
            }
            if (_lenght > 0) {
                this.bytIndex = new byte[_lenght];
                byte _t = 0;
                for (byte i = 0; i < _type.length; ++i) {
                    if (!_type[i]) {
                        this.bytIndex[_t] = i;
                        ++_t;
                    }
                }
                for (int j = -127; j < 127; ++j) {
                    try {
                        _strGetKeyName = this.canvas.getKeyName(j).toUpperCase();
                    } catch (Exception e) {
                        continue;
                    }
                    byte l = 0;
                    while (l < this.bytIndex.length) {
                        if (_strGetKeyName.indexOf(this.strKeyCodeName[this.bytIndex[l]]) != -1) {
                            this.intKeyCod[this.bytIndex[l]] = j;
                            if (this.bytIndex.length == 1) {
                                return;
                            }
                            this.bytArray(l);
                            break;
                        } else {
                            ++l;
                        }
                    }
                }
            }
        }
    }

    private byte check(final String _strGetKeyName, final int _key) {
        try {
            if (this.canvas.getKeyName(_key).toUpperCase().indexOf(_strGetKeyName) != -1) {
                final byte _nameIndex = this.getNameIndex(_strGetKeyName);
                this.intKeyCod[_nameIndex] = _key;
                return _nameIndex;
            }
        } catch (Exception ex) {
        }
        return -1;
    }

    private byte getNameIndex(final String _s) {
        for (byte i = 0; i < this.strKeyCodeName.length; ++i) {
            if (this.strKeyCodeName[i].equals(_s)) {
                return i;
            }
        }
        return -1;
    }

    private void bytArray(final byte l) {
        if (this.bytIndex.length == 1) {
            this.bytIndex = null;
            return;
        }
        final byte[] _tempIndex = this.bytIndex;
        this.bytIndex = new byte[_tempIndex.length - 1];
        if (l != 0) {
            System.arraycopy(_tempIndex, 0, this.bytIndex, 0, l);
        }
        if (l != _tempIndex.length - 1) {
            System.arraycopy(_tempIndex, l + 1, this.bytIndex, l, _tempIndex.length - l - 1);
        }
    }
}
