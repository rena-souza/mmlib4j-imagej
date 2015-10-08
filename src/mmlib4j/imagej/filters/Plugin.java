package mmlib4j.imagej.filters;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
/**
 * 
 * @author rsouza
 */
public interface Plugin extends PlugInFilter {
	
	/**
	 * The name used in UI
	 * @return
	 */
	public String getPluginName();
	
	/**
	 * Called in setup fase accepts return the imageJ int number equivalent
	 * to the accepted image types
	 * @return
	 */
	public int accepts();
	
	/**
	 * Called on the setup fase to init the parameters
	 * @return
	 */
	public abstract boolean initParameters();
	
	public void setImgPlus(ImagePlus plus);
	public ImagePlus getImgPlus();
	
}