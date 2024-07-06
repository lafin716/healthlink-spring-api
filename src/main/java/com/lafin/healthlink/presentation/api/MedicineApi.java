package com.lafin.healthlink.presentation.api;


import com.lafin.healthlink.domain.medicine.Medicine;
import com.lafin.healthlink.domain.medicine.MedicineUseCase;
import com.lafin.healthlink.presentation.api.base.BaseApi;
import com.lafin.healthlink.presentation.api.base.BaseResponse;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineApi extends BaseApi {
  private final MedicineUseCase medicineUseCase;

  @GetMapping
  public ResponseEntity<BaseResponse<List<Medicine>>> getList(
      @RequestParam(name = "pageNum", required = false) String pageNum,
      @RequestParam(name = "medicineName", required = false) String medicineName
  ) {
    // 의약품명이 없는 경우 오류 반환
    if (StringUtils.isBlank(medicineName)) {
      return BaseResponse.fail("의약품명을 입력해주세요.").response();
    }
    // 페이지번호가 없는 경우 1로 고정
    // TODO 번호형식이 아닌경우 (음수, 소수점 포함) 추가 처리 필요
    if (StringUtils.isBlank(pageNum)) {
      pageNum = "1";
    }

    // 의약품 검색
    var result = medicineUseCase.search(medicineName);
    if (result == null) {
      return BaseResponse.fail("의약품 검색 중 오류가 발생하였습니다").response();
    }

    return BaseResponse.ok(result).response();
  }
}
