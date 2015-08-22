package x.y.z.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.stereotype.Repository;


@Repository
public class CsvLoadDAO {
	
	@Resource(name="sqlSessionFactory")
	private SqlSessionFactory sqlSessionFactory;
	
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	
	/**
	 *  mybatis batch 처리 방식 성능이 그닦 좋지 않음.
	 *  500건에 15초 소요 
	 * @param lines
	 * @return
	 */
	public boolean saveToDB(List<String> lines) {
		SqlSession sqlBatchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false); // auto commit false
		List<String> bindingParams = null;
		int COL_SIZE = 6;
		long beginTime = System.currentTimeMillis();
		
		try {
			for (int j = 0; j < 10000; j++) {
				bindingParams = getBindingParams(lines, COL_SIZE, j);
				sqlBatchSession.insert("sample.batchInsert", bindingParams);
				
				if(j > 0 && j%500 == 0) { 
					sqlBatchSession.commit();
					sqlBatchSession.clearCache();
					System.out.print("inserted : " + j);
					System.out.println("\t elaspse time :" + (System.currentTimeMillis() - beginTime)/1000);
					beginTime = System.currentTimeMillis();
				}
			}
			
			sqlBatchSession.commit();
			sqlBatchSession.clearCache();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("total record :" +lines.size());
		System.out.println("finished elaspse time :" + (System.currentTimeMillis() - beginTime)/1000);
		return true;
	}


	private List<String> getBindingParams(List<String> lines, int COL_SIZE,
			int j) {
		List<String> data;
		String[] tokens = lines.get(j).split(",");
		data = Arrays.asList(tokens);
		
		if(tokens.length != COL_SIZE) {
			String[] template = new String[COL_SIZE];
			System.arraycopy(tokens, 0, template, 0, tokens.length);
			data = Arrays.asList(template);
		}
		return data;
	}
	
	/**
	 * mybatis batch 보다 빠른 성능을 보임.
	 * 500건에 7초 소
	 * @param lines
	 * @return
	 */
	public boolean saveToDB2(List<String> lines) {
		Connection dbcon = SqlSessionUtils.getSqlSession(
				sqlSession.getSqlSessionFactory(), sqlSession.getExecutorType(),
				sqlSession.getPersistenceExceptionTranslator()).getConnection();
		
		String sql = "insert into tmp1 values (?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		int COL_SIZE = 6;
		String[] template = new String[COL_SIZE];
		long beginTime = System.currentTimeMillis();
		try{
			dbcon.setAutoCommit(false);
			pstmt = dbcon.prepareStatement(sql);
			
			String[] tokens = null;
			int idx = 0;
			for (int i = 0; i < lines.size(); i++) {
				
				tokens = lines.get(i).split(",");
				template = new String[COL_SIZE];
				System.arraycopy(tokens, 0, template, 0, tokens.length);
				idx = 1;
				for (int j = 0; j < template.length; j++) {
					pstmt.setString(idx++, template[j]);
				}
				pstmt.addBatch();
				
				if(i > 0 && i%500 == 0) {
					int[] rs = pstmt.executeBatch();
					System.out.println("inserted rows :" + rs.length);
					System.out.println("elasped time:" + (System.currentTimeMillis()- beginTime)/1000);
					beginTime = System.currentTimeMillis();
				}
			}
			pstmt.executeBatch();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbcon.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	/**
	 * load data local 방식이 가장 빠른 처리를 보여주고 있음.
	 * 5만건에 2초 소
	 * @param path
	 * @return
	 */
	public boolean saveToDB3(String path) {
		long beginTime = System.currentTimeMillis();
		sqlSession.insert("sample.load_ublk", path);
		System.out.println("eclpsed time : " + (System.currentTimeMillis() - beginTime)/1000);
		return true;
	}
	
}
