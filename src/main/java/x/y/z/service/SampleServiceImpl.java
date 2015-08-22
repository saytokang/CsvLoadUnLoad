package x.y.z.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import x.y.z.dao.SampleDAO;

@Service("sampleService")
public class SampleServiceImpl implements SampleService{

	@Autowired
	private SampleDAO sampleDAO;
	
	@Override
	public String getToday() {

		return sampleDAO.getToday();
	}

	@Override
	public boolean saveTowTables(Map<String, Object> params) {

		return sampleDAO.saveTowTables(params);
	}

	@Override
	public List<Map<String, Object>> getSampleDBList() {
		return sampleDAO.getSampleDBList();
	}
	
}
