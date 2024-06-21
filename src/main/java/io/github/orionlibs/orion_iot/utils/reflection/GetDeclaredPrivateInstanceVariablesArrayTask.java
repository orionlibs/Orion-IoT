package io.github.orionlibs.orion_iot.utils.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class GetDeclaredPrivateInstanceVariablesArrayTask
{
    public static Field[] run(Class<?> aClass) throws SecurityException
    {
        List<Field> declaredPrivateInstanceVariables = new ArrayList<Field>();
        for(Field instanceVariable : aClass.getDeclaredFields())
        {
            if(Modifier.isPrivate(instanceVariable.getModifiers()) && IsNotStaticVariableTask.run(instanceVariable))
            {
                declaredPrivateInstanceVariables.add(instanceVariable);
            }
        }
        return declaredPrivateInstanceVariables.toArray(new Field[0]);
    }


    public static Field[] run(Object object) throws SecurityException
    {
        return run(object.getClass());
    }
}