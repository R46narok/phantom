package com.phantom.shell;

import java.io.IOException;

public interface InputProvider {
    String readNextLine() throws IOException;
}
