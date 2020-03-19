package com.learn.webflux.result;

/**
 * @author SuanCaiYv
 * @time 2020/3/16 下午7:48
 */
public class ResultBean<T>
{
    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    private int code = SUCCESS;

    private String msg;

    private T data;


    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
