package io.github.orionlibs.orion_iot.utils.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class IsNotStaticVariableTask
{
    public static boolean run(Field variable)
    {
        return !Modifier.isStatic(variable.getModifiers());
    }
}