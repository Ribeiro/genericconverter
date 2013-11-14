package br.com.gigio.genericconverter.enums;

import java.util.HashMap;
import java.util.Map;
import br.com.gigio.genericconverter.domain.JPMaps;
import br.com.gigio.genericconverter.implementations.CustomXmlListOfMapsConverter;
import br.com.gigio.genericconverter.implementations.ListOfMapsXmlConverter;
import br.com.gigio.genericconverter.implementations.XmlListOfMapsConverter;
import br.com.gigio.genericconverter.interfaces.Converter;

public enum ConverterFactory {
	INSTANCE;

    private Map<Key, Converter<?, ?>> converters;

    private ConverterFactory() {
            converters = new HashMap<Key, Converter<?, ?>>();
            converters.put(new Key(JPMaps.class, String.class), new ListOfMapsXmlConverter());
            converters.put(new Key(String.class, JPMaps.class), new XmlListOfMapsConverter());
            converters.put(new Key(String.class, Object.class), new CustomXmlListOfMapsConverter());
    }

    public static ConverterFactory getInstance() {
            return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <F, T> Converter<F, T> getConverter(Class<F> fromType, Class<T> toType) {
            return (Converter<F, T>) converters.get(new Key(fromType, toType));
    }

    private class Key {
            private Object class1;
            private Object class2;

            public Key(Object class1, Object class2) {
                    this.class1 = class1;
                    this.class2 = class2;
            }

            @Override
            public int hashCode() {
                    final int prime = 31;
                    int result = 1;
                    result = prime * result + getOuterType().hashCode();
                    result = prime * result
                                    + ((class1 == null) ? 0 : class1.hashCode());
                    result = prime * result
                                    + ((class2 == null) ? 0 : class2.hashCode());
                    return result;
            }

            @Override
            public boolean equals(Object obj) {
                    if (this == obj)
                            return true;
                    if (obj == null)
                            return false;
                    if (getClass() != obj.getClass())
                            return false;
                    Key other = (Key) obj;
                    if (!getOuterType().equals(other.getOuterType()))
                            return false;
                    if (class1 == null) {
                            if (other.class1 != null)
                                    return false;
                    } else if (!class1.equals(other.class1))
                            return false;
                    if (class2 == null) {
                            if (other.class2 != null)
                                    return false;
                    } else if (!class2.equals(other.class2))
                            return false;
                    return true;
            }

            private ConverterFactory getOuterType() {
                    return ConverterFactory.this;
            }

    }

}