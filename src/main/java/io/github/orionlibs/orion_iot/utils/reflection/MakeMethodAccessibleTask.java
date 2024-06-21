package io.github.orionlibs.orion_iot.utils.reflection;

import java.lang.reflect.Method;

public class MakeMethodAccessibleTask
{
    public void run(Method method) throws SecurityException
    {
        method.setAccessible(true);
    }


    public void run(String method, Object objectMethodBelongsTo, Class<?>[] argumentTypes) throws SecurityException, NoSuchMethodException
    {
        objectMethodBelongsTo.getClass().getDeclaredMethod(method, argumentTypes).setAccessible(true);
    }
}