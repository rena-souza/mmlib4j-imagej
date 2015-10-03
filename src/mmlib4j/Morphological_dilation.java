package mmlib4j;

import java.awt.Color;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import mmlib4j.filtering.MorphologicalOperators;
import mmlib4j.imagej.utils.ImageJAdapter;
import mmlib4j.imagej.utils.ImageUtils;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.images.impl.ImageFactory;
import mmlib4j.utils.AdjacencyRelation;

/**
 * MMLib4J - Mathematical Morphology Library for Java 
 * @author Wonder Alexandre Luz Alves
 *
 * Graphic User Interface by ImageJ
 */
public class Morphological_dilation implements PlugInFilter {
	
	double raio;
	ImagePlus imgPlus;
	
	public int setup(String arg, ImagePlus imp) {
		GenericDialog tela = new GenericDialog("Dilation");
		tela.addNumericField("Radius", 1.5, 1);
		tela.showDialog();
		if(tela.wasCanceled()){
			return PlugInFilter.DONE;
		}
		imgPlus = imp;
		raio = tela.getNextNumber();
		return PlugInFilter.DOES_8G + PlugInFilter.DOES_RGB;
	}
	
	public void run(ImageProcessor ip) { 
		if(ip instanceof ColorProcessor){
			ColorProcessor imgRGB = (ColorProcessor) ip;
			byte pixelsR[] = imgRGB.getChannel(0);
			byte pixelsG[] = imgRGB.getChannel(1);
			byte pixelsB[] = imgRGB.getChannel(2);
			GrayScaleImage imageR = ImageFactory.createGrayScaleImage(8, pixelsR, ip.getWidth(), ip.getHeight());
			GrayScaleImage imageG = ImageFactory.createGrayScaleImage(8, pixelsG, ip.getWidth(), ip.getHeight());
			GrayScaleImage imageB = ImageFactory.createGrayScaleImage(8, pixelsB, ip.getWidth(), ip.getHeight());
			
			GrayScaleImage imgOutR = MorphologicalOperators.dilation(imageR, AdjacencyRelation.getCircular(raio));
			GrayScaleImage imgOutG = MorphologicalOperators.dilation(imageG, AdjacencyRelation.getCircular(raio));
			GrayScaleImage imgOutB = MorphologicalOperators.dilation(imageB, AdjacencyRelation.getCircular(raio));
			ColorProcessor rgb = new ColorProcessor(ip.getWidth(), ip.getHeight());
			
			for(int x = 0; x < ip.getWidth(); x++){
				for(int y=0 ; y < ip.getHeight(); y++){
					rgb.putPixel(x, y, new Color(imgOutR.getPixel(x,y), imgOutG.getPixel(x,y), imgOutB.getPixel(x,y)).getRGB());
				}
			}
			imgPlus.setProcessor("Dilation", rgb);
			imgPlus.show();
		}else{
			ImageUtils.initMMorph4J();
			GrayScaleImage imgOut = MorphologicalOperators.dilation(ImageJAdapter.toGrayScaleImage((ByteProcessor) ip), AdjacencyRelation.getCircular(raio));
			imgPlus.setProcessor("Dilation", ImageJAdapter.toByteProcessor(imgOut));
			//ImagePlus plus = new ImagePlus("Morphological Dilation", ImageJAdapter.toByteProcessor(imgOut));
			//plus.show();
			
		}
		
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
