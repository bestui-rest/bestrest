package bean;

public class Search extends Page {
	private String courseName;//课程方向名称
	private String name;//课程名称
	private String creater;//上传者
	private String createtime;//上传时间
	
	public Search() {
		super();
	}
	public Search(String courseName, String name, String creater,
			String createtime,int page,int pageSize) {
		super();
		super.setCurrentPage(page);
		super.setPageSize(pageSize);
		this.courseName = courseName;
		this.name = name;
		this.creater = creater;
		this.createtime = createtime;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
}
