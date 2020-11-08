package data;

//import com.esotericsoftware.kryo.io.Input;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by user on 2020-11-02.
 */
public class ImageData {
  public static final int SYNC = 0x1ACFFC1D;
  private final data.ImageDataHeader imageDataHeader;
  private final int[] imageData;


  public ImageData(data.ImageDataHeader imageDataHeader, int[] imageData) {
    this.imageDataHeader = imageDataHeader;
    this.imageData = imageData;
  }

  public int[] getImageData() {
    return imageData;
  }

  public data.ImageDataHeader getImageDataHeader() {
    return imageDataHeader;
  }
}
