package com.ivy.device;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.android.client.AndroidSdk;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DeviceUtil {

    private static DeviceUtil instance = null;
    private Bundle bundle = new Bundle();

    private DeviceUtil() {
        getCpuInfo();
    }

    public static DeviceUtil getInstance() {
        if (instance == null) {
            synchronized (DeviceUtil.class) {
                if (instance == null) {
                    instance = new DeviceUtil();
                }
            }
        }
        return instance;
    }

    public Bundle getData(){
        return bundle;
    }

    private final FileFilter CPU_FILTER = new FileFilter() { // from class: com.hwbx.game.common.utils.MaInitInfoUtils.1
        @Override // java.io.FileFilter
        public boolean accept(File pathname) {
            String name = pathname.getName();
            if (!name.startsWith("cpu")) {
                return false;
            }
            for (int i = 3; i < name.length(); i++) {
                if (name.charAt(i) < '0' || name.charAt(i) > '9') {
                    return false;
                }
            }
            return true;
        }
    };

    public int getCPU() {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            return new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
        } catch (NullPointerException | SecurityException unused) {
            return -1;
        }
    }

    public int getCPUMaxFreqKHz() {
        int i = -1;
        for (int i2 = 0; i2 < getCPU(); i2++) {
            try {
                File file = new File("/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/cpuinfo_max_freq");
                if (file.exists()) {
                    byte[] bArr = new byte[128];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        fileInputStream.read(bArr);
                        int i3 = 0;
                        while (bArr[i3] >= 48 && bArr[i3] <= 57 && i3 < 128) {
                            i3++;
                        }
                        Integer valueOf = Integer.valueOf(Integer.parseInt(new String(bArr, 0, i3)));
                        if (valueOf.intValue() > i) {
                            i = valueOf.intValue();
                        }
                    } catch (NumberFormatException unused) {
                    } catch (Throwable th) {
                        fileInputStream.close();
                        throw th;
                    }
                    fileInputStream.close();
                }
            } catch (IOException unused2) {
                return -1;
            }
        }
        return i;
    }

    public int getCPUMinFreqKHz() {
        int i = -1;
        for (int i2 = 0; i2 < getCPU(); i2++) {
            try {
                File file = new File("/sys/devices/system/cpu/cpu" + i2 + "/cpufreq/cpuinfo_min_freq");
                if (file.exists()) {
                    byte[] bArr = new byte[128];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        fileInputStream.read(bArr);
                        int i3 = 0;
                        while (bArr[i3] >= 48 && bArr[i3] <= 57 && i3 < 128) {
                            i3++;
                        }
                        Integer valueOf = Integer.valueOf(Integer.parseInt(new String(bArr, 0, i3)));
                        if (i == -1) {
                            i = valueOf.intValue();
                        } else if (valueOf.intValue() < i) {
                            i = valueOf.intValue();
                        }
                    } catch (NumberFormatException unused) {
                    } catch (Throwable th) {
                        fileInputStream.close();
                        throw th;
                    }
                    fileInputStream.close();
                }
            } catch (IOException unused2) {
                return -1;
            }
        }
        return i;
    }


    private float byteToMB(long b){
        return b / 1024.f / 1024.f;
    }

    public float byteToGB(long b){
        return b / 1024.f / 1024.f / 1024.f;
    }

    public void getCpuInfo() {
        int cpuMaxFreq = getCPUMaxFreqKHz();
        if (cpuMaxFreq != -1) {
            bundle.putInt("cpu_max_freq", cpuMaxFreq);
        }
        int cpuMinFreq = getCPUMinFreqKHz();
        if (cpuMinFreq != -1) {
            bundle.putInt("cpu_min_freq", cpuMinFreq);
        }
        long totalMemory = AndroidSdk.getTotalMem();
        bundle.putFloat("total_memory", byteToMB(totalMemory))                   ;
        long freeMemory = AndroidSdk.getFreeMem();
        bundle.putFloat("free_memory", byteToMB(freeMemory));
        int cpu_core_count = getCPU();
        bundle.putLong("cpu_core_count", cpu_core_count);
        String[] DATA = {"/system/bin/cat", "/proc/cpuinfo"};
        ProcessBuilder processBuilder;
        Process process;
        byte[] byteArray;
        InputStream inputStream;
        byteArray = new byte[1024];
        StringBuffer buffer = new StringBuffer();
        try {
            processBuilder = new ProcessBuilder(DATA);
            process = processBuilder.start();
            inputStream = process.getInputStream();
            int length = -1;
            while ((length = inputStream.read(byteArray)) > 0) {
                buffer.append(new String(byteArray, 0, length));
            }
            inputStream.close();
            String result = buffer.toString();
            result = result.replace("\t", "");
            String[] lines = result.split("\n");
            for (String lineItem : lines) {
                String[] line = lineItem.split(":");
                if (line.length == 2) {
                    String key = line[0].toLowerCase();
                    if (key.equals("hardware")) {
                        bundle.putString("hardware", line[1]);
                    } else if (key.equals("cpu architecture")) {
                        bundle.putString("cpu_architecture", line[1]);
                    }
                }
            }
            Log.e("ADSFALL", bundle.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}