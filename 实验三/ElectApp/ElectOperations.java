package ElectApp;


/**
* ElectApp/ElectOperations.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��Elect.idl
* 2015��12��20�� ������ ����11ʱ43��54�� CST
*/

public interface ElectOperations 
{
  void getList (ElectApp.ElectedListHolder list);
  void castVote (String name) throws ElectApp.noElected;
  void shutdown ();
} // interface ElectOperations
