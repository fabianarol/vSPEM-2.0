package org.eclipse.epf.diagram.ui.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

/**
 * This class provides various methods that
 * help with the image processing tasks
 * 
 */
public class ImageUtils {

	/**
	 * Apply the desired blending to the given image with the given color
	 * @param image the source image for the blending
	 * @param compositeToUse the composite to be used by the blending
	 * @param red an 0-255 INT value for the red channel
	 * @param green an 0-255 INT value for the green channel
	 * @param blue an 0-255 INT value for the blue channel
	 * @return the new blended SWT image data
	 */
	public static ImageData applyBlendingSWT(BufferedImage image, Composite compositeToUse, int red, int green, int blue) {
		BufferedImage finalImage = applyBlending(image, compositeToUse, red, green, blue);
		return ImageUtils.convertToSWT(finalImage);
	}

	/**
	 * Apply the desired blending to the given image with the given color
	 * @param image the source image for the blending
	 * @param compositeToUse the composite to be used by the blending
	 * @param red an 0-255 INT value for the red channel
	 * @param green an 0-255 INT value for the green channel
	 * @param blue an 0-255 INT value for the blue channel
	 * @return the new blended BufferedImage
	 */
	public static BufferedImage applyBlending(BufferedImage image, Composite compositeToUse, int red, int green, int blue) {
		if (image == null) return null;

		//the final result
		BufferedImage finalImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = finalImage.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.setColor(new java.awt.Color(red,green,blue));
		g2d.setComposite(compositeToUse);
		g2d.fillRect(0, 0, finalImage.getWidth(), finalImage.getHeight());
		g2d.dispose();

		return finalImage;
	}

	/**
	 * Converts a given SWT {@link ImageData} into a AWT {@link BufferedImage}
	 * @param data the SWT image data
	 * @return the equivalent AWT buffered image
	 */
	public static BufferedImage convertToAWT(ImageData data) {
		ColorModel colorModel = null;
		PaletteData palette = data.palette;
		if (palette.isDirect) {
			colorModel = new DirectColorModel(data.depth, palette.redMask,
					palette.greenMask, palette.blueMask);
			BufferedImage bufferedImage = new BufferedImage(colorModel,
					colorModel.createCompatibleWritableRaster(data.width,
							data.height), false, null);
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[3];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					RGB rgb = palette.getRGB(pixel);
					pixelArray[0] = rgb.red;
					pixelArray[1] = rgb.green;
					pixelArray[2] = rgb.blue;
					raster.setPixels(x, y, 1, 1, pixelArray);
				}
			}
			return bufferedImage;
		} else {
			RGB[] rgbs = palette.getRGBs();
			byte[] red = new byte[rgbs.length];
			byte[] green = new byte[rgbs.length];
			byte[] blue = new byte[rgbs.length];
			for (int i = 0; i < rgbs.length; i++) {
				RGB rgb = rgbs[i];
				red[i] = (byte) rgb.red;
				green[i] = (byte) rgb.green;
				blue[i] = (byte) rgb.blue;
			}
			if (data.transparentPixel != -1) {
				colorModel = new IndexColorModel(data.depth, rgbs.length, red,
						green, blue, data.transparentPixel);
			} else {
				colorModel = new IndexColorModel(data.depth, rgbs.length, red,
						green, blue);
			}
			BufferedImage bufferedImage = new BufferedImage(colorModel,
					colorModel.createCompatibleWritableRaster(data.width,
							data.height), false, null);
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[1];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					pixelArray[0] = pixel;
					raster.setPixel(x, y, pixelArray);
				}
			}
			return bufferedImage;
		}
	}

	/**
	 * Converts a given AWT {@link BufferedImage} into a SWT {@link ImageData}
	 * @param awt the AWT buffered image
	 * @return the equivalent SWT image data
	 */
	public static final ImageData convertToSWT(BufferedImage awt) {

		int width = awt.getWidth();
		int height = awt.getHeight();

		PixelGrabber grabber = new PixelGrabber(awt, 0, 0, width, height, true);
		ImageData imgdata = null;
		try {
			if (grabber.grabPixels()) {
				DirectColorModel cm = (DirectColorModel)ColorModel.getRGBdefault();
				int[] pixels = (int[])grabber.getPixels();
				PaletteData palette = new PaletteData(cm.getRedMask(), cm.getGreenMask(), cm.getBlueMask());
				imgdata  = new ImageData(width, height, cm.getPixelSize(), palette);
				imgdata.setPixels(0, 0, width * height, pixels, 0);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgdata;
	}

	/**
	 * Returns the resizing ratio that is needed to scale the given image
	 * so that will fit inside the desired dimension  with at least one
	 * of it's length (width/height) equal to given one.
	 * @param image the source image
	 * @param desiredWidth the desired width of the given image
	 * @param desiredHeight the desired height of the given image
	 * @return the resizing ratio
	 */
	public static double  getResizingRatio(BufferedImage image, int desiredWidth, int desiredHeight) {
		double imageWidth = image.getWidth();
		double imageHeight = image.getHeight();
		return getResizingRatio(imageWidth, imageHeight, desiredWidth, desiredHeight);
	}
	
	public static double  getResizingRatio(double imageWidth, double imageHeight, int desiredWidth, int desiredHeight) {
		double ratio = desiredWidth / imageWidth;
		double newHeight = imageHeight * ratio;
		if (newHeight > desiredHeight) {
			ratio = desiredHeight / imageHeight;
		}
		return ratio;
	}

	/**
	 * Create a soft clipped image of a given image using a given clipping shape
	 * @param sourceImage the image from which clipping will be created
	 * @param clippingShape the shape used to do the clipping
	 * @return the clipped image
	 */
	public static BufferedImage createSoftClipping(BufferedImage sourceImage, Shape clippingShape) {

		// Create a transparent image in which we can perform
		BufferedImage clippedImage = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = clippedImage.createGraphics();

		// Render our clip shape into the image. Note that we enable
		// antialiasing to achieve the soft clipping effect.
		g2d.setComposite(AlphaComposite.Src);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		g2d.fill(clippingShape);

		// We use SrcAtop, which effectively uses the alpha value as a coverage
		// value for each pixel stored in the destination. For the areas outside
		// our clip shape, the destination alpha will be zero, so nothing is
		// rendered in those areas. For the areas inside our clip shape, the
		// destination alpha will be fully opaque, so the full color is rendered.
		// At the edges, the original antialiasing is carried over to give us
		// the desired soft clipping effect.
		g2d.setComposite(AlphaComposite.SrcAtop);
		g2d.drawImage(sourceImage, 0, 0, null);
		g2d.dispose();

		//return the clipped image
		return clippedImage;
	}
}