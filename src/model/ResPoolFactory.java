// 
// Decompiled by Procyon v0.5.36
// 
package model;

import java.util.Enumeration;
import means.ImageManager;
import means.DebugFrame;
import means.QSpriteRes;
import base.Macro;
import java.util.Hashtable;
import java.util.Vector;

public class ResPoolFactory {

    private static ResPoolFactory resPoolFactoryInstance;
    public static int[] POOL_SIZE;
    public static byte POOL_POSITION_NONE;
    public static byte POOL_POSITION_ANY;
    Vector[] vec_res_pool_key;
    Hashtable[] ht_res_pool_data;

    static {
        ResPoolFactory.resPoolFactoryInstance = null;
        ResPoolFactory.POOL_SIZE = new int[]{16, 30, 10, 100, 60, Macro.LEVEL_FUNCTION_POOL_SIZE[1] + 1 + 1 + 2, Macro.LEVEL_FUNCTION_POOL_SIZE[2] + 1 + 5 + 5, Macro.LEVEL_FUNCTION_POOL_SIZE[0] * 2};
        ResPoolFactory.POOL_POSITION_NONE = -2;
        ResPoolFactory.POOL_POSITION_ANY = -1;
    }

    private ResPoolFactory() {
        this._init();
    }

    public static synchronized ResPoolFactory getInstance() {
        if (ResPoolFactory.resPoolFactoryInstance == null) {
            ResPoolFactory.resPoolFactoryInstance = new ResPoolFactory();
        }
        return ResPoolFactory.resPoolFactoryInstance;
    }

    private void _init() {
        this.vec_res_pool_key = new Vector[8];
        this.ht_res_pool_data = new Hashtable[8];
        for (int index = 0; index < 8; ++index) {
            this.vec_res_pool_key[index] = new Vector(1);
            this.ht_res_pool_data[index] = new Hashtable(1);
        }
    }

    public boolean isResExist(final int poolType, final String key) {
        for (int index = 0; index < this.vec_res_pool_key[poolType].size(); ++index) {
            final Element_key elemKey = (Element_key) this.vec_res_pool_key[poolType].elementAt(index);
            if (elemKey.res_key.equals(key) && this.ht_res_pool_data[poolType].containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    public synchronized QSpriteRes getRes(final int poolType, final String key) {
        if (this.isResExist(poolType, key)) {
            return (QSpriteRes) this.ht_res_pool_data[poolType].get(key);
        }
        return null;
    }

    private boolean _isPoolAvailableForRes(final int poolType, final String key, final int priority, final byte type) {
        boolean success = false;
        for (int index = 0; index < this.vec_res_pool_key[poolType].size(); ++index) {
            this._assertKey_Res(poolType, key);
            final Element_key elemKey = (Element_key) this.vec_res_pool_key[poolType].elementAt(index);
            if (elemKey.res_key.equals(key)) {
                success = true;
            }
        }
        if (!success) {
            if (this.vec_res_pool_key[poolType].size() < ResPoolFactory.POOL_SIZE[poolType]) {
                success = true;
            } else {
                final int minElemKey = this._getMinPriority(poolType);
                if (priority < minElemKey) {
                    DebugFrame.getInstance().logIn("Tip! \u68c0\u6d4b\uff0c\u6c60\u5bf9\u4e8e\u8d44\u6e90\u68c0\u6d4b\u53d1\u73b0\uff0cpriority\u592a\u5c0f\u65e0\u6cd5\u63d2\u5165!");
                } else {
                    success = true;
                }
            }
        }
        return success;
    }

    public synchronized boolean pool_insert(final int poolType, final String key, final int priority, final byte type) {
        boolean success = false;
        for (int index = 0; index < this.vec_res_pool_key[poolType].size(); ++index) {
            this._assertKey_Res(poolType, key);
            final Element_key elemKey = (Element_key) this.vec_res_pool_key[poolType].elementAt(index);
            if (elemKey.res_key.equals(key)) {
                final Element_key element_key = elemKey;
                element_key.res_priority += priority;
                this._sortResPool(poolType, index);
                success = true;
            }
        }
        if (!success) {
            if (this.vec_res_pool_key[poolType].size() < ResPoolFactory.POOL_SIZE[poolType]) {
                final int appendPos = this._pool_append(poolType, key, priority, type);
                if (appendPos >= 0) {
                    this._sortResPool(poolType, appendPos);
                    success = true;
                }
            } else {
                final int minElemKey = this._getMinPriority(poolType);
                if (priority < minElemKey) {
                    DebugFrame.getInstance().logIn("Warning! Priority \u592a\u5c0f\u65e0\u6cd5\u63d2\u5165!");
                } else {
                    final boolean popSucc = this._pop(poolType);
                    if (popSucc) {
                        final int appendPos2 = this._pool_append(poolType, key, priority, type);
                        if (appendPos2 >= 0) {
                            this._sortResPool(poolType, appendPos2);
                            success = true;
                        }
                    } else {
                        DebugFrame.getInstance().logIn("Error! \u8d44\u6e90\u6c60\u5f39\u51fa\u8d44\u6e90\u5931\u8d25!");
                    }
                }
            }
        }
        this._PrintResPoolDebugInfo(poolType);
        return success;
    }

    private boolean _pool_update(final int poolType, final String key, final int priority, final byte type) {
        boolean success = false;
        for (int index = 0; index < this.vec_res_pool_key[poolType].size(); ++index) {
            this._assertKey_Res(poolType, key);
            final Element_key elemKey = (Element_key) this.vec_res_pool_key[poolType].elementAt(index);
            if (elemKey.res_key.equals(key)) {
                final Element_key element_key = elemKey;
                element_key.res_priority -= priority;
                this._sortResPool(poolType, index);
                success = true;
            }
        }
        this._PrintResPoolDebugInfo(poolType);
        return success;
    }

    private int _getMinPriority(final int poolType) {
        return ((Element_key) this.vec_res_pool_key[poolType].elementAt(0)).res_priority;
    }

    private int _getResKeyPosition(final int poolType, final String key) {
        for (int index = 0; index < this.vec_res_pool_key[poolType].size(); ++index) {
            final Element_key elemKey = (Element_key) this.vec_res_pool_key[poolType].elementAt(index);
            if (elemKey.res_key.equals(key)) {
                return index;
            }
        }
        return -1;
    }

    private boolean _getResDataPosition(final int poolType, final String key) {
        return this.ht_res_pool_data[poolType].containsKey(key);
    }

    private boolean _assertKey_Res(final int poolType, final String key) {
        boolean assertRes = true;
        final boolean keyExist = this._getResKeyPosition(poolType, key) >= 0;
        final boolean resExist = this._getResDataPosition(poolType, key);
        if (keyExist != resExist) {
            String info = "Assert \u9519\u8bef!";
            if (keyExist && !resExist) {
                info = String.valueOf(info) + "Key \u5b58\u5728\uff0c res\u4e0d\u5b58\u5728";
            } else if (!keyExist && resExist) {
                info = String.valueOf(info) + "Key \u4e0d\u5b58\u5728\uff0c res\u5b58\u5728";
            }
            DebugFrame.getInstance().logIn(info);
            assertRes = false;
        }
        return assertRes;
    }

    private void _sortResPool(final int poolType, final int poolIndex) {
        final Element_key elemKey = (Element_key) this.vec_res_pool_key[poolType].elementAt(poolIndex);
        this.vec_res_pool_key[poolType].removeElementAt(poolIndex);
        if (this.vec_res_pool_key[poolType].isEmpty()) {
            this.vec_res_pool_key[poolType].addElement(elemKey);
        } else {
            for (int index = 0; index < this.vec_res_pool_key[poolType].size(); ++index) {
                final Element_key elemKeyTmp = (Element_key) this.vec_res_pool_key[poolType].elementAt(index);
                if (!elemKeyTmp.res_key.equals(elemKey.res_key)) {
                    if (elemKey.res_priority > elemKeyTmp.res_priority) {
                        this.vec_res_pool_key[poolType].insertElementAt(elemKey, index);
                        break;
                    }
                    if (index == this.vec_res_pool_key[poolType].size() - 1) {
                        this.vec_res_pool_key[poolType].addElement(elemKey);
                        break;
                    }
                }
            }
        }
    }

    private int _pool_append(final int poolType, final String key, final int priority, final byte type) {
        int appendPos = -1;
        this._assertKey_Res(poolType, key);
        if (!this._getResDataPosition(poolType, key)) {
            QSpriteRes data = null;
            if (type == 2) {
                data = ImageManager.CreatSpriteData(poolType, key);
            } else if (type == 1) {
                data = ImageManager.CreatSpritePng(poolType, key);
            }
            if (data != null) {
                this.ht_res_pool_data[poolType].put(key, data);
                final Element_key elemKey = new Element_key(key, priority, type);
                this.vec_res_pool_key[poolType].addElement(elemKey);
                appendPos = this.vec_res_pool_key[poolType].size() - 1;
                this._assertKey_Res(poolType, key);
            }
        } else {
            final String info = "Error! _pool_append \u8d44\u6e90\u5df2\u5b58\u5728!";
            DebugFrame.getInstance().logIn(info);
        }
        return appendPos;
    }

    private boolean _pop(final int poolType) {
        boolean success = false;
        if (!this.vec_res_pool_key[poolType].isEmpty()) {
            final Element_key elem_key = (Element_key) this.vec_res_pool_key[poolType].firstElement();
            if (elem_key != null) {
                this._assertKey_Res(poolType, elem_key.res_key);
                if (this.ht_res_pool_data[poolType].containsKey(elem_key.res_key)) {
                    this.ht_res_pool_data[poolType].remove(elem_key.res_key);
                    this.vec_res_pool_key[poolType].removeElementAt(0);
                    this._assertKey_Res(poolType, elem_key.res_key);
                    success = true;
                } else {
                    final String info = "Warning! Hashtable not exist!";
                    DebugFrame.getInstance().logIn(info);
                }
            } else {
                final String info = "Warning! \u65e0\u6cd5\u53d6\u5f97\u9996\u4f4d\u7f6ekey!";
                DebugFrame.getInstance().logIn(info);
            }
        }
        return success;
    }

    public void ClearAllPool() {
        for (int poolType = 0; poolType < 8; ++poolType) {
            this.ClearPool(poolType);
        }
    }

    public void ClearPool(final int poolType) {
        this.ClearPool(poolType, null);
    }

    public synchronized void ClearPool(final int poolType, final Hashtable excepSpr) {
        if (excepSpr == null || excepSpr.isEmpty()) {
            this.vec_res_pool_key[poolType].removeAllElements();
            this.ht_res_pool_data[poolType].clear();
        } else {
            for (int index = 0; index < this.vec_res_pool_key[poolType].size(); ++index) {
                final Element_key elemKeyTmp = (Element_key) this.vec_res_pool_key[poolType].elementAt(index);
                if (!excepSpr.containsKey(elemKeyTmp.res_key)) {
                    this.vec_res_pool_key[poolType].removeElementAt(index);
                }
            }
            final Enumeration sprEnum = this.ht_res_pool_data[poolType].keys();
            while (sprEnum.hasMoreElements()) {
                final String sprKey = (String) sprEnum.nextElement();
                if (excepSpr.containsKey(sprKey)) {
                    continue;
                }
                this.ht_res_pool_data[poolType].remove(sprKey);
            }
        }
    }

    private void _PrintResPoolDebugInfo(final int poolType) {
        if (Macro.BLN_RES_POOL_ADVANCED_Debug) {
            String info = "********************************************";
            DebugFrame.getInstance().logIn(info);
            info = "PoolType:" + poolType + "/ Size:" + this.vec_res_pool_key[poolType].size();
            DebugFrame.getInstance().logIn(info);
            for (int index = 0; index < this.vec_res_pool_key[poolType].size(); ++index) {
                final Element_key elemKeyTmp = (Element_key) this.vec_res_pool_key[poolType].elementAt(index);
                info = "Index:" + index + "/Key:" + elemKeyTmp.res_key + " /Priority:" + elemKeyTmp.res_priority;
                DebugFrame.getInstance().logIn(info);
                if (this.ht_res_pool_data[poolType].containsKey(elemKeyTmp.res_key)) {
                    final QSpriteRes spriteRes = (QSpriteRes) this.ht_res_pool_data[poolType].get(elemKeyTmp.res_key);
                    info = "Res ->Id:" + spriteRes.spriteId + " /Type:" + spriteRes.res_type;
                } else {
                    info = "Res ->" + elemKeyTmp.res_key + "Error! \u4e0d\u5b58\u5728!";
                }
                DebugFrame.getInstance().logIn(info);
                info = "********************";
                DebugFrame.getInstance().logIn(info);
            }
        }
    }

    class Element_key {

        int res_priority;
        String res_key;
        byte res_type;

        public Element_key(final String res_key, final int res_priority, final byte res_type) {
            this.res_priority = res_priority;
            this.res_key = res_key;
            this.res_type = res_type;
        }
    }
}
