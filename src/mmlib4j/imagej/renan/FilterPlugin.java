package mmlib4j.imagej.renan;

import mmlib4j.images.GrayScaleImage;

public interface FilterPlugin extends Plugin {

	public GrayScaleImage filterImage(GrayScaleImage bp);
	public abstract boolean initParameters();
	
}
