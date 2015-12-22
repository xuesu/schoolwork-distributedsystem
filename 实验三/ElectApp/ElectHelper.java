package ElectApp;


/**
* ElectApp/ElectHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Elect.idl
* 2015年12月20日 星期日 下午11时43分54秒 CST
*/

abstract public class ElectHelper
{
  private static String  _id = "IDL:ElectApp/Elect:1.0";

  public static void insert (org.omg.CORBA.Any a, ElectApp.Elect that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ElectApp.Elect extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (ElectApp.ElectHelper.id (), "Elect");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ElectApp.Elect read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ElectStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ElectApp.Elect value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static ElectApp.Elect narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ElectApp.Elect)
      return (ElectApp.Elect)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ElectApp._ElectStub stub = new ElectApp._ElectStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static ElectApp.Elect unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof ElectApp.Elect)
      return (ElectApp.Elect)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      ElectApp._ElectStub stub = new ElectApp._ElectStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
