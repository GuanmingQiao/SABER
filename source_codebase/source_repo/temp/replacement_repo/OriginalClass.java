package temp.replacement_repo;

import java.util.*;

public class OriginalClass {
    public int originalMethod(List<Integer> list, int in) {
        int res = 0;
        for (int i : list) {
            res += i;
        }
        return res + in;
    }

    public String objectMethod(List<Integer> list, CustomObject c) {

	if (list.size() > 2) {
		return "list size greater than 2";
	}
	return c.getValue() + "_" + list.size();
    }

    public OriginalClass() {

    }
}
