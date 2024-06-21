package io.github.orionlibs.orion_iot.utils.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;

public class ReflectionInstanceVariablesAccessService
{
    public static void makeInstanceVariableAccessible(Field instanceVariable) throws InaccessibleObjectException, SecurityException
    {
        new MakeInstanceVariableAccessibleTask().run(instanceVariable);
    }
}