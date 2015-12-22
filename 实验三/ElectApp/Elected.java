package ElectApp;


/**
* ElectApp/Elected.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Elect.idl
* 2015��12��20�� ������ ����11ʱ43��54�� CST
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
