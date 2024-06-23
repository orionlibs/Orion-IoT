package io.github.orionlibs.orion_iot.database;

import io.github.orionlibs.core.configuration.InMemoryConfigurationService;
import io.github.orionlibs.orion_iot.config.ConfigurationService;

public class DataSecurityConfigurationLoader
{
    public static void load()
    {
        String a = ConfigurationService.getProp("data.security.encoding.and.encryption.algorithms.to.use.for.username");
        String b = ConfigurationService.getProp("data.security.decoding.and.decryption.algorithms.to.use.for.username");
        String c = ConfigurationService.getProp("data.security.encoding.and.encryption.algorithms.to.use.for.sensitive.user.data");
        String d = ConfigurationService.getProp("data.security.decoding.and.decryption.algorithms.to.use.for.sensitive.user.data");
        InMemoryConfigurationService.registerProp("data.security.encoding.and.encryption.algorithms.to.use.for.username", a);
        InMemoryConfigurationService.registerProp("data.security.decoding.and.decryption.algorithms.to.use.for.username", b);
        InMemoryConfigurationService.registerProp("data.security.encoding.and.encryption.algorithms.to.use.for.sensitive.user.data", c);
        InMemoryConfigurationService.registerProp("data.security.decoding.and.decryption.algorithms.to.use.for.sensitive.user.data", d);
    }
}