package com.images;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import sun.awt.image.JPEGImageDecoder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageSizeTest {
	/**
	 * @param args
	 * @throws AWTException
	 */
	public void getImageSize() throws AWTException {
		java.awt.Robot rb = new java.awt.Robot();
		Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rt = new Rectangle(0, 0, (int) d.getWidth(), (int) d
				.getHeight());
		for (int i = 0; i < 1000; i++) {
			BufferedImage image = rb.createScreenCapture(rt);
			// bufferedImageTobytes(image, "gif");
			giftest(bufferedImageTobytes(image, "jpeg"));
			giftest(bufferedImageTobytes(image, 0.2f));
			giftest(newCompressImage(image, 0.2f));

		}
	}

	/**
	 * 用Format对应格式中ImageIO默认参数把IMAGE打包成BYTE[]
	 * 
	 * @param image
	 * @return
	 */
	private byte[] bufferedImageTobytes(BufferedImage image, String format) {
		System.out.println(format + "格式开始打包" + getCurrentTime());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, format, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(format + "格式完成打包-----" + getCurrentTime()
				+ "----lenth------" + out.toByteArray().length);
		return out.toByteArray();
	}

	/**
	 * 
	 * 自己设置压缩质量来把图片压缩成byte[]
	 * 
	 * @param image
	 *            压缩源图片
	 * @param quality
	 *            压缩质量，在0-1之间，
	 * @return 返回的字节数组
	 */
	private byte[] bufferedImageTobytes(BufferedImage image, float quality) {
		System.out.println("jpeg" + quality + "质量开始打包" + getCurrentTime());
		// 如果图片空，返回空
		if (image == null) {
			return null;
		}
		// 得到指定Format图片的writer
		Iterator<ImageWriter> iter = ImageIO
				.getImageWritersByFormatName("jpeg");// 得到迭代器
		ImageWriter writer = (ImageWriter) iter.next(); // 得到writer

		// 得到指定writer的输出参数设置(ImageWriteParam )
		ImageWriteParam iwp = writer.getDefaultWriteParam();
		iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
		iwp.setCompressionQuality(quality); // 设置压缩质量参数

		iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		iwp.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,
				colorModel.createCompatibleSampleModel(16, 16)));

		// 开始打包图片，写入byte[]
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
		IIOImage iIamge = new IIOImage(image, null, null);
		try {
			// 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
			// 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
			writer.setOutput(ImageIO
					.createImageOutputStream(byteArrayOutputStream));
			writer.write(null, iIamge, iwp);
		} catch (IOException e) {
			System.out.println("write errro");
			e.printStackTrace();
		}
		System.out.println("jpeg" + quality + "质量完成打包-----" + getCurrentTime()
				+ "----lenth----" + byteArrayOutputStream.toByteArray().length);
		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 自己定义格式，得到当前系统时间
	 * 
	 * @return
	 */
	private String getCurrentTime() {
		Calendar c = new GregorianCalendar();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int millsecond = c.get(Calendar.MILLISECOND);
		String time = hour + "点" + min + "分" + second + "秒" + millsecond;
		return time;
	}

	/**
	 * 通过 com.sun.image.codec.jpeg.JPEGCodec提供的编码器来实现图像压缩
	 * 
	 * @param image
	 * @param quality
	 * @return
	 */
	private byte[] newCompressImage(BufferedImage image, float quality) {
		// 如果图片空，返回空
		if (image == null) {
			return null;
		}
		// 开始开始，写入byte[]
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
		// 设置压缩参数
		JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(image);
		param.setQuality(quality, false);
		// 设置编码器
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(
				byteArrayOutputStream, param);
		System.out.println("newCompressive" + quality + "质量开始打包"
				+ getCurrentTime());
		try {
			encoder.encode(image);
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		System.out.println("newCompressive" + quality + "质量打包完成"
				+ getCurrentTime() + "----lenth----"
				+ byteArrayOutputStream.toByteArray().length);
		return byteArrayOutputStream.toByteArray();

	}

	/**
	 * 测试把图片先压缩成JPEG，再用JPEG压缩成GIF
	 */
	public byte[] giftest(byte[] imagedata) {
		System.out.println("giftest开始打包" + getCurrentTime());
		BufferedImage image = null;
		ByteArrayInputStream input = new ByteArrayInputStream(imagedata);
		// 得到解码器
		JPEGImageDecoder decoder = (JPEGImageDecoder) JPEGCodec
				.createJPEGDecoder(input);
		// 把JPEG 数据流解压缩
		try {
			image = ((com.sun.image.codec.jpeg.JPEGImageDecoder) decoder)
					.decodeAsBufferedImage();
		} catch (Exception ef) {
			ef.printStackTrace();
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "gif", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("giftest开始打包" + getCurrentTime() + "----lenth----"
				+ out.toByteArray().length);
		return out.toByteArray();
	}

	public static void main(String args[]) throws Exception {
		ImageSizeTest test = new ImageSizeTest();
		test.getImageSize();
	}

}
