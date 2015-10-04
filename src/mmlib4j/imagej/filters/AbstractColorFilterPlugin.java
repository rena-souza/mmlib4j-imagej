package mmlib4j.imagej.filters;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;
import mmlib4j.imagej.filters.annotations.Plugin;
import mmlib4j.imagej.types.Channel;
import mmlib4j.imagej.utils.ImageJAdapter;
import mmlib4j.imagej.utils.ImageUtils;
import mmlib4j.images.GrayScaleImage;

public abstract class AbstractColorFilterPlugin <T> extends AbstractFilterPlugin implements ColorFilterPlugin, GrayScalePlugin {
	
	private ImagePlus imgPlus;
	
	@Override
	public void run(ImageProcessor arg0) {
		if (arg0.getClass().isAssignableFrom(ij.process.ColorProcessor.class))
			run((ColorProcessor) arg0);

		else if (arg0.getClass().isAssignableFrom(ByteProcessor.class))
			run((ByteProcessor) arg0);
		else
			throw new IllegalArgumentException("Not supported: " + arg0.getClass());
	}

	public void run(ColorProcessor cp) {
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
	
	public void run(ByteProcessor bp) {
		ImageUtils.initMMorph4J();
		GrayScaleImage output = filterImage(ImageJAdapter.toGrayScaleImage(bp));
		show(ImageJAdapter.toByteProcessor(output));
	}
	
	@Override
	public int accepts() {
		return PlugInFilter.DOES_8G | PlugInFilter.DOES_RGB;
	};
	
	@Override
	public String getPluginName() {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) this.getClass();
		
		if(clazz.getDeclaredAnnotation(Plugin.class) == null)
			return "mmlib4j - plugin";
		
		Plugin pluginAnnotarion = (Plugin) clazz.getDeclaredAnnotation(Plugin.class);
		return pluginAnnotarion.name();
	}
	
	@Override
	public void setImgPlus(ImagePlus plus) {
		this.imgPlus = plus;
	}

	@Override
	public ImagePlus getImgPlus() {
		return imgPlus;
	}
}
