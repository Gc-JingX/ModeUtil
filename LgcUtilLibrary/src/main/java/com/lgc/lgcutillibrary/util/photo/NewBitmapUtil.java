package com.lgc.lgcutillibrary.util.photo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * <pre>
 *     author : feijin_lgc
 *     e-mail : 595184932@qq.com
 *     time   : 2017/10/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class NewBitmapUtil {
    /**
     * 先按宽高比例大小缩小，再压缩为指定大小以下
     * 针对的是File
     *
     * @param srcPath      图片路径
     * @param targetWidth  目标宽度
     * @param targetHeight 目标高度
     * @param targetKbSize 多少kb以下
     * @return
     */
    public static Bitmap decodeAndCompress(String srcPath, float targetWidth, float targetHeight, int targetKbSize) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设为true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int startW = newOpts.outWidth;
        int startH = newOpts.outHeight;

        float targetH = targetHeight;//这里设置目标高度
        float targetW = targetWidth;//这里设置目标宽度
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int squareRate = 1;//be=1表示不缩放,squareRate只能是整数大于1的整数，如果是2表示四分之一
        if (startW > startH && startW > targetW) {//如果宽度大的话根据宽度固定大小缩放
            squareRate = (int) (newOpts.outWidth / targetW);
        } else if (startW < startH && startH > targetH) {//如果高度高的话根据宽度固定大小缩放
            squareRate = (int) (newOpts.outHeight / targetH);
        }
        if (squareRate <= 0) {
            squareRate = 1;
        }
        newOpts.inSampleSize = squareRate;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);


        return compressQuality(bitmap, targetKbSize);//压缩好比例大小后再进行质量压缩
    }

    /**
     * 先让图片缩放到多少内存以下，接着缩放，最后压缩
     * 针对的是bitmap
     * 此方法对比decodeAndCompress最大的好处就是针对特别大的图片，一开始就先把大小限制在指定范围以下
     *
     * @param image        bitmap
     * @param maxSize      界限，多少kb以下
     * @param targetWidth  目标宽
     * @param targetHeight 目标高
     * @param targetKbSize 最终压缩到多少kb以下
     * @return
     */

    public static Bitmap compressLimitDecodeCompress(Bitmap image, int maxSize, float targetWidth, float targetHeight, int targetKbSize) {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if ((baos.toByteArray().length / 1024) > maxSize) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设为了true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int startW = newOpts.outWidth;
        int startH = newOpts.outHeight;

        float targetW = targetWidth;
        float targetH = targetHeight;
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (startW > startH && startW > targetH) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / targetH);
        } else if (startW < startH && startH > targetW) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / targetW);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//降低图片从ARGB888到RGB565
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressQuality(bitmap, targetKbSize);//压缩好比例大小后再进行质量压缩
    }

    /**
     * 压缩图片质量到指定大小以下
     *
     * @param bitmap
     * @param targetKbSize
     * @return
     */
    private static Bitmap compressQuality(Bitmap bitmap, int targetKbSize) {
        // 将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        // 将字节换成KB
        double mid = b.length / 1024;
        // 判断bitmap占用空间是否大于允许最大空间 如果大于则压缩 小于则不压缩
        if (mid > targetKbSize) {
            // 获取bitmap大小 是允许最大大小的多少倍
            double i = mid / targetKbSize;
            // 开始压缩 此处用到平方根 将宽带和高度压缩掉对应的平方根倍
            bitmap = zoomImage(bitmap, bitmap.getWidth() / Math.sqrt(i),
                    bitmap.getHeight() / Math.sqrt(i));
        }
        return bitmap;

    }

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }


    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 300, 120);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 按比例缩放图片
     *
     * @param origin 原图
     * @param ratio  比例
     * @return 新的bitmap
     */
    public static Bitmap scaleBitmap(Bitmap origin, float ratio) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(ratio, ratio);
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }
//    public static Bitmap getSmallBitmap(String filePath) {
//        Bitmap bitmap=BitmapFactory.decodeFile(filePath);
//        File sourceFile = new File(filePath);
//        Bitmap newBitmap=bitmap;
//        if (sourceFile.length() / 1024 > 512) {
//            newBitmap= scaleBitmap(bitmap,0.3F);
//        }
//
//
//        return newBitmap;
//    }
}
