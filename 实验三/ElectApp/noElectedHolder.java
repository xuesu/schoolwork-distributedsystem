package ElectApp;

/**
* ElectApp/noElectedHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Elect.idl
* 2015��12��20�� ������ ����11ʱ43��54�� CST
*/

public final class noElectedHolder implements org.omg.CORBA.portable.Streamable
{
  public ElectApp.noElected value = null;

  public noElectedHolder ()
  {
  }

  public noElectedHolder (ElectApp.noElected initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ElectApp.noElectedHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ElectApp.noElectedHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ElectApp.noElectedHelper.type ();
  }

}
