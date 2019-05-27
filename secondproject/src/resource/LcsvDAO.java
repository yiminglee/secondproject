package resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LcsvDAO implements ILcsvDAO {
	private static final String INSERT_STMT = "INSERT INTO Lcsv VALUES (?, ?, ?, ?, ?, ?,? )";
	private static final String UPDATE_STMT = "UPDATE Lcsv SET  cmdname=?, indcat=?, addr=?, chairname=?, income=?, createdate=? WHERE cmdid=?";
	private static final String DELETE_STMT = "DELETE FROM Lcsv WHERE cmdid=?";
	private static final String GET_ONE_STMT = "SELECT cmdid, cmdname, indcat, addr, chairname, income, createdate FROM Lcsv WHERE cmdid=?";
	private static final String GET_ALL_STMT = "SELECT cmdid, cmdname, indcat, addr, chairname, income, createdate FROM Lcsv ORDER BY cmdid";
	DataSource ds = null;
	Connection conn=null;

	@Override
	public void getConnection() throws SQLException {
		conn=ds.getConnection();
	}

	public LcsvDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(Lcsvbean lcsv) throws SQLException {
		int updateCount = 0;
		PreparedStatement pstmt = conn.prepareStatement(INSERT_STMT);
		pstmt.setInt(1, lcsv.getCmdid());
		pstmt.setString(2, lcsv.getCmdname());
		pstmt.setString(3, lcsv.getIndcat());
		pstmt.setString(4, lcsv.getAddr());
		pstmt.setString(5, lcsv.getChairname());
		pstmt.setLong(6, lcsv.getIncome());
		pstmt.setDate(7, lcsv.getCreatedate());
		updateCount = pstmt.executeUpdate();
		return updateCount;
	}

	@Override
	public int update(Lcsvbean lcsv) throws SQLException {
		int updateCount = 0;
		PreparedStatement pstmt = conn.prepareStatement(UPDATE_STMT);
		pstmt.setString(1, lcsv.getCmdname());
		pstmt.setString(2, lcsv.getIndcat());
		pstmt.setString(3, lcsv.getAddr());
		pstmt.setString(4, lcsv.getChairname());
		pstmt.setLong(5, lcsv.getIncome());
		pstmt.setDate(6, lcsv.getCreatedate());
		pstmt.setInt(7, lcsv.getCmdid());
		updateCount = pstmt.executeUpdate();
		return updateCount;
	}

	@Override
	public int delete(int cmdid) throws SQLException {
		int updateCount = 0;
		PreparedStatement pstmt = conn.prepareStatement(DELETE_STMT);
		pstmt.setInt(1, cmdid);
		updateCount = pstmt.executeUpdate();
		return updateCount;
	}

	@Override
	public Lcsvbean findByEmpno(int cmdid) throws SQLException {
		Lcsvbean lcsv = null;
		PreparedStatement pstmt = conn.prepareStatement(GET_ONE_STMT);
		pstmt.setInt(1, cmdid);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			lcsv = new Lcsvbean();
			lcsv.setCmdid(rs.getInt("cmdid"));
			lcsv.setCmdname(rs.getString("cmdname"));
			lcsv.setIndcat(rs.getString("indcat"));
			lcsv.setAddr(rs.getString("addr"));
			lcsv.setChairname(rs.getString("chairname"));
			lcsv.setIncome(rs.getLong("income"));
			lcsv.setCreatedate(rs.getDate("createdate"));
		}
		return lcsv;
	}

	@Override
	public List<Lcsvbean> getAll() throws SQLException {
		Lcsvbean lcsv=null;
		List<Lcsvbean> lcsvs = new ArrayList<Lcsvbean>();
		PreparedStatement pstmt = conn.prepareStatement(GET_ALL_STMT);
		ResultSet rs = pstmt.executeQuery();
			
		while (rs.next()) {
			lcsv = new Lcsvbean();
			lcsv.setCmdid(rs.getInt("cmdid"));
			lcsv.setCmdname(rs.getString("cmdname"));
			lcsv.setIndcat(rs.getString("indcat"));
			lcsv.setAddr(rs.getString("addr"));
			lcsv.setChairname(rs.getString("chairname"));
			lcsv.setIncome(rs.getLong("income"));
			lcsv.setCreatedate(rs.getDate("createdate"));
			lcsvs.add(lcsv);
		}
		return lcsvs;		
	}

	@Override
	public void closeConn() throws SQLException {
		if (conn != null)
			conn.close();
	}

}
