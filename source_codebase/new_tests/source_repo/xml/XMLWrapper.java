package xml;

import java.io.Serializable;

public class XMLWrapper implements Serializable {
	
	public static final int UNINITIALIZED = Integer.MIN_VALUE;
	
	public Object obj;
	
	public int deepHash;
	
	public XMLWrapper(Object obj) {
		this.obj = obj;
		this.deepHash = DeepHash.deepHash(this.obj);
	}
		
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof XMLWrapper)) {
			return false;
		}
		
		XMLWrapper tmp = (XMLWrapper) o;
		return this.deepHash == tmp.deepHash;
	}
	
	@Override
	public int hashCode() {
		return this.deepHash;
	}

}
