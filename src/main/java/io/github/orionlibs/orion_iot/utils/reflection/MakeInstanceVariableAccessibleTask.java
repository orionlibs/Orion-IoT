package io.github.orionlibs.orion_iot.utils.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;

public class MakeInstanceVariableAccessibleTask
{
    public void run(Field instanceVariable) throws InaccessibleObjectException, SecurityException
    {
        instanceVariable.setAccessible(true);
    }
}