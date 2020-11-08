import data.ImageData;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static CodingTestFor2020_GT1_Newbi.src.main.java.application.ImageDataReader.readImageDataFile;

/**
 * Created by user on 2020-11-02.
 */
public class ImageMakerLauncher {

  public static void main(String[] args) throws IOException {
    //implement code
    //read file
    //resources -> sample 폴더안에 있는 파일이름=="filePath" 를 이미지 데이터로 불러 오고 반환하기
    File path = new File("./src/CodingTestFor2020_GT1_Newbi/src/main/resources/sample/");
    System.out.println(path);

      System.out.println("---파일 리스트 출력 시작---");
    File[] fileList = path.listFiles();

    int fileLen = fileList.length;
    if(fileList.length > 0){
      for(int i=0; i<fileLen; i++){
        System.out.println(fileList[i]);
        System.out.println(String.valueOf(fileList[i]).getClass());
      }
    }

    System.out.println("---파일 리스트 출력 끝---");
    //processing
      //Image Data 객체 생성 (리스트 크기 만큼 ImageData 배열 만들기 ->
      List<ImageData> ID = new ArrayList<>();

      for(int i=0; i<fileLen; i++){
        ID.add(readImageDataFile(String.valueOf(fileList[i])));
      }

      //각 생성된 Image Data 객체를 가지고 Sequence Number에 맞게 재배치하여 Image Data 병합 (Sequence Number에 맞게 정렬후에 각 Image Data가 가지고 있는 int[]배열을 이어 붙여주기
      Collections.sort(ID);
      System.out.println("정렬 완료");

      //병합된 Image Data(int[][] 배열이어 붙여진 것)을 이미지 파일로 저장 (이어진 imageData 배열을 그림으로 바꾸어 주고 저장)
      //write result (bmp, jpg, png or etc.)

      int[][] resultImage = new int[fileLen][ID.get(0).getImageDataHeader().getDataLength()];
      int ImageLen = ID.get(0).getImageDataHeader().getDataLength();

      for(int i=0; i<fileLen; i++){
        for(int j=0; j<ImageLen; j++){
          int[] temp = ID.get(i).getImageData();
          resultImage[i][j] = temp[j];
        }
      }

    BufferedImage theImage = new BufferedImage(ImageLen, fileLen, BufferedImage.TYPE_INT_RGB);
    for(int y = 0; y<fileLen; y++){
      for(int x = 0; x<ImageLen; x++){
        int value = resultImage[y][x] << 16 | resultImage[y][x] << 8 | resultImage[y][x];
        theImage.setRGB(x, y, value);
      }
    }

    File outputfile = new File("saved.bmp");
    ImageIO.write(theImage, "png", outputfile);


  }
}
