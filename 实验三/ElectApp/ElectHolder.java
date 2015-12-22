package ElectApp;

/**
* ElectApp/ElectHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Elect.idl
* 2015年12月20日 星期日 下午11时43分54秒 CST
*/

public final class ElectHolder implements org.omg.CORBA.portable.Streamable
{
  public ElectApp.Elect value = null;

  public ElectHolder ()
  {
  }

  public ElectHolder (ElectApp.Elect initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ElectApp.ElectHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ElectApp.ElectHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ElectApp.ElectHelper.type ();
  }

}
