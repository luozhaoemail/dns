package dns;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
public class ConnectionDB {  
   
    private static final String DRIVER = "com.mysql.jdbc.Driver";  
    private static final String URLSTR = "jdbc:mysql://localhost:3306/storm_test";   
    private static final String USERNAME = "root";    
    private static final String USERPASSWORD = "root"; 
    
    private Connection connnection = null;     
    private Statement statement = null;    
    private ResultSet resultSet = null;  
      
    static {  
        try {  
            // 加载数据库驱动程序  
            Class.forName(DRIVER);  
        } catch (ClassNotFoundException e) {  
            System.out.println("加载驱动错误");  
            System.out.println(e.getMessage());  
        }  
    }  
  
    //建立数据库连接    
    public Connection getConnection() {  
        try {  
          // 获取连接  
         connnection = DriverManager.getConnection(URLSTR, USERNAME,USERPASSWORD);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return connnection;  
    }  
  
    /** 
     * insert update delete 统一的方法      *  
     * @param sql 
     * insert,update,delete SQL 语句 
     * @return 受影响的行数 
     */  
    public int executeUpdate(String sql) {  
        int affectedLine = 0;  
        try {  
            // 获得连接  
            connnection = this.getConnection();  
            
            // 创建对象  
            statement = connnection.createStatement();  
            
            // 执行SQL语句  
            affectedLine = statement.executeUpdate(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            // 释放资源  
            closeAll();  
        }  
        return affectedLine;  
    }  
  
    /** 
     * 查询数据库方法     
     * @param sql sql语句          
     * @return 结果集 
     */  
    public ResultSet executeQueryRS(String sql) {  
        try {  
            // 获得连接  
            connnection = this.getConnection();  
  
            // 创建Statement对象  
            statement = connnection.createStatement();  
  
            // 执行SQL 语句  
            resultSet = statement.executeQuery(sql);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        return resultSet;  
    }  
      
    /** 
     * 获取结果集，并将结果放在List中 
     * @param sql SQL语句 
     * @return List结果集 
     */  
    public List<Object> excuteQuery(String sql) {   
        ResultSet rs = executeQueryRS(sql);  
        ResultSetMetaData rsmd = null;  
        int columnCount = 0;  
        try {  
            rsmd = rs.getMetaData();  
        } catch (SQLException e2) {  
            System.out.println(e2.getMessage());  
        }  
        try {  
            columnCount = rsmd.getColumnCount();  
        } catch (SQLException e1) {  
            System.out.println(e1.getMessage());  
        }  
          
        List<Object> list = new ArrayList<Object>();  
          
        try {  
            while(rs.next()) {  
                Map<String, Object> map = new HashMap<String, Object>();  
                for(int i = 1; i <= columnCount; i++) {  
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));                 
                }  
                list.add(map);  
            }  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } finally {  
            closeAll();  
        }  
          
        return list;  
    }  
      
  
    /** 
     * 释放所有资源 
     */  
    public void closeAll() {  
        // 释放结果集连接  
        if (resultSet != null) {  
            try {  
                resultSet.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
  
        // 释放声明连接  
        if (statement != null) {  
            try {  
                statement.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
  
        // 释放数据库连接  
        if (connnection != null) {  
            try {  
                connnection.close();  
            } catch (SQLException e) {  
                System.out.println(e.getMessage());  
            }  
        }  
    }  
}  