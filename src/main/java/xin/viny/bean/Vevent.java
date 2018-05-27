package xin.viny.bean;

import java.sql.Date;
import java.sql.Time;

public class Vevent {
	private int id;
	private String info;
	private Date vdate;
	private Time vtime;
	private int priority;
	private byte is_done;
	private int u_id;
	private int label_id;

	/**
	 * 
	 */
	public Vevent() {
		super();
	}

	/**
	 * @param id
	 * @param info
	 * @param vdate
	 * @param vtime
	 * @param priority
	 * @param is_done
	 * @param u_id
	 * @param label_id
	 */
	public Vevent(int id, String info, Date vdate, Time vtime, int priority, byte is_done, int u_id, int label_id) {
		super();
		this.id = id;
		this.info = info;
		this.vdate = vdate;
		this.vtime = vtime;
		this.priority = priority;
		this.is_done = is_done;
		this.u_id = u_id;
		this.label_id = label_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getVdate() {
		return vdate;
	}

	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}

	public Time getVtime() {
		return vtime;
	}

	public void setVtime(Time vtime) {
		this.vtime = vtime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public byte getIs_done() {
		return is_done;
	}

	public void setIs_done(byte is_done) {
		this.is_done = is_done;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getLabel_id() {
		return label_id;
	}

	public void setLabel_id(int label_id) {
		this.label_id = label_id;
	}

}
