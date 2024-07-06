package com.lafin.healthlink.app.medicine;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class GovMedicineApiResponseDto {
  private Header header;
  private Body body;

  @Data
  @ToString
  public static class Header {
    private String resultCode;
    private String resultMsg;
  }

  @Data
  @ToString
  public static class Body {
    private int pageNo;
    private int totalCount;
    private int numOfRows;
    private List<Item> items;
  }

  @Data
  @ToString
  public static class Item {
    private String entpName;
    private String itemName;
    private String itemSeq;
    private String efcyQesitm;
    private String useMethodQesitm;
    private String atpnWarnQesitm;
    private String atpnQesitm;
    private String intrcQesitm;
    private String seQesitm;
    private String depositMethodQesitm;
    private String openDe;
    private String updateDe;
    private String itemImage;
    private String bizrno;
  }
}
