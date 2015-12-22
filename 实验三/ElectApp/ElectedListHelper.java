package ElectApp;


/**
* ElectApp/ElectedListHelper.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Elect.idl
* 2015��12��20�� ������ ����11ʱ43��54�� CST
*/

abstract public class ElectedListHelper
{
  private static String  _id = "IDL:ElectApp/ElectedList:1.0";

  public static void insert (org.omg.CORBA.Any a, ElectApp.Elected[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static ElectApp.Elected[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = ElectApp.ElectedHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (ElectApp.ElectedListHelper.id (), "ElectedList", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static ElectApp.Elected[] read (org.omg.CORBA.portable.InputStream istream)
  {
    ElectApp.Elected value[] = null;
    int _len0 = istream.read_long ();
    value = new ElectApp.Elected[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = ElectApp.ElectedHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, ElectApp.Elected[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      ElectApp.ElectedHelper.write (ostream, value[_i0]);
  }

}
