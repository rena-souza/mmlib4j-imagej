package mmlib4j.imagej.filters;

import mmlib4j.images.GrayScaleImage;

/**
 *
 * @author Renã P. Souza
 */
public interface ReconstructionPlugin extends Plugin{
	
	public GrayScaleImage reconstruct(GrayScaleImage imgF, GrayScaleImage imgG);

}
