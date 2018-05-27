package xin.viny.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import xin.viny.bean.Shares;

/**
 * Servlet implementation class getSharesServlet
 */
@WebServlet(name = "getSharesInfo", urlPatterns = { "/getShares.do" })
public class GetSharesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSharesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String url = "http://hq.sinajs.cn/list=s_sh000001,s_sz399001,sz399006";
		String html = sendGet(url);
		
		try {
			//打成 JSON 格式
			String jsonstr = JSON.toJSONString(splitResult(html));
			
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonstr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		doGet(request, response);
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @return String[] 所代表远程资源的响应结果 以";" 进行划分，返回数组
	 */
	private static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("Content-Type", "application/javascript; charset=GBK");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			//...
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "GBK"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			result = "发送GET请求出现异常！";
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 把 splitResultS 和 splitResultC 整合为一个统一的方法
	 * @param result 通过 URL 获取的股票信息集合
	 * @return List<Shares> 集合
	 */
	private List<Shares> splitResult(String html) {
		String[] result = html.split(";");
		List<Shares> shares = new ArrayList<Shares>();
		//把请求返回的数组处理成 list<Shares> 集合
		if(result.length > 1) {
			for(String res:result) {
				//如果字符串的长度 <95 表明此结果为上证指数或深成指数，使用splitResultS处理 ， 否则为其余的股票，其余的股票则使用 splitResultC 处理 
				if(res.length() < 95) {
					shares.add(splitResultS(res));
				}else {
					shares.add(splitResultC(res));
				}
			}
		}else {
			shares.add(new Shares("系统异常","0","0","0","red"));
		}
		return shares;
	}
	
	/**
	 * 
	 * @param result 通过 URL 获取的股票信息
	 * @return Shares 对象
	 */
    private Shares splitResultS(String result) {
		String array[] = result.split("\"");
		String arr[] = array[1].split(",");
		for(int i = 1;i<arr.length;i++) {
			int len = arr[i].indexOf('.')+2;//小数点后两位
			if(len <= arr[i].length()) {
				arr[i] = arr[i].substring(0, len+1);//左闭右开，故需要 +1 
			}
		}
		char[] r = new char[1];
		arr[2].getChars(0, 1, r, 0);
		return new Shares(arr[0],arr[1],arr[2],arr[3],r[0]=='-'?"green":"red");
	}
	
	/**
	 * var_hq_str_sz399006="创业板指,1857.985,1855.809,1852.826,1865.020,1847.890,0.000,0.000,842241358,14431420280.140,0,0.000,0,0.000,0,0.000,0,0.000,0,0.000,0,0.000,0,0.000,0,0.000,0,0.000,0,0.000,2018-05-10,11:35:03,00";
	 * 0：”大秦铁路”，股票名字； 
	 * 1：”27.55″，今日开盘价；
	 *  2：”27.25″，昨日收盘价；
	 *   3：”26.91″，当前价格；
	 */
	private Shares splitResultC(String result) {
		String arr[] = result.split(",", 5);
		/**10689.076
		 * @Arr[3] 当前 当前
		 * @Arr[2] 昨收 涨跌=当前-昨收
		 * @Arr[1] 今日 率=涨跌/昨收
		 */
		String color = "red";
		float x = Float.parseFloat(arr[3]) - Float.parseFloat(arr[2]);
		if(x<0) {
			color = "green";
		}
		float r = x/Float.parseFloat(arr[2])*100;
		DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		return  new Shares("创业板指",arr[3],decimalFormat.format(x),decimalFormat.format(r),color);//format 返回的是字符串
	}

	
}
