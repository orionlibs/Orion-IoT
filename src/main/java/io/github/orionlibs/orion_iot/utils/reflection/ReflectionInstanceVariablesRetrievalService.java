package io.github.orionlibs.orion_iot.utils.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ReflectionInstanceVariablesRetrievalService
{
    public static Field[] getAllPrivateInstanceVariablesArray(Object object)
    {
        return GetAllPrivateInstanceVariablesArrayTask.run(object);
    }


    public static List<Field> getAllPrivateInstanceVariables(Object object)
    {
        return Arrays.asList(getAllPrivateInstanceVariablesArray(object));
    }
}