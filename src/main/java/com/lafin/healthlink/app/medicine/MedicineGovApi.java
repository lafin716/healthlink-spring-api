package com.lafin.healthlink.app.medicine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lafin.healthlink.domain.medicine.Medicine;
import com.lafin.healthlink.domain.medicine.MedicineGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MedicineGovApi implements MedicineGateway {
  private final String apiUrl = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
  private final String apiKey;

  public MedicineGovApi(
      @Value("${medicine.api-key}") String apiKey
  ) {
    this.apiKey = apiKey;
  }

  @Override
  public List<Medicine> search(String medicineName) {
    try {
      RestTemplate rt = new RestTemplate();
      URI uri = new URI(
          apiUrl
              .concat("?serviceKey=").concat(apiKey)
              .concat("&type=json")
              .concat("&pageNo=").concat("1")
              .concat("&itemName=").concat(URLEncoder.encode(medicineName, StandardCharsets.UTF_8))
      );

      // HTTP GET 요청
      ResponseEntity<String> response = rt.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY, String.class);

      // HTTP GET 요청에 대한 응답 확인
      System.out.println("status : " + response.getStatusCode());
      System.out.println("body : " + response.getBody());

      ObjectMapper mapper = new ObjectMapper();
      var responseDto = mapper.readValue(response.getBody(), GovMedicineApiResponseDto.class);

      return toMedicines(responseDto);
    } catch (Exception e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  private List<Medicine> toMedicines(GovMedicineApiResponseDto dto) {
    if (Objects.isNull(dto)
        || Objects.isNull(dto.getBody())
        || Objects.isNull(dto.getBody().getItems())) {
      return new ArrayList<>();
    }

    return dto.getBody().getItems()
        .stream()
        .map(this::toMedicine)
        .collect(Collectors.toList());
  }

  private Medicine toMedicine(GovMedicineApiResponseDto.Item item) {
    return Medicine.builder()
        .itemSeq(item.getItemSeq())
        .medicineName(item.getItemName())
        .companyName(item.getEntpName())
        .pillImage(item.getItemImage())
        .usage(item.getUseMethodQesitm())
        .build();
  }
}
