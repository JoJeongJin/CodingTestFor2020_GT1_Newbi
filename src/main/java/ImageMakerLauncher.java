import java.io.File;

/**
 * Created by user on 2020-11-02.
 */
public class ImageMakerLauncher {

  public static void main(String[] args) {
    //implement code
    //read file
    //resources -> sample 폴더안에 있는 파일이름=="filePath" 를 이미지 데이터로 불러 오고 반환하기
    File path = new File("./src/CodingTestFor2020_GT1_Newbi/src/main/resources/sample/");
    System.out.println(path);
    File[] fileList = path.listFiles();

    if(fileList.length > 0){
      int fileLen = fileList.length;
      for(int i=0; i<fileLen; i++){
        System.out.println(fileList[i]);
      }
    }

    //processing

    //write result (bmp, jpg, png or etc.)
  }
}
