package x.y.z.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SampleDAO {
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	public String getToday() {
		
		return sqlSession.selectOne("sample.getToday");
	}

	public boolean saveTowTables(Map<String, Object> params) {
		Integer rs1 = sqlSession.insert("sample.insertData1", params);
		Integer rs2 = sqlSession.insert("sample.insertData2", params);
		
		return rs1 > 0 && rs2 > 0;
	}

	public List<Map<String, Object>> getSampleDBList() {
		
		return sqlSession.selectList("sample.getSampleDBList");
	}
	
}
