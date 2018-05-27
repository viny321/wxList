package xin.viny.bean;

public class Shares {

	private String name;
	private String trade;
	private String change;
	private String chg;
	private String arr;

	public Shares() {
		super();
	}

	/**
	 * @param name
	 *            股票名称
	 * @param trade
	 *            股价
	 * @param change
	 *            涨跌额
	 * @param chg
	 *            涨跌幅度
	 */
	public Shares(String name, String trade, String change, String chg, String arr) {
		super();
		this.name = name;
		this.trade = trade;
		this.change = change;
		this.chg = chg;
		this.arr = arr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getChg() {
		return chg;
	}

	public void setChg(String chg) {
		this.chg = chg;
	}

	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

}
