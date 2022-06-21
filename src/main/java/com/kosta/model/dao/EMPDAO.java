package com.kosta.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kosta.model.dto.*;
import com.kosta.model.util.*;

//CRUD�۾� INSERT, SELECT, UPDATE, DELETE -> DAO ; DATA ACCESS OBJECT
@Repository
public class EMPDAO {
	static final String SQL_SELECT_ALL = "select * from employees order by 1";
	static final String SQL_SELECT_BYDEPT = "select * from employees where department_id = ? order by 1";
	static final String SQL_SELECT_BYMANAGER = "select * from employees where manager_id = ? order by 1";
	static final String SQL_SELECT_JOB = "select * from employees where job_id = ? order by 1";
	static final String SQL_SELECT_EMAIL = "select * from employees where email = ? order by 1";
	static final String SQL_SELECT_CONDITION = "select * from employees "
												+ "where department_id = ? "
												+ "AND job_id = ? "
												+ "AND salary >= ? "
												+ "AND hire_date >= ? "
												+ "order by 1";
	static final String SQL_SELECT_BYID = "select * from employees where employee_id = ?";
	static final String SQL_INSERT = "INSERT INTO EMPLOYEES VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	static final String SQL_UPDATE = "UPDATE EMPLOYEES SET"
			+ "	FIRST_NAME=?,"
			+ "	LAST_NAME=?,"
			+ "	EMAIL=?,"
			+ "	PHONE_NUMBER=?,"
			+ "	HIRE_DATE=?,"
			+ "	JOB_ID=?,"
			+ "	SALARY=?,"
			+ "	COMMISSION_PCT=?,"
			//+ "	MANAGER_ID=?,"
			+ "	MANAGER_ID=decode(?,0,null,?),"
			+ "	DEPARTMENT_ID=?"
			+ "	WHERE EMPLOYEE_ID =?";
			//"update employees set first_name = ?, salary =? , job_id = ? where  employee_id =?";
	static final String SQL_UPDATEBYDEPT = "update employees set salary =? , COMMISSION_PCT = ? where  department_id =?";
	static final String SQL_DELETE = "delete from employees where employee_id = ?";
	static final String SQL_DELETE_BYDEPT = "delete from employees where department_id = ?";

	
	Connection conn;
	Statement st;
	PreparedStatement pst; //���ε��������� [?]
	ResultSet rs;
	int result;
	//1.���������ȸ
	public List<EMPVO> selectAll() {
		List<EMPVO> emplist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(SQL_SELECT_ALL);
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emplist;
		
	}
	public List<JobVO> selectJobAll() {
		List<JobVO> joblist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select * from jobs order by 1");
			while(rs.next()) {
				JobVO job = new JobVO(rs.getString(1), rs.getString(2),rs.getInt(3),rs.getInt(4)); 
				joblist.add(job);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return joblist;
		
	}
	public Map<Integer, String> selectManagerAll() {
		Map<Integer, String> managerMap = new HashMap<>();
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery("select employee_id, first_name from employees where employee_id in (select distinct manager_id from employees)");
			while(rs.next()) {
				
				managerMap.put(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return managerMap;
		
	}
	private EMPVO makeEmp(ResultSet rs) throws SQLException {
		EMPVO emp = new EMPVO();
		emp.setCommission_pct(rs.getDouble("commission_pct"));
		emp.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
		emp.setEmail(rs.getString("email"));
		emp.setEmployee_id(rs.getInt("employee_id"));
		emp.setFirst_name(rs.getString("first_name"));
		emp.setHire_date(rs.getDate("hire_date"));
		emp.setJob_id(rs.getString("job_id"));
		emp.setLast_name(rs.getString("last_name"));
		emp.setManager_id(rs.getInt("manager_id"));
		emp.setPhone_number(rs.getString("phone_number"));
		emp.setSalary(rs.getDouble("salary"));
		return emp;
	}
	//2.������ȸ(Ư���μ�)
	public List<EMPVO> selectByDept(int deptid) {
		List<EMPVO> emplist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BYDEPT); //sql���� �̸� �غ�
			pst.setInt(1, deptid); //ù��° ����ǥ�� deptid�� �ִ´�
			rs = pst.executeQuery(); //������ �Ʊ� �־��༭ �� ���� �ʿ����
			
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emplist;
	}
	//3.������ȸ(Ư���Ŵ���)
	public List<EMPVO> selectByManager(int mid) {
		List<EMPVO> emplist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BYMANAGER); //sql���� �̸� �غ�
			pst.setInt(1, mid); //ù��° ����ǥ�� mid�� �ִ´�
			rs = pst.executeQuery(); //������ �Ʊ� �־��༭ �� ���� �ʿ����
			
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emplist;
		
	}
	//4.������ȸ(Ư��JOB)
	public List<EMPVO> selectByJob(String job_id) {
		List<EMPVO> emplist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_JOB); //sql���� �̸� �غ�
			pst.setString(1, job_id); //ù��° ����ǥ�� job_id�� �ִ´�
			rs = pst.executeQuery(); //������ �Ʊ� �־��༭ �� ���� �ʿ����
			
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return emplist;
		
	}
	//5.������ȸ(Ư�� deptid, jobid, salary, hire_date)
	public List<EMPVO> selectByCondition(int deptid, String job_id, double sal, String hire_date) {
		List<EMPVO> emplist = new ArrayList<>();
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_CONDITION); //sql���� �̸� �غ�
			pst.setInt(1, deptid); //ù��° ����ǥ�� deptid�� �ִ´�
			pst.setString(2, job_id); //�ι�° ����ǥ�� job_id�� �ִ´�
			pst.setDouble(3, sal); //����° ����ǥ�� sal�� �ִ´�
			pst.setString(4, hire_date); //�׹�° ����ǥ�� hire_date�� �ִ´�
			rs = pst.executeQuery(); //������ �Ʊ� �־��༭ �� ���� �ʿ����
			
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emplist;
		
	}
	//6.Ư������ �ѰǸ� ��ȸ
	public EMPVO selectById(int empid) {
		EMPVO emp = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_BYID); //sql���� �̸� �غ�
			pst.setInt(1, empid); //ù��° ����ǥ�� empid�� �ִ´�
			rs = pst.executeQuery(); //������ �Ʊ� �־��༭ �� ���� �ʿ����
			
			while(rs.next()) {
				emp= makeEmp(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emp;
		
	}
	public EMPVO selectByEmail(String empEmail) {
		EMPVO emp = null;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_SELECT_EMAIL); //sql���� �̸� �غ�
			pst.setString(1, empEmail); //ù��° ����ǥ�� empid�� �ִ´�
			rs = pst.executeQuery(); //������ �Ʊ� �־��༭ �� ���� �ʿ����
			
			while(rs.next()) {
				emp= makeEmp(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return emp;
		
	}
	//7.insert
	public int empInsert(EMPVO emp) {
		int result = 0 ;
		
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_INSERT); //sql���� �̸� �غ�
			pst.setInt(1, emp.getEmployee_id()); 
			pst.setString(2, emp.getFirst_name()); 
			pst.setString(3, emp.getLast_name()); 
			pst.setString(4, emp.getEmail()); 			
			pst.setString(5, emp.getPhone_number()); 			
			pst.setDate(6, emp.getHire_date()); 
			pst.setString(7, emp.getJob_id()); 
			pst.setDouble(8, emp.getSalary()); 
			pst.setDouble(9, emp.getCommission_pct()); 
			pst.setInt(10, emp.getManager_id()); 
			pst.setInt(11, emp.getDepartment_id()); 
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	//8.update(Ư������1�� ����)
	public int empUpdate(EMPVO emp) {
		int result = 0 ;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_UPDATE); //sql���� �̸� �غ�
			pst.setString(1, emp.getFirst_name()); 
			pst.setString(2, emp.getLast_name()); 
			pst.setString(3, emp.getEmail()); 			
			pst.setString(4, emp.getPhone_number()); 			
			pst.setDate(5, emp.getHire_date()); 
			pst.setString(6, emp.getJob_id()); 
			pst.setDouble(7, emp.getSalary()); 
			pst.setDouble(8, emp.getCommission_pct()); 
			pst.setInt(9, emp.getManager_id()); 
			pst.setInt(10, emp.getManager_id()); 
			pst.setInt(11, emp.getDepartment_id()); 
			pst.setInt(12, emp.getEmployee_id()); 
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	//9.update(���� department_id�� �ش��ϴ� ������ ����)
	public int empUpdateByDept(EMPVO emp, int deptid) {
		int result = 0 ;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_UPDATEBYDEPT); //sql���� �̸� �غ�
			pst.setInt(3, deptid); 
			pst.setDouble(2, emp.getCommission_pct()); 
			pst.setDouble(1, emp.getSalary()); 
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	//10.delete(Ư������ 1�� ����)
	public int empDelete(int empid) {
		int result = 0 ;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_DELETE); //sql���� �̸� �غ�
	
			pst.setInt(1, empid); 
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
	//11.delete(���� department_id�� �ش��ϴ� ������ ����)
	public int empDeleteByDept(int deptid) {
		int result = 0 ;
		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(SQL_DELETE_BYDEPT); //sql���� �̸� �غ�
	
			pst.setInt(1, deptid); 
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return result;
	}
}
