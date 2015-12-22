package ElectApp;


/**
* ElectApp/Elected.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Elect.idl
* 2015年12月20日 星期日 下午11时43分54秒 CST
*/

public final class Elected implements org.omg.CORBA.portable.IDLEntity
{
  public String name = null;
  public int poll = (int)0;

  public Elected ()
  {
  } // ctor

  public Elected (String _name, int _poll)
  {
    name = _name;
    poll = _poll;
  } // ctor

} // class Elected
