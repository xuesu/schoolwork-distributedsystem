package ElectApp;


/**
* ElectApp/ElectPOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从Elect.idl
* 2015年12月20日 星期日 下午11时43分54秒 CST
*/

public abstract class ElectPOA extends org.omg.PortableServer.Servant
 implements ElectApp.ElectOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getList", new java.lang.Integer (0));
    _methods.put ("castVote", new java.lang.Integer (1));
    _methods.put ("shutdown", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // ElectApp/Elect/getList
       {
         ElectApp.ElectedListHolder list = new ElectApp.ElectedListHolder ();
         this.getList (list);
         out = $rh.createReply();
         ElectApp.ElectedListHelper.write (out, list.value);
         break;
       }

       case 1:  // ElectApp/Elect/castVote
       {
         try {
           String name = in.read_string ();
           this.castVote (name);
           out = $rh.createReply();
         } catch (ElectApp.noElected $ex) {
           out = $rh.createExceptionReply ();
           ElectApp.noElectedHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // ElectApp/Elect/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ElectApp/Elect:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Elect _this() 
  {
    return ElectHelper.narrow(
    super._this_object());
  }

  public Elect _this(org.omg.CORBA.ORB orb) 
  {
    return ElectHelper.narrow(
    super._this_object(orb));
  }


} // class ElectPOA
