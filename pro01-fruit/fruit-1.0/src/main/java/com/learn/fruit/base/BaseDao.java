package com.learn.fruit.base;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BaseDao<T> {
    private Class<T> entityClass ;

    protected Connection conn ;
    protected PreparedStatement psmt ;
    protected ResultSet rs ;

    String DRIVER ;
    String URL ;
    String UNAME ;
    String PWD ;

    //加载jdbc.properties文件
    private void loadProperties(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);

            DRIVER = properties.getProperty ("jdbc.driver-class-name");
            URL = properties.getProperty("jdbc.url");
            UNAME = properties.getProperty("jdbc.username");
            PWD = properties.getProperty("jdbc.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setEntityClass(){
        //1. getClass() 调用的是FruitDaoImpl中的getClass()
        //因为，将来我们是 new FruitDaoImpl()
        //那么子类的构造方法中首先是调用父类的无参构造方法，因此BaseDao的无参构造方法被调用
        //此时this.getClass()中的this指的是当前对象。而当前new的是FruitDaoImpl。因此this指的是FruitDaoImpl

        //2.getGenericSuperclass(); 获取带有泛型的父类  -> BaseDao<Fruit>
        Type type = this.getClass().getGenericSuperclass();

        //3.获取泛型父类中的泛型
        ParameterizedType parameterizedType =(ParameterizedType)type;
        //4.获取实际传入的泛型类型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if(actualTypeArguments!=null && actualTypeArguments.length>0){
            Type actualTypeArgument = actualTypeArguments[0];
            String className = actualTypeArgument.getTypeName();
            try {
                entityClass = (Class<T>) Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            //Throwable : Error , Exception
            //Exception : RuntimeException -> NullPointerException
            throw new RuntimeException("T是不是忘记指定了！");
        }
    }

    public BaseDao(){
        loadProperties();
        setEntityClass();
    }

    protected Connection getConn() throws SQLException {
        /*
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL,UNAME,PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(UNAME);
        dataSource.setPassword(PWD);

        dataSource.setMinIdle(5);
        dataSource.setMaxActive(10);
        dataSource.setMaxWait(5000);

        return dataSource.getConnection() ;
    }

    protected void close(ResultSet rs , PreparedStatement psmt , Connection conn){
        try {
            if (rs != null) {
                rs.close();
                rs=null;
            }
            if(psmt!=null){
                psmt.close();
                psmt=null;
            }
            if(conn!=null && !conn.isClosed()){
                conn.close();
                conn=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //填充参数
    private void setParams(PreparedStatement psmt , Object... params) throws SQLException {
        if(params!=null && params.length>0){
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i+1,params[i]);
            }
        }
    }

    protected void executeUpdate(String sql , Object... params){
        try {
            conn = getConn() ;
            //3.编写SQL语句

            //4.创建预处理命令对象
            psmt = conn.prepareStatement(sql);
            //5.填充参数
            setParams(psmt,params);
            // 6.执行更新，返回影响行数
            int count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs,psmt,conn);
        }
    }

    protected List<T> executeQuery(String sql , Object... params){
        List<T> list = new ArrayList<>();
        try {
            conn = getConn() ;
            //4.创建预处理命令对象
            psmt = conn.prepareStatement(sql);
            setParams(psmt,params);
            //5.执行查询，返回结果集
            rs = psmt.executeQuery();
            //6.解析结果集
            while(rs.next()){
                //Fruit fruit = new Fruit();
                //T仅仅是一个代号，并不是实际的类名。因此不能new T()
                //T t = new T();
                T instance = (T)entityClass.newInstance();

                //rs.getObject(1)
                //从rs对象中也可以获取元数据
                //元数据：对数据集的结构进行说明的数据
                //数据集的结构：有多少列，列名是什么，每一列的数据类型是什么....
                ResultSetMetaData rsmd = rs.getMetaData();
                //获取结果集的列数
                int columnCount = rsmd.getColumnCount();
                for (int i = 1 ; i<= columnCount ; i++){
                    Object columnValue = rs.getObject(i);               //1     苹果
                    String columnLabel = rsmd.getColumnLabel(i);        //id    fname
                    //通过反射技术获取实例对象中的某一个属性
                    Field field = entityClass.getDeclaredField(columnLabel);
                    //通过反射技术给这个实例的这个属性赋值
                    if(field!=null){
                        field.setAccessible(true);
                        field.set(instance,columnValue);
                    }
                }
                list.add(instance);
            }
            return list;
        } catch (SQLException | IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            close(rs,psmt,conn);
        }

        return list;
    }

    protected T load(String sql , Object... params){
        List<T> list = executeQuery(sql,params);
        if(list==null || list.size()==0){
            return null ;
        }else{
            return list.get(0);
        }
    }
}
