package com.rfs.jvm;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fileInputStream = new FileInputStream(classPath + "/" + name + ".class");
            int available = fileInputStream.available();
            byte[] bytes = new byte[available];
            fileInputStream.read(bytes);
            fileInputStream.close();
            return bytes;
        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] bytes = loadByte(name);
                return defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                throw new ClassNotFoundException();
            }
        }

        /**
         * @author: rfs
         * @create: 2021/8/24
         * @description: 重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
         **/
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {

                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();
                        if (!name.startsWith("com.rfs.javastudy")) {
                            c = this.getParent().loadClass(name);
                        } else {
                            c = findClass(name);
                        }


                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // 初始化自定义类加载器，会先初始化父类ClassLoader,其中会把自定义类加载器的父加载器设置为应用程序类加载器AppClassLoader
        MyClassLoader classLoader = new MyClassLoader("C:/test");
        Class<?> clazz = classLoader.loadClass("com.rfs.javastudy.modules.study.jvm.User");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("say", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());
    }
}
