package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bean.Search;

import util.DBUtil;

import entity.Course;
import entity.Lore;
import entity.Product;

public class ProductDAOImpl implements ProductDAO {

	public List<Course> findCourse() throws Exception {
		List<Course> list = new ArrayList<Course>();
		Connection con = DBUtil.getConnection();
		String sql = "select * from course";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Course c = null;
		while(rs.next()){
			c = new Course();
			c.setId(rs.getInt("id"));
			c.setName(rs.getString("name"));
			list.add(c);
		}
		DBUtil.close(con);
		return list;
	}

	public List<Lore> findLore(int courseId) throws Exception {
		List<Lore> list = new ArrayList<Lore>();
		Connection con = DBUtil.getConnection();
		String sql = "select * from lore where course_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,courseId);
		ResultSet rs = ps.executeQuery();
		Lore lore = null;
		while(rs.next()){
			lore = new Lore();
			lore.setId(rs.getInt("id"));
			lore.setName(rs.getString("name"));
			lore.setCourse_id(courseId);
			list.add(lore);
		}
		DBUtil.close(con);
		return list;
	}

	public void save(Product p) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "insert into product values (" +
				"null,?,?,?,?,?,?,?,?,?,now(),null,null)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,p.getCourse_id());
		ps.setInt(2,p.getLore_id());
		ps.setString(3,p.getName());
		ps.setString(4,p.getDescription());
		ps.setString(5, p.getStatus());
		ps.setDouble(6,p.getPrice());
		ps.setString(7,p.getImage());
		ps.setString(8,p.getVideo());
		ps.setString(9,p.getCreater());
		ps.executeUpdate();
		DBUtil.close(con);
		
	}

	public List<Product> findAll(Search search) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "select p.*,c.name cname from product p join " +
				"course c on p.course_id=c.id where 1=1 ";
		//添加搜索条件
		if(!StringUtils.isBlank(search.getCourseName())){
			sql +=" and c.name='"+search.getCourseName()+"'"; 
		}
		if(!StringUtils.isBlank(search.getName())){
			sql +=" and p.name like '%" +search.getName()+"%'";
		}
		if(!StringUtils.isBlank(search.getCreater())){
			sql +=" and p.creater like '%"+search.getCreater()+"%'";
		}
		
		if(!StringUtils.isBlank(search.getCreatetime())&&
				!"-请选择-".equals(search.getCreatetime())){
			//"2017/05/25"
			String data = search.getCreatetime().
			replaceAll("/", "-");
			sql +=" and p.createtime like '%"+data+"%'";
		}
		
		sql +=" limit ?,?";
		//System.out.println(sql);
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,(search.getCurrentPage()-1)*search.getPageSize());
		ps.setInt(2,search.getPageSize());
		ResultSet rs = ps.executeQuery();
		List<Product> list = getProducts(rs);
		DBUtil.close(con);
		return list;
	}
	
	public List<Product> getProducts(ResultSet rs) throws SQLException{
		List<Product> list = new ArrayList<Product>();
		Product p = null;
		SimpleDateFormat sf = 
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while(rs.next()){
			p = new Product();
			p.setId(rs.getInt("id"));
			p.setCourse_id(rs.getInt("course_id"));
			p.setLore_id(rs.getInt("lore_id"));
			p.setName(rs.getString("name"));
			p.setDescription(rs.getString("description"));
			p.setStatus(rs.getString("status"));
			p.setPrice(rs.getDouble("price"));
			p.setImage(rs.getString("image"));
			p.setVideo(rs.getString("video"));
			p.setCreater(rs.getString("creater"));
			p.setCreatetime(rs.getTimestamp("createtime"));
			Date createtime = rs.getTimestamp("createtime");
			if(createtime==null){
				p.setCtime("");
			}else{
				p.setCtime(sf.format(createtime));
			}
			p.setMender(rs.getString("mender"));
			p.setModifytime(rs.getTimestamp("modifytime"));
			Date modifytime = rs.getTimestamp("modifytime");
			if(modifytime==null){
				p.setMtime("");
			}else{
				p.setMtime(sf.format(modifytime));
			}
			Course c = new Course();
			c.setId(rs.getInt("course_id"));
			c.setName(rs.getString("cname"));
			p.setCourse(c);
			list.add(p);
		}
		return list;
	}

	public int getTotalPages(Search search) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "select count(*) from product p join course c " +
				"on p.course_id=c.id where 1=1";
		if(!StringUtils.isBlank(search.getCourseName())){
			sql +=" and c.name='"+search.getCourseName()+"'"; 
		}
		if(!StringUtils.isBlank(search.getName())){
			sql +=" and p.name like '%" +search.getName()+"%'";
		}
		if(!StringUtils.isBlank(search.getCreater())){
			sql +=" and p.creater like '%"+search.getCreater()+"%'";
		}
		
		if(!StringUtils.isBlank(search.getCreatetime())&&
				!"-请选择-".equals(search.getCreatetime())){
			String data = search.getCreatetime().
			replaceAll("/", "-");
			sql +=" and p.createtime like '%"+data+"%'";
		}
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int rows = rs.getInt(1);
		DBUtil.close(con);
		if(rows%search.getPageSize()==0){
			return rows/search.getPageSize();
		}else{
			return rows/search.getPageSize()+1;
		}
		
	}

	public void delProduct(int id) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "delete from product where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,id);
		ps.executeUpdate();
		DBUtil.close(con);
		
	}

	public Product findById(int id) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "select * from product where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery();
		Product p = getOneProduct(rs);
		DBUtil.close(con);
		return p;
	}

	
	public Product getOneProduct(ResultSet rs) throws SQLException{
		Product p = null;
		SimpleDateFormat sf = 
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(rs.next()){
			p = new Product();
			p.setId(rs.getInt("id"));
			p.setCourse_id(rs.getInt("course_id"));
			p.setLore_id(rs.getInt("lore_id"));
			p.setName(rs.getString("name"));
			p.setDescription(rs.getString("description"));
			p.setStatus(rs.getString("status"));
			p.setPrice(rs.getDouble("price"));
			p.setImage(rs.getString("image"));
			p.setVideo(rs.getString("video"));
			p.setCreater(rs.getString("creater"));
			p.setCreatetime(rs.getTimestamp("createtime"));
			Date createtime = rs.getTimestamp("createtime");
			if(createtime==null){
				p.setCtime("");
			}else{
				p.setCtime(sf.format(createtime));
			}
			p.setMender(rs.getString("mender"));
			p.setModifytime(rs.getTimestamp("modifytime"));
			Date modifytime = rs.getTimestamp("modifytime");
			if(modifytime==null){
				p.setMtime("");
			}else{
				p.setMtime(sf.format(modifytime));
			}
		}
		return p;
	}
	
	
	
}




