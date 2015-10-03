package mmlib4j.imagej.filters;

import ij.process.ColorProcessor;

public interface ColorFilterPlugin extends FilterPlugin {
	public void run(ColorProcessor cp);
}
