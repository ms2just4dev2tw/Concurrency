package jcip.ex06;

import java.util.*;

/**
 * <h6>CodeList 6-10 SingleThreadRendere</h6>
 * <i>Rendering page elements sequentially</i>
 * <p>
 * 
 * @author Brian Goetz and Tim Peierls
 */
public abstract class SingleThreadRenderer {
	
	void renderPage(CharSequence source) {
		renderText(source);
		List<ImageData> imageData = new ArrayList<ImageData>();
		for (ImageInfo imageInfo : scanForImageInfo(source))
			imageData.add(imageInfo.downloadImage());
		for (ImageData data : imageData)
			renderImage(data);
	}

	interface ImageData {
	}

	interface ImageInfo {
		ImageData downloadImage();
	}

	abstract void renderText(CharSequence s);

	abstract List<ImageInfo> scanForImageInfo(CharSequence s);

	abstract void renderImage(ImageData i);
}
