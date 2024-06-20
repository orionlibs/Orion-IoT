package io.github.orionlibs.orion_iot;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.core.env.Environment;

public abstract class DatabaseConfigurator
{
    protected BasicDataSource dataSource;
    protected Environment env;


    public DatabaseConfigurator(Environment env, BasicDataSource dataSource)
    {
        this.env = env;
        this.dataSource = dataSource;
    }


    public abstract DataSource configure();
}