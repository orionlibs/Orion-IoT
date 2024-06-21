package io.github.orionlibs.orion_iot.database;

import io.github.orionlibs.orion_iot.OrionDomain;
import io.github.orionlibs.orion_iot.config.ConfigurationService;
import org.apache.commons.dbcp2.BasicDataSource;

public class RealDatabaseConfigurator extends DatabaseConfigurator
{
    public RealDatabaseConfigurator(BasicDataSource dataSource)
    {
        super(dataSource);
    }


    @Override
    public BasicDataSource configure()
    {
        dataSource.setUrl(ConfigurationService.getProp("database." + OrionDomain.domainName + ".url"));
        dataSource.setUsername(ConfigurationService.getProp("database." + OrionDomain.domainName + ".username"));
        dataSource.setPassword(ConfigurationService.getProp("database." + OrionDomain.domainName + ".password"));
        dataSource.setMinIdle(Integer.parseInt(ConfigurationService.getProp("database." + OrionDomain.domainName + ".connection.pool.minimum.number.of.idle.connections")));
        dataSource.setMaxIdle(Integer.parseInt(ConfigurationService.getProp("database." + OrionDomain.domainName + ".connection.pool.maximum.number.of.idle.connections")));
        dataSource.setMaxOpenPreparedStatements(Integer.parseInt(ConfigurationService.getProp("database." + OrionDomain.domainName + ".maximum.open.prepared.statements.in.pool")));
        dataSource.setDriverClassName(ConfigurationService.getProp("database." + OrionDomain.domainName + ".jdbc.driver.class.name"));
        dataSource.setMaxTotal(Integer.parseInt(ConfigurationService.getProp("database." + OrionDomain.domainName + ".connection.pool.size")));
        dataSource.setPoolPreparedStatements(Boolean.parseBoolean(ConfigurationService.getProp("database." + OrionDomain.domainName + ".connection.pool.prepared.statements")));
        dataSource.setMaxConnLifetimeMillis(Integer.parseInt(ConfigurationService.getProp("database." + OrionDomain.domainName + ".connection.lifetime.in.milliseconds")));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(ConfigurationService.getProp("database." + OrionDomain.domainName + ".test.connection.on.connection.object.borrow")));
        dataSource.setTestOnCreate(Boolean.parseBoolean(ConfigurationService.getProp("database." + OrionDomain.domainName + ".test.connection.on.connection.object.create")));
        dataSource.setTestOnReturn(Boolean.parseBoolean(ConfigurationService.getProp("database." + OrionDomain.domainName + ".test.connection.on.connection.object.return")));
        dataSource.setTestWhileIdle(Boolean.parseBoolean(ConfigurationService.getProp("database." + OrionDomain.domainName + ".test.connection.while.idle")));
        //dataSource.setConnectionProperties("connectTimeout=5;socketTimeout=5");
        return dataSource;
    }
}