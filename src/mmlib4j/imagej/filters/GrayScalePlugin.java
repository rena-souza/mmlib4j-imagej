package mmlib4j.imagej.filters;

import ij.process.ByteProcessor;

public interface GrayScalePlugin extends Plugin {
	public void run(ByteProcessor bp);
}
