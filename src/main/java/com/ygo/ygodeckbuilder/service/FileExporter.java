package com.ygo.ygodeckbuilder.service;

import java.nio.file.Path;

public interface FileExporter {
    Path export(String fileContent, String fileName);
}
