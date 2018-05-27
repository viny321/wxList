package xin.viny.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import xin.viny.bean.Vevent;
import xin.viny.service.EventService;

/**
 * Servlet implementation class GetVeventServlet
 */
@WebServlet("/getEvent.do")
public class GetVeventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EventService eventService = new EventService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetVeventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Vevent event = eventService.getEvent(2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
		System.out.println(sdf.format(event.getVdate())+"::"+stf.format(event.getVtime()));
		
		List<Vevent> list = new ArrayList<>(2);
		list.add(event);
		String jsonstr = JSON.toJSONString(list);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonstr);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
