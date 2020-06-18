package com.zen.community.dto;

import com.zen.community.exception.CustomizeErrorCode;
import com.zen.community.exception.CustomizeException;
import com.zen.community.exception.ICustomizeErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/18 9:19
 */

// 通用 JSON 传递对象
@Data
@AllArgsConstructor
public class ResultDTO {
  private Integer code;
  private String message;

  // 通用返回 200 对象
  private static final ResultDTO COMMON_OK = new ResultDTO(200, "请求成功");
  private static final ResultDTO COMMON_ERROR = ResultDTO.errorOf(CustomizeErrorCode.REQUEST_5_SERIES_ERROR);

  private ResultDTO() {}

  private static ResultDTO errorOf(Integer code, String message) {
    return new ResultDTO(code, message);
  }

  public static ResultDTO errorOf(ICustomizeErrorCode customizeErrorCode) {
    return new ResultDTO(customizeErrorCode.getCode(), customizeErrorCode.getMessage());
  }

  public static ResultDTO errorOf(CustomizeException customizeException) {
    return new ResultDTO(customizeException.getCode(), customizeException.getMessage());
  }

  public static ResultDTO error() {
    return COMMON_ERROR;
  }

  public static ResultDTO ok() {
    return COMMON_OK;
  }
}
