package com.vtb.test;

import com.vtb.test.dataconfig.DataSourceConfig;
import com.vtb.test.service.TestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.*;


@SpringBootTest(classes = ApplicationTests.class)
@RunWith(SpringRunner.class)
class ApplicationTests {

    private Connection connection;
    private TestService service;

    @BeforeEach
    void contextLoads() throws SQLException{
        service = new TestService();
        DataSource dataSource = new DataSourceConfig().getDataSource();

        String query =
                "CREATE ALIAS MYFUNCTION AS $$\n" +
                "String mrpc9000(String inv , String out) throws Exception {\n" +
                "    out = inv;" +
                "    return out;\n" +
                "}\n" +
                "$$;";

        connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    @org.junit.jupiter.api.Test
    void test() throws SQLException {
        service.setName("test");
        service.setInfo("someINFO");
        String out = "";

        CallableStatement callableStatement = connection.prepareCall("CALL MYFUNCTION(?, ?)");
        callableStatement.setString(1, service.toString());
        callableStatement.setString(2, out);
        ResultSet rs = callableStatement.executeQuery();
        rs.next();
        Assertions.assertEquals(rs.getString(1), "TestService{name='test', info='someINFO'}");
    }
}
