package mmlib4j.imagej.filters;

import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import mmlib4j.images.GrayScaleImage;

/**
 * 
 * @author rsouza
 */
public interface FilterPlugin extends Plugin {

	/**
	 * Apply the filter to the image and return the output image
	 * @param image
	 * @return
	 */
	public GrayScaleImage filterImage(GrayScaleImage image);
	
	/**
	 * Called if the input image is a RGB image
	 * @param cp
	 */
	public void run(ColorProcessor cp);
	
	/**
	 * Called if the input image is a 8 bit image
	 * @param bp
	 */
	public void run(ByteProcessor bp);
	
}
