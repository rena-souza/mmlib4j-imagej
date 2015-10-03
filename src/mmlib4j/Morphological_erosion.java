package mmlib4j;

import ij.IJ;
import ij.ImagePlus;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.filters.AbstractRadiusPlugin;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.utils.AdjacencyRelation;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class Morphological_erosion extends AbstractRadiusPlugin {
	
	@Override
	public String getPluginName() {
		return "Erosion"; 
	}
	
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return  MorphologicalOperators.erosion(image, AdjacencyRelation.getCircular(getRadius()));
	}

	public static void main(String args[]){
		ImagePlus plus = IJ.openImage();
		Morphological_erosion plugin = new Morphological_erosion();
		plugin.setup(null, plus);
		plugin.run(plus.getProcessor());
	}

}
