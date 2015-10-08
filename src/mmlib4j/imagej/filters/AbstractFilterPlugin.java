package mmlib4j.imagej.filters;

import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import mmlib4j.imagej.types.Channel;
import mmlib4j.imagej.utils.ImageJAdapter;
import mmlib4j.imagej.utils.ImageUtils;
import mmlib4j.images.GrayScaleImage;
import mmlib4j.utils.AdjacencyRelation;

/**
 * 
 * @author rsouza
 */
public abstract class AbstractFilterPlugin implements AdjacencyRelationPlugin {
	
	private double radius;
	private ImagePlus imgPlus;
	
	@Override
	public int setup(String arg, ImagePlus imgPlus) {
		if(!initParameters())
			return PlugInFilter.DONE;
		
		setImgPlus(imgPlus);
		return accepts();
	}
	
	@Override
	public boolean initParameters() {
		GenericDialog tela = new GenericDialog(getPluginName());
		tela.addNumericField("Radius", initialRadius(), 1);
		tela.showDialog();
		if(tela.wasCanceled()){
			return false;
		}
		radius = tela.getNextNumber();
		return true;
	}
	
	@Override
	public int accepts() {
		return PlugInFilter.DOES_8G | PlugInFilter.DOES_RGB;
	};
	
	@Override
	public void run(ImageProcessor arg0) {
		if (arg0.getClass().isAssignableFrom(ij.process.ColorProcessor.class))
			run((ColorProcessor) arg0);

		else if (arg0.getClass().isAssignableFrom(ByteProcessor.class))
			run((ByteProcessor) arg0);
		else
			throw new IllegalArgumentException("Not supported: " + arg0.getClass());
	}

	@Override
	public void run(ColorProcessor cp) {
		//TODO implementing as a single thread for each channel
		Splitter spliter = new Splitter(cp);

		GrayScaleImage imageR = spliter.getChannel(Channel.RED);
		GrayScaleImage imageG = spliter.getChannel(Channel.GREEN);
		GrayScaleImage imageB = spliter.getChannel(Channel.BLUE);

		GrayScaleImage imgOutR = filterImage(imageR);
		GrayScaleImage imgOutG = filterImage(imageG);
		GrayScaleImage imgOutB = filterImage(imageB);

		ColorProcessor rgbOutput = ImageJAdapter.toColorProcessor(cp.getWidth(), cp.getHeight(), imgOutR, imgOutG, imgOutB);

		show(rgbOutput);

	}
	
	@Override
	public void run(ByteProcessor bp) {
		ImageUtils.initMMorph4J();
		GrayScaleImage output = filterImage(ImageJAdapter.toGrayScaleImage(bp));
		show(ImageJAdapter.toByteProcessor(output));
	}
	
	/**
	 * Initial Radius used when user is asked to input the radius
	 * @return
	 */
	public double initialRadius(){
		return 1.5;
	}
	
	/**
	 * Show the image in the user screen
	 * @param output image
	 */
	public void show(ImageProcessor output) {
		getImgPlus().setProcessor(getPluginName(), output);
		getImgPlus().show();
	}

	@Override
	public AdjacencyRelation getAdjancencyRelation() {
		return AdjacencyRelation.getCircular(radius);
	}
	
	/* Getters and Setters */
	public void setImgPlus(ImagePlus imgPlus) {
		this.imgPlus = imgPlus;
	}
	
	@Override
	public ImagePlus getImgPlus() {
		return imgPlus;
	}

}
