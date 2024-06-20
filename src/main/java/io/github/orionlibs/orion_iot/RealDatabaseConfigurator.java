package io.github.orionlibs.orion_iot;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.core.env.Environment;

public class RealDatabaseConfigurator extends DatabaseConfigurator
{
    public RealDatabaseConfigurator(Environment env, BasicDataSource dataSource)
    {
        super(env, dataSource);
    }


    @Override
    public DataSource configure()
    {
        /*dataSource.setUrl(env.getRequiredProperty("database." + Orion.systemProfileMode + ".url"));
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
        return dataSource;
    }
}