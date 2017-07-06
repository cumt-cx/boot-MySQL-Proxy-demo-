package cc.qianglovepei.exception;

import cc.qianglovepei.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        Result<String> r = new Result<>();
        r.setMessage(e.getMessage());
        r.setCode(Result.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURI());
        r.setServerTime(new Date());
        return r;
    }

}
