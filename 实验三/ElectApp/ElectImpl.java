package ElectApp;

import org.omg.CORBA.ORB;

public class ElectImpl extends ElectPOA {
	private Elected[] alist;
	
	private ORB orb;
	
	public ORB getOrb() {
		return orb;
	}

	public void setOrb(ORB orb) {
		this.orb = orb;
	}
	

	public Elected[] getAlist() {
		return alist;
	}

	public void setAlist(Elected[] alist) {
		this.alist = alist;
	}

	@Override
	public void getList(ElectedListHolder list) {
		// TODO Auto-generated method stub
		list.value = alist;
	}

	@Override
	public void castVote(String name) throws noElected {
		int ind = 0;
		for(Elected e:alist){
			if(e.name.equals(name)){
				break;
			}
			ind++;
		}
		if(alist.length == 0 || ind>=alist.length){
			throw new noElected();
		}
		alist[ind].poll++;
	}

	@Override
	public void shutdown() {
	    orb.shutdown(false);
	}

}
