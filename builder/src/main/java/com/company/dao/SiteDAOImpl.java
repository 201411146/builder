package com.company.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.company.dto.SiteDTO;
@Repository
public class SiteDAOImpl implements SiteDAO {
	@Inject
	private SqlSession sql;

	private static String namespace = "com.company.mappers.siteMapper";
	
	//사이트 목록
	@Override
	public List<SiteDTO> list(final String userid) {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".list",userid);
	}
	
	//사이트 생성
	@Override
	public void create(final String userid, final String userpass, final String sitename, final String category, final String status, final String topcategory, final String siteemail, final String logo) {
		// TODO Auto-generated method stub
		 HashMap data = new HashMap();
		  
		 data.put("userid", userid);
		 data.put("userpass", userpass);
		 data.put("sitename", sitename);
		 data.put("category", category);
		 data.put("status", status);
		 data.put("topcategory", topcategory);
		 data.put("siteemail", siteemail);
		 data.put("logo", logo);
		if(status.equals("deploy")) {
			String member = "create table "+ sitename +"(userno int not null auto_increment, management varchar(50), \r\n" + 
					"  userid varchar(50) not null,\r\n" + 
					"  userpass varchar(50) not null,\r\n" + 
					"   regDate     date           not null default now(),\r\n" + 
					"  primary key(userid),\r\n" + 
					"  UNIQUE(userno))";
			data.put("member",member);
			sql.update(namespace+".createmember",data);
			sql.insert(namespace+".management",data);
			
			String bookmark = "create table "+sitename+"bookmark(boardid int,userid varchar(50) not null)";
			data.put("bookmark",bookmark);
			sql.update(namespace+".createbookmark",data);
			
			String event = "create table "+sitename+"event(eventid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,file varchar(300), title varchar(50))";
			data.put("event",event);
			sql.update(namespace+".createevent",data);
			
			String notebox = "create table "+sitename+"notebox(noteboxid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
					"boardid int,\r\n" + 
					"touserid varchar(50) not null,\r\n" + 
					"fromuserid varchar(50) not null,\r\n" + 
					"title varchar(50),\r\n" + 
					"content varchar(300))";
			data.put("notebox",notebox);
			sql.update(namespace+".createnotebox",data);
			
			String banner = "create table "+sitename+"banner(bannerid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,file varchar(300), title varchar(300), link varchar(300))";
			data.put("banner",banner);
			sql.update(namespace+".createbanner",data);
			
			
			
			if(category.equals("motel")) {
				String board = "create table "+sitename+"board(boardid INT NOT NULL AUTO_INCREMENT PRIMARY KEY, regdate date not null default now(), userid VARCHAR(50), title varchar(50), image varchar(300), 지역 varchar(50), 종류 varchar(50))";
				data.put("board",board);
				sql.update(namespace+".createboard",data);
				
				String sitecategory = "create table "+sitename+"category(categoryid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
						"categoryname varchar(50),\r\n" + 
						"depth varchar(50),\r\n" + 
						"parent varchar(50))";
				data.put("sitecategory",sitecategory);
				sql.update(namespace+".createsitecategory",data);
				String categoryname[]= {"지역","종류","서울","모텔","호텔"};
				String depth_category[]= {"1","1","2","2","2"};
				String parent_category[]= {"","","지역","종류","종류"};
				for(int i = 0; i < categoryname.length; i++) {
					data.put("categoryname",categoryname[i]);
					data.put("depth_category",depth_category[i]);
					data.put("parent_category",parent_category[i]);
					sql.insert(namespace+".insertcategory",data);
				}
				
				String sitefield = "create table "+sitename+"field(fieldid int not null auto_increment, fieldname varchar(50), fieldtype varchar(50), depth varchar(50), parent varchar(50),  primary key(fieldid))";
				data.put("sitefield",sitefield);
				sql.update(namespace+".createsitefield",data);
				String fieldname[] = {"userid","title","image","지역","종류","서울","모텔","호텔"};
				String fieldtype[] = {"hidden","title","file","select","select","","",""};
				String depth[] = {"1","1","1","1","1","2","2","2"};
				String parent[] = {"","","","","","지역","종류","종류"};
				for(int i = 0; i < fieldname.length; i++) {
					data.put("fieldname",fieldname[i]);
					data.put("fieldtype",fieldtype[i]);
					data.put("depth",depth[i]);
					data.put("parent",parent[i]);
					sql.insert(namespace+".insertfield",data);
				}
				
				
			}
			if(category.equals("clothes")) {
				String board = "create table "+sitename+"board(boardid INT NOT NULL AUTO_INCREMENT PRIMARY KEY, regdate date not null default now(), userid VARCHAR(50), title varchar(50), image varchar(300), 종류 varchar(50), 세부종류 varchar(50))";
				data.put("board",board);
				sql.update(namespace+".createboard",data);
				
				String sitecategory = "create table "+sitename+"category(categoryid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
						"categoryname varchar(50),\r\n" + 
						"depth varchar(50),\r\n" + 
						"parent varchar(50))";
				data.put("sitecategory",sitecategory);
				sql.update(namespace+".createsitecategory",data);
				String categoryname[]= {"상의","하의"};
				String depth_category[]= {"1","1"};
				String parent_category[]= {"",""};
				for(int i = 0; i < categoryname.length; i++) {
					data.put("categoryname",categoryname[i]);
					data.put("depth_category",depth_category[i]);
					data.put("parent_category",parent_category[i]);
					sql.insert(namespace+".insertcategory",data);
				}

				String sitefield = "create table "+sitename+"field(fieldid int not null auto_increment, fieldname varchar(50), fieldtype varchar(50), depth varchar(50), parent varchar(50),  primary key(fieldid))";
				data.put("sitefield",sitefield);
				sql.update(namespace+".createsitefield",data);
				String fieldname[] = {"userid","title","image","종류","세부종류"};
				String fieldtype[] = {"hidden","title","file","select","select"};
				String depth[] = {"1","1","1","1","1"};
				String parent[] = {"","","","",""};
				for(int i = 0; i < fieldname.length; i++) {
					data.put("fieldname",fieldname[i]);
					data.put("fieldtype",fieldtype[i]);
					data.put("depth",depth[i]);
					data.put("parent",parent[i]);
					sql.insert(namespace+".insertfield",data);
				}
			}
			
			if(category.equals("allba")) {
				String board = "create table "+sitename+"board(boardid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
						"regdate date not null default now(),\r\n" + 
						"userid VARCHAR(50),\r\n" + 
						"title varchar(50),\r\n" + 
						"경력 varchar(50),\r\n" + 
						"성별 varchar(50),\r\n" + 
						"직종 varchar(50), \r\n" + 
						"기간 varchar(50), \r\n" + 
						"지역 varchar(50), \r\n" + 
						"address varchar(50), \r\n" + 
						"시급 varchar(50),\r\n" + 
						"image varchar(300),\r\n" + 
						"deadline date,\r\n" + 
						"content varchar(300))";
				data.put("board",board);
				sql.update(namespace+".createboard",data);
				
				String sitecategory = "create table "+sitename+"category(categoryid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
						"categoryname varchar(50),\r\n" + 
						"depth varchar(50),\r\n" + 
						"parent varchar(50))";
				data.put("sitecategory",sitecategory);
				sql.update(namespace+".createsitecategory",data);
				String categoryname[]= {"직종","지역","경력","매장관리","서빙주방","서울","부산","인천","무관","신입","경력자"};
				String depth_category[]= {"1","1","1","2","2","2","2","2","2","2","2"};
				String parent_category[]= {"","","","직종","직종","지역","지역","지역","경력","경력","경력"};
				for(int i = 0; i < categoryname.length; i++) {
					data.put("categoryname",categoryname[i]);
					data.put("depth_category",depth_category[i]);
					data.put("parent_category",parent_category[i]);
					sql.insert(namespace+".insertcategory",data);
				}

				String sitefield = "create table "+sitename+"field(fieldid int not null auto_increment, fieldname varchar(50), fieldtype varchar(50), depth varchar(50), parent varchar(50),  primary key(fieldid))";
				data.put("sitefield",sitefield);
				sql.update(namespace+".createsitefield",data);
				String fieldname[] = {"userid","title","경력","성별","직종","기간","지역","address","시급","image","deadline","content","매장관리","서빙주방","무관","신입","경력자","남","여","ALL","서울","부산","인천","1일","1주일이내","1주일~1개월","1개월~3개월"};
				String fieldtype[] = {"hidden","title","select","select","select","select","select","address","text","file","date","content","","","","","","","","","","","","","","",""};
				String depth[] = {"1","1","1","1","1","1","1","1","1","1","1","1","2","2","2","2","2","2","2","2","2","2","2","2","2","2","2"};
				String parent[] = {"","","","","","","","","","","","","직종","직종","경력","경력","경력","성별","성별","성별","지역","지역","지역","기간","기간","기간","기간"};
				for(int i = 0; i < fieldname.length; i++) {
					data.put("fieldname",fieldname[i]);
					data.put("fieldtype",fieldtype[i]);
					data.put("depth",depth[i]);
					data.put("parent",parent[i]);
					sql.insert(namespace+".insertfield",data);
				}
			}
		}
		
		
		sql.insert(namespace+".create",data);
	}
	
	//사이트 삭제
	@Override
	public void delete(final int siteid, final String sitename, final String status) {
		// TODO Auto-generated method stub
		HashMap data = new HashMap();
		
		if(status.equals("deploy")) {
			String member = "drop table "+ sitename;
			String board = "drop table "+ sitename+"board";
			String bookmark = "drop table "+ sitename+"bookmark";
			String notebox = "drop table "+ sitename+"notebox";
			String sitecategory = "drop table "+ sitename+"category";
			String sitefield = "drop table "+ sitename+"field";
			String event = "drop table "+ sitename+"event";
			String banner = "drop table "+ sitename+"banner";
			
			 data.put("member",member);
			 data.put("board",board);
			 data.put("bookmark",bookmark);
			 data.put("notebox",notebox);
			 data.put("sitecategory",sitecategory);
			 data.put("sitefield",sitefield);
			 data.put("event",event);
			 data.put("banner",banner);
			 
			sql.delete(namespace+".deletemember",data);
			sql.delete(namespace+".deleteboard",data);
			sql.delete(namespace+".deletebookmark",data);
			sql.delete(namespace+".deletenotebox",data);
			sql.delete(namespace+".deletesitecategory",data);
			sql.delete(namespace+".deletesitefield",data);
			sql.delete(namespace+".deleteevent",data);
			sql.delete(namespace+".deletebanner",data);
			
		}else {
			
		}
		sql.delete(namespace+".delete",siteid);
	}

	//사이트 수정get
	@Override
	public SiteDTO view(int siteid) {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace+".view", siteid);
	}
	//사이트 수정post
	@Override
	public void modify(final String userid, final String userpass, final String sitename, final String category, final String status, final String topcategory, final String siteemail, final String logo, final int siteid) {
		// TODO Auto-generated method stub
		 HashMap data = new HashMap();
		  
		 data.put("siteid", siteid);
		 data.put("userid", userid);
		 data.put("userpass", userpass);
		 data.put("sitename", sitename);
		 data.put("category", category);
		 data.put("status", status);
		 data.put("topcategory", topcategory);
		 data.put("siteemail", siteemail);
		 data.put("logo", logo);
		 
		 if(status.equals("deploy")) {
				String member = "create table "+ sitename +"(userno int not null auto_increment, management varchar(50), \r\n" + 
						"  userid varchar(50) not null,\r\n" + 
						"  userpass varchar(50) not null,\r\n" + 
						"   regDate     date           not null default now(),\r\n" + 
						"  primary key(userid),\r\n" + 
						"  UNIQUE(userno))";
				data.put("member",member);
				sql.update(namespace+".createmember",data);
				sql.insert(namespace+".management",data);
				
				String bookmark = "create table "+sitename+"bookmark(boardid int,userid varchar(50) not null)";
				data.put("bookmark",bookmark);
				sql.update(namespace+".createbookmark",data);
				
				String event = "create table "+sitename+"event(eventid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,file varchar(300), title varchar(50))";
				data.put("event",event);
				sql.update(namespace+".createevent",data);
				
				String notebox = "create table "+sitename+"notebox(noteboxid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
						"boardid int,\r\n" + 
						"touserid varchar(50) not null,\r\n" + 
						"fromuserid varchar(50) not null,\r\n" + 
						"title varchar(50),\r\n" + 
						"content varchar(300))";
				data.put("notebox",notebox);
				sql.update(namespace+".createnotebox",data);
				
				String banner = "create table "+sitename+"banner(bannerid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,file varchar(300), title varchar(300), banner varchar(300))";
				data.put("banner",banner);
				sql.update(namespace+".createbanner",data);
				
				
				
				if(category.equals("motel")) {
					String board = "create table "+sitename+"board(boardid INT NOT NULL AUTO_INCREMENT PRIMARY KEY, regdate date not null default now(), userid VARCHAR(50), title varchar(50), image varchar(300), 지역 varchar(50), 종류 varchar(50))";
					data.put("board",board);
					sql.update(namespace+".createboard",data);
					
					String sitecategory = "create table "+sitename+"category(categoryid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
							"categoryname varchar(50),\r\n" + 
							"depth varchar(50),\r\n" + 
							"parent varchar(50))";
					data.put("sitecategory",sitecategory);
					sql.update(namespace+".createsitecategory",data);
					String categoryname[]= {"지역","종류","서울","모텔","호텔"};
					String depth_category[]= {"1","1","2","2","2"};
					String parent_category[]= {"","","지역","종류","종류"};
					for(int i = 0; i < categoryname.length; i++) {
						data.put("categoryname",categoryname[i]);
						data.put("depth_category",depth_category[i]);
						data.put("parent_category",parent_category[i]);
						sql.insert(namespace+".insertcategory",data);
					}
					
					String sitefield = "create table "+sitename+"field(fieldid int not null auto_increment, fieldname varchar(50), fieldtype varchar(50), depth varchar(50), parent varchar(50),  primary key(fieldid))";
					data.put("sitefield",sitefield);
					sql.update(namespace+".createsitefield",data);
					String fieldname[] = {"userid","title","image","지역","종류","서울","모텔","호텔"};
					String fieldtype[] = {"hidden","title","file","select","select","","",""};
					String depth[] = {"1","1","1","1","1","2","2","2"};
					String parent[] = {"","","","","","지역","종류","종류"};
					for(int i = 0; i < fieldname.length; i++) {
						data.put("fieldname",fieldname[i]);
						data.put("fieldtype",fieldtype[i]);
						data.put("depth",depth[i]);
						data.put("parent",parent[i]);
						sql.insert(namespace+".insertfield",data);
					}
					
					
				}
				if(category.equals("clothes")) {
					String board = "create table "+sitename+"board(boardid INT NOT NULL AUTO_INCREMENT PRIMARY KEY, regdate date not null default now(), userid VARCHAR(50), title varchar(50), image varchar(300), 종류 varchar(50), 세부종류 varchar(50))";
					data.put("board",board);
					sql.update(namespace+".createboard",data);
					
					String sitecategory = "create table "+sitename+"category(categoryid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
							"categoryname varchar(50),\r\n" + 
							"depth varchar(50),\r\n" + 
							"parent varchar(50))";
					data.put("sitecategory",sitecategory);
					sql.update(namespace+".createsitecategory",data);
					String categoryname[]= {"상의","하의"};
					String depth_category[]= {"1","1"};
					String parent_category[]= {"",""};
					for(int i = 0; i < categoryname.length; i++) {
						data.put("categoryname",categoryname[i]);
						data.put("depth_category",depth_category[i]);
						data.put("parent_category",parent_category[i]);
						sql.insert(namespace+".insertcategory",data);
					}

					String sitefield = "create table "+sitename+"field(fieldid int not null auto_increment, fieldname varchar(50), fieldtype varchar(50), depth varchar(50), parent varchar(50),  primary key(fieldid))";
					data.put("sitefield",sitefield);
					sql.update(namespace+".createsitefield",data);
					String fieldname[] = {"userid","title","image","종류","세부종류"};
					String fieldtype[] = {"hidden","title","file","select","select"};
					String depth[] = {"1","1","1","1","1"};
					String parent[] = {"","","","",""};
					for(int i = 0; i < fieldname.length; i++) {
						data.put("fieldname",fieldname[i]);
						data.put("fieldtype",fieldtype[i]);
						data.put("depth",depth[i]);
						data.put("parent",parent[i]);
						sql.insert(namespace+".insertfield",data);
					}
				}
				
				if(category.equals("allba")) {
					String board = "create table "+sitename+"board(boardid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
							"regdate date not null default now(),\r\n" + 
							"userid VARCHAR(50),\r\n" + 
							"title varchar(50),\r\n" + 
							"경력 varchar(50),\r\n" + 
							"성별 varchar(50),\r\n" + 
							"직종 varchar(50), \r\n" + 
							"기간 varchar(50), \r\n" + 
							"지역 varchar(50), \r\n" + 
							"address varchar(50), \r\n" + 
							"시급 varchar(50),\r\n" + 
							"image varchar(300),\r\n" + 
							"deadline date,\r\n" + 
							"content varchar(300))";
					data.put("board",board);
					sql.update(namespace+".createboard",data);
					
					String sitecategory = "create table "+sitename+"category(categoryid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\r\n" + 
							"categoryname varchar(50),\r\n" + 
							"depth varchar(50),\r\n" + 
							"parent varchar(50))";
					data.put("sitecategory",sitecategory);
					sql.update(namespace+".createsitecategory",data);
					String categoryname[]= {"직종","지역","경력","매장관리","서빙주방","서울","부산","인천","무관","신입","경력자"};
					String depth_category[]= {"1","1","1","2","2","2","2","2","2","2","2"};
					String parent_category[]= {"","","","직종","직종","지역","지역","지역","경력","경력","경력"};
					for(int i = 0; i < categoryname.length; i++) {
						data.put("categoryname",categoryname[i]);
						data.put("depth_category",depth_category[i]);
						data.put("parent_category",parent_category[i]);
						sql.insert(namespace+".insertcategory",data);
					}

					String sitefield = "create table "+sitename+"field(fieldid int not null auto_increment, fieldname varchar(50), fieldtype varchar(50), depth varchar(50), parent varchar(50),  primary key(fieldid))";
					data.put("sitefield",sitefield);
					sql.update(namespace+".createsitefield",data);
					String fieldname[] = {"userid","title","경력","성별","직종","기간","지역","address","시급","image","deadline","content","매장관리","서빙주방","무관","신입","경력자","남","여","ALL","서울","부산","인천","1일","1주일이내","1주일~1개월","1개월~3개월"};
					String fieldtype[] = {"hidden","title","select","select","select","select","select","address","text","file","date","content","","","","","","","","","","","","","","",""};
					String depth[] = {"1","1","1","1","1","1","1","1","1","1","1","1","2","2","2","2","2","2","2","2","2","2","2","2","2","2","2"};
					String parent[] = {"","","","","","","","","","","","","직종","직종","경력","경력","경력","성별","성별","성별","지역","지역","지역","기간","기간","기간","기간"};
					for(int i = 0; i < fieldname.length; i++) {
						data.put("fieldname",fieldname[i]);
						data.put("fieldtype",fieldtype[i]);
						data.put("depth",depth[i]);
						data.put("parent",parent[i]);
						sql.insert(namespace+".insertfield",data);
					}
				}
			}
		 else {
			 
		 }
		 
		 
		 sql.update(namespace+".modifysite",data);
		 
		
	}

}
