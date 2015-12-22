package ElectApp;


/**
* ElectApp/ElectOperations.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Elect.idl
* 2015年12月20日 星期日 下午11时43分54秒 CST
*/

public interface ElectOperations 
{
  void getList (ElectApp.ElectedListHolder list);
  void castVote (String name) throws ElectApp.noElected;
  void shutdown ();
} // interface ElectOperations
