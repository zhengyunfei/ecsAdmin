package com.zero2ipo.cfj.order.bo;

import java.util.Date;

import com.zero2ipo.module.entity.order.OrderEntity;

public class Order {
	private String orderId;
	private String orderNum;
	private String productId;
	private String productContent;
	private String price;
	private String invoice;
	private String invoiceType;
	private String invoiceName;
	private String postage;
	private String userName;
	private String company;
	private String address;
	private String post;
	private String tel;
	private String phone;
	private String email;
	private Date inputTime;
	private String audit;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getPostage() {
		return postage;
	}
	public void setPostage(String postage) {
		this.postage = postage;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getInputTime() {
		return inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
	}
	public static OrderEntity boToEntity(Order bo){
		OrderEntity entity=new OrderEntity();
		entity.setOrderId(bo.getOrderId());
		entity.setOrderNum(bo.getOrderNum());
		entity.setProductId(bo.getProductId());
		entity.setProductContent(bo.getProductContent());
		entity.setPrice(bo.getPrice());
		entity.setInvoice(bo.getInvoice());
		entity.setInvoiceType(bo.getInvoiceType());
		entity.setInvoiceName(bo.getInvoiceName());
		entity.setPostage(bo.getPostage());
		entity.setUserName(bo.getUserName());
		entity.setCompany(bo.getCompany());
		entity.setAddress(bo.getAddress());
		entity.setPost(bo.getPost());
		entity.setTel(bo.getTel());
		entity.setPhone(bo.getPhone());
		entity.setEmail(bo.getEmail());
		entity.setInputTime(bo.getInputTime());
		entity.setAudit(bo.getAudit());
		return entity;
	}
	public static Order entityToBo(OrderEntity entity){
		Order bo=new Order();
		bo.setOrderId(entity.getOrderId());
		bo.setOrderNum(entity.getOrderNum());
		bo.setProductId(entity.getProductId());
		bo.setProductContent(entity.getProductContent());
		bo.setPrice(entity.getPrice());
		bo.setInvoice(entity.getInvoice());
		bo.setInvoiceType(entity.getInvoiceType());
		bo.setInvoiceName(entity.getInvoiceName());
		bo.setPostage(entity.getPostage());
		bo.setUserName(entity.getUserName());
		bo.setCompany(entity.getCompany());
		bo.setAddress(entity.getAddress());
		bo.setPost(entity.getPost());
		bo.setTel(entity.getTel());
		bo.setPhone(entity.getPhone());
		bo.setEmail(entity.getEmail());
		bo.setInputTime(entity.getInputTime());
		bo.setAudit(entity.getAudit());
		return bo;
	}
	
	
}
