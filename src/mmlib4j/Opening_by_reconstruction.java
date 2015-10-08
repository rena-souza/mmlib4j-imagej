package mmlib4j;

import ij.gui.GenericDialog;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.filters.AbstractFilterPlugin;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.representation.tree.componentTree.ReconstructionMorphological;
import mmlib4j.utils.AdjacencyRelation;

/**
 * MMLib4J - Mathematical Morphology Library for Java
 * 
 * @author Wonder Alexandre Luz Alves
 *
 *         Graphic User Interface by ImageJ
 */
public class Opening_by_reconstruction extends AbstractFilterPlugin {

	double raio;
	double raioEE;

	public boolean initParameters() {
		GenericDialog gd = new GenericDialog("Morphological reconstruction based on marked image");

		gd.addNumericField("Adjacency relation [rec] (Radius)", 1.5, 1);
		gd.addNumericField("Structuring element [opening] (Radius)", 1.5, 1);

		gd.showDialog();
		if (gd.wasCanceled())
			return false;

		raio = gd.getNextNumber();
		raioEE = gd.getNextNumber();

		return true;
	}

	@Override
	public GrayScaleImage filterImage(GrayScaleImage image) {
		GrayScaleImage g = MorphologicalOperators.opening(image, AdjacencyRelation.getCircular(raioEE));
		ReconstructionMorphological rec = new ReconstructionMorphological(image, AdjacencyRelation.getCircular(raio),
				true);
		GrayScaleImage imgOut = rec.reconstructionByDilation(g);
		return imgOut;
	}

	@Override
	public String getPluginName() {
		return "Opening by reconstruction";
	}

}
