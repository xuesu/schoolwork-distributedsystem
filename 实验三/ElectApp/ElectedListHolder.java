package ElectApp;


/**
* ElectApp/ElectedListHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Elect.idl
* 2015年12月20日 星期日 下午11时43分54秒 CST
*/

public final class ElectedListHolder implements org.omg.CORBA.portable.Streamable
{
  public ElectApp.Elected value[] = null;

  public ElectedListHolder ()
  {
  }

  public ElectedListHolder (ElectApp.Elected[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ElectApp.ElectedListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ElectApp.ElectedListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ElectApp.ElectedListHelper.type ();
  }

}
