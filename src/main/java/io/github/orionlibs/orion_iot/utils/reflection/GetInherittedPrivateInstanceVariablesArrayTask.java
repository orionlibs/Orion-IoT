package io.github.orionlibs.orion_iot.utils.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetInherittedPrivateInstanceVariablesArrayTask
{
    public static Field[] run(Class<?> aClass) throws SecurityException
    {
        List<Field> instanceVariables = new ArrayList<Field>();
        Class<?> currentSuperClass = aClass.getSuperclass();
        while(currentSuperClass != null)
        {
            instanceVariables.addAll(Arrays.asList(GetDeclaredPrivateInstanceVariablesArrayTask.run(currentSuperClass)));
            currentSuperClass = currentSuperClass.getSuperclass();
        }
        return instanceVariables.toArray(new Field[0]);
    }


    public static Field[] run(Object object) throws SecurityException
    {
        return run(object.getClass());
    }
}