package io.github.orionlibs.orion_iot;

import io.github.orionlibs.core.data.source.database.Database;
import io.github.orionlibs.core.data.source.database.DatabaseDAO;
import io.github.orionlibs.core.data.source.database.DatabaseQueriesDAO;
import io.github.orionlibs.core.data.source.database.DatabaseStaticDAO;
import io.github.orionlibs.core.data.source.database.DatabaseUpdatesDAO;
import io.github.orionlibs.orion_iot.config.ConfigurationService;
import io.github.orionlibs.orion_iot.database.IoTDatabase;
import io.github.orionlibs.orion_iot.database.RealDatabaseConfigurator;
import io.github.orionlibs.orion_iot.database.TestingDatabaseConfigurator;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class Setup
{
    private static boolean moduleInitialised;
    private static BasicDataSource dataSource;


    public static void setup()
    {
        if(!moduleInitialised)
        {
            loadActiveProfile();
            ConfigurationService.initialise();
            dataSource = setupDataSource();
            DatabaseDAO bean = new DatabaseDAO();
            bean.setDataSource(dataSource);
            bean.setJDBC(new JdbcTemplate(dataSource));
            bean.setTransactionalJDBC(new TransactionTemplate(new JdbcTransactionManager(dataSource)));
            bean.setDatabaseUpdatesDAO(new DatabaseUpdatesDAO());
            bean.setDatabaseQueriesDAO(new DatabaseQueriesDAO());
            bean.setDatabaseStaticDAO(new DatabaseStaticDAO());
            Database.connection = bean;
            Runtime.getRuntime().addShutdownHook(new Thread(Setup::shutdown));
            moduleInitialised = true;
        }
    }


    private static void loadActiveProfile()
    {
        if(OrionDomain.testing.equals(ConfigurationService.getProp("active.execution.profile")))
        {
            OrionDomain.domainName = OrionDomain.testing;
        }
        else
        {
            OrionDomain.domainName = OrionDomain.production;
        }
    }


    public static BasicDataSource setupDataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        if(OrionDomain.testing.equals(OrionDomain.domainName))
        {
            return new TestingDatabaseConfigurator(dataSource).configure();
        }
        else
        {
            return new RealDatabaseConfigurator(dataSource).configure();
        }
    }


    private static void shutdown()
    {
        if(dataSource != null)
        {
            try
            {
                dataSource.close();
            }
            catch(SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}