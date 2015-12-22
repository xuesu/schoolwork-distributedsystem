package ElectApp;

/**
* ElectApp/ElectHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Elect.idl
* 2015��12��20�� ������ ����11ʱ43��54�� CST
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
