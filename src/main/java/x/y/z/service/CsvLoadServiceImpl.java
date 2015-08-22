package x.y.z.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import x.y.z.dao.CsvLoadDAO;

public class CsvLoadServiceImpl implements CsvLoadService{
	
	@Autowired
	CsvLoadDAO csvLoadDAO;
	
	@Override
	public boolean saveToDB(List<String> lines) {
		return csvLoadDAO.saveToDB(lines);
	}

}
