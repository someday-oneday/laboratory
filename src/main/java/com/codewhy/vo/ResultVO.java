package com.codewhy.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ResultVO {
    private Integer code;
    private String msg;
    private Object data;

    public ResultVO() {

    }

    public ResultVO(int i, String s) {
        this.code=i;
        this.msg=s;
    }

    /**
     * 请求成功
     * @param msg
     * @param data
     * @return
     */
    public ResponseEntity<ResultVO> success(String msg, Object data){
        ResultVO result = new ResultVO();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<ResultVO> fail(String msg){
        ResultVO result = new ResultVO();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(null);
        return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ResultVO> fail1(ResultVO msg){
        ResultVO result = new ResultVO();
        result.setCode(500);
        result.setMsg(msg.getMsg());
        result.setData(null);
        return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ResultVO> fail(String msg, Object data){
        ResultVO result = new ResultVO();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(data);
        return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
