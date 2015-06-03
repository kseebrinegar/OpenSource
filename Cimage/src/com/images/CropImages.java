package com.images;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

public class CropImages {
	public void splitImage(HttpServletRequest request,
			HttpServletResponse response) {

		// 1,创建图片源
		BufferedImage borderPicture = createSourceImage(request
				.getParameter("bordersrc"));// 边框图片
		BufferedImage sourcePicture = createSourceImage(request
				.getParameter("picsrc"));// 艺术图片
		// 2,获取原始边框宽度
		int borderWidth = Integer.parseInt(request.getParameter("bw"));
		BufferedImage resetPicture = null;

		if (isCustomImagesWriteOut(request)) {
			// (1)自定义边框大小,和,输出大小
			int customBorder = Integer.parseInt(request
					.getParameter("border_w"));
			int customOutWidth = Integer
					.parseInt(request.getParameter("dst_w"));
			int customOutHeigt = Integer
					.parseInt(request.getParameter("dst_h"));

			resetPicture = new BufferedImage(sourcePicture.getWidth()
					+ customBorder * 2, sourcePicture.getHeight()
					+ customBorder * 2, sourcePicture.getType());
			Graphics rg = resetPicture.getGraphics();

			customdrawImage(rg, borderPicture, borderWidth, resetPicture,
					sourcePicture, customBorder);

			resetPicture = resetImageSize(customOutWidth, customOutHeigt,
					resetPicture);
			rg.dispose();

		} else {
			// (1)默认边框大小,和,默认输出大小
			resetPicture = new BufferedImage(sourcePicture.getWidth()
					+ borderWidth * 2, sourcePicture.getHeight() + borderWidth
					* 2, sourcePicture.getType());

			Graphics rg = resetPicture.getGraphics();
			drawImage(rg, borderPicture, borderWidth, resetPicture,
					sourcePicture);
			rg.dispose();
		}

		writeOutImageStream(resetPicture, response);
	}

	private BufferedImage resetImageSize(int customOutWidth,
			int customOutHeigt, BufferedImage resetPicture) {
		BufferedImage resizePic = new BufferedImage(customOutWidth,
				customOutHeigt, resetPicture.getType());
		Graphics g = resizePic.getGraphics();
		g.drawImage(resetPicture, 0, 0, customOutWidth, customOutHeigt, 0, 0,
				resetPicture.getWidth(), resetPicture.getHeight(), null);
		g.dispose();
		return resizePic;
	}

	private boolean isCustomImagesWriteOut(HttpServletRequest request) {
		if (request.getParameter("border_w") != null
				&& request.getParameter("dst_w") != null
				&& request.getParameter("dst_h") != null) {
			return true;
		} else {
			return false;
		}
	}

	private void drawImage(Graphics rg, BufferedImage borderPicture,
			int borderWidth, BufferedImage resetPicture,
			BufferedImage sourcePicture) {
		// 画左角
		rg.drawImage(borderPicture, 0, 0, borderWidth, borderWidth, 0, 0,
				borderWidth, borderWidth, null);
		// 画上横梁
		rg.drawImage(borderPicture, borderWidth, 0, resetPicture.getWidth()
				- borderWidth, borderWidth, borderWidth, 0, borderPicture
				.getWidth()
				- borderWidth, borderWidth, null);
		// 画右角
		rg.drawImage(borderPicture, resetPicture.getWidth() - borderWidth, 0,
				resetPicture.getWidth(), borderWidth,/*--*/
				borderPicture.getWidth() - borderWidth, 0, borderPicture
						.getWidth(), borderWidth, null);

		// 画底部
		rg.drawImage(resetPicture, 0, resetPicture.getHeight() - borderWidth,
				resetPicture.getWidth(), resetPicture.getHeight(),/*--*/
				resetPicture.getWidth(), borderWidth, 0, 0, null);

		// 画左侧

		rg.drawImage(borderPicture, 0, borderWidth, borderWidth, resetPicture
				.getHeight()
				- borderWidth, /*--*/
		0, borderWidth, borderWidth, borderPicture.getHeight() - borderWidth,
				null);

		// 画右侧
		rg.drawImage(resetPicture, resetPicture.getWidth() - borderWidth,
				borderWidth, resetPicture.getWidth(), resetPicture.getHeight()
						- borderWidth, /*--*/
				borderWidth, borderWidth, 0, resetPicture.getHeight()
						- borderWidth, null);

		// 画主图
		rg.drawImage(sourcePicture, borderWidth, borderWidth, resetPicture
				.getWidth()
				- borderWidth, resetPicture.getHeight() - borderWidth, /*--*/
		0, 0, sourcePicture.getWidth(), sourcePicture.getHeight(), null);

	}

	private void customdrawImage(Graphics rg, BufferedImage borderPicture,
			int borderWidth, BufferedImage resetPicture,
			BufferedImage sourcePicture, int customBorder) {
		// 画左角
		rg.drawImage(borderPicture, 0, 0, customBorder, customBorder, 0, 0,
				borderWidth, borderWidth, null);

		// 画上横梁

		rg.drawImage(borderPicture, customBorder, 0, resetPicture.getWidth()
				- customBorder, customBorder, borderWidth, 0, borderPicture
				.getWidth()
				- borderWidth, borderWidth, null);
		// 画右角
		rg.drawImage(borderPicture, resetPicture.getWidth() - customBorder, 0,
				resetPicture.getWidth(), customBorder,/*--*/
				borderPicture.getWidth() - borderWidth, 0, borderPicture
						.getWidth(), borderWidth, null);

		// 画底部
		rg.drawImage(resetPicture, 0, resetPicture.getHeight() - customBorder,
				resetPicture.getWidth(), resetPicture.getHeight(),/*--*/
				resetPicture.getWidth(), customBorder, 0, 0, null);

		// 画左侧

		rg.drawImage(borderPicture, 0, customBorder, customBorder, resetPicture
				.getHeight()
				- customBorder, /*--*/
		0, borderWidth, borderWidth, borderPicture.getHeight() - borderWidth,
				null);

		// 画右侧
		rg.drawImage(resetPicture, resetPicture.getWidth() - customBorder,
				customBorder, resetPicture.getWidth(), resetPicture.getHeight()
						- customBorder, /*--*/
				customBorder, customBorder, 0, resetPicture.getHeight()
						- customBorder, null);

		// 画主图
		rg.drawImage(sourcePicture, customBorder, customBorder, resetPicture
				.getWidth()
				- customBorder, resetPicture.getHeight() - customBorder, /*--*/
		0, 0, sourcePicture.getWidth(), sourcePicture.getHeight(), null);
		
	}

	private void writeOutImageStream(BufferedImage resetPicture,
			HttpServletResponse response) {

		
		

		
		BufferedImage  src = resetPicture;
		
		
		
		 response.setContentType("image/jpeg");
		 try {
		 ImageIO.write(resetPicture, "jpg", response.getOutputStream());
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
	}

	BufferedImage createSourceImage(String sourceUrl) {
		try {
			URL url = new URL(sourceUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			return ImageIO.read(conn.getInputStream());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
