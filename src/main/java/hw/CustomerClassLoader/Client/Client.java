package hw.CustomerClassLoader.Client;

import java.lang.reflect.Method;

/**
 * @author hw 2017.1.4
 * 从网络加载类文件，并使用自定义类加载器加载
 */
public class Client {

  public static void main(String[] args) {
    //这个名字的包必须和当前一致。。。
    String name = "hw.CustomerClassLoader.Store";
    String host = "127.0.0.1";
    int port = 8888;
    NetworkClassLoader networkClassLoader = new NetworkClassLoader(host, port);

    try {
      Class clazz = networkClassLoader.loadClass(name);
      Method method = clazz.getDeclaredMethod("sell", int.class, int.class);
      int result = (Integer) method.invoke(null, 10, 20);
      System.out.println("result " + result + " \nclassloader :" + clazz.getClassLoader());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
