package mmlib4j;

import ij.IJ;
import ij.ImagePlus;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.filters.AbstractFilterPlugin;
import mmlib4j.imagej.filters.annotations.NumericParameter;
import mmlib4j.imagej.filters.annotations.Plugin;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.utils.AdjacencyRelation;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */

@Plugin(name = "Erosion")
public class Morphological_erosion extends AbstractFilterPlugin {
	
	@NumericParameter(name="Radius")
	private double radius;
		
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return  MorphologicalOperators.erosion(image, AdjacencyRelation.getCircular(radius));
	}

	public static void main(String args[]){
		ImagePlus plus = IJ.openImage();
		Morphological_erosion plugin = new Morphological_erosion();
		plugin.setup(null, plus);
		plugin.run(plus.getProcessor());
	}

}
