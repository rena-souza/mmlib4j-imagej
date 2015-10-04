package mmlib4j;

import ij.IJ;
import ij.ImagePlus;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.filters.AbstractRadiusPlugin;
import mmlib4j.imagej.filters.annotations.Parameter;
import mmlib4j.imagej.filters.annotations.Plugin;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.utils.AdjacencyRelation;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */

@Plugin(name = "Dilation")
public class Morphological_dilation extends AbstractRadiusPlugin {
	
//	@Override
//	public String getPluginName() {
//		return "Dilation";
//	}
	
	@Parameter(name="radius", type = Double.class, defaultValue=1.5)
	private double radius;
	
	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		return MorphologicalOperators.dilation(image, AdjacencyRelation.getCircular(radius));
	}
	
	public static void main(String args[]){
		ImagePlus plus = IJ.openImage();
		plus.setProcessor(plus.getProcessor().convertToRGB());
		plus.show();
		Morphological_dilation plugin = new Morphological_dilation();
		plugin.setup(null, plus);
		plugin.run(plus.getProcessor());
	}

}
