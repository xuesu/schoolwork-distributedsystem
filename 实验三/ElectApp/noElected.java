package ElectApp;


/**
* ElectApp/noElected.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Elect.idl
* 2015��12��20�� ������ ����11ʱ43��54�� CST
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
