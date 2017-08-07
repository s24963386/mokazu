package com.mokazu.redis;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author jesen.shen
 * @since 2017-03-29
 */
@TableName("ipw_account")
public class Account extends Model<Account> {

    private static final long serialVersionUID = 1L;

	private String id;
	@TableField("qb_account")
	private String qbAccount;
	private String pass;
	private String roleid;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQbAccount() {
		return qbAccount;
	}

	public void setQbAccount(String qbAccount) {
		this.qbAccount = qbAccount;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
