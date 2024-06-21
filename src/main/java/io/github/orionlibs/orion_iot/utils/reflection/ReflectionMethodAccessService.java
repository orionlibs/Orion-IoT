package io.github.orionlibs.orion_iot.utils.reflection;

public class ReflectionMethodAccessService
{
    public static Object callMethod(String method, Object objectMethodBelongsTo)
    {
        return new CallMethodTask().run(method, objectMethodBelongsTo);
    }
}