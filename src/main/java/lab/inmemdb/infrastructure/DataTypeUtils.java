package lab.inmemdb.infrastructure;

import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.infrastructure.exceptions.IncompatibleDataTypeException;

public class DataTypeUtils {

    public static boolean isCorrespondedValue(TableAttribute attribute, String value) {
        try {
            switch (attribute.getType()) {
                case INT: {
                    Integer v = Integer.valueOf(value);
                    return true;
                }
                case INTERVAL_INT: {
                    //"Integer(1,4)"
                    Integer v = Integer.valueOf(value);
                    if (v < attribute.getType().getLeft() || v > attribute.getType().getRight()) {
                        return false;
                    }
                    return true;
                }
                case REAL: {
                    Double v = Double.valueOf(value);
                    return true;
                }
                case CHAR: {
                    return true;
                }
                case TXT: {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
