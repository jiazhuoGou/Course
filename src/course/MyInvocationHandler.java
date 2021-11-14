package course;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "方法调用前");
        // 当代理对象调用实现类的方法时，其会自动跳转到代理对象
        // 关联的handler的invoke方法
        method.invoke(object, args);
        System.out.println(method.getName() + "方法调用后");
        return null;
    }
}
