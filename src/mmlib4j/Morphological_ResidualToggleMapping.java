package mmlib4j;

import ij.ImagePlus;
import mmlib4j.filtering.ToggleMapping;
import mmlib4j.imagej.filters.AbstractMorphologicalPlugin;
import mmlib4j.imagej.utils.ImageUtils;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.utils.AdjacencyRelation;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class Morphological_ResidualToggleMapping extends AbstractMorphologicalPlugin {
	
	@Override
	public String getPluginName() {
		return "Residual toggle mapping (erosion/dilation)";
	}
	
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return ToggleMapping.toggleMappingResidue(image, AdjacencyRelation.getCircular(getRadius()));
	}

	
	public static void main(String args[]){
		ImagePlus plus = ImageUtils.openGrayScale();
		Morphological_ResidualToggleMapping plugin = new Morphological_ResidualToggleMapping();
		plugin.setup(null, plus);
		plugin.run(plus.getProcessor());
	}
	
}
