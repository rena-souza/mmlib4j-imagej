package mmlib4j.imagej.filters;

import mmlib4j.images.GrayScaleImage;

public interface FilterPlugin extends Plugin {

	public GrayScaleImage filterImage(GrayScaleImage image);
	public boolean initParameters();
	
}
