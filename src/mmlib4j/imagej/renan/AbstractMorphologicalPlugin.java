package mmlib4j.imagej.renan;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import mmlib4j.imagej.types.Channel;
import mmlib4j.imagej.utils.ImageJAdapter;
import mmlib4j.imagej.utils.ImageUtils;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.images.impl.ImageFactory;

/**
 * Abstract Morphological filter plugin
 * 
 * Apply the filter for 8bit image color and rgb image color
 * 
 * @author rsouza
 */
public abstract class AbstractMorphologicalPlugin extends AbstractFilterPlugin {

	private ImagePlus imgPlus;
	
	@Override
	public int accepts() {
		return PlugInFilter.DOES_8G | PlugInFilter.DOES_RGB;
	};

	@Override
	public void run(ImageProcessor arg0) {
		if (arg0.getClass().isAssignableFrom(ij.process.ColorProcessor.class))
			run((ColorProcessor) arg0);

		else if (arg0.getClass().isAssignableFrom(ByteProcessor.class))
			run((ByteProcessor) arg0);
		else
			throw new IllegalArgumentException("Not supported: " + arg0.getClass());
	}

	public void run(ColorProcessor cp) {
		int width = cp.getWidth();
		int height = cp.getHeight();
		int depth = 8;

		GrayScaleImage imageR = getGrayScaleImage(depth, getPixels(cp, Channel.RED), width, height);
		GrayScaleImage imageG = getGrayScaleImage(depth, getPixels(cp, Channel.GREEN), width, height);
		GrayScaleImage imageB = getGrayScaleImage(depth, getPixels(cp, Channel.BLUE), width, height);

		GrayScaleImage imgOutR = filterImage(imageR);
		GrayScaleImage imgOutG = filterImage(imageG);
		GrayScaleImage imgOutB = filterImage(imageB);

		ColorProcessor rgbOutput = new ColorProcessor(cp.getWidth(), cp.getHeight());
		rgbOutput.setRGB((byte[]) imgOutR.getPixels(), 
					     (byte[]) imgOutG.getPixels(), 
					     (byte[]) imgOutB.getPixels());

		show(rgbOutput);

	}
	
	private byte[] getPixels(ColorProcessor cp, Channel channel){
		return cp.getChannel(channel.value());
	}

	public void run(ByteProcessor bp) {
		ImageUtils.initMMorph4J();
		GrayScaleImage output = filterImage(ImageJAdapter.toGrayScaleImage(bp));
		show(ImageJAdapter.toByteProcessor(output));
	}

	private GrayScaleImage getGrayScaleImage(int depth, byte[] pixels, int width, int height) {
		return ImageFactory.createGrayScaleImage(depth, pixels, width, height);
	}
	
	/* Getters and Setters */
	
	public void setImgPlus(ImagePlus imgPlus) {
		this.imgPlus = imgPlus;
	}
	
	@Override
	public ImagePlus getImgPlus() {
		return imgPlus;
	}

}
