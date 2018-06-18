package org.scalakoans;

import java.util.List;

public class SomeJavaClass {
    public int findSizeOfRawType(List list) {
        return list.size();
    }

    public int findSizeOfUnknownType(List<?> list) {
        return list.size();
    }
}