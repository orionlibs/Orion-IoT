package io.github.orionlibs.orion_iot.utils.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetAllPrivateInstanceVariablesArrayTask
{
    public static Field[] run(Class<?> aClass)
    {
        List<Field> instanceVariables = new ArrayList<>();
        instanceVariables.addAll(Arrays.asList(GetDeclaredPrivateInstanceVariablesArrayTask.run(aClass)));
        instanceVariables.addAll(Arrays.asList(GetInherittedPrivateInstanceVariablesArrayTask.run(aClass)));
        return instanceVariables.toArray(new Field[0]);
    }


    public static Field[] run(Object object)
    {
        return run(object.getClass());
    }
}