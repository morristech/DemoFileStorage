package com.icetea09.com.demofilestorage;

import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Trinh Le on 7/19/2015.
 */
public class FileStorageHelper {

    private static final String TAG = FileStorageHelper.class.getSimpleName();

    public static void writeToInternalStorage(Context context, String fileName, byte[] data)
            throws IOException {
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        fos.write(data);
        fos.close();
    }

    public static byte[] readFromInternalStorage(Context context, String fileName)
            throws IOException {
        FileInputStream fis = context.openFileInput(fileName);
        byte[] result = getBytesArrayFromInputStream(fis);
        fis.close();
        return result;
    }

    private static byte[] getBytesArrayFromInputStream(InputStream input) throws IOException {
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public static void writeToExternalPublicFile(String dirType, String fileName, byte[] data)
            throws IOException {
        File file = new File(Environment.getExternalStoragePublicDirectory(dirType), fileName);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
    }

    public static void writeToExternalPrivateFile(Context context, String dirType, String fileName, byte[] data)
            throws IOException {
        File file = new File(context.getExternalFilesDir(dirType), fileName);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
    }

    public static byte[] readFromExternalPublicFile(String dirType, String fileName)
            throws IOException {
        File file = new File(Environment.getExternalStoragePublicDirectory(dirType), fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] result = getBytesArrayFromInputStream(fis);
        fis.close();
        return result;
    }

    public static byte[] readFromExternalPrivateFile(Context context, String dirType, String fileName)
            throws IOException {
        File file = new File(context.getExternalFilesDir(dirType), fileName);
        FileInputStream fis = new FileInputStream(file);
        byte[] result = getBytesArrayFromInputStream(fis);
        fis.close();
        return result;
    }
}
