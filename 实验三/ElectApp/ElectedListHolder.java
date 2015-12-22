package ElectApp;


/**
* ElectApp/ElectedListHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Elect.idl
* 2015��12��20�� ������ ����11ʱ43��54�� CST
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
