package course;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) throws Exception {
        /*IStudentService studentServiceProxy = new StudentServiceProxy(new StudentService());
        studentServiceProxy.insertStudent();
        studentServiceProxy.deleteStudent();*/

        StudentService target = new StudentService();
        IStudentService studentServiceProxy = (IStudentService) getProxy(target);
        studentServiceProxy.insertStudent();
        studentServiceProxy.deleteStudent();

    }

    private static Object getProxy(final Object target) throws Exception {
        Object proxy = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),/*类加载器*/
                target.getClass().getInterfaces(),/*让代理对象和目标对象实现相同接口*/
                new InvocationHandler() {/*代理对象的方法最终都会被JVM导向它的invoke方法*/
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(method.getName() + "方法开始执行...");
                        Object result = method.invoke(target, args);
                        System.out.println(method.getName() + "方法执行结束...");
                        return result;
                    }
                }
        );
        return proxy;
    }
}
        /*
        IStudentService studentService = new StudentService(); // 实现类
        // 要代理哪个真实对象，就将该对象传进去
        InvocationHandler studentIncovationHandler =
                new MyInvocationHandler(studentService);
        // 使用JDK API动态生成一个代理对象
        IStudentService studentServiceProxy =
                (IStudentService) Proxy.newProxyInstance(
                        studentIncovationHandler.getClass().getClassLoader(),
                        studentService.getClass().getInterfaces(),
                        studentIncovationHandler); // 执行的是重写的handler invoke方法
        // 执行代理对象相应方法
        studentServiceProxy.insertStudent();
        studentServiceProxy.deleteStudent();
        */

        /*
        * 基于JDK的动态接待，
        * proxy.newProxyInstance：
        *   ClassLoader: 固定写法，指定目标类对象的类加载器，用于加载目标类及其接口的字节码文件
        *   Class<?>[] interfaces： 固定写法，指定目标类的实现的所有接口的字节码对象的数组，
        *                          通常使用目标类的字节码对象
        *   InvocationHandler h: 这个参数是一个接口，关注它里面唯一的方法invoke，在代理类对象中调用
        *   任何方法时，都会执行invoke方法，所以重写invoke方法，以增强需求
        *               Object invoke(Object proxy, Method method, Object[] args)
        *               proxy: 代理类对象的一个引用，也是newPrxyInstance返回值
        *               method: 对应的是invoke执行的方法的method对象，假如我们调用了xxx方法，该方法就是
        *                       触发了invoke的执行，那么mehtod就是xxx方法对应的反射对象
        *               args: 代理对象方法的参数
        * */


