package resource;
import java.sql.SQLException;
import java.util.*;

public interface  ILcsvDAO {
	public void getConnection() throws SQLException;
	public int insert(Lcsvbean lcsv) throws SQLException;
	public int update(Lcsvbean lcsv) throws SQLException;
	public int delete(int cmdid) throws SQLException;
	public Lcsvbean findByEmpno(int cmdid) throws SQLException;
	public List<Lcsvbean> getAll() throws SQLException;
	public void closeConn() throws SQLException;
} 