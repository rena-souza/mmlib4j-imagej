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
public class Morphological_ResidualToggleMapping extends AbstractFilterPlugin {
	
	@Override
	public String getPluginName() {
		return "Residual toggle mapping (erosion/dilation)";
	}
	
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return ToggleMapping.toggleMappingResidue(image, getAdjancencyRelation());
	}

	
	public static void main(String args[]){
		ImagePlus plus = ImageUtils.openGrayScale();
		Morphological_ResidualToggleMapping plugin = new Morphological_ResidualToggleMapping();
		plugin.setup(null, plus);
		plugin.run(plus.getProcessor());
	}
	
}
