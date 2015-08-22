package x.y.z.service;

import java.util.List;
import java.util.Map;

public interface SampleService {

	public String getToday();

	public boolean saveTowTables(Map<String, Object> params);

	public List<Map<String, Object>> getSampleDBList();
}
