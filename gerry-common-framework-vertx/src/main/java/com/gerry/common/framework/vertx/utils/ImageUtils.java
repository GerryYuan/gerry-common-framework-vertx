package com.gerry.common.framework.vertx.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 图片工具类
 * 
 *
 * @author gerry
 * @version  1.0.1, 2016年6月26日
 * @since   com.gerry 1.0.1
 */
public class ImageUtils {
	private static String[] iamgeNames = { "bmp", "dib", "gif", "jfif", "jpe", "jpeg", "jpg", "png", "tif", "tiff", "ico" };

	private static List<String> images = Arrays.asList(iamgeNames);

	/**
	 * 判断一个文件扩展名是否是图片格式
	 * 
	 * @param extName
	 *            文件扩展名
	 * @return
	 */
	public static boolean isImage(String extName) {
		boolean result = false;
		if (VertxEmptyUtils.isNotEmpty(extName)) {
			if (images.contains(extName.toLowerCase()))
				result = true;
		}
		return result;
	}

	/**
	 * <b>function:</b> 设置图片压缩质量枚举类；
	 */
	public enum ImageQuality {
		max(1.0f), high(0.75f), medium(0.5f), low(0.25f);
		private Float quality;

		public Float getQuality() {
			return this.quality;
		}

		ImageQuality(Float quality) {
			this.quality = quality;
		}
	}

	private static Image image;

	/**
	 * <b>function:</b> 通过目标对象的大小和标准（指定）大小计算出图片缩小的比例
	 * 
	 * @param targetWidth
	 *            目标的宽度
	 * @param targetHeight
	 *            目标的高度
	 * @param standardWidth
	 *            标准（指定）宽度
	 * @param standardHeight
	 *            标准（指定）高度
	 * @return 最小的合适比例
	 */
	public static double getScaling(double targetWidth, double targetHeight, double standardWidth, double standardHeight) {
		double widthScaling = 0d;
		double heightScaling = 0d;
		if (targetWidth > standardWidth) {
			widthScaling = standardWidth / (targetWidth * 1.00d);
		} else {
			widthScaling = 1d;
		}
		if (targetHeight > standardHeight) {
			heightScaling = standardHeight / (targetHeight * 1.00d);
		} else {
			heightScaling = 1d;
		}
		return Math.min(widthScaling, heightScaling);
	}

	/**
	 * <b>function:</b> 将Image的宽度、高度缩放到指定width、height，并保存在savePath目录
	 * 
	 * @param width
	 *            缩放的宽度
	 * @param height
	 *            缩放的高度
	 * @param savePath
	 *            保存目录
	 * @param targetImage
	 *            即将缩放的目标图片
	 * @return 图片保存路径、名称
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static String resize(int width, int height, String savePath, Image targetImage) throws IOException {
		width = Math.max(width, 1);
		height = Math.max(height, 1);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(targetImage, 0, 0, width, height, null);
		String formatName = savePath.substring(savePath.lastIndexOf(".") + 1);
		ImageIO.write(image, formatName, new File(savePath));
		return savePath;
	}

	/**
	 * @param width
	 *            指定的宽度
	 * @param height
	 *            指定的高度
	 * @param image
	 *            图片文件
	 * @return 返回宽度、高度的int数组
	 */
	public static int[] getSize(int width, int height, Image image) {
		int targetWidth = image.getWidth(null);
		int targetHeight = image.getHeight(null);
		double scaling = getScaling(targetWidth, targetHeight, width, height);
		long standardWidth = Math.round(targetWidth * scaling);
		long standardHeight = Math.round(targetHeight * scaling);
		return new int[] { Integer.parseInt(Long.toString(standardWidth)), Integer.parseInt(String.valueOf(standardHeight)) };
	}

	/**
	 * <b>function:</b> 通过指定的比例和图片对象，返回一个放大或缩小的宽度、高度
	 * 
	 * @param scale
	 *            缩放比例
	 * @param image
	 *            图片对象
	 * @return 返回宽度、高度
	 */
	public static int[] getSize(float scale, Image image) {
		int targetWidth = image.getWidth(null);
		int targetHeight = image.getHeight(null);
		long standardWidth = Math.round(targetWidth * scale);
		long standardHeight = Math.round(targetHeight * scale);
		return new int[] { Integer.parseInt(Long.toString(standardWidth)), Integer.parseInt(String.valueOf(standardHeight)) };
	}

	public static int[] getSize(int width, Image image) {
		int targetWidth = image.getWidth(null);
		int targetHeight = image.getHeight(null);
		long height = Math.round((targetHeight * width) / (targetWidth * 1.00f));
		return new int[] { width, Integer.parseInt(String.valueOf(height)) };
	}

	public static int[] getSizeByHeight(int height, Image image) {
		int targetWidth = image.getWidth(null);
		int targetHeight = image.getHeight(null);
		long width = Math.round((targetWidth * height) / (targetHeight * 1.00f));
		return new int[] { Integer.parseInt(String.valueOf(width)), height };
	}

	/**
	 * 
	 * <b>function:</b>
	 * 将指定的targetFile图片文件的宽度、高度大于指定width、height的图片缩小，并保存在savePath目录
	 * 
	 * @param width
	 *            缩小的宽度
	 * @param height
	 *            缩小的高度
	 * @param savePath
	 *            保存目录
	 * @param targetImage
	 *            改变的目标图片
	 * @return 图片保存路径、名称
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static String resize(int width, int height, String savePath, File targetFile) throws IOException {
		image = ImageIO.read(targetFile);
		int[] size = getSize(width, height, image);
		return resize(size[0], size[1], savePath, image);
	}

	/**
	 * 
	 * <b>function:</b>
	 * 将指定的targetURL网络图片文件的宽度、高度大于指定width、height的图片缩小，并保存在savePath目录
	 * 
	 * @param width
	 *            缩小的宽度
	 * @param height
	 *            缩小的高度
	 * @param savePath
	 *            保存目录
	 * @param targetImage
	 *            改变的目标图片
	 * @return 图片保存路径、名称
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static String resize(int width, int height, String savePath, URL targetURL) throws IOException {
		image = ImageIO.read(targetURL);
		int[] size = getSize(width, height, image);
		return resize(size[0], size[1], savePath, image);
	}

	/**
	 * <b>function:</b> 将一个本地的图片文件按照指定的比例进行缩放
	 * 
	 * @param scale
	 *            缩放比例
	 * @param savePath
	 *            保存文件路径、名称
	 * @param targetFile
	 *            本地图片文件
	 * @return 新的文件名称
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static String resize(float scale, String savePath, File targetFile) throws IOException {
		image = ImageIO.read(targetFile);
		int[] size = getSize(scale, image);
		return resize(size[0], size[1], savePath, image);
	}

	/**
	 * <b>function:</b> 将一个网络图片文件按照指定的比例进行缩放
	 * 
	 * @param scale
	 *            缩放比例
	 * @param savePath
	 *            保存文件路径、名称
	 * @param targetFile
	 *            本地图片文件
	 * @return 新的文件名称
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static String resize(float scale, String savePath, URL targetURL) throws IOException {
		image = ImageIO.read(targetURL);
		int[] size = getSize(scale, image);
		return resize(size[0], size[1], savePath, image);
	}

	/**
	 * <b>function:</b> 按照固定宽度进行等比缩放本地图片
	 * 
	 * @param width
	 *            固定宽度
	 * @param savePath
	 *            保存路径、名称
	 * @param targetFile
	 *            本地目标文件
	 * @return 返回保存路径
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static String resize(int width, String savePath, File targetFile) throws IOException {
		image = ImageIO.read(targetFile);
		int[] size = getSize(width, image);
		return resize(size[0], size[1], savePath, image);
	}

	/**
	 * <b>function:</b> 按照固定宽度进行等比缩放网络图片
	 * 
	 * @param width
	 *            固定宽度
	 * @param savePath
	 *            保存路径、名称
	 * @param targetFile
	 *            本地目标文件
	 * @return 返回保存路径
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static String resize(int width, String savePath, URL targetURL) throws IOException {
		image = ImageIO.read(targetURL);
		int[] size = getSize(width, image);
		return resize(size[0], size[1], savePath, image);
	}

	/**
	 * 
	 * <b>function:</b> 按照固定高度进行等比缩放本地图片
	 * 
	 * @param height
	 *            固定高度
	 * @param savePath
	 *            保存路径、名称
	 * @param targetFile
	 *            本地目标文件
	 * @return 返回保存路径
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static String resizeByHeight(int height, String savePath, File targetFile) throws IOException {
		image = ImageIO.read(targetFile);
		int[] size = getSizeByHeight(height, image);
		return resize(size[0], size[1], savePath, image);
	}

	/**
	 * <b>function:</b> 按照固定高度进行等比缩放网络图片
	 * 
	 * @param height
	 *            固定高度
	 * @param savePath
	 *            保存路径、名称
	 * @param targetFile
	 *            本地目标文件
	 * @return 返回保存路径
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static String resizeByHeight(int height, String savePath, URL targetURL) throws IOException {
		image = ImageIO.read(targetURL);
		int[] size = getSizeByHeight(height, image);
		return resize(size[0], size[1], savePath, image);
	}

	/** */
	/**
	 * 缩放图像
	 * 
	 * @param srcImageFile
	 *            源图像文件地址
	 * @param result
	 *            缩放后的图像地址
	 * @param scale
	 *            缩放比例
	 * @param flag
	 *            缩放选择:true 放大; false 缩小;
	 */
	public static void scale(String srcImageFile, String result, int scale, boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			if (flag) {
				// 放大
				width = width * scale;
				height = height * scale;
			} else {
				// 缩小
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** */
	/**
	 * 图像切割
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param descDir
	 *            切片目标文件夹
	 * @param destWidth
	 *            目标切片宽度
	 * @param destHeight
	 *            目标切片高度
	 */
	public static void cut(String srcImageFile, String descDir, int destWidth, int destHeight) {
		try {
			Image img;
			ImageFilter cropFilter;
			// 读取源图像
			BufferedImage bi = ImageIO.read(new File(srcImageFile));
			int srcWidth = bi.getHeight(); // 源图宽度
			int srcHeight = bi.getWidth(); // 源图高度
			if (srcWidth > destWidth && srcHeight > destHeight) {
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				destWidth = 200; // 切片宽度
				destHeight = 150; // 切片高度
				int cols = 0; // 切片横向数量
				int rows = 0; // 切片纵向数量
				// 计算切片的横向和纵向数量
				if (srcWidth % destWidth == 0) {
					cols = srcWidth / destWidth;
				} else {
					cols = (int) Math.floor(srcWidth / destWidth) + 1;
				}
				if (srcHeight % destHeight == 0) {
					rows = srcHeight / destHeight;
				} else {
					rows = (int) Math.floor(srcHeight / destHeight) + 1;
				}
				// 循环建立切片
				// 改进的想法:是否可用多线程加快切割速度
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						// 四个参数分别为图像起点坐标和宽高
						// 即: CropImageFilter(int x,int y,int width,int height)
						cropFilter = new CropImageFilter(j * 200, i * 150, destWidth, destHeight);
						img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
						BufferedImage tag = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
						Graphics g = tag.getGraphics();
						g.drawImage(img, 0, 0, null); // 绘制缩小后的图
						g.dispose();
						// 输出为文件
						ImageIO.write(tag, "JPEG", new File(descDir + "pre_map_" + i + "_" + j + ".jpg"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** */
	/**
	 * 图像类型转换 GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)
	 */
	public static void convert(String source, String result) {
		try {
			File f = new File(source);
			f.canRead();
			f.canWrite();
			BufferedImage src = ImageIO.read(f);
			ImageIO.write(src, "JPG", new File(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** */
	/**
	 * 彩色转为黑白
	 * 
	 * @param source
	 * @param result
	 */
	public static void gray(String source, String result) {
		try {
			BufferedImage src = ImageIO.read(new File(source));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
