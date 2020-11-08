package CodingTestFor2020_GT1_Newbi.src.main.java.application;

import data.ImageData;
import data.ImageDataHeader;

import java.io.FileInputStream;
import java.io.IOException;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by user on 2020-11-02.
 */
public class ImageDataReader {


  public static ImageData readImageDataFile(String filePath) throws IOException {
    //implement code
      FileInputStream fin = new FileInputStream(filePath);

      int c;
      int sequenceNumber, DataLength;
      int[] ImageData;

      for(int i=0; i<4; i++){
          fin.read();//Sync
      }

      byte[] temp = new byte[4];
      for(int i=0; i<4; i++){
          temp[i] =  (byte)fin.read();//Sequence Number
      }
      sequenceNumber = readDataByLittleEndianToInt(temp);

      temp = new byte[4];
      for(int i=0; i<4; i++){
          temp[i] =  (byte)fin.read();//Data Length
      }
      DataLength = readDataByLittleEndianToInt(temp);
      DataLength /=4;

      ImageData = new int[DataLength];
      for(int i=0; i<DataLength; i++){
          byte[] tempByte = new byte[4];

          for(int j=0; j<4; j++){
              tempByte[j] = (byte)fin.read();
          }

          ImageData[i] = readDataByLittleEndianToInt(tempByte); //ImageData array

          //이 부분에서 버그가 날 수도 있으니 주의
      }

      ImageDataHeader h = new ImageDataHeader(sequenceNumber, DataLength);
      ImageData d = new ImageData(h,ImageData);

      return d;
    //throw new NotImplementedException();
  }

  private static int readDataByLittleEndianToInt(byte[] readData) {
    //implement code
      //n번째부터 n+4번째 바이트 배열의 수를 인트로 바꿔주기 위한 함수 (리틀 엔디안 방식)

      int len = readData.length;
      int sum = 0;
      int multiple = 1;

      for(int i=0; i<len; i++){
          int temp = (int)readData[i];
          if(temp<0) temp+=256;
          sum += temp * multiple;
          multiple *= (16*16);
      }

      return sum;

    //throw new NotImplementedException();

  }
}
