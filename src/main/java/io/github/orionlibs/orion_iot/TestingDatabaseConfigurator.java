package io.github.orionlibs.orion_iot;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.core.env.Environment;

public class TestingDatabaseConfigurator extends DatabaseConfigurator
{
    public TestingDatabaseConfigurator(Environment env, BasicDataSource dataSource)
    {
        super(env, dataSource);
    }


    @Override
    public DataSource configure()
    {
        DBConfigurationBuilder config = DBConfigurationBuilder.newBuilder();
        config.setPort(3307);
        config.setSecurityDisabled(true);

        try
        {
            DB db = DB.newEmbeddedDB(config.build());
            db.start();
            db.createDB("test_database");
            /*dataSource.setUrl(config.getURL("test_database") + "?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&elideSetAutoCommits=true&allowPublicKeyRetrieval=true&autoReconnect=true&allowMultiQueries=true");
            dataSource.setUsername(env.getRequiredProperty("database." + Orion.systemProfileMode + ".username"));
            dataSource.setPassword(env.getRequiredProperty("database." + Orion.systemProfileMode + ".password"));
            dataSource.setMinIdle(Integer.parseInt(env.getRequiredProperty("database." + Orion.systemProfileMode + ".connection.pool.minimum.number.of.idle.connections")));
            dataSource.setMaxIdle(Integer.parseInt(env.getRequiredProperty("database." + Orion.systemProfileMode + ".connection.pool.maximum.number.of.idle.connections")));
            dataSource.setMaxOpenPreparedStatements(Integer.parseInt(env.getRequiredProperty("database." + Orion.systemProfileMode + ".maximum.open.prepared.statements.in.pool")));
            dataSource.setDriverClassName(env.getRequiredProperty("database." + Orion.systemProfileMode + ".jdbc.driver.class.name"));
            dataSource.setMaxTotal(Integer.parseInt(env.getRequiredProperty("database." + Orion.systemProfileMode + ".connection.pool.size")));
            dataSource.setPoolPreparedStatements(Boolean.parseBoolean(env.getRequiredProperty("database." + Orion.systemProfileMode + ".connection.pool.prepared.statements")));
            dataSource.setMaxConnLifetimeMillis(Integer.parseInt(env.getRequiredProperty("database." + Orion.systemProfileMode + ".connection.lifetime.in.milliseconds")));
            dataSource.setTestOnBorrow(Boolean.parseBoolean(env.getRequiredProperty("database." + Orion.systemProfileMode + ".test.connection.on.connection.object.borrow")));
            dataSource.setTestOnCreate(Boolean.parseBoolean(env.getRequiredProperty("database." + Orion.systemProfileMode + ".test.connection.on.connection.object.create")));
            dataSource.setTestOnReturn(Boolean.parseBoolean(env.getRequiredProperty("database." + Orion.systemProfileMode + ".test.connection.on.connection.object.return")));
            dataSource.setTestWhileIdle(Boolean.parseBoolean(env.getRequiredProperty("database." + Orion.systemProfileMode + ".test.connection.while.idle")));*/
            //dataSource.setConnectionProperties("connectTimeout=5;socketTimeout=5");
        }
        catch(ManagedProcessException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return dataSource;
    }
}