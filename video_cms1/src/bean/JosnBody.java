package bean;

public class JosnBody {
	private String code;//状态码 0 1 
	private String msg;//描述信息 success
	private Object obj;//服务器返回的数据 比如集合
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
