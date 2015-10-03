package mmlib4j.imagej.filters;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public abstract class AbstractFilterPlugin implements FilterPlugin{
	
	@Override
	public int setup(String arg, ImagePlus imgPlus) {
		if(!initParameters())
			return PlugInFilter.DONE;
		
		setImgPlus(imgPlus);
		return accepts();
	}

	public void show(ImageProcessor output) {
		getImgPlus().setProcessor(getPluginName(), output);
		getImgPlus().show();
	}
	
	
}
