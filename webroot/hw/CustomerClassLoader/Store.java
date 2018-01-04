package hw.CustomerClassLoader;

/**
 * @author hw
 * 我们通过网络将Store类的class文件发送给客户端，并由客户端自定义类加载器加载来实现我们的业务逻辑
 */
public class Store {
  public static int buy(int assets, int price) throws Exception {
    if (assets >= price) {
      return assets - price;
    } else {
      throw new Exception("Your assets is lack.");
    }
  }

  public static int sell(int assets, int price) {
    return assets + price;
  }

  public static void main(String[] args) {
    Store store = new Store();
  }
}
