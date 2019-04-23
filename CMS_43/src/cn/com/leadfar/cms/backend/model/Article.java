package cn.com.leadfar.cms.backend.model;

import java.util.Date;
import java.util.Set;

public class Article {
	private int id;
	private String title;//标题
	private String content;//内容
	private String source;//来源
	private String author; //作者
	private String keyword; //关键字
	private String intro; //简介
	private String type; //分类
	private boolean recommend; //是否推荐阅读
	private boolean headline; //是否作为首页头条
	private int leaveNumber; //留言数
	private int clickNumber; //点击量
	private Set<Channel> channels; //所属频道
	private int topicId; //文章所属的主题，如果不属于某个主题，则此值为0
	private Date createTime; //创建时间
	private Date updateTime; //更新时间
	private Date deployTime; //发布时间
	private int adminId; //本篇文章是由哪个管理员创建的
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getDeployTime() {
		return deployTime;
	}
	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLeaveNumber() {
		return leaveNumber;
	}
	public void setLeaveNumber(int leaveNumber) {
		this.leaveNumber = leaveNumber;
	}
	public int getClickNumber() {
		return clickNumber;
	}
	public void setClickNumber(int clickNumber) {
		this.clickNumber = clickNumber;
	}
	public Set<Channel> getChannels() {
		return channels;
	}
	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isRecommend() {
		return recommend;
	}
	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}
	public boolean isHeadline() {
		return headline;
	}
	public void setHeadline(boolean headline) {
		this.headline = headline;
	}
}
