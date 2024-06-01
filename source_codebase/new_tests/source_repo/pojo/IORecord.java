package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IORecord implements Serializable {
	public String methodKey;
	public String stackInfo;
	public int id = -1;
	public List<Object> sortedInputs = new ArrayList<>();
	public List<Object> sortedOutputs = new ArrayList<>();
	public String stamp;
}