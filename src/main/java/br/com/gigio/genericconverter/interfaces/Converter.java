package br.com.gigio.genericconverter.interfaces;

public interface Converter< FROM, TO > {
	 public TO convert ( FROM aFromObject );
     public String getName();

}
