package mmlib4j.imagej.filters;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;

public interface Plugin extends PlugInFilter {
	public String getPluginName();
	public int accepts();
	public void setImgPlus(ImagePlus plus);
	public ImagePlus getImgPlus();
}
