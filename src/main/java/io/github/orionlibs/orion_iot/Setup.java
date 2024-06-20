package io.github.orionlibs.orion_iot;

public class Setup
{
    private static boolean moduleInitialised;


    public Setup()
    {
        if(!moduleInitialised)
        {
            //loadDatabasesNames();
            //Database.geodataDatabaseName = env.getRequiredProperty("database.of.geodata.name");
            //OneliveryDatabase.businessesDatabaseName = env.getRequiredProperty("database.of.businesses.name");
            moduleInitialised = true;
        }
    }


    /*public DataSource dataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();

        if(OneliveryDomain.testing.equals(Orion.domainName))
        {
            return new TestingDatabaseConfigurator(env, dataSource).configure();
        }
        else
        {
            return new RealDatabaseConfigurator(env, dataSource).configure();
        }
    }*/
}