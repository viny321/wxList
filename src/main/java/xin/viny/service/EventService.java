package xin.viny.service;

import xin.viny.bean.Vevent;
import xin.viny.dao.impl.VeventDaoImpl;

public class EventService {

	private VeventDaoImpl eventDao = new VeventDaoImpl();
	
	public Vevent getEvent(int id) {
		return eventDao.selectVeventById(id);
	}
}
