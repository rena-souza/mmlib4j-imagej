package mmlib4j;

import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.filters.AbstractFilterPlugin;
import mmlib4j.imagej.filters.annotations.NumericParameter;
import mmlib4j.imagej.filters.annotations.Plugin;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.representation.tree.componentTree.ReconstructionMorphological;
import mmlib4j.utils.AdjacencyRelation;
/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */

@Plugin(name = "Opening by reconstruction")
public class Opening_by_reconstruction extends AbstractFilterPlugin {
	
	@NumericParameter(name="Adjacency relation [rec] (Radius)", defaultValue = 1.5, digits = 1)
	double raio;
	@NumericParameter(name="Structuring element [opening] (Radius)", defaultValue = 1.5, digits = 1)
	double raioEE;

	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		GrayScaleImage g = MorphologicalOperators.opening(image, AdjacencyRelation.getCircular(raioEE));
		ReconstructionMorphological rec = new ReconstructionMorphological(image, AdjacencyRelation.getCircular(raio), true);
		GrayScaleImage imgOut = rec.reconstructionByDilation(g);
		return imgOut;
	}
}

