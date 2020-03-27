package com.company;

import java.util.List;

public interface ISaveable {
    void Read(List<String> list);
    List<String> Write();
}
