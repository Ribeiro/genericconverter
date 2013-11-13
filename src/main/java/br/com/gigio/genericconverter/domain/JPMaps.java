package br.com.gigio.genericconverter.domain;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JPMaps extends AbstractCollection<Map<String, Object>> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2247016100795722104L;
	
	private final List<Map<String, Object>> maps;
	
	public JPMaps(List<Map<String, Object>> maps){
		this.maps = createInputs(maps);
	}

	@Override
	public Iterator<Map<String, Object>> iterator() {
		return maps.iterator();
	}

	@Override
	public int size() {
		return maps.size();
	}
	
	private List<Map<String, Object>> createInputs(List<Map<String, Object>> maps) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : maps) {
			list.add(new HashMap<String, Object>(map));
		}
		return list;
	}

}