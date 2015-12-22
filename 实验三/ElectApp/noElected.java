package ElectApp;


/**
* ElectApp/noElected.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Elect.idl
* 2015年12月20日 星期日 下午11时43分54秒 CST
*/

public final class noElected extends org.omg.CORBA.UserException
{

  public noElected ()
  {
    super(noElectedHelper.id());
  } // ctor


  public noElected (String $reason)
  {
    super(noElectedHelper.id() + "  " + $reason);
  } // ctor

} // class noElected
