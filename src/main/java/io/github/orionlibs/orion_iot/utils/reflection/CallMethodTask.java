package io.github.orionlibs.orion_iot.utils.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CallMethodTask
{
    public Object run(String method, Object objectMethodBelongsTo, Object[] methodArguments)
    {
        Class<?>[] methodArgumentsAsClasses = getMethodArgumentsAsClasses(methodArguments);
        Method methodToCall = null;
        try
        {
            methodToCall = objectMethodBelongsTo.getClass().getMethod(method, methodArgumentsAsClasses);
        }
        catch(NoSuchMethodException e)
        {
            return null;
        }
        catch(SecurityException e)
        {
            return null;
        }
        return run(methodToCall, objectMethodBelongsTo, methodArguments);
    }


    public Object run(String method, Object objectMethodBelongsTo)
    {
        Class<?>[] methodArgumentsAsClasses = new Class<?>[0];
        Method methodToCall = null;
        try
        {
            methodToCall = objectMethodBelongsTo.getClass().getMethod(method, methodArgumentsAsClasses);
        }
        catch(NoSuchMethodException e)
        {
            return null;
        }
        catch(SecurityException e)
        {
            return null;
        }
        return run(methodToCall, objectMethodBelongsTo, new Object[0]);
    }


    public Object run(String method, Object objectMethodBelongsTo, Class<?>[] argumentTypes, Object[] methodArguments)
    {
        Method methodToCall = null;
        try
        {
            methodToCall = objectMethodBelongsTo.getClass().getMethod(method, argumentTypes);
        }
        catch(NoSuchMethodException e)
        {
            return null;
        }
        catch(SecurityException e)
        {
            return null;
        }
        return run(methodToCall, objectMethodBelongsTo, methodArguments);
    }


    public Object run(Method method, Object objectMethodBelongsTo, Object[] methodArguments)
    {
        new MakeMethodAccessibleTask().run(method);
        Object[] methodArgumentsTemp = null;
        try
        {
            methodArgumentsTemp = buildMethodArguments(methodArguments);
            return method.invoke(objectMethodBelongsTo, methodArgumentsTemp);
        }
        catch(IllegalAccessException e)
        {
            return null;
        }
        catch(IllegalArgumentException e)
        {
            return null;
        }
        catch(InvocationTargetException e)
        {
            return null;
        }
    }


    private Class<?>[] getMethodArgumentsAsClasses(Object[] methodArguments)
    {
        Class<?>[] methodArgumentsAsClasses = new Class<?>[methodArguments.length];
        for(int i = 0; i < methodArguments.length; i++)
        {
            methodArgumentsAsClasses[i] = methodArguments[i].getClass();
        }
        return methodArgumentsAsClasses;
    }


    private Object[] buildMethodArguments(Object[] methodArguments)
    {
        Object[] methodArgumentsTemp = null;
        if(methodArguments.length == 0)
        {
            methodArgumentsTemp = new Object[] {};
        }
        else
        {
            methodArgumentsTemp = new Object[methodArguments.length];
            for(int i = 0; i < methodArguments.length; i++)
            {
                if(methodArguments[i].getClass().isArray())
                {
                    methodArgumentsTemp[i] = (Object[])methodArguments[i];
                }
                else
                {
                    methodArgumentsTemp[i] = methodArguments[i];
                }
            }
        }
        return methodArgumentsTemp;
    }
}