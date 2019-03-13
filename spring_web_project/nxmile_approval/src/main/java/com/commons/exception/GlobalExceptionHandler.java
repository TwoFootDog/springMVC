package com.commons.exception;


import com.commons.dao.daoInterface.MongoDBDao;
import com.commons.domain.CustomizeHeaderVO;
import com.commons.domain.ErrorOutputWrapperVO;
import com.commons.utils.JsonUtils;
//import com.commons.utils.RereadableRequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
@ResponseBody
@Log4j
public class GlobalExceptionHandler{

    @Setter(onMethod_ = {@Autowired})
    private MongoDBDao mongoDBDao;

    // 요청 + 응답 custom header
    private CustomizeHeaderVO requestCustomHeader;
    private CustomizeHeaderVO responseCustomHeader;

    // response header
    private HttpHeaders responseHeader;

    // 에러 output(header + body)
    private ErrorOutputWrapperVO outputWrapperVO;


    @ExceptionHandler(value = ValidException.class)
    public ResponseEntity<ErrorOutputWrapperVO> handleValidException(ValidException e, HttpServletRequest request)  {

        JsonUtils jsonUtils = new JsonUtils();  // 자체 정의한 jsonUtils 객체 생성
        JSONObject jObj = jsonUtils.readJSONStringFromRequestBody(request); // HttpServletRequest를 JSONObject 형태로 변환


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            requestCustomHeader = objectMapper.readValue(jObj.get("header").toString(), CustomizeHeaderVO.class);   // JSONObject에 포함된 CustomerHeader를 추출
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        /* TEST 용 */
        log.info("json header-------------" + requestCustomHeader);
        log.info("parameter---------------" + request.getParameter("resd_no"));
        log.info("parameter---------------" + request.getParameter("name"));
        log.info("parameters--------------" + request.getParameterNames());
        /* TEST용 종료 */

        responseCustomHeader =
                new CustomizeHeaderVO(
                        requestCustomHeader.getTelgrm_no().substring(0, 3).concat("1"),
                        requestCustomHeader.getOrgan_cd(),
                        new SimpleDateFormat("yyyyMMdd").format(new Date()),
                        new SimpleDateFormat("HHmmss").format(new Date()),
                        requestCustomHeader.getTrc_no(),
                        requestCustomHeader.getTelgrm_fg(),
                        e.getAns_cd().substring(0, 2),
                        e.getAns_cd().substring(2, 4),
                        e.getAns_msg());

        outputWrapperVO = new ErrorOutputWrapperVO();
        outputWrapperVO.setHeader(responseCustomHeader);

        responseHeader = new HttpHeaders();
        responseHeader.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<ErrorOutputWrapperVO> result = new ResponseEntity<ErrorOutputWrapperVO>(outputWrapperVO, responseHeader, HttpStatus.OK);

        mongoDBDao.outputDataInsert(result);    // 에러 출력값을 mongodb 에 저장

        return result;
    }
}
