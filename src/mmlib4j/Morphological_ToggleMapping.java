package mmlib4j;

import ij.ImagePlus;
import mmlib4j.filtering.ToggleMapping;
import mmlib4j.imagej.filters.AbstractFilterPlugin;
import mmlib4j.imagej.utils.ImageUtils;
import mmlib4j.images.GrayScaleImage;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class Morphological_ToggleMapping extends AbstractFilterPlugin {

	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return  ToggleMapping.toggleMapping(image, getAdjancencyRelation());
	}

	@Override
	public String getPluginName() {
		return "Toggle mapping (erosion/dilation)";
	}
	
	public static void main(String args[]){
		ImagePlus plus = ImageUtils.openGrayScale();
		Morphological_ToggleMapping plugin = new Morphological_ToggleMapping();
		plugin.setup(null, plus);
		plugin.run(plus.getProcessor());
	}

	
}
