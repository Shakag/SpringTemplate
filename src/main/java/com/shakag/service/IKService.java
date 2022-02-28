package com.shakag.service;

import java.io.IOException;
import java.util.List;

public interface IKService {
    public List<String> IKAnalyzer(String originMsg) throws IOException;
}
