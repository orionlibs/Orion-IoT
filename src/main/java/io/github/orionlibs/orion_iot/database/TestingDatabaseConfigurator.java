package io.github.orionlibs.orion_iot.database;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import io.github.orionlibs.orion_iot.OrionDomain;
import io.github.orionlibs.orion_iot.config.ConfigurationService;
import org.apache.commons.dbcp2.BasicDataSource;

public class TestingDatabaseConfigurator extends DatabaseConfigurator
{
    public TestingDatabaseConfigurator(BasicDataSource dataSource)
    {
        super(dataSource);
    }


    @Override
    public BasicDataSource configure()
    {
        DBConfigurationBuilder config = DBConfigurationBuilder.newBuilder();
        config.setPort(3307);
        config.setSecurityDisabled(true);
        try
        {
            DB db = DB.newEmbeddedDB(config.build());
            db.start();
            db.createDB("test_database");
            dataSource.setUrl(config.getURL("test_database") + "?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&elideSetAutoCommits=true&allowPublicKeyRetrieval=true&autoReconnect=true&allowMultiQueries=true");
            dataSource.setUsername(ConfigurationService.getProp("database." + OrionDomain.domainName + ".username"));
            dataSource.setPassword(ConfigurationService.getProp("database." + OrionDomain.domainName + ".password"));
            dataSource.setMinIdle(Integer.parseInt(ConfigurationService.getProp("database." + OrionDomain.domainName + ".connection.pool.minimum.number.of.idle.connections")));
            dataSource.setMaxIdle(Integer.parseInt(ConfigurationService.getProp("database." + OrionDomain.domainName + ".connection.pool.maximum.number.of.idle.connections")));
            dataSource.setMaxOpenPreparedStatements(Integer.parseInt(ConfigurationService.getProp("database." + OrionDomain.domainName + ".maximum.open.prepared.statements.in.pool")));
            //dataSource.setDriverClassName(ConfigurationService.getProp("database." + OrionDomain.domainName + ".jdbc.driver.class.name"));
            dataSource.setMaxTotal(Integer.parseInt(ConfigurationService.getProp("database." + OrionDomain.domainName + ".connection.pool.size")));
            dataSource.setPoolPreparedStatements(Boolean.parseBoolean(ConfigurationService.getProp("database." + OrionDomain.domainName + ".connection.pool.prepared.statements")));
            dataSource.setMaxConnLifetimeMillis(Integer.parseInt(ConfigurationService.getProp("database." + OrionDomain.domainName + ".connection.lifetime.in.milliseconds")));
            dataSource.setTestOnBorrow(Boolean.parseBoolean(ConfigurationService.getProp("database." + OrionDomain.domainName + ".test.connection.on.connection.object.borrow")));
            dataSource.setTestOnCreate(Boolean.parseBoolean(ConfigurationService.getProp("database." + OrionDomain.domainName + ".test.connection.on.connection.object.create")));
            dataSource.setTestOnReturn(Boolean.parseBoolean(ConfigurationService.getProp("database." + OrionDomain.domainName + ".test.connection.on.connection.object.return")));
            dataSource.setTestWhileIdle(Boolean.parseBoolean(ConfigurationService.getProp("database." + OrionDomain.domainName + ".test.connection.while.idle")));
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